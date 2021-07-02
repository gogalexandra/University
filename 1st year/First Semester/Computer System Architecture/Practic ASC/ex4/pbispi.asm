bits 32 ; assembling for the 32 bits architecture

;citim nume fisier si nr nat N intre 0 si 5
;citim text max 100 caractere din fisier
;det si afis cuv de pe multiplii de N + nr vocale
;propozitiile au . si primul cuv se afla pe 1

; declare the EntryPoint (a label defining the very first instruction of the program)
global start    
   

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
extern printf,scanf,fopen,fclose,fread 
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fread msvcrt.dll
; our data is declared here (the variables needed by our program)
segment data use32 class=data
    nume_fis TIMES 30 db 0
    mod_acces db "r",0
    format_fis db "%s",0
    format_n db " %d",0
    format_pr db "Introdu numele fisierului:",0
    format_prn db "n= ",0
    endl db 10,13,0
    spatiu db " ",0
    sir TIMES 101 db 0
    cuvant TIMES 101 db 0
    descriptor dd -1
    len dd 0
    n dd 0
    poz dw 1
    voc dd 0
    
    
    

; our code starts here
segment code use32 class=code
    evoc:
    ;eax=1 daca e vocala eax=0 daca nu e
    
        cmp al,'a'
        je gata
        
        cmp al,'e'
        je gata
        
        cmp al,'i'
        je gata
        
        cmp al,'o'
        je gata
        
        cmp al,'u'
        je gata
        
        cmp al,'A'
        je gata
        
        cmp al,'E'
        je gata
        
        cmp al,'I'
        je gata
        
        cmp al,'O'
        je gata
        
        cmp al,'E'
        je gata
        
        mov eax,0
        jmp gata2
        
        gata:
        mov eax,1
        
        gata2:
    
        ret

    start:
    
       push dword format_pr
       call [printf]
       add esp,4
       
       push dword nume_fis
       push dword format_fis
       call [scanf]
       add esp,4*2
       
      
       push dword format_prn
       call [printf]
       add esp,4
       
       
       push dword n
       push dword format_n
       call [scanf]
       add esp,4*2
       
       push dword mod_acces
       push dword nume_fis
       call [fopen]
       add esp,4*2
       
       cmp eax,0
       je final
       
       mov [descriptor],eax
       
       push dword [descriptor]
       push dword 100
       push dword 1
       push dword sir
       call [fread]
       add esp, 4*4
       
       mov [len],eax
       
       ;afisare len
       push dword [len]
       push dword format_n
       call [printf]
       add esp,4*2
       
       ;afisam endl
                push dword endl
                call [printf]
                add esp,4
       
       
       ;afisare fis
       push dword sir
       call [printf]
       add esp,4
       
       ;afisam endl
                push dword endl
                call [printf]
                add esp,4
       
       ; pana aici e ok
       
       mov ecx,[len]
       mov esi,sir
       mov edi,cuvant
       CLD
       
       repeta:
                
            LODSB
            cmp al," "
            je verifica_poz
            
            cmp al,"."
            je verifica_poz
            
            STOSB
            
            call evoc
            
            cmp al,0
            je nuevoc
            inc byte[voc]
            
            nuevoc:
            jmp next
            verifica_poz:
                mov ax,[poz]
                div byte[n]
                
                cmp ah,0
                jne urm
                
                ;afisam cuvantul
                mov byte[EDI],0
          
                push dword cuvant
                push dword format_fis
                call [printf]
                add esp,4*2
                
                ; afisam space
                push dword [voc]
                push dword format_n
                call [printf]
                add esp,4*2
                
                ;afisam numarul de vocale
                
           
                ;afisam endl
                push dword endl
                call [printf]
                add esp,4
                
                urm:
                    inc byte[poz]
                    mov byte[voc],0
                    mov edi,cuvant
            next:  
       loop repeta
              
       
       
       push dword [descriptor]
       call [fclose]
       add esp,4
       
       final:
       
       
       
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program