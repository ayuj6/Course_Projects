// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Book Class
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
/**
 * This class contains all the methods for creating and performing tasks on a new book.
 * 
 * @author ayujprasad
 *
 */
public class Book {

  // class/static fields

  // class variable that represents the identifier of the next book
  private static int nextId = 1;

  // Instance fields

  // unique identifier of this book
  private final int ID;

  // name of the author of this book
  private String author;

  // title of this book
  private String title;

  // card bar code of the borrower of this book.
  // When borrowerCardBarCode == null, the book is available (no one has the book)
  private Integer borrowerCardBarCode;

  /**
   * Construct a new Book object and initialize its instance fields.
   * 
   * @param title is the title of the new book.
   * @param author is the author of the new book.
   */
  public Book(String title, String author) {
    // The title is set to the book title entered.
    this.title = title;

    // The author is set to the book author entered.
    this.author = author;

    // The default borrowerCardBarCode is null.
    this.borrowerCardBarCode = null;

    // The ID of the book is set to nextID.
    this.ID = nextId;

    // NextID is incremented.
    nextId++;
  }

  /**
   * Records the borrower's card bar code of this book if the book is available. If this book is not
   * available, this method does nothing.
   * 
   * @param borrowerCardBarCode is the borrowerCardBarCode to set
   */
  public void borrowBook(Integer borrowerCardBarCode) {
    // if the borrowerCardBarCode is null (book is available)
    if (this.borrowerCardBarCode == null) {

      // then the code is set to the borrowerCardBarCode (parameter)
      this.borrowerCardBarCode = borrowerCardBarCode;
    }
  }

  /**
   * Return the author of this book
   * 
   * @return the author.
   */
  public String getAuthor() {
    // return the author of the book
    return this.author;
  }

  /**
   * Return the the borrower's card bar code of this book if the book has been checked out or null
   * if not.
   * 
   * @return the borrowerCardBarCode of the borrower
   */
  public Integer getBorrowerCardBarCode() {
    // if the book is not available, then return the borrower's card bar code
    if (borrowerCardBarCode != null) {
      return this.borrowerCardBarCode;

      // otherwise, return null.
    } else {
      return null;
    }
  }

  /**
   * Returns the ID of this Book object
   * 
   * @return the ID of this Book object
   */
  public int getID() {
    // return the book's ID
    return this.ID;
  }

  /**
   * Return the title of this book
   * 
   * @return the title
   */
  public String getTitle() {
    // return the book's title
    return this.title;
  }

  /**
   * Checks if this book is available
   * 
   * @return true if no one is borrowing this book, false otherwise
   */
  public boolean isAvailable() {
    // if the book's borrowerCardBarCode is null (book is available), return true
    if (this.borrowerCardBarCode == null) {
      return true;

      // otherwise, return false.
    } else {
      return false;
    }
  }

  /**
   * Sets this book to be available. When the borrowerCardBarCode is set to null, no one is
   * borrowing it
   */
  public void returnBook() {
    // set the book's borrowerCardBarCode to null (make it available)
    this.borrowerCardBarCode = null;
  }
}
