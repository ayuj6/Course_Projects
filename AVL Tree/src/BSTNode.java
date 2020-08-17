// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AVL tree - BSTNode.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// and make this an inner class in your tree implementation.
//
// package level access means that all classes in the package can access directly.
// and accessing the node fields from classes other than your balanced search 
// is bad design as it creates many more chances for bugs to be introduced and not
// caught.
//
// Classes that use this type:  <TODO, list which if any classes use this type>

/**
 * The node class for the AVL tree in BALST.java
 * @author ayujprasad
 *
 * @param <K> the data type used for the key.
 * @param <V> the data type used for the value.
 */
class BSTNode<K,V> {
    
    K key;
    V value;
    BSTNode<K,V> leftChild;
    BSTNode<K,V> rightChild;
    

    /**
     * @param key
     * @param value
     * @param leftChild
     * @param rightChild
     */
    BSTNode(K key, V value, BSTNode<K,V>  leftChild, BSTNode<K,V> rightChild) {
        this.key = key;
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    BSTNode(K key, V value) { this(key,value,null,null); }
    
    /**
	 * Getter for the right child of this dictionary word
	 * 
	 * @param rightChild the right child of the current node to be set
	 */
	void setRightChild(BSTNode<K,V> rightChild) {
		// set the right child of the current node to the parameter "rightChild"
		this.rightChild = rightChild;
	}

	/**
	 * Setter for the left child of this dictionary word
	 * 
	 * @param leftChild the left child of the current node to be set
	 */
	void setLeftChild(BSTNode<K,V> leftChild) {
		// set the left child of the current node to the parameter "leftChild"
		this.leftChild = leftChild;
	}

	/**
	 * Getter for the right child of this dictionary word
	 * 
	 * @return the right child of the current node
	 */
	BSTNode<K,V> getRightChild() {
		// return the right child of the current node
		return this.rightChild;
	}

	/**
	 * Getter for the left child of this dictionary word
	 * 
	 * @return the left child of the current node
	 */
	BSTNode<K,V> getLeftChild() {
		// return the left child of the current node
		return this.leftChild;
	}
}
