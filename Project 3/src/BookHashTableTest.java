
// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P3a - BookHashTableTest.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Filename:   TestHashTableDeb.java
 * Project:    p3
 * Authors:    Debra Deppeler (deppeler@cs.wisc.edu)
 * 
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   before 10pm on 10/29
 * Version:    1.0
 * 
 * Credits:    None so far
 * 
 * Bugs:       
 */

import org.junit.After;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test HashTable class implementation to ensure that required functionality
 * works for all cases.
 */
public class BookHashTableTest {

	// Default name of books data file
	public static final String BOOKS = "books_clean.csv";

	// Empty hash tables that can be used by tests
	static BookHashTable bookObject;
	static ArrayList<Book> bookTable;

	static final int INIT_CAPACITY = 2;
	static final double LOAD_FACTOR_THRESHOLD = 0.49;

	static Random RNG = new Random(0); // seeded to make results repeatable (deterministic)

	/** Create a large array of keys and matching values for use in any test */
	@BeforeAll
	public static void beforeClass() throws Exception {
		bookTable = BookParser.parse(BOOKS);
	}

	/** Initialize empty hash table to be used in each test */
	@BeforeEach
	public void setUp() throws Exception {
		// TODO: change HashTable for final solution
		bookObject = new BookHashTable(INIT_CAPACITY, LOAD_FACTOR_THRESHOLD);
	}

	/** Not much to do, just make sure that variables are reset */
	@AfterEach
	public void tearDown() throws Exception {
		bookObject = null;
	}

