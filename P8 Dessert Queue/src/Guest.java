// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 - Dessert Queue - Guest class
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
 * This class is used to implement a Guest Object. It contains constructors to define a guest with
 * or without dietary restrictions.
 * 
 * @author ayujprasad
 */
public class Guest {

  // variable to store the current guest's index
  public int guestIndex;

  // variable to store the number of guests created (it will be used to set the guest's index)
  public static int numberGuests = 0;

  // variable to store any dietary restriction a guest may have
  private String dietRestriction;

  // the boolean variable to test if the second constructor was used (the one with restrictions)
  private boolean restriction;

  /**
   * Resets the counting of newly constructed guest indexes, so that the next new Guest that is
   * created will be associated with the guest index of one.
   * 
   * Note: that this will be helpful when running several tests, or solving solving several dessert
   * simulation problems. And that this should never be called from ServingQueue.java
   */
  public static void resetNextGuestIndex() {
    // set number of guests to 0
    numberGuests = 0;
  }

  /**
   * Constructs a new guest with no dietary restrictions. This guest should be associated with an
   * index that is one larger than the previously constructed guest (or 1, if no prior guest, or if
   * resetNextGuestIndex() was called more recently).
   */
  public Guest() {
    // set the current guests' index to be the number of Guests + 1;
    this.guestIndex = numberGuests + 1;

    // set the diet restriction of the guest to null
    this.dietRestriction = null;

    // increment number of guests (as guest has been created)
    numberGuests++;

    // set the restriction variable to false (first constructor is used)
    restriction = false;
  }

  /**
   * Constructs a new guest with the specified dietary restrictions. This guest must be associated
   * with an index that is one larger than the previously constructed guest (or 1, if no prior
   * guest, or if resetNextGuestIndex() was called more recently).
   * 
   * @param dietaryRestriction describes requirements for what this guest can and cannot eat
   */
  public Guest(String dietaryRestriction) {
    // set the current guests' index to be the number of Guests + 1;
    this.guestIndex = numberGuests + 1;

    // set the diet restriction of the guest to the dietary restriction in the parameter
    this.dietRestriction = dietaryRestriction;

    // increment number of guests (as guest has been created)
    numberGuests++;

    // set the restriction variable to true (second constructor is used)
    restriction = true;
  }

  /**
   * Access whether this guest has any dietary restrictions or not
   * 
   * @return true for guests constructed using new Guest(String), false otherwise
   */
  public boolean hasDietaryRestriction() {
    // if the second constructor was used, return true
    if (restriction) {
      return true;

      // otherwise, return false
    } else {
      return false;
    }
  }

  /**
   * The string representation of a Guest should be formatted as, for examples: #3(no dairy) for a
   * guest with a guest index of 3 and the dietary restriction of "no dairy", or: #4 for a guest
   * with a guest index of 4 and no dietary restriction
   * 
   * @return string representing the guest index and any dietary restriction they might have
   * @see java.lang.Object#toString()
   */
  public String toString() {
    // if the first restriction was used
    if (restriction) {
      // return the following string format
      return "#" + this.guestIndex + "(" + this.dietRestriction + ")";

      // otherwise,
    } else {
      // use this format
      return "#" + this.guestIndex;
    }
  }
}
