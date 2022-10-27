(defun evenLevelNode (tree level x)
        (cond
            ((and (atom tree) (eq tree x) (eq (mod level 2) 0)) (list T))
            ((and (atom tree) (eq tree x) (eq (mod level 2) 1))  nil)
            ((and (atom tree) (not (eq tree x))) nil)
            (t (mapcan #'(lambda (s) (evenLevelNode s (+ level 1) x)) tree))
        )
)

(defun wrapper (l x)
    (car (evenLevelNode l -1 x))
)

(print (wrapper '(a (b (g)) (c (d (e)) (f))) 'd))