//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P6-StorageUnit - LinkedBoxNode
// Files:           StorageUnitOrganizerGraphics and srcStorageUnitGraphic
// Course:          CS300 - Spring 2019
//
// Author:          Ayuj Prasad
// Email:           prasad22@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class is used to create and initialize the Node object for the program. It also holds
 * methods that change and retrieve the nodes' next pointer and box at the node.
 * 
 * @author ayujprasad
 *
 */
public class LinkedBoxNode {

  // box that represents the data for this Linked node
  private Box box;

  // reference to the next Linked Box Node
  private LinkedBoxNode next;

  // constructors
  /**
   * creates a new LinkedBoxNode object with a given box and without referring to any next
   * LinkedBoxNode
   * 
   * @param box the box to be initialized
   */
  public LinkedBoxNode(Box box) {
    // set the box field to be the parameter "box"
    this.box = box;

    // set the next pointer of this box to be null
    this.next = null;
  }

  /**
   * creates a new LinkedBoxNode
   * 
   * @param box the box to be initialized
   * @param next the pointer of the box to be set
   */
  public LinkedBoxNode(Box box, LinkedBoxNode next) {
    // set the box field to be the parameter "box"
    this.box = box;

    // set the next field to be the parameter "next"
    this.next = next;
  }


  // getters and setters methods

  /**
   * finds the next node on the list
   * 
   * @return the next node on the list
   */
  public LinkedBoxNode getNext() {
    // return the next pointer of the node
    return this.next;
  }

  /**
   * set the next pointer of the box to the parameter "next"
   * 
   * @param next the pointer to be set for the current box
   */
  public void setNext(LinkedBoxNode next) {
    // set the next pointer to the parameter "next"
    this.next = next;
  }

  /**
   * return the box of the current node
   * 
   * @return the box at the current node
   */
  public Box getBox() {
    // return the box at the node
    return this.box;
  }

  /**
   * set the box at the current node to the parameter "box"
   * 
   * @param box the box to be set at the current node
   */
  public void setBox(Box box) {
    // set the box field to the parameter "box"
    this.box = box;
  }
}
