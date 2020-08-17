// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Escape Room - VisibleThing Class
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
import java.io.File;
import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class is used to implement objects of the type VisibleThing (it extends Thing). In this
 * class, we initialize the object's position and load its image. We can call methods from this
 * class to move and check if it is being covered by a point or another image.
 * 
 * @author ayujprasad
 *
 */
public class VisibleThing extends Thing {

  // the graphical representation of this thing
  private PImage image;

  // the horizontal position (in pixels of this thing's left side)
  private int x;

  // the vertical position (in pixels of this thing's top side)
  private int y;

  /**
   * initialize this new thing
   * 
   * @param name the name to be set for the object
   * @param x the horizontal position of the object
   * @param y the vertical position of the object
   */
  public VisibleThing(String name, int x, int y) {
    // super call to set the name
    super(name);

    // set the x and y position of the object
    this.x = x;
    this.y = y;

    // load the image to the image field
    this.image = super.getProcessing().loadImage("images" + File.separator + name + ".png");
  }

  /**
   * draws image at its position before returning null
   * 
   */
  @Override
  public Action update() {
    // draws the image "image" at position "this.x" and "this.y" and then returns null
    Thing.getProcessing().image(this.image, this.x, this.y);
    return null;
  }

  /**
   * changes x by adding dx to it (and y by dy)
   * 
   * @param dx the value to add to "this.x"
   * @param dy the value to add to "this.y"
   */
  public void move(int dx, int dy) {
    this.x = this.x + dx;
    this.y = this.y + dy;
  }

  /**
   * checks to see if the point x,y is over our image
   * 
   * @param x the x-coordinate of our point
   * @param y the y-coordinate of our point
   * @return true when the point is over our image, false otherwise
   */
  public boolean isOver(int x, int y) {
    // if the point is not within any of the edges of the image, we return false
    if ((y < this.y) || (y > (this.y + image.height))
        || ((x < this.x) || (x > (this.x + image.width)))) {
      return false;
    }

    // otherwise, (it is somewhere over the image) we return true
    return true;

  }

  /**
   * checks to see if our "other" Thing is over our current image
   * 
   * @param other the other thing that we check to see if it is over our image
   * @return true when the other's image overlaps this one's, otherwise, false
   */
  public boolean isOver(VisibleThing other) {
    // checks each of the false conditions for the other's image not being over our current image
    // returns false if each of these conditions fails, true otherwise

    if (other == null) {
      return false;
    }
    if ((other.x > (this.x + this.image.width))) {
      return false;
    }

    if ((other.x + other.image.width) < this.x) {
      return false;
    }

    if (other.y > (this.y + this.image.height)) {
      return false;
    }

    if ((other.y + other.image.height) < this.y) {
      return false;
    }

    return true;


  }
}
