// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P4 - PackageManager.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {
    
	// graph field that will be used by this class
    private Graph graph;
    
    /**
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
    	// initialize the graph
        graph = new Graph();
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
    	// read the file path
    	Object obj = new JSONParser().parse(new FileReader(jsonFilepath));	
    	
    	// parse Object to be a JSONObject
    	JSONObject jo = (JSONObject) obj;
    	// get the "packages" array from the object
    	JSONArray packages = (JSONArray) jo.get("packages");
    	
    	// for all element in the array
    	for(int i = 0; i < packages.size(); i++) {
    		// put the currentPackage in the variable
    		 JSONObject currentPackage = (JSONObject) packages.get(i);
    		 // store the name of the package in the variable
    		 String packageName = (String) currentPackage.get("name");
    		 // add this package to the graph
    		 graph.addVertex(packageName);
    		 // get the dependencies for this package and store it in the JSONArray
    		 JSONArray packageDependencies = (JSONArray) currentPackage.get("dependencies");
    		   
    		 // for all the dependencies, add it as an edge to the graph for the current package
    		 for(int j=0; j<packageDependencies.size(); j++) {
    			 graph.addEdge(packageName, (String) packageDependencies.get(j));
    		 }		    
    	}  	
    }
    
    /**
     * Helper method to get all packages in the graph.
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
    	// call the graph's getAllVertices() method
        return graph.getAllVertices();
    }
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
    	// make a call to the private helper method and store result in the variable
    	List<String> returnList = getInstallationOrderHelper(pkg, new ArrayList<String>());
    	
    	// loop through list is reverse order
    	for(int i = returnList.size()-1; i>=0; i-- ) {
    		// make a call to the private helper method and store result in the variable
    		List<String> list = getInstallationOrderHelper(returnList.get(i), new ArrayList<String>());
    		
    		// loop through the list from the i+1st vertex
    		for(int j=i+1; j<returnList.size(); j++) {
    			// if there is an element in the path order that contains the current returnList element
    			if(list.contains(returnList.get(j))) {
    				// throw a new CycleException
    				throw new CycleException();
    			}
    		}
    	}
    	// return the list
    	return returnList;
    }
  
    
    /**
     * The private helper method that will return the installation order for pkg
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    private List<String> getInstallationOrderHelper(String pkg, List<String> recursiveList) throws CycleException, PackageNotFoundException{
    	// initialize a new list that will store the installation Order
    	List<String> installationOrder = new ArrayList<String>();
    	// add the current package to the parameter list
    	recursiveList.add(pkg);
    	//get the adjacent vertices to pkg and store them in the list
    	List<String> neighbours = graph.getAdjacentVerticesOf(pkg);
    	
    	// if the neighbours are null, throw a new PackageNotFoundException
    	if(neighbours == null) {
    		throw new PackageNotFoundException();
    	}
    	
    	// for all adjacent vertices,
    	for(int i=0; i<neighbours.size(); i++) {
    		// if recursiveList doesn't have the adjacent vertices
    		if(!recursiveList.contains(neighbours.get(i))) {
    			// get the installation order for the current adjacent vertices
    			List<String> neighbourInstallationOrder = getInstallationOrderHelper(neighbours.get(i), recursiveList);
    		
    			// for all vertices in the list
    			for(int j=0; j<neighbourInstallationOrder.size(); j++) {
    				// if the current package in the list is the same as pkg (possibly in recursive call)
    				if(neighbourInstallationOrder.get(j).equals(pkg)) {
    					// throw a new CycleException
    					throw new CycleException();
    				}
    				// otherwise add the current package in the list to the main list
    				installationOrder.add(neighbourInstallationOrder.get(j));
    			}
    		}
    	}
    	// for all vertices in the main list
    	for(int i = 0; i<installationOrder.size(); i++) {
    		// for all the vertices in the main list (inner loop)
    		for(int j =0; j< installationOrder.size(); j++) {
    			// as long as i and j are not the same
    			if(i!=j) {
    				// if there is a match (iterating through j is the same as i)
    				if(installationOrder.get(j).equals(installationOrder.get(i))) {
    					// throw a new CycleException
    					throw new CycleException();
    			}
    		}
    	}
    }
    // since there are no cycles, add the package to the main list
    installationOrder.add(pkg);
    // return the main list
    return installationOrder; 	
    }
    
    
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
    	// if the package is not in the graph, throw the exception
    	if(!graph.getAllVertices().contains(newPkg)) {
    		throw new PackageNotFoundException();
    	}
    	// if the package is not in the graph, throw the exception
    	if(!graph.getAllVertices().contains(installedPkg)) {
    		throw new PackageNotFoundException();
    	}

    	// store list of dependencies for and including installedPkg
    	List<String> list1 = getInstallationOrder(installedPkg);
    	// store list of dependencies for and including newPkg
    	List<String> list2 = getInstallationOrder(newPkg);
    	
    	// for all elements in list2 (newPkg)
    	for(int i = 0; i<list2.size(); i++) {
    		// if they are present in list1 (already installed),
    		if(list1.contains(list2.get(i))) {
    			// remove it from the list
    			list2.remove(i);
    			// decrement i to make up for remove
    			i--;
    		}
    	}
    	// return the final list
    	return list2;
    }
    
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
        // get and store all of the vertices in the graph
    	ArrayList<String> allPackages = new ArrayList<String>(graph.getAllVertices());
       // initialize a new list of ArrayLists
    	List<ArrayList<String>> allInstallationOrder = new ArrayList<ArrayList<String>>();
        
    	// loop through all packages
    	for(int i =0; i<allPackages.size(); i++) {
    		// set a new list to null
    		List<String> currentInstallationOrder = null;
			
    		try {
    			// set the list to the order of installations received from the current package
				currentInstallationOrder = getInstallationOrder(allPackages.get(i));
				// catch exception for getInstallationOrder
			} catch (PackageNotFoundException e) {				
			}
    		// add this list to the list of arrayLists
        	allInstallationOrder.add((ArrayList<String>)currentInstallationOrder);
        }
    	
        List<String> tempInstallationOrder = tempOrder(allInstallationOrder);
        List<String> correctOrder = correctInstallationOrder(tempInstallationOrder);
        return correctOrder;
    }

    /**
     * Private helper method used to take the complex path and make a simplified path
     * 
     * @param list a list that will be used to simplify and find the best path
     * @return List<String>, order in which all the packages have to be installed
     */
	private List<String> correctInstallationOrder(List<String> list) {
		// initialize a new ArrayList
		ArrayList<String> better = new ArrayList<String>();
		
		// loop through the parameter list
		for(int i =0; i<list.size(); i++) {
			// if the new arrayList doesn't contain the current vertex and isn't null,
			if(!better.contains(list.get(i)) && list.get(i)!= null) {
				// add this vertex to the list
				better.add(list.get(i));
			}
		}
		// return this new best path 
		return better;
	}
	
	/**
	 * Private helper method to create a complex path to visit the vertices (with repetitions)
	 * 
	 * @param list the list of all installation orders for each vertex
	 * @return the complex list of vertices
	 */
	 private List<String> tempOrder(List<ArrayList<String>> list) {
		 // initialize a new ArrayList
		 ArrayList<String> complexInstallation = new ArrayList<String>();
		 // get all the vertices in the graph
		 ArrayList<String> allPackages = new ArrayList<String>(graph.getAllVertices());
		
		 // loop through all the packages
		 for(int i =0; i<allPackages.size(); i++) {
			
			// loop through all the installation orders in the parameter list
			for(int j=0; j<list.size(); j++) {
				
				if(list.get(j).size() >i) {
					complexInstallation.add(list.get(j).get(i));
				}
			}
		 }
		 // return the new complex path
		 return complexInstallation;
	}

	/**
     * Find and return the name of the package with the maximum number of dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
    	// get all packages in the graph
    	ArrayList<String> allPackages = new ArrayList<String>(graph.getAllVertices());
    	// set the package with the largest path to package at index 0
    	String maxPackage = allPackages.get(0);
    	
    	// loop through all the packages
    	for(int i=0; i<allPackages.size(); i++) {
    		try {
    			// get the installationOrder for the current package
    			List<String> installationOrder = getInstallationOrder(allPackages.get(i));
    			
    			// if the current installationOrder size is larger than the size of the installationOrder for the current maxPackage
    			if(installationOrder.size()> getInstallationOrder(maxPackage).size()) {
    				// set the new maxPackage to the current Package in the list
    				maxPackage = allPackages.get(i);
    			}
    			// otherwise, continue
    			else {
    				continue;
    			}
    			
    		// catch a PackageNotFoundException from getInstallationOrder
    		} catch(PackageNotFoundException e) {
    		}
    	}
    	// return maxPackage
    	return maxPackage;
    }

    /**
     * Main method for the program
     * 
     * @param args unused
     */
    public static void main (String [] args) {
        System.out.println("PackageManager.main()");
    }  
}
