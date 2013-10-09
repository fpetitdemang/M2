(defclass memo-class (standard-class)
  ;liste des instances direct
  ((instance-set :initform '();:initform definit la valeur de l'attribut par defaut
		 :accessor get-instance-set))
  (:metaclass standart-class))

;alloue et initialise de la memoire conformement aux besoins de la classe
(defmethod make-instance ((mc memo-class) &rest)
  (let ((instance (call-next-method)))
    ;ajoute la nouvelle instance à la collection des instances direct
    (setf (get-instance-set mc)
          (cons instance (get-instance-set mc))
          )
    ;retourne l'instance crée
    instance)
)



;verifie les combinaisons possibles des couples (super-classe, instance de) 
;(SC,SC) par defaut
(defmethod validate-superclass ((mc memo-class) (mc memo-class)) T)
(defmethod validate-superclass (() ()) )
(defmethod validate-superclass (() ()) )
(defmethod validate-superclass (() ()) )


