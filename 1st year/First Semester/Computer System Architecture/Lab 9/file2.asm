; A file name and a text (which can contain any type of character) are given in data segment. Calculate the sum of digits in the text. Create a file with the given name and write the result to file.
; The following code will open a file called "gog2.txt" from current folder,
; and it will read maximum 100 characters from this file.

; The program will use:
; - the function fopen() to open/create the file
; - the function fclose() to close the created file.

; Because the fopen() call uses the file access mode "r", the file will be open for
; reading. The file must exist, otherwise the fopen() call will fail.
; For details about the file access modes see the section "Theory".

bits 32

global start

; declare external functions needed by our program
extern exit, fopen, fread, fclose, fprintf
import exit msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll  
import fprintf msvcrt.dll

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
    file_name db "gog2.txt", 0   ; filename to be read
    access_mode db "w", 0       ; file access mode:
                                ; w - opens a file for writing
    file_descriptor dd -1       ; variable to hold the file descriptor
    format db " The sum of the digits is %d " , 0
    
    text db "do sum 4+3"
    len equ $-text
    rez db 0

; our code starts here
segment code use32 class=code
    start:
        
        mov ecx, len    ;ecx has the len of the text
        mov edx, 0      ;in edx is calculated the sum
        mov esi, text
        cld
        
        repeta:
        
            mov eax, 0      ;clear eax
            lodsb           ;load in al the first char
            cmp al, 30h     ; checks to see if the char is bigger than 0
            jl here         ; if not go to end
            cmp al, 39h     ; if it is bigger than 0 , checks to see if it is smaller than 9(check if the char is a digit)
            jle add_sum     ; if it is a digit jumps to add_sum
            jmp here        ; else to the end
           
            add_sum:
                sub eax, 48         ; sub 48 to compute the digit 
                add edx,eax         ; add the digit to edx
            
            here:
        loop repeta
        mov [rez], dl           ; stores the sum into rez
        
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

        ; write the text to file using fprintf()
        ; fprintf(file_descriptor, text)
        mov edx ,[rez]      ; mov in edx the rez to push the nr into the stack
        push edx
        push dword format   ; push the format 
        
        push dword [file_descriptor]
        call [fprintf]
        add esp, 4*3

        ; call fclose() to close the file
        ; fclose(file_descriptor)
        push dword [file_descriptor]
        call [fclose]
        add esp, 4
        
        
        final:
        ; exit(0)
        push dword 0
        call [exit]
