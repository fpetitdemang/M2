"Solution générale : Chaque instance de MemoClass (Classe avec MemoClass comme metamodel) possede un attribut instance_set (=enss des instances directs). MemoObject est une interface des objets des classes instance de MemoClass, pour manipuler les instances des classes.."

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
        self.add_instance()

    def add_instance(self):
        type(self).add_instanceMetacls()

        


class Personne(MemoObject):
    pass


class Auteur(Personne):
    pass
