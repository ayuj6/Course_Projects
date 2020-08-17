// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P4 - PackageManagerTest.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the functionality of the packageManager class
 * 
 * @author ayujprasad
 *
 */
class PackageManagerTest {

	// field that will be used to test the class
	PackageManager test;
	
	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// inialize field to a new PackageManager
		test = new PackageManager();
		// construct new graph with my file path
		test.constructGraph("/Users/ayujprasad/Desktop/TestClass.json");
	}

	/**
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		// set field to null
		test = null;
	}

	/**
	 * Method to test if there are 5 packages from this filePath
	 */
	@Test
	void test00_getAllPackages_size() {
		// set field to hold the values returned from the method call
		ArrayList<String> packages = new ArrayList<String>(test.getAllPackages());
		
		// if the size of packages is not 5, fail the test
		if(packages.size() != 5) {
			fail("Should be 5");
		}	
	}

	/**
	 * Method to confirm if toInstall method returns the correct list
	 */
	@Test
	void test01_toInstall_size() {
		// create a new field
		ArrayList<String> packages;
		try {
			// set field to hold the values returned from the method call
			packages = new ArrayList<String>(test.toInstall("A", "B"));
			
			// if the size of packages is not 1, fail the test 
			if(packages.size() != 1) {
				fail("Should be 1");
			}	
			// if the exceptions are thrown, fail the test
		} catch (CycleException | PackageNotFoundException e) {
			fail("Should not be thrown");
		}
	}
	
	/**
	 * Method to confirm if getInstallationOrderForAllPackages returns the correct list
	 */
	@Test
	void test02_getInstallationOrderForAllPackages_size() {
		// create a new field
		ArrayList<String> packages;
		try {
			// set field to hold the values returned from the method call
			packages = new ArrayList<String>(test.getInstallationOrderForAllPackages());
			
			// if the size of packages is not 5, fail the test
			if(packages.size() != 5) {
				fail("Should be 5");
			}	
			// if the exception is thrown, fail the test
		} catch (CycleException e) {
			fail("Should not be thrown");
		}
	}
	
	/**
	 * Method to confirm if getInstallationOrderForAllPackages returns the correct list
	 */
	@Test
	void test03_getInstallationOrderForAllPackages_position() {
		// create a new field
		ArrayList<String> packages;
		try {
			// set field to hold the values returned from the method call
			packages = new ArrayList<String>(test.getInstallationOrderForAllPackages());
			
			// if the packages list doesn't hold the values in the correct position, fail the test
			if(!packages.get(0).equals("C") || !packages.get(1).equals("D") ||
					!packages.get(2).equals("B") || !packages.get(3).equals("A")|| !packages.get(4).equals("F")) {
				fail("Not in correct position");
			}	
			// if the exception is thrown, fail the test
		} catch (CycleException e) {
			fail("Should not be thrown");
		}
	}
	
	/**
	 * Method to see if constructGraph with an invalid path throws an exception
	 */
	@Test
	void test04_constructGraph_invalid_filepath() {
		try {
			// construct graph with invalid filepath
			test.constructGraph("failPath");
			
			// if this exception is thrown, return
		} catch (FileNotFoundException e) {
			return;
			
			// for these exceptions, fail the test
		} catch (IOException e) {
			fail("Should not be thrown");
		} catch (ParseException e) {
			fail("Should not be thrown");
		}
	}
	
	/**
	 * Method to see if getInstallationOrder returns the correct list
	 */
	@Test
	void test05_getInstallationOrder_size() {
		// create a new field
		ArrayList<String> packages;	
			try {
				// set field to hold the values returned from the method call
				packages = new ArrayList<String>(test.getInstallationOrder("A"));
				
				// if the size of the list is not 4, fail the test
				if(packages.size() != 4) {
					fail("Should be 4");
				}	
				
				// if these exceptions are thrown, fail the test
			} catch (PackageNotFoundException e) {
				fail("Should not be thrown");
			} catch (CycleException e) {
				fail("Should not be thrown");
			}		
		} 
	
	
	/**
	 * Method to see if getPackageWithMaxDependencies returns the correct package
	 */
	@Test
	void test06_getPackageWithMaxDependencies() {
		try {
			// set field to hold the values returned from the method call
			String packageName = test.getPackageWithMaxDependencies();
			
			// if the field does not contain "F", fail the test
			if(!packageName.equals("F")) {
				fail("Should have been package: F");
			}
			
			// if the exception is thrown, fail the test
		} catch (CycleException e) {
			fail("Should not be thrown");
		}
		
	}
}

