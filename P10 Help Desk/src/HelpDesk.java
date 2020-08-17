// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P10 - Help Desk - HelpDesk class
// Files: HelpDesk.java, SupportTicket.java, HelpDeskInterface.java, HelpDeskTestSuite.java
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
 * This class is used to create a help desk that will be used to sort the support tickets. It
 * implements HelpDeskInterface and its methods to be used on the help desk. The methods in this
 * class are used to act on the max-heap created from the help desk array.
 * 
 * @author ayujprasad
 */
public class HelpDesk implements HelpDeskInterface {

  // zero-indexed max-heap
  protected SupportTicket[] array;

  // holds size of the max-heap (number of elements)
  protected int size;


  /**
   * This constructor creates an instance of a help desk, creating a max-heap with the specified
   * array capacity.
   * 
   * @param capacity the size to be set for the array field.
   * @throws IllegalArgumentException when called with a non-positive capacity
   */
  public HelpDesk(int capacity) {

    // if the capacity is not positive,
    if (capacity <= 0) {
      // throw a new IllegalArgumentException with the associated error
      throw new IllegalArgumentException("Enter a positive capacity!");
    }

    // set the size to be 0
    this.size = 0;
    // initialize the array with the parameter "capacity"
    array = new SupportTicket[capacity];
  }


  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   * 
   * @param message names the client and describes their need for support.
   * @throws NullPointerException when the String message argument is null.
   * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
   */
  @Override
  public void createNewTicket(String message) {
    // if the message is null,
    if (message == null) {
      // throw a new NullPointerException with the error message
      throw new NullPointerException("Message cannot be null!");
    }

    // if the array is full,
    if (array.length == size) {
      // throw a new IndexOutOfBoundsException with the associated error message
      throw new IndexOutOfBoundsException("Queue is full!");
    }

    // Create an instance of a support ticket with the message
    SupportTicket addTicket = new SupportTicket(message);

    // add this ticket to the end of the array
    array[size] = addTicket;

    // increment size
    size++;

    // call propagateUp with the second to last index (last index is null as we incremented size)
    propagateUp(size - 1);

  }


  /**
   * Returns the message within this HelpDesk that has the highest priority. This method does not
   * change the state of this HelpDesk.
   * 
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String checkNextTicket() {
    // if the array is empty,
    if (size == 0) {
      // throw a new IllegalStateException with the associated error message
      throw new IllegalStateException("Queue is empty!");
    }

    // set the best message's index to be 0
    int bestMessageIndex = 0;

    // loop through all tickets in the array
    for (int i = 1; i < size; i++) {

      // if the value at the current index is greater/larger than the message at bestIndex,
      if (array[i].compareTo(array[bestMessageIndex]) > 0) {
        // then set bestMessageIndex to the current index
        bestMessageIndex = i;
      }
    }

    // get the support ticket with the best message (largest)
    SupportTicket bestString = array[bestMessageIndex];

    // return this ticket's message
    return bestString.toString();
  }


  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * 
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String closeNextTicket() {
    // if the array is empty,
    if (size == 0) {
      // throw a new IllegalStateException with the associated error message
      throw new IllegalStateException("Queue is empty!");
    }

    // set the best message's index to be 0
    int bestMessageIndex = 0;

    // loop through all tickets in the array
    for (int i = 1; i < size; i++) {

      // if the value at the current index is greater/larger than the message at bestIndex,
      if (array[i].compareTo(array[bestMessageIndex]) > 0) {
        // then set bestMessageIndex to the current index
        bestMessageIndex = i;
      }
    }

    // get the support ticket with the best message (largest)
    SupportTicket bestString = array[bestMessageIndex];

    // set the ticket at bestMessageIndex to the last item and decrement size simultaneously
    array[bestMessageIndex] = array[--size];
    // set the item at size to null
    array[size] = null;

    // call propagateDown from the root
    propagateDown(0);

    // return bestString's message
    return bestString.toString();

  }


  /**
   * Given an index into the heap array, this method returns that index's parent index.
   * 
   * @param index the index of a ticket in the heap array
   * @return the index of the parent at this index
   */
  protected static int parentOf(int index) {
    // return the index of the parent to "index"
    return (index - 1) / 2;
  }


