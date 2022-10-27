(defun sub3(l level)
  (cond
          (( and (atom l) (eq (mod level 2) 0) ) (list 0) )
          (( and (atom l) (eq (mod level 2) 1) ) (list l) )
          (t (list(apply #'append(mapcar #' (lambda (li)(sub3 li (+ level 1 )))l))))
   )
)