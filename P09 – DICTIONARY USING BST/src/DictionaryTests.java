import java.util.ArrayList;

// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 - DICTIONARY USING BST - DictionaryTests class
// Files: DictionaryTests.java
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

/**
 * This class holds the test methods to check if our implemented methods in the other classes work
 * as they are supposed to without error.
 * 
 * @author ayujprasad
 */
public class DictionaryTests {

  // test Dictionary to be used to perform methods on
  private static DictionaryBST testDictionary;

  // test ArrayList to store words returned from getAllWords()
  private static ArrayList<String> testWords;

  /**
   * This method is used to check if the addWord method in DictionaryBST class works as expected.
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testDictionaryBSTAddWord() {
    // initialize the testDictionary
    testDictionary = new DictionaryBST();

    // test word (and its meaning) to be added to the dictionary
    String testWord = "Word";
    String testMeaning = "Meaning";

    // add word to testDictionary
    testDictionary.addWord(testWord, testMeaning);

    // if the dictionary size is not 1 (word not added), print error message and return false
    if (testDictionary.size() != 1) {
      System.out.println("FAILED: addWord Failed!");
      return false;
    }

    // test word 2 (and its meaning) to be added to the dictionary
    String testWord2 = "Word2";
    String testMeaning2 = "Meaning2";

    // add second word to testDictionary
    testDictionary.addWord(testWord2, testMeaning2);

    // if dictionary size is not 2 (second word not added), print error message and return false
    if (testDictionary.size() != 2) {
      System.out.println("FAILED: addWord Failed!");
      return false;
    }

    // return true as both tests passed
    return true;
  }


  /**
   * This method is used to check if the lookup method in DictionaryBST class works as expected.
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testDictionaryBSTLookup() {
    // initialize the testDictionary
    testDictionary = new DictionaryBST();

    // test word (and its meaning) to be added to the dictionary
    String testWord = "Word";
    String testMeaning = "Meaning";

    // add word to testDictionary
    testDictionary.addWord(testWord, testMeaning);

    // test word 2 (and its meaning) to be added to the dictionary
    String testWord2 = "Hello";
    String testMeaning2 = "World";

    // add second word to testDictionary
    testDictionary.addWord(testWord2, testMeaning2);

    // store result with the result from the lookup method
    String result = testDictionary.lookup("Hello");

    // if the result is not "World", print fail message and return false
    if (!result.equals("World")) {
      System.out.println("FAILED: lookup Failed!");
      return false;
    }

    // return true as test passed
    return true;
  }


  /**
   * This method is used to check if the size method in DictionaryBST class works as expected.
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testDictionaryBSTSize() {
    // initialize the testDictionary
    testDictionary = new DictionaryBST();

    // if dictionary size is not 0, print fail method and return false
    if (testDictionary.size() != 0) {
      System.out.println("FAILED: size Failed!");
      return false;
    }

    // test word (and its meaning) to be added to the dictionary
    String testWord = "Waterfall";
    String testMeaning = "water fall";

    // add word to testDictionary
    testDictionary.addWord(testWord, testMeaning);

    // if the dictionary size is not 1, print error message and return false
    if (testDictionary.size() != 1) {
      System.out.println("FAILED: size Failed!");
      return false;
    }

    // test word 2 (and its meaning) to be added to the dictionary
    String testWord2 = "Hola";
    String testMeaning2 = "Hello";

    // add second word to testDictionary
    testDictionary.addWord(testWord2, testMeaning2);

    // if dictionary size is not 2 (second word not added), print error message and return false
    if (testDictionary.size() != 2) {
      System.out.println("FAILED: size Failed!");
      return false;
    }

    // return true as all tests passed
    return true;
  }


  /**
   * This method is used to check if the getAllWords method in DictionaryBST class works as
   * expected.
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testDictionaryBSTGetAllWords() {
    // initialize the testDictionary
    testDictionary = new DictionaryBST();

    // initialize testWords to an empty arrayList
    testWords = new ArrayList<String>();

    // test word (and its meaning) to be added to the dictionary
    String testWord = "Ayuj";
    String testMeaning = "Prasad";

    // add word to testDictionary
    testDictionary.addWord(testWord, testMeaning);

    // second test word (and its meaning) to be added to the dictionary
    String testWord2 = "Wow";
    String testMeaning2 = "Amazing";

    // add word to testDictionary
    testDictionary.addWord(testWord2, testMeaning2);

    // third test word (and its meaning) to be added to the dictionary
    String testWord3 = "testWord";
    String testMeaning3 = "testMeaning";

    // add word to testDictionary
    testDictionary.addWord(testWord3, testMeaning3);

    // load testWords with return arrayList from getAllWords method
    testWords = testDictionary.getAllWords();

    // if the arrayList doesn't contain at least one of the words in our dictionary,
    if ((!testWords.contains(testWord) || (!testWords.contains(testWord2)) || (!testWords
        .contains(testWord3)))) {
      // print fail message and return false
      System.out.println("FAILED: getAllWords Failed!");
      return false;
    }

    // return true as test passed
    return true;
  }

  /**
   * This method is used to check if the isEmpty method in DictionaryBST class works as expected.
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testDictionaryBSTIsEmpty() {
    // initialize the testDictionary
    testDictionary = new DictionaryBST();

    // if the dictionary is not empty, print fail message and return false
    if (!testDictionary.isEmpty()) {
      System.out.println("FAILED: isEmpty Failed!");
      return false;
    }

    // test word (and its meaning) to be added to the dictionary
    String testWord = "Waterfall";
    String testMeaning = "water fall";

    // add word to testDictionary
    testDictionary.addWord(testWord, testMeaning);

    // if the dictionary is empty, print fail message and return false
    if (testDictionary.isEmpty()) {
      System.out.println("FAILED: isEmpty Failed!");
      return false;
    }

    // return true as all tests passed
    return true;
  }


  /**
   * This method is used to make the calls to the test methods in this class. It will check to
   * confirm that our test methods work as expected.
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {
    // load each result variable is stored with the return value of their respective method call
    boolean result1 = testDictionaryBSTAddWord();
    boolean result2 = testDictionaryBSTLookup();
    boolean result3 = testDictionaryBSTSize();
    boolean result4 = testDictionaryBSTGetAllWords();
    boolean result5 = testDictionaryBSTIsEmpty();

    // if all tests are true, print pass message
    if (result1 && result2 && result3 && result4 && result5) {
      System.out.println("All tests PASSED!");
    }

    // otherwise, print fail message
    else {
      System.out.println("Atleast 1 test FAILED!");
    }
  }
}