  /**
   * Given an index into the heap array, this method returns that index's left child index.
   * 
   * @param index the index of a ticket in the heap array
   * @return the index of the left child at this index
   */
  protected static int leftChildOf(int index) {
    // return the index of the left child to "index"
    return (2 * index) + 1;
  }


  /**
   * Given an index into the heap array, this method returns that index's right child index.
   * 
   * @param index the index of a ticket in the heap array
   * @return the index of the right child at this index
   */
  protected static int rightChildOf(int index) {
    // return the index of the right child to "index"
    return (2 * index) + 2;
  }


  /**
   * Given two indexes into the heap array, this method swaps the SupportTickets at those indexes.
   * 
   * @param indexA the index of the first ticket to swap
   * @param indexB the index of the second ticket to swap the first ticket with
   * @throws IndexOutOfBoundsException when called with an invalid index
   * @throws NullPointerException when called with an index with a null ticket
   */
  protected void swap(int indexA, int indexB) {
    // if the first index is out of the array capacity
    if (indexA < 0 || indexA >= array.length) {
      // throw a new IndexOutOfBoundsException with the given error message
      throw new IndexOutOfBoundsException("Invalid Index for A");
    }

    // if the second index is out of the array capacity
    if (indexB < 0 || indexB >= array.length) {
      // throw a new IndexOutOfBoundsException with the given error message
      throw new IndexOutOfBoundsException("Invalid Index for B");
    }

    // if the ticket at either index is null (not filled),
    if (array[indexA] == null || array[indexB] == null) {
      // throw a new NullPointerException with the given error message
      throw new NullPointerException("Cannot swap with a null ticket");
    }

    // store the ticket at indexA into a temporary variable
    SupportTicket tempTicket = array[indexA];
    // store the ticket at indexB into the position "indexA"
    array[indexA] = array[indexB];
    // store the temporary ticket (from indexA) at indexB
    array[indexB] = tempTicket;
  }


  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and the heap's root.
   * 
   * @param index the index of the heap to start the swap from
   * @throws IndexOutOfBoundsException when called with an invalid heap index
   */
  protected void propagateUp(int index) {
    // if the index is out of the heap array's capacity,
    if (index < 0 || index >= array.length) {
      // throw a new IndexOutOfBoundsException with the given message
      throw new IndexOutOfBoundsException("Invalid Index input");
    }

    // find the parent of the ticket at the current Index
    int parentIndex = parentOf(index);

    // if the parent index is in the size range of the heap array
    if (parentIndex >= 0 && parentIndex < size) {
      // if the ticket at index is greater than the ticket at the parent index,
      if (array[index].compareTo(array[parentIndex]) > 0) {
        // swap the two items,
        swap(index, parentIndex);
        // change the value of index to be the parentIndex (the same ticket swapped)
        index = parentIndex;
        // make a recursive call to make the next check with the ticket
        propagateUp(parentIndex);

        // otherwise, return out of the method call
      } else {
        return;
      }
    }
  }


  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and it's children.
   * 
   * @param index the index of the heap to start the swap from
   * @throws IndexOutOfBoundsException when called with an invalid heap index
   */
  protected void propagateDown(int index) {
    // if the index is out of the heap array's capacity,
    if (index < 0 || index >= array.length) {
      // throw a new IndexOutOfBoundsException with the given message
      throw new IndexOutOfBoundsException("Invalid Index input");
    }

    // set the largestMessageIndex to the current index
    int largestMessageIndex = index;
    // find the right and left child of the current Index
    int leftChildIndex = leftChildOf(index);
    int rightChildIndex = rightChildOf(index);

    // if the left child is in the range and is larger than the value at the current index
    if ((leftChildIndex < this.size)
        && (array[leftChildIndex].compareTo(array[largestMessageIndex]) > 0)) {
      // set the largestMessageIndex to the left Child's index
      largestMessageIndex = leftChildIndex;
    }

    // if the right child is in the range and is larger than the value at the current index
    if ((rightChildIndex < this.size)
        && (array[rightChildIndex].compareTo(array[largestMessageIndex]) > 0)) {
      // set the largestMessageIndex to the right Child's index
      largestMessageIndex = rightChildIndex;
    }

    // if at least one of the children is larger,
    if (largestMessageIndex != index) {
      // swap the ticket at current index and the ticket at largestMessageIndex
      swap(index, largestMessageIndex);
      // make a recursive call to check the next set of children and their values
      propagateDown(largestMessageIndex);
    }
  }
}
