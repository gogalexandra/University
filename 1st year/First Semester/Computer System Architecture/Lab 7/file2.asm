; The code below will print message ”n=”, then will read from keyboard the value of perameter n.
bits 32

global start        

; declare extern functions used by the programme
extern exit, printf, scanf ; add printf and scanf as extern functions            
import exit msvcrt.dll    
import printf msvcrt.dll    ; tell the assembler that function printf is found in msvcrt.dll library
import scanf msvcrt.dll     ; similar for scanf
                          
segment data use32 class=data
	a db  0     ; in this variable we'll store the value read from the keyboard
    b dw 0 
    ; char strings are of type byte
	message1  db "a=", 0  
    message2 db "b=", 0
	format  db "%d", 0  ; %d <=> a decimal number (base 10)
    
    message3 db "Yes", 0
    message4 db "No", 0
segment code use32 class=code
    start:
       
        push dword message1 ; ! on the stack is placed the address of the string, not its value
        call [printf]      ; call function printf for printing
        add esp, 4*1       ; free parameters on the stack; 4 = size of dword; 1 = number of parameters
        push dword a       ; ! addressa of a, not value
        push dword format
        call [scanf]       ; call function scanf for reading
        add esp, 4 * 2     ; free parameters on the stack
        
        
        push dword message2 ; ! on the stack is placed the address of the string, not its value
        call [printf]      ; call function printf for printing
        add esp, 4*1       ; free parameters on the stack; 4 = size of dword; 1 = number of parameters
        push dword b       ; ! addressa of b, not value
        push dword format
        call [scanf]       ; call function scanf for reading
        add esp, 4 * 2     ; free parameters on the stack
        
        mov eax, 0
        mov ebx, 0
        mov al, [a]         ; move in al the value of a
        mov bx, [b]         ; move in al the value of b
        
        searching:
        
            cmp bx, 0       ; make sure that we did no go through all the bits of b
            je not_found    ; if is 0 that means that we checked all the bits and the bits of a were not found
            
            cmp bl,al       ; if is not 0 we compare the bits from bl with the ones from al 
            je found        ; if equal then we found the bits of a in b so the message on output will be Yes
            jne not_yet     ; if not equal and b has bits felt we need to move ahead with one bit to keep searching
                            ;for the bits of a
                            
        found:
            push dword message3 ; ! on the stack is placed the address of the string, not its value
            call [printf]
            add esp, 4 * 1
            jmp here
        not_yet:
            
            shr bx, 1           ; if there are bits left to check we move to the next group of bits with shift
            jmp searching       ; jump again to search
            
         not_found:
            push dword message4 ; ! on the stack is placed the address of the string, not its value
            call [printf]  
            add esp, 4 * 1
        
        here:
        
        ; exemple a = 10 (1010b) ; b = 256 (0000_0001_0000_0000 b) -> No
        ;         a = 10 (1010b) ; b = 24913 (0110_0001_0101_0001 b) -> Yes
        ; exit(0)
        push dword 0      ; place on stack parameter for exit
        call [exit]       ; call exit to terminate the program
