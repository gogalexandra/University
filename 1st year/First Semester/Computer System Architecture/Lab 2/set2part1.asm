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

           a dw 1
           
           b db 2
           c db 4
           d db 2
           
           e dd 2
           x dq 3
           
           rez dd 0
           
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(a*2+b/2+e)/(c-d)+x/a 
        
        mov bx,[a]      ;bx = a =1
        mov cx, 0       ;bx becomes cx:bx
        push cx
        push bx
        pop ebx     ; ebx = cx:bx = 1
        
        mov eax, dword[x+0]     ; eax = 0003
        mov edx, dword[x+4]     ; edx = 0000
        
        div ebx    ; eax = 3/1= 3  
        
        mov [rez], eax           ; rez =3

        
        mov ax, word 2      ;ax = 2
        mul word [a]        ;dx:ax = 2 * a = 2* 1 =1 
        push dx
        push ax
        pop eax         ; eax = 2
        mov ebx , eax   ; ebx = 2
        
        mov al,[b]      ;al = 2 
        mov ah,0        ;ax =2
        mov dx , 0      
        push dx
        push ax
        pop eax         ;eax = dx:ax = 2
        mov edx, 0 
        
        mov ecx, dword 2    ;ecx = 2
        
        div ecx        ; eax = edx:eax /ecx = 2/2 = 1 
        
        add eax, dword [e]    ;eax = 1+2 =3
        add eax, ebx    ;eax = 3+ 2 =5
        
        push eax    ;eax = 5 
        pop ax      ; ax = 05
        pop dx      ;dx =0
        
        mov bl , [c]    ; bl = 4
        sub bl, [d]     ; bl =4 -2 =2
        mov bh, 0       ; bx = 2
        
        div bx          ; ax = dx:ax / bx = 5/2 = 2
        
        mov dx, 0   
        push dx
        push ax
        pop eax     ;eax = 2 
        
        add eax, [rez]  ;eax= 2 +3 = 5
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
