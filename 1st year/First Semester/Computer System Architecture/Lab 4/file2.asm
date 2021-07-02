; Given the word A, compute the doubleword B as follows:
; the bits 28-31 of B have the value 1;
; the bits 24- 25 and 26-27 of B are the same as the bits 8-9 of A
; the bits 20-23 of B are the invert of the bits 0-3 of A ;
; the bits 16-19 of B have the value 0
; the bits 0-15 of B are the same as the bits 16-31 of B.

; We will obtain the word C by successive "isolation" of bits sequences. The isolation
; of the bits 10-12 of B is done by obtaining the unchanged values of these bits,  
; and initialising all other bits to 0. The isolation operation can be performed
; using the operator AND between the word B and the mask
; 0001110000000000. Once isolated, the sequence of bits is put on the right  position by using a rotation operation. 
; The final word is obtained by applying the operator OR between all intermediate results obtained by using isolations and rotations.

; Observation: bits are numbered from right to left

bits 32 ;assembling for the 32 bits architecture
; the start label will be the entry point in the program
global  start 

extern  exit ; we inform the assembler that the exit symbol is foreign, i.e. it exists even if we won't be defining it
import  exit msvcrt.dll; exit is a function that ends the process, it is defined in msvcrt.dll
        ; msvcrt.dll contains exit, printf and all the other important C-runtime functions
segment  data use32 class=data ; the data segment where the variables are declared 
     a dw 0101_1100_0110_1000b
     b dd 0
    
segment  code use32 class=code ; code segment
start: 
    ; the bits 28-31 of B have the value 1
    mov ebx, dword 0000_0000_0000_0000_0000_0000_0000_0000b  ; ebx = 0
    or  ebx, dword 1111_0000_0000_0000_0000_0000_0000_0000b  ; adding 1 to the 28-31 bits
    
    ; the bits 24- 25 and 26-27 of B are the same as the bits 8-9 of A
    mov ax,[a]                      ; ax = a = 0101 1100 0110 1000
    mov cl, 8                       ; cl = 8
    ror ax, cl                      ; rotation to the right with 8 bits 0110 1000 0101 1100
    and ax, word 0000_0000_0000_0011b  ; isolatig the first 2 bits
    mov dx,0                        ;
    push dx                         ;
    push ax                         ;
    pop eax                         ; converting to eax = 0
    
    mov cl ,24      ; cl = 24
    ror ebx, cl     ; rotating ebx with 24 bits   0000 0000 0000 0000 0000 0000 1111 0000
    or ebx, eax     ; adding the bits to the result 0000 0000 0000 0000 0000 0000 1111 0000
    mov cl ,2       ; cl = 2
    ror ebx, cl     ; rotating ebx with 2 bits  0000 0000 0000 0000 0000 0000 0011 1100
    or ebx, eax     ; adding the bits to the result 0000 0000 0000 0000 0000 0000 0011 1100
    
    ; the bits 20-23 of B are the invert of the bits 0-3 of A 
    mov ax,[a]      ; ax = 0101 1100 0110 1000
    not ax          ; ax = 1010 0011 1001 0111
    and ax , word 0000_0000_0000_1111b  ; ax = 0000 0000 0000 0111 
    mov dx, 0
    push dx
    push ax 
    pop eax       ;0000 0000 0000 0000 0000 0000 0000 0111
    
    mov cl,byte 6      
    ror ebx, cl   ;   1111 0000 0000 0000 0000 0000 0000 0000 ca la inceput
    
    mov cl ,byte 20
    ror ebx, cl   ;   0000 0000 0000 0000 0000 1111 0000 0000
    or ebx, eax   ;   0000 0000 0000 0000 0000 1111 0000 0111
    
    mov cl, byte 12
    ror ebx, cl    ;  1111 0000 0111 0000 0000 0000 0000 0000 
    ; the bits 16-19 of B have the value 0
    and ebx, 1111_1111_1111_0000_1111_1111_1111_1111b      ;1111 0000 0111 0000 0000 0000 0000 0000 
     
    
    ; the bits 0-15 of B are the same as the bits 16-31 of B.
     mov eax, ebx 
     and eax , 1111_1111_1111_1111_0000_0000_0000_0000b    ;1111 0000 0111 0000 0000 0000 0000 0000
     mov cl ,byte 16
     ror eax,cl     ; eax = 0000 0000 0000 0000 1111 0000 0111 0000 
     or ebx, eax    ; ebx = 1111 0000 0111 0000 1111 0000 0111 0000
     
     mov [b], ebx 
     push dword 0 ;saves on stack the parameter of the function exit
     call [exit] ;function exit is called in order to end the execution of
