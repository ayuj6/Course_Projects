// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BST ADT - DS_My class
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class is the outer class of the BST ADT that has been implemented. It
 * makes use of the insert, remove, contains, get, and size methods (along with
 * other helper methods) to function.
 * 
 * @author ayujprasad
 *
 */
public class DS_My implements DataStructureADT {

	/**
	 * This is the inner class that creates the nodes that will hold the key-pair
	 * values. It also has setter and getter methods that will help with the
	 * functionality of the outer class.
	 * 
	 * @author ayujprasad
	 *
	 */
	private static class Node {
		// the key of type "Comparable"
		Comparable key;
		// the value that will be stored with the key
		Object value;
		// the left child to a given node
		private Node leftChild;
		// the right child to a given node
		private Node rightChild;

		/**
		 * The constructor for the inner class that will initialize the key, value,
		 * leftChild, and rightChild values.
		 * 
		 * @param key   the comparable value to store in the "key" field
		 * @param value the object to store in the "value" field
		 */
		private Node(Comparable key, Object value) {
			// make explicit call to no argument constructor
			super();
			// set the key field with parameter "key"
			this.key = key;
			// set the value field with parameter "value"
			this.value = value;
			// set leftChild to null
			this.leftChild = null;
			// set rightChild to null
			this.rightChild = null;
		}

		/**
		 * Getter for the right child of this dictionary word
		 * 
		 * @param rightChild the right child of the current node to be set
		 */
		private void setRightChild(Node rightChild) {
			// set the right child of the current node to the parameter "rightChild"
			this.rightChild = rightChild;
		}

		/**
		 * Setter for the left child of this dictionary word
		 * 
		 * @param leftChild the left child of the current node to be set
		 */
		private void setLeftChild(Node leftChild) {
			// set the left child of the current node to the parameter "leftChild"
			this.leftChild = leftChild;
		}

		/**
		 * Getter for the right child of this dictionary word
		 * 
		 * @return the right child of the current node
		 */
		private Node getRightChild() {
			// return the right child of the current node
			return this.rightChild;
		}

		/**
		 * Getter for the left child of this dictionary word
		 * 
		 * @return the left child of the current node
		 */
		private Node getLeftChild() {
			// return the left child of the current node
			return this.leftChild;
		}
	}

	// the root node of the BST structure
	private Node root;

	/**
	 * constructor of the outer class that will set the default value of the root
	 * node to null (to indicate an empty structure).
	 */
	public DS_My() {
		// set root to null
		this.root = null;
	}

	/**
	 * Inserts the key "k" and value "v" to the data structure. Throws
	 * IllegalArgumentException if the key is null. Throws RuntimeExeption if the
	 * key is already present in the structure.
	 * 
	 * @param k the key value to be inserted
	 * @param v the value associated with the key to be inserted
	 */
	@Override
	public void insert(Comparable k, Object v) {
		// if key is null, throw a new IllegalArgumentException
		if (k == null) {
			throw new IllegalArgumentException("null key");
		}

		// if the root is null (empty structure),
		if (root == null) {
			// create a new key-value node
			Node insertValue = new Node(k, v);
			// insert this node into the root of the structure
			root = insertValue;

			// if the structure is not empty
		} else {
			// call private helper method starting with the root
			insertHelper(k, v, this.root);
		}
	}

	/**
	 * Remove a specified node using the given key. If key is found, we remove the
	 * associated node from the structure. Throws IllegalArgumentException if the
	 * key is null. Returns false if the key is not found.
	 * 
	 * @param k the key to be removed from the structure
	 * @return true if the key (node) was removed, false otherwise
	 */
	@Override
	public boolean remove(Comparable k) {
		// if the key is null, throw a new IllegalArgumentException
		if (k == null) {
			throw new IllegalArgumentException("null key");
		}

		// if the root is null (empty structure)
		if (root == null) {
			// return false
			return false;
		}

		// make a call to the helper method starting from the root, and store the result
		// in the node
		Node helperResult = removeHelper(k, root);

		// if the result from the helper method is null,
		if (helperResult == null) {
			// return false
			return false;

			// otherwise,
		} else {
			// return true
			return true;
		}
	}

	/**
	 * Checks to see if a given key is present in the data structure or not.
	 * 
	 * @param k the key to be checked for in the structure
	 * @return true if the key (node) is present in the structure, false otherwise
	 */
	@Override
	public boolean contains(Comparable k) {

		// if the root is null (empty structure),
		if (root == null) {
			// return false
			return false;
		}

		// if the key is null,
		if (k == null) {
			// return false
			return false;
		}

		// return the value returned from the helper method
		return containsHelper(k, root);
	}

