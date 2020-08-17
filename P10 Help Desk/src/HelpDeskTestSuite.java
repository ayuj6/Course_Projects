// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P10 - Help Desk - HelpDeskTestSuite class
// Files: HelpDeskTestSuite.java, SupportTicket.java, HelpDesk.java, HelpDeskInterface.java
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
 * This class is used to make sure that the methods in the HelpDesk class (and it's inherited
 * methods), work as excepted and throw the correct exceptions. It holds various test methods to
 * check for the different test cases.
 * 
 * @author ayujprasad
 */
public class HelpDeskTestSuite extends HelpDesk {

  /**
   * This constructor is used to declare an instance of a HelpDeskTestSuite
   * 
   * @param capacity the capacity of the heap array to be set
   */
  public HelpDeskTestSuite(int capacity) {
    // call the HelpDesk's constructor with the same capacity
    super(capacity);
  }


  /**
   * Test method for the ParentOf method
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskParentOf() {
    // create a new help Desk and load it with tickets
    HelpDesk testDesk = new HelpDesk(4);
    testDesk.createNewTicket("Hiiii");
    testDesk.createNewTicket("testTicket");
    testDesk.createNewTicket("testTickett");
    testDesk.createNewTicket("WaterFall");

    // if the parent of the first index is not 0, print fail message and return false
    if (parentOf(1) != 0) {
      System.out.println("Test ParentOf Failed");
      return false;
    }
    // if the parent of the second index is not 0, print fail message and return false
    if (parentOf(2) != 0) {
      System.out.println("Test ParentOf Failed");
      return false;
    }
    // if the parent of the third index is not 1, print fail message and return false
    if (parentOf(3) != 1) {
      System.out.println("Test ParentOf Failed");
      return false;
    }

    // otherwise (all pass), return true
    return true;
  }


  /**
   * Test method for the LeftChildOf method
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskLeftChildOf() {
    // create a new help Desk and load it with tickets
    HelpDesk testDesk = new HelpDesk(4);
    testDesk.createNewTicket("Hello");
    testDesk.createNewTicket("Board");
    testDesk.createNewTicket("test");
    testDesk.createNewTicket("Baton");

    // if the left child of the 0th index is not 1, print the fail message and return false
    if (leftChildOf(0) != 1) {
      System.out.println("Test LeftChildOf Failed");
      return false;
    }

    // if the left child of the 1st index is not 3, print the fail message and return false
    if (leftChildOf(1) != 3) {
      System.out.println("Test LeftChildOf Failed");
      return false;
    }

    // otherwise (all pass), return true
    return true;
  }


  /**
   * Test method for the RightChildOf method
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskRightChildOf() {
    // create a new help Desk and load it with tickets
    HelpDesk testDesk = new HelpDesk(5);
    testDesk.createNewTicket("Football");
    testDesk.createNewTicket("Brazil");
    testDesk.createNewTicket("France");
    testDesk.createNewTicket("Phone");
    testDesk.createNewTicket("Ginger");

    // if the right child of the 0th index is not 2, print the fail message and return false
    if (rightChildOf(0) != 2) {
      System.out.println("Test RightChildOf Failed");
      return false;
    }

    // if the right child of the 1st index is not 4, print the fail message and return false
    if (rightChildOf(1) != 4) {
      System.out.println("Test RightChildOf Failed");
      return false;
    }

    // otherwise (all pass), return true
    return true;
  }

  /**
   * Test method for the HelpDesk's constructor method
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskConstructor() {
    // try
    try {
      // create a helpDesk with an invalid capacity (negative)
      HelpDesk testDesk = new HelpDesk(-1);
      // if it doesn't go to the catch block, print the fail message and return false
      System.out.println("Test Constructor Failed");
      return false;

      // catch the exception thrown from the invalid call
    } catch (IllegalArgumentException e) {
      // return true if thrown
      return true;
    }
  }


  /**
   * Test method for an invalid createNewTicket call
   * 
   * @return true if the test passes, false otherwise
   * @throws IndexOutOfBoundsException when invalid index is used (used in test)
   * @throws NullPointerException when using null message (used in test)
   */
  public static boolean testHelpDeskCreateNewTicketInvalid() {
    // create a new test help desk and load a ticket into it (it is now full)
    HelpDesk testDesk = new HelpDesk(1);
    testDesk.createNewTicket("Nine");

    // try
    try {
      // create a new ticket with a null message
      testDesk.createNewTicket(null);
      // if it doesn't go to the catch block, print the fail message and return false
      System.out.println("Test Create null ticket Failed");
      return false;

      // catch the exception thrown from the invalid call
    } catch (NullPointerException e) {

      // try
      try {
        // add a new ticket (capactiy is already at max)
        testDesk.createNewTicket("Hello");

        // if it doesn't go to the next catch block, print the fail message and return false
        System.out.println("Test Create ticket for full queue Failed");
        return false;

        // catch the exception thrown from the invalid call
      } catch (IndexOutOfBoundsException f) {

        // if it reaches here (all catches were caught), return true
        return true;
      }
    }
  }


