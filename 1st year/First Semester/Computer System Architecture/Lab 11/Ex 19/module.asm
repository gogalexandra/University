bits 32     
  
global _double_nr  
                         
; our code starts here
segment code use32 public code
    _double_nr:
        push ebp
        mov ebp, esp
        
        mov eax, [ebp + 8]              ; eax <- a
    
    
        sub al, 30h
        mov edx , 0
        mov dl, byte 2
        mul dl
        
        
        mov esp, ebp
        pop ebp
        
        ret 
