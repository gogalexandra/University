bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fscanf, fprintf, fclose, scanf        
import exit msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fscanf msvcrt.dll
import scanf msvcrt.dll    
import fprintf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
   
    file_name_input times 30 db 0
    access_mode_input db "r", 0
    file_descriptor_input dd 0

    file_name_output dd "output.txt", 0
    access_mode_output db "w", 0
    file_descriptor_output dd 0
    
    format_reading_string db "%s", 0
    format_reading_char db "%c", 0
    format_reading_nr db "%d", 0
    format_printing db "%s ", 0
  
    s db 0
    p db 0
    
    text times 30 db 0
    
    
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;reading file name
        push dword file_name_input            
        push dword format_reading_string
        call [scanf]
        add esp, 4*2
        
        ; reading special char
        push dword s            
        push dword format_reading_string
        call [scanf]
        add esp, 4*2
        
        ;reading nr
        push dword p           
        push dword format_reading_nr
        call [scanf]
        add esp, 4*2
        
        ;open input file
        push dword access_mode_input
        push dword file_name_input
        call [fopen]
        add esp, 4*2
        
        mov [file_descriptor_input], eax
        
        cmp eax, 0
        je final
        
        ;open output file
        push dword access_mode_output
        push dword file_name_output
        call [fopen]
        add esp, 4*2
        
        mov [file_descriptor_output], eax
        
        cmp eax, 0
        je final
        
        
        reading:
     
            push dword text
            push dword format_reading_string
            push dword [file_descriptor_input]
            call [fscanf]
            add esp, 4*3
            
            cmp eax, -1
            je final
            
            mov ebx, 0
            mov esi, text
            
        	repeta:
            
           		lodsb
                
            	cmp eax, 0 
            	je out_of_loop
                
                cmp bl, [p]
                jg change_char
                jmp here
                
                change_char:
                    mov dl, 0
                    mov dl, [s]
                    mov [text+ebx], dl
                 
                here:
                inc ebx
                
                
            jmp repeta

            out_of_loop:
            
            push dword text
            push dword format_printing  
            push dword [file_descriptor_output]
            call [fprintf]
            add esp, 4*3
        jmp reading
    
        push dword [file_descriptor_input]
        call [fclose]
        add esp, 4
        
        push dword [file_descriptor_output]
        call [fclose]
        add esp, 4
    
    final:
    ; exit(0)
    push    dword 0      ; push the parameter for exit onto the stack
    call    [exit]       ; call exit to terminate the program
