// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public
// License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of
// proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package org.mindswap.pellet.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Set;

import org.mindswap.pellet.owlapi.Reasoner;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLAxiom;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDataFactory;
import org.semanticweb.owl.model.OWLException;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyCreationException;
import org.semanticweb.owl.model.OWLOntologyManager;

import com.clarkparsia.explanation.PelletExplanation;
import com.clarkparsia.explanation.io.manchester.ManchesterSyntaxExplanationRenderer;

/**
 * <p>
 * Title: ExplanationExample
 * </p>
 * <p>
 * Description: This program shows how to use Pellet's explanation service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Clark & Parsia, LLC. <http://www.clarkparsia.com>
 * </p>
 * 
 * @author Markus Stocker
 */
public class ExplanationExample {

	private final String	file	= "file:examples/data/people+pets.owl";
	private final String	NS		= "http://cohse.semanticweb.org/ontologies/people#";

	public void run() throws OWLOntologyCreationException, OWLException, IOException {
		PelletExplanation.setup();
		
		// The renderer is used to pretty print explanation
		ManchesterSyntaxExplanationRenderer renderer = new ManchesterSyntaxExplanationRenderer();
		// The writer used for the explanation rendered
		PrintWriter out = new PrintWriter( System.out );
		renderer.startRendering( out );

		// Create an OWLAPI manager that allows to load an ontology file and
		// create OWLEntities
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntology( URI.create( file ) );
		OWLDataFactory factory = manager.getOWLDataFactory();

		// Create the reasoner and load the ontology
		Reasoner reasoner = new Reasoner( manager );
		reasoner.loadOntology( ontology );

		// Create an explanation generator
		PelletExplanation expGen = new PelletExplanation( reasoner );

		// Create some concepts
		OWLClass madCow = factory.getOWLClass( URI.create( NS + "mad+cow" ) );
		OWLClass animalLover = factory.getOWLClass( URI.create( NS + "animal+lover" ) );
		OWLClass petOwner = factory.getOWLClass( URI.create( NS + "pet+owner" ) );

		// Explain why mad cow is an unsatisfiable concept
		Set<Set<OWLAxiom>> exp = expGen.getUnsatisfiableExplanations( madCow );
		out.println( "Why is " + madCow + " concept unsatisfiable?" );		
		renderer.render( exp );

		// Now explain why animal lover is a sub class of pet owner
		exp = expGen.getSubClassExplanations( animalLover, petOwner );
		out.println( "Why is " + animalLover + " subclass of " + petOwner + "?" );
		renderer.render( exp );
		
		renderer.endRendering();
	}

	public static void main(String[] args) throws OWLOntologyCreationException, OWLException,
			IOException {
		ExplanationExample app = new ExplanationExample();

		app.run();
	}
}