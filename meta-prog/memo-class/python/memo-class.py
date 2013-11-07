"classes sont modeler par la classe -type-"

class MemoClass(type):
     
    """Exemple d'une metaclasse."""
    def __new__(metacls, nom, bases, dict):
        """Creation de notre classe."""
        print("On cree la classe {}".format(nom))
        "Cree un attribut de classe"
        dictionnaire = {"instance_set":[]}
        return type.__new__(metacls, nom, bases, dictionnaire)

    def __init__(cls, nom, bases, dict):
        """Constructeur de notre metaclasse, appele quand on cree une classe."""
        cls.instance_set = [cls]
        type.__init__(cls, nom, bases, dict)

   
    
        
        
class MemoObject:
    __metaclass__ = MemoClass
    
    "constructeur de MemoObject"
    def __init__(self):
        print("appel constructeur MemoObject")
        self.add_instance()

    def add_instance():
        type(self).instance_set.append(self)


class Personne(MemoObject):
    pass


class Toto:
    __metaclass__ = MemoClass
    def __init__(self):
        print("hello")
