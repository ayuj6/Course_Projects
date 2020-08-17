//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P6-StorageUnit - LinkedBoxList
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
 * This is the class that implements the LinkedList. It initializes the node, size of the list, and
 * the capacity of the list. It also holds methods that can be used to alter the LinkedList.
 * 
 * @author ayujprasad
 *
 */
public class LinkedBoxList { 


  // head of this LinkedBoxList (refers to the element stored at index 0 within this list)
  private LinkedBoxNode head;

  // number of boxes already stored in this list
  private int size;

  // the maximum number of box elements that this LinkedBoxList can store
  private int capacity;

  /**
   * Creates an empty LinkedBoxList with a given initial capacity
   * 
   * @param capacity the capacity of the list
   */
  public LinkedBoxList(int capacity) {
    // set the capacity field to the parameter "capacity"
    this.capacity = capacity;
  }

  /**
   * Returns the size of this list
   * 
   * @return the size of the list (how many boxes are currently on the list)
   */
  public int size() {
    // return the size of the LinkedList (the number of boxes currently on the list)
    return this.size;
  }

  /**
   * Return the capacity of this list
   * 
   * @return the maximum number of boxes the list can hold
   */
  public int getCapacity() {
    // return the capacity of the LinkedList (max items that can be held)
    return this.capacity;
  }

  /**
   * Expands the capacity of this LinkedBoxList with the specified number a of additional elements
   * 
   * @param a the number of elements to expand the capacity by
   */
  public void expandCapacity(int a) {
    // change the capacity by adding parameter "a" to the current capacity
    this.capacity = this.capacity + a;
  }

  /**
   * Checks whether this LinkedBoxList is empty
   * 
   * @return true if this LinkedBoxList is empty, false otherwise
   */
  public boolean isEmpty() {
    // if the head is null, then there are no boxes on the list. We return the result of the check
    return (head == null);
  }

  /**
   * Checks whether this LinkedBoxList is full
   * 
   * @return true if this list is full, false otherwise
   */
  public boolean isFull() {
    // if the size of the Linked is the same as the capacity (max items reached)
    if (this.size == this.capacity) {
      // then we return true
      return true;

      // otherwise, we return false
    } else {
      return false;
    }
  }


  /**
   * Adds a new box into this sorted list
   * 
   * @param newBox the box to be added to the list
   * @throws IllegalArgumentException if newBox is null
   * @throws IllegalStateException if this list is full
   */
  public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
    // set a boolean variable that will update if an item was added to the LinkedList
    boolean increment = false;

    // if the box we are trying to add is null, throw a new IllegalArgumentException with error
    if (newBox == null) {
      throw new IllegalArgumentException("Error: Cannot add null box");
    }

    // if the list is full, then throw an IllegalStateException with error
    if (isFull()) {
      throw new IllegalStateException("Error: List is full! Cannot add more boxes");
    }

    // set a new node created using newBox
    LinkedBoxNode tempNode = new LinkedBoxNode(newBox);

    // if the list is empty
    if (isEmpty()) {
      // set our head to be the tempNode (first item)
      head = tempNode;

      // and we set increment to true as we added a box to the list
      increment = true;
    }

    // if the box we are trying to add is heavier than the box at the first index
    if (newBox.compareTo(head.getBox()) == 1) {

      // we are going to add the box at the first position so increment is set to true
      increment = true;

      // we set our node to point to the head
      tempNode.setNext(head);

      // change our head to be the tempNode
      head = tempNode;
    }

    // otherwise, if the box at head is heavier
    else {
      // create a previousNode to hold the node at head
      LinkedBoxNode previousNode = head;

      // create a currentNode to hold the node at the node after head
      LinkedBoxNode currentNode = head.getNext();

      // while the current node is not a null value
      while (currentNode != null) {
        // if newBox is lighter as heavy as the previousNode and heavier than the currentNode
        if (newBox.compareTo(previousNode.getBox()) <= 0
            && newBox.compareTo(currentNode.getBox()) > 0) {
          // then we set tempNode to point to our currentNode
          tempNode.setNext(currentNode);
          // and set the previousNode to point to our tempNode
          previousNode.setNext(tempNode);
          // because we inserted tempNode in the list, we set increment to true
          increment = true;
          // we break out of the while loop
          break;

          // otherwise,
        } else {
          // we set the previousNode to be our currentNode
          previousNode = currentNode;
          // and set our currentNode to be the following node
          currentNode = currentNode.getNext();
        }
      }
      // if we finished the list and we did not add the box (increment false),
      if (!increment) {
        // then our previousNode (our last box) will be set to point to our tempNode (newBox)
        previousNode.setNext(tempNode);
        // and because we added the box, increment is set to true
        increment = true;
      }
    }

