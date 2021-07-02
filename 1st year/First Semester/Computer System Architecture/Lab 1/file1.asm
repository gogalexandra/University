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
        a db 5
        b db 6
        c db 12
        d db 4
; our code starts here
segment code use32 class=code
    start:
        ; ...
        mov CL,[a] ;CL = a=5
        sub CL,[b] ;CL = CL -b= 5-6
        
        mov AL,[c] ;AL = 12
        cbw 
        div byte [d]; AH = AX / d, AL = AX % d
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
