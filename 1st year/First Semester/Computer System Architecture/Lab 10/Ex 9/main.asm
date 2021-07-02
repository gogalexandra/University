bits 32

global start

extern exit, printf, fopen, fread, fclose   
extern double_nr
       
import exit msvcrt.dll    
import printf msvcrt.dll   
import fopen msvcrt.dll
import fread msvcrt.dll
import fclose msvcrt.dll 


segment data use32 class=data
 
    
    file_name db 'numere.txt', 0   ; filename to be read
    access_mode db 'r', 0       ; file access mode:
                                ; r - opens a file for reading. The file must exist. 
             
    format db '%d, ', 0
    
    file_descriptor dd -1       ; variable to hold the file descriptor
    len equ 100                 ; maximum number of characters to read
    text times len db 0         ; string to hold the text which is read from file
    lent db 0


segment code use32 class=code
    start:
        ; ...
        ; call fopen() to create the file
        ; fopen() will return a file descriptor in the EAX or 0 in case of error
        ; eax = fopen(file_name, access_mode)
        push dword access_mode
        push dword file_name
        call [fopen]
        add esp, 4 * 2             ; clean-up the stack

        mov [file_descriptor], eax  ; store the file descriptor returned by fopen

        ; check if fopen() has successfully created the file (EAX != 0)
        cmp eax, 0
        je final
        
        push dword [file_descriptor]
        push dword len
        push dword 1
        push dword text
        call [fread]
        add esp, 4 * 4

        mov [lent], eax                 ; the length of the text that was read

        ; call fclose() to close the file
        ; fclose(file_descriptor)
        push dword [file_descriptor]
        call [fclose]
        add esp, 4

        
        mov ecx, [lent]
        mov esi, text
        add esi, [lent]
        sub esi, 1
        std

        inverse_string:
        
            mov eax, 0
            lodsb       
            mov ebx, ecx
            
            push eax
            call double_nr
            
            push eax
            push dword format
            call [printf]
            add esp, 4 * 2
            
            mov ecx, ebx
            ; push eax
            ; call Function

        loop inverse_string

        final:
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
