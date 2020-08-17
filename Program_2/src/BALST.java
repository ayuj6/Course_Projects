// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AVL tree - BALST.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * Class to implement a BalanceSearchTree. This is an AVL tree.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

	// the root of the AVL tree
	private BSTNode<K, V> root;

	// variable to store number of keys in the tree
	private int numKeys;

	// the constructor when creating the AVL tree
	public BALST() {
		// set root to null
		root = null;
		// set numKeys to 0
		numKeys = 0;
	}

	/**
	 * Returns the key that is in the root node of this BST. If root is null,
	 * returns null.
	 * 
	 * @return key found at root node, or null
	 */
	@Override
	public K getKeyAtRoot() {
		// if the root is null (empty tree),
		if (root == null) {
			// return null
			return null;
		}

		// otherwise,
		else {
			// return the key at the root
			return root.key;
		}
	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the left child. If
	 * the left child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the left child of the found key
	 * 
	 * @throws IllegalNullKeyException if key argument is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// if the key is null, throw a new IllegalArgumentException
		if (key == null) {
			throw new IllegalArgumentException();
		}

		// store the value returned from the helper method
		BSTNode<K, V> returnVal = containsHelper(key, root);

		// if the returned value is null,
		if (returnVal == null) {
			// throw a new KeyNotFoundException
			throw new KeyNotFoundException();
		}

		// if the left child of this node is null (not present),
		if (returnVal.leftChild == null) {
			// return null
			return null;
		}

		// otherwise, return the key to the leftChild
		return returnVal.leftChild.key;

	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the right child. If
	 * the right child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the right child of the found key
	 * 
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// if the key is null, throw a new IllegalArgumentException
		if (key == null) {
			throw new IllegalArgumentException();
		}

		// store the value returned from the helper method
		BSTNode<K, V> returnVal = containsHelper(key, root);

		// if the returned value is null,
		if (returnVal == null) {
			// throw a new KeyNotFoundException
			throw new KeyNotFoundException();
		}

		// if the right child of this node is null (not present),
		if (returnVal.rightChild == null) {
			// return null
			return null;
		}

		// otherwise, return the key of the rightChild
		return returnVal.rightChild.key;

	}

	/**
	 * Returns the height of this BST. H is defined as the number of levels in the
	 * tree.
	 * 
	 * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max(
	 * height(root.left), height(root.right) )
	 * 
	 * Examples: A BST with no keys, has a height of zero (0). A BST with one key,
	 * has a height of one (1). A BST with two keys, has a height of two (2). A BST
	 * with three keys, can be balanced with a height of two(2) or it may be linear
	 * with a height of three (3) ... and so on for tree with other heights
	 * 
	 * @return the number of levels that contain keys in this BINARY SEARCH TREE
	 *         (AVL)
	 */
	@Override
	public int getHeight() {
		// return the value returned from the helper method (called from root)
		return height(root);
	}

	/**
	 * Returns the keys of the data structure in sorted order. In the case of binary
	 * search trees, the visit order is: L V R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in-order
	 */
	@Override
	public List<K> getInOrderTraversal() {
		// the list that will be used to store the values
		List<K> keyList = new ArrayList<K>();

		// return the list that is returned from the helper method
		return inOrderTraversal(root, keyList);
	}

	/**
	 * Returns the keys of the data structure in pre-order traversal order. In the
	 * case of binary search trees, the order is: V L R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in pre-order
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		// the list that will be used to store the values
		List<K> keyList = new ArrayList<K>();

		// return the list that is returned from the helper method
		return preOrderTraversal(root, keyList);
	}

	/**
	 * Returns the keys of the data structure in post-order traversal order. In the
	 * case of binary search trees, the order is: L R V
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in post-order
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		// the list that will be used to store the values
		List<K> keyList = new ArrayList<K>();

		// return the list that is returned from the helper method
		return postOrderTraversal(root, keyList);
	}

	/**
	 * Returns the keys of the data structure in level-order traversal order.
	 * 
	 * The root is first in the list, then the keys found in the next level down,
	 * and so on.
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in level-order
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		// store the height of the tree
		int h = height(root);

		// the list that will be used to store the values
		List<K> keyList = new ArrayList<K>();

		// for each level in the tree
		for (int i = 1; i <= h; i++) {
			// update the list with the value returned from the helper method
			keyList = printGivenLevel(root, i, keyList);
		}

		// return the final list
		return keyList;
	}

	/**
	 * Add the key,value pair to the data structure and increase the number of keys.
	 * If key is null, throw IllegalNullKeyException; If key is already in data
	 * structure, throw DuplicateKeyException(); Do not increase the num of keys in
	 * the structure, if key,value pair is not added.
	 * 
	 * @param key   is the key of the key-value pair to be added
	 * @param value is the value of the key-value pair to be added
	 * 
	 * @throws IllegalNullKeyException if the key is null
	 * @throws DuplicateKeyException   if the key is already in the tree
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// if key is null, throw a new IllegalArgumentException
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// make a BSTNode from the key-value pair
		BSTNode<K, V> insertNode = new BSTNode<K, V>(key, value);

		// set the root of the tree to be the return value of the helper method
		this.root = insertHelper(insertNode, this.root);

		// increment numKeys
		numKeys++;

	}

	/**
	 * If key is found, remove the key,value pair from the data structure and
	 * decrease num keys. If key is not found, do not decrease the number of keys in
	 * the data structure. If key is null, throw IllegalNullKeyException If key is
	 * not found, throw KeyNotFoundException().
	 * 
	 * @param key is the key to search and remove from the tree
	 * 
	 * @throws IllegalNullKeyException if the key is null
	 * @throws KeyNotFoundException    if the key is not found
	 * 
	 * @return true if the key is found
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// if the key is null, throw a new IllegalNullKeyException
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// if the root is null (empty structure)
		if (root == null) {
			// then throw a new KeyNotFoundException
			throw new KeyNotFoundException();
		}

		// make a call to the helper method starting from the root, and store the result
		// in the node
		BSTNode<K, V> helperResult = removeHelper(key, root);

		// if the result from the helper method is null,
		if (helperResult == null) {
			// throw a new KeyNotFoundException
			throw new KeyNotFoundException();

			// otherwise,
		} else {
			// decrement numKeys (as a key is removed)
			this.numKeys--;

			// return true
			return true;
		}
	}

	/**
	 * Returns the value associated with the specified key
	 *
	 * Does not remove key or decrease number of keys If key is null, throw
	 * IllegalNullKeyException If key is not found, throw KeyNotFoundException().
	 * 
	 * @param key the key to get from the tree
	 * 
	 * @throws IllegalNullKeyException if the key is null
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// if the key is null, throw a new IllegalNullKeyException
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// return the value returned from the helper method
		V returnVal = getHelper(key, root);

		// if the value is null, throw a new KeyNotFoundException
		if (returnVal == null) {
			throw new KeyNotFoundException();
		}

		// return the value
		return returnVal;

	}

	/**
	 * Returns true if the key is in the data structure If key is null, throw
	 * IllegalNullKeyException Returns false if key is not null and is not present
	 * 
	 * @param key the key to get from the tree
	 * @return true if present, false otherwise
	 * @throws IllegalNullKeyException if the key is null
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		// if the key is null, throw a new IllegalNullKeyException
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// if the root is null (empty tree)
		if (root == null) {
			// return false
			return false;
		}

		// if there are no keys (empty tree)
		if (numKeys == 0) {
			// return false
			return false;
		}

		// store the value returned from the helper method
		BSTNode<K, V> foundNode = containsHelper(key, root);

		// if the node returned is null, return false
		if (foundNode == null) {
			return false;
		}

		// otherwise return true
		return true;

	}

	/**
	 * Returns the number of key,value pairs in the data structure
	 */
	@Override
	public int numKeys() {
		// return the variable numKeys
		return numKeys;
	}

	/**
	 * Print the tree.
	 */
	@Override
	public void print() {
		// call the helper method to print the tree
		printHelper(root, 0);
	}

	/**
	 * Private helper method to print out the tree.
	 * 
	 * @param root  the currentNode of the tree
	 * @param space the number of spaces that we will leave
	 */
	private void printHelper(BSTNode<K, V> root, int distance) {
		// Base case
		if (root == null)
			return;

		// Increase distance between levels
		distance += 10;

		// Call helper method for the rightChild
		printHelper(root.rightChild, distance);

		// Print current node after printing given spaces
		System.out.print("\n");
		for (int i = 10; i < distance; i++) {
			System.out.print(" ");
		}
		System.out.print(root.key + "\n");

		// Call helper method for leftChild
		printHelper(root.leftChild, distance);

	}

	/**
	 * Private helper method to help return the value of the node with the given
	 * key.
	 * 
	 * @param k           the key whose value we need to obtain
	 * @param currentNode the node that we are currently on
	 * @return the value of the node with the corresponding key k
	 */
	private V getHelper(K k, BSTNode<K, V> currentNode) {

		// if the currentNode is null, return null
		if (currentNode == null) {
			return null;
		}

		// if the comparison of keys is 0 (a match),
		if (k.compareTo(currentNode.key) == 0) {
			// return the value stored in the currentNode
			return currentNode.value;
		}

		// otherwise, if the comparison of keys is >0 (k is greater than currentNode's
		// key),
		else if (k.compareTo(currentNode.key) > 0) {
			// return the call of the helper method with currentNode's rightChild
			return getHelper(k, currentNode.rightChild);
		}

		// otherwise, (k is less than currentNode's key)
		else if (k.compareTo(currentNode.key) < 0) {
			// return the call of the helper method with currentNode's leftChild
			return getHelper(k, currentNode.leftChild);
		}

		// otherwise return null
		return null;
	}

	/**
	 * The private helper method that will recurse through the tree to get the
	 * longest length between the root and a leaf node.
	 * 
	 * @param root the currentNode we are at in the tree
	 * @return the height of the tree
	 */
	private int height(BSTNode<K, V> root) {

		// if the currentNode is null,
		if (root == null) {
			// return 0
			return 0;
		}
		// if the currentNode has no children (leaf node),
		else if (root.rightChild == null && root.leftChild == null) {
			// return 1
			return 1;

			// otherwise,
		} else {
			// return the result of the private helper method call with right and left
			// children of currentNode
			return 1 + Math.max(height(root.leftChild), height(root.rightChild));
		}
	}

	/**
	 * The private helper method that will go through the tree in order and store
	 * them in a list.
	 * 
	 * @param root    the currentNode in the tree
	 * @param keyList the list that will store the keys of the tree
	 * @return the keyList that stores the keys in-order
	 */
	private List<K> inOrderTraversal(BSTNode<K, V> root, List<K> keyList) {

		// if the root is not null,
		if (root != null) {
			// keep going to the leftChild of the currentNode (with recursive call)
			inOrderTraversal(root.leftChild, keyList);
			// add the currentNode's key to the list
			keyList.add((K) root.key);
			// keep going to the rightChild of the currentNode (with recursive call)
			inOrderTraversal(root.rightChild, keyList);
		}
		// return the final keyList
		return keyList;
	}

	/**
	 * The private helper method that will go through the tree in pre-order and
	 * store them in a list.
	 * 
	 * @param root    the currentNode in the tree
	 * @param keyList the list that will store the keys of the tree
	 * @return the keyList that stores the keys in pre-order
	 */
	private List<K> preOrderTraversal(BSTNode<K, V> root, List<K> keyList) {

		// if the root is not null,
		if (root != null) {
			// add the currentNode's key to the list
			keyList.add((K) root.key);
			// keep going to the leftChild of the currentNode (with recursive call)
			preOrderTraversal(root.leftChild, keyList);
			// keep going to the rightChild of the currentNode (with recursive call)
			preOrderTraversal(root.rightChild, keyList);
		}
		// return the final keyList
		return keyList;
	}

	/**
	 * The private helper method that will go through the tree in post-order and
	 * store them in a list.
	 * 
	 * @param root    the currentNode in the tree
	 * @param keyList the list that will store the keys of the tree
	 * @return the keyList that stores the keys in post-order
	 */
	private List<K> postOrderTraversal(BSTNode<K, V> root, List<K> keyList) {

		// if the root is not null,
		if (root != null) {
			// keep going to the leftChild of the currentNode (with recursive call)
			postOrderTraversal(root.leftChild, keyList);
			// keep going to the rightChild of the currentNode (with recursive call)
			postOrderTraversal(root.rightChild, keyList);
			// add the currentNode's key to the list
			keyList.add((K) root.key);
		}
		// return the final keyList
		return keyList;
	}

	/**
	 * The private helper method that will go through the tree in level-order and
	 * store them in a list.
	 * 
	 * @param root    the currentNode in the tree
	 * @param level   the current level we are at in the tree
	 * @param keyList the list that will store the keys of the tree
	 * @return the keyList that stores the keys in level-order
	 */
	private List<K> printGivenLevel(BSTNode<K, V> root, int level, List<K> keyList) {
		// if the root is null,
		if (root == null) {
			// return our keyList
			return keyList;
		}

		// if the current level is 1,
		if (level == 1) {
			// add the currentNode's key to the keyList
			keyList.add(root.key);
		}

		// otherwise, if the level is greater than 1 (need to get the children),
		else if (level > 1) {
			// call the recursive method with the leftChild and reduce a level
			printGivenLevel(root.leftChild, level - 1, keyList);
			// call the recursive method with the rightChild and reduce a level
			printGivenLevel(root.rightChild, level - 1, keyList);
		}

		// return the final keyList
		return keyList;
	}

	/**
	 * This method will be used to re-structure the tree using a rightRotate if it
	 * get's out of balance.
	 * 
	 * @param currentNode our node that is currently out of balance
	 * @return the new root of our re-balance
	 */
	private BSTNode<K, V> rightRotate(BSTNode<K, V> currentNode) {
		// set a new node to hold currentNode's leftChild
		BSTNode<K, V> secondNode = currentNode.leftChild;

		// set the leftChild to be this new node's rightChild
		currentNode.leftChild = secondNode.rightChild;

		// set this newNode's rightChild to be our original currentNode
		secondNode.rightChild = currentNode;

		// Return new root after re-balance
		return secondNode;
	}

	/**
	 * This method will be used to re-structure the tree using a leftRotate if it
	 * get's out of balance.
	 * 
	 * @param currentNode our node that is currently out of balance
	 * @return the new root of our re-balance
	 */
	private BSTNode<K, V> leftRotate(BSTNode<K, V> currentNode) {
		// set a new node to hold currentNode's rightChild
		BSTNode<K, V> secondNode = currentNode.rightChild;

		// set the rightChildChild to be this new node's leftChild
		currentNode.rightChild = secondNode.leftChild;

		// set this newNode's leftChild to be our original currentNode
		secondNode.leftChild = currentNode;

		// Return new root after re-balance
		return secondNode;
	}

	/**
	 * Recursive helper method that checks to see if a given key is present in the
	 * data structure or not.
	 * 
	 * @param k           the key to search for
	 * @param currentNode the node to do the search from
	 * @return the node which has the key in the structure, null otherwise
	 */
	private BSTNode<K, V> containsHelper(K k, BSTNode<K, V> currentNode) {

		// if the currentNode is null, return null
		if (currentNode == null) {
			return null;
		}

		// store the result of the comparison between the currentNode's key and our key
		// into
		// a variable
		int comparison = (k).compareTo(currentNode.key);

		// if comparison is 0 (found), return the currentNode
		if (comparison == 0) {
			return currentNode;

			// else, if comparison is >0 (k is greater),
		} else if (comparison > 0) {
			// return the value obtained from recursive helper method with rightChild
			return containsHelper(k, currentNode.rightChild);

			// else, if comparison <0 (k is smaller),
		} else {
			// return the value obtained from recursive helper method with leftChild
			return containsHelper(k, currentNode.leftChild);
		}
	}

	/**
	 * This helper method is used to search for a position in the tree to insert
	 * "insertNode". It will then re-balance the tree based on where the insert
	 * happened.
	 * 
	 * @param insertNode the node to be inserted into the tree
	 * @param root the current position in our tree
	 * @return the root (will be set to root)
	 * @throws DuplicateKeyException if the key is already present in the key
	 */
	private BSTNode<K, V> insertHelper(BSTNode<K, V> insertNode, BSTNode<K, V> root) throws DuplicateKeyException {

		// if the root is null, return insertNode
		if (root == null) {
			return insertNode;
		}

		// if the key comparison between insertNode and our currentNode is >0,
		if (insertNode.key.compareTo(root.key) > 0) {
			// set currentNode's rightChild with the return value of the recursive call with the rightChild
			root.rightChild = insertHelper(insertNode, root.rightChild);
			
			// else, if the key comparison between insertNode and our currentNode is <0,
		} else if (insertNode.key.compareTo(root.key) < 0) {
			// set currentNode's leftChild with the return value of the recursive call with the leftChild
			root.leftChild = insertHelper(insertNode, root.leftChild);
			
			// otherwise, (the keys are equal),
		} else {
			// throw a new DuplicateKeyException
			throw new DuplicateKeyException();
		}

		// Check for re-balance after inserting node
		
		// get the balance factor with the left and right sub-trees
		int balanceFactor = height(root.leftChild) - height(root.rightChild);

		// Left Left Case
		if (balanceFactor > 1 && insertNode.key.compareTo(root.leftChild.key) < 0)
			return rightRotate(root);

		// Right Right Case
		if (balanceFactor < -1 && insertNode.key.compareTo(root.rightChild.key) > 0)
			return leftRotate(root);

		// Left Right Case
		if (balanceFactor > 1 && insertNode.key.compareTo(root.leftChild.key) > 0) {
			root.leftChild = leftRotate(root.leftChild);
			return rightRotate(root);
		}

		// Right Left Case
		if (balanceFactor < -1 && insertNode.key.compareTo(root.rightChild.key) < 0) {
			root.rightChild = rightRotate(root.rightChild);
			return leftRotate(root);
		}
		
		// return root
		return root;
	}

	/**
	 * Recursive helper method that will help search for the key in the structure
	 * that has to be removed.
	 * 
	 * @param k           the value to be removed from the structure
	 * @param currentNode the node to start the search from
	 * @return null if the node is not present, the Node removed if present
	 */
	private BSTNode<K, V> removeHelper(K k, BSTNode<K, V> currentNode) {

		// set a node called parent to null
		BSTNode<K, V> parent = null;

		// set a node called currNode to hold the node "currentNode"
		BSTNode<K, V> currNode = currentNode;

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

				// otherwise (if this was the same as currentNode),
			} else {
				// set root to null
				root = null;
			}

			// if currNode has 2 children,
		} else if (currNode.leftChild != null && currNode.rightChild != null) {

			// find the successor to currNode using the helper method
			BSTNode<K, V> successor = findSuccessor(currNode.rightChild);

			// store the key and values in the respective variables
			K keyValue = successor.key;
			V successorValue = successor.value;

			// remove the successor from the structure
			removeHelper(keyValue, currentNode);

			// set currNode's key and value to the successor's key and value respectively
			currNode.key = keyValue;
			currNode.value = successorValue;

			// otherwise (1 child),
		} else {

			// find child node
			BSTNode<K, V> childNode = (currNode.leftChild != null) ? currNode.leftChild : currNode.rightChild;

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
	 * a helper method that helps to find the successor node to "node" parameter.
	 * 
	 * @param node the node whose successor we need to find
	 * @return the successor to "node"
	 */
	private BSTNode<K, V> findSuccessor(BSTNode<K, V> node) {
		// while the node's leftChild is not null,
		while (node.leftChild != null) {
			// we set node to its left child and repeat the loop
			node = node.leftChild;
		}
		// return node (should be smallest non-null node in the sub-tree)
		return node;
	}
}
