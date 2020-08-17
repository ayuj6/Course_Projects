// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Exceptional Book Library - tests
// Files: None
// Course: CS 300 , Spring 2019
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
import java.text.ParseException;

/**
 * Tests some of the methods in the ExceptionalLibrary class to see if they perform as we expect
 * them to
 * 
 * @author ayujprasad
 *
 */
public class ExceptionalBookLibraryTests {

  // new library that will be referred to for our test methods
  public static ExceptionalLibrary testLibrary = new ExceptionalLibrary("WI", "waterfall", "abc");

  /**
   * Tests to see if ParseCardBarCode method in the ExceptionalLibrary works as expected
   * 
   * @return the result of the tests
   */
  public static boolean testLibraryParseCardBarCode() {

    // boolean value to hold the results of the test
    boolean testPassed = false;

    try {
      // try to parse an invalid barcode, if it matches then testPassed is set to false
      if (testLibrary.parseCardBarCode("2039000001", 2) == 2039000001) {
        testPassed = false;
      }

      // we expect to catch the parseException from the call
    } catch (ParseException e) {
      // if the exception is caught, we set testPassed to true
      testPassed = true;
    }

    // if testPassed is false, print out the error message
    if (testPassed == false) {
      System.out.println("ERROR: testLibraryParseCardBarCode() failed");
    }

    // return the result of the tests
    return testPassed;
  }

  /**
   * Tests to see if ParseRunLibrarianCheckoutBookCommand method in the ExceptionalLibrary works as
   * expected
   * 
   * @return the result of the tests
   */
  public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {

    // boolean value to hold the results of the test
    boolean testPassed = true;

    // book is added to the library
    testLibrary.addBook("ayuj", "ayuj");

    // the book that was added in now added to testBook1
    Book testBook1 = testLibrary.findBook(1);

    try {
      // try to add a subscriber to the library
      testLibrary.addSubscriber("Ayuj", 1234, "abc", "1234567890");

    } catch (InstantiationException e) {
      // print out error message and set testPassed to false
      System.out.println("Error making testSub1");
      testPassed = false;
    }

    // set testSub1 to the subscriber that was added
    Subscriber testSub1 = testLibrary.findSubscriber(2019000001);

    // array of length 3 and given values at indexes is created
    String[] testArray = new String[3];
    testArray[0] = "3";
    testArray[1] = "2019000001";
    testArray[2] = "1";

    try {
      // try to checkOut the book using the testArray
      testLibrary.parseRunLibrarianCheckoutBookCommand(testArray);

      // if the book is not in testSub1's checkedOut array, testPassed is set to false
      if (!testSub1.isBookInBooksCheckedOut(testBook1)) {
        testPassed = false;

        // otherwise testPassed is set to true
      } else {
        testPassed = true;
      }

    } catch (ParseException e) {
      // the error message is printed and testPassed is set to false
      System.out.println("Error occured in parseRunLibrarianCheckoutBookCommand()");
      testPassed = false;
    }

    // if testPassed is false, print out the error message
    if (testPassed == false) {
      System.out.println("ERROR: testLibraryParseRunLibrarianCheckoutBookCommand() failed");
    }

    // return the result of the tests
    return testPassed;
  }

  /**
   * Tests to see if the ParseRunSubscriberReturnBookCommand() method in the ExceptionalLibrary
   * works as expected
   * 
   * @return the result of the tests
   */
  public static boolean testLibraryParseRunSubscriberReturnBookCommand() {

    // boolean value to hold the results of the test
    boolean testPassed = true;

    // add this book to the library
    testLibrary.addBook("ayuj", "ayuj");

    // the book that was added to the library is now stored in testBook2
    Book testBook2 = testLibrary.findBook(2);

    try {
      // try to add the subscriber to the library
      testLibrary.addSubscriber("Ayuj2", 4321, "cba", "0987654321");

    } catch (InstantiationException e) {
      // print out error message and set testPassed to false
      System.out.println("Error making testSub2");
      testPassed = false;
    }

    // put the created subscriber in testSub2
    Subscriber testSub2 = testLibrary.findSubscriber(2019000002);

    // array of length 3 and given values at indexes is created
    String[] testArray = new String[3];
    testArray[0] = "4";
    testArray[1] = "2019000002";
    testArray[2] = "2";

    try {
      // try to checkout the testbook2 with testSub2
      testSub2.checkoutBook(testBook2);

      // try to return the book using our testArray
      testLibrary.parseRunLibrarianReturnBookCommand(testArray);

      // is the book is not in booksReturned array for testSub2, testPassed is set to false
      if (!testSub2.isBookInBooksReturned(testBook2)) {
        testPassed = false;

        // otherwise testPassed is set to true
      } else {
        testPassed = true;
      }

    } catch (ParseException e) {
      // print out the error and set testPassed to false
      System.out.println("Error occured in testLibraryParseRunSubscriberReturnBookCommand()");
      testPassed = false;
    }

    // if testPassed is false, print out the error message
    if (testPassed == false) {
      System.out.println("ERROR: testLibraryParseRunSubscriberReturnBookCommand() failed");
    }

    // return the result of the tests
    return testPassed;
  }

