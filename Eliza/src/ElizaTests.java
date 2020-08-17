//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Eliza Project (Tests File) - Big Program 2
// Files:           
// Course:          Computer science 200 - Semester 1,2018
//
// Author:          Ayuj Prasad
// Email:           prasad22@wisc.edu
// Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
// Enter your program here

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as
 * they are developed. These methods are private since they are only intended
 * for use within this class.
 * 
 * @author Jim Williams
 * @author TODO add your name here when you add tests and comment the tests
 *
 */
public class ElizaTests {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests
	 * when you are ready for them to run.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {

		// Milestone 1: Process User Input
		// M1: main nothing to do
		testSeparatePhrases();
		testFoundQuitWord();
		testSelectPhrase();
		testReplaceWord();
		testAssemblePhrase();

		// Milestone 2:
		// M2: implement parts of main as described in main method comments
		testSwapWords();
		testPrepareInput();
		testLoadResponseTable();

		// Milestone 3:
		// main: implement the rest of main as described in the main method
		// comments
		testFindKeyWordsInPhrase();
		testSelectResponse();
		testInputAndResponse();
		testSaveDialog();
	}

	/**
	 * This runs some tests on the separatePhrases method. Each test is to check
	 * and see if different phrases entered return the expected results. The
	 * phrase entered is split and put into the array. The size (how many times
	 * the phrase was split) of the expected array is compared to what the
	 * method would return. If they are different then an error message is
	 * produced. If the sizes are the same then a loop is used to check if each
	 * phrase is stored in the correct form (no extra white-spaces etc...) If
	 * there are no errors present then the test is passed (and pass message is
	 * produced).
	 * 
	 */
	private static void testSeparatePhrases() {
		// hold the current status of the test (true = error present, false = no
		// error)
		boolean error = false;

		// Test 1 - test if the sentence is split correctly.
		ArrayList<String> phrases = Eliza
				.separatePhrases("No.  I'm talking  to my dog! Bye.");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("no");
		expected.add("i'm talking to my dog");
		expected.add("bye");

		if (phrases.size() != expected.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected "
					+ expected.size() + " but found " + phrases.size()
					+ " phrases.");
		}

		for (int i = 0; i < expected.size(); i++) {
			if (!expected.get(i).equals(phrases.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected.get(i));
				System.out.println("  " + phrases.get(i));
			}
		}

		// "What? This isn't the 4th messy-sentence!" should result in ?
		// Test 2 - see if the sentence is split correctly.
		ArrayList<String> phrase2 = Eliza
				.separatePhrases("What? This isn't the 4th messy-sentence!");
		ArrayList<String> expected2 = new ArrayList<>();
		expected2.add("what");
		expected2.add("this isn't the 4th messy sentence");

