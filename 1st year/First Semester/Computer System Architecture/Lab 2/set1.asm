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
    ;a - byte, b - word, c - double word, d - qword
            a db 2
            b dw 1
            c dd 1
            d dq 5
            
            r resq 1
            
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(a-b)+(c-b-d)+d
        
        ; mov ax,[b] ; ax = b = 1
        ; mov dx,0   ; dx = 0 : ax becomes eax
        ; push dx    
        ; push ax 
        ; pop eax
        
        ; mov ebx, [c]     ; ebx = c = 1
        ; sub ebx, eax     ; ebx = ebx - eax = 1 - 1 = 0
        ; mov ecx, 0       ; ecx = 0 : ebx becomes a quadword ecx:ebx
        
        ; mov eax, dword [d+0]     ; eax = 00000005
        ; mov edx, dword [d+4]     ; edx = 00000000
        
        ; clc              ; cf = 0
        ; sub ebx,eax      ; ebx = ebx - eax 
        ; sbb ecx,edx      ; ecx = ecx - edx - cf
        
        ; clc              ; cf = 0
        ; add ebx,eax      ; ebx = ebx + eax
        ; adc ecx,edx      ; ecx = ecx + edx + cf
        
        ; mov eax,0
        ; mov al,[a]       ; al = a = 2
        ; mov ah, 0        ; ah = 0 : al becomes ax
        ; sub ax, [b]      ; ax = ax - b = 2 - 1 = 1
        ; mov dx, 0        ; dx = 0 : ax becomes dx:ax
        ; push dx
        ; push ax
        ; pop eax 
        ; mov edx,0        ; edx = 0 : eax becomes a quadword edx:eax
        
        ; clc              ; cf = 0
        ; add ebx,eax      ; ebx = ebx + eax = 00000001
        ; adc ecx,edx      ; ecx = ecx + edx + cf = 00000000
        
        
        ;(d+d)-(a+a)-(b+b)-(c+c)
        
        mov eax,0    ; eax = 0
        mov edx,0    ; edx = 0
        mov eax, dword[d+0]   ; eax = 00000005 
        mov edx, dword[d+4]   ; edx = 00000000
        
        clc    ; cf = 0 
        add eax, eax    ; eax = eax + eax = 00000010 (0000000A h)
        adc edx, edx    ; edx = edx + edx + cf = 00000000
        
        mov bl,[a]      ; bl = a = 2
        add bl, bl      ; bl = bl + bl = 4
        mov bh, 0       ; bh = 0 : bl becomes bx
        mov cx, 0       ; cx = 0 : bx becomex ebx
        push cx
        push bx 
        pop ebx
        mov ecx, 0      ; ecx = 0 : ebx becomex quadword ecx:ebx
        
        clc             ; cf = 0
        sub eax, ebx    ; eax = eax - ebx = 0000006
        sbb ecx, edx    ; ecx = ecx - edx - cf = 00000000
        
        mov ebx, 0      ; ebx = 0
        mov bx ,[b]     ; bx = b = 1
        add bx,bx       ; bx = bx + bx = 1 + 1 = 2
        mov cx, 0       ; cx = 0 : bx becomes cx:bx
        push cx
        push bx 
        pop ebx 
        mov ecx, 0      ; ecx = 0 : ebx becomes quadword ecx:ebx 
        
        clc             ; cf = 0
        sub eax, ebx    ; eax = eax - ebx = 0000004
        sbb ecx, edx    ; ecx = ecx - edx - cf = 00000000
        
        mov ebx, 0      ; ebx = 0
        mov ebx, [c]    ; ebx = c = 1
        add ebx, ebx    ; ebx = ebx + ebx = 1 + 1 =2 
        mov ecx, 0      ; ecx = 0 : ebx becomes quadword ecx:ebx
        
        clc             ; cf = 0 
        sub eax, ebx    ; eax = eax - ebx = 0000002
        sbb ecx, edx    ; ecx = ecx - edx - cf = 00000000
        
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
