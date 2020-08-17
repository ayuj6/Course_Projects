// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P10 - Help Desk - SupportTicket class
// Files: SupportTicket.java, HelpDeskInterface.java, HelpDesk.java, HelpDeskTestSuite.java
// Course: CS 300 - Spring 2019
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
 * This class is used to implement a support ticket that will be utilized in our help desk. It also
 * has a toString method and a compareTo method to be used in the other classes.
 * 
 * @author ayujprasad
 */
public class SupportTicket implements Comparable<SupportTicket> {

  // the message associated with the ticket
  private String message;

  /**
   * This constructor is used to create an instance of a support ticket. It assigns the message in
   * the parameter to the ticket.
   * 
   * @param message the message to be associated with the support ticket being created
   * @throws NullPointerException when called with a null message
   */
  public SupportTicket(String message) {
    // if the message is null,
    if (message == null) {
      // throw a new null pointer exception with the error message
      throw new NullPointerException("The message for the ticket cannot be null!");

      // otherwise,
    } else {
      // set the message field to the parameter "message"
      this.message = message;
    }
  }

  /**
   * This method is used to print out the message associated with the ticket
   */
  public String toString() {
    // return the message associated with the support ticket.
    return this.message;
  }

  /**
   * This overridden method is used to compare the current ticket's message with the message
   * associated with the parameter "o". It will be used to sort the tickets in order of their
   * lengths/ values (larger is sorted higher up).
   * 
   * @param o the support ticket whose message is going to be compared.
   */
  @Override
  public int compareTo(SupportTicket o) {
    // if the message of the current ticket is greater than the message of o,
    if (this.message.length() > o.message.length()) {
      // return 1
      return 1;

      // otherwise, if the length of this message is less than o's message,
    } else if (this.message.length() < o.message.length()) {
      // return -1
      return -1;

      // otherwise (same size),
    } else {
      // return the value returned from the String.compareTo method
      return this.message.toUpperCase().compareTo(o.message.toUpperCase());
    }
  }
}
