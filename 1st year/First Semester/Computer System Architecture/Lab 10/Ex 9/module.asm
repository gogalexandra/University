bits 32     
  
global double_nr  
                         
; our code starts here
segment code use32 public code
    double_nr:
    
        sub al, 30h
        mov edx , 0
        mov dl, byte 2
        mul dl
        
        ret 4