	/**
	 * Search through the structure to find the given key and return its associated
	 * value. Throws IllegalArgumentException if the key is null. If the key is not
	 * present in the structure, it returns null.
	 * 
	 * @param k the key to be searched for in the structure
	 * @return the value associated with the given key, null if the key is non-null
	 *         and not in the structure
	 */
	@Override
	public Object get(Comparable k) {

		// if the key is null, throw a new IllegalArgumentException
		if (k == null) {
			throw new IllegalArgumentException("null key");
		}

		// if the root is null (empty structure), return null
		if (root == null) {
			return null;
		}

		// return the value returned from the helper method
		return getHelper(k, root);
	}

	/**
	 * Gives the size (number of nodes) of the structure
	 * 
	 * @return the size of the structure
	 */
	@Override
	public int size() {

		// return the value returned from the helper method
		return sizeHelper(root);
	}

	/**
	 * Recursive helper method that inserts a key-value pair into the data structure
	 * (starting from root in most cases). Throws a RuntimeException if the key
	 * value of the node is already present in the structure.
	 * 
	 * @param k       the key to be inserted
	 * @param v       the value to be inserted
	 * @param newNode the node from where we will start our insert
	 */
	private void insertHelper(Comparable k, Object v, Node newNode) {

		// create a new node for the key-value pair
		Node insertValue = new Node(k, v);

		// store the result of the comparison between the newNode's key and our key into
		// a variable
		int comparison = insertValue.key.compareTo(newNode.key);

		// if the value is 0 (duplicate key), throw a RuntimeException
		if (comparison == 0) {
			throw new RuntimeException("duplicate key");

			// otherwise, if the comparison is >0 (k is greater),
		} else if (comparison > 0) {

			// check if current's rightChild is null
			if (newNode.rightChild == null) {

				// if so, set current's rightChild to newWordNode and leave the method
				newNode.rightChild = insertValue;
				return;
			}

			// otherwise make the recursive call with the current node's right child
			insertHelper(k, v, newNode.rightChild);
			return;

			// if k is smaller,
		} else {

			// check if current's leftChild is null
			if (newNode.leftChild == null) {

				// if so, set current's leftChild to newWordNode and leave the method
				newNode.leftChild = insertValue;
				return;
			}

			// otherwise, return the recursive call with the current node's left child
			insertHelper(k, v, newNode.leftChild);
			return;
		}
	}

	/**
	 * Recursive helper method that will help search for the key in the structure
	 * that has to be removed.
	 * 
	 * @param k           the value to be removed from the structure
	 * @param currentNode the node to start the search from
	 * @return null if the node is not present, the Node removed if present
	 */
	private Node removeHelper(Comparable k, Node currentNode) {

		// set a node called parent to null
		Node parent = null;

		// set a node called currNode to hold the node "currentNode"
		Node currNode = currentNode;

		// store the result of the comparison between the newNode's key and our key into
		// a variable
		int comparison = currentNode.key.compareTo(k);

		// while currNode is not null and we have not found our node to remove
		while (currNode != null && currNode.key.compareTo(k) != 0) {
			// set the parent node to currNode
			parent = currNode;

			// if the comparison of keys is greater than 0 (k is smaller),
			if (currNode.key.compareTo(k) > 0) {
				// set currNode to its left child
				currNode = currNode.getLeftChild();

				// otherwise, set it to its right child
			} else {
				currNode = currNode.getRightChild();
			}
		}

		// if the value in currNode is null, return null
		if (currNode == null) {
			return null;
		}

		// if currNode's left and right child are null (leaf node)
		if (currNode.leftChild == null && currNode.rightChild == null) {

			// if currNode is not the same node as currentNode (we traversed down the
			// structure),
			if (currNode != currentNode) {

				// if currNode was the parent's left child,
				if (parent.leftChild == currNode) {
					// set it to null
					parent.setLeftChild(null);

					// otherwise, set its right child to null
				} else {
					parent.setRightChild(null);
				}

				// otherise (if this was the same as currentNode),
			} else {
				// set root to null
				root = null;
			}

			// if currNode has 2 children,
		} else if (currNode.leftChild != null && currNode.rightChild != null) {

			// find the successor to currNode using the helper method
			Node successor = findSuccessor(currNode.rightChild);

			// store the key and values in the respective variables
			Comparable keyValue = successor.key;
			Object successorValue = successor.value;

			// remove the successor from the structure
			removeHelper(keyValue, currentNode);

			// set currNode's key and value to the successor's key and value respectively
			currNode.key = keyValue;
			currNode.value = successorValue;

			// otherise (1 child),
		} else {

			// find child node
			Node childNode = (currNode.leftChild != null) ? currNode.leftChild : currNode.rightChild;

			// if the node to be deleted is not the root, then set its parent to its child
			if (currNode != currentNode) {
				if (currNode == parent.leftChild) {
					parent.leftChild = childNode;
				} else {
					parent.rightChild = childNode;
				}

				// otherwise, (node to delete is the root), set the root to the child
			} else {
				currentNode = childNode;
			}
		}
		// return the newly replaced node
		return currentNode;
	}

