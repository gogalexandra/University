(defun maxLevel(tree level)
  (cond
          ((atom tree) level)
          (t (apply #' max(mapcar #'(lambda(li) (maxLevel li (+ 1 level))) tree )))
   )
)

(defun heigth(tree lMax elem)
  (cond
          ((and (atom tree) (eq tree elem)) lMax )
          ((atom tree) -1)
          (t (apply '* ( mapcar #'(lambda(li)(heigth li (- lMax 1) elem) )  tree  )))
   )
)

(defun main(tree elem)
  (heigth tree (maxLevel tree 0) elem)
)