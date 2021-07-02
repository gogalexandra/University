bits 32 
global start
extern exit; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll; exit is a function that ends the calling process. It is defined in msvcrt.dll
; our data is declared here (the variables needed by our program)
segment data use32 class=data
	sir  db  5, 25, 55, 127
	len equ ($-sir);the length of the string 
    d times len db 0 
	sum  db  0;variabile used for holding the sum of the digits 
; our code starts here
segment code use32 class=code
    start:
	mov esi, sir    ;in eds:esi we will store the FAR address of the string "sir"
    mov edi, d      ;in edi holds the offset of d
	cld             ;parse the string from left to right(DF=0).    
	mov ecx, len    ;we will parse the elements of the string in a loop with len iterations.
	repeta:
        mov eax, 0
        
		lodsb           ;The byte from the address <DS:ESI> is loaded in AL ; df= 0  so ESi = ESI +1   
		count_bits:
            shr al, 1           ; shift to the right the bits, so CF with get the last bit of the nr
            adc [sum], byte 0   ; in sum is calculated the sum of the bits

		cmp al, 0         ; verify if the there are bits left
        
		jne count_bits    ; if yes jump back and do one more time 
        mov al, [sum]      ; put in al the sum
        mov [sum], byte 0   ; clear sum 
        stosb               ; moves al to the address of d

	loop repeta;if there are more elements (ecx>0) resume the loop.

	;next, we obtain the 10-th base digits of the number EBX by successive divisions to 10 and then compute the sum of these digits


sfarsit:;end the program
           
        push dword 0; push the parameter for exit onto the stack
        call [exit]; call exit to terminate the program
        
