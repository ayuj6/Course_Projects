// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Library Tests Class
// Files: None
// Course: CS300, Spring 2019
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
import java.util.ArrayList;

/**
 * The class holds the test methods for the other classes of this program. The test methods check to
 * see that specific methods in the other classes are functioning as expected.
 * 
 * @author ayujprasad
 *
 */
public class BookLibraryTests {

  /**
   * Tests the Book Constructor Getter methods
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testBookConstructorGetters() {
    // the boolean variable that holds the result of the tests
    boolean testPassed = true;
    // new book created to check getter methods
    Book testBook1 = new Book("CS300 - UW", "Mouna");
    // second book created to check if nextID got incremented
    Book testBook2 = new Book("CS300 - Student", "Ayuj");

    // result1 stores the author received by the getAuthor method
    String result1 = testBook1.getAuthor();
    // result2 stores the title received by the getTitle method
    String result2 = testBook1.getTitle();
    // result3 stores the borrowerCardBarCode from the getter method
    Integer result3 = testBook1.getBorrowerCardBarCode();
    // result4 stores the bookId for book1
    int result4 = testBook1.getID();
    // result5 stores the bookId for book2 (to see if it was incremented)
    int result5 = testBook2.getID();

    // if author is not "Mouna", fail test
    if (!result1.equals("Mouna")) {
      testPassed = false;
    }

    // if title is not "CS300 - UW", fail test
    if (!result2.equals("CS300 - UW")) {
      testPassed = false;
    }

    // if the borrowerCardBarCode of the new book is not null, fail the test
    if (result3 != null) {
      testPassed = false;
    }

    // if the bookId of book1 is not 1, fail the test
    if (result4 != 1) {
      testPassed = false;
    }

    // if the bookId of book2 is not 2 (was not incremented), fail the test
    if (result5 != 2) {
      testPassed = false;
    }

    // if any of the tests failed, print out the fail message
    if (testPassed == false) {
      System.out.println("testBookConstructorGetters() FAILED");
    }

    // return the result of the tests
    return testPassed;
  }

  /**
   * The method tests the returnBook method in the Book class.
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testBookReturnBook() {
    // the boolean variable that holds the result of the tests
    boolean testPassed = true;
    // new book used to be returned in this method
    Book testBook3 = new Book("World's best", "Ronaldo");
    // new subscriber that will be returning the book
    Subscriber testSub1 = new Subscriber("Ayuj", 1234, "wxyz123", "1234567890");

    // the book is borrowed by our subscriber
    testBook3.borrowBook(testSub1.getCARD_BAR_CODE());

    // the book is then returned
    testBook3.returnBook();

    // if the book's cardBarCode is not null (unavailable after returning), fail the test
    if (testBook3.getBorrowerCardBarCode() != null) {
      testPassed = false;
    }

    // if any of the tests failed, print out the fail message
    if (testPassed == false) {
      System.out.println("testBookReturnBook() FAILED");
    }

    // the result of the tests is returned
    return testPassed;
  }

  /**
   * This method tests the checkoutBook method in the Subscriber class
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testSubscriberCheckoutBook() {
    // the boolean variable that holds the result of the tests
    boolean testPassed = true;
    // the arrayList that will hold all the books checked out
    ArrayList<Book> booksCheckedOut = new ArrayList<Book>();

    {
      // new book to be used in the first test case
      Book testBook4 = new Book("CS300", "Ayuj");
      // new subscriber to be used in the first test case
      Subscriber testSub2 = new Subscriber("Gary", 4321, "wxyz132", "1234567890");

      // fill up the arrayList to 10 new books (which is MAX_BOOKS_CHECKED_OUT at the moment)
      for (int i = 0; i < 10; ++i) {
        testSub2.checkoutBook(new Book("Test Book", "Gary Dahl"));
      }

      // try adding a book after max checkouts have been reached (should produce the error message)
      testSub2.checkoutBook(testBook4);

      // if the book was added to the arrayList despite reaching max capacity, the test fails
      if (testSub2.isBookInBooksCheckedOut(testBook4)) {
        testPassed = false;
      }
    }

    {
      // new book for the second test case
      Book testBook5 = new Book("World's Second Best", "Messi");
      // new subscriber for the second test case
      Subscriber testSub3 = new Subscriber("Alex", 1231, "iejww232", "0353032456");
      // set the arrayList to reference a new array (so previous operations don't affect this test)
      booksCheckedOut = new ArrayList<Book>();

      // the book is checked out
      testSub3.checkoutBook(testBook5);
      // check to see if the book is in subscriber's checkedOut array
      if (testSub3.isBookInBooksCheckedOut(testBook5)) {

        // we try to checkout the book again (should produce the error message)
        testSub3.checkoutBook(testBook5);
      }

      // if the array's size is 2 (the same book was checked out twice), the test fails
      if (booksCheckedOut.size() == 2) {
        testPassed = false;
      }
    }

    {
      // new book for the third test case
      Book testBook6 = new Book("CS At UW-Madison", "Ayuj");
      // new subscriber for the third test case
      Subscriber testSub4 = new Subscriber("AJ P", 1231, "wxqw232", "0335427453");
      // another new subscriber for the third test case
      Subscriber testSub5 = new Subscriber("UW OSU FM", 1231, "wxqw232", "0335427453");
      // arrayList is set to reference a new array (so that previous operations don't affect this
      // test)
      booksCheckedOut = new ArrayList<Book>();

      // the book is borrowed by subscriber (assign it to unavailable)
      testBook6.borrowBook(testSub5.getCARD_BAR_CODE());
      // the subscriber tries to checkout the book (should produce the error message)
      testSub4.checkoutBook(testBook6);

      // if the book appears in the array (it still added the book despite being unavailable), fail
      // the test
      if (testSub4.isBookInBooksCheckedOut(testBook6)) {
        testPassed = false;
      }
    }

    // if any of the tests failed, print out the fail message
    if (testPassed == false) {
      System.out.println("testSubscriberCheckoutBook() FAILED");
    }

    // the result of the tests is returned
    return testPassed;
  }

  /**
   * The method tests the findBookByAuthor method in the Library class
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testLibraryFindBookByAuthor() {
    // the boolean variable that holds the result of the tests
    boolean testPassed = true;
    // new library to store the books
    Library footballBanter = new Library("England", "Football Bantz", "Banter");
    // arrayList that will hold books for a given author
    ArrayList<Book> bookByAuthor = new ArrayList<Book>();
    // new books added to library by various authors
    footballBanter.addBook("We will win the league (2019)", "Lovren");
    footballBanter.addBook("Why I'm the greatest", "Lovren");
    footballBanter.addBook("Bottling the Champions League", "Lovren");
    footballBanter.addBook("Hattrick against Chelsea - Simple Stuff", "Aguero");
    footballBanter.addBook("From Relegation to Champions (2019)", "Ole");
    // assign bookByAuthor with the books from the library with author name "Lovren"
    bookByAuthor = footballBanter.findBookByAuthor("Lovren");

    // if there aren't 3 books by Lovren in the array, the test fails
    if (bookByAuthor.size() != 3) {
      testPassed = false;
    }

    // if the test failed, print out the fail message
    if (testPassed == false) {
      System.out.println("testLibraryFindBookByAuthor() FAILED");
    }

    // the result of the test is returned
    return testPassed;
  }

  /**
   * The method tests the checkPassword method in the Librarian class
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testLibrarianCheckPassword() {
    // the boolean variable that holds the result of the tests
    boolean testPassed = true;
    // new Librarian with password 123
    Librarian testLibrarian = new Librarian("Ayuj", "123");
    // 3 test passwords that will be checked
    String testPassword1 = "abc";
    String testPassword2 = "wall";
    String testPassword3 = "123";

    // check our password with abc, if it returns true, then the test fails
    if (testLibrarian.checkPassword(testPassword1)) {
      testPassed = false;
    }

    // check our password with wall, if it returns true, then the test fails
    if (testLibrarian.checkPassword(testPassword2)) {
      testPassed = false;
    }

    // check our password with 123, if it returns false, then the test fails
    if (!testLibrarian.checkPassword(testPassword3)) {
      testPassed = false;
    }

    // if any of the tests failed, print out the fail message
    if (testPassed == false) {
      System.out.println("testLibrarianCheckPassword() FAILED");
    }

    // the result of the tests is returned
    return testPassed;
  }

  /**
   * The method tests the Constructor getters of the Subscriber class
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testSubscriberConstructorGetters() {
    // the boolean variable that holds the result of the tests
    boolean testPassed = true;
    // 3 subscribers that will be used to check the getters
    Subscriber testSub1 = new Subscriber("Ayuj", 1234, "Madison", "1234567890");
    Subscriber testSub2 = new Subscriber("Lovren", 6609, "Croatia", "0983430923");
    Subscriber testSub3 = new Subscriber("Pogba", 1111, "France", "2324124341");

    // if the first subscriber's address doesn't match our declaration, fail the test
    if (!testSub1.getAddress().equals("Madison")) {
      testPassed = false;
    }
    // if the second subscriber's address doesn't match our declaration, fail the test
    if (!testSub2.getAddress().equals("Croatia")) {
      testPassed = false;
    }
    // if the third subscriber's address doesn't match our declaration, fail the test
    if (!testSub3.getAddress().equals("France")) {
      testPassed = false;
    }


    // if the first subscriber's pin doesn't match our declaration, fail the test
    if (testSub1.getPin() != 1234) {
      testPassed = false;
    }
    // if the second subscriber's pin doesn't match our declaration, fail the test
    if (testSub2.getPin() != 6609) {
      testPassed = false;
    }
    // if the third subscriber's pin doesn't match our declaration, fail the test
    if (testSub3.getPin() != 1111) {
      testPassed = false;
    }


    // if the first subscriber's Name doesn't match our declaration, fail the test
    if (!testSub1.getName().equals("Ayuj")) {
      testPassed = false;
    }
    // if the second subscriber's Name doesn't match our declaration, fail the test
    if (!testSub2.getName().equals("Lovren")) {
      testPassed = false;
    }
    // if the third subscriber's Name doesn't match our declaration, fail the test
    if (!testSub3.getName().equals("Pogba")) {
      testPassed = false;
    }


    // if the first subscriber's phone number doesn't match our declaration, fail the test
    if (!testSub1.getPhoneNumber().equals("1234567890")) {
      testPassed = false;
    }
    // if the second subscriber's phone number doesn't match our declaration, fail the test
    if (!testSub2.getPhoneNumber().equals("0983430923")) {
      testPassed = false;
    }
    // if the third subscriber's phone number doesn't match our declaration, fail the test
    if (!testSub3.getPhoneNumber().equals("2324124341")) {
      testPassed = false;
    }


    // if the first subscriber's card bar code doesn't match our declaration, fail the test
    // note: bar codes are starting at 6 because of previous subscriber declarations
    if (!testSub1.getCARD_BAR_CODE().equals(2019000006)) {
      testPassed = false;
    }
    // if the second subscriber's card bar code doesn't match our declaration, fail the test
    if (!testSub2.getCARD_BAR_CODE().equals(2019000007)) {
      testPassed = false;
    }
    // if the third subscriber's card bar code doesn't match our declaration, fail the test
    if (!testSub3.getCARD_BAR_CODE().equals(2019000008)) {
      testPassed = false;
    }

    // if any of the tests failed, print out the fail message
    if (testPassed == false) {
      System.out.println("testSubscriberConstructorGetters() FAILED");
    }

    // the result of the tests is returned
    return testPassed;
  }

  /**
   * Calls all the test methods in this class
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {
    // Each test method's result is stored in a boolean variable
    boolean test1 = testBookConstructorGetters();
    boolean test2 = testBookReturnBook();
    boolean test3 = testSubscriberCheckoutBook();
    boolean test4 = testLibraryFindBookByAuthor();
    boolean test5 = testLibrarianCheckPassword();
    boolean test6 = testSubscriberConstructorGetters();

    // formatting to show result of tests from other printed statements
    System.out.println();
    System.out.println();

    // if all the tests are true, the pass message is printed
    if (test1 && test2 && test3 && test4 && test5 && test6) {
      System.out.println("All tests passed.");

      // otherwise, the fail message is printed
    } else {
      System.out.println("One or more tests are failing");
    }
  }
}
