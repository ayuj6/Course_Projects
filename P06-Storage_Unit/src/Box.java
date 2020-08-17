//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P6-StorageUnit - Box
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
import java.util.Random;

/**
 * This class creates and initializes a box. The box will have a random color and weight (within a
 * range if needed). It also contains methods to get the box's color and weight. Some methods in
 * this class can compare two object to compare class, weight and color or just the weight itself.
 * 
 * @author ayujprasad
 *
 */
public class Box implements Comparable<Box> {

  // generator of random numbers
  private static Random randGen = new Random();

  // color of this box
  private int color;

  // weight of this box in lbs between 1 inclusive and 31 exclusive
  private int weight;


  /**
   * Creates a new Box and initializes its instance fields color and weight to random values
   */
  public Box() {
    // set the color to be a random integer
    this.color = randGen.nextInt();

    // set the weight to be a random integer between 1 and 30 inclusive
    this.weight = randGen.nextInt(30) + 1;
  }

  /**
   * Creates a new Box and initializes its instance fields color and weight to the specified values
   * 
   * @param color the color of the box to be set
   * @param weight the weight of the box to be set
   * @throws IllegalArgumentException if the provided weight value is out of the range of [1..30]
   */
  public Box(int color, int weight) throws IllegalArgumentException {
    // set the color field of the box to the parameter "color"
    this.color = color;

    // if the weight is in the valid range [1,30]
    if (weight >= 1 && weight < 31) {
      // set the weight field of the box to the parameter "weight"
      this.weight = weight;

      // otherwise, throw an IllegalArgumentException with the given error
    } else {
      throw new IllegalArgumentException("Error: Weight is out of range");
    }
  }

  /**
   * equals method defined in Object class. Checks to see if the two objects have the same class,
   * color and weight
   * 
   * @param other is the other object we are comparing with
   * @return true if both objects match, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    // if the other object is of the same class as our current object (box)
    if (other.getClass() == this.getClass()) {
      // if the two boxes are of the same color (cast because we have proven other is a box)
      if (((Box) other).getColor() == this.getColor()) {
        // if the two boxes are of the same weight (cast because we have proven other is a box)
        if (((Box) other).getWeight() == this.getWeight()) {
          // if all conditions are true, then we return true
          return true;
        }
      }
    }
    // if even one condition fails, then we return false
    return false;
  }

  /**
   * compareTo method defined in Comparable<Box> interface.Compares the weights of our box with the
   * parameter "otherBox"
   * 
   * @param otherBox is the other box we are comparing weights with
   * @return -1 if the otherBox is heavier, 1 if our box is lighter, 0 if they both are equal
   */
  @Override
  public int compareTo(Box otherBox) {
    // if otherBox has a greater weight than our box,
    if (otherBox.getWeight() > this.getWeight()) {
      // then we return 1
      return -1;

      // otherwise, if our box has a greater box than otherBox,
    } else if (otherBox.getWeight() < this.getWeight()) {
      // then we return 1
      return 1;

      // otherwise, (they are of the same weight), we return 0
    } else {
      return 0;
    }
  }

  /**
   * Getter for the instance field color of this box
   * 
   * @return the color of the box
   */
  public int getColor() {
    // return the box's color
    return this.color;
  }

  /**
   * Getter for the instance field weight of this box
   * 
   * @return the weight of the box
   */
  public int getWeight() {
    // return the box's weight
    return this.weight;
  }
}
