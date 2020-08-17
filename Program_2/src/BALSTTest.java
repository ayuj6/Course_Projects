
// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AVL tree - BALSTTest.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.Assert.fail;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

/**
 * The test page to test the different methods and check if the implementation
 * is correct.
 * 
 * @author ayujprasad
 *
 */
//@SuppressWarnings("rawtypes")
public class BALSTTest {

	BALST<String, String> balst1;
	BALST<Integer, String> balst2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		balst1 = createInstance();
		balst2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		balst1 = null;
		balst2 = null;
	}

	protected BALST<String, String> createInstance() {
		return new BALST<String, String>();
	}

	protected BALST<Integer, String> createInstance2() {
		return new BALST<Integer, String>();
	}

	/**
	 * Insert three values in sorted order and then check the root, left, and right
	 * keys to see if re-balancing occurred.
	 */
	@Test
	void testBALST_001_insert_sorted_order_simple() {
		try {
			balst2.insert(10, "10");
			if (!balst2.getKeyAtRoot().equals(10))
				fail("avl insert at root does not work");

			balst2.insert(20, "20");
			if (!balst2.getKeyOfRightChildOf(10).equals(20))
				fail("avl insert to right child of root does not work");

			balst2.insert(30, "30");
			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Insert three values in reverse sorted order and then check the root, left,
	 * and right keys to see if re-balancing occurred in the other direction.
	 */
	@Test
	void testBALST_002_insert_reversed_sorted_order_simple() {

		try {
			balst2.insert(30, "30");
			if (!balst2.getKeyAtRoot().equals(30))
				fail("avl insert at root does not work");

			balst2.insert(20, "20");
			if (!balst2.getKeyOfLeftChildOf(30).equals(20))
				fail("avl insert to right child of root does not work");

			balst2.insert(10, "10");
			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}

	/**
	 * Insert three values so that a right-left rotation is needed to fix the
	 * balance.
	 * 
	 * Example: 10-30-20
	 * 
	 * Then check the root, left, and right keys to see if re-balancing occurred in
	 * the other direction.
	 */
	@Test
	void testBALST_003_insert_smallest_largest_middle_order_simple() {

		try {
			balst2.insert(10, "10");
			if (!balst2.getKeyAtRoot().equals(10))
				fail("avl insert at root does not work");

			balst2.insert(30, "30");
			if (!balst2.getKeyOfRightChildOf(10).equals(30))
				fail("avl insert to right child of root does not work");

			balst2.insert(20, "20");
			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}

	/**
	 * Insert three values so that a left-right rotation is needed to fix the
	 * balance.
	 * 
	 * Example: 30-10-20
	 * 
	 * Then check the root, left, and right keys to see if re-balancing occurred in
	 * the other direction.
	 */
	@Test
	void testBALST_004_insert_largest_smallest_middle_order_simple() {

		try {
			balst2.insert(30, "30");
			if (!balst2.getKeyAtRoot().equals(30))
				fail("avl insert at root does not work");

			balst2.insert(10, "10");
			if (!balst2.getKeyOfLeftChildOf(30).equals(10))
				fail("avl insert to right child of root does not work");

			balst2.insert(20, "20");
			Integer k = balst2.getKeyAtRoot();
			if (!k.equals(20))
				fail("avl rotate does not work");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
			Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
			Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

			balst2.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}

	/**
	 * Test to see if the height of the tree changes correctly as nodes are inserted
	 * into the tree.
	 */
	@Test
	void testBALST_005_get_Height() {
		try {
			// empty tree case
			if (balst2.getHeight() != 0) {
				fail("avl getHeight for empty tree does not work");
			}
			balst2.insert(40, "Hello");

			if (balst2.getHeight() != 1) {
				fail("avl getHeight for only root does not work");
			}
			balst2.insert(30, "Hlo");

			if (balst2.getHeight() != 2) {
				fail("avl getHeight for many inserts does not work");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if inOrderTraversal works as expected and keys get stored into
	 * the list as expected.
	 */
	@Test
	void testBALST_006_test_inOrder_traversal() {
		try {
			// empty tree case
			if (balst2.getInOrderTraversal().size() != 0) {
				fail("avl inOrderTraversal for empty tree does not work");
			}
			balst2.insert(40, "40");
			balst2.insert(20, "20");
			balst2.insert(70, "70");
			balst2.insert(10, "10");

			// list should be ordered with the items in the given order
			if (balst2.getInOrderTraversal().size() != 4) {
				fail("avl inOrderTraversal does not work");
			}
			
			// the 3rd item seen in the traversal should be 40
			if (balst2.getInOrderTraversal().get(2) != 40) {
				fail("avl inOrderTraversal does not work");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}

	}

	/**
	 * Test to see if preOrderTraversal works as expected and keys get stored into
	 * the list as expected.
	 */
	@Test
	void testBALST_007_test_preOrder_traversal() {
		try {
			// empty tree case
			if (balst2.getPreOrderTraversal().size() != 0) {
				fail("avl preOrderTraversal for empty tree does not work");
			}
			balst2.insert(40, "40");
			balst2.insert(20, "20");
			balst2.insert(70, "70");
			balst2.insert(10, "10");

			// list should be ordered with the items in the given order
			if (balst2.getPreOrderTraversal().size() != 4) {
				fail("avl preOrderTraversal does not work");
			}
			
			// the 3rd item seen in the traversal should be 10
			if (balst2.getPreOrderTraversal().get(2) != 10) {
				fail("avl preOrderTraversal does not work");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if postOrderTraversal works as expected and keys get stored into
	 * the list as expected.
	 */
	@Test
	void testBALST_008_test_postOrder_traversal() {
		try {
			// empty tree case
			if (balst2.getPostOrderTraversal().size() != 0) {
				fail("avl postOrderTraversal for empty tree does not work");
			}
			balst2.insert(40, "40");
			balst2.insert(20, "20");
			balst2.insert(70, "70");
			balst2.insert(10, "10");

			// list should be ordered with the items in the given order
			if (balst2.getPostOrderTraversal().size() != 4) {
				fail("avl postOrderTraversal does not work");
			}
			
			// the 3rd item seen in the traversal should be 70
			if (balst2.getPostOrderTraversal().get(2) != 70) {
				fail("avl postOrderTraversal does not work");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if levelOrderTraversal works as expected and keys get stored into
	 * the list as expected.
	 */
	@Test
	void testBALST_009_test_levelOrder_traversal() {
		try {
			// empty tree case
			if (balst2.getLevelOrderTraversal().size() != 0) {
				fail("avl levelOrderTraversal for empty tree does not work");
			}
			balst2.insert(40, "40");
			balst2.insert(20, "20");
			balst2.insert(70, "70");
			balst2.insert(10, "10");

			// list should be ordered with the items in the given order
			if (balst2.getLevelOrderTraversal().size() != 4) {
				fail("avl levelOrderTraversal does not work");
			}
			
			// the 3rd item seen in the traversal should be 70
			if (balst2.getLevelOrderTraversal().get(2) != 70) {
				fail("avl levelOrderTraversal does not work");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the insert method throws the appropriate exception when called
	 * with a null key value.
	 */
	@Test
	void testBALST_010_test_insert_null_key_throws_exception() {
		try {

			balst2.insert(40, "40");
			balst2.insert(20, "20");
			balst2.insert(70, "70");
			balst2.insert(10, "10");
			balst2.insert(null, "ExceptionValue");
			fail("Insert should have thrown exception");
		} catch (IllegalNullKeyException e) {
			// expected exception
			return;
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the insert method throws the appropriate exception when trying
	 * to insert a duplicate key.
	 */
	@Test
	void testBALST_011_test_insert_duplicate_throws_exception() {
		try {

			balst2.insert(80, "80");
			balst2.insert(20, "20");
			balst2.insert(80, "ExceptionValue");
			fail("Insert should have thrown exception");
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			// expected exception
			return;
		}
	}

	/**
	 * Test to see if the remove method works as expected when trying to remove a
	 * valid key.
	 */
	@Test
	void testBALST_012_test_remove_with_present_key() {
		try {

			balst2.insert(80, "80");
			balst2.insert(20, "20");

			if (balst2.remove(20) != true) {
				fail("Remove method does not work");
			}
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the remove method throws the appropriate exception when called
	 * with a null key.
	 */
	@Test
	void testBALST_013_test_remove_with_null_key_throws_exception() {
		try {

			balst2.insert(80, "80");
			balst2.insert(20, "20");

			balst2.remove(null);
			fail("Remove method should have thrown exception");

		} catch (IllegalNullKeyException e) {
			// expected exception
			return;
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the remove method throws the appropriate exception when called
	 * with an invalid key.
	 */
	@Test
	void testBALST_014_test_remove_key_not_present() {
		try {

			balst2.insert(20, "20");
			balst2.insert(800, "800");

			balst2.remove(40);
			fail("Remove method should have thrown exception");

		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (KeyNotFoundException e) {
			// expected exception
			return;
		}
	}

	/**
	 * Test to see if the get method works as expected when called with a valid key.
	 */
	@Test
	void testBALST_015_test_get_with_valid_key() {
		try {

			balst2.insert(90, "Hi");
			balst2.insert(10, "Hello");
			balst2.insert(150, "Hola");

			if (balst2.get(90) != "Hi") {
				fail("get method did not work as expected");
			}
			if (balst2.get(10) != "Hello") {
				fail("get method did not work as expected");
			}
			if (balst2.get(150) != "Hola") {
				fail("get method did not work as expected");
			}

		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the get method throws the appropriate exception when called
	 * with a null key.
	 */
	@Test
	void testBALST_016_test_get_with_null_key() {
		try {

			balst2.insert(2, "Goodbye");
			balst2.insert(1, "Bye");
			balst2.insert(15, "Adios");

			if (balst2.get(null) != "Message") {
				fail("get method did not work as expected");
			}

		} catch (IllegalNullKeyException e) {
			// expected exception
			return;
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the get method throws the appropriate exception when called
	 * with a key that is not in the tree.
	 */
	@Test
	void testBALST_017_test_get_with_key_not_present() {
		try {

			balst2.insert(49, "49");
			balst2.insert(59, "59");
			balst2.insert(39, "39");

			if (balst2.get(7) != "Message") {
				fail("get method did not work as expected");
			}

		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (KeyNotFoundException e) {
			// expected exception
			return;
		}
	}

	/**
	 * Test to see if the contains method works as expected when called with a valid
	 * key.
	 */
	@Test
	void testBALST_018_test_contains_with_present_key() {
		try {

			balst2.insert(100, "100");
			balst2.insert(200, "200");
			balst2.insert(300, "300");

			if (balst2.contains(100) != true) {
				fail("contains method did not work as expected");
			}
			if (balst2.contains(200) != true) {
				fail("contains method did not work as expected");
			}
			if (balst2.contains(300) != true) {
				fail("contains method did not work as expected");
			}

		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the contains method throws the appropriate exception when
	 * called with a null key.
	 */
	@Test
	void testBALST_019_test_contains_with_null_key() {
		try {

			balst2.insert(1, "1");
			balst2.insert(9, "9");
			balst2.insert(3, "3");

			if (balst2.contains(null) != false) {
				fail("get method did not work as expected");
			}

		} catch (IllegalNullKeyException e) {
			// expected exception
			return;
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}

	/**
	 * Test to see if the contains method throws the appropriate exception when
	 * called with a null that is not in the tree.
	 */
	@Test
	void testBALST_020_test_contains_with_key_not_present() {
		try {
			balst2.insert(1, "1");
			balst2.insert(9, "9");
			balst2.insert(3, "3");

			if (balst2.contains(200) != false) {
				fail("get method did not work as expected");
			}
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
			
		}
	}

	/**
	 * Test to see if the numKeys method increments and decrements correctly.
	 */
	@Test
	void testBALST_021_test_numKeys_with_insert_and_remove() {
		try {

			for (int i = 0; i < 40; i++) {
				balst2.insert(i, "i");
			}
			if (balst2.numKeys() != 40) {
				fail("numKeys did not increment properly");
			}

			for (int i = 0; i < 40; i++) {
				balst2.remove(i);
			}
			if (balst2.numKeys() != 0) {
				fail("numKeys did not decrement properly");
			}
		} catch (IllegalNullKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}
}