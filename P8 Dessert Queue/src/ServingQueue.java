// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 - Dessert Queue - ServingQueue class
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
 * This class is used to make the serving queue that will be used in other classes. It contains
 * methods to add a guest to the queue, remove a guest from the queue, peek at the next guest to be
 * removed, and check if our queue is empty.
 * 
 * @author ayujprasad
 */
public class ServingQueue {

  // array to store all the guests we add
  private Guest[] array;

  // the position to add the next guest to
  private int enqueueTo = 0;

  // the size of the queue (number of people currently present in the queue)
  private int size = 0;

  // max number of people the queue can hold
  private int totalGuests = 0;

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public ServingQueue(int seatsAtTable) {
    // initialize the array to be of length "seatsAtTable"
    array = new Guest[seatsAtTable];

    // set totalGuests to be of the same size
    totalGuests = seatsAtTable;

  }

  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {
    // check if size is 0 (no guests in queue) and return the result
    return (size == 0);
  }

  /**
   * Adds a single new guest to this queue (served after the others that were previously added to
   * the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   */
  public void add(Guest newGuest) {
    // if the size is full,
    if (size == totalGuests) {
      // throw an IllegalStateException
      throw new IllegalStateException();
    }

    // add the new guest to the current enqueueTo position
    array[enqueueTo] = newGuest;

    // increment size (as we added a guest)
    size++;

    // change index of enqueueTo
    enqueueTo = (enqueueTo + 1) % array.length;
  }



  /**
   * Accessor for the guest that has been in this queue for the longest amount of time. This method
   * does not add or remove any guests.
   * 
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    // if there are no guests in our queue
    if (size == 0) {
      // throw an IllegalStateException
      throw new IllegalStateException();
    }

    // add the guest at the front of the queue to tempGuest
    Guest tempGuest = array[(enqueueTo - size + array.length) % array.length];

    // return tempGuest
    return tempGuest;
  }


  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    // if there are no guests in our queue
    if (size == 0) {
      // throw an IllegalStateException
      throw new IllegalStateException();
    }

    // add the guest at the front of the queue to tempGuest
    Guest tempGuest = array[(enqueueTo - size + array.length) % array.length];

    // set tempGuest's index in the queue to null
    array[(enqueueTo - size + array.length) % array.length] = null;

    // decrement size
    size--;

    // return tempGuest
    return tempGuest;
  }


  /**
   * The string representation of the guests in this queue will display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue for the longest amount of time, to the
   * guest that has been in this queue the shortest. When called on an empty ServingQueue, returns
   * "[]".
   * 
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    // set an output string to the open bracket "["
    String output = "[";

    // for each guest in our queue,
    for (Guest guestToAdd : array) {
      // if it is null, we leave it and continue
      if (guestToAdd == null) {
        continue;

        // otherwise,
      } else {
        // we add that to our string followed by a comma
        output = output + guestToAdd.toString() + ", ";
      }
    }

    // we close our string with a close bracket "]"
    output = output + "]";

    // return our string to output
    return output;
  }

}
