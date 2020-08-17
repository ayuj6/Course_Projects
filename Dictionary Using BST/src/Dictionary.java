// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 - DICTIONARY USING BST - Dictionary class
// Files: Dictionary.java
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
 * This interface models and abstract data type for a dictionary
 * 
 * @author ayujprasad
 */
public interface Dictionary {

  /**
   * checks whether the dictionary is empty or not
   * 
   * @return true if the dictionary is empty, false otherwise
   */
  public boolean isEmpty();


  /**
   * Adds this word definition (word and the provided meaning) to the dictionary. Throws
   * IllegalArgumentException if either word or meaning is null or an empty string.
   * 
   * @param word the word to be added to the dictionary
   * @param meaning the meaning associated with the word to be added
   * @return true if the word was successfully added, false if word was already in the dictionary
   */
  public boolean addWord(String word, String meaning);


  /**
   * This method traverses the dictionary to return the meaning of the word "s" we are trying to
   * find. Throws a NoSuchElementException if the word s was not found in this dictionary
   * 
   * @param s the word whose meaning we are trying to find in the dictionary
   * @return the meaning of the word s if it is present in this dictionary
   */
  public String lookup(String s);


  /**
   * Traverses through the dictionary and returns the number of words stored in it.
   */
  public int size();
}
