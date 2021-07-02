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

	string db 5, 25, 55, 127, 130, 5, 25
    len1 equ $-string
    substring db 5, 25
    len2 equ $-substring	
	
; our code starts here
segment code use32 class=code
    start:
        ; ...
		; Being given two strings of bytes, compute all positions where the second string appears as a substring in the first string.
        ; Example:
        ; s = 5, 25, 55, 127, 130, 5, 25
        ; st = 5, 25
		
		mov esi, string           ; esi holds the offset of string
		mov edi, substring        ; edi holds the offset of substring
		cld                       ; clead direction flag (DC = 0, we parse the strings from left to right)
		mov ecx, len1             ; ecx = len1 
		mov edx, 0                ; counts elem from substring
		
		repeta:
		
			mov al, [edi]              ;move in al the elem from string
            mov ah, [esi]              ;move in ah the elem from substring
			cmp al, ah                 ; compare the elements
			je equal                   ; jump to equal if they are equal to check the rest of the string
			jmp not_equal              ; jump to not_equalif they are not equal    
			
				equal:
					inc esi             ;goes to the next elem
					inc edi             
			
					add edx, byte 1     
					cmp edx, len2       ; check if there is no other elem in the substring
					je end_of_substring        ; if they are equal it means that the substring is found
					
					jmp here            ; if not goes to the end
					
					end_of_substring:
						mov edi, substring      ;reset edi to check if the substring appears one more time
						mov edx, 0      ;reset edx to count again the elements from substring
						mov ebx, esi    ;in ebx is calculated the position where the substring begings
						sub ebx, len2
						sub ebx, string
						add ebx, 1
						push ebx        ; position is stored in the stack
			
					
					here:
					jmp done_here
					
			not_equal:
				inc esi             ; goes to the next elem from string
				mov edi, substring          ; reset edi to search from the beging of the substring
			
			done_here:
				
			
			
		loop repeta
	
	
	
	
	
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
        