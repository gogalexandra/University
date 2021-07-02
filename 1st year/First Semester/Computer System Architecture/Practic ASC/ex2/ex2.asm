bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program

extern exit, scanf, fprintf, fclose, fopen; add printf and scanf as extern functions            
import exit msvcrt.dll    
import fprintf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import scanf msvcrt.dll     ; similar for scanf

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    n db 0 
    
    format db "%c", 0
    
    filename_write db "numar.txt", 0
    acces_mode_write db "w", 0
    file_write_descriptor dd -1
    
    array resb 20

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        mov edi, 0
        
        read_numbers:
            push dword n
            push dword format
            call [scanf]
            add esp, 4*2
            
            cmp [n], byte "!"
            je final
            
            cmp [n], byte 0Ah
            je read_another
            
            ; cmp [n], byte " "
            ; je read_another
            
            sub [n], byte 30h
            mov al, [n]
            
            test al, byte 1
            jnz read_another
                                     
            add al, byte 30h
            
            mov [array + edi], al
            inc edi
          
            read_another:
        loop read_numbers
        
        final:
        
        push dword acces_mode_write
        push dword filename_write
        call [fopen]
        add esp, 4*2
        
        mov [file_write_descriptor], eax
        
        mov esi, array
        mov ecx, edi
        cld
        
        search_8:
        
            mov ebx, ecx
            mov eax, 0
            lodsb 
            cmp al,byte 38h
            je print_8
            jmp here8
            
            print_8:
                push eax
                push dword format
                push dword [file_write_descriptor]
                call [fprintf]
                add esp, 4*3
                
            mov ecx, ebx
            here8:
        loop search_8
        
        
        mov esi, array
        mov ecx, edi
        cld
        
        search_6:
        
            mov ebx, ecx
            mov eax, 0
            lodsb 
            cmp al,byte 36h
            je print_6
            jmp here6
            
            print_6:
                push eax
                push dword format
                push dword [file_write_descriptor]
                call [fprintf]
                add esp, 4*3
                
            mov ecx, ebx
            here6:
            
        loop search_6
        
        mov esi, array
        mov ecx, edi
        cld
        
        search_4:
        
            mov ebx, ecx
            mov eax, 0
            lodsb 
            cmp al, byte 34h
            je print_4
            jmp here4
            
            print_4:
                push eax
                push dword format
                push dword [file_write_descriptor]
                call [fprintf]
                add esp, 4*3
                
            mov ecx, ebx
            here4:
            
        loop search_4
        
        mov esi, array
        mov ecx, edi
        cld
        
        search_2:
        
            mov ebx, ecx
            mov eax, 0
            lodsb 
            cmp al, byte 32h
            je print_2
            jmp here2
            
            print_2:
                push eax
                push dword format
                push dword [file_write_descriptor]
                call [fprintf]
                add esp, 4*3
                
            mov ecx, ebx
            here2:
        
        loop search_2
        
        mov esi, array
        mov ecx, edi
        cld
        
        search_0:
        
            mov ebx, ecx
            mov eax, 0
            lodsb 
            cmp al, byte 30h
            je print_0
            jmp here0
            
            print_0:
                push eax
                push dword format
                push dword [file_write_descriptor]
                call [fprintf]
                add esp, 4*3
                
            mov ecx, ebx
            
            here0:
            
        loop search_0
       

        push dword [file_write_descriptor]
        call [fclose]
        add esp, 4
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
