"classes sont modeler par la classe -type-"

class MemoClass(type):
        
    """Exemple d'une metaclasse."""
    def __new__(metacls, nom, bases, dict):
        """Creation de notre classe."""
        return type.__new__(metacls, nom, bases, dict)

    def __init__(cls, nom, bases, dict):
        """Constructeur de notre metaclasse, appele quand on cree une classe."""
        cls.instance_set = []
        type.__init__(cls, nom, bases, dict)

    def add_instanceMetacls(cls):
        cls.instance_set.append(cls)
 

   
    
        
        
class MemoObject:
    __metaclass__ = MemoClass
    
    "constructeur de MemoObject"
    def __init__(self):
        self.add_instance(self)

    @staticmethod
    def add_instance(cls):
        type(cls).add_instanceMetacls()

        


class Personne(MemoObject):
    pass


class Auteur(Personne):
    pass
