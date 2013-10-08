(defclass memo-class (standard-class)
  ;attribut -> liste des instances direct
  ((instance-set :initform '()));:initform definit la valeur de l'attribut par defaut
  (:metaclass standart-class))

(defmethod make-instance ((mc memo-class) &rest));herité par les instances de memo-classe -> ajoute l'instance à la liste des instances directs

;verifie les combinaisons possibles des couples (super-classe, instance de) 
;(SC,SC) par defaut
(defmethod validate-superclass ((mc memo-class)) (standart-class) (meta-class))
(defmethod validate-superclass (


