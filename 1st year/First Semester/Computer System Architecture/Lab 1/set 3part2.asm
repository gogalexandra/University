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
        a db 2
        b db 3
        c db 2
        d db 1
        
        x dw 0
        e dw 1
        f dw 3
        g dw 4
        h dw 6
        v dw 0
        
        y dw 0
        z dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(a-c)*3+b*b
        ; mov al,[a] ; al = a
        ; sub al,[c] ; al = al -c
        ; mov ah,3   ; ah = 3
        ; mul ah     ; ax = ah * al
        ; mov [x],ax ;  x = ax 
        
        ; mov al,[b] ; al = b 
        ; mul al     ; ax = al * ah
        
        ; add ax,[x] ; ax = ax + x
       
        
        ;(e + g) * 2 / (a * c) + (h – f) + b * 3
        mov al, [b]
        mov ah, byte 3
        mul ah    
    
        mov bx, [h]
        sub bx, [f]
        add bx, ax    
        
        mov ah, [a]
        mov al, [c]
        mul ah 
        mov [y],ax
        
        mov ax, 0 
        mov ax, [e]
        add ax, [g]
        mov cx, word 2
        mul cx
        push dx
        push ax
        pop eax 
        
        div word [y]
        
        add ax,bx
        
        
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
