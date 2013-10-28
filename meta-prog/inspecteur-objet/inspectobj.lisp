(defmethod inspect-instance ((instance standard-class))
  (
   (print (class-of instance))
   )
  )

;Test
;(inspect-instance (make-instance 'standard-object))
