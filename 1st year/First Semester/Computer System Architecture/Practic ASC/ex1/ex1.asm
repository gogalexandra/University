bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fprintf, fread, fclose, fopen              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import fprintf msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import fread msvcrt.dll
import fclose msvcrt.dll
import fopen msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    
    filename_read db "read_from_here.txt", 0
    acces_mode_read db "r", 0
    file_read_descriptor dd -1
    
    filename_write db "write_here.txt", 0
    acces_mode_write db "w", 0
    file_write_descriptor dd -1
    format db "%s", 0
    
    len equ 100
    text times len db 0
    len_text db 0
    
    new_text times len db 0
    contor db 0
    contor_sentence db 0
    
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        push dword acces_mode_read
        push dword filename_read
        call [fopen]
        add esp, 4*2
        
        mov [file_read_descriptor], eax
        
        cmp eax, 0
        je final
        
        push dword [file_read_descriptor]
        push dword len
        push dword 1
        push dword text
        call [fread]
        add esp, 4*4
        
        mov [len_text], eax
        
        push dword [file_read_descriptor]
        call [fclose]
        add esp, 4
        
        
        push dword acces_mode_write
        push dword filename_write
        call [fopen]
        add esp, 4*2
        
        mov [file_write_descriptor], eax
            
        mov eax, 0
        mov edi, 0
        mov esi, text
        mov ecx, [len_text]
        cld
        
        going_throught_the_text:
        
            lodsb
            mov [new_text + edi], al
            inc edi
            cmp al, byte 21h
            je full_sentence
            
            jmp not_full_sentence
            
           
            full_sentence:
                add [contor], byte 1
                mov dl, [contor]
                test dl, 1
                jnz good_sentence
                
                jmp not_good_sentence
                
    
                good_sentence:
                    mov ebx, ecx
                    
                    mov [new_text + edi], byte 0
                    
                    push dword new_text
                    push dword format
                    push dword [file_write_descriptor]
                    call [fprintf]
                    add esp, 4*3
                    
                    mov ecx, ebx
                    
                    mov [new_text], dword 0
                    
                not_good_sentence:
                
                mov edi, 0
            
            not_full_sentence:
        
        loop going_throught_the_text
        
        push dword [file_read_descriptor]
        call [fclose]
        add esp, 4
     
        final:
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
