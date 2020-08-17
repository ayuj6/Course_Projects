// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 - DICTIONARY USING BST - DictionaryWord class
// Files: DictionaryWord.java
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
/**
 * This class models a binary node of a Binary Search Tree. It also provides methods to set and get
 * the node's left and right children. It also contains a method to get the word and meaning in the
 * current node.
 * 
 * @author ayujprasad
 */
public class DictionaryWord {

  // word that represents the search key for this dictionary word
  private final String word;

  // The meaning of the word that this dictionary node defines
  private final String meaning;

  // The left child of the the current Node
  private DictionaryWord leftChild;

  // The right child of the the current Node
  private DictionaryWord rightChild;

  /**
   * Creates a new dictionary word with the provided word and its meaning pair Throws
   * IllegalArgumentException when the word or meaning are either references to an empty string or
   * null references. The thrown exception includes a significant error message describing which
   * problem was encountered.
   * 
   * @param word the word to be allocated to the node
   * @param meaning the meaning associated with given word
   */
  public DictionaryWord(String word, String meaning) {

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

    // allocate the word and meaning parameters to the word and meaning fields
    this.word = word;
    this.meaning = meaning;
  }

  /**
   * Getter for the left child of this dictionary word
   * 
   * @return the left child of the current node
   */
  public DictionaryWord getLeftChild() {
    // return the left child to the current node
    return this.leftChild;
  }

  /**
   * Setter for the left child of this dictionary word
   * 
   * @param leftChild the left child of the current node to be set
   */
  public void setLeftChild(DictionaryWord leftChild) {
    // set the left child of the current node to the parameter "leftChild"
    this.leftChild = leftChild;
  }

  /**
   * Getter for the right child of this dictionary word
   * 
   * @return the right child of the current node
   */
  public DictionaryWord getRightChild() {
    // return the right child of the current node
    return this.rightChild;
  }

  /**
   * Getter for the right child of this dictionary word
   * 
   * @param rightChild the right child of the current node to be set
   */
  public void setRightChild(DictionaryWord rightChild) {
    // set the right child of the current node to the parameter "rightChild"
    this.rightChild = rightChild;
  }

  /**
   * Getter for the word of this dictionary word
   * 
   * @return return the word associated with the current node
   */
  public String getWord() {
    // return the word associated with the current node
    return this.word;
  }

  /**
   * Getter for the meaning of the word of this dictionary word
   * 
   * @return return the meaning associated with the current word
   */
  public String getMeaning() {
    // return the meaning associated with the current word
    return this.meaning;
  }

  /**
   * Returns a String representation of this DictionaryWord. This String should be formatted as
   * follows. "<word>: <meaning>". For instance, for a dictionaryWord that has the String "Awesome"
   * for the instance field word and the String "adj. Inspiring awe; dreaded." as value for meaning
   * field, the String representing that dictionaryWord is "Awesome: adj. Inspiring awe; dreaded."
   */
  public String toString() {
    // return the format of the word and its meaning
    return this.word + ": " + this.meaning;
  }
}
