(defclass Point ()
  ((x :initform 0 ;initialisation valeur de attribut
      :initarg :x ;init valeur à affecter
      :accessor point-x);accesseur pour acceder à l'attribut
   (y :initform 1
      :initarg :y
      :accessor point-y)))

;;clisp
;;(load 'exemple.lisp)
;;(point-x (make-instance 'Point :x 3 :y 4))
