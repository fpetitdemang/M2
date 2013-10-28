class MemoClass(type):
     
    """Exemple d'une metaclasse."""
     
    def __new__(metacls, nom, bases, dict):
        """Creation de notre classe."""
        print("On cree la classe {}".format(nom))
        metacls.instanceSet = metacls.getInstanceSet
        return type.__new__(metacls, nom, bases, dict)

    def __init__(cls, nom, bases, dict):
        """Constructeur de notre metaclasse, appele quand on cree une classe."""
        type.__init__(cls, nom, bases, dict)
        cls.instanceSet = list()

    def getInstanceSet(self):
        return self.instanceSet

        
    
    
        
        
class MemoObject(MemoClass):
    __metaclass__ = MemoClass