  /**
   * Test method for an invalid checkNextTicket call
   * 
   * @return true if the test passes, false otherwise
   * @throws IllegalStateException when accessing empty array (used in test)
   */
  public static boolean testHelpDeskCheckNextTicketInvalid() {
    // create a new help desk
    HelpDesk testDesk = new HelpDesk(3);

    // try
    try {
      // check the ticket of an empty heap array
      testDesk.checkNextTicket();
      // if it doesn't go to the next catch block, print the fail message and return false
      System.out.println("Test Check next ticket for empty queue Failed");
      return false;

      // catch the exception thrown from the invalid call
    } catch (IllegalStateException e) {

      // if it reaches here (exception was caught), return true
      return true;
    }
  }


  /**
   * Test method for an invalid closeNextTicket call
   * 
   * @return true if the test passes, false otherwise
   * @throws IllegalStateException when accessing empty array (used in test)
   */
  public static boolean testHelpDeskCloseNextTicketInvalid() {
    // create a new help desk
    HelpDesk testDesk = new HelpDesk(3);

    // try
    try {
      // close the next ticket of an empty heap array
      testDesk.closeNextTicket();
      // if it doesn't go to the next catch block, print the fail message and return false
      System.out.println("Test Close next ticket for empty queue Failed");
      return false;

      // catch the exception thrown from the invalid call
    } catch (IllegalStateException e) {

      // if it reaches here (exception was caught), return true
      return true;
    }
  }


  /**
   * Test method for an invalid swap call
   * 
   * @return true if the test passes, false otherwise
   * @throws IndexOutOfBoundsException when invalid index is used (used in test)
   * @throws NullPointerException when using null heap index (used in test)
   */
  public static boolean testHelpDeskSwapInvalid() {
    // create a new help desk and load it with tickets
    HelpDesk testDesk = new HelpDesk(5);
    testDesk.createNewTicket("Football");
    testDesk.createNewTicket("Brazil");
    testDesk.createNewTicket("France");

    // try
    try {
      // call swap with an invalid indexA
      testDesk.swap(-1, 2);
      // if it doesn't go to the next catch block, print the fail message and return false
      System.out.println("Test Swap with invalid index A Failed");
      return false;

      // catch the exception thrown from the invalid call
    } catch (IndexOutOfBoundsException e) {

      // try
      try {
        // call swap with an invalid indexB
        testDesk.swap(1, 1000);
        // if it doesn't go to the next catch block, print the fail message and return false
        System.out.println("Test Swap with invalid index B Failed");
        return false;

        // catch the exception thrown from the invalid call
      } catch (IndexOutOfBoundsException f) {

        // try
        try {
          // call swap with a null ticket
          testDesk.swap(2, 3);
          // if it doesn't go to the next catch block, print the fail message and return false
          System.out.println("Test Swap with null ticket index Failed");
          return false;

          // catch the exception thrown from the invalid call
        } catch (NullPointerException g) {

          // if it reaches here, return true
          return true;
        }
      }
    }
  }


