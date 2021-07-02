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
        
        a db 3
        b db 2
        c db 1
        d dw 4
        x dw 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;–a*a + 2*(b-1) – d
        mov al,[a]      ; al = a
        mul byte [a]    ; ax = al * al
        mov word [x],0  ; x = 0 
        sub [x],ax      ; x = x - ax 
        
        mov ah,2        ; ah = 2
        mov al,[b]      ; al = b
        sub al, 1       ; al = al - 1
        mul ah          ; ax = al *ah
        
        add ax,[x]      ; ax = ax * x
        
        sub ax,[d]      ; ax = ax - d
        
        ;[(a-b)*3+c*2]-d
        ; mov al,[a]      ; al = a
        ; sub al,[b]      ; al = al - b
        ; mov ah, 3       ; ah = 3
        ; mul ah          ; ax = ah * al
        ; mov [x], ax     ;  x = ax
        
        ; mov al,[c]      ; al = c
        ; mov ah,2        ; ah = 2
        ; mul ah          ; ax = al * ah
        
        ; add ax,[x]      ; ax = ax + x 
           
        ; sub ax, [d]     ; ax = ax - d
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
