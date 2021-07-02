; A text file is given. Read the content of the file, count the number of odd digits and display the result on the screen. The name of text file is defined in the data segment.
; The following code will open a file called "ana.txt" from current folder,
; and it will read maximum 100 characters from this file.

; The program will use:
; - the function fopen() to open/create the file
; - the function fread() to read from file
; - the function fclose() to close the created file.

; Because the fopen() call uses the file access mode "r", the file will be open for
; reading. The file must exist, otherwise the fopen() call will fail.
; For details about the file access modes see the section "Theory".

bits 32

global start

; declare external functions needed by our program
extern exit, fopen, fread, fclose, printf
import exit msvcrt.dll
import fopen msvcrt.dll
import fread msvcrt.dll
import fclose msvcrt.dll  
import printf msvcrt.dll    ; tell the assembler that function printf can be found in library msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    file_name db "gog.txt", 0   ; filename to be read
    access_mode db "r", 0       ; file access mode:
                                ; r - opens a file for reading. The file must exist. 
    file_descriptor dd -1       ; variable to hold the file descriptor
    len equ 100                 ; maximum number of characters to read
    text times len db 0         ; string to hold the text which is read from file
    format db " Number of odd digits is %d ", 0
    len1 dd -1

; our code starts here
segment code use32 class=code
    start:
        ; call fopen() to create the file
        ; fopen() will return a file descriptor in the EAX or 0 in case of error
        ; eax = fopen(file_name, access_mode)
        push dword access_mode     
        push dword file_name
        call [fopen]
        add esp, 4*2                ; clean-up the stack

        mov [file_descriptor], eax  ; store the file descriptor returned by fopen

        ; check if fopen() has successfully created the file (EAX != 0)
        cmp eax, 0
        je final

        ; read the text from file using fread()
        ; after the fread() call, EAX will contain the number of chars we've read 
        ; eax = fread(text, 1, len, file_descriptor)
        push dword [file_descriptor]
        push dword len
        push dword 1
        push dword text        
        call [fread]
        add esp, 4*4
        
        mov [len1], eax

        ; call fclose() to close the file
        ; fclose(file_descriptor)
        push dword [file_descriptor]
        call [fclose]
        add esp, 4
       
      final:
         
        mov ecx, [len1]
        mov esi, text
        mov ebx , 0
        repeta:
        
            mov eax, 0      ;clear eax
            lodsb           ;load in al the first char
            cmp al, 30h     ; checks to see if the char is bigger than 0
            jl here         ; if not go to end
            cmp al, 39h     ; if it is bigger than 0 , checks to see if it is smaller than 9(check if the char is a digit)
            jle is_digit
            jmp here
            
            is_digit:
                sub al,byte 48         ; sub 48 to compute the digit 
                test al,1    ;chech if odd
                jnz add_to_counter
        
            jmp here
            
          
            add_to_counter:
                inc ebx
                
            here:
            inc esi
        loop repeta
        

        push ebx
        push dword format
        call [printf]      ; call function printf for printing 
        add esp, 4 * 2    ; free parameters on the stack; 4 = size of dword; 2 = number of parameters
        ; exit(0)
        push dword 0
        call [exit]
