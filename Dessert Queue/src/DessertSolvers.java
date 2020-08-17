// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 - Dessert Queue - DesserSolvers class
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
 * This class is used to implement 2 methods that perform similar tasks (based on the Josephus
 * problem). These methods removing guests from the queue when they are served, and by removing and
 * then adding back to this queue guests who are skipped over and continue waiting to be served.
 * 
 * @author ayujprasad
 */
public class DessertSolvers {

  // a counter to keep track of how many guests are left in the queue
  private static int counter = 0;

  // the main serving queue to hold the guests to be served
  private static ServingQueue servingQueue;

  // the queue to be used to hold the guests that were removed from our original queue
  private static ServingQueue removeQueue;

  // a counter to keep track of number of courses that have been served
  private static int coursesCounter = 0;

  /**
   * This method serves "numberOfGuests" number of guests in our queue and skips "guestsSkipped"
   * guests. It uses this value and the Josephus problem to solve for who is the last guest that
   * will be served.
   * 
   * @param numberOfGuests number of guests in our queue
   * @param guestsSkipped the number of guests we skip while we are serving
   * @return the guest that is served last
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    // if number of guests is not positive,
    if (numberOfGuests <= 0) {
      // throw an IllegalArgumentException
      throw new IllegalArgumentException("Invalid Parameter for numberOfGuests!");
    }

    // if guestsSkipped is negative,
    if (guestsSkipped < 0) {
      // throw an IllegalArgumentException
      throw new IllegalArgumentException("Invalid Parameter for guestsSkipped!");
    }

    // reset the number of guests
    Guest.resetNextGuestIndex();

    // we initialize our servingQueue with "numberOfGuests" number of guests
    servingQueue = new ServingQueue(numberOfGuests);

    // we set our counter to the number of guests in the queue
    counter = numberOfGuests;

    // fill up our queue by adding the appropriate number of guests
    for (int i = 0; i < numberOfGuests; ++i) {
      servingQueue.add(new Guest());
    }

    // while we still have more than one person in our queue
    while (counter > 1) {

      // we remove the next person to remove
      servingQueue.remove();

      // decrement our counter (as we removed a person from our queue)
      counter--;

      // for the number of skips we have
      for (int i = 0; i < guestsSkipped; i++) {
        // we will remove a guest from the front of the queue and add them to the end
        servingQueue.add(servingQueue.remove());
      }
    }

    // return the last person left in the queue
    return servingQueue.peek();
  }

  /**
   * This method takes the number of guests at a party, creates as many guests with an index
   * starting at one, simulate the serving of those guests through the specified number of courses,
   * and then return a reference to the guest that is served last in the second to last course (as
   * this is the guest who will be served dessert first).
   * 
   * @param numberOfGuests number of guests in our queue
   * @param coursesServed the number of courses that will be served to the guests
   * @return the guest that will be served dessert first
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
    // if the numberOfGuests is negative
    if (numberOfGuests <= 0) {
      // throw an IllegalArgumentException
      throw new IllegalArgumentException("Invalid Parameter for numberOfGuests!");
    }

    // if the coursesServed is negative
    if (coursesServed <= 0) {
      // throw an IllegalArgumentException
      throw new IllegalArgumentException("Invalid Parameter for coursesServed!");
    }

    // reset the number of guests
    Guest.resetNextGuestIndex();

    // set our queues with the size "numberOfGuests"
    servingQueue = new ServingQueue(numberOfGuests);
    removeQueue = new ServingQueue(numberOfGuests);

    // we set our counter to the number of guests in the queue
    counter = numberOfGuests;

    // set the coursesCounter to the number of courses to be served
    coursesCounter = coursesServed;

    // add the required number of quests to servingQueue
    for (int i = 0; i < numberOfGuests; ++i) {
      servingQueue.add(new Guest());
    }

    // if coursesServed is 1, return the first person from servingQueue
    if (coursesServed == 1) {
      return servingQueue.remove();
    }

    // while there is still more another course to be served before dessert
    while (coursesCounter > 1) {

      // if were on our second course or greater,
      if (coursesCounter < coursesServed) {
        // add the guests from the removeQueue into servingQueue
        for (int i = 0; i < numberOfGuests - 1; i++) {
          servingQueue.add(removeQueue.remove());
        }
      }

      // set our counter to the number of guests in our queue
      counter = numberOfGuests;

      // while there is still more than one guest left to be served
      while (counter > 1) {

        // we add to removeQueue - the guest to be served in servingQueue
        removeQueue.add(servingQueue.remove());

        // as we served a guest, we decrement our counter
        counter--;

        // for the number of skips (in this case 1), we take the guest at the front and add them to
        // the end of our queue
        for (int i = 0; i < 1; i++) {
          servingQueue.add(servingQueue.remove());
        }
      }

      // we are done with our current course and decrement our coursesCounter
      coursesCounter--;
    }

    // we return the last person left in our serving queue (the first person to be served dessert)
    return servingQueue.remove();
  }
}
