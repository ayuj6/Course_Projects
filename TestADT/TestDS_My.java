// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BST ADT - TestDS_My class
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

// TO TEST A DATA STRUCTURE CLASS:
//
// for each data structure class file you wish to test:
//     1. create a test class (like this one) 
//     2. edit the actual type being created (line 16)
//     3. run this test class 
//     4. OR, configure Eclipse project to run all tests
//        Eclipse: Run->Run Configurations->"Run All Tests..."

/**
 * This class will be used to create an instance of the data structure and see
 * if it passes all the tests for the methods.
 * 
 * @author ayujprasad
 *
 */
@SuppressWarnings("rawtypes")
public class TestDS_My extends DataStructureADTTest {

	// the return type must be the name of the data structure class you are testing
	@Override
	protected DataStructureADT createInstance() {
		return new DS_My();
	}

}