    // if increment is true, then we increase the size by 1
    if (increment) {
      this.size++;
    }
  }

  /**
   * Checks if this list contains a box that matches with (equals) a specific box object
   * 
   * @param findBox the box to be searched for in our list
   * @return true if this list contains findBox, false otherwise
   */
  public boolean contains(Box findBox) {
    // while the next node is not null
    while (head.getNext() != null) {
      // if the box at head is the same as "findBox"
      if (head.getBox().equals(findBox)) {
        // then we return true
        return true;

        // otherwise, we set head to be the next node and loop again
      } else {
        head = head.getNext();
      }
    }

    // if after looping, the box was not found, we return false
    return false;
  }

  /**
   * Returns a box stored in this list given its index
   * 
   * @param index the index position on the list that we need to search
   * @return the box at the given index
   * @throws IndexOutOfBoundsException if index is out of the range 0..size-1
   */
  public Box get(int index) throws IndexOutOfBoundsException {
    // counter is set to 0
    int counter = 0;

    // set a current node to be head
    LinkedBoxNode current = head;

    // if the index is outside the list range, we throw and IndexOutOfBoundsException with error
    if (index < 0 && index > this.size - 1) {
      throw new IndexOutOfBoundsException("Went out of list");
    }

    // while the nextNode is not null and the index is in the range of size
    while (current.getNext() != null && index <= size) {
      // if the counter is the same as index, we return the box at the current position
      if (counter == index) {
        return current.getBox();
      }

      // otherwise, we increment the counter and set current to be the following node
      counter++;
      current = current.getNext();
    }

    // return the box at the current node
    return current.getBox();
  }


  /**
   * Removes and returns the box stored at index from this LinkedBoxList
   * 
   * @param index the index at which the box must be removed.
   * @return the reference to the box that was removed
   * @throws IndexOutOfBoundsException if index is out of bounds. index should be in the range of
   *         [0.. size()-1]
   */
  public Box remove(int index) throws IndexOutOfBoundsException {
    // if our index is outside the range, then we throw an IndexOutOfBoundsException with error
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of range");
    }

    // set the currentNode to be the head
    LinkedBoxNode currentNode = head;

    // if the head is null, then return null
    if (head == null) {
      return null;
    }

    // if only 1 box is in the list, then we clear the list, decrement size, and return the box at
    // that head position
    if (size == 1) {
      clear();
      size--;
      return currentNode.getBox();
    }

    // if the index to remove is 0, then we set our head to be the next node, decrement size and
    // return the box at the old head position
    if (index == 0) {
      head = head.getNext();
      size--;
      return currentNode.getBox();
    }

    // the index is set to the last element
    if (index == size - 1) {
      // set the previousNode to be the currentNode
      LinkedBoxNode prevNode = currentNode;

      // set the currentNode to be the next node
      currentNode = currentNode.getNext();

      // loop through all boxes in the list
      for (int i = 1; i < size; i++) {
        // continue to set the previousNode to currentNode and currentNode to the next node
        prevNode = currentNode;
        currentNode = currentNode.getNext();
      }

      // the removeBox is set to the last box in the list
      Box removeBox = prevNode.getBox();

      // we set the previousNode to point to null (it is now the last box)
      prevNode.setNext(null);

      // decrement the size
      size--;

      // and return removeBox
      return removeBox;
    }

    // if we never entered the if statement, we set currentNode to head
    currentNode = head;

    // we loop through the list as long as we're in the list and currentNode is not null
    for (int i = 0; currentNode != null && i < size; ++i) {
      // if i is not at index-1
      if (i != index - 1) {
        // set the currentNode to be the following node
        currentNode = currentNode.getNext();

        // otherwise, we break from the loop
      } else {
        break;
      }
    }

    // we assign setBox with the box after the one we remove
    LinkedBoxNode setBox = currentNode.getNext().getNext();

    // removeBox is set the the box at the next node
    Box removeBox = currentNode.getNext().getBox();

    // currentNode is set to the setBox
    currentNode.setNext(setBox);

    // the size is decremented
    this.size--;

    // return removeBox
    return removeBox;
  }



  /**
   * Removes all the boxes from this list
   */
  public void clear() {
    // set the head to null (everything gets garbage collected)
    head = null;

    // we set the size to 0
    size = 0;
  }

  /**
   * Returns a String representation for this LinkedBoxList
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(); // creates a StringBuilder object
    String newLine = System.getProperty("line.separator");
    result.append("------------------------------------------------" + newLine);
    if (!isEmpty()) {
      LinkedBoxNode runner = head;
      int index = 0;
      // traverse the list and add a String representation for each box
      while (runner != null) {
        result.insert(0, "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs"
            + newLine);
        runner = runner.getNext();
        index++;
      }
      result.insert(0, "Box List Content:" + newLine);
    }
    result.insert(0, "List size: " + size + " box(es)." + newLine);
    result.insert(0, "Box List is empty: " + isEmpty() + newLine);
    result.insert(0, "------------------------------------------------" + newLine);
    return result.toString();
  }
}
