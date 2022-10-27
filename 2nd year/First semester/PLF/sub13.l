(defun sub13(l level k)
  (cond
         ((and (atom l)(eq level k)) (list 0))
         ((atom l) (list l))
         (t (list (mapcan #' (lambda (li) (sub13 li (+ level 1) k))l)))
   )
)