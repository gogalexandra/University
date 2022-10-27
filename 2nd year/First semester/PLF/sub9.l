(defun sub9(l elem elem1)
   (cond
            ((and (atom l)      (eq l elem))  (list elem1))
            ((and (atom l) (not (eq l elem)))  (list l )  )
            (T (list(apply #'append(mapcar #'(lambda(li)(sub9 li elem elem1) )l ))))
    )
)