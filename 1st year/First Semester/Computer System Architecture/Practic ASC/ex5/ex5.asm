bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, scanf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll                    ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    mesaj_codat times 100 db 0 
    format db "%s", 0
    file_name db "mesaj_original.txt", 0
    acces_mode_write db "w", 0
    file_write_descriptor dd -1
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword mesaj_codat
        push dword format
        call [scanf]
        add esp, 4*2
        
        mov esi, mesaj_codat
        repeta:
            lodsb
            jmp repeta
            
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
