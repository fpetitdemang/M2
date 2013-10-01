// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public
// License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of
// proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package org.mindswap.pellet.examples;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLAxiom;
import org.semanticweb.owl.model.OWLEntity;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

import uk.ac.manchester.cs.owl.modularity.ModuleType;

import com.clarkparsia.modularity.ModularityUtils;
import com.clarkparsia.owlapi.OWL;

/**
 * <p>
 * Title: ModularityExample
 * </p>
 * <p>
 * Description: This program shows the usage of Pellet's module extraction
 * service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Clark & Parsia, LLC. <http://www.clarkparsia.com>
 * </p>
 * 
 * @author Markus Stocker
 * @author Evren Sirin
 */
public class ModularityExample {

	private final String	file	= "file:examples/data/simple-galen.owl";
	private final String	NS		= "http://www.co-ode.org/ontologies/galen#";

	private void run() throws Exception {
		// Create an OWLAPI manager that allows to load an ontology 
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

		// Load the ontology file into an OWL ontology object
		OWLOntology ontology = manager.loadOntology( URI.create( file ) );
		
		// Get some figures about the ontology and print them
		System.out.println( "The ontology contains "
			+ ontology.getLogicalAxiomCount() + " axioms, "
			+ ontology.getReferencedClasses().size() + " classes, and "
			+ ontology.getReferencedObjectProperties().size() + " properties" );
		
		// Create the signature of the module with are interested to extract
		Set<OWLEntity> signature = new HashSet<OWLEntity>();
		signature.add( OWL.Class( NS + "Heart" ) );
		signature.add( OWL.Class( NS + "Liver" ) );
		signature.add( OWL.Class( NS + "BloodPressure" ) );
		
		// Select a module type. Modules contain axioms related to the signature 
		// elements that describe how they relate to each other. There are four 
		// module types supported with the following very rough explanations:
		// * lower (top) module 
		//   contains subclasses of the signature elements
		// * upper (bot) module 
		//   contains superclasses of the signature elements
		// * upper-of-lower (bot_of_top) module 
		//   extract the upper module from the lower module
		// * lower-of-upper (top_of_bot) module - 
		//   extract the lower module from the upper module
		//
		// The module types are closely related to the locality class used. Lower
		// module is extracted with top locality and thus also called top module.
		//
		// Upper-of-lower and lower-of-upper modules tend to be smaller (compared
		// to upper and lower modules) and we'll extract upper-of-lower module in
		// this example
		ModuleType moduleType = ModuleType.BOT_OF_TOP;

		// Extract the module axioms for the specified signature
		Set<OWLAxiom> moduleAxioms = 
			ModularityUtils.extractModule( ontology, signature, moduleType );
		// Create an ontology for the module axioms  
		OWLOntology moduleOnt = manager.createOntology( moduleAxioms );
		
		// Get some figures about the extracted module and print them
		System.out.println( "The module contains "
			+ moduleOnt.getLogicalAxiomCount() + " axioms, "
			+ moduleOnt.getReferencedClasses().size() + " classes, and "
			+ moduleOnt.getReferencedObjectProperties().size() + " properties" );
	}

	public static void main(String[] args) throws Exception {
		ModularityExample app = new ModularityExample();
		app.run();
	}

}
