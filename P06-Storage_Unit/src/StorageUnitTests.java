// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P6-StorageUnit - StorageUnitTests
// Files: StorageUnitOrganizerGraphics and srcStorageUnitGraphic
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
 * This class holds test methods to ensure that methods in the other classes of this package work as
 * expected, without any error.
 * 
 * @author ayujprasad
 *
 */
public class StorageUnitTests {

  /**
   * Checks whether the behavior of equals method is correct
   * 
   * @return true is the test passed, false otherwise
   */
  public static boolean testBoxEquals() {

    // boolean variable to hold result of tests
    boolean testPassed = true;

    // create 3 new boxes to be used for the tests
    Box testBox = new Box(10, 2);
    Box testBox2 = new Box(10, 2);
    Box testBox3 = new Box(10, 3);

    // if equals method returns false for comparing box 1 and 2
    if (testBox.equals(testBox2) == false) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // if equals method returns true for comparing box 1 and 2
    if (testBox.equals(testBox3) == true) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // return the value in testPassed
    return testPassed;
  }

  /**
   * Checks whether the behavior of compareTo method is correctly implemented
   * 
   * @return true is the test passed, false otherwise
   */
  public static boolean testBoxCompareTo() {

    // boolean variable to hold result of tests
    boolean testPassed = true;

    // create 3 boxes used for the tests
    Box testBox = new Box(10, 4);
    Box testBox2 = new Box(12, 4);
    Box testBox3 = new Box(10, 18);

    // if the compareTo test returns a value that is not negative
    if (testBox.compareTo(testBox3) >= 0) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // if the compareTo test returns a value that is not 0
    if (testBox.compareTo(testBox2) != 0) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // if the compareTo test returns a value that is not positive
    if (testBox3.compareTo(testBox2) <= 0) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // return the value in testPassed
    return testPassed;
  }

  /**
   * Checks whether remove method defined in your LinkedBoxList works correctly
   * 
   * @return true is the test passed, false otherwise
   */
  public static boolean testLinkedBoxListRemove() {

    // boolean variable to hold result of tests
    boolean testPassed = true;

    // new LinkedBoxList to use for tests
    LinkedBoxList testList = new LinkedBoxList(5);

    // new boxes to use for tests (being added to our list)
    Box testBox = new Box(12, 14);
    testList.add(new Box(10, 2));
    testList.add(new Box(8, 2));
    testList.add(new Box(6, 9));
    testList.add(testBox);

    // if the box removed at index 0 is not testBox (because of weight),
    if (testList.remove(0) != testBox) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // after removing, if the size is not 3,
    if (testList.size() != 3) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // return the value in testPassed
    return testPassed;
  }

  /**
   * Checks to see if the add method defined in LinkedBoxList works correctly
   * 
   * @return true is the test passed, false otherwise
   */
  public static boolean testLinkedBoxListAdd() {

    // boolean variable to hold result of tests
    boolean testPassed = true;

    // new LinkedBoxList to use for tests
    LinkedBoxList testList = new LinkedBoxList(5);

    // new boxes to use for tests (being added to our list)
    Box testBox = new Box(12, 14);
    testList.add(new Box(10, 2));
    testList.add(new Box(8, 2));
    testList.add(new Box(6, 9));
    testList.add(testBox);

    // after adding all boxes, if the size of our list is not 4
    if (testList.size() != 4) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // return the value in testPassed
    return testPassed;
  }

  /**
   * Checks to see if the contains method defined in LinkedBoxList works correctly
   * 
   * @return true is the test passed, false otherwise
   */
  public static boolean testLinkedBoxListContains() {

    // boolean variable to hold result of tests
    boolean testPassed = true;

    // new LinkedBoxList to use for tests
    LinkedBoxList testList = new LinkedBoxList(5);

    // new boxes to use for tests (being added to our list)
    Box testBox = new Box(12, 14);
    testList.add(new Box(10, 2));
    testList.add(testBox);

    // after adding testBox, if it is not contained in our list,
    if (!testList.contains(testBox)) {
      // Print fail message and set testPassed to false
      System.out.println("Test Failed");
      testPassed = false;
    }

    // return the value in testPassed
    return testPassed;
  }

  /**
   * The main method from which the calls to each test method is made
   * 
   * @param Args unused
   */
  public static void main(String[] Args) {

    // 5 boolean variables created
    boolean testResult1;
    boolean testResult2;
    boolean testResult3;
    boolean testResult4;
    boolean testResult5;

    // each boolean variable holds the result of the respective test method calls
    testResult1 = testBoxEquals();
    testResult2 = testBoxCompareTo();
    testResult3 = testLinkedBoxListRemove();
    testResult4 = testLinkedBoxListAdd();
    testResult5 = testLinkedBoxListContains();

    // if all tests pass (all are true)
    if (testResult1 && testResult2 && testResult3 && testResult4 && testResult5) {
      // print pass message
      System.out.println("All Tests Passed!");

      // otherwise, print fail message
    } else {
      System.out.println("1 or more tests Failed!");
    }

  }
}
