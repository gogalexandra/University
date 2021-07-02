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
      ; the string
    string db 1, 5, 25, 55, 127,130, 5,25, 45
    stringlength equ $-string
    ; the substring
    substring db 5,25  ;"string we are searching"
    substringlength equ $-substring    
    rez times stringlength db 0
    pos db 0 
; our code starts here
segment code use32 class=code
    start:
        ; ...
    mov ebx ,0
    mov     esi, string                    ; pointer to string in RSI
    mov     edx, stringlength              ; length of string in RDX

    ; Subtract substring length to prevent looking beyond the string length,
    ; We can also check here if the substring fits in the string.
    ; If not we never can find the substring in the string

    sub edx, substringlength
    cmp edx, 0
    jl notfound
    
    ; enter the compare loop
repeat:    
    mov eax, 0
    mov edi, substring                 ; pointer to substring in RDI
    mov ecx, substringlength           ; length substring in RCX (loop counter)
    cld
    mov eax, esi
    add [pos], byte 1
    cmpsb                      ; compare string at rdi with length rcx with string in rsi
    jz       found                   ; if zero flag then substring is found within string, exit loop

    ; substring is not found yet, put substring pointer at begin of substring
    
    inc eax
    add [pos], byte 1
    mov esi, eax
    dec edx                        ; decrement length of string
    and edx, edx                   ; check remaining length to search in
    jnz repeat                  ; remaining length non-zero, repeat

notfound:
    ; else, substring wasn't found, exit loop
    ; substring not found actions

found:
    ; substring found actions
    ; rsi has address to start of substring+the length of the substring
    ; subtracting the start of the string we can calculate the offset (or index) in the string where substring starts
    mov eax, esi
    sub esi, substringlength
    mov [rez+ebx], esi
    inc ebx
    cmp eax, stringlength
    mov esi, eax
    jne repeat
    ; mov eax, [pos]
    ; sub ax, substringlength
    ; add eax, 1
    ; mov [rez+ebx], eax
    ; inc ebx
    ; jne repeat
    
    ; cmp eax, stringlength
    ; jne repeat
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
