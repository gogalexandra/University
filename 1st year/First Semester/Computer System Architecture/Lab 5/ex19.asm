bits 32 
global start        
extern exit ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll ; exit is a function that ends the calling process. It is defined in msvcrt.dll
; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
; our data is declared here (the variables needed by our program)
segment data use32 class=data
	A db 2, 1, 3, -3, -4, 2, -6 ; declare the string of bytes
    lA equ $-A
    B db 4, 5, -5, 7, -6, -2, 1
    lB equ $-B ; compute the length of the string in l
    
	R times lA+lB db 0 ; reserve l bytes for the destination string and initialize it
    
segment code use32 class=code
start:
    ;Two byte strings A and B are given. Obtain the string R that contains only the even negative elements of the two strings
	mov ecx, lA ; we put the length lA in ECX in order to make the loop for string A
	mov esi, 0   ;esi is 0 as the first "index" from the string
    mov eax, 0
    mov ebx, 0
    jecxz endfor1  
	startfor1:
        mov al, [A+esi]     ;al = the elem with index esi from A
        cmp al,0            ; compares the value from al with 0
        jl negative_nr1
            jmp here1       ; If the elem is positive jumps to here
        negative_nr1:
            test al,1       ; compares the last bit of al with 1: ZF is 0 if nr is even, ZF is 1 if nr odd
            jz even_nr1     ; if zf is 0 jumps to the label 
                jmp here1   ; if the elem is odd jumps to here
            even_nr1:
                mov [R+ebx],al     ;moves in R the number if it is even and negative
                inc ebx         ; increses ebx to move to the space reserved for the next elem from R
        here1:           
            inc esi         ; increses esi to move to the next elem
    loop startfor1
	endfor1:
    
    mov ecx, lB     ; we put the length lb in ECX in order to make the loop for string B
    mov esi, 0      ;esi is 0 as the first "index" from the string
    jecxz endfor2  
	startfor2:
        mov al, [B+esi]     ;al = the elem with index esi from B
        cmp al,0            ; compares the value from al with 0
        jl negative_nr2
            jmp here2       ; If the elem is positive jumps to here
        negative_nr2:
            test al,1       ; compares the last bit of al with 1: ZF is 0 if nr is even, ZF is 1 if nr odd
            jz even_nr2     ; if zf is 0 jumps to the label 
                jmp here2   ; if the elem is odd jumps to here
            even_nr2:
                mov [R+ebx],al   ;moves in R the number if it is even and negative
                inc ebx          ; increses ebx to move to the space reserved for the next elem from R
        here2:           
            inc esi         ; increses esi to move to the next elem
    loop startfor2
	endfor2:
   
	; exit(0)
	push dword 0 ; push the parameter for exit onto the stack
	call [exit] ; call exit to terminate the program