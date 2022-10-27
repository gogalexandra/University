(defun level(l node nivel)
  (cond
         ((null (car l)) nil)
         ((eq (car l) node) nivel )
         (t (or (level (cadr l) node (+ nivel 1)) (level (caddr l) node (+ 1 nivel)) ))
   )
)

(defun p3(l e)
  (cond
         ( eq (level l (car l) 0) (list l))
   )  
)