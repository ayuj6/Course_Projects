// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Escape Room - Action Class
// Files: P5Distributables.zip
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
import java.util.ArrayList;

/**
 * This class initializes the action for a "Thing". In this class, we implement methods to perform
 * an action on an item
 * 
 * @author ayujprasad
 *
 */
public class Action {

  // message printed by this action (or null to do nothing)
  private String message;

  // the item we are referring to
  private Thing thing;

  /**
   * Initializes the thing object
   * 
   * @param thing the object to initialize
   */
  Action(Thing thing) {
    // set our thing field to the parameter thing
    this.thing = thing;
  }

  /**
   * Initializes the object along with its message
   * 
   * @param message the message to be printed
   * @param thing the object to initialize
   */
  Action(String message, Thing thing) {
    // initialize the message and thing fields
    this.message = message;
    this.thing = thing;
  }

  /**
   * initialize this new action
   * 
   * @param message the message to be printed
   */
  public Action(String message) {
    // initialize the message
    this.message = message;
  }

  /**
   * when message is not null, message is printed to System.out
   * 
   * @param thingArray the array passed to the method that holds all the items
   */
  public void act(ArrayList<Thing> thingArray) {
    // if the items message is not null, we print the message associated with the object
    if (this.message != null) {
      System.out.println(this.message);
    }

    // if the thing field is not null (it was initialized) - avoid nullPointer
    if (thing != null) {
      // activate our current object
      thing.activate();

      // add our thing to the arrayList
      thingArray.add(thing);

      // set our object to null
      this.thing = null;
    }
  }
}