	private void insertMany(ArrayList<Book> bookTable) throws IllegalNullKeyException, DuplicateKeyException {
		for (int i = 0; i < bookTable.size(); i++) {
			bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
		}
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon
	 * initialization
	 */
	@Test
	public void test000_collision_scheme() {
		if (bookObject == null)
			fail("Gg");
		int scheme = bookObject.getCollisionResolutionScheme();
		if (scheme < 1 || scheme > 9)
			fail("collision resolution must be indicated with 1-9");
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon
	 * initialization
	 */
	@Test
	public void test000_IsEmpty() {
		// "size with 0 entries:"
		assertEquals(0, bookObject.numKeys());
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is not empty after
	 * adding one (key,book) pair
	 * 
	 * @throws DuplicateKeyException
	 * @throws IllegalNullKeyException
	 */
	@Test
	public void test001_IsNotEmpty() throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		String expected = "" + 1;
		// "size with one entry:"
		assertEquals(expected, "" + bookObject.numKeys());
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Test if the hash table will be resized after
	 * adding two (key,book) pairs given the load factor is 0.49 and initial
	 * capacity to be 2.
	 */
	@Test
	public void test002_Resize() throws IllegalNullKeyException, DuplicateKeyException {
		bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
		int cap1 = bookObject.getCapacity();
		bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
		int cap2 = bookObject.getCapacity();

		// "size with one entry:"
		assertTrue(cap2 > cap1 & cap1 == 2);
	}

	/**
	 * Test to see if the getLoadFactorThreshold method works before and after
	 * inserting items into the table.
	 */
	@Test
	public void test003_GetLoadFactorThreshold() {

		// get threshold before inserts
		double threshold1 = bookObject.getLoadFactorThreshold();
		try {
			// insert book into the table
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));

			// get the threshold after this insert
			double threshold2 = bookObject.getLoadFactorThreshold();

			// if the values don't match, fail the test
			if ((threshold1 != 0.0) & (threshold2 != 0.5)) {
				fail("threshold not correct");
			}

			// fail if exceptions are thrown
		} catch (IllegalNullKeyException e) {
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			fail("Should not be thown");
		}
	}

	/**
	 * Test to see if the numkeys method returns the correct number of keys in the
	 * table.
	 */
	@Test
	public void test004_numkeys() {
		// if numkeys before insert is not 0, fail the test
		if (bookObject.numKeys() != 0) {
			fail("empty table should have no keys");
		}

		try {

			// insert 4 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
			bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
			bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

			// if the value of numkeys is not 4, fail the test
			if (bookObject.numKeys() != 4) {
				fail("numkeys should be 4, but returned" + bookObject.numKeys());
			}

			// fail if exceptions are thrown
		} catch (IllegalNullKeyException e) {
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			fail("Should not be thown");
		}
	}

	/**
	 * Test to see if the insert method (with a null key) throws an
	 * IllegalNullKeyException.
	 */
	@Test
	public void test005_Insert_Null_Key_Throws_Exception() {
		try {
			// insert a null key
			bookObject.insert(null, bookTable.get(1));

			// if exception is not thrown, fail the test
			fail("Exception should be thrown");

		} catch (IllegalNullKeyException e) {
			// return if this exception is thrown
			return;
		} catch (DuplicateKeyException e) {
			// fail for this exception being thrown
			fail("Should not be thown");
		}
	}

	/**
	 * Test to see if the insert method (with duplicate key) throws a
	 * DuplicateKeyExcpetion.
	 */
	@Test
	public void test006_Insert_Duplicate_Throws_Exception() {
		try {
			// insert 2 same books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));

			// if exception is not thrown, fail the test
			fail("Exception should be thrown");

		} catch (IllegalNullKeyException e) {
			// fail for this exception being thrown
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			// return if this exception is thrown
			return;
		}
	}

	/**
	 * Test to see if the remove method (with null key) throws an
	 * IllegalNullKeyException.
	 */
	@Test
	public void test007_Remove_Null_Key_Throws_Exception() {
		try {
			// insert 4 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
			bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
			bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

			// fail if exceptions are thrown
		} catch (IllegalNullKeyException e) {
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			fail("Should not be thown");
		}

		try {

			// try to remove book with a null key
			bookObject.remove(null);

			// if exception is not thrown, fail the test
			fail("Exception should be thrown");

		} catch (IllegalNullKeyException e) {
			// return if this exception is thrown
			return;
		}
	}

	/**
	 * Test to see if the remove method returns true for a key that is present in
	 * the table.
	 */
	@Test
	public void test008_Remove_Returns_True_For_Valid_Key() {
		try {
			// insert 4 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
			bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
			bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

			// store result from removing book in variable
			boolean result = bookObject.remove(bookTable.get(1).getKey());

			// if result is not true, fail the test
			if (result != true) {
				fail("Should be true");
			}

			// fail if exceptions are thrown
		} catch (IllegalNullKeyException e) {
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			fail("Should not be thown");
		}
	}

	/**
	 * Test to see if the remove method returns false for a key that is not present
	 * in the table.
	 */
	@Test
	public void test009_Remove_Returns_False_For_Key_Not_Present() {
		try {
			// insert 2 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));

			// store result from removing book in variable
			boolean result = bookObject.remove(bookTable.get(5).getKey());

			// if result is not false, fail the test
			if (result != false) {
				fail("Should be false");
			}

			// fail if exceptions are thrown
		} catch (IllegalNullKeyException e) {
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			fail("Should not be thown");
		}
	}

	/**
	 * Test to see if the Get method returns the correct book for a key that is
	 * present in the table.
	 */
	@Test
	public void test010_Get_Returns_Correct_Book_For_Valid_Key() {
		try {
			// insert 2 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));

			// store result from getting book in variable
			Book returnValue = bookObject.get(bookTable.get(2).getKey());

			// if the book does not match, fail the test
			if (returnValue != bookTable.get(2)) {
				fail("Should have returned this book");
			}

			// fail if exceptions are thrown
		} catch (IllegalNullKeyException e) {
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			fail("Should not be thown");
		} catch (KeyNotFoundException e) {
			fail("Should not be thown");
		}
	}

	/**
	 * Test to see if the Get method throws a KeyNotFoundException for a key that is
	 * not in the table.
	 */
	@Test
	public void test011_Get_Throws_Exception_For_Invalid_Key() {
		try {
			// insert 2 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));

			// store result from getting book in variable
			Book returnValue = bookObject.get(bookTable.get(4).getKey());

			// if exception is not thrown, fail the test
			fail("Exception should be thrown");

		} catch (IllegalNullKeyException e) {
			// fail for this exception being thrown
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			// fail for this exception being thrown
			fail("Should not be thown");
		} catch (KeyNotFoundException e) {
			// return if this exception is thrown
			return;
		}
	}

	/**
	 * Test to see if the Get method throws an IllegalNullKeyException for a null
	 * key used.
	 */
	@Test
	public void test012_Get_Throws_Exception_For_Null_Key() {
		try {
			// insert 2 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));

			// store result from getting book in variable
			Book returnValue = bookObject.get(null);

			// if exception is not thrown, fail the test
			fail("Exception should be thrown");

		} catch (IllegalNullKeyException e) {
			// return if this exception is thrown
			return;
		} catch (DuplicateKeyException e) {
			// fail for this exception being thrown
			fail("Should not be thown");
		} catch (KeyNotFoundException e) {
			// fail for this exception being thrown
			fail("Should not be thown");
		}
	}

	/**
	 * Test to see if the remove method reduces the number of keys
	 */
	@Test
	public void test013_Remove_Reduces_Nunber_Of_Key() {
		try {
			// insert 4 books
			bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
			bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
			bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
			bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

			// if number of keys is not 4, fail the test
			if (bookObject.numKeys() != 4) {
				fail("Numkeys should be 4");
			}

			// remove 2 books
			bookObject.remove(bookTable.get(1).getKey());
			bookObject.remove(bookTable.get(2).getKey());

			// if number of keys is not 2, fail the test
			if (bookObject.numKeys() != 2) {
				fail("Numkeys should be 2");
			}

			// fail if exceptions are thrown
		} catch (IllegalNullKeyException e) {
			fail("Should not be thown");
		} catch (DuplicateKeyException e) {
			fail("Should not be thown");
		}
	}
}
