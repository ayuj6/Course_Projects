// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Iterating to Philosophy - FiniteIterator class
// Files: FiniteIterator.java
// Course: CS300 - Spring 2019
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
import java.util.Iterator;

/**
 * This class is used to create a Finite Iterator. It has methods that will get the next value and
 * keep track of whether the iterator has a next value.
 * 
 * @author ayujprasad
 * 
 * @param <T> The generic data type that the class can make use of
 */
public class FiniteIterator<T> implements Iterator<T> {

  // the infinite iterator that will be used to create the finite iterator
  private InfiniteIterator<T> iterator;

  // the variable that holds the number of iterations
  private int length;

  // the counter that will be utilized by the "next" method
  private int counter;

  /**
   * This constructor will be used to assign the iterator, length and counter fields
   * 
   * @param iterator the infinite iterator to take in as an input
   * @param length the number of iterations that will be done
   */
  public FiniteIterator(InfiniteIterator<T> iterator, int length) {
    // the global fields are set with the respective parameters
    this.iterator = iterator;
    this.length = length;

    // counter is initialized to 0
    this.counter = 0;
  }

  /**
   * This method returns true or false depending on whether there is another value in the iterator
   * 
   * @return true if the counter has matched or exceeded the length field, false otherwise
   */
  @Override
  public boolean hasNext() {
    // if the counter is the same or greater than the length field,
    if (counter >= length) {
      // then return false
      return false;

      // otherwise, return true
    } else {
      return true;
    }
  }

  /**
   * This value returns the next value in the iterator
   * 
   * @return the next value in the iterator
   */
  @Override
  public T next() {
    // increment the counter
    counter++;

    // return the next value from the iterator's "next" method
    return this.iterator.next();
  }
}
