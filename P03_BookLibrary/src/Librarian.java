// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Librarian Class
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
 * This class contains methods for a librarian of the library. A Librarian is responsible for
 * managing the books in the library and helping the library subscribers to checkout and return
 * books
 * 
 * @author ayujprasad
 *
 */
public class Librarian {

  // librarian's username
  private String username;

  // librarian password to have access to this application
  private String password;

  /**
   * Creates a new Librarian with a given name and a given password
   * 
   * @param username is the username of this librarian
   * @param password is the password of this librarian to have access to this application
   */
  public Librarian(String username, String password) {
    // set the librarian's username to the username we entered
    this.username = username;

    // set the librarian's password to the password we entered
    this.password = password;
  }

  /**
   * Checks if a given password equals the password of this librarian
   * 
   * @param password the password to check
   * @return true if a given password equals the password of this librarian, false otherwise
   */
  public boolean checkPassword(String password) {
    // if the password is the same as the librarian's password, return true
    if (password.equals(this.password)) {
      return true;

      // otherwise, return false
    } else {
      return false;
    }
  }

  /**
   * Returns the name of this librarian
   * 
   * @return the name of this librarian
   */
  public String getUsername() {
    // returns the librarian's username
    return username;
  }
}
