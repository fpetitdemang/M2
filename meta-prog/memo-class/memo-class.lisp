;meta-classe des classes qui memorisent leurs instances
(defclass memo-class (standard-class)
  ;liste des instances direct
  ((instance-set :initform '();:initform definit la valeur de l'attribut par defaut
		 :accessor get-instance-set))
  (:metaclass standard-class))

;alloue et initialise de la memoire conformement aux besoins de la classe
(defmethod make-instance ((mc memo-class) &rest params)
  (let ((instance (call-next-method)))
    ;ajoute la nouvelle instance à la collection des instances DIRECT
    (setf (get-instance-set mc)
          (cons instance (get-instance-set mc))
          )
    ;retourne l'instance crée
    instance)
)

;super-classe des objets des classes qui memorisent leurs instances 
(defclass memo-object (standard-object)
  ()
  (:metaclass standard-class)
  )

; Libération des instances pour le GC.
; ---------------------------------------------------------------------

; Libération pour memo-class.
(defmethod free-instance ((mc memo-class) item)
  ;SET field -> generic way of assigning values to data structures
  (setf (instance-set mc)
        (delete item (instance-set mc))
        )
  )

; Libération pour memo-object.
(defmethod free-object ((mo memo-object))
    (free-instance (class-of mo) mo)
)
;verifie les combinaisons possibles des couples (super-classe, instance de) 
;(SC,SC) par defaut

;instance de memo-class est sous-classe de memo-class : vrai
;(defmethod validate-superclass ((mc memo-class) (sup memo-class)) T)
;instance de memo-class est instance de std-class : faux
;(defmethod validate-superclass ((mc memo-class) (sup standard-class)) ())
;(defmethod validate-superclass ((std-class standard-class) (sup memo-class)) ())
;(defmethod validate-superclass ((std-class standard-class) (std-class standard-class)))


;TEST
;verifier que l'on à pas memorisé les instances de la sous-classe

(defun get-instances (mc)
  (get-instance-set (find-class mc))
  )

(defclass Personne (memo-object)
 ()
 (:metaclass memo-class))

(defclass SubPersonne (Personne)()(:metaclass memo-class))

(setf p1 (make-instance 'Personne))
(setf p2 (make-instance 'Personne))
(setf sp1 (make-instance 'SubPersonne))
;(setf sp2 (make-instance 'SubPersonne))

;(print p1)
;(print p2)
;(print sp1)

(setf p1 (free-object 'p1))
;(print (get-instances 'Personne))