  /**
   * Test method for an invalid propagateUp call
   * 
   * @return true if the test passes, false otherwise
   * @throws IndexOutOfBoundsException when invalid index is used (used in test)
   */
  public static boolean testHelpDeskPropagateUpInvalid() {
    // create a new help desk and load it's heap array with tickets
    HelpDesk testDesk = new HelpDesk(3);
    SupportTicket testTicket = new SupportTicket("B");
    SupportTicket testTicket2 = new SupportTicket("I");
    SupportTicket testTicket3 = new SupportTicket("G");
    testDesk.array[0] = testTicket;
    testDesk.array[1] = testTicket2;
    testDesk.array[2] = testTicket3;

    // try
    try {
      // call propagateUp with an invalid index
      testDesk.propagateUp(-1);
      // if it doesn't go to the next catch block, print the fail message and return false
      System.out.println("Test propagateUp with invalid index Failed");
      return false;

      // catch the exception thrown from the invalid call
    } catch (IndexOutOfBoundsException e) {

      // if it reaches here, return true
      return true;
    }
  }


  /**
   * Test method for an invalid propagateDown call
   * 
   * @return true if the test passes, false otherwise
   * @throws IndexOutOfBoundsException when invalid index is used (used in test)
   */
  public static boolean testHelpDeskPropagateDownInvalid() {
    // create a testDesk and add tickets to it
    HelpDesk testDesk = new HelpDesk(3);
    SupportTicket testTicket = new SupportTicket("Allez");
    SupportTicket testTicket2 = new SupportTicket("Ingraham");
    SupportTicket testTicket3 = new SupportTicket("Ole");
    testDesk.array[0] = testTicket;
    testDesk.array[1] = testTicket2;
    testDesk.array[2] = testTicket3;

    // try
    try {
      // call propagateDown with an invalid index
      testDesk.propagateUp(3);
      // if it doesn't go to the next catch block, print the fail message and return false
      System.out.println("Test propagateDown with invalid index Failed");
      return false;

      // catch the exception thrown from the invalid call
    } catch (IndexOutOfBoundsException e) {

      // return true
      return true;
    }
  }

