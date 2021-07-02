bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fscanf, printf, fclose        
import exit msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fscanf msvcrt.dll    
import printf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...

    file_name db "input.txt", 0
    access_mode db "r", 0
    file_descriptor dd 0
    counter db 0
    format db "%x", 0
    beautiful_format dd "%x has %d bits of 1; "
    text db 0
    
    
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        push dword access_mode
        push dword file_name
        call [fopen]
        add esp, 4*2
        
        mov [file_descriptor], eax
        
        cmp eax, 0
        je final
        
        
        
        reading:
     
            push dword text
            push dword format
            push dword [file_descriptor]
            call [fscanf]
            add esp, 4*3
            
            cmp eax, -1
            je final
            
            mov eax, 0
            mov al, [text]
            test al, byte 1
            jz not_odd
            
            mov ebx, 0
            
            count_bits:
            
                cmp al, 0
                je done_with_bits
                shr al, 1
                
                adc ebx, 0
                
            jmp count_bits
                
            done_with_bits:
            
            push ebx
            push dword [text]
            push dword beautiful_format
            call [printf]
            add esp, 4*3
            
            not_odd:
            
        jmp reading
    
        push dword [file_descriptor]
        call [fclose]
        add esp, 4
    
    final:
    ; exit(0)
    push    dword 0      ; push the parameter for exit onto the stack
    call    [exit]       ; call exit to terminate the program
