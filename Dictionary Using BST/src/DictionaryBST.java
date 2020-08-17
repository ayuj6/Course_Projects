// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 - DICTIONARY USING BST - DictionaryBST class
// Files: DictionaryBST.java
// Course: CS 300, Spring 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Gary Dahl
//
// ////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
// /////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class implements the Dictionary interface defined. It holds the method (and their private
 * helper methods) that adds words, lookup a word, return the size, check the height, get all the
 * words, and check if the BST (dictionary) is empty.
 * 
 * @author ayujprasad
 */
public class DictionaryBST implements Dictionary {

  // The root node of the binary search tree
  private DictionaryWord root;


  /**
   * Creates an empty dictionaryBST.
   */
  public DictionaryBST() {
    // set the root of the BST to null
    this.root = null;
  }

  // The methods defined in the Dictionary interface


  /**
   * checks whether the dictionary is empty or not
   */
  public boolean isEmpty() {
    // return the result of checking if the root is null
    return (root == null);
  }


  /**
   * Adds this word definition (word and the provided meaning) to the dictionary. Throws
   * IllegalArgumentException if either word or meaning is null or an empty string.
   * 
   * @param word the word to be added to the dictionary
   * @param meaning the meaning associated with the word to be added
   * @return true if the word was successfully added, false if word was already in the dictionary
   */
  public boolean addWord(String word, String meaning) {

    // if the word is null, throw the exception with the given message
    if (word == null) {
      throw new IllegalArgumentException("Word is null!");
    }

    // if the word is empty, throw the exception with the given message
    if (word == "") {
      throw new IllegalArgumentException("Word is empty!");
    }

    // if the meaning is null, throw the exception with the given message
    if (meaning == null) {
      throw new IllegalArgumentException("Meaning is null!");
    }

    // if the meaning is empty, throw the exception with the given message
    if (meaning == "") {
      throw new IllegalArgumentException("meaning is empty!");
    }

    // make a new instance of a DictionaryWord using the word and meaning to be added
    DictionaryWord wordToAdd = new DictionaryWord(word, meaning);

    // if the Dictionary is empty,
    if (isEmpty()) {
      // set the root to our wordToAdd and return true
      root = wordToAdd;
      return true;

      // otherwise,
    } else {
      // return the result of the private helper method
      return addWordHelper(wordToAdd, root);
    }
  }


  /**
   * This method traverses the dictionary to return the meaning of the word "s" we are trying to
   * find. Throws a NoSuchElementException if the word s was not found in this dictionary
   * 
   * @param s the word whose meaning we are trying to find in the dictionary
   * @return the meaning of the word s if it is present in this dictionary
   */
  public String lookup(String s) {

    // if the root is null (if the dictionary is empty)
    if (root == null) {
      // throw a NoSuchElementException
      throw new NoSuchElementException("No definition found for the word " + s);

      // otherwise,
    } else {
      // return the result of the private helper method
      return lookupHelper(s, root);
    }
  }


  /**
   * Traverses through the dictionary and returns the number of words stored in it.
   */
  public int size() {
    // return the result of the private helper method
    return sizeHelper(root);
  }

  // Public methods not defined in the Dictionary interface


  /**
   * Computes and returns the height of this dictionaryBST, as the number of nodes from root to the
   * deepest leaf DictionaryWord node.
   * 
   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
   */
  public int height() {
    // if the root is null (dictionary is empty)
    if (root == null) {
      // return 0 (as there are no words)
      return 0;

      // otherwise,
    } else {
      // return the result of the private helper method
      return heightHelper(root);
    }
  }


  /**
   * Returns all the words within this dictionary sorted from A to Z
   * 
   * @return an ArrayList that contains all the words in this dictionary sorted in the ascendant
   *         order
   */
  public ArrayList<String> getAllWords() {
    // create an arrayList that will hold all the words in the dictionary
    ArrayList<String> allWords = new ArrayList<String>();

    // assign the arrayList with the result of the private helper method
    allWords = getAllWordsHelper(root);

    // if no words are added to the dictionary, print message before returning empty array
    if (allWords.size() == 0) {
      System.out.println("Dictionary is empty.");
    }
    
    // return the arrayList
    return allWords;
  }

  // Recursive private helper methods
  // Each public method should make call to the recursive helper method with the corresponding name