  /**
   * Tests to see if the ParseRunSubscriberUpdatePhoneNumberCommand() method in the
   * ExceptionalLibrary works as expected
   * 
   * @return the result of the tests
   */
  public static boolean testLibraryParseRunSubscriberUpdatePhoneNumberCommand() {

    // boolean value to hold the results of the test
    boolean testPassed = true;

    try {
      // try to add the subscriber to the library
      testLibrary.addSubscriber("Ayuj3", 1234, "acb", "2134567890");

    } catch (InstantiationException e) {
      // print out the error message and set testPassed to false
      System.out.println("Error making testSub3");
      testPassed = false;
    }

    // store the created subscriber in testSub3
    Subscriber testSub3 = testLibrary.findSubscriber(2019000003);

    // array of length 3 and given values at indexes is created
    String[] testArray = new String[2];
    testArray[0] = "8";
    testArray[1] = "2175184660";

    try {
      // try to update the subscriber's phoneNumber using the testArray and testSub3
      testLibrary.parseRunSubscriberUpdatePhoneNumberCommand(testArray, testSub3);

      // if the phoneNumber was not updated to the new phoneNumber, set testPassed to false
      if (testSub3.getPhoneNumber() != "2175184660") {
        testPassed = false;

        // otherwise, testPassed is set to true
      } else {
        testPassed = true;
      }

    } catch (ParseException e) {
      // print out the error message and set testPassed to false
      System.out
          .println("Error occured in testLibraryParseRunSubscriberUpdatePhoneNumberCommand()");
      testPassed = false;
    }

    // if testPassed is false, print out the error message
    if (testPassed == false) {
      System.out.println("ERROR: testLibraryParseRunSubscriberUpdatePhoneNumberCommand() failed");
    }

    // return the result of the tests
    return testPassed;
  }

  /**
   * Tests to see if the ParseRunSubscriberUpdateAddressCommand() method in the ExceptionalLibrary
   * works as expected
   * 
   * @return the result of the tests
   */
  public static boolean testLibraryParseRunSubscriberUpdateAddressCommand() {

    // boolean value to hold the results of the test
    boolean testPassed = true;

    try {
      // try to add the subscriber to the library
      testLibrary.addSubscriber("Ayuj3", 1234, "Michigan", "2134567890");

    } catch (InstantiationException e) {
      // print out the error message and set testPassed to false
      System.out.println("Error making testSub4");
      testPassed = false;
    }

    // store the created subscriber in testSub4
    Subscriber testSub4 = testLibrary.findSubscriber(2019000004);

    // array of length 3 and given values at indexes is created
    String[] testArray = new String[2];
    testArray[0] = "8";
    testArray[1] = "Wisconsin";

    try {
      // try to update the subscriber's address using the testArray and testSub4
      testLibrary.parseRunSubscriberUpdateAddressCommand(testArray, testSub4);

      // if the address was not updated, set testPassed to false
      if (testSub4.getAddress() != "Wisconsin") {
        testPassed = false;

        // otherwise, set testPassed to true
      } else {
        testPassed = true;
      }

    } catch (ParseException e) {
      // print out the error message and set testPassed to false
      System.out.println("Error occured in testLibraryParseRunSubscriberUpdateAddressCommand()");
      testPassed = false;
    }

    // if testPassed is false, print out the error message
    if (testPassed == false) {
      System.out.println("ERROR: testLibraryParseRunSubscriberUpdateAddressCommand() failed");
    }

    // return the result of the tests
    return testPassed;
  }

  public static void main(String[] args) {

    // the result of each test call is stored in their respective boolean varaibles
    boolean result1 = testLibraryParseCardBarCode();
    boolean result2 = testLibraryParseRunLibrarianCheckoutBookCommand();
    boolean result3 = testLibraryParseRunSubscriberReturnBookCommand();
    boolean result4 = testLibraryParseRunSubscriberUpdatePhoneNumberCommand();
    boolean result5 = testLibraryParseRunSubscriberUpdateAddressCommand();

    // formatting to print out result after all in-method print statements
    System.out.println();
    System.out.println();

    // if all tests returned true, print the pass message
    if (result1 && result2 && result3 && result4 && result5) {
      System.out.println("All tests passed");

      // otherwise, print out error message
    } else {
      System.out.println("ERROR: 1 or more tests failing");
    }
  }
}
