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
        a db 2
        b db 2
        c db 1
        
        e dd 2
        x dq 3

        rez dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(a+a+b*c*100+x)/(a+10)+e*a
        
        mov al,[a]          ;al = a = 2
        add al, byte 10     ;al = 12
        cbw         
        cwde                ;eax = 12
        mov [rez], eax      ;rez = 12
        
        mov al,[b]          ;al = 2
        imul byte [c]       ;ax = al * c = 2 * 1 = 2
        cwde                ;eax = 2 
        
        mov ebx , dword 100     ; ebx =100
        imul ebx                ; edx:eax = 200
        
        clc     
        add eax, dword[x+0]     ; eax = 200 +3 =203
        adc edx, dword[x+4]     ; edx = 0 
        
        mov ebx, eax    ;ebx = 203
        mov ecx, edx    ;ecx = 0    
        
        mov al,[a]      ;al = a = 2
        add al,[a]      ;al = a+a = 2 + 2  = 4
        cbw
        cwde
        cdq             ;edx:eax = 4
        
        clc
        add eax, ebx    ; eax = 203+4 =207
        adc edx, ecx    ; edx =0
        
        idiv dword [rez]    ; eax= 207 /12 = 17  edx =207 % 12= 3
           
        mov ecx,edx     ;ecx = 3
        mov ebx,eax     ;ebx = 17
        
        mov al,[a]      ;al =2
        cbw     
        cwde            ;eax = 2
        imul dword [e]  ; edx:eax = 2 * 2 = 4
        
        clc
        add eax, ebx        ;eax =  17 +4 = 21 in decimal  (eax : 0000 0015 )
        adc edx, dword 0    ;edx = edx +cf = 0+0 =0
        
  
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
