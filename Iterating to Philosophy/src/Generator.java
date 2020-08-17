// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Iterating to Philosophy - Generator class
// Files: Generator.java
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
 * This Generator class makes use of constructors and the iterator method to create a finite or
 * infinite iterator.
 * 
 * @author ayujprasad
 *
 * @param <T> The generic data type that the class can make use of
 */
public class Generator<T> implements Iterable<T> {

  // the first value in our iterating series
  private T firstValue;

  // the function utilized by our iterator
  private Function<T, T> func;

  // the number of iterations to be performed
  private int length;

  // the variable that holds the declared infinite iterator
  private InfiniteIterator<T> IIterator;

  // the variable that holds the declared finite variable (if one is created)
  private FiniteIterator<T> FIterator;

  // the boolean value to check if the first constructor was used or not
  private boolean first;

  /**
   * This constructor makes use of the two parameters and sets them to the field variables. It also
   * sets the boolean variable to true.
   * 
   * @param firstValue the first value to use (assigned to the firstValue field)
   * @param generateNextFromLast the function that will be assigned to the "func" field.
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    // the global fields are set with the respective parameters
    this.firstValue = firstValue;
    this.func = generateNextFromLast;

    // the boolean first is set to true (first constructor is used)
    first = true;
  }

  /**
   * This constructor makes use of the three parameters and sets them to the field variables.
   * 
   * @param firstValue the first value to use (assigned to the firstValue field)
   * @param generateNextFromLast the function that will be assigned to the "func" field.
   * @param length the number of iterations to perform
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    // the global fields are set with the respective parameters
    this.firstValue = firstValue;
    this.func = generateNextFromLast;
    this.length = length;

    // the boolean first is set to false (as second constructor is used)
    first = false;
  }

  /**
   * This method creates a finite iterator and an infinite iterator. And depending on which
   * constructor was used, it will either return the correct iterator.
   * 
   * @return the respective iterator depending on which constructor was used.
   */
  public Iterator<T> iterator() {
    // create an infinite iterator with the declared fields
    IIterator = new InfiniteIterator<T>(this.firstValue, this.func);

    // if the first constructor was used,
    if (first) {
      // then we return the infinite iterator
      return IIterator;

      // otherwise,
    } else {
      // create and return a new finite iterator using the infinite iterator and length field
      FIterator = new FiniteIterator<T>(IIterator, this.length);
      return FIterator;
    }
  }
}
