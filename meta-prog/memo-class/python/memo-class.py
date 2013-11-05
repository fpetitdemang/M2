"classes sont modeler par la classe -type-"

class MemoClass(type):
     
    """Exemple d'une metaclasse."""

    def __init__(cls, nom, bases, dict):
        """Constructeur de notre metaclasse, appele quand on cree une classe."""
        type.__init__(cls, nom, bases, dict)
    

        
    
    
        
        
class MemoObject(MemoClass):
    __metaclass__ = MemoClass
    
    "constructeur de MemoObject"
    "def __init__():"
