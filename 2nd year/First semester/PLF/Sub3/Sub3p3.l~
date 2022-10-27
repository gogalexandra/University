(defun p3(tree level elem)
  (cond
          (( and (atom tree) (eq (mod level 2) 0) ) (list tree) )
          (( and (atom tree) (eq (mod level 2) 1) ) (list elem) )
          (t (list(apply #'append(mapcar #' (lambda (li)(p3 li (+ level 1 )elem ))tree))))
   )
)