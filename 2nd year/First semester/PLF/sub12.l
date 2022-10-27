(defun sub12(tree level k)
  (cond
          ((and (atom tree)(eq level k)) (list tree))
          ((atom tree) nil)
          (t (list(apply #'append(mapcar #' (lambda(li) (sub12 li (+ level 1) k)  ) tree  ) ) ))
   )
)

(defun my_append (l k)
    (if (null l) k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun lin (l)
    (cond
        ((null l) nil)
        ((atom (car l)) (cons (car l) (lin (cdr l))))
        ((listp (car l)) (my_append (lin (car l)) (lin (cdr l))))
        (T (lin (cdr l)))
    )
)

(defun main(tree k)
  (lin (sub12 tree -1 k))
)