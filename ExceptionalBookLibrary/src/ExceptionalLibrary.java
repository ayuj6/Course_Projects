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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;

/**
 * This class models a simple book library. The main method of this class implements the management
 * system for this library.
 *
 * @author ayujprasad
 */
public class ExceptionalLibrary {
  // instance fields

  // Street address of this library
  private String address;
  // this library's librarian. This library must have only ONE librarian
  private Librarian librarian;
  // list of the books in this library
  private ArrayList<Book> books;
  // list of this library's subscribers
  private ArrayList<Subscriber> subscribers;

  /**
   * Creates a new Library and initializes all its instance fields. Initially both books and
   * subscribers lists are empty.
   * 
   * @param address Address of this Library
   * @param librarianUsername username of the librarian of this book library
   * @param librarianPassword password of the librarian of this book library
   */
  public ExceptionalLibrary(String address, String librarianUsername, String librarianPassword) {
    // sets the library's address to the address in the parameter
    this.address = address;
    // creates the librarian of this library
    this.librarian = new Librarian(librarianUsername, librarianPassword);
    // creates an empty arraylist of books
    books = new ArrayList<Book>();
    // creates an empty arraylist of subscribers
    subscribers = new ArrayList<Subscriber>();
  }

  /**
   * Returns the librarian of this library
   * 
   * @return the librarian
   */
  public Librarian getLibrarian() {
    // return the librarian of the library
    return librarian;
  }

  /**
   * Returns the address of this library
   * 
   * @return the address
   */
  public String getAddress() {
    // return the address of the library
    return address;
  }

  /**
   * Returns a Book given a book identifier if found, and null otherwise. If the book is not found,
   * this method displays the following message: "Error: this book identifier didn't match any of
   * our books identifiers."
   * 
   * @param bookId identifier of the book to find
   * @return reference to the Book if found and null otherwise
   */
  public Book findBook(int bookId) {
    // traverse the list of books and look for a match with bookId
    for (int i = 0; i < books.size(); i++)
      // if a match is found, return the book with bookId
      if (books.get(i).getID() == bookId)
        return books.get(i);
    // otherwise, if book not found: display an error message and return null
    System.out.println("Error: this book identifier didn't match any of our books identifiers.");
    return null;
  }



  /**
   * Returns the list of books having a given title in this library. The comparison used by this
   * method is case insensitive
   * 
   * @param title title of the book(s) to find
   * @return ArrayList of the books having a given title in this library (0 or more books can be
   *         found)
   */
  public ArrayList<Book> findBookByTitle(String title) {
    // create an empty ArrayList to store found books
    ArrayList<Book> foundBooks = new ArrayList<>();
    // traverse the ArrayList books looking for books matching with the provided title
    for (int i = 0; i < books.size(); i++)
      // if the title matches the title at the current book, add that book to foundBooks
      if (books.get(i).getTitle().equalsIgnoreCase(title))
        foundBooks.add(books.get(i));
    // return found books ArrayList. It may be empty
    return foundBooks;
  }

  /**
   * Returns the list of books having a given author. The comparison used by this method is case
   * insensitive
   * 
   * @param author author of the book(s) to find
   * @return ArrayList of the books having a given author (0 or more books can be found)
   */
  public ArrayList<Book> findBookByAuthor(String author) {
    // create an empty ArrayList to store found books
    ArrayList<Book> foundBooks = new ArrayList<>();
    // traverse the ArrayList books looking for books matching with the provided author
    for (int i = 0; i < books.size(); i++)
      // if the author matches the author of the current book, add that book to foundBooks
      if (books.get(i).getAuthor().equalsIgnoreCase(author))
        foundBooks.add(books.get(i));
    // return found books ArrayList. It may be empty
    return foundBooks;
  }

  /**
   * Adds a new book to the library (to the books list). This method displays the following message:
   * "Book with Title " + title + " is successfully added to the library."
   * 
   * @param title title of the new book
   * @param author author of the new book
   */
  public void addBook(String title, String author) {
    // creates a new book with given title and author, and adds it to the books arrayList
    books.add(new Book(title, author));
    // print out the message after the book was added to the list
    System.out.println("Book with Title " + title + " is successfully added to the library.");
  }

  /**
   * Removes a book given its identifier from the library (from books list)
   * 
   * @param bookId identifier of the book to remove
   * @return a reference to the removed book, and null if the book is not found in this library or
   *         if it is not available
   */
  public Book removeBook(int bookId) {
    // find the book
    // findBook displays an error message if book not found
    Book book = findBook(bookId);
    // book found --> remove the book
    if (book != null) {
      // check if the book is available
      if (book.isAvailable())
        // if the book is available, the book is removed from the books array
        books.remove(book);
      // if the book is not available, display error message, book not available and return null
      else {
        System.out.println("You cannot remove a non available book. This book has been "
            + "checked out by the subscriber n° " + book.getBorrowerCardBarCode() + " and is not "
            + "yet returned.");
        return null;
      }
    }
    // return the reference to the removed book
    return book;
  }

  /**
   * Adds a new subscriber to this library (to subscribers list). This method displays the following
   * message: "Library card with bar code " + card bar code + " is successfully issued to the new
   * subscriber " + name + "."
   * 
   * @param name name of the new subscriber
   * @param pin 4-digit personal identifier number of the new subscriber
   * @param address address of the new subscriber
   * @param phoneNumber phone number of the new subscriber
   * @throws InstantiationException if a new subscriber cannot be created
   */
  public void addSubscriber(String name, int pin, String address, String phoneNumber)
      throws InstantiationException {
    // create a new subscriber with given name, pin, address and phoneNumber
    // may throw an InstantiationException
    Subscriber newSubscriber = new Subscriber(name, pin, address, phoneNumber);
    // add new subscriber to the subscribers arrayList
    subscribers.add(newSubscriber);
    // print out the message after the subscriber was added
    System.out.println("Library card with bar code " + newSubscriber.getCARD_BAR_CODE()
        + " is successfully issued to the new subscriber " + name + ".");
  }

  /**
   * Finds a subscriber given its cardBarCode. This method displayed the following message: "Error:
   * this card bar code didn't match any of our records." and returns null if the provided
   * cardBarCode did not match with any of the subscribers' card bar codes
   * 
   * @param cardBarCode of the subscriber to find
   * @return a reference to the subscriber if found, otherwise null
   */
  public Subscriber findSubscriber(int cardBarCode) {
    // traverse the list of subscribers looking for a subscriber having the provided cardBarCode
    for (int i = 0; i < subscribers.size(); i++)
      // if the barcode matches the barcode of the current subscriber, then return this subscriber
      if (subscribers.get(i).getCARD_BAR_CODE() == cardBarCode)
        return subscribers.get(i);
    // otherwise, if no subscribers matched, then print out error message and return null
    System.out.println("Error: this card bar code didn't match any of our records.");
    return null;
  }

  /**
   * Displays a list of books
   * 
   * @param books ArrayList of books
   */
  public static void displayBooks(ArrayList<Book> books) {
    // if the list books is empty display "No books found"
    if (books.isEmpty())
      System.out.println("No books found.");
    // books list not empty
    // Traverse the list of books and display book id, title, author, and availability of each book
    for (int i = 0; i < books.size(); i++) {
      System.out.print("<Book ID>: " + books.get(i).getID() + " ");
      System.out.print("<Title>: " + books.get(i).getTitle() + " ");
      System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
      System.out.println("<Is Available>: " + books.get(i).isAvailable());
    }
  }

  /**
   * Checks if an array of command arguments has the correct length with respect to a provided count
   * 
   * @param commands an array of Strings that stores the arguments extracted from a user command
   *        line
   * @param validArgCount valid count of arguments of the provided commands
   * @throws ParseException if commands length is different from validArgCount with default
   *         errorOffset equals to zero
   */
  public void checkCommandArgumentsCount(String[] commands, int validArgCount)
      throws ParseException {
    // if the length of the array is not the same as the validArgCount
    if (commands.length != validArgCount)
      // then a parseException is thrown
      throw new ParseException(this.getSyntaxErrorMsg(), 0);
  }


  /**
   * Parses the String argument as a phone number
   * 
   * @param s String that represents a phone number
   * @param errorOffset errorOffset for the ParseException if thrown
   * @throws ParseException if the String argument cannot be parsed as a phone number
   */
  public void parsePhoneNumber(String s, int errorOffset) throws ParseException {
    try {
      // parse the String argument s as a phone number (as long number)
      Long.parseLong(s);
      // syntax error
    } catch (NumberFormatException e) {
      // throw a new parseException
      throw new ParseException("Error: The phone number MUST be a NUMBER.\n", errorOffset);
    }
  }

  /**
   * Parses the String argument as a PIN (Personal Identification Number)
   * 
   * @param s A String that represents a PIN code
   * @param errorOffset errorOffset for the ParseException if thrown
   * @return an integer that represents the parsed PIN if it is valid (4-digits number in the range
   *         of [1000, 9999]
   * @throws ParseException if the provided argument for the pin code is invalid
   */
  public int parsePinCode(String s, int errorOffset) throws ParseException {
    // create a pinCode variable
    int pinCode;
    try {
      // try to parse our string s as an integer and store the result to pinCode
      pinCode = Integer.parseInt(s);
      // if the pin-code is in the range 1000-9999 (inclusive), it is returned
      if (pinCode >= 1000 && pinCode <= 9999) {
        return pinCode;
        // if it is not in the range, a new parseException is thrown
      } else {
        throw new ParseException("Error: The Pin Code MUST be a 4-digit NUMBER.\n", errorOffset);
      }
      // syntax error
    } catch (NumberFormatException e) {
      // throw a new parseException
      throw new ParseException("Error: The Pin Code MUST be a 4-digit NUMBER.\n", errorOffset);
    }
  }

  /**
   * Parses a String argument as a subscriber card bar code
   * 
   * @param s a String that represents a card bar code
   * @param errorOffset errorOffset for the ParseException if thrown
   * @return an integer that represents the parsed card bar code if it is valid
   * @throws ParseException if the String argument cannot be parsed as a valid card bar code
   */
  public int parseCardBarCode(String s, int errorOffset) throws ParseException {
    // create a cardBarCode variable
    int cardBarCode;
    try {
      // try to parse string s as an integer and store the result in cardBarCode
      cardBarCode = Integer.parseInt(s);
      // if the cardBarCode passes the checkCardBarCode method, return the cardBarCode
      if (Subscriber.checkCardBarCode(cardBarCode)) {
        return cardBarCode;
        // otherwise, a new parseException is thrown
      } else {
        throw new ParseException("Error: The Card Bar Code MUST be a NUMBER.\n", errorOffset);
      }
      // syntax error
    } catch (NumberFormatException e) {
      // throw a new parseException
      throw new ParseException("Error: The Card Bar Code MUST be a NUMBER.\n", errorOffset);
    }
  }

  /**
   * Parses a String as a book identifier
   * 
   * @param s a String that represents a book identifier
   * @param errorOffset errorOffset for the ParseException if thrown
   * @return an integer that represents the book id if valid
   * @throws ParseException if the provided bookIdArgument cannot be parsed as an integer with the
   *         following error message "Error: Book id MUST be an integer."
   */
  public int parseBookId(String s, int errorOffset) throws ParseException {
    // create a new bookId variable
    int bookId;
    try {
      // try to parse string s as an integer and store the result in bookId
      bookId = Integer.parseInt(s);
      // syntax error
    } catch (NumberFormatException e) {
      // throw a new parseException
      throw new ParseException("Error: Book id MUST be an integer.\n", errorOffset);
    }
    // return bookId
    return bookId;
  }

  /**
   * Parses and runs a command line provided by a librarian to add a new book
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by the librarian to add a new book
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunLibrarianAddBookCommand(String[] commands) throws ParseException {

    // checks if commands stores 3 arguments
    this.checkCommandArgumentsCount(commands, 3);
    // commands[1]: book title, commands[2]: book author
    // create and add new Book
    this.addBook(commands[1], commands[2]);
  }

  /**
   * Parses and runs add subscriber command line provided by a librarian
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by the librarian for adding a new subscriber
   * @throws InstantiationException if a new instance of the Subscriber cannot be created
   * @throws ParseException if the syntax of the provided command line is incorrect
   */
  public void parseRunLibrarianAddSubscriberCommand(String[] commands)
      throws InstantiationException, ParseException {

    // checks if commands stores 5 arguments
    this.checkCommandArgumentsCount(commands, 5);

    // check if our PinCode and PhoneNumber commands are of the correct format and type
    parsePinCode(commands[2], 2);
    parsePhoneNumber(commands[4], 4);

    // commands[1] name; commands[2] pin; commands[3] address; commands[4] phone number
    // create and add new subscriber
    this.addSubscriber(commands[1], Integer.parseInt(commands[2]), commands[3], commands[4]);
  }

  /**
   * Parses and runs a command line provided by a librarian to checkout a book for a subscriber
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by the librarian to checkout a book for a subscriber
   * @throws ParseException if commands include any syntax error or invalid argument (card bar code
   *         or book identifier)
   */
  public void parseRunLibrarianCheckoutBookCommand(String[] commands) throws ParseException {

    // checks if commands stores 3 arguments
    this.checkCommandArgumentsCount(commands, 3);

    // check if our CardBarCode and BookId are of the correct format and type
    parseCardBarCode(commands[1], 1);
    parseBookId(commands[2], 2);

    // find the book with the bookId in commands[2] and store it to the variable book
    Book book = this.findBook(Integer.parseInt(commands[2]));
    // find the subscriber with the CardBarCode commands[1] and store it to the variable subscriber
    Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
    // if the book and subscriber are found and not null
    if (book != null && subscriber != null) {
      // helps the subscriber to check out the book
      subscriber.checkoutBook(book);
    }
  }

  /**
   * Parses and runs a command line provided by the librarian to return a book for a subscriber
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by the librarian to return a book for a subscriber
   * @throws ParseException if commands include any syntax error or invalid argument (card bar code
   *         or book identifier)
   */
  public void parseRunLibrarianReturnBookCommand(String[] commands) throws ParseException {
    // Return a Book for a subscriber [4 <card bar code> <book ID>]

    // checks if commands stores 3 arguments
    this.checkCommandArgumentsCount(commands, 3);

    // check if our CardBarCode and BookId are of the correct format and type
    parseCardBarCode(commands[1], 1);
    parseBookId(commands[2], 2);

    // find the book with the bookId in commands[2] and store it to the variable book
    Book book = this.findBook(Integer.parseInt(commands[2]));
    // find the subscriber with the CardBarCode commands[1] and store it to the variable subscriber
    Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));

    // if the book and subscriber are found and not null
    if (book != null && (subscriber != null)) {
      // helps the subscriber to return a book
      subscriber.returnBook(book);
    }
  }

  /**
   * Parses and runs a command line provided by a librarian to display the personal information of a
   * subscriber
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by the librarian to display the personal info of a subscriber
   * @throws ParseException if commands include any syntax error or invalid argument (card bar code)
   */
  public void parseRunLibrarianDisplayPersonalInfoOfSubscriberCommand(String[] commands)
      throws ParseException {

    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // check if our CardBarCode is of the correct format and type
    parseCardBarCode(commands[1], 1);

    // find the subscriber with the CardBarCode commands[1] and store it to the variable subscriber
    Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
    // if the subscriber is found, display their personal info
    if (subscriber != null) {
      subscriber.displayPersonalInfo();
    }
  }

  /**
   * Parses and runs a command line provided by a librarian to display the books checked out by a
   * subscriber
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by the librarian to display the books checked out by a subscriber * @throws
   *        ParseException if commands include any syntax error or invalid argument (card bar code)
   */
  public void parseRunLibrarianDisplayBooksCheckedOutBySubscriberCommand(String[] commands)
      throws ParseException {
    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // check if our CardBarCode is of the correct format and type
    parseCardBarCode(commands[1], 1);

    // find the subscriber with the CardBarCode commands[1] and store it to the variable subscriber
    Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
    // if the subscriber is found, display the books they have checked out
    if (subscriber != null) {
      subscriber.displayBooksCheckedOut();
    }
  }

  /**
   * Parses and runs a command line provided by a librarian to remove a book
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by the librarian to remove a book
   * @throws ParseException if commands include any syntax error or invalid argument (arguments
   *         count, book id)
   */
  public void parseRunLibrarianRemoveBookCommand(String[] commands) throws ParseException {
    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // check if our BookId is of the correct format and type
    parseBookId(commands[1], 1);

    // remove a book given its valid id
    this.removeBook(Integer.parseInt(commands[1]));
  }


  /**
   * Parses and runs a command line provided by a subscriber to checkout a book
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by a subscriber to checkout a book
   * @param subscriber reference to the subscriber who is going to check out a book
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunSubscriberCheckoutBookCommand(String[] commands, Subscriber subscriber)
      throws ParseException {
    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // look for the book and check it out if it is already checked out by the subscriber
    Book book = this.findBook(Integer.parseInt(commands[1]));
    if (book != null)
      subscriber.checkoutBook(book);
  }

  /**
   * Parses and runs a command line provided by a subscriber to return a book
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by a subscriber to return a book
   * @param subscriber reference to the subscriber who is going to return a book
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunSubscriberReturnBookCommand(String[] commands, Subscriber subscriber)
      throws ParseException {
    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // look for the book and return it if it is already checked out by the subscriber
    Book book = this.findBook(Integer.parseInt(commands[1]));
    if (book != null)
      subscriber.returnBook(book);

  }

  /**
   * Parses and runs a command line provided by a subscriber to update his phone number After
   * updating the phone number of the subscriber, this method displays the following message: "Phone
   * number successfully updated."
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by a subscriber to update his phone number
   * @param subscriber reference to the subscriber who is going to update his phone number
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunSubscriberUpdatePhoneNumberCommand(String[] commands, Subscriber subscriber)
      throws ParseException {
    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // check if our phoneNumber is of the correct format and type
    parsePhoneNumber(commands[1], 1);

    // set the subscriber's phoneNumber to the phoneNumber in commands[1] and print out the message
    subscriber.setPhoneNumber(commands[1]);
    System.out.println("Phone number successfully updated.");
  }

  /**
   * Parses and runs a command line provided by a subscriber to find a list of books by title. This
   * method calls findBookByTitle() method and displays the content of the returned ArrayList of
   * Books if it is not empty. If no books match the search criteria (findBookByTitle() returned an
   * empty list), this method displays the following message: "No books match your search."
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by a subscriber to find a list of books by title
   * @param subscriber reference to the subscriber who is going to search for books
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunSubscriberFindBooksByTitleCommand(String[] commands, Subscriber subscriber)
      throws ParseException {

    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // all the books with the title in commands[1] is stored in bookList
    ArrayList<Book> bookList = this.findBookByTitle(commands[1]);

    // if the bookList is not empty, we display the list of books
    if (!bookList.isEmpty())
      displayBooks(bookList);

    // otherwise, we print out the "no books match" message
    else
      System.out.println("No books match your search.");
  }

  /**
   * Parses and runs a command line provided by a subscriber to a list of books by author This
   * method makes call of findBookByAuthor() method and displays the content of the returned
   * ArrayList of Books if it is not empty. If no books match the search criteria
   * (findBookByAuthor() returned an empty list), this method displays the following message: "No
   * books match your search."
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by a subscriber to a list of books by author
   * @param subscriber reference to the subscriber who is going to search for books
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunSubscriberFindBooksByAuthorCommand(String[] commands, Subscriber subscriber)
      throws ParseException {

    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // all the books with the author in commands[1] is stored in bookList
    ArrayList<Book> bookList = this.findBookByAuthor(commands[1]);

    // if the bookList is not empty, we display the list of books
    if (!bookList.isEmpty())
      displayBooks(bookList);

    // otherwise, we print the "no books match" message
    else
      System.out.println("No books match your search.");
  }

  /**
   * Parses and runs a command line provided by a subscriber to update its home address This method
   * displays "Address successfully updated." after the subscriber's address is updated
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by a subscriber to update his address
   * @param subscriber reference to the subscriber who is going to update his address
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunSubscriberUpdateAddressCommand(String[] commands, Subscriber subscriber)
      throws ParseException {

    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // set the subscriber's address to the address in commands[1] and print out the message
    subscriber.setAddress(commands[1]);
    System.out.println("Address successfully updated.");
  }


  /**
   * Parses and runs a command line provided by a librarian to save the titles and authors of the
   * current list of books
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by a librarian to save the current list of books
   * @throws ParseException if commands include any syntax error or invalid argument
   */
  public void parseRunLibrarianSaveBooksCommand(String[] commands) throws ParseException {

    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // create a PrintWriter variable
    PrintWriter saveFile = null;

    try {
      // try to set the printWriter to write to the file in commands[1]
      saveFile = new PrintWriter(commands[1]);

      // if no such file is found, print the error message
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could NOT load books contents to file " + commands[1]);
    }

    // loop through the books array
    for (int i = 0; i < books.size(); ++i) {
      // for each book in the list, we write to our file in the format "title:author"
      saveFile.println(books.get(i).getTitle() + ":" + books.get(i).getAuthor());
    }
    // the PrintWriter is flushed so it can write to the file
    saveFile.close();



  }

  /**
   * Parses and runs a command line provided by a librarian to load a list of books (title:author)
   * from a file given its filename and new books with these pairs of title/author to the current
   * list of books
   * 
   * @param commands commands an array of Strings that stores the arguments extracted from a command
   *        line provided by a librarian to load a list of books from a file and add it to the
   *        current list of books
   * @throws ParseException if commands include any syntax error or invalid argument
   */

  public void parseRunLibrarianLoadBooksCommand(String[] commands) throws ParseException {

    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // new file variable to read from the file in commands[1] is created
    File saveFile = new File(commands[1]);
    // our scanner is set to null
    Scanner readLine = null;
    try {
      // set the scanner to try to read from our file
      readLine = new Scanner(saveFile);
      // as long as there is another line to read from the file
      while (readLine.hasNextLine()) {
        // we read the next line of the file
        String line = readLine.nextLine();
        // trim the line to get rid of white-spaces.
        // if the file has a length >0,
        if (line.trim().length() > 0) {
          // new array of length 2 is created
          String[] bookDetails = new String[2];
          // the line is separated into the array with ":" as the divider
          bookDetails = line.split(":");

          try {
            // set the title and author of the book as in our array
            String title = bookDetails[0].trim();
            String author = bookDetails[1].trim();
            // add the book with given author and title into our bookToAdd book
            Book bookToAdd = new Book(title, author);
            // add the new book to the arrayList
            books.add(bookToAdd);

            // if the array happened to go out of bounds, print our given error message
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Found incorrectly formatted line in file " + commands[1]
                + ".");

            // if one of our values was null, print out given error message
          } catch (NullPointerException e) {
            System.out.println("Error: Null value found in file " + commands[1] + ".");
          }
        }
      }
      // if no such file was found, print out the given error message
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could NOT load books contents from file " + commands[1]);


    } finally {
      // if the scanner was not null, close the scanner
      if (readLine != null) {
        readLine.close();
      }
    }
  }


  /**
   * Parses and runs a command line provided by a user to login to the application as librarian
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by a user to login as a librarian
   * @param scanner a reference to a Scanner object to read the librarian command lines after a
   *        successful login into the application
   * @throws ParseException thrown if the command line provided by the user to login as a librarian
   *         is invalid or contains a syntax error
   */
  public void parseRunLoginAsLibrarian(String[] commands, Scanner scanner) throws ParseException {
    // checks if commands stores 2 arguments
    this.checkCommandArgumentsCount(commands, 2);

    // check if the password is correct
    if (this.librarian.checkPassword(commands[1])) {
      // read and process librarian commands
      readProcessLibrarianCommand(scanner);
      // otherwise, if the password is wrong, print the error message
    } else {
      System.out.println("Error: Password incorrect!");
    }
  }

  /**
   * Parses and runs a command line provided by a user to login to the application as subscriber
   * 
   * @param commands an array of Strings that stores the arguments extracted from a command line
   *        provided by a user to login as a subscriber
   * @param scanner a reference to a Scanner object to read the subscriber command lines after a
   *        successful login into the application
   * @throws ParseException thrown if the command line provided by the user to login as a subscriber
   *         is invalid or contains a syntax error
   */
  public void parseRunLoginAsSubscriber(String[] commands, Scanner scanner) throws ParseException {
    // checks if commands stores 3 arguments
    this.checkCommandArgumentsCount(commands, 3);

    // check if our CardBarCOde and PinCode are of the correct format and type
    parseCardBarCode(commands[1], 1);
    parsePinCode(commands[2], 2);

    // find subscriber with the given CardBarCode in commands[1]
    Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
    // if the subscriber in not null
    if (subscriber != null) {
      // if the pin of the subscriber matches the pin in commands[2]
      if (subscriber.getPin() == Integer.parseInt(commands[2]))
        // read and process subscriber commands
        readProcessSubscriberCommand(subscriber, scanner);
      // otherwise, print out the error message
      else
        System.out.println("Error: Incorrect PIN.");
    }

  }


  /**
   * Reads and processes the user commands with respect to the menu of this application
   * 
   * @param scanner Scanner object used to read the user command lines
   */
  public void readProcessUserCommand(Scanner scanner) {
    // command prompt
    String promptCommandLine = "ENTER COMMAND: ";
    // display the library management system main menu
    displayMainMenu();
    // print out the command prompt
    System.out.print(promptCommandLine);
    // read user command line
    String command = scanner.nextLine();
    // split user command
    String[] commands = command.trim().split(" ");

    // 3: Exit the application
    while (!(commands[0].equals("3") && commands.length == 1)) {
      try {
        switch (commands[0]) {

        // login as librarian commands[1]: password
          case "1":
            this.parseRunLoginAsLibrarian(commands, scanner);
            break;

          // login as subscriber commands[1]: card bar code commands[2]: 4-digit PIN
          case "2":
            this.parseRunLoginAsSubscriber(commands, scanner);
            break;

          // otherwise, print out error message
          default:
            System.out.println(this.getSyntaxErrorMsg());
        }

        // This catch block catches only ParseException exceptions thrown if the syntax of the user
        // command line from the main menu is incorrect.
        // All exceptions thrown from readProcessLibrarianCommand() or
        // readProcessSubscriberCommand() methods must be handled using try catch blocks within
        // those methods.
      } catch (ParseException e) {
        String error;

        // if the errorOffset is 0, error is set to first statement
        if (e.getErrorOffset() == 0) {
          error = " Arguments count is incorrect.";

          // otherwise, error is set to the second statement
        } else
          error =
              " Argument number " + e.getErrorOffset() + " within your command line is invalid.";

        // print out the error message
        System.out.println(e.getMessage() + error);
      }

      // display the library management system main menu
      displayMainMenu();
      // print prompt
      System.out.print(promptCommandLine);
      // read user command line
      command = scanner.nextLine();
      // split user command line
      commands = command.trim().split(" ");
    }
  }



  /**
   * Reads and processes the librarian commands according to the librarian menu
   * 
   * @param scanner Scanner object used to read the librarian command lines
   */
  public void readProcessLibrarianCommand(Scanner scanner) {
    // command prompt
    String promptCommandLine = "ENTER COMMAND: ";
    // display the library management system main menu
    ExceptionalLibrary.displayLibrarianMenu();
    // print prompt
    System.out.print(promptCommandLine);
    // read user command line
    String command = scanner.nextLine();
    // split user command
    String[] commands = command.trim().split(" ");

    // "9": Exit the application
    while (!(commands[0].toUpperCase().equals("9") && commands.length == 1)) {
      try {

        switch (commands[0].toUpperCase()) {
        // add a new book commands[1] title; commands[2] author
          case "1":
            this.parseRunLibrarianAddBookCommand(commands);
            break;

          // add a new subscriber: commands[1] name; commands[2] pin; commands[3] address;
          // commands[4] phone number
          case "2":
            this.parseRunLibrarianAddSubscriberCommand(commands);
            break;

          // Check out a Book for a subscriber [3 <card bar code> <book ID>]
          case "3":
            this.parseRunLibrarianCheckoutBookCommand(commands);
            break;

          // Return a Book for a subscriber [4 <card bar code> <book ID>]
          case "4":
            this.parseRunLibrarianReturnBookCommand(commands);
            break;

          // Display Personal Info of a Subscriber [5 <card bar code>]
          case "5":
            this.parseRunLibrarianDisplayPersonalInfoOfSubscriberCommand(commands);
            break;

          // [6 <card bar code>] Display Books Checked out by a Subscriber");
          case "6":
            this.parseRunLibrarianDisplayBooksCheckedOutBySubscriberCommand(commands);
            break;

          // [7] Display Books List
          case "7":
            ExceptionalLibrary.displayBooks(this.books);
            break;

          // [8 <book ID>] Remove a Book
          case "8":
            this.parseRunLibrarianRemoveBookCommand(commands);
            break;

          // [L <filename>] Load list of Books from a data file named filename
          case "L":
            this.parseRunLibrarianLoadBooksCommand(commands);
            break;

          // [S <filename>] Save list of Books to a data file named filename
          case "S":
            this.parseRunLibrarianSaveBooksCommand(commands);
            break;

          // otherwise, print the error message
          default:
            System.out.println(this.getSyntaxErrorMsg());

        }
      } catch (ParseException e) {
        String error;

        // if the errorOffset is 0, error is set to first statement
        if (e.getErrorOffset() == 0) {
          error = " Arguments count is incorrect.";

          // otherwise, error is set to the second statement
        } else
          error =
              " Argument number " + e.getErrorOffset() + " within your command line is invalid.";

        // print out the error message
        System.out.println(e.getMessage() + error);
      }

      catch (InstantiationException e) {
        // print out error message
        System.out.println(e.getMessage());
      }

      // display the library management system main menu
      displayLibrarianMenu();
      // print prompt
      System.out.print(promptCommandLine);
      // read user command line
      command = scanner.nextLine();
      // split user command line
      commands = command.trim().split(" ");
    }
  }


  /**
   * Reads and processes the subscriber commands according to the subscriber menu
   * 
   * @param subscriber Current logged in subscriber
   * @param scanner Scanner object used to read the librarian command lines
   */
  public void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
    // command prompt
    String promptCommandLine = "ENTER COMMAND: ";
    // display the library management system main menu
    ExceptionalLibrary.displaySubscriberMenu();
    // print prompt
    System.out.print(promptCommandLine);
    // read user command line
    String command = scanner.nextLine();
    // split user command
    String[] commands = command.trim().split(" ");

    // "9": Exit the Subscriber space
    while (!(commands[0].toUpperCase().equals("9") && commands.length == 1)) {

      try {
        switch (commands[0]) {

        // check out a book commands[1]: book id
          case "1":
            this.parseRunSubscriberCheckoutBookCommand(commands, subscriber);
            break;

          // return a book commands[1]: book id
          case "2":
            this.parseRunSubscriberReturnBookCommand(commands, subscriber);
            break;

          // Search a book by title commands[1]: title
          case "3":
            this.parseRunSubscriberFindBooksByAuthorCommand(commands, subscriber);
            break;

          // Search a book by author commands[1]: author
          case "4":
            this.parseRunSubscriberFindBooksByTitleCommand(commands, subscriber);
            break;

          // print lists of books checked out
          case "5":
            subscriber.displayBooksCheckedOut();
            break;

          // print history of books returned
          case "6":
            subscriber.displayHistoryBooksReturned();
            break;

          // Update address commands[1]: address
          case "7":
            this.parseRunSubscriberUpdateAddressCommand(commands, subscriber);
            break;

          // Update phone number commands[1]: phone number
          case "8":
            this.parseRunSubscriberUpdatePhoneNumberCommand(commands, subscriber);
            break;

        }
      } catch (ParseException e) {
        String error;

        // if the errorOffset is 0, error is set to first statement
        if (e.getErrorOffset() == 0) {
          error = " Arguments count is incorrect.";

          // otherwise, error is set to the second statement
        } else
          error =
              " Argument number " + e.getErrorOffset() + " within your command line is invalid.";

        // print out the error message
        System.out.println(e.getMessage() + error);
      }

      // display the library management system main menu
      displaySubscriberMenu();
      // print prompt
      System.out.print(promptCommandLine);
      // read user command line
      command = scanner.nextLine();
      // split user command line
      commands = command.trim().split(" ");
    }
  }

  /**
   * helper method that returns a general - Syntax error message
   */
  private String getSyntaxErrorMsg() {
    // return the error message
    return "Syntax Error: Please enter a valid command!\n";
  }


  /**
   * Displays the main menu for this book library application
   */
  private static void displayMainMenu() {
    // print out main menu in this format
    System.out.println("\n--------------------------------------------------------");
    System.out.println("     Welcome to our Book Library Management System");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <password>] Login as a librarian");
    System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
    System.out.println("[3] Exit"); // Exit the application
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for a Subscriber
   */
  private static void displaySubscriberMenu() {
    // print our subscriber's menu in this format
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Subscriber's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <book ID>] Check out a book");
    System.out.println("[2 <book ID>] Return a book");
    System.out.println("[3 <title>] Search a Book by title");
    System.out.println("[4 <author>] Search a Book by author");
    System.out.println("[5] Print list of books checked out");
    System.out.println("[6] Print history of returned books");
    System.out.println("[7 <address>] Update address");
    System.out.println("[8 <phone number>] Update phone number");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for the Librarian
   */
  private static void displayLibrarianMenu() {
    // print out librarian's menu in this format
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Librarian's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <title> <author>] Add new Book");
    System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
    System.out.println("[3 <card bar code> <book ID>] Check out a Book for a subscriber");
    System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
    System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
    System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
    System.out.println("[7] Display All Books");
    System.out.println("[8 <book ID>] Remove a Book");
    System.out.println("[L <filename.data>] Load list of Books from filename.data");
    System.out.println("[S <filename.data>] Save list of Books to filename.data");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Display the Application GoodBye and logout message.
   */
  private static void displayGoodByeLogoutMessage() {
    // print out Logout message in this format
    System.out.println("\n--------------------------------------------------------");
    System.out.println("       Thanks for Using our Book Library App!!!!");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Main method that represents the driver for this application
   * 
   * @param args
   */
  public static void main(String[] args) {
    // create a scanner object to read user inputs
    Scanner scanner = new Scanner(System.in);

    // create a new library object
    ExceptionalLibrary madisonLibrary = new ExceptionalLibrary("Madison, WI", "april", "abc");

    // read and process user command lines
    madisonLibrary.readProcessUserCommand(scanner);

    // display good bye message
    displayGoodByeLogoutMessage();

    // close this scanner
    scanner.close();
  }
}
