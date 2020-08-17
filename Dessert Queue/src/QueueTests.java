// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 - Dessert Queue - QueueTests class
// Files: Guest.java, ServingQueue.java, DessertSolvers.java, QueueTests.java
// Course: CS 300 - Spring, 2019
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
 * This class holds the test methods to check if our implemented methods in the other classes work
 * as they are supposed to without error.
 * 
 * @author ayujprasad
 */
public class QueueTests {

  /**
   * This method checks to see if our isEmpty() method in the ServingQueue class works as expected
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testServingQueueIsEmpty() {

    // initialize our testQueue, used to add and remove guests
    ServingQueue testQueue = new ServingQueue(5);

    // add guests to our queue
    testQueue.add(new Guest());
    testQueue.add(new Guest());
    testQueue.add(new Guest());

    // if the queue is empty (isEmpty working wrong),
    if (testQueue.isEmpty() != false) {
      // print the fail message
      System.out.println("testServingQueueIsEmpty FAILED");
      // and return false
      return false;
    }

    // remove guests from queue
    testQueue.remove();
    testQueue.remove();
    testQueue.remove();

    // if the queue is not empty (not working correctly)
    if (testQueue.isEmpty() != true) {
      // print the fail message
      System.out.println("testServingQueueIsEmpty FAILED");
      // and return false
      return false;
    }

    // otherwise, return true
    return true;
  }

  /**
   * This method checks to see if our add() method in the ServingQueue class works as expected
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testServingQueueAdd() {

    // initialize our testQueue, used to add and remove guests
    ServingQueue testQueue = new ServingQueue(1);

    // add guest to queue
    testQueue.add(new Guest());

    // if the queue is empty (guest did not add)
    if (testQueue.isEmpty()) {
      // print fail message
      System.out.println("testServingQueueAdd FAILED");
      // and return false
      return false;
    }

    // try to add another guest
    try {
      testQueue.add(new Guest());
    }

    // catch IllegalStateException
    catch (IllegalStateException e) {
      // return true as this was supposed to happen
      return true;
    }

    // otherwise, return false
    return false;
  }


  /**
   * This method checks to see if our remove() method in the ServingQueue class works as expected
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testServingQueueRemove() {

    // initialize our testQueue, used to add and remove guests
    ServingQueue testQueue = new ServingQueue(2);

    // add guests to queue
    testQueue.add(new Guest());
    testQueue.add(new Guest());

    // remove one guest from our queue
    testQueue.remove();

    // if the queue is empty (just check to see if it removes 1 guest)
    if (testQueue.isEmpty()) {
      // print out the fail message
      System.out.println("testServingQueueAdd FAILED");
      // and return false
      return false;
    }

    // remove another guest from our queue
    testQueue.remove();

    // if the queue is empty
    if (testQueue.isEmpty()) {
      // return true
      return true;
    }

    // otherwise return false
    return false;
  }

  /**
   * This method checks to see if our peek() method in the ServingQueue class works as expected
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testServingQueuePeek() {

    // initialize our testQueue, used to add and remove guests
    ServingQueue testQueue = new ServingQueue(2);

    // 2 guests created to be added to the queue
    Guest testGuest1 = new Guest();
    Guest testGuest2 = new Guest();

    // add the guests to the queue
    testQueue.add(testGuest1);
    testQueue.add(testGuest2);

    // if the first peek does not give testGuest1
    if (testQueue.peek() != testGuest1) {
      // print out the fail message
      System.out.println("testServingQueuePeek FAILED");
      // and return false
      return false;
    }

    // remove testGuest1 from the queue
    testQueue.remove();

    // if the next peek does not give testGuest2
    if (testQueue.peek() != testGuest2) {
      // print out the fail message
      System.out.println("testServingQueuePeek FAILED");
      // and return false
      return false;
    }

    // return true
    return true;
  }

  /**
   * This method is used to make the calls to the test methods in this class. It will check to
   * confirm that our test methods work as expected.
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {

    // four boolean values used to store the results from the four tests
    boolean test1 = testServingQueueIsEmpty();
    boolean test2 = testServingQueueAdd();
    boolean test3 = testServingQueueRemove();
    boolean test4 = testServingQueuePeek();

    // if all tests returned true
    if (test1 && test2 && test3 && test4) {
      // print the pass message
      System.out.println("ALL TESTS PASSED!");

      // otherwise,
    } else {
      // print out fail (alternative) message
      System.out.println("ONE OR MORE TESTS FAILING!");
    }
  }
}
