// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: Escape Room - ClickableThing Class
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
 * This is the class that implements an object of type ClickableThing (extends VisibleThing). In
 * this class, we set the objects name, position and action. This class also has an update method
 * that returns an action when the mouse is clicked.
 * 
 * @author ayujprasad
 *
 */
public class ClickableThing extends VisibleThing {

  // action returned from update when this object is clicked
  private Action action;

  // tracks whether the mouse was pressed during the last update()
  private boolean mouseWasPressed;

  /**
   * initializes this new object
   * 
   * @param name the name of the object
   * @param x the x-coordinate of the object
   * @param y the y-coordinate if the object
   * @param action the action associated with the object
   */
  public ClickableThing(String name, int x, int y, Action action) {
    // call the super method to set name and position
    super(name, x, y);

    // set the objects action
    this.action = action;
  }

  /**
   * calls VisibleThing update, then returns action only when mouse is first clicked
   * 
   */
  @Override
  public Action update() {
    // call update from the VisibleThing method
    super.update();

    // check if the mouse is pressed, if the mouse was pressed in the previous update call, and if
    // the position of our mouse is over our object/image
    if (getProcessing().mousePressed && (!mouseWasPressed)
        && isOver(getProcessing().mouseX, getProcessing().mouseY)) {

      // if all cases are true, then:

      // set mouseWasPressed to true
      mouseWasPressed = true;

      // and the action associated with our object is returned
      return action;

      // otherwise
    } else {
      // we set mouseWasPressed to the value in getProcessing().mousePressed
      mouseWasPressed = getProcessing().mousePressed;

      // and we return null
      return null;
    }
  }
}
