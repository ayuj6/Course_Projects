// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Library Class
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
import java.util.Scanner;

/**
 * This method contains methods for the library that holds all the information.The main method of
 * this class implements the management system for this library.
 * 
 * @author ayujprasad
 *
 */
public class Library {

  // Street address of this library
  private String address;

  // this library's librarian. This library must have only ONE librarian
  private Librarian librarian;

  // list of the books in this library
  private ArrayList<Book> books;

  // list of this library's subscribers
  private ArrayList<Subscriber> subscribers;

  /**
   * Creates a new Library and initializes all its instance fields.Initially both books and
   * subscribers lists are empty.
   * 
   * @param address is the address of this Library
   * @param librarianUsername is the username of the librarian of this book library
   * @param librarianLogin is the password of the librarian of this book library
   */
  public Library(String address, String librarianUsername, String librarianLogin) {
    // set the librarianUsername and login to the librarian of the library
    this.librarian = new Librarian(librarianUsername, librarianLogin);

    // set the address to the address entered
    this.address = address;

    // initialize the arrayList of the books and subscribers to new arrayLists
    books = new ArrayList<Book>();
    subscribers = new ArrayList<Subscriber>();
  }

  /**
   * Adds a new book to the library (to the books list). This method displays the following message:
   * "Book with Title " + title + " is successfully added to the library."
   * 
   * @param title is the title of the new book
   * @param author is the author of the new book
   */
  public void addBook(String title, String author) {
    // add the new book with the title and author to the books array
    books.add(new Book(title, author));

    // print out the message
    System.out.println("Book with Title " + title + " is successfully added to the library.");
  }

  /**
   * Adds a new subscriber to this library (to subscribers list). This method displays the following
   * message: "Library card with bar code " + card bar code +
   * " is successfully issued to the new subscriber " + name + "."
   * 
   * @param name is the name of the new subscriber
   * @param pin is the 4-digit personal identifier number of the new subscriber
   * @param address is the address of the new subscriber
   * @param phoneNumber is the phone number of the new subscriber
   */
  public void addSubscriber(String name, int pin, String address, String phoneNumber) {
    // add the new subscriber with the given name, pin, address, and number
    subscribers.add(new Subscriber(name, pin, address, phoneNumber));

    // print out the message
    System.out.println("Library card with bar code "
        + subscribers.get(subscribers.size() - 1).getCARD_BAR_CODE()
        + " is successfully issued to the new subscriber " + name + ".");
  }


  /**
   * Returns a Book given a book identifier if found, and null otherwise. If the book is not found,
   * this method displays the following message:
   * "Error: this book identifier didn't match any of our books identifiers."
   * 
   * @param bookId is the identifier of the book to find
   * @return the reference to the Book if found and null otherwise
   */
  public Book findBook(int bookId) {
    // loop through each book in the books array
    for (int i = 0; i < books.size(); ++i) {

      // if the ID of the book at index i matches our "bookId", we return the book at the current
      // index
      if (books.get(i).getID() == bookId) {
        return books.get(i);
      }
    }
    // if no book with the bookId was found, print out the error message and return null
    System.out.println("Error: this book identifier didn't match any of our books identifiers.");
    return null;
  }

  /**
   * Returns the list of books having a given author. The comparison used by this method is case
   * insensitive
   * 
   * @param author is the author of the book(s) to find
   * @return the ArrayList of the books having a given author (0 or more books can be found)
   */
  public ArrayList<Book> findBookByAuthor(String author) {
    // initialize a new arrayList of type Book
    ArrayList<Book> booksByAuthor = new ArrayList<Book>();

    // loop through the books in the books array
    for (int i = 0; i < books.size(); ++i) {

      // if the author (case-insensitive) matches the book at the current index
      if (books.get(i).getAuthor().equalsIgnoreCase(author)) {

        // add this book to the new arrayList
        booksByAuthor.add(books.get(i));

        // otherwise we continue to the next book in the arrayList
      } else {
        continue;
      }
    }
    // return the new arrayList with all with the given author (empty array if no books were there)
    return booksByAuthor;
  }

  /**
   * Returns the list of books having a given title in this library. The comparison used by this
   * method is case insensitive
   * 
   * @param title is the title of the book(s) to find
   * @return ArrayList of the books having a given title in this library (0 or more books can be
   *         found)
   */
  public ArrayList<Book> findBookByTitle(String title) {
    // initialize a new arrayList of type Book
    ArrayList<Book> booksByTitle = new ArrayList<Book>();

    // loop through each book in the books arrayList
    for (int i = 0; i < books.size(); ++i) {

      // if the title (case-insensitive) matches the title of the book at index i
      if (books.get(i).getTitle().equalsIgnoreCase(title)) {

        // add this book to the new arrayList
        booksByTitle.add(books.get(i));

        // otherwise, continue to the next book in the arrayList
      } else {
        continue;
      }
    }

    // return all the books with the given title in our array (empty if no books were there)
    return booksByTitle;
  }

  /**
   * Finds a subscriber given its cardBarCode. This method displayed the following message:
   * "Error: this card bar code didn't match any of our records." and returns null if the provided
   * cardBarCode did not match with any of the subscribers' card bar codes
   * 
   * @param cardBarCode of the subscriber to find
   * @return a reference to the subscriber if found, otherwise null
   */
  public Subscriber findSubscriber(int cardBarCode) {
    // loop through the subscribers in the subscribers array
    for (int i = 0; i < subscribers.size(); ++i) {

      // if the subscriber at the current index has the same barCode as the given cardBarCode
      if (subscribers.get(i).getCARD_BAR_CODE().equals(cardBarCode)) {

        // return this subscriber
        return subscribers.get(i);
      }
    }

    // if no subscriber with the cardBarCode was found, print out the error message and return null
    System.out.println("Error: this card bar code didn't match any of our records.");
    return null;
  }

  /**
   * Returns the address of this library
   * 
   * @return the address
   */
  public String getAddress() {
    // return the library's address
    return address;
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
   * Removes a book given its identifier from the library (from books list)
   * 
   * @param bookId is the identifier of the book to remove
   * @return a reference to the removed book, and null if the book is not found in this library or
   *         if it is not available
   */
  public Book removeBook(int bookId) {
    // loop through all the books in the books array
    for (int i = 0; i < books.size(); ++i) {

      // if the current book's ID is the same as bookId
      if (books.get(i).getID() == bookId) {

        // record the current book in a variable
        Book removedBook = books.get(i);

        // remove this book
        books.remove(i);

        // return the reference to the removed book
        return removedBook;
      }
    }
    // if no book was found or if the book was not available, return null
    return null;
  }

  /**
   * Displays a list of books
   * 
   * @param books ArrayList of books
   */
  public static void displayBooks(ArrayList<Book> books) {
    // Traverse the list of books and display book id, title, author, and availability of each book
    for (int i = 0; i < books.size(); i++) {
      System.out.print("<Book ID>: " + books.get(i).getID() + " ");
      System.out.print("<Title>: " + books.get(i).getTitle() + " ");
      System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
      System.out.println("<Is Available>: " + books.get(i).isAvailable());
    }
  }

  /**
   * Reads and processes the user commands with respect to the menu of this application
   * 
   * @param scanner Scanner object used to read the user command lines
   */
  public void readProcessUserCommand(Scanner scanner) {
    // the prompt to ask for a command
    final String promptCommandLine = "ENTER COMMAND: ";

    // display the library management system main menu
    displayMainMenu();

    // print out the prompt
    System.out.println(promptCommandLine);

    // read user command line
    String command = scanner.nextLine();

    // split user command
    String[] commands = command.trim().split(" ");

    // '3': Exit the application
    while (commands[0].trim().charAt(0) != '3') {
      switch (commands[0].trim().charAt(0)) {

      // login as librarian commands[1]: password
        case '1':
          // if checkPassword with commands[1] returns true
          if (this.librarian.checkPassword(commands[1])) {

            // read and process librarian commands
            readProcessLibrarianCommand(scanner);

            // otherwise, print out the error message
          } else {
            System.out.println("Error: Password incorrect!");
          }
          // break
          break;

        // login as subscriber commands[1]: card bar code
        case '2':
          // the subscriber found will be stored in a variable
          Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));

          // if the subscriber is not null
          if (subscriber != null) {

            // if the pin is the same as commands[2], read and process subscriber commands
            if (subscriber.getPin() == Integer.parseInt(commands[2]))
              readProcessSubscriberCommand(subscriber, scanner);

            // otherwise, print out the error message
            else
              System.out.println("Error: Incorrect PIN.");
          }
          // break
          break;
      }

      // display the library management system main menu
      displayMainMenu();

      // prompt for the command
      System.out.println(promptCommandLine);

      // read user command line
      command = scanner.nextLine();

      // split user command line
      commands = command.trim().split(" ");
    }
  }

  /**
   * Displays the main menu for this book library application
   */
  private static void displayMainMenu() {
    // print out the display menu for main
    System.out.println("\n--------------------------------------------------------");
    System.out.println("     Welcome to our Book Library Management System");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <password>] Login as a librarian");
    System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
    System.out.println("[3] Exit");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for a Subscriber
   */
  private static void displaySubscriberMenu() {
    // print out the display menu for subscribers
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
    // print out the display menu for librarians
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Librarian's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <title> <author>] Add new Book");
    System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
    System.out.println("[3 <card bar code> <book ID>] Check out a Book");
    System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
    System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
    System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
    System.out.println("[7] Display All Books");
    System.out.println("[8 <book ID>] Remove a Book");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Display the Application GoodBye and logout message.
   */
  private static void displayGoodByeLogoutMessage() {
    // print out the logout message
    System.out.println("\n--------------------------------------------------------");
    System.out.println("       Thanks for Using our Book Library App!!!!");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Reads and processes the librarian commands according to the librarian menu
   * 
   * @param scanner Scanner object used to read the librarian command lines
   */
  private void readProcessLibrarianCommand(Scanner scanner) {
    // the prompt to ask for a command is stored
    final String promptCommandLine = "ENTER COMMAND: ";

    // librarian's menu is displayed
    Library.displayLibrarianMenu();

    // the prompt is printed
    System.out.println(promptCommandLine);

    // read user command line
    String command = scanner.nextLine();

    // split user command
    String[] commands = command.trim().split(" ");

    // '9' : logout of librarian's menu
    while (commands[0].trim().charAt(0) != '9') {
      switch (commands[0].trim().charAt(0)) {

      // if commands[0] is 1
        case '1':
          // add a book with title commands[1] and author commands[2]
          addBook(commands[1], commands[2]);
          break;

        // if commands[0] is 2, add a subscriber with name commands[1], pin commands[2], address
        // commands[3] and phone number commands[4]
        case '2':
          addSubscriber(commands[1], Integer.parseInt(commands[2]), commands[3], commands[4]);
          break;

        // if commands[0] is 3
        case '3':
          // subscriber (sub1) found from findSubscriber with carBarCode commands[1]
          Subscriber sub1 = findSubscriber(Integer.parseInt(commands[1]));

          // book (book1) found from findBook with bookId commands[2]
          Book book1 = findBook(Integer.parseInt(commands[2]));

          // make sub1 checkout book1
          sub1.checkoutBook(book1);
          break;

        // if commands[0] is 4
        case '4':
          // subscriber (sub2) found from findSubscriber with carBarCode commands[1]
          Subscriber sub2 = findSubscriber(Integer.parseInt(commands[1]));

          // book (book2) found from findBook with bookId commands[2]
          Book book2 = findBook(Integer.parseInt(commands[2]));

          // make sub2 return book2
          sub2.returnBook(book2);
          break;

        // if commands[0] is 5
        case '5':
          // subscriber (sub3) found from findSubscriber with carBarCode commands[1]
          Subscriber sub3 = findSubscriber(Integer.parseInt(commands[1]));

          // display sub3's Personal Info
          sub3.displayPersonalInfo();
          break;

        // if commands[0] is 6
        case '6':
          // subscriber (sub4) found from findSubscriber with carBarCode commands[1]
          Subscriber sub4 = findSubscriber(Integer.parseInt(commands[1]));

          // display all of sub4's checked out books
          sub4.displayBooksCheckedOut();
          break;

        // if commands[0] is 7
        case '7':
          // display all the books
          displayBooks(books);
          break;

        // if commands[0] is 8
        case '8':
          // remove the book with bookId commands[1] from the library
          removeBook(Integer.parseInt(commands[1]));
          break;
      }

      // display the library management system main menu
      displayLibrarianMenu();

      // print out the command prompt
      System.out.println(promptCommandLine);

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
  private void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
    // the prompt to ask for a command is stored
    final String promptCommandLine = "ENTER COMMAND: ";

    // display the library management system main menu
    displaySubscriberMenu();

    // the command prompt is printed
    System.out.println(promptCommandLine);

    // read user command line
    String command = scanner.nextLine();

    // split user command
    String[] commands = command.trim().split(" ");

    // '9': logout of subscriber's menu
    while (commands[0].trim().charAt(0) != '9') {
      switch (commands[0].trim().charAt(0)) {

      // if commands[0] is 1
        case '1':
          // book (book1) found from findBook with bookId commands[1]
          Book book1 = findBook(Integer.parseInt(commands[1]));

          // let the current subscriber checkout book1
          subscriber.checkoutBook(book1);
          break;

        // if commands[0] is 2
        case '2':
          // book (book2) found from findBook with bookId commands[1]
          Book book2 = findBook(Integer.parseInt(commands[1]));

          // let the current subscriber return book2
          subscriber.returnBook(book2);
          break;

        // if commands[0] is 3
        case '3':
          // all books from library with title commands[1] is stored in the arrayList
          ArrayList<Book> book3 = findBookByTitle(commands[1]);

          // the arrayList is printed out
          System.out.println(book3);
          break;

        // if commands[0] is 4
        case '4':
          // all books from library with author commands[1] is stored in the arrayList
          ArrayList<Book> book4 = findBookByAuthor(commands[1]);

          // the arrayList is printed out
          System.out.println(book4);
          break;

        // if commands[0] is 5
        case '5':
          // the list of books the subscriber has checked out is displayed
          subscriber.displayBooksCheckedOut();
          break;

        // if commands[0] is 6
        case '6':
          // the list of books that the subscriber has returned is displayed
          subscriber.displayHistoryBooksReturned();
          break;

        // if commands[0] is 7
        case '7':
          // the subscriber's address is changed to address commands[1]
          subscriber.setAddress(commands[1]);
          break;

        // if commands[0] is 8
        case '8':
          // the subscriber's phone number is set to phone number commands[1]
          subscriber.setPhoneNumber(commands[1]);
          break;
      }

      // display the library management system main menu
      displaySubscriberMenu();

      // print out the command prompt
      System.out.println(promptCommandLine);

      // read user command line
      command = scanner.nextLine();

      // split user command line
      commands = command.trim().split(" ");
    }
  }

  /**
   * Main method that represents the driver for this application
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {
    // create a scanner object to read user inputs
    Scanner scanner = new Scanner(System.in);

    // create a new library object
    Library madisonLibrary = new Library("Madison, WI", "april", "abc");

    // read and process user command lines
    madisonLibrary.readProcessUserCommand(scanner);

    // display good bye message
    displayGoodByeLogoutMessage();

    // close this scanner
    scanner.close();
  }

}
