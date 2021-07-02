
bits 32
global start        

; declare extern functions used by the program
extern exit, printf         ; add printf as extern function            
import exit msvcrt.dll    
import printf msvcrt.dll    ; tell the assembler that function printf can be found in library msvcrt.dll
                          
segment data use32 class=data
        a dw 2
        b dw 3
        format db " %d * %d = %d ", 0
        
				; char strings for C functions must terminate with 0
segment  code use32 class=code
    start:
    
         mov ax, [a]
         imul word [b]
         cwde
         push eax           ; calculates a*b and stores the result in eax, then eax is pushed into the stack
         
         mov ax, [a]
         cwde
         push eax           ; in eax is now the value of a and is being push to the stack
         
         mov ax, [b]
         cwde
         push eax           ; in eax is now the value of b and is being push to the stack
         
         push dword format  ; push into the stack the format for printing on the output
         call [printf]      ; call function printf for printing 
         add esp, 4 * 8    ; free parameters on the stack; 4 = size of dword; 8 = number of parameters

        ;exit(0)
        ; push dword 0      ; push on stack the parameter for exit
        call [exit]       ; call exit to terminate the programme