  /**
   * Recursive helper method to add newWord in the subtree rooted at node
   * 
   * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
   * @param current the current DictionaryWord that is the root of the subtree where newWord will be
   *        inserted
   * @return true if the newWordNode is successfully added to this dictionary, false otherwise
   */
  private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) {

    // variable that holds the result of comparing the current word with our newWordNode's word
    int comparison = current.getWord().toLowerCase().compareTo(newWordNode.getWord().toLowerCase());

    // if the comparison is 0 (words are the same)
    if (comparison == 0) {
      // return false as we're not allowing nodes with duplicate data to be added
      return false;
    }

    // if the comparison is less than 0 (newWordNode's word is greater)
    else if (comparison < 0) {
      // check if current's rightChild is null
      if (current.getRightChild() == null) {
        // if so, set current's rightChild to newWordNode and return true
        current.setRightChild(newWordNode);
        return true;
      }

      // otherwise, return the recursive call with the current's rightChild
      return addWordHelper(newWordNode, current.getRightChild());
    }

    // otherwise, (current's word is greater)
    else {
      // check if current's leftChild is null
      if (current.getLeftChild() == null) {
        // if so, set current's leftChild to newWordNode and return true
        current.setLeftChild(newWordNode);
        return true;
      }

      // otherwise, return the recursive call with the current's leftChild
      return addWordHelper(newWordNode, current.getLeftChild());
    }
  }


  /**
   * Recursive helper method to lookup a word s in the subtree rooted at current
   * 
   * @param s String that represents a word
   * @param current pointer to the current DictionaryWord within this dictionary
   * @return the meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if s is not found in this dictionary
   */
  private static String lookupHelper(String s, DictionaryWord current) {

    // set currentMeaning to the current node's meaning
    String currentMeaning = current.getMeaning();

    // variable that holds the result of comparing the current word with our newWordNode's word
    int comparison = current.getWord().toLowerCase().compareTo(s.toLowerCase());

    // if the comparison is 0 (our words matched),
    if (comparison == 0) {
      // return the meaning of the current word's node
      return current.getMeaning();
    }

    // otherwise, if the comparison is less than 0 (s is greater)
    else if (comparison < 0) {
      // check if the current's rightChild is not null
      if (current.getRightChild() != null) {
        // set currentMeaning to the result of the recursive call with the rightChild.
        currentMeaning = lookupHelper(s, current.getRightChild());

        // else, (if node is null)
      } else {
        // throw a NoSuchElementException
        throw new NoSuchElementException("No definition found for the word " + s);
      }

      // else, if the comparison is greater than 0 (s is smaller)
    } else if (comparison > 0) {
      // check if the current's leftChild is not null
      if (current.getLeftChild() != null) {
        // set currentMeaning to the result of the recursive call with the leftChild
        currentMeaning = lookupHelper(s, current.getLeftChild());

        // else, (if node is null)
      } else {
        // throw a NoSuchElementException
        throw new NoSuchElementException("No definition found for the word " + s);
      }
    }

    // return currentMeaning
    return currentMeaning;
  }


  /**
   * Recursive helper method that returns the number of dictionary words stored in the subtree
   * rooted at current
   * 
   * @param current current DictionaryWord within this dictionaryBST
   * @return the size of the subtree rooted at current
   */
  private static int sizeHelper(DictionaryWord current) {
    // currSize (current number of nodes) is set to 0
    int currSize = 0;

    // if the current node is null,
    if (current == null) {
      // return currSize
      return currSize;
    }

    // increment currSize (reading a new node)
    currSize++;

    // increment currSize with the result of the leftChild recursive call
    currSize += sizeHelper(current.getLeftChild());

    // increment currSize with the result of the rightChild recursive call
    currSize += sizeHelper(current.getRightChild());

    // return currSize
    return currSize;
  }


  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current DictionaryWord within this DictionaryBST
   * @return height of the subtree rooted at current counting the number of DictionaryWord nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private static int heightHelper(DictionaryWord current) {
    // set left subtree height to 0
    int heightLeft = 0;

    // set right subtree height to 0
    int heightRight = 0;

    // if the current's leftChild is not null
    if (current.getLeftChild() != null) {
      // set the left height to the result of the left recursive call
      heightLeft = heightHelper(current.getLeftChild());
    }

    // if the current's rightChild is not null
    if (current.getRightChild() != null) {
      // set the right height to the result of the right recursive call
      heightRight = heightHelper(current.getRightChild());
    }

    // After both subtrees have been visited, we check if the left height is greater than the right
    // subtree
    if (heightLeft > heightRight) {
      // if so, return the left height + 1 (accounting for root)
      return heightLeft + 1;

      // otherwise,
    } else {
      // return the right height + 1 (accounting for root)
      return heightRight + 1;
    }
  }


  /**
   * Recursive Helper method that returns a list of all the words stored in the subtree rooted at
   * current
   * 
   * @param current pointer to the current DictionaryWord within this dictionaryBST
   * @return an ArrayList of all the words stored in the subtree rooted at current
   */
  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
    // set an arrayList to a new arrayList
    ArrayList<String> allWords = new ArrayList<String>();

    // if the current node is null
    if (current == null) {
      // return the arrayList
      return allWords;
    }

    // add the collection of words retrieved from the left recursive call
    allWords.addAll(getAllWordsHelper(current.getLeftChild()));

    // add the word at current node (as we have only been traversing)
    allWords.add(current.getWord());

    // add the collection of words retrieved from the right recursive call
    allWords.addAll(getAllWordsHelper(current.getRightChild()));

    // return the arrayList
    return allWords;
  }
}
