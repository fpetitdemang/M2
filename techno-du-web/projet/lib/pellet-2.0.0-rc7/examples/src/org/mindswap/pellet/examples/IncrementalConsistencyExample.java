// Copyright (c) 2006 - 2008, Clark & Parsia, LLC. <http://www.clarkparsia.com>
// This source code is available under the terms of the Affero General Public License v3.
//
// Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
// Questions, comments, or requests for clarification: licensing@clarkparsia.com

package org.mindswap.pellet.examples;

import java.net.URI;

import org.mindswap.pellet.KnowledgeBase;
import org.mindswap.pellet.PelletOptions;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.pellet.owlapi.Reasoner;
import org.mindswap.pellet.utils.ATermUtils;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.AddAxiom;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDataFactory;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

import aterm.ATermAppl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * An example program that incrementally checks consistency through additions to
 * the ABox. The example demonstrates the necessary flags that need to be set,
 * which enable the incremental consistency checking service. Currently the
 * incremental consistency checking service can  be used through
 * the Pellet, Jena and OWL APIs. The example loads an ontology, makes ABox
 * changes and incrementally performs consistency checks.
 * 
 * @author Christian Halaschek-Wiener
 */
public class IncrementalConsistencyExample {
	// namespaces that will be used
	static final String	foaf			= "http://xmlns.com/foaf/0.1/";

	static final String	mindswap		= "http://www.mindswap.org/2003/owl/mindswap#";

	static final String	mindswappers	= "http://www.mindswap.org/2004/owl/mindswappers#";

	public static void main(String[] args) throws Exception {

		// Set flags for incremental consistency
		PelletOptions.USE_COMPLETION_QUEUE = true;
		PelletOptions.USE_INCREMENTAL_CONSISTENCY = true;
		PelletOptions.USE_SMART_RESTORE = false;

		runWithPelletAPI();

		runWithOWLAPI();
		
		runWithJenaAPIAndPelletInfGraph();
		
		runWithJenaAPIAndOntModel();
	}

	public static void runWithPelletAPI() {
		System.out.println( "\nResults after applying changes through Pellet API" );
		System.out.println( "-------------------------------------------------" );

		// read the ontology with its imports
		OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		model.read( mindswappers );

		// load the model to the reasoner
		model.prepare();

		// Get the KnolwedgeBase object
		KnowledgeBase kb = ((PelletInfGraph) model.getGraph()).getKB();

		// perform initial consistency check
		long s = System.currentTimeMillis();
		boolean consistent = kb.isConsistent();
		long e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );

