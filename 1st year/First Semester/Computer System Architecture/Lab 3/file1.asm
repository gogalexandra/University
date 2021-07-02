bits 32 ;assembling for the 32 bits architecture
; the start label will be the entry point in the program
global  start 

; Given the byte A, obtain the integer number n represented on the bits 2-4 of A. Then obtain the byte B by rotating A n positions to the right. Compute the doubleword C as follows:
; the bits 8-15 of C have the value 0
; the bits 16-23 of C are the same as the bits of B
; the bits 24-31 of C are the same as the bits of A
; the bits 0-7 of C have the value 1

extern  exit ; we inform the assembler that the exit symbol is foreign, i.e. it exists even if we won't be defining it
import  exit msvcrt.dll; exit is a function that ends the process, it is defined in msvcrt.dll
        ; msvcrt.dll contains exit, printf and all the other important C-runtime functions
segment  data use32 class=data ; the data segment where the variables are declared 
     a dw 0111 0111 0101 0111b
     b dw 1001 1011 1011 1110b
     c dw 0
segment  code use32 class=code ; code segment
start: 
     
     mov eax, 0
     mov edx, 0
     and eax, 1111 1111 0000 0000
     




     mov  bx, 0 ; we compute the result in bx

     mov  ax, [b] ; we isolate bits 10-12 of B
     and  ax, 0001110000000000b
     mov  cl, 10
     ror  ax, cl ; we rotate 10 positions to the right
     or   bx, ax ; we put the bits into the result

     or   bx, 0000000001111000b ; we force the value of bits 3-6 of the result to the value 1

     mov  ax, [a] ; we isolate bits 1-4 of A
     and  ax, 0000000000011110b
     mov  cl, 6
     rol  ax, cl ; we rotate 6 positions to the left
     or   bx, ax ; punem bitii in rezultat

     and  bx, 1110011111111111b ; facem biti 11-12 din rezultat sa aiba valoarea 0

     mov  ax, [b]
     not  ax ; we invert the value of b
     and  ax, 0000111000000000b ; we isolate the bits 9-11 of B
     mov  cl, 4
     rol  ax, cl ; we rotate 4 positions to the left
     or   bx, ax ; punem biti in rezultat

     mov  [c], bx ; we move the result from the register to the result variable

     push dword 0 ;saves on stack the parameter of the function exit
     call [exit] ;function exit is called in order to end the execution of
the program	
