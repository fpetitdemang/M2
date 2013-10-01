import javax.annotation.Resource;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.VCARD;

import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import java.util.List;
import java.util.Iterator;

public class main {
	
	   	static org.jdom2.Document document;
	   	static Element racine;
	   	
	   	
	  //Ajouter cette méthodes à la classe JDOM2
	   	static void afficheALL()
	   	{
	   	   //On crée une List contenant tous les noeuds "etudiant" de l'Element racine
	   	   List listEtudiants = racine.getChildren("etudiant");

	   	   //On crée un Iterator sur notre liste
	   	   Iterator i = listEtudiants.iterator();
	   	   while(i.hasNext())
	   	   {
	   	      //On recrée l'Element courant à chaque tour de boucle afin de
	   	      //pouvoir utiliser les méthodes propres aux Element comme :
	   	      //sélectionner un nœud fils, modifier du texte, etc...
	   	      Element courant = (Element)i.next();
	   	      //On affiche le nom de l’élément courant
	   	      System.out.println(courant.getChild("nom").getText());
	   	   }
	   	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		 //On crée une instance de SAXBuilder
	      SAXBuilder sxb = new SAXBuilder();
	      try
	      {
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé ;)
	         document = sxb.build(new File("openstreetmap.xml"));
	      }
	      catch(Exception e){}

	      //On initialise un nouvel élément racine avec l'élément racine du document.
	      racine = document.getRootElement();

	      //Méthode définie dans la partie 3.2. de cet article
	      afficheALL();
		
		
		
		// TODO Auto-generated method stub
		// quelques définitions
		String personURI    = "http://somewhere/JohnSmith";
		String fullName     = "John Smith";

		// créer un modèle vide
		Model model = ModelFactory.createDefaultModel();

		// créer la ressource
		com.hp.hpl.jena.rdf.model.Resource johnSmith = model.createResource(personURI);

		// ajouter la propriété
		 johnSmith.addProperty(VCARD.FN, fullName);
		 model.write(System.out);
	}

}
