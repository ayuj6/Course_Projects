// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Escape Room - DraggableThing Class
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
 * This class implements a new object of type DraggableThing (extends VisibleThing). In this class,
 * we set the objects name and position. This class also has an update method that moves according
 * to the mouse's drag.
 * 
 * @author ayujprasad
 *
 */
public class DraggableThing extends VisibleThing {

  // similar to use in ClickableThing
  private boolean mouseWasPressed;

  // true when this object is being dragged by the user
  private boolean isDragging;

  // horizontal position of mouse during last update
  private int oldMouseX;

  // vertical position of mouse during last update
  private int oldMouseY;

  /**
   * initialize new thing
   * 
   * @param name the name of the item
   * @param x the x-coordinate of the item
   * @param y the y-coordinate of the item
   */
  public DraggableThing(String name, int x, int y) {
    // call the super constructor to set the name and position
    super(name, x, y);
  }

  /**
   * calls VisibleThing update(), then moves according to mouse drag each time isDragging changes
   * from true to false, the drop() method below will be called once and any action objects returned
   * from that method should then be returned from update()
   * 
   */
  @Override
  public Action update() {
    // call the update method from the parent class
    super.update();

    // if the object is being dragged
    if ((isDragging)) {
      // move the object according to the mouse's position
      this.move(getProcessing().mouseX - oldMouseX, getProcessing().mouseY - oldMouseY);
    }

    // if mousePressed is true, the mouse was pressed in the previous update call, and the mouse
    // position is over our image/object
    if ((getProcessing().mousePressed && !mouseWasPressed && (isOver(getProcessing().mouseX,
        getProcessing().mouseY)))) {

      // set isDragging to true
      isDragging = true;

      // update the oldMouse coordinates
      oldMouseX = getProcessing().mouseX;
      oldMouseY = getProcessing().mouseY;

      // otherwise,
    } else {
      // set mousePressed to the value in getProcessing().mousePressed
      mouseWasPressed = getProcessing().mousePressed;

      // set isDragging to false
      isDragging = false;

      // return the action received from the drop method call
      return this.drop();

    }

    // return null
    return null;
  }

  /**
   * this method returns null. subclass types will override this drop() method to do more
   * interesting things
   * 
   * @return null
   */
  protected Action drop() {
    // return null
    return null;
  }
}
