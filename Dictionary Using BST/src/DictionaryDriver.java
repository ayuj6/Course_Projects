// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 - DICTIONARY USING BST - DictionaryDriver class
// Files: DictionaryDriver.java
// Course: CS 300, Spring 2019
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
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is a driver application to make use of your Dictionary implemented using BST. It also
 * contains a main method to act as a driver of this applications and a printMenu method to print
 * the command menu.
 * 
 * @author ayujprasad
 */
public class DictionaryDriver {

  // an ArrayList that will hold the words stored in the dictionary
  private static ArrayList<String> words;

  // a dictionary that will be used to perform the dictionary methods on
  private static DictionaryBST mainDictionary;

  /**
   * This method is used to print out the command menu in the format required
   */
  private static void printMenu() {
    String userCommands =
        "\n=========================== Dictionary ============================\n"
            + "Enter one of the following options:\n"
            + "[A <word> <meaning>] to add a new word and its definition in the dictionary\n"
            + "[L <word>] to search a word in the dictionary and display its definition\n"
            + "[G] to print all the words in the dictionary in sorted order\n"
            + "[S] to get the count of all words in the dictionary\n"
            + "[H] to get the height of this dictionary implemented as a binary search tree\n"
            + "[Q] to quit the program\n"
            + "======================================================================\n";
    System.out.println(userCommands);
  }

  /**
   * This is the main method of the class that acts as the driver of this application
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {

    // initialize the dictionary
    mainDictionary = new DictionaryBST();

    // the scanner used to read the user's commands
    Scanner userInput = new Scanner(System.in);

    // words is initialized to a new ArrayList
    words = new ArrayList<String>();

    // create a variable to hold the user's input
    String userCommand;

    // loop:
    do {
      // print out the command menu
      printMenu();

      // prompt user for command
      System.out.println("Please enter your command: ");

      // read the user's command and store in userCommand
      userCommand = userInput.nextLine();

      // remove all leading and trailing whitespaces
      userCommand = userCommand.trim();

      // if the user's command is empty, print warning and prompt for next input
      if (userCommand.length() == 0) {
        System.out.println("WARNING: Unrecognized command.");
        continue;
      }

      // if the first character of the command is 'a'
      if (userCommand.toLowerCase().charAt(0) == 'a') {
        // split the user's command: separated by " "
        String[] addCommand = userCommand.split(" ");

        // if the length of the user's command is less than 3,
        if (addCommand.length < 3) {
          // print out the error and continue for next input
          System.out.println("WARNING: Syntax Error for [A <word> <meaning>] command line.");
          continue;

          // otherwise,
        } else {
          // set the user's word to the 1st index of the split
          String word = addCommand[1];

          // set meaning to an empty string
          String meaning = "";

          // each subsequent word that the user enters, we will consider it as the word's meaning
          for (int i = 2; i < addCommand.length; ++i) {
            meaning += addCommand[i] + " ";
          }

          // trim the meaning for the last trailing whitespace
          meaning = meaning.trim();

          // try to add the word into the dictionary
          try {
            mainDictionary.addWord(word, meaning);

            // prompt user for next command
            continue;

            // catch an IllegalArgumentException and print the message associated with the error.
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
      }

      // if the first character is 'l'
      if (userCommand.toLowerCase().charAt(0) == 'l') {
        // split the command with " "
        String[] lookupCommand = userCommand.split(" ");

        // if the length is not size 2
        if (lookupCommand.length != 2) {
          // print the error message and continue to ask for next command input
          System.out.println("WARNING: Syntax Error for [L <word>] command line.");
          continue;

          // otherwise,
        } else {
          // try to lookup with the user's input
          try {
            System.out.println(lookupCommand[1] + ": "
                + mainDictionary.lookup(lookupCommand[1]).toString());

            // prompt user for next command
            continue;

            // catch an IllegalArgumentException and print the message associated with the error.
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
        }
      }

      // if the first character is 'g'
      else if (userCommand.toLowerCase().charAt(0) == 'g') {
        // if the user's input is not size 1,
        if (userCommand.length() != 1) {
          // print the error message and continue to ask for next command input
          System.out.println("WARNING: Syntax Error for [G] command line.");
          continue;

          // otherwise,
        } else {
          // get the words and store it in the ArrayList
          words = mainDictionary.getAllWords();
          // and print out the ArrayList of words
          System.out.println(words);

          // prompt user for next command
          continue;
        }
      }

      // if the first character is 's'
      else if (userCommand.toLowerCase().charAt(0) == 's') {
        // if the user's input is not size 1,
        if (userCommand.length() != 1) {
          // print the error message and continue to ask for next command input
          System.out.println("WARNING: Syntax Error for [S] command line.");
          continue;

          // otherwise,
        } else {
          // print out the size of the dictionary
          System.out.println(mainDictionary.size());

          // prompt user for next command
          continue;
        }
      }

      // if the first character is 'h'
      else if (userCommand.toLowerCase().charAt(0) == 'h') {
        // if the user's input is not size 1,
        if (userCommand.length() != 1) {
          // print the error message and continue to ask for next command input
          System.out.println("WARNING: Syntax Error for [H] command line.");
          continue;

          // otherwise,
        } else {
          // print the height of the Dictionary BST
          System.out.println(mainDictionary.height());

          // prompt user for next command
          continue;
        }
      }

      // if the first character is 'q'
      else if (userCommand.toLowerCase().charAt(0) == 'q') {
        // if the user's input is not size of 1
        if (userCommand.length() != 1) {
          // print the error message and continue to ask for next command input
          System.out.println("WARNING: Syntax Error for [Q] command line.");
          continue;

          // otherwise, break from the method
        } else {
          break;
        }
      }

      // lastly, if the input does not have any of these commands, print out error and prompt again
      else {
        System.out.println("WARNING: Unrecognized command.");
        continue;
      }


      // repeat this while the user's input is not 'q'
    } while (userCommand.trim().toLowerCase() != "q");

    // print out the end line of the log
    System.out.println("============================== END ===================================");

    // close the scanner
    userInput.close();
  }
}
