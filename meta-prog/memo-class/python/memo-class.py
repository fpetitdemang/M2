"Solution générale : Chaque instance de MemoClass (Classe avec MemoClass comme metamodel) possede un attribut instance_set (=enss des instances directs). MemoObject est une interface des objets des classes instance de MemoClass, pour manipuler les instances des classes.."


def creer_instance(nom, bases, dict):
    print("appel cree_instance ", cls)
    instance = super(cls, bases, dict).__new__
    print(instance)
    cls.instance_set.append(instance)
    return instance

class MemoClass(type):
        
    """Exemple d'une metaclasse."""
    def __new__(metacls, nom, bases, dict):
        """Creation de notre classe."""
        print(metacls, nom)
        return type.__new__(metacls, nom, bases, dict)

    def __init__(cls, nom, bases, dict):
        """Constructeur de notre metaclasse, appele quand on cree une classe."""
        cls.instance_set = []
        type.__init__(cls, nom, bases, dict)

    def add_instanceMetacls(cls, instance):
        cls.instance_set.append(instance)
 

        
        
class MemoObject:
    __metaclass__ = MemoClass    

    def __init__(self):
        self.add_instance(self)

    @staticmethod
    def add_instance(instance):
        type(instance).add_instanceMetacls(instance)


class Personne(MemoObject):
    def __init__(self, nom):
        self.nom = nom
        super(Personne, self).__init__()

class Auteur(Personne):
    pass
