// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P4 - GraphTest.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * A class to test the functionality of the Graph.java class
 * @author ayujprasad
 *
 */
class GraphTest {
	
	// private field that will be used for tests
	private Graph testGraph;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// initialize field to new graph
		testGraph = new Graph();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		// set field to null
		testGraph = null;
	}

	/**
	 * Method to test if initialization has size 0
	 */
	@Test
	void test00_empty_order() {
		// if the order is not 0, fail the test
		if(testGraph.order() !=0) {
			fail("Should be 0");
		}
	}
	
	/**
	 * Method to test we can add a vertex
	 */
	@Test
	void test01_add_one_vertex() {
		// add the vertex
		testGraph.addVertex("A");
		
		// if the graph doesn't contain this vertex, fail the test
		if(!testGraph.getAllVertices().contains("A")) {
			fail("Should contain A");
		}
	}

	/**
	 * Method to test if duplicate vertices can be added
	 */
	@Test
	void test02_add_duplicate_vertex_get_order() {
		// add the vertices to the graph
		testGraph.addVertex("A");
		testGraph.addVertex("B");
		
		// add a duplicate vertex
		testGraph.addVertex("A");
		
		// if the order is not 2, fail the test
		if(testGraph.order() != 2) {
			fail("Should be 2");
		}
	}
	
	/**
	 * Method to test if the remove method works as expected
	 */
	@Test
	void test03_remove_vertex_correctly() {
		// add vertices to the graph
		testGraph.addVertex("A");
		testGraph.addVertex("B");
		testGraph.addVertex("C");
		
		// remove valid vertex from the graph
		testGraph.removeVertex("C");
		
		// if the order is not 2, fail the test
		if(testGraph.order() != 2) {
			fail("Should be 2");
		}
	}
	
	/**
	 * Method to test if the remove method works if vertex is not present
	 */
	@Test
	void test04_remove_vertex_not_present() {
		// add vertices to the graph
		testGraph.addVertex("A");
		testGraph.addVertex("B");
		testGraph.addVertex("C");
		
		// remove invalid vertex from the graph
		testGraph.removeVertex("F");
		
		// if the graph order is not 3, fail the test
		if(testGraph.order() != 3) {
			fail("Should not alter current vertices");
		}
	}
	
	/**
	 * Method to test if addEdge works with a vertex not present
	 */
	@Test
	void test05_add_edge_vertex2_not_present() {
		// add vertices to the graph
		testGraph.addVertex("A");
		testGraph.addVertex("B");
		testGraph.addVertex("C");
		
		// add the edge
		testGraph.addEdge("A", "4");
		
		// if the adjacentVertices of 4 is not A, fail the test
		if(testGraph.getAdjacentVerticesOf("4").contains("A")) {
			fail("4 should have been created");
		}
	}
	
	/**
	 * Method to test if removeEdge works as expected
	 */
	@Test
	void test06_remove_edge() {
		// add vertices to the graph
		testGraph.addVertex("A");
		testGraph.addVertex("B");
		
		// add the edges to the graph
		testGraph.addEdge("A", "4");
		testGraph.addEdge("B", "4");
		
		// if the size is not 2, fail the test
		if(testGraph.size() != 2) {
			fail("Should be 2");
		}
		
		// remove the edge
		testGraph.removeEdge("4", "A");
		
		// if the adjacentVertices of A is not 4, fail the test
		if(!testGraph.getAdjacentVerticesOf("A").contains("4")) {
			fail("4 must have removed A");
		}
	}
	
	/**
	 * Method to test if getAdjacentVertices has the correct size
	 */
	@Test
	void test07_test_adjacent_vertices_size() {
		// add the edges to the graph
		testGraph.addEdge("1", "3");
		testGraph.addEdge("1", "4");
		testGraph.addEdge("1", "5");
		testGraph.addEdge("1", "6");
		
		// store the adjacent vertices in the arrayList
		ArrayList<String> test = new ArrayList<String>(testGraph.getAdjacentVerticesOf("1"));
			// if the size is not 4, fail the test
			if(test.size() != 4) {
				fail("size should be 4");
			}		
	}
	
	/**
	 * Method to test if getAdjacentVertices has elements in the correct position
	 */
	@Test
	void test08_test_adjacent_vertices_position() {
		// add the given edges to the graph
		testGraph.addEdge("1", "3");
		testGraph.addEdge("1", "4");
		testGraph.addEdge("1", "5");
		testGraph.addEdge("1", "6");
		
		// store the adjacent vertices in the arrayList
		ArrayList<String> test = new ArrayList<String>(testGraph.getAdjacentVerticesOf("1"));
		
		// if these positions don't match, fail the test
		if(!test.get(0).equals("3") || !test.get(1).equals("4") ||
					!test.get(2).equals("5") || !test.get(3).equals("6")) {
				fail("positions don't match");
			}		
	}
	
	/**
	 * Method to see if the order is correctly updated with add and remove
	 */
	@Test
	void test09_add_5_remove_5_check_order() {
		// add the vertices to the graph
		testGraph.addVertex("A");
		testGraph.addVertex("B");
		testGraph.addVertex("C");
		testGraph.addVertex("D");
		testGraph.addVertex("E");
		
		// remove the same vertices from the graph
		testGraph.removeVertex("A");
		testGraph.removeVertex("B");
		testGraph.removeVertex("C");
		testGraph.removeVertex("D");
		testGraph.removeVertex("E");

		// if the order is not 0, fail the test
		if(testGraph.order() != 0) {
			fail("Should be 0");
		}
	}
	
	/**
	 * Method to see if size is correctly updated
	 */
	@Test
	void test10_add_5_edges_check_size() {
		// add the given edges to the graph
		testGraph.addEdge("1","2");
		testGraph.addEdge("1","3");
		testGraph.addEdge("1","4");
		testGraph.addEdge("1","5");
		testGraph.addEdge("1","6");
		
		// if the size is not 5, fail the test
		if(testGraph.size() != 5) {
			fail("Should be 5");
		}
	}
	}

