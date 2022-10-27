(defun sub73(l level)
  (cond
         ((numberp l)  (list l)) 
         ((and  (atom l) (eq (mod level 2) 0)) nil )
         ((atom l) (list l))
         (t (list (mapcan #'(lambda(li)(sub73 li (+ 1 level) ))l )))
   )
)

(defun main(l)
  (sub73 l 0)
)