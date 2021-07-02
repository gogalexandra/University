bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
        string db 5, 25, 55, 127, 130 , 5, 25, 45
        len1 equ ($-string);the length of the string 
        substring db 5,25
        len2 equ ($-substring);the length of the string 
        rez times len1 db 0
    
; our code starts here
segment code use32 class=code
    start:
        ; ..
        mov esi, string
        mov edi, substring
        mov ebx, 1
        mov ecx, len2
        mov edx, 0
        jmp search
        search:
            repz cmpsb
            add ebx,len2
        cmp ecx , 0
        
        jne try_again
        je save_position
        
        save_position:
            mov eax, ebx
            sub eax ,len2
            mov [rez + edx], eax
            inc edx
            cmp ebx, len1
            jae here
            
        
        try_again:
            mov edi, substring
            jmp search
            loadsb
        here:
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
