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

    ; Two byte strings S1 and S2 are given, having the same length.
    ; Obtain the string D in the following way: 
    ; each element found on the even positions of D is the sum 
    ; of the corresponding elements from S1 and S2, and each element 
    ; found on the odd positions of D is the difference of the corresponding elements from S1 and S2.
    
	mov ecx, l ; we put the length l in ECX in order to make the loop
	mov esi, 0 ;esi is 0 as the first "index" from the string
    mov eax, 0
    mov ebx, 0

	jecxz endfor  ; does the loop until ecx= 0
	startfor:
		mov al, [s1 + esi]  ; al = the elem with index esi from s1
		mov bl, [s2 + esi]  ; lb = the elem with index esi from s2
        test esi,1          ; compares the last bits of esi with 1 ->ZF is 0 if the nr is even and 1 if it is odd  
        jnz is_odd          ;jumps if zf is not 0(number is odd)
        jz is_even          ;jumps if zf is 0(number is even)
        is_even:             
            add al,bl       ;if nr even does the add and then jumps to here
            jmp here
        is_odd:
            sub al,bl       ;if nr odd the does the sub
        here:          
            mov [d + esi], al   ;puts in  string the at position esi the result
            inc esi             ; increases esi ->the index
	loop startfor
	endfor:

	; exit(0)
	push dword 0 ; push the parameter for exit onto the stack
	call [exit] ; call exit to terminate the program
