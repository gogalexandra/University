(defun exists (l e)
    (cond
        ((and (atom l) (equal l e)) (list t))
         ((listp l) (apply #'append (mapcar #'(lambda (li) (exists li e)) l)))
    )
)