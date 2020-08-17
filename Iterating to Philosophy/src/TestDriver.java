// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Iterating to Philosophy - TestDriver class
// Files: TestDriver.java
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
 * This class hold the various test methods that will test the correct functioning of other classes
 * and the methods in those classes.
 * 
 * @author ayujprasad
 *
 */
public class TestDriver {


  /**
   * Checks to see if the EvenNumbers class and methods work as expected
   * 
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testEvenNumbers() {

    // create a new instance of EvenNumbers (in this case with starting number 44)
    EvenNumbers it = new EvenNumbers(44);

    // if the first "next" call does not return 44,
    if (it.next() != 44) {

      // then print the ERROR/FAIL message
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");

      // and return false
      return false;
    }

    // if the second "next" call does not return 46,
    if (it.next() != 46) {

      // then print the ERROR/FAIL message
      System.out.println("The second call of EvenNumbers.next() "
          + "did not return the smallest even number, "
          + "that is larger than the previously returned number.");

      // and return false
      return false;
    }

    // if hasNext call on the iterator does not return true,
    if (it.hasNext() != true) {

      // then print the ERROR/FAIL message
      System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");

      // and return false
      return false;
    }

    // otherwise, return true
    return true;
  }

  /**
   * Checks to see if the NextPowerOfTwo class works as expected
   * 
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testPowersOfTwo() {

    // create a new instance of an infinite iterator
    InfiniteIterator<Integer> it = new InfiniteIterator<Integer>(8, new NextPowerOfTwo());

    // if the first "next" call does not return 8,
    if (it.next() != 8) {

      // then print the ERROR/FAIL message
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");

      // and return false
      return false;
    }

    // if the second "next" call does not return 16,
    if (it.next() != 16) {

      // then print the ERROR/FAIL message
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");

      // and return false
      return false;
    }

    // if the hasNext method does not return true,
    if (it.hasNext() != true) {

      // then print the ERROR/FAIL message
      System.out.println("InfiniteIterator.next() returned false, "
          + "but should always return true.");

      // and return false
      return false;
    }

    // otherwise, return true
    return true;
  }

  /**
   * Checks to see if our apply/function works with a string (generic) data type
   * 
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testAddExtraSmile() {

    // create a new instance of an infinite iterator of type String
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());

    // if the first "next" call does not return "Hello",
    if (!it.next().equals("Hello")) {

      // then print the ERROR/FAIL message
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");

      // and return false
      return false;
    }

    // if the second "next" call does not contain a smile face,
    if (!it.next().contains(" :)")) {

      // then print the ERROR/FAIL message
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");

      // and return false
      return false;
    }

    // if hasNext does not return true
    if (it.hasNext() != true) {

      // then print the ERROR/FAIL message
      System.out.println("InfiniteIterator.next() returned false, "
          + "but should always return true.");

      // and return false
      return false;
    }

    // otherwise, return true
    return true;
  }

  /**
   * Checks to see if the finite iterator works as expected (for a fixed length).
   * 
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testFiniteIterator() {

    // create a new instance of an infinite iterator
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());

    // using the infinite iterator, create a new instance of a finite iterator
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);

    // create an empty string s
    String s = "";

    // while hasNext (finite iterator) is true
    while (it.hasNext())
      // prepend the next value to the string
      s += " " + it.next();

    // if the string is not equal to the following sequence,
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {

      // then print the ERROR/FAIL message
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect values were returned:" + s);

      // and return false
      return false;
    }

    // otherwise, return true
    return true;
  }

  /**
   * Checks to see if our generator class works as expected and the iterator methods return an
   * infinite iterator or a finite iterator when the respective constructors are used.
   * 
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testGenerator() {

    // boolean variable that holds the result of the tests
    boolean testPassed = true;

    // test1
    {
      // create a new instance of a generator
      Generator<Integer> testGen = new Generator<Integer>(2, new NextPowerOfTwo(), 6);

      // create empty string s
      String s = "";

      // loop through each value and prepend the values returned to string s
      for (int num : testGen) {
        s += " " + num;
      }

      // if the string does not equal the sequence below,
      if (!s.equals(" 2 4 8 16 32 64")) {

        // then print the ERROR/FAIL message
        System.out.println("Repeatedly called the next() method of a FiniteIterator,"
            + "and the incorrect values were returned:" + s);

        // and set testPassed to false
        testPassed = false;
      }
    }

    // test2
    {
      // create a new instance of an iterator type
      Iterator<Integer> testIterator;

      // create a new instance of a generator
      Generator<Integer> testGen2 = new Generator<Integer>(2, new NextPowerOfTwo());

      // load the result of the "iterator" method to the variable
      testIterator = testGen2.iterator();

      // if the first "next" call does not return 2,
      if (testIterator.next() != 2) {

        // then print the ERROR/FAIL message
        System.out.println("The first call of InfiniteIterator.next() "
            + "did not return the number passed into its constructor.");

        // and set testPassed false
        testPassed = false;
      }

      // if the second "next" call does not return 4,
      if (testIterator.next() != 4) {

        // then print the ERROR/FAIL message
        System.out.println("The second call of InfiniteIterator.next() "
            + "did not return the smallest power of two number, "
            + "that is larger than the previously returned number.");

        // and set testPassed false
        testPassed = false;
      }

      // if the third "next" call does not return 8,
      if (testIterator.next() != 8) {

        // then print the ERROR/FAIL message
        System.out.println("The third call of InfiniteIterator.next() "
            + "did not return the smallest power of two number, "
            + "that is larger than the previously returned number.");

        // and set testPassed false
        testPassed = false;
      }

      // if the hasNext method does not return true,
      if (testIterator.hasNext() != true) {

        // then print the ERROR/FAIL message
        System.out.println("InfiniteIterator.next() returned false, "
            + "but should always return true.");

        // and set testPassed false
        testPassed = false;
      }
    }

    // return the value stored in testPassed
    return testPassed;
  }

  /**
   * Makes the method calls and the return values are stored in variables. It checks to see if all
   * the tests passed or not.
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {

    // boolean variables that will hold results from respective tests
    boolean test1;
    boolean test2;
    boolean test3;
    boolean test4;
    boolean test5;

    // store each result in respective variables
    test1 = testEvenNumbers();
    test2 = testPowersOfTwo();
    test3 = testAddExtraSmile();
    test4 = testFiniteIterator();
    test5 = testGenerator();

    // if all tests are true,
    if (test1 && test2 && test3 && test4 && test5) {
      // then print the pass message
      System.out.println("All tests passed!");
    }

    // otherwise,
    else {
      // print the fail message
      System.out.println("FAILED: one or more tests failing!");
    }
  }
}


/**
 * This class (implementing function) holds the overrided apply method to get the next power of 2
 * for a given integer.
 *
 */
class NextPowerOfTwo implements Function<Integer, Integer> {

  /**
   * This overrided method multiplies the input by 2 and returns the result
   * 
   * @param previous the current integer to multiply by 2
   * @return the input integer multiplied by 2
   */
  @Override
  public Integer apply(Integer previous) {
    // return the input number multiplied by 2 (twice the input)
    return 2 * previous;
  }
}


/**
 * This class (implementing function) holds the overrided apply method to add one extra smile face
 * to the input string
 *
 */
class AddExtraSmile implements Function<String, String> {

  /**
   * The overrided method takes a string input and adds an extra smile face at the end
   * 
   * @param t the current string to be altered
   * @return the original input string with an extra smile face at the end
   */
  @Override
  public String apply(String t) {
    // return the original string plus a smile face added to the end
    return t + " :)";
  }
}
