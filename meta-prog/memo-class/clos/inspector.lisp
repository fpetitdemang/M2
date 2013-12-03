;navigateur 
(defun read-inspect (obj)

)

;affiche type de l'objet
;appel inspect-instance
(defun inspect-object (obj)
  (format t "~a~%" obj)
  (inspect-instance (find-class (type-of obj)) obj)
)

;parcour les attributs de l'objet
;appel inspect-slot pour chaque attributs
(defun inspect-instance (cls obj)
  (let ((attr-set (class-direct-slots cls)))
    (loop for i in attr-set
	  do (inspect-slot i obj)
	  )
    ))

;affiche nom + valeur d'un objet
(defun inspect-slot (eff-slot-def obj)
  ;attention tester intialisation de l'attribut
  (if (slot-boundp obj (slot-definition-name eff-slot-def))
      (format t "~@a : ~a~%" (slot-definition-name eff-slot-def) (slot-value obj (slot-definition-name eff-slot-def)))
    (format t "~@a : NIL" (slot-definition-name eff-slot-def)))
  ) 
