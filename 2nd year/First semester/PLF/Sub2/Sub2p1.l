(DEFUN F(L)
  (COND
         ((NULL L) 0)
         (T ((LAMBDA (X) 
               (COND
                      ((> X 1) (F (CDR L)))
                      (T X (F (CDR L)))
               )
          ) (F (CAR L))))
  )
)