		// peform ABox addition which results in a consistent KB
		ATermAppl concept = ATermUtils.makeTermAppl( mindswap + "GraduateStudent" );
		ATermAppl individual = ATermUtils.makeTermAppl( mindswappers + "JohnDoe" );
		kb.addIndividual( individual );
		kb.addType( individual, concept );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		consistent = kb.isConsistent();
		e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );

		// peform ABox addition which results in an inconsistent KB
		ATermAppl role = ATermUtils.makeTermAppl( foaf + "mbox" );
		individual = ATermUtils.makeTermAppl( mindswappers + "Christian.Halaschek" );
		ATermAppl mbox = ATermUtils.makeTermAppl( "mailto:kolovski@cs.umd.edu" );
		kb.addPropertyValue( role, individual, mbox );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		consistent = kb.isConsistent();
		e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );
	}

	public static void runWithOWLAPI() throws Exception {
		System.out.println( "\nResults after applying changes through OWL API" );
		System.out.println( "----------------------------------------------" );

		// read the ontology
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLDataFactory factory = manager.getOWLDataFactory();
		OWLOntology ontology = manager.loadOntology( URI.create( mindswappers ) );

		Reasoner reasoner = new Reasoner( manager );

		manager.addOntologyChangeListener( reasoner );

		reasoner.loadOntology( ontology );

		// perform initial consistency check
		long s = System.currentTimeMillis();
		boolean consistent = reasoner.isConsistent();
		long e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );

		// peform ABox addition which results in a consistent KB
		OWLClass concept = factory.getOWLClass( URI.create( mindswap + "GraduateStudent" ) );
		OWLIndividual individual = factory
				.getOWLIndividual( URI.create( mindswappers + "JohnDoe" ) );
		manager.applyChange( new AddAxiom( ontology, factory.getOWLClassAssertionAxiom( individual,
				concept ) ) );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		consistent = reasoner.isConsistent();
		e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );

		// peform ABox addition which results in an inconsistent KB
		OWLObjectProperty role = factory.getOWLObjectProperty( URI.create( foaf + "mbox" ) );
		individual = factory.getOWLIndividual( URI.create( mindswappers + "Christian.Halaschek" ) );
		OWLIndividual mbox = factory.getOWLIndividual( URI.create( "mailto:kolovski@cs.umd.edu" ) );
		manager.applyChange( new AddAxiom( ontology, factory.getOWLObjectPropertyAssertionAxiom(
				individual, role, mbox ) ) );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		consistent = reasoner.isConsistent();
		e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );
	}
	
	
	public static void runWithJenaAPIAndPelletInfGraph() {
		System.out.println( "\nResults after applying changes through Jena API using PelletInfGraph" );
		System.out.println( "-------------------------------------------------" );

		// read the ontology using model reader
		OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		model.setStrictMode( false );
		model.read( mindswappers );
		
		//get the PelletInfGraph object
		PelletInfGraph pelletJenaGraph = ( PelletInfGraph )model.getGraph();
		
		// perform initial consistency check
		long s = System.currentTimeMillis();
		boolean consistent = pelletJenaGraph.isConsistent();
		long e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );

		// perform ABox addition which results in a consistent KB
		Resource concept = model.getResource( mindswap + "GraduateStudent" );
		Individual individual = model.createIndividual( mindswappers + "JohnDoe", concept );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		consistent = pelletJenaGraph.isConsistent();
		e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );

		// perform ABox addition which results in an inconsistent KB
		Property role = model.getProperty( foaf + "mbox" );
		individual = model.getIndividual( mindswappers + "Christian.Halaschek" );
		RDFNode mbox = model.getIndividual( "mailto:kolovski@cs.umd.edu" );
		individual.addProperty( role, mbox );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		consistent = pelletJenaGraph.isConsistent();
		e = System.currentTimeMillis();
		System.out.println( "Consistent? " + consistent + " (" + (e - s) + "ms)" );
	}


	public static void runWithJenaAPIAndOntModel() {
		System.out.println( "\nResults after applying changes through Jena API using OntModel" );
		System.out.println( "-------------------------------------------------" );

		// read the ontology using model reader
		OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		model.setStrictMode( false );
		model.read( mindswappers );
				
		// perform initial consistency check
		long s = System.currentTimeMillis();
		model.prepare();
		long e = System.currentTimeMillis();
		
		//print time and validation report
		System.out.println( "Total time " + (e - s) + " ms)" );
		JenaReasoner.printIterator( model.validate().getReports(), "Validation Results" );

		// perform ABox addition which results in a consistent KB
		Resource concept = model.getResource( mindswap + "GraduateStudent" );
		Individual individual = model.createIndividual( mindswappers + "JohnDoe", concept );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		model.prepare();
		e = System.currentTimeMillis();

		//print time and validation report
		System.out.println( "Total time " + (e - s) + " ms)" );
		JenaReasoner.printIterator( model.validate().getReports(), "Validation Results" );

		// perform ABox addition which results in an inconsistent KB
		Property role = model.getProperty( foaf + "mbox" );
		individual = model.getIndividual( mindswappers + "Christian.Halaschek" );
		RDFNode mbox = model.getIndividual( "mailto:kolovski@cs.umd.edu" );
		individual.addProperty( role, mbox );

		// perform incremental consistency check
		s = System.currentTimeMillis();
		model.prepare();
		e = System.currentTimeMillis();

		//print time and validation report
		System.out.println( "Total time " + (e - s) + " ms)" );
		JenaReasoner.printIterator( model.validate().getReports(), "Validation Results" );
	}

	
}
