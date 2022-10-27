(defun p3(l elem)
  (cond
          (( and (atom l) (eq l elem) ) nil )
          ((atom l) (list l) )
          (t (list(apply #'append(mapcar #' (lambda (li)(p3 li elem ))l))))
   )
)