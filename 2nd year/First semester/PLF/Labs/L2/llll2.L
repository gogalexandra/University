
(defun left-subtree(tree)
    (car(cdr tree)))

(defun right-subtree(tree)
     (car(cdr(cdr tree))))

(defun postorder (tree)
  (cond 
      ((null tree) nil)
      (t (postorder (left-subtree tree)) (postorder (right-subtree tree)) (print (car tree)))
   )
) 