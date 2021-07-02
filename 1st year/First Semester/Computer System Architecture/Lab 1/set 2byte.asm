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
    a db 6
    b db 1
    c db 9
    d db 1
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(a-b)+(c-b-d)+d
        mov AL,[a]; al = a
        sub AL,[b]; al = al-b
        
        mov BL,[c]; bl = c
        sub BL,[b]; bl = bl-b
        sub BL,[d]; bl = bl-c
        
        add AL,BL ; al = al + bhl
        add AL,[d]; al = al +d
        
        ;d-(a+b)-c
        mov DL,[d]; DL = d
        
        mov CL,[a]; CL = a
        add CL,[b]; CL = CL + b
        
        sub DL,CL ; DL = DL - CL
        sub DL,[c]; DL = DL - c
    
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
