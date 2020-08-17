// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P4 - Graph.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */
public class Graph implements GraphADT {
	
	// fields that will be used by this class
	private HashMap<String, HashSet<String>> adjacencyMap;
    private int numVertices;
    private int numEdges;
	
    /**
	 * Default no-argument constructor
	 */ 
	public Graph() {
		// initialize each field
		adjacencyMap = new HashMap<String, HashSet<String>>();
		numVertices = 0;
		numEdges = 0;
	}

	/**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void addVertex(String vertex) {
		// if vertex is null, leave the method
		if(vertex == null) {
			return;
		}
		
		// if the adjacencyMap already holds the vertex, leave the method
		if(this.adjacencyMap.containsKey(vertex)) {
			 return;
		}
		
		// otherwise, add the vertex to the Map and increment numVertices
		this.adjacencyMap.put(vertex, new HashSet<String>());
        this.numVertices++;
	}

	/**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void removeVertex(String vertex) {
		// if vertex is null, leave the method
		if(vertex == null) {
			return;
		}
		
		// if the adjacencyMap already contains the vertex,
		if(this.adjacencyMap.containsKey(vertex)) {
			// remove the vertex from the map
            this.adjacencyMap.remove(vertex);
            
            // remove all the other instances of vertex (where packages have dependencies on vertex)
            for(HashMap.Entry<String, HashSet<String>> entry : this.adjacencyMap.entrySet()) {
                if(entry.getValue().contains(vertex)) {
                    this.adjacencyMap.get(entry.getKey()).remove(vertex);
                }
            }
            // decrement numVertices and leave the method
            this.numVertices--;
            return;
        }
		// otherwise leave the method
        return;
		
	}

	/**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * add vertex, and add edge, no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
		// if vertex1 is null, leave the method
		if(vertex1 == null) {
			return;
		}
		// if vertex2 is null, leave the method
		if(vertex2 == null) {
			return;
		}
		
		// if the Map doesn't contain vertex1, add it to the map
		if(!this.adjacencyMap.containsKey(vertex1)) {
			addVertex(vertex1);
		}
		
		// if the Map doesn't contain vertex2, add it to the map
		if(!this.adjacencyMap.containsKey(vertex2)) {
			addVertex(vertex2);
		}
		
		// if the edge from vertex1 to vertex2 doesn't already exist
	    if(!this.adjacencyMap.get(vertex1).contains(vertex2)) {
	    	// add the edge
	    	this.adjacencyMap.get(vertex1).add(vertex2);
	    	// increment numEdges
	        this.numEdges++;
	        // leave the method
	        return;
	    }
	 }
	        
	
	/**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     */
	public void removeEdge(String vertex1, String vertex2) {
		// if vertex1 is null, leave the method
		if(vertex1 == null) {
			return;
		}
		// if vertex2 is null, leave the method
		if(vertex2 == null) {
			return;
		}

		// if the Map doesn't contain vertex1, add it to the map
		if(!this.adjacencyMap.containsKey(vertex1)) {
			return;
		}
		// if the Map doesn't contain vertex2, add it to the map
		if(!this.adjacencyMap.containsKey(vertex2)) {
			return;
		}

		// if the edge from vertex1 to vertex2 exists
		if(this.adjacencyMap.get(vertex1).contains(vertex2)) {
			// remove this edge
	    	this.adjacencyMap.get(vertex1).remove(vertex2);
	    	// decrement numEdges
	        this.numEdges--;
	        // leave the method
	        return;
	    }
	}	

	/**
     * Returns a Set that contains all the vertices
     * 
	 */
	public Set<String> getAllVertices() {
		// initialize a new set that will hold all the vertices 
		Set<String> visitedNodes = new HashSet<String>();
			// for each entry in the hashSet (essentially all vertices in the graph),
			for (HashMap.Entry<String, HashSet<String>> entry : this.adjacencyMap.entrySet()) {
				// add that entry to the Set
				visitedNodes.add(entry.getKey());
			}
		// return the set with all vertices
		return visitedNodes;
	}

	/**
     * Get all the neighbor (adjacent) vertices of a vertex
     *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		// initialize a new list that will hold all the neighbours of vertex
		List<String> neighbourList = new ArrayList<String>();
		// for each dependency for vertex
		for(String v: adjacencyMap.get(vertex)) {
			// add it to the list
			neighbourList.add(v);
		}
		// return the list with all the neighbours
		return neighbourList;
	}
	
	/**
     * Returns the number of edges in this graph.
     */
    public int size() {
    	// return numEdges
        return this.numEdges;
    }

	/**
     * Returns the number of vertices in this graph.
     */
	public int order() {
		// return numVertices
        return this.numVertices;
    }
}
