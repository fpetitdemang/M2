
import java.util.ArrayList;
import java.util.Iterator;


public class MemoObject {
    //redefinir l'attribut instanceSet dans toute les classes
    //redefinir les accesseurs en ecriture
    //redefinir accesseurs en lecture set<? extends MemoObject> getInstanceSet() -> utilisation des jockers???? attribut immutable
    //explorer les annotations pour reproduire code

	 private static ArrayList<MemoObject> instanceSet = new ArrayList<MemoObject>(); 
	 
 
	 public MemoObject(){
		 //acces en ecriture de instanceSet
	     //(cast) getinstanceSet.add(this)
	     instanceSet.add(this);
	 }
	 
	 
	 //acces en lecture de instanceSet
	 public ArrayList<MemoObject> getInstanceSet(){
		 ArrayList<MemoObject> resultat = new ArrayList<MemoObject>();
		 
		 Iterator<MemoObject> it = instanceSet.iterator();
		 MemoObject tmp;
		 
		 while(it.hasNext()){
			 tmp = it.next();
			 if ((tmp.getClass() == this.getClass())){
				 resultat.add(tmp);
			 }
		 }
		 
		return resultat;
		 
	 }

}
