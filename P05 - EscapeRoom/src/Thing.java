// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Escape Room - Thing Class
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
import processing.core.PApplet;

/**
 * This class initializes the Thing object and processing field for the EscapeRoom class. It also
 * contains methods to activate, deactivate and update a Thing. It can also set and get from the
 * processing field.
 * 
 * @author ayujprasad
 *
 */
public class Thing {

  // the constant name identifying this object
  private final String NAME;

  // active means thing is visible and can be interacted with
  private boolean isActive;

  // the processing to bet set for the "Thing"
  private static PApplet processing;


  /**
   * Initializes the name and sets isActive to true.
   * 
   * @param name the name of the thing to initialize
   */
  public Thing(String name) {
    // set the name and isActive values for the object
    this.NAME = name;
    this.isActive = true;
  }

  /**
   * Checks to see if the name is equal to NAME or not
   * 
   * @param name
   * @return true when the contents of name equal NAME, false otherwise.
   */
  public boolean hasName(String name) {
    // if the NAME is the same as name, return true
    if (this.NAME.equals(name)) {
      return true;

      // otherwise, return false
    } else {
      return false;
    }
  }

  /**
   * Checks to see if isActive is true or not
   * 
   * @return true when isActive is true, false otherwise.
   */
  public boolean isActive() {
    // if isActive is true, return true
    if (isActive) {
      return true;

      // otherwise, return false
    } else {
      return false;
    }
  }

  /**
   * changes isActive to true.
   * 
   */
  public void activate() {
    isActive = true;
  }

  /**
   * changes isActive to false
   * 
   */
  public void deactivate() {
    isActive = false;
  }

  /**
   * this method returns null subclass types will override this update() method to do more
   * interesting things
   * 
   * @return null
   */
  public Action update() {
    return null;
  }

  /**
   * mutator method to set the processing field
   * 
   * @param processing the processing to be set for the processing field
   */
  public static void setProcessing(PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * accessor method to retrieve this static field
   * 
   * @return the processing field
   */
  protected static PApplet getProcessing() {
    return processing;
  }
}
