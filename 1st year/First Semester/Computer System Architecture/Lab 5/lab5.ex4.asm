bits 32 
global start        
extern exit ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll ; exit is a function that ends the calling process. It is defined in msvcrt.dll
; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
; our data is declared here (the variables needed by our program)
segment data use32 class=data
	s1 db 1,2,3,4 ; declare the string of bytes
    l equ $-s1 ; compute the length of the string in l
    s2 db 5,6,7,8
	d times l db 0 ; reserve l bytes for the destination string and initialize it
segment code use32 class=code
start:
	mov ecx, l ; we put the length l in ECX in order to make the loop
	mov esi, 0     
	jecxz endfor      
	startfor:
		mov al, [s1 + esi]
		mov bl, [s2 + esi]
		jpe t
                sub al,bl
                jmp here

            t:
                add al,bl
                jmp here
        here:       
            mov [d + esi], al  
            inc esi
	loop startfor
	endfor:

	; exit(0)
	push dword 0 ; push the parameter for exit onto the stack
	call [exit] ; call exit to terminate the program