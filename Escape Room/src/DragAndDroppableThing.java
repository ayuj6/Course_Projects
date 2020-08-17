// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Escape Room - DragAndDroppableThing Class
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

/**
 * This class is used to implement an object of type DragAndDroppableThing (extends DraggableThing).
 * This initializes the objects name, position, target object and action. It also has a method that
 * drops an object when it is over its target.
 * 
 * @author ayujprasad
 *
 */
public class DragAndDroppableThing extends DraggableThing {

  // object over which this object can be dropped
  private VisibleThing target;

  // action that results from dropping this object over target
  private Action action;

  /**
   * initialize new object
   * 
   * @param name the name of the object
   * @param x the x-coordinate of the object
   * @param y the y-coordinate of the object
   * @param target the target item
   * @param action the action associated with the object
   */
  public DragAndDroppableThing(String name, int x, int y, VisibleThing target, Action action) {
    // call the super constructor to initialize the objects name and position
    super(name, x, y);

    // set the objects target item and action
    this.target = target;
    this.action = action;
  }

  /**
   * returns action and deactivates objects in response to successful drop When this object is over
   * its target and its target is active: deactivate both this object and the target object, and
   * return action, otherwise return null
   * 
   */
  @Override
  protected Action drop() {
    // if our object is over the target item and the target item is active
    if (this.isOver(target) && (target.isActive() == true)) {
      // deactivate our object and the target item
      target.deactivate();
      this.deactivate();

      // return the action associated with the object
      return action;
    }

    // return null
    return null;
  }
}
