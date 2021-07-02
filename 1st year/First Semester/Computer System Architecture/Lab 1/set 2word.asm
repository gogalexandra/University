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
    a dw 2
    b dw 6
    c dw 1
    d dw 3
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(b+b)-c-(a+d)
        mov ax,[b] ; ax = b
        add ax,[b] ; ax=ax+b
        
        sub ax,[c] ; ax=ax-c
        
        mov bx,[a] ; bx=a
        add bx,[d] ; bx= bx+d
        
        sub ax,bx ; ax=ax-bx
        
        ;b+a-(4-d+2)+c+(a-b)
        
        mov cx, [b] ; cx = b        
        add cx, [a] ; cx = cx + a
        
        mov dx, 4   ; dx = 4
        sub dx, [d] ; dx = dx - d 
        add dx, 2   ; dx = dx + 2
        
        sub cx, dx  ; cx = cx - dx
        add cx, [c] ; cx = cx + c
        
        mov dx, [a] ; dx = a
        sub dx, [b] ; dx = dx - b
        
        add cx, dx  ; cx = cx + dx
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
