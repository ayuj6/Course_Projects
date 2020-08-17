// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Iterating to Philosophy - InfiniteIterator class
// Files: InfiniteIterator.java
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
import java.util.function.Function;

/**
 * This class is used to create an Infinite Iterator. It has methods that will get the next value
 * and keep track of whether the iterator has a next value. It has 2 apply methods that are used to
 * find the next value depending on the type of iterator value being returned.
 * 
 * @author ayujprasad
 *
 * @param <T> The generic data type that the class can make use of
 */
public class InfiniteIterator<T> implements Iterator<T> {

  // the first value in the iterating series
  private T startValue;

  // the next value in the iterating series
  private T nextValue;

  // counter to be utilized in the "next" method
  private int counter;

  // function to be utilized by the iterator
  private Function<T, T> func;

  /**
   * This constructor is used to create an infinite iterator and assigns the startValue, func and
   * counter fields.
   * 
   * @param num the starting value to take
   * @param func the function to make use of for this iterator
   */
  public InfiniteIterator(T startValue, Function<T, T> func) {
    // the global fields are set with the respective parameters
    this.startValue = startValue;
    this.func = func;

    // counter is initialized to 0
    this.counter = 0;
  }

  /**
   * The hasNext method for the infinite iterator always returns true.
   * 
   * @return always returns true
   */
  @Override
  public boolean hasNext() {
    // return true
    return true;
  }

  /**
   * This method returns the next value that occurs in the iterator.
   * 
   * @return returns the next value in the iterator
   */
  @Override
  public T next() {
    // if the counter is 0,
    if (this.counter == 0) {
      // then increment counter and return the original startValue
      counter++;
      return startValue;

      // otherwise (if counter is greater than 0),
    } else {
      // use the apply method on our function and obtain the next value
      nextValue = func.apply(startValue);

      // store this next value into our "startValue variable"
      startValue = nextValue;

      // increment counter
      counter++;

      // return nextValue
      return nextValue;
    }
  }


  /**
   * This method is used to return the next value in the iterator
   * 
   * @param t the current number in the iterator
   * @return the integer that is twice the value of the value entered
   */
  public Integer apply(Integer t) {
    // return the input parameter multiplied by 2 (2 times the input)
    return 2 * t;
  }

  /**
   * This method is used to return the next value in the iterator
   * 
   * @param t the current string value in the iterator
   * @return the original string entered but with an extra smile face at the end.
   */
  public String apply(String t) {
    // return the input string with an extra smile face at the end
    return t + " :)";
  }
}