  /**
   * Test method for a valid propagateDown call
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskPropagateDownValid() {
    // create a new test desk and load tickets onto the heap array
    HelpDesk testDesk = new HelpDesk(3);
    SupportTicket testTicket = new SupportTicket("Gerrard");
    SupportTicket testTicket2 = new SupportTicket("Abraham");
    SupportTicket testTicket3 = new SupportTicket("Xavi");
    testDesk.array[0] = testTicket3;
    testDesk.array[1] = testTicket2;
    testDesk.array[2] = testTicket;

    // call propagateDown
    testDesk.propagateDown(0);

    // after the call, if the 0th index is not testTicket3,
    if (!testDesk.array[0].equals(testTicket3)) {
      // print the fail message and return false
      System.out.println("Test propagateDown Failed");
      return false;
    }

    // call propagateDown again
    testDesk.propagateDown(0);

    // if the 0th index is not testTicket3
    if (!testDesk.array[0].equals(testTicket3)) {
      // print fail message and return false
      System.out.println("Test propagateDown Failed");
      return false;
    }

    // return true
    return true;
  }


  /**
   * Test method for a valid propagateUp call
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskPropagateUpValid() {
    // create a new desk and a testTicket
    HelpDesk testDesk = new HelpDesk(3);
    SupportTicket testTicket2 = new SupportTicket("International");


    // Create new ticket makes use of propagateUp
    testDesk.createNewTicket("Hi");
    testDesk.createNewTicket("International");
    testDesk.createNewTicket("Grade");

    // if the ticket at index 0 doesn't have the message "International"
    if (!testDesk.array[0].toString().equals(testTicket2.toString())) {
      // print the fail message and return false
      System.out.println("Test propagateUp2 Failed");
      return false;
    }

    // return true
    return true;
  }


  /**
   * Test method for a valid swap call
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskSwapValid() {
    // create a help desk and add tickets to the heap array
    HelpDesk testDesk = new HelpDesk(3);
    SupportTicket testTicket = new SupportTicket("Football");
    SupportTicket testTicket2 = new SupportTicket("International");
    SupportTicket testTicket3 = new SupportTicket("Germany");
    testDesk.array[0] = testTicket;
    testDesk.array[1] = testTicket2;
    testDesk.array[2] = testTicket3;

    // call swap with the 0th and 2nd index
    testDesk.swap(0, 2);

    // if the ticket was not swapped, print fail message and return false
    if (!testDesk.array[0].equals(testTicket3)) {
      System.out.println("Test Swap Failed");
      return false;
    }
    // if the ticket was not swapped, print fail message and return false
    if (!testDesk.array[2].equals(testTicket)) {
      System.out.println("Test Swap Failed");
      return false;
    }

    // return true
    return true;
  }


  /**
   * Test method for a valid closeNextTicket call
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskCloseNextTicketValid() {
    // create a new testDesk and add tickets to the heap array
    HelpDesk testDesk = new HelpDesk(5);
    testDesk.createNewTicket("Football");
    testDesk.createNewTicket("Brazil");
    testDesk.createNewTicket("Armenia");

    // if the first close does not return "Football", print fail message and return false
    if (!testDesk.closeNextTicket().equals("Football")) {
      System.out.println("Test CloseNextTicket Failed");
      return false;
    }
    // if the first close does not return "Armenia", print fail message and return false
    if (!testDesk.closeNextTicket().equals("Armenia")) {
      System.out.println("Test CloseNextTicket Failed");
      return false;
    }
    // if the first close does not return "Brazil", print fail message and return false
    if (!testDesk.closeNextTicket().equals("Brazil")) {
      System.out.println("Test CloseNextTicket Failed");
      return false;
    }
    // return true
    return true;
  }


  /**
   * Test method for a valid checkNextTicket call
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskCheckNextTicketValid() {
    // create a new help desk and add tickets to the heap array
    HelpDesk testDesk = new HelpDesk(3);
    testDesk.createNewTicket("Just");
    testDesk.createNewTicket("Annoying");
    testDesk.createNewTicket("People");

    // if the check for the next ticket is does not return "Annoying",
    if (!testDesk.checkNextTicket().equals("Annoying")) {
      // print the fail message and return false
      System.out.println("Test CheckNextTicket Failed");
      return false;
    }
    // return true
    return true;
  }


  /**
   * Test method for a valid createNewTicket call
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskCreateNewTicketValid() {
    // create a new help desk and add a ticket to it
    HelpDesk testDesk = new HelpDesk(3);
    testDesk.createNewTicket("Blah");

    // if the size is not 1, print fail message and return false
    if (testDesk.size != 1) {
      System.out.println("Test CreateNewTicket Failed");
      return false;
    }

    // add another ticket to the heap array
    testDesk.createNewTicket("Gary");

    // if the size is not 2, print fail message and return false
    if (testDesk.size != 2) {
      System.out.println("Test CreateNewTicket Failed");
      return false;
    }
    // return true
    return true;
  }


  /**
   * The main method that calls all the test methods and checks their output
   * 
   * @param args unused
   */
  public static void main(String[] args) {

    // load the result of each test into its own variable
    boolean test1 = testHelpDeskParentOf();
    boolean test2 = testHelpDeskLeftChildOf();
    boolean test3 = testHelpDeskRightChildOf();
    boolean test4 = testHelpDeskConstructor();
    boolean test5 = testHelpDeskCreateNewTicketInvalid();
    boolean test6 = testHelpDeskCheckNextTicketInvalid();
    boolean test7 = testHelpDeskCloseNextTicketInvalid();
    boolean test8 = testHelpDeskSwapInvalid();
    boolean test9 = testHelpDeskPropagateUpInvalid();
    boolean test10 = testHelpDeskPropagateDownInvalid();
    boolean test11 = testHelpDeskPropagateDownValid();
    boolean test12 = testHelpDeskPropagateUpValid();
    boolean test13 = testHelpDeskSwapValid();
    boolean test14 = testHelpDeskCloseNextTicketValid();
    boolean test15 = testHelpDeskCheckNextTicketValid();
    boolean test16 = testHelpDeskCreateNewTicketValid();

    // if all tests are true, Print the pass message
    if (test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14 && test15 && test16) {
      System.out.println("All tests passed!");
    }
    // If a single test fails, its respective fail message prints
  }
}
