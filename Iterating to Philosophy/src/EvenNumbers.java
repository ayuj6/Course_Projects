// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Iterating to Philosophy - EvenNumbers class
// Files: EvenNumbers.java
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
 * This class is an example of an EvenNumber Infinite iterator. It contains methods that will help
 * to return the next smallest even number in our iterator.
 * 
 * @author ayujprasad
 *
 */
public class EvenNumbers implements Iterator<Integer> {

  // the num field that holds the declared number
  private int num;

  // the counter that will be used in the "next" method
  private int counter;

  /**
   * this constructor creates an instance of EvenNumbers with the field num being initialized with
   * "num" and a counter being set to 0.
   * 
   * @param num
   */
  public EvenNumbers(int num) {
    // set the field "num" to the parameter "num"
    this.num = num;

    // set the counter of this new instance to 0
    this.counter = 0;
  }


  /**
   * For an infinite iterator, this method always returns true.
   * 
   * @return true
   */
  @Override
  public boolean hasNext() {
    // return true
    return true;
  }


  /**
   * This method should return the smallest even number that is larger than the previously returned
   * one.
   * 
   * @return the next smallest even number in the series
   */
  @Override
  public Integer next() {
    // if the counter is 0,
    if (this.counter == 0) {

      // then increment the counter by 2
      this.counter += 2;

      // and return the current number in "num"
      return this.num;

      // otherwise (if counter is not 0 and is 2),
    } else {
      // add counter to num and store the result in num
      num += this.counter;

      // return the new value in num
      return num;
    }
  }
}