		if (phrase2.size() != expected2.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected "
					+ expected2.size() + " but found " + phrase2.size()
					+ " phrases.");
		}

		for (int i = 0; i < expected2.size(); i++) {
			if (!expected2.get(i).equals(phrase2.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected2.get(i));
				System.out.println("  " + phrase2.get(i));
			}
		}

		// "NO" should result in?
		// Test 3 - see if a single word is split correctly and it matches.
		ArrayList<String> phrase3 = Eliza.separatePhrases("NO");
		ArrayList<String> expected3 = new ArrayList<>();
		expected3.add("no");

		if (phrase3.size() != expected3.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected "
					+ expected3.size() + " but found " + phrase3.size()
					+ " phrases.");
		}

		for (int i = 0; i < expected3.size(); i++) {
			if (!expected3.get(i).equals(phrase3.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected3.get(i));
				System.out.println("  " + phrase3.get(i));
			}
		}

		// "this tab" should result in?
		// Test 4 - see if "this tab" is split correctly using the method call
		ArrayList<String> phrase4 = Eliza.separatePhrases("this tab");
		ArrayList<String> expected4 = new ArrayList<>();
		expected4.add("this tab");

		if (phrase4.size() != expected4.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected "
					+ expected4.size() + " but found " + phrase4.size()
					+ " phrases.");
		}

		for (int i = 0; i < expected4.size(); i++) {
			if (!expected4.get(i).equals(phrase4.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected4.get(i));
				System.out.println("  " + phrase4.get(i));
			}
		}

		// "What?" should result in?
		// Test 5 - see if a single word (with capital word and ?) is split
		// correctly and it matches.
		ArrayList<String> phrase5 = Eliza.separatePhrases("What?");
		ArrayList<String> expected5 = new ArrayList<>();
		expected5.add("what");

		if (phrase5.size() != expected5.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected "
					+ expected5.size() + " but found " + phrase5.size()
					+ " phrases.");
		}

		for (int i = 0; i < expected5.size(); i++) {
			if (!expected5.get(i).equals(phrase5.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected5.get(i));
				System.out.println("  " + phrase5.get(i));
			}
		}

		// "Thank you, but no, I have to go. Goodbye!!!" should result in?
		// Test 6 - See if a sentence with Capital letters, commas, and ! are
		// split correctly.
		ArrayList<String> phrase6 = Eliza
				.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
		ArrayList<String> expected6 = new ArrayList<>();
		expected6.add("thank you");
		expected6.add("but no");
		expected6.add("i have to go");
		expected6.add("goodbye");

		if (phrase6.size() != expected6.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected "
					+ expected6.size() + " but found " + phrase6.size()
					+ " phrases.");
		}

		for (int i = 0; i < expected6.size(); i++) {
			if (!expected6.get(i).equals(phrase6.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected6.get(i));
				System.out.println("  " + phrase6.get(i));
			}
		}

		if (error) {
			System.out.println("testSeparatePhrases failed");
		} else {
			System.out.println("testSeparatePhrases passed");
		}
	}

	/**
	 * This runs some tests on the foundQuitWord method. This will test to see
	 * if different test phrases will find the quit-word or not. With the list
	 * given, the test will call foundQuitWord() and check to see if the quit
	 * word is present. The result will be stored in a variable and this will be
	 * used to check if the value returned from the method is correct If it is
	 * not then an error message is produced and the test is failed. Otherwise
	 * it will pass through and repeat with the other tests. If all tests are
	 * error-free then the test is passed and the pass message is produced
	 * 
	 */

	// TODO should QUIT_WORDS be embedded within foundQuitWord?
	private static void testFoundQuitWord() {
		boolean error = false;

		// Test 1 - tests the method with the sentence.
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("thank you for talking");
		phrases.add("bye");

		boolean quit = Eliza.foundQuitWord(phrases);
		if (!quit) {
			error = true;
			System.out.println("testFoundQuitWord 1: failed");
		}

		// additional test suggestions:
		// should foundQuitWord return true if bye is a part of a phrase rather
		// than
		// separate?
		// Test 2 - see if test will work correctly if quit word is in the
		// sentence.
		ArrayList<String> phrases2 = new ArrayList<>();
		phrases2.add("hello ayuj");
		phrases2.add("i will say bye later");

		boolean quit2 = Eliza.foundQuitWord(phrases2);
		if (quit2 == true) {
			error = true;
			System.out.println("testFoundQuitWord 2: failed");
		}
		// should foundQuitWord return true if goodbye is the first or only
		// phrase?
		// Test 3 - checks to see if it works with only quit words input
		ArrayList<String> phrases3 = new ArrayList<>();
		phrases3.add("goodbye");

		boolean quit3 = Eliza.foundQuitWord(phrases3);
		if (!quit3) {
			error = true;
			System.out.println("testFoundQuitWord 3: failed");
		}
		// should foundQuitWord return true if there are no quit words in the
		// phrases
		// list.
		// Test 4 - checks to see if test works with no quit words
		ArrayList<String> phrases4 = new ArrayList<>();
		phrases4.add("hello");
		phrases4.add("nice to meet you");

		boolean quit4 = Eliza.foundQuitWord(phrases4);
		if (quit4) {
			error = true;
			System.out.println("testFoundQuitWord 4: failed");
		}
		if (error) {
			System.err.println("testFoundQuitWord failed");
		} else {
			System.out.println("testFoundQuitWord passed");
		}
	}

	/**
	 * This runs some tests on the selectPhrase method. Uses the selectPhrase()
	 * method to check which phrase will be used. With the list that is given,
	 * the return from the method call will be stored in the variable. The
	 * result will be used to compare what the expected phrase should be. If the
	 * values don't match then an error message is given and the test is failed.
	 * If they match then the further test cases are checked. If all test cases
	 * are passed then the test is passed and the pass message if given.
	 * 
	 */
	private static void testSelectPhrase() {
		boolean error = false;

		// choose the longest
		// Test 1 - tests to see if the longest sentence is chosen.
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("no");
		phrases.add("sometimes I remember being on a boat");
		phrases.add("not often");
		String choice = Eliza.selectPhrase(phrases);
		if (!choice.equals("sometimes I remember being on a boat")) {
			error = true;
			System.out.println("testSelectPhrase 1: failed");
		}

		// What should happen when there are 2 choices of the same length?
		// Test 2- tests to see if the first longest sentence is chosen.
		ArrayList<String> phrases2 = new ArrayList<>();
		phrases2.add("okay");
		phrases2.add("that is fun");
		phrases2.add("i am happy");
		String choice2 = Eliza.selectPhrase(phrases2);
		if (!choice2.equals("that is fun")) {
			error = true;
			System.out.println("testSelectPhrase 2: failed");
		}
		// What should happen if an empty list is passed to selectPhrase?
		// Test 3 - tests to see if the test returns "" for an empty phrase.
		ArrayList<String> phrases3 = new ArrayList<>();
		String choice3 = Eliza.selectPhrase(phrases3);
		if (!choice3.equals("")) {
			error = true;
			System.out.println("testSelectPhrase 3: failed");
		}

		if (error) {
			System.err.println("testSelectPhrase failed");
		} else {
			System.out.println("testSelectPhrase passed");
		}
	}

	/**
	 * This runs some tests on the assemblePhrase method. Uses the
	 * assemblePhrase() method to check if the sentence assembled if correct The
	 * return from the method is put in to a variable, which is used to check if
	 * the sentenced returned was correct If the sentence is matched correctly
	 * with the expected sentence, then further test cases are checked. If the
	 * sentences are not the same, then an error message is produced and the
	 * test is failed. If all test cases are correct, then the test is passed
	 * and the pass message is produced.
	 * 
	 */
	private static void testAssemblePhrase() {
		boolean error = false;

		// Test 1 - see if phrase is assembled correctly.
		String[] words = { "This", "is a", "sentence" };
		String sentence = Eliza.assemblePhrase(words);
		String expectedSentence = "This is a sentence";

		if (!sentence.equals(expectedSentence)) {
			error = true;
			System.out.println("testAssembleSentence 1 failed '" + sentence
					+ "'");
		}

		// what should happen if an array with no strings in it is passed in?
		// Test 2 - see if phrase is returned is "".
		String[] words2 = {};
		String sentence2 = Eliza.assemblePhrase(words2);
		String expectedSentence2 = "";

		if (!sentence2.equals(expectedSentence2)) {
			error = true;
			System.out.println("testAssembleSentence 2 failed '" + sentence2
					+ "'");
		}

		// what should happen if just a list of words, with no internal spaces,
		// is
		// passed in?
		// Test 3 - see if correct phrase is returned.
		String[] words3 = { "helloayujnicetomeetyou" };
		String sentence3 = Eliza.assemblePhrase(words3);
		String expectedSentence3 = "helloayujnicetomeetyou";

		if (!sentence3.equals(expectedSentence3)) {
			error = true;
			System.out.println("testAssembleSentence 3 failed '" + sentence3
					+ "'");
		}

		if (error) {
			System.err.println("testAssemblePhrase failed");
		} else {
			System.out.println("testAssemblePhrase passed");
		}
	}

	/**
	 * This runs some tests on the findKeyWordsInPhrase method. Uses the
	 * findKeyWordsInPhrase() method to return the unmatched the words. The
	 * method is called and the result is stored in the variable. If the result
	 * matches with the expected output, the next test is checked. If any of the
	 * tests fail, the test fails and the fail message is printed. If all tests
	 * pass, then the pass message is printed.
	 * 
	 */
	private static void testFindKeyWordsInPhrase() {
		boolean error = false;

		{// block so each test has its own variable scope.
			// 1.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "are", "you", "a", "computer" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2
					|| !matches[0].equals("are you a")
					|| !matches[1].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 1 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 2.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "computer", "are", "you" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2
					|| !matches[0].equals("") || !matches[1].equals("are you")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 2 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 3.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "does", "that", "computer", "on", "your",
					"desk", "work" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2
					|| !matches[0].equals("does that")
					|| !matches[1].equals("on your desk work")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 3 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 4.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			String[] phrase = { "why", "don't", "you", "like", "me" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 3
					|| !matches[0].equals("why don't")
					|| !matches[1].equals("like") || !matches[2].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 4 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 5.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			String[] sentence = { "me", "don't", "like", "you" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 5 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 6.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] sentence = { "do", "you", "like", "that", "computer" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches == null || matches.length != 2
					|| !matches[0].equals("do you like that")
					|| !matches[1].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 6 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		if (error) {
			System.err.println("testFindKeyWordsInPhrase failed");
		} else {
			System.out.println("testFindKeyWordsInPhrase passed");
		}
	}

	/**
	 * This runs some tests on the saveDialog method. Uses the saveDialog()
	 * method to see if the dialog is saved correctly. The lines are stored into
	 * an arrayList and then stored on the file. The method is called and
	 * checked if the line(s) are read correctly. If it is read correctly and
	 * the line(s) match, then the test is passed and the next test is tested.
	 * If any of the tests fail, the test fails and the fail message is printed.
	 * If all tests pass, then the pass message is printed.
	 */
	private static void testSaveDialog() {
		boolean error = false;
		final String TEST_FILENAME = "testing.txt";
		{ // 1 - see if single line file is stored correctly
			ArrayList<String> list = new ArrayList<>();
			list.add("this is a single line.");
			try {
				Eliza.saveDialog(list, TEST_FILENAME);
				String readFromFile = readFile(TEST_FILENAME);
				if (!readFromFile.equals(list.get(0) + "\n")) {
					error = true;
					System.out.println("testSaveDialog 1 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile(TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		{ // 2. test multiple lines of output written to the file.
			// see if multiple lines file is stored correctly
			ArrayList<String> list = new ArrayList<>();
			list.add("this is a single line.");
			list.add("this is another single line");
			try {
				Eliza.saveDialog(list, TEST_FILENAME);
				String readFromFile = readFile(TEST_FILENAME);
				if (!readFromFile.equals(list.get(0) + "\n" + list.get(1)
						+ "\n")) {
					error = true;
					System.out.println("testSaveDialog 2 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile(TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (error) {
			System.err.println("testSaveDialog failed");
		} else {
			System.out.println("testSaveDialog passed");
		}
	}

	/**
	 * Supporting method for testSaveDialog
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return the contents of the file
	 */
	private static String readFile(String fileName) {
		StringBuffer buf = new StringBuffer();
		try (BufferedReader reader = new BufferedReader(
				new FileReader(fileName));) {
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line);
				buf.append("\n");
			}
			return buf.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Supporting method for testSaveDialog.
	 * 
	 * @param filename
	 *            file to be removed.
	 */
	private static void removeFile(String filename) {
		File file = new File(filename);
		try {
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This runs some tests on the replaceword method. Uses the replaceWord()
	 * method to check if the correct replacement word is used. Using the map, a
	 * call is made to the method and stored in a variable. The result will be
	 * used to compare what the expected phrase should be. If the values don't
	 * match then an error message is given and the test is failed. If they
	 * match then the further test cases are checked. If all test cases are
	 * passed then the test is passed and the pass message if given.
	 */
	private static void testReplaceWord() {
		boolean error = false;

		{ // 1.
			String word = "machine";
			String expected = "computer";
			String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 1 failed. result:'"
						+ result + "' expected:'" + expected + "'");
			}
		}

		{ // 2.
			String word = "yourself";
			String expected = "myself";
			String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 3 failed. result:'"
						+ result + "' expected:'" + expected + "'");
			}
		}

		// Do these tests meet all the requirements described in the replaceWord
		// header comment,
		// such as handling a null wordMap?
		{ // 3 - performing test with null wordMap
			final String[][] nullMap = null;
			String word = "machine";
			String expected = "machine";
			String result = Eliza.replaceWord(word, nullMap);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 1 failed. result:'"
						+ result + "' expected:'" + expected + "'");
			}
		}

		if (error) {
			System.err.println("testReplaceWord failed");
		} else {
			System.out.println("testReplaceWord passed");
		}
	}

	/**
	 * This runs some tests on the swapWords method. Uses the wordMap to replace
	 * words in the phrase if matches are found. The swapWords method is called
	 * and the result is stored in a variable. This variable will be used to see
	 * if the result from the method call is as expected. If it is, the next
	 * test is executed. If not, then the test is failed and the fail message is
	 * printed. If all tests pass then the pass message is printed.
	 * 
	 */
	private static void testSwapWords() {
		boolean error = false;

		{ // 1.
			String someWords = "i'm cant recollect";
			String expected = "i am cannot remember";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 1 failed. result:'" + result
						+ "' expected:'" + expected + "'");
			}
		}

		{ // 2.
			String someWords = "i'm happy";
			String expected = "you are happy";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 2 failed. result:'" + result
						+ "' expected:'" + expected + "'");
			}
		}

		{ // 3.
			String someWords = "about my dog";
			String expected = "about your dog";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result
						+ "' expected:'" + expected + "'");
			}
		}

		{ // 4.
			String someWords = "i'm sorry";
			String expected = "i am apologize";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 4 failed. result:'" + result
						+ "' expected:'" + expected + "'");
			}
		}

		{ // 5.
			String someWords = "i'm sorry mom";
			String expected = "i am apologize mother";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 5 failed. result:'" + result
						+ "' expected:'" + expected + "'");
			}
		}

		if (error) {
			System.err.println("testSwapWords failed");
		} else {
			System.out.println("testSwapWords passed");
		}
	}

	/**
	 * This runs some tests on the selectResponse method. Uses the
	 * selectResponse() method to return the required response from the bot. The
	 * method is called and the result is stored in the variable. If the result
	 * matches with the expected output, the next test is checked. If any of the
	 * tests fail, the test fails and the fail message is printed. If all tests
	 * pass, then the pass message is printed.
	 * 
	 */
	private static void testSelectResponse() {
		boolean error = false;

		{ // 1.
			// is one of the 3 strings chosen?
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			strList.add("happy");
			strList.add("cat");
			String choice = Eliza.selectResponse(randGen, strList);

			if (!(choice.equals("The") || choice.equals("happy") || choice
					.equals("cat"))) {
				error = true;
				System.out.println("testSelectResponse 1 failed.");
			}
		}

		{ // 2.
			// if called 1000 times, are the choices approximately random?
			Random randGen = new Random(765);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("this");
			strList.add("is");
			strList.add("a");
			strList.add("list");
			strList.add("to");
			strList.add("choose");
			strList.add("from");
			int[] actualCount = new int[strList.size()];
			int[] expectedCount = new int[] { 156, 146, 142, 138, 160, 130, 128 };
			for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
				String choice = Eliza.selectResponse(randGen, strList);
				for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
					if (choice.equals(strList.get(wordIndex))) {
						actualCount[wordIndex]++;
					}
				}
			}
			// since we set a seed on the random number generator we should know
			// the expected
			// outcome
			for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
				if (actualCount[countIndex] != expectedCount[countIndex]) {
					error = true;
					System.out.println("testSelectResponse 2 failed.");
					System.out.println("  expectedCount: "
							+ Arrays.toString(expectedCount));
					System.out.println("  actualCount: "
							+ Arrays.toString(actualCount));
				}
			}

		}

		{ // 3.
			// What should happen when null is passed to selectResponse?
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			String choice = Eliza.selectResponse(randGen, strList);

			if (!(choice == null)) {
				error = true;
				System.out.println("testSelectResponse 3 failed.");
			}
		}

		if (error) {
			System.err.println("testSelectResponse failed");
		} else {
			System.out.println("testSelectResponse passed");
		}
	}

	/**
	 * This runs some tests on the prepareInput method. Uses the prepareInput()
	 * method to determine the input that is used. The method is called and the
	 * result is stored in the variable. If the result matches with the expected
	 * output, the next test is checked. If any of the tests fail, the test
	 * fails and the fail message is printed. If all tests pass, then the pass
	 * message is printed.
	 * 
	 */
	private static void testPrepareInput() {
		boolean error = false;

		{ // 1.
			String input = "bye";
			String[] result = null;
			result = Eliza.prepareInput("bye");
			if (result != null) {
				error = true;
				System.out.println("testPrepareInput 1: failed");
				System.out.println("  input: " + input);
				System.out.println("  result: " + Arrays.toString(result));
			}
		}

		if (error) {
			System.err.println("testPrepareInput failed");
		} else {
			System.out.println("testPrepareInput passed");
		}
	}

	/**
	 * This runs some tests on the loadResponseTable method. Uses the
	 * loadResponseTable() to see if the table is loaded correctly. Once loaded,
	 * tests will be done to check if the table was loaded correctly. If the
	 * test passes, the next test is done. If a test fails, then the fail
	 * message is printed. If all tests pass, then the pass message is printed.
	 */
	private static void testLoadResponseTable() {
		boolean error = false;
		{ // 1.
			ArrayList<String> expected1stRow = new ArrayList<String>();
			expected1stRow.add("computer");
			ArrayList<ArrayList<String>> table = Eliza
					.loadResponseTable("Eliza.rsp");
			if (!table.get(0).equals(expected1stRow)) {
				error = true;
				System.out.println("testLoadResponseTable 1: failed");
				System.out.println("  expected1stRow: " + expected1stRow);
				System.out.println("  table1stRow: " + table.get(0));
			}

			// 2.
			if (table.size() % 2 != 0) {
				error = true;
				System.out.println("testLoadResponseTable 2: failed");
				System.out
						.println("  expected an even number of elements in table but found: "
								+ table.size());
			}

		}
		if (error) {
			System.err.println("testLoadResponseTable failed");
		} else {
			System.out.println("testLoadResponseTable passed");
		}
	}

	/*
	 * Supporting method for testInputAndResponse. Returns 1 if the test passed
	 * and 0 if the test failed.
	 */
	private static int checkResponse(String input, String expectedResponse,
			Random rand, ArrayList<ArrayList<String>> table) {

		String[] words = Eliza.prepareInput(input);
		if (words == null) {
			if (expectedResponse == null) {
				return 1;
			} else {
				System.out.println("testInputLines  checkResponse error");
				System.out.println("  input='" + input + "'");
				System.out.println("  response=null");
				System.out.println("  expected='" + expectedResponse + "'");
				return 0;
			}
		}

		String response = Eliza.prepareResponse(words, rand, table);
		if (!response.equals(expectedResponse)) {
			System.out.println("testPrepareResponse  checkResponse error");
			System.out.println("  input='" + input + "'");
			System.out.println("  response='" + response + "'");
			System.out.println("  expected='" + expectedResponse + "'");
			return 0;
		} else {
			return 1;
		}
	}

	/*
	 * Runs tests on a bunch of example sentences using a Random number
	 * generator with a seed.
	 */
	private static void testInputAndResponse() {
		int numPassed = 0;
		// since we are using a seed in the random number generator, the
		// responses should be fixed for the provided response table.
		Random randGen = new Random(923);
		ArrayList<ArrayList<String>> responseTable = Eliza
				.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

		numPassed += checkResponse("I like computers.",
				"What do you think machines have to do with your problem?",
				randGen, responseTable);

		numPassed += checkResponse("What is your name?",
				"I don't care about names -- please continue.", randGen,
				responseTable);
		numPassed += checkResponse("You remind me of a grumpy uncle.",
				"What other connections do you see?", randGen, responseTable);
		numPassed += checkResponse("You don't seem to say much.",
				"What are your feelings now?", randGen, responseTable);
		numPassed += checkResponse("You reflect me.",
				"What makes you think I reflect you?", randGen, responseTable);
		numPassed += checkResponse("I wish to believe you.",
				"Why do you think you wish to believe me?", randGen,
				responseTable);
		numPassed += checkResponse("I dreamed of going into space.",
				"Have you ever dreamed of going into space before?", randGen,
				responseTable);
		numPassed += checkResponse("I apologize for boring you.",
				"Why do you think you apologize for boring me?", randGen,
				responseTable);
		numPassed += checkResponse("Because it seems the polite thing to do.",
				"Does that reason seem to explain anything else?", randGen,
				responseTable);
		numPassed += checkResponse("Yes.", "You are sure.", randGen,
				responseTable);
		numPassed += checkResponse("I remember being in 1st grade.",
				"What is the connection between me and being in 1st grade?",
				randGen, responseTable);
		numPassed += checkResponse(
				"No, not often. Sometimes I remember being on a boat.",
				"Why do you recollect being on a boat just now?", randGen,
				responseTable);
		numPassed += checkResponse("My family liked to play cards.",
				"Tell me more about your family.", randGen, responseTable);
		numPassed += checkResponse("Do you remember growing up?",
				"What about growing up?", randGen, responseTable);
		numPassed += checkResponse("Are you a Packers fan?",
				"Perhaps I am a packers fan in your fantasies.", randGen,
				responseTable);
		numPassed += checkResponse("I am sad to hear that.",
				"Can you explain what made you sad?", randGen, responseTable);
		numPassed += checkResponse("I cannot explain this.",
				"Do you really want to be able to explain this?", randGen,
				responseTable);
		numPassed += checkResponse(
				"You seem to have a different perspective than many.",
				"You're not really talking about me -- are you?", randGen,
				responseTable);
		numPassed += checkResponse("I'm talking to my dog.",
				"How long have you been talking to your dog?", randGen,
				responseTable);
		numPassed += checkResponse("goodbye", null, randGen, responseTable);

		numPassed += checkResponse("", "I'm not sure I understand you fully.",
				randGen, responseTable);

		int expected = 21;
		if (numPassed == expected) {
			System.out.println("testInputAndResponse passed ");
		} else {
			System.err.println("testInputAndResponse failed "
					+ (expected - numPassed));
		}
	}

}
