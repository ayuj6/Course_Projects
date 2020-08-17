// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BST ADT - Tests class
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class holds all the tests for testing the different methods in DS_My. It
 * takes into consideration a variety of scenarios and is not specific to any
 * single ADT.
 * 
 * @author ayujprasad
 *
 * @param <T> the generic type associated with the data structure.
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

	// instance of our data structure
	private T dataStructureInstance;

	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	/**
	 * Test to see if our data structure is initially size 0.
	 */
	@Test
	void test00_empty_ds_size() {
		// after setup, if the size of the data structure is not 0, then fail the test
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
	}

	/**
	 * Test to see if inserting 1 node will result in the size being 1.
	 */
	@Test
	void test01_after_insert_one_size_is_one() {
		// try
		try {
			// after setup, insert the key-value pair
			dataStructureInstance.insert("B", "11");

			// if the size is not 1, fail the test
			if (dataStructureInstance.size() != 1)
				fail("data structure should have size=1, but size=" + dataStructureInstance.size());

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if inserting 1 node and removing it will result in the size being
	 * 0.
	 */
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		// try
		try {
			// insert one node with given key-value pair
			dataStructureInstance.insert("C", "8");
			// use remove method to remove that node
			dataStructureInstance.remove("C");

			// if the size is not 0 (didn't get removed), fail the test
			if (dataStructureInstance.size() != 0)
				fail("data structure should be empty after removing only item, but size="
						+ dataStructureInstance.size());

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if inserting a duplicate key will throw a Runtime Exception
	 */
	@Test
	void test03_duplicate_exception_is_thrown() {
		// try
		try {

			// insert 2 key-value pairs into the structure
			dataStructureInstance.insert("A", "3");
			dataStructureInstance.insert("Z", "19");

			// insert a key-pair value with a duplicate key
			dataStructureInstance.insert("A", "3");
			// fail if it didn't get caught
			fail("data structure should have thrown a Runtime Exception after adding a duplicate key");

			// catch a Runtime Exception
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
			// for different (wrong) implementations
		} catch (RuntimeException e) {
			return;
		}
	}

	/**
	 * Test to see if removing a key that is not present returns false
	 */
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("M", "2");
			dataStructureInstance.insert("L", "16");
			dataStructureInstance.insert("V", "9");

			// store the result of the remove method into the variable
			boolean test04Result = dataStructureInstance.remove("J");

			// if the result is not false, fail the test
			if (test04Result != false)
				fail("data structure delete should have returned false for trying to delete non-existent key");
			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if inserting a key-value pair with a null key throws an
	 * IllegalArgumentExeption.
	 */
	@Test
	void test05_insert_null_key_exception_thrown() {
		// try
		try {
			// insert the key-value pair
			dataStructureInstance.insert("M", "2");

			// inserting a null key
			dataStructureInstance.insert(null, "2");
			// fail if not caught
			fail("data structure should have thrown an IllegalArgument Exception after adding a null key");

			// catch an IllegalArgumentException
		} catch (IllegalArgumentException e) {
			return;

			// for different (wrong) implementations
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if trying the remove method with a null key throws an
	 * IllegalArgumentException.
	 */
	@Test
	void test06_remove_null_key_exception_thrown() {
		// try
		try {

			// insert the following key-value pair
			dataStructureInstance.insert("R", "9");

			// remove a null key
			dataStructureInstance.remove(null);
			// fail if not caught
			fail("data structure should have thrown an IllegalArgument Exception for trying to remove a null key");

			// catch an IllegalArgumentException
		} catch (IllegalArgumentException e) {
			return;

			// for different (wrong) implementations
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if remove works correctly when trying to remove a present key
	 */
	@Test
	void test07_remove_returns_true_when_key_present_in_structure() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("A", "7");
			dataStructureInstance.insert("R", "9");
			dataStructureInstance.insert("M", "2");

			// store the result of the remove method in the variable
			boolean test07Result = dataStructureInstance.remove("R");

			// if the result is not true, fail the test
			if (test07Result != true) {
				fail("data structure delete should have returned true for removing a key that is in the structure");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if insert works correctly when trying to insert with a null
	 * "value"
	 */
	@Test
	void test08_insert_with_null_value() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("W", "14");
			dataStructureInstance.insert("Q", "9");
			dataStructureInstance.insert("S", null);

			// store the result of the get method in the variable
			String test08Result = dataStructureInstance.get("S");

			// if the result is not "null", fail the test
			if (test08Result != null) {
				fail("data structure should have inserted the key will null value");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if the get() method will return the correct value associated with
	 * the given key (when present)
	 */
	@Test
	void test09_get_returns_correct_value() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("GG", "8");
			dataStructureInstance.insert("Traap", "2");

			// store the result of the get method in the variable
			String test09Result = dataStructureInstance.get("GG");

			// if the result is not "8", fail the test
			if (!test09Result.equals("8")) {
				fail("data structure should have returned the correct value (8) associated with GG ");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see that the get() method doesn't remove the key we searched for
	 */
	@Test
	void test10_get_does_not_remove_key() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("David", "14");
			dataStructureInstance.insert("Carlos", "1");

			// store the result of the get method in the variable
			String test10Result = dataStructureInstance.get("Carlos");

			// if the structure no longer contains "Carlos", fail the test
			if (dataStructureInstance.contains("Carlos") == false) {
				fail("data structure should not have removed key when we used the get method");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see that the get() method doesn't decrease the size
	 */
	@Test
	void test11_get_does_not_decrease_size() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("2", "Ayuj");
			dataStructureInstance.insert("17", "Patrick");

			// store the size before the get method
			int structureSize = dataStructureInstance.size();

			// store the result of the get method in the variable
			String test11Result = dataStructureInstance.get("Carlos");

			// if the size before the get method is not the same as the current size, fail
			// the test
			if (structureSize != dataStructureInstance.size()) {
				fail("data structure should not have decreased its size when we used the get method");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if get method with a null key throws an IllegalArgumentException
	 */
	@Test
	void test12_get_null_key_throws_exception() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("21", "AJ");
			dataStructureInstance.insert("8", "Louis");

			// get with a null key
			dataStructureInstance.get(null);
			// fail if not caught
			fail("this method call should have thrown an exception for trying to get a null key");

			// catch an IllegalArgumentException
		} catch (IllegalArgumentException e) {
			return;

			// for different (wrong) implementations
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if contains() method returns true for finding a key present in
	 * the structure
	 */
	@Test
	void test13_contains_returns_true_for_present_key() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("3", "Dad");
			dataStructureInstance.insert("4", "Mom");

			// store the result of the contains method in the variable
			boolean test13Result = dataStructureInstance.contains("3");

			// if the result is not true, fail the test
			if (test13Result != true) {
				fail("data structure should have returned true for testing method with present key");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if the contains() method returns false for not finding a key in
	 * the structure.
	 */
	@Test
	void test14_contains_returns_false_for_key_not_present() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("3", "Sis");
			dataStructureInstance.insert("13", "Bro");
			dataStructureInstance.insert("6", "Me");

			// store the result of the contains method in the variable
			boolean test14Result = dataStructureInstance.contains("70");

			// if the result is not false, fail the test
			if (test14Result != false) {
				fail("data structure should have returned false for testing method with a key that isn't present");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to see if the contains() method returns false for searching with a null
	 * key
	 */
	@Test
	void test15_contains_returns_false_for_null_key() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("2", "Cool");
			dataStructureInstance.insert("19", "Hello");
			dataStructureInstance.insert("100", "World");

			// store the result of the contains method in the variable
			boolean test15Result = dataStructureInstance.contains(null);

			// if the result is not false, fail the test
			if (test15Result != false) {
				fail("data structure should have returned false for testing method with a null key");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test to check if size is calculated correctly
	 */
	@Test
	void test16_check_size() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("2", "Hola");
			dataStructureInstance.insert("3", "Me");
			dataStructureInstance.insert("10", "A");
			dataStructureInstance.insert("20", "Fun");
			dataStructureInstance.insert("19", "Guy");

			// if the size is not 5, fail the test
			if (dataStructureInstance.size() != 5) {
				fail("data structure should have a size of 5 for inserting 5 values");
			}

			// for different (wrong) implementations
		} catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}

	/**
	 * Test that a key can be re-added if the key was inserted and then removed
	 * (this should not be a duplicate).
	 */
	@Test
	void test17_re_insert_of_key() {
		// try
		try {
			// insert the following key-value pairs
			dataStructureInstance.insert("2", "Hola");
			dataStructureInstance.insert("3", "Me");
			dataStructureInstance.insert("5", "Funday");

			// remove the node with key of 3
			dataStructureInstance.remove("3");

			// re-insert that node
			dataStructureInstance.insert("3", "Me");
		}

		// should not catch exceptions
		catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not produce a duplicate key error");
		}
	}

	/**
	 * check that it can store at least 500 items, should be able remove all of them
	 * too
	 */
	@Test
	void test18_add_500_items_then_remove() {
		// try
		try {
			// insert 500 nodes into the structure
			for (int i = 0; i < 500; i++) {
				dataStructureInstance.insert("" + i, "" + i);
			}

			// remove all 500 nodes in reverse order
			for (int i = 499; i >= 0; i--) {
				dataStructureInstance.remove("" + i);
			}

			// if the size is not 0, fail the test
			if (dataStructureInstance.size() != 0) {
				fail("All items should have been removed without error");
			} 
		}

		// Thrown Exceptions should fail the test
		catch (IllegalArgumentException e) {
			fail("Should not be thrown");
		} catch (RuntimeException e) {
			fail("Should not be thrown");
		}
	}
}
