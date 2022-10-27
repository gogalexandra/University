(defun sub17(l level)
  (cond 
         ((and (atom l) (eq (mod level 2) 0)) (list 0))
         ((atom l) (list l))
         (t (list (mapcan #' (lambda(li)(sub17 li (+ level 1))) l)))
   )
)