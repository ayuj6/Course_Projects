// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Subscriber Class
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
 * This class contains methods for a subscriber of the library. These methods have the ability to
 * change and receive the subscribers information as well as perform actions related to the
 * subscriber.
 * 
 * @author ayujprasad
 *
 */
public class Subscriber {


  // static fields

  // maximum number of books to be checked out one subscriber
  private final static int MAX_BOOKS_CHECKED_OUT = 10;

  // class variable that represents the card bar code of the next subscriber to be created
  private static int nextCardBarCode = 2019000001;


  // Instance fields

  // 4-digits Personal Identification Number to verify the identity of this subscriber
  private int pin;

  // card bar code of this subscriber
  private final Integer CARD_BAR_CODE;

  // name of this subscriber
  private String name;

  // address of this subscriber
  private String address;

  // phone number of this subscriber
  private String phoneNumber;

  // list of books checked out by this subscriber and not yet returned. A subscriber can have at
  // most 10 checked out books
  private ArrayList<Book> booksCheckedOut;

  // list of the books returned by this subscriber
  private ArrayList<Book> booksReturned;


  /**
   * Creates a new subscriber with given name, address, and phone number, and initializes its other
   * instance fields
   * 
   * @param name is the name of this subscriber
   * @param pin is the 4-digits personal information number of this subscriber
   * @param address is the address of this subscriber
   * @param phoneNumber is the phone number of this subscriber
   */
  public Subscriber(String name, int pin, String address, String phoneNumber) {
    // the arrays for checking out and returning books are initialized to new arrays
    booksCheckedOut = new ArrayList<Book>();
    booksReturned = new ArrayList<Book>();

    // the name of the subscriber is set to the name entered
    this.name = name;

    // the pin of the subscriber is set to the pin entered
    this.pin = pin;

    // the address of the subscriber is set to the address entered
    this.address = address;

    // the phone number of the subscriber is set to the phone number entered
    this.phoneNumber = phoneNumber;

    // the CARD_BAR_CODE of the subscriber is set to nextCardBarCode
    this.CARD_BAR_CODE = nextCardBarCode;

    // nextCardBarCode is incremented
    nextCardBarCode++;
  }

  /**
   * Checks out an available book. The checkout operation fails if the maximum number of checked out
   * books by this subscriber is already reached
   * 
   * @param book
   */
  public void checkoutBook(Book book) {
    // if the booksCheckedOut array has more than or equal to MAX_BOOKS_CHECKED_OUT (10) books
    if (booksCheckedOut.size() >= MAX_BOOKS_CHECKED_OUT) {
      // then the fail message is printed
      System.out.println("Checkout Failed: You cannot check out more than " + MAX_BOOKS_CHECKED_OUT
          + " books.");
      // and we leave the method
      return;
    }

    // if the book we want to checkout is already is the booksCheckedOut array
    if (isBookInBooksCheckedOut(book)) {
      // the following message is printed
      System.out.println("You have already checked out " + book.getTitle() + " book.");
      // and we leave the method
      return;

    }

    // if the book is not available,
    if (!book.isAvailable()) {
      // the following message is printed
      System.out.println("Sorry, " + book.getTitle() + " is not available.");
      // and we leave the method
      return;
    }

    // if none of the tests fail then we add our book to the booksCheckedOut array
    booksCheckedOut.add(book);

    // and we change the borrowerCardBarCode of the book
    book.borrowBook(this.CARD_BAR_CODE);
  }


  /**
   * Checks if this subscriber booksCheckedOut list contains a given book
   * 
   * @param book is the book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksCheckedOut contains book, false otherwise
   */
  public boolean isBookInBooksCheckedOut(Book book) {
    // return the result of whether booksCheckedOut contains our book
    return booksCheckedOut.contains(book);
  }

  /**
   * Checks if this subscriber booksReturned list contains a given book
   * 
   * @param book is the book to check if it is within this subscriber booksReturned list
   * @return true if booksReturned contains book, false otherwise
   */
  public boolean isBookInBooksReturned(Book book) {
    // return the result of whether booksReturned contains our book
    return booksReturned.contains(book);
  }

  /**
   * Displays the list of the books checked out and not yet returned
   */
  public void displayBooksCheckedOut() {
    // if the array is empty, print the message
    if (booksCheckedOut.isEmpty())
      System.out.println("No books checked out by this subscriber");

    // otherwise, traverse the list of books checked out by this subscriber and display its content
    else
      for (int i = 0; i < booksCheckedOut.size(); i++) {
        System.out.print("Book ID: " + booksCheckedOut.get(i).getID() + " ");
        System.out.print("Title: " + booksCheckedOut.get(i).getTitle() + " ");
        System.out.println("Author: " + booksCheckedOut.get(i).getAuthor());
      }
  }

  /**
   * Displays the history of the returned books by this subscriber
   */
  public void displayHistoryBooksReturned() {
    // if the array is empty, print the message
    if (booksReturned.isEmpty())
      System.out.println("No books returned by this subscriber");

    // otherwise, traverse the list of borrowed books already returned by this subscriber and
    // display its content
    else
      for (int i = 0; i < booksReturned.size(); i++) {
        System.out.print("Book ID: " + booksReturned.get(i).getID() + " ");
        System.out.print("Title: " + booksReturned.get(i).getTitle() + " ");
        System.out.println("Author: " + booksReturned.get(i).getAuthor());
      }
  }

  /**
   * Displays this subscriber's personal information
   */
  public void displayPersonalInfo() {
    // print out the Subscribers information
    System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
    System.out.println("  Name: " + name);
    System.out.println("  Address: " + address);
    System.out.println("  Phone number: " + phoneNumber);
  }

  /**
   * Returns this subscriber's address
   * 
   * @return the address
   */
  public String getAddress() {
    // return the subscirber's address
    return address;
  }

  /**
   * Returns this subscriber's card bar code
   * 
   * @return the CARD_BAR_CODE
   */
  public Integer getCARD_BAR_CODE() {
    // return the subscriber's CARD_BAR_CODE
    return CARD_BAR_CODE;
  }

  /**
   * Returns this subscriber's name
   * 
   * @return the name
   */
  public String getName() {
    // return the subscriber's name
    return name;
  }

  /**
   * Returns this subscriber's phone number
   * 
   * @return the phone number
   */
  public String getPhoneNumber() {
    // return the subscriber's phone number
    return phoneNumber;
  }

  /**
   * Returns this subscriber PIN (4-digits Personal Identification Number)
   * 
   * @return the pin
   */
  public int getPin() {
    // return the subscriber's pin
    return pin;
  }

  /**
   * Returns a library book
   * 
   * @param book is the reference to the book to return by this subscriber
   */
  public void returnBook(Book book) {
    // if the booksCheckedOut array contains the book we want to return
    if (booksCheckedOut.contains(book)) {

      // set the borrower's card bar code to null
      book.returnBook();

      // remove the book from the CheckedOut array
      booksCheckedOut.remove(book);

      // add the book to the booksReturned array
      booksReturned.add(book);
    }
  }

  /**
   * Updates this subscriber's address
   * 
   * @param address is the address to set
   */
  public void setAddress(String address) {
    // set the subscriber's address to the address entered
    this.address = address;
  }

  /**
   * Updates this subscriber's phone number
   * 
   * @param phoneNumber is the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    // set the subscriber's phone number to the phone number entered
    this.phoneNumber = phoneNumber;
  }
}
