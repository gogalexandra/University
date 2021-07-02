;Given the byte A, obtain the integer number n represented on the bits 2-4 of A. Then obtain the byte B by rotating A n positions to the right. Compute the doubleword C as follows:
;the bits 8-15 of C have the value 0
;the bits 16-23 of C are the same as the bits of B
;the bits 24-31 of C are the same as the bits of A
;the bits 0-7 of C have the value 1

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
     a db 0101_0111b
     b db 0
     c dd 0
     n db 0
segment  code use32 class=code ; code segment
start: 
     ;obtain the integer number n represented on the bits 2-4 of A
     mov al,[a]                 ; al = a
     and al,byte 0001_1100b      ; isolate the 2-4 bits of a = 0001 0100
     mov cl, 2                  ; cl = 2
     shr al,cl                  ; shift to right with 2 bits = 0000 0101
     mov [n],al                 ; n = the 2-4 bits = 5
     
     ;obtain the byte B by rotating A n positions to the right
     mov al,[a]     ; al = a
     mov cl,[n]     ; cl = 5
     ror al,cl      ; rotating to right with 5 bits = 1011 1010
     mov [b],al     ; b = al = 1011 1010
     
     ;the bits 8-15 of C have the value 0
     mov ebx, dword 0000_0000_0000_0000_0000_0000_0000_0000b  ; ebx = 0
     
     ;the bits 16-23 of C are the same as the bits of B
     ;mov al,[b]         ; al = b
     ; mov ah,0
     ; mov dx,0
     ; push dx
     ; push ax
     ; pop eax            ; converting b to doubleword eax = b
     ; or ebx, eax        ; moving the bits of b to ebx = 0000 0000 0000 0000 0000 0000 1011 1010
     ; mov [b],al         ; b = al = 1011 1010
     ; mov cl, byte 16    ; cl = 16
     ; rol ebx, cl        ; rotating ebx to left with 16 bits so the 16-23 bits of c will be the same as b 
                        ;   0000 0000 1011 1010 0000 0000 0000 0000
     mov al,[b]         ; al = b                   
     mov cl, 16
     rol ebx, cl
     or bl,al
     rol ebx, cl
     
     ;the bits 24-31 of C are the same as the bits of A
     ; mov cl, byte 8
     ; rol ebx, cl        ; rotating to the left with 16 bits so the bits 24-31 are at the begining 
                          ;1011 1010 0000 0000 0000 0000 0000 0000 
     ; mov al,[a]         ; al = a
     ; mov ah,0
     ; mov dx,0
     ; push dx
     ; push ax
     ; pop eax            ; converting al to eax 
     ; or ebx, eax        ; puting the bits of a at the begining  1011 1010 0000 0000 0000 0000 0101 0111        
     ; ror ebx, cl        ; rotating to the right with 8 bits     0101 0111 1011 1010 0000 0000 0000 0000 
     
     mov al,[a]
     mov cl, byte 24
     ror ebx,cl
     or bl,al
     rol ebx,cl
     
     ;the bits 0-7 of C have the value 1
     or ebx,0000_0000_0000_0000_0000_0000_1111_1111b     ; adding 1 to the 0-7 bits    0101 0111 1011 1010 0000 0000 1111 1111
     mov [c],ebx

     push dword 0 ;saves on stack the parameter of the function exit
     call [exit] ;function exit is called in order to end the execution of
	
