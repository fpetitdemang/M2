// Portions Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// Clark & Parsia, LLC parts of this source code are available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package org.mindswap.pellet.examples;

import java.net.URI;
import java.util.Set;

import org.mindswap.pellet.owlapi.Reasoner;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDataProperty;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;


/*
 * Created on Oct 10, 2004
 */

/**
 * @author Evren Sirin
 */
public class OWLAPIExample {
    public final static void main(String[] args) throws Exception  {
		String file = "http://www.mindswap.org/2004/owl/mindswappers#";
		
		System.out.print("Reading file " + file + "...");
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntology(URI.create(file));

		Reasoner reasoner = new Reasoner( manager );
		System.out.println("done.");
		
		reasoner.loadOntology(ontology);
		
		reasoner.getKB().realize();
		reasoner.getKB().printClassTree();
		
		// create property and resources to query the reasoner
		OWLClass Person = manager.getOWLDataFactory().getOWLClass(URI.create("http://xmlns.com/foaf/0.1/Person"));
		OWLObjectProperty workHomepage = manager.getOWLDataFactory().getOWLObjectProperty(URI.create("http://xmlns.com/foaf/0.1/workInfoHomepage"));
		OWLDataProperty foafName = manager.getOWLDataFactory().getOWLDataProperty(URI.create("http://xmlns.com/foaf/0.1/name"));
		
		// get all instances of Person class
		Set<OWLIndividual> individuals = reasoner.getIndividuals(Person, false);
		for(OWLIndividual ind : individuals) {
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
