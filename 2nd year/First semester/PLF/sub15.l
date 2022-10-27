(defun sub15(l level)
  (cond 
           ((and (numberp l) (eq (mod level 2) 1) (eq (mod l 2) 0) )  nil  )
           ( (atom l) (list l))
           (t (list(mapcan #' (lambda (li) (sub15 li (+ level 1)))l  )))
   )
)