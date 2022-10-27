;a
(defun my_len(l)
  (cond
       ((null l) 0)
       (T (+ 1 (my_len(cdr l))))
  )
)

(defun insertN (l e n)
    (cond
        
        ((< (my_len l) (- n 1)) l)
        ((equal n 1) (cons e l))
        ((null l) nil)
        (T (cons (car l) (insertN (cdr l) e (- n 1))))
    )
) 

;b 
(defun suma(l)
  (cond
         ((null l) 0)
         ((numberp (car l)) (+ (car l) (suma(cdr l))))
         ((listp (car l)) (+ (suma(car l)) (suma(cdr l))))
         (T (suma(cdr l)))
   )
)

;c
(defun my_append (l1 l2)
  (cond
       ((null l1) l2)
       (T (cons (car l1) (my_append (cdr l1) l2)))
  )
)

(defun sub(l)
  (cond
      ((null l) nil)
      ;((listp (car l)) (append (list (car l))  (sub (car l)) (sub (cdr l))))
      ((listp (car l)) (  my_append (my_append  (sub (car l)) (list(car l)) ) (sub (cdr l )) ))
      (T (sub(cdr l)))
   )
)

;d
(defun rm (l e)
    (cond
        ((null l) nil)
        ((equal (car l) e) (rm (cdr l) e))
        (T (cons (car l) (rm (cdr l) e)))
    )
)

(defun eqq(l1 l2)
  (cond
       ((and (null l1) (null l2)) T )
       ((or (null l1) (null l2)) nil)
       (T (eqq (cdr l1) (rm l2 (car l1))))
  )
)