	/**
	 * A recursive helper method to find a key k in the data structure and return
	 * the value associated with that key.
	 * 
	 * @param k           the key to search for
	 * @param currentNode the node to begin the search from
	 * @return the value associated with the key if found, null if not found
	 */
	private Object getHelper(Comparable k, Node currentNode) {

		// put the currentNode's value in a variable
		Object currentValue = currentNode.value;

		// store the result of the comparison between the newNode's key and our key into
		// a variable
		int comparison = currentNode.key.compareTo(k);

		// if comparison is 0 (the keys are the same - found),
		if (comparison == 0) {
			// return the currentNode's value
			return currentNode.value;

			// otherwise, if the comparison is <0 (k is greater),
		} else if (comparison < 0) {

			// if currentNode's rightChild is not null,
			if (currentNode.rightChild != null) {
				// store the recursive call's result in currentValue
				currentValue = getHelper(k, currentNode.rightChild);

				// otherwise, return null (not found)
			} else {
				return null;
			}

			// if comparison is >0 (k is smaller),
		} else if (comparison > 0) {
			// if the leftChild is not null,
			if (currentNode.leftChild != null) {
				// store the result of the recursive call in currentValue
				currentValue = getHelper(k, currentNode.leftChild);

				// otherwise, return null (not found)
			} else {
				return null;
			}
		}

		// return the final value stored in currentValue
		return currentValue;
	}

	/**
	 * Recursive helper method that checks to see if a given key is present in the
	 * data structure or not.
	 * 
	 * @param k           the key to search for
	 * @param currentNode the node to do the search from
	 * @return true if the key is in the structure, false otherwise
	 */
	private boolean containsHelper(Comparable k, Node currentNode) {

		// if the currentNode is null, return false
		if (currentNode == null) {
			return false;
		}

		// store the result of the comparison between the newNode's key and our key into
		// a variable
		int comparison = currentNode.key.compareTo(k);

		// if comparison is 0 (found), return true
		if (comparison == 0) {
			return true;

			// else, if comparison is <0 (k is greater),
		} else if (comparison < 0) {
			// return the value obtained from recursive helper method with rightChild
			return containsHelper(k, currentNode.rightChild);

			// else, if comparison >0 (k is smaller),
		} else if (comparison > 0) {
			// return the value obtained from recursive helper method with leftChild
			return containsHelper(k, currentNode.leftChild);

			// otherwise, return true
		} else {
			return true;
		}
	}

	/**
	 * Recursive helper method that returns the number of keys (nodes) stored in the
	 * data structure
	 * 
	 * @param currentNode the node to start the count from (first call is root)
	 * @return the number of keys (nodes) counted
	 */
	private int sizeHelper(Node currentNode) {
		// begin by setting a counter variable to 0
		int currSize = 0;

		// if the current node is null,
		if (currentNode == null) {
			// return currSize
			return currSize;
		}

		// increment currSize (reading a new node)
		currSize++;

		// increment currSize with the result of the leftChild recursive call
		currSize += sizeHelper(currentNode.leftChild);

		// increment currSize with the result of the rightChild recursive call
		currSize += sizeHelper(currentNode.rightChild);

		// return currSize
		return currSize;
	}

	/**
	 * a helper method that helps to find the successor node to "node" parameter.
	 * 
	 * @param node the node whose successor we need to find
	 * @return the successor to "node"
	 */
	private Node findSuccessor(Node node) {
		// while the node's leftChild is not null,
		while (node.leftChild != null) {
			// we set node to its left child and repeat the loop
			node = node.leftChild;
		}
		// return node (should be smallest non-null node in the sub-tree)
		return node;
	}
}
