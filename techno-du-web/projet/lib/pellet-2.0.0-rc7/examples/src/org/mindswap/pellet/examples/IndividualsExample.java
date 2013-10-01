// Portions Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// Clark & Parsia, LLC parts of this source code are available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package org.mindswap.pellet.examples;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;

import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.pellet.owlapi.Reasoner;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDataFactory;
import org.semanticweb.owl.model.OWLDataProperty;
import org.semanticweb.owl.model.OWLException;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Example to demonstrate how to use the reasoner for queries related to individuals. Exact 
 * same functionality is shown for both Jena and OWL-API interfaces. 
 * 
 * @author Evren Sirin
 */
public class IndividualsExample {
    public static void main(String[] args) throws Exception {
        System.out.println("Results using Jena interface");
        System.out.println("----------------------------");
        runWithJena();
        
        System.out.println("Results using OWL-API interface");
        System.out.println("-------------------------------");
        runWithOWLAPI();
    }
    
    public static void runWithJena() {
        // ontology that will be used
        String ont = "http://www.mindswap.org/2004/owl/mindswappers#";

        // load the ontology with its imports and no reasoning
		OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		model.read( ont );

		// load the model to the reasoner
		model.prepare();
		
		// create property and resources to query the reasoner
		OntClass Person = model.getOntClass("http://xmlns.com/foaf/0.1/Person");
		Property workHomepage = model.getProperty("http://xmlns.com/foaf/0.1/workInfoHomepage");
		Property foafName = model.getProperty("http://xmlns.com/foaf/0.1/name");
		
		// get all instances of Person class
		Iterator<?> i = Person.listInstances();
		while( i.hasNext() ) {
		    Individual ind = (Individual) i.next();
		    
		    // get the info about this specific individual
		    String name = ((Literal) ind.getPropertyValue( foafName )).getString();
		    Resource type = ind.getRDFType();
		    Resource homepage = (Resource) ind.getPropertyValue(workHomepage);
		    
		    // print the results
		    System.out.println("Name: " + name);
		    System.out.println("Type: " + type.getLocalName());
		    if(homepage == null)
		        System.out.println("Homepage: Unknown");
		    else
		        System.out.println("Homepage: " + homepage);
		    System.out.println();
		}
    }
    
	public static void runWithOWLAPI() throws OWLException {
		String ont = "http://www.mindswap.org/2004/owl/mindswappers#";
		
		// create an ontology manager
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLDataFactory factory = manager.getOWLDataFactory();
		
		// read the ontology
		OWLOntology ontology = manager.loadOntology( URI.create(ont) );
		
		// load the ontology to the reasoner
		Reasoner reasoner = new Reasoner( manager );
		reasoner.setOntology( ontology );
		
		// create property and resources to query the reasoner
		OWLClass Person = factory.getOWLClass(URI.create("http://xmlns.com/foaf/0.1/Person"));
		OWLObjectProperty workHomepage = factory.getOWLObjectProperty(URI.create("http://xmlns.com/foaf/0.1/workInfoHomepage"));
		OWLDataProperty foafName = factory.getOWLDataProperty(URI.create("http://xmlns.com/foaf/0.1/name"));
		
		// get all instances of Person class
		Set<OWLIndividual> individuals = reasoner.getIndividuals(Person, false);
		for( OWLIndividual ind : individuals ) {
		    // get the info about this specific individual
		    String name = reasoner.getRelatedValue(ind, foafName).getLiteral();
		    OWLClass type = reasoner.getType(ind);
		    OWLIndividual homepage = reasoner.getRelatedIndividual(ind, workHomepage);
		    
		    // print the results
		    System.out.println("Name: " + name);
		    System.out.println("Type: " + type.getURI().getFragment());
		    if(homepage == null)
		        System.out.println("Homepage: Unknown");
		    else
		        System.out.println("Homepage: " + homepage.getURI());
		    System.out.println();
		}
	}
}
