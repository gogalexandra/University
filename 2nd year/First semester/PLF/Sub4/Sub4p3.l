(defun sub4(l)
  (cond
         ( (and (numberp l) (eq (mod l 3) 0) ) nil)
         ( (atom l) (list l))
         (t (list( apply '*(mapcar #'sub4 l) )  ) )
   )
)