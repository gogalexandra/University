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
        b dw 3
        c dd 3
        d dq 3
        
        x dd 0
        y dd 0
        
        r resq 1
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        ;(b+b)-c-(a+d)
        ; mov ebx, dword[d+0]   ; ebx = 0000 0003
        ; mov ecx, dword[d+4]   ; ecx = 0000 0000
        
        ; mov al,[a]            ; al = a = 2
        ; cbw                   ; ax = al 
        ; cwde                  ; eax = ax 
        ; cdq                   ; edx:eax = eax = 0000 0002
        
        ; clc                   ; cf = 0
        ; add ebx,eax           ; ebx = ebx + eax = 0005
        ; adc ecx,edx           ; ecx = ecx + edx + cf = 0000
        
        ; mov ax,[b]            ; ax = b = 3
        ; add ax,ax             ; ax = ax + ax = 3 + 3 = 6 
        ; cwde                  ; eax = ax
        ; cdq                   ; edx:eax = eax = 0000 0006
        
        ; clc                   ; cf = 0
        ; sub eax,ebx           ; eax = eax - ebx = 0001
        ; sbb edx,ecx           ; edx = edx - ecx - cf = 0000
        
        ; mov ebx,eax           ; ebx = eax = 0001
        ; mov ecx,edx           ; ecx = edx = 0000
        
        ; mov eax,0             ; eax = 0
        ; mov edx,0             ; edx = 0
        ; mov eax, [c]          ; eax = c = 3
        ; cdq                   ; edx:eax = eax = 0000 0003
        
        ; clc                   ; cf = 0
        ; sub ebx, eax          ; ebx = ebx - eax = 000-2 
        ; sub ecx, edx          ; ecx = edx - ecx = 0000
        
        ; mov dword [r+0],ebx 
        ; mov dword [r+4],ecx 
        
        ; push    dword 0 
        
        ;(d+a)-(c-b)-(b-a)+(c+d)
        mov ebx, dword[d+0]   ; ebx = 0003
        mov ecx, dword[d+4]   ; ecx = 0000
        
        mov eax,0      ; eax = 0
        mov edx,0      ; edx = 0
        mov al,[a]     ; al = a = 2
        cbw            ; ax= al
        cwde           ; eax = ax
        cdq            ; edx:eax = eax = 0000 0002
        
        clc            ; cf = 0 
        add ebx,eax    ; ebx = ebx + eax = 0005 
        adc ecx,edx    ; ecx = ecx + edx + cf = 0000
        
        mov edx,0      ; edx = 0 
        mov eax,0      ; eax = 0 
        mov edx, [c]   ; edx = c = 3
        mov ax, [b]    ; ax = b =3
        cwde           ; eax = ax = 3
        sub edx, eax   ; edx = edx - eax = 3 - 3 = 0
        mov eax, edx   ; eax = edx
        cdq            ; edx:eax = eax = 0000 0000 
        
        clc            ; cf = 0
        sub ebx,eax    ; ebx = ebx - eax = 0005
        sbb ecx,edx    ; ecx = ecx - edx - cf = 0000
        
        mov edx, 0     ; edx = 0
        mov dx, [b]    ; dx = b = 3
        mov eax, 0     ; eax = 0 
        mov al, [a]    ; al = a = 2
        cbw            ; ax = al 
        sub dx, ax     ; dx = dx - ax = 3 - 2 = 1 
        
        mov ax, dx     ; ax = dx = 1
        cwde           ; eax = ax
        cdq            ; edx:eax = eax = 0000 0001
        
        clc            ; cf = 0
        sub ebx,eax    ; ebx = ebx - eax = 0004
        sbb ecx,edx    ; ecx = ecx - edx - cf = 0000
        
        mov eax, 0     ; eax = 0
        mov eax, [c]   ; eax = c = 3
        cdq            ; edx:eax = 0000 0003
        
        clc                  ; cf = 0
        add eax, dword[d+0]  ; eax = eax + 0003 = 0006
        adc edx, dword[d+4]  ; edx = edx + 0000 = 0000
        
        clc            ; cf = 0
        add ebx,eax    ; ebx = ebx + eax = 000A 
        adc ecx,edx    ; ecx = ecx + edx +cf = 0000 
        
       
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
