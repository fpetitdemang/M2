import javax.annotation.Resource;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
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
	   	
	   	public static final String NS_FOAF = "http://xmlns.com/foaf/0.1/";
	   	public static final String NS_OWL = "http://www.w3.org/2002/07/owl#";
	   	public static final String NS_GEO = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	   	
	   	
	   	public static Model writePersonFOAF(){
	   		FOAF foaf = new FOAF();
	   		Model model = ModelFactory.createDefaultModel();
	   		
	   		com.hp.hpl.jena.rdf.model.Resource personne = model.createResource("http://petitdemange-franck.com");
	   		personne.addProperty(foaf.firstName, "franck");
	   		personne.addProperty(foaf.family_name, "petitdemange");
	   		return model;
	   	}

	   	
	   	public static Model writeOrganisation(){
			Model model = ModelFactory.createDefaultModel();
			String organisationURI = "http://www.natoine.fr#me";
			String strgivenName = "al-andalous";
			String strgivenLat = "X";
			String strgivenLong = "Y";
			
			//ajout d un namespace
			model.setNsPrefix("foaf", NS_FOAF);
			model.setNsPrefix("owl", NS_OWL);
			model.setNsPrefix("geo", NS_GEO);
			
			//creation des predicats
			Property name = model.createProperty( NS_OWL + "name" );
			Property lat = model.createProperty( NS_GEO + "lat" );
			Property longi = model.createProperty( NS_GEO + "long" );
			
			com.hp.hpl.jena.rdf.model.Resource orga = model.createResource(organisationURI);
			orga.addProperty(name, strgivenName);
			orga.addProperty(lat, strgivenLat);
			
			return model;
	   	}
	   	
		public static Model writePerson()
		{
			Model model = ModelFactory.createDefaultModel();
			String personURI    = "http://www.natoine.fr#me";
			String strgivenName    = "Franck";
			String strfamilyName   = "Petitdemange";
			String strfullName     = strgivenName + " " + strfamilyName;
			
			//ajout d un namespace
			model.setNsPrefix("foaf", NS_FOAF);
			//creation des proprietes
			Property firstname = model.createProperty( NS_FOAF + "firstName" );
			Property familyName = model.createProperty( NS_FOAF + "familyName" );
			Property name = model.createProperty( NS_FOAF + "name" );
			
			//TODO
			//need to create a foaf:Person not a generic resource
			com.hp.hpl.jena.rdf.model.Resource person = model.createResource(personURI);
			
			person.addProperty(firstname, strgivenName);
			person.addProperty(familyName, strfamilyName);
			person.addProperty(name, strfullName);
			
			return model;
		}
	   	
	   	
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
	      //afficheALL();
		
		
			Model model = writePersonFOAF();
			model.write(System.out, "RDF/XML-ABBREV");
	}

}
