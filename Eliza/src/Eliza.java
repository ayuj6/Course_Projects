//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Eliza Project (Main File) - Big Program 2
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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that
 * collects user input and responds appropriately. Eliza is based off of a
 * computer program written at MIT in the 1960's by Joseph Weizenbaum. Eliza
 * uses keyword matching to respond to users in a way that displays interest in
 * the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

	/*
	 * This method does input and output with the user. It calls supporting
	 * methods to read and write files and process each user input.
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {

		// name of the therapist.
		String therapistName = "";

		// variable to hold the responseTable for specific therapist.
		ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();

		// scanner to read user inputs.
		Scanner sc = new Scanner(System.in);

		// random variable with Config SEED to use on the methods.
		Random rand = new Random(Config.SEED);

		// ArrayList to store each line of the conversation between the
		// therapist and the user.
		ArrayList<String> saveConversation = new ArrayList<String>();

		// How the program starts depends on the command-line arguments.
		// Command-line arguments can be names of therapists for example:
		// Eliza Joe Laura

		// If no command-line arguments then the therapists name is Eliza
		if (args.length == 0) {

			// responseTable = loadResponseTable("Eliza.rsp");
			therapistName = "Eliza";
		}

		// If there is more than one command-line argument then offer them
		// as a list of people to talk with.
		if (args.length > 1) {
			System.out.print("Would you like to speak with ");

			// counter for the "for" loop.
			int i = 0;
			for (i = 0; i < args.length - 1; ++i) {
				System.out.print(args[i] + ", ");
			}
			System.out.print("or " + args[i] + "?");
			therapistName = sc.next();
		}

		// If only one command-line argument, then that is the therapist name to
		// be used.
		if (args.length == 1) {
			therapistName = args[0];
		}

		// When a user types a name then read the responses from the file which
		// is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
		responseTable = loadResponseTable(therapistName
				+ Config.RESPONSE_FILE_EXTENSION);

		// If loadResponseTable can't load
		// the file then it will report an error.
		if (responseTable == null) {

			// Program will terminate if there is an error.
			System.exit(0);
		}

		// name prompt
		System.out.println("Hi I'm " + therapistName + ", what is your name?");

		// Stores the user's name in nameUser.
		String nameUser = sc.nextLine();

		// The name prompt is added to the ArrayList.
		saveConversation
				.add("Hi I'm " + therapistName + ", what is your name?");

		// welcome prompt
		System.out.println("Nice to meet you " + nameUser.trim()
				+ ". What is on your mind?");

		// The welcome prompt is added to the ArrayList.
		saveConversation.add("Nice to meet you " + nameUser.trim()
				+ ". What is on your mind?");

		// begin conversation loop
		while (true) {

			// user's input is stored in variable.
			String userInput = sc.nextLine();

			// user's input is added to the ArrayList.
			saveConversation.add(userInput);

			// the user's input is prepared.
			String[] input = prepareInput(userInput);

			// If there is a quit word in the input.
			if (input == null) {

				// The goodbye prompt is printed.
				System.out.println("Goodbye " + nameUser.trim() + ".");

				// The goodbye prompt is added to the ArrayList.
				saveConversation.add("Goodbye " + nameUser.trim() + ".");

				// We break out of the conversation loop.
				break;
			}

			// If no quit word is found in the input.
			else {

				// The therapist response is prepared
				String preparedResponse = prepareResponse(input, rand,
						responseTable);

				// The response is printed.
				System.out.println(preparedResponse);

				// The response is added to the ArrayList.
				saveConversation.add(preparedResponse);
			}
		}

		// End of the conversation loop

		// Prompt to save conversation.
		System.out
				.println("Would you like to save a record of this converstion?");

		// User's response is saved in variable.
		String save = sc.next();

		while (true) {

			// Check if the user's response starts with 'y' or 'Y'
			if ((save.charAt(0) == 'y') || (save.charAt(0) == 'Y')) {

				// if it does, then prompt to ask which file to save
				// conversation to.
				System.out
						.println("Which file would you like to save this Conversation to?");

				// File name to store the conversation in is recorded and stored
				// in a variable.
				String savedFile = sc.next();

				try {

					// Call saveDialog to save the conversation in the ArrayList
					// to savedFile.
					saveDialog(saveConversation, savedFile);

					// Prompt to user where the file is saved.
					System.out
							.println("Thanks again for talking! Our conversation is saved in: "
									+ savedFile + ".");

					break;
				}

				catch (IOException e) {

					// Response to give if IOException is caught.
					System.out.print("Unable to save conversation to: "
							+ savedFile);

					// Repeat the code prompting the user if they want to save
					// the dialog.
					continue;
				}
			}
		}

		// Close scanner.
		sc.close();
	}

	/**
	 * This method processes the user input, returning an ArrayList containing
	 * Strings, where each String is a phrase from the user's input. This is
	 * done by removing leading and trailing whitespace, making the user's input
	 * all lower case, then going through each character of the user's input.
	 * When going through each character this keeps all digits, alphabetic
	 * characters and ' (single quote). The characters ? ! , . signal the end of
	 * a phrase, and possibly the beginning of the next phrase, but are not
	 * included in the result. All other characters such as ( ) - " ] etc.
	 * should be replaced with a space. This method makes sure that every phrase
	 * has some visible characters but no leading or trailing whitespace and
	 * only a single space between words of a phrase. If userInput is null then
	 * return null, if no characters then return a 0 length list, otherwise
	 * return a list of phrases. Empty phrases and phrases with just
	 * invalid/whitespace characters should NOT be added to the list.
	 * 
	 * Example userInput: "Hi,  I  am! a big-fun robot!!!" Example returned:
	 * "hi", "i am", "a big fun robot"
	 * 
	 * @param userInput
	 *            text the user typed
	 * @return the phrases from the user's input
	 */
	public static ArrayList<String> separatePhrases(String userInput) {

		// if input is null then return null
		if (userInput == null) {
			return null;
		}

		// create new array to store the different phrases.
		ArrayList<String> inputPhrases = new ArrayList<String>();

		// after removing white-spaces and turning phrase to lower-case
		userInput = userInput.toLowerCase().trim();

		// if there are no characters then return a 0 length list.
		if (userInput.length() == 0) {
			return new ArrayList<String>(0);
		}

		// valToAdd is the phrase that will be added to the list
		String valToAdd = "";

		// loop through each character in the phrase
		for (int i = 0; i < userInput.length(); ++i) {

			// currVal holds the current character at position i.
			char currVal = userInput.charAt(i);

			// check for if the character is a letter, digit, or a "'"
			// semi-colon.
			if ((currVal >= 'a' && currVal <= 'z')
					|| (currVal >= '0' && currVal <= '9') || (currVal == '\'')) {

				// add it to the phrase to add if it is true.
				valToAdd = valToAdd + currVal;
				continue;
			}

			// if there are 2 spaces next to each other then only 1 is kept.
			if ((currVal == ' ') && (userInput.charAt(i - 1) == ' ')) {
				continue;
			} else {

				// check if the character is any of the end-phrase characters
				switch (currVal) {
				case '?':
					break;
				case '!':
					break;
				case ',':
					break;
				case '.':
					break;
				default:

					// for any other character that is detected: (,),],} etc...
					// a white-space is added.
					if (valToAdd.length() > 0) {

						// to avoid multiple white-spaces in the phrase
						if (!(valToAdd.charAt(valToAdd.length() - 1) == ' ')) {
							valToAdd = valToAdd + " ";
						}
					}
					continue;
				}
			}

			// remove all leading and trailing white-spaces
			valToAdd = valToAdd.trim();

			// if the length is 0, then we don't add it to the list.
			if (valToAdd.length() == 0) {
				valToAdd = "";
				continue;
			}

			// add the phrase to the list and reset the valToAdd for the next
			// phrase.
			valToAdd = valToAdd.trim();
			inputPhrases.add(valToAdd);
			valToAdd = "";
		}

		// return ArrayList.
		valToAdd = valToAdd.trim();
		if (valToAdd.length() != 0) {
			inputPhrases.add(valToAdd);
		}
		return inputPhrases;
	}

	/**
	 * Checks whether any of the phrases in the parameter match a quit word from
	 * Config.QUIT_WORDS. Note: complete phrases are matched, not individual
	 * words within a phrase.
	 * 
	 * @param phrases
	 *            List of user phrases
	 * @return true if any phrase matches a quit word, otherwise false
	 */
	public static boolean foundQuitWord(ArrayList<String> phrases) {

		// Loop to go through every index in the ArrayList.
		for (int i = 0; i < phrases.size(); ++i) {

			// Store the value at the current cell in a variable
			String indexVal = phrases.get(i);

			// Loop through each index of QUIT_WORDS.
			for (int j = 0; j < Config.QUIT_WORDS.length; ++j) {

				// If the current value is a Quit Word.
				if (indexVal.equals(Config.QUIT_WORDS[j])) {

					// True is returned.
					return true;
				}
			}
		}
		// If no Quit Word is present then false is returned.
		return false;
	}

	/**
	 * Iterates through the phrases of the user's input, finding the longest
	 * phrase to which to respond. If two phrases are the same length, returns
	 * whichever has the lower index in the list. If phrases parameter is null
	 * or size 0 then return null.
	 * 
	 * @param phrases
	 *            List of user phrases
	 * @return the selected phrase
	 */
	public static String selectPhrase(ArrayList<String> phrases) {

		// Variable to hold the longest phrase.
		String selectPhrase = "";

		// Holds the length of the longest phrase.
		int max = 0;

		// If phrase is empty or null then "" is returned.
		if ((phrases.size() == 0) || (phrases == null)) {
			return "";
		}

		// loop through phrases
		for (int i = 0; i < phrases.size(); ++i) {

			// if the length is greater than the current longest phrase.
			if (phrases.get(i).length() > max) {

				// Max is updated and the phrase is stored in a variable.
				max = phrases.get(i).length();
				selectPhrase = phrases.get(i);
			}
		}

		// Longest phrase is returned.
		return selectPhrase;
	}

	/**
	 * Looks for a replacement word for the word parameter and if found, returns
	 * the replacement word. Otherwise if the word parameter is not found then
	 * the word parameter itself is returned. The wordMap parameter contains
	 * rows of match and replacement strings. On a row, the element at the 0
	 * index is the word to match and if it matches return the string at index 1
	 * in the same row. Some example word maps that will be passed in are
	 * Config.INPUT_WORD_MAP and Config.PRONOUN_MAP.
	 * 
	 * If word is null return null. If wordMap is null or wordMap length is 0
	 * simply return word parameter. For this implementation it is reasonable to
	 * assume that if wordMap length is >= 1 then the number of elements in each
	 * row is at least 2.
	 * 
	 * @param word
	 *            The word to look for in the map
	 * @param wordMap
	 *            The map of words to look in
	 * @return the replacement string if the word parameter is found in the
	 *         wordMap otherwise the word parameter itself.
	 */
	public static String replaceWord(String word, String[][] wordMap) {

		// If the word is null, then null is returned.
		if (word == null) {
			return null;
		}

		// If the wordMap is null or it is empty (length = 0), then the original
		// word is returned.
		if ((wordMap == null) || (wordMap.length == 0)) {
			return word;
		}

		// Loop through wordMap
		for (int i = 0; i < wordMap.length; ++i) {

			// If the word at WordMap's 0th index and word match
			if (wordMap[i][0].equals(word)) {

				// The word at the 1st index is returned.
				return wordMap[i][1];
			}
		}

		// Else the original word is returned.
		return word;
	}

	/**
	 * Concatenates the elements in words parameter into a string with a single
	 * space between each array element. Does not change any of the strings in
	 * the words array. There are no leading or trailing spaces in the returned
	 * string.
	 * 
	 * @param words
	 *            a list of words
	 * @return a string containing all the words with a space between each.
	 */
	public static String assemblePhrase(String[] words) {

		// The String to return is initialized.
		String returnString = "";

		// Loop through each character in the word.
		for (int i = 0; i < words.length; ++i) {

			// The character is added and a space is added after that.
			returnString = returnString + words[i];
			returnString = returnString + " ";
		}

		// The String, after trimming, is returned.
		return returnString.trim();
	}

	/**
	 * Replaces words in phrase parameter if matching words are found in the
	 * mapWord parameter. A word at a time from phrase parameter is looked for
	 * in wordMap which may result in more than one word. For example: i'm => i
	 * am Uses the replaceWord and assemblePhrase methods. Example wordMaps are
	 * Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then
	 * phrase parameter is returned. Note: there will Not be a case where a
	 * mapping will itself be a key to another entry. In other words, only one
	 * pass through swapWords will ever be necessary.
	 * 
	 * @param phrase
	 *            The given phrase which contains words to swap
	 * @param wordMap
	 *            Pairs of corresponding match & replacement words
	 * @return The reassembled phrase
	 */
	public static String swapWords(String phrase, String[][] wordMap) {

		// if wordMap is null then the original phrase is returned.
		if (wordMap == null) {
			return phrase;
		}

		// The array will hold each word (separated by a space) in a cell.
		String[] phraseArray = phrase.split(" ");

		// The variable to hold the reassembled phrase.
		String newPhrase = "";

		// loop through each cell in the array.
		for (int j = 0; j < phraseArray.length; ++j) {

			// The value at the current index of the array is replaced with the
			// word returned from replaceWord.
			phraseArray[j] = replaceWord(phraseArray[j], wordMap);
		}

		// The phrase is assembled from the array using assemblePhrase and it is
		// returned.
		newPhrase = assemblePhrase(phraseArray);
		return newPhrase;
	}

	/**
	 * This prepares the user input. First, it separates input into phrases
	 * (using separatePhrases). If a phrase is a quit word (foundQuitWord) then
	 * return null. Otherwise, select a phrase (selectPhrase), swap input words
	 * (swapWords with Config.INPUT_WORD_MAP) and return an array with each word
	 * its own element in the array.
	 * 
	 * @param input
	 *            The input from the user
	 * @return words from the selected phrase
	 */
	public static String[] prepareInput(String input) {

		ArrayList<String> phrasesArray = new ArrayList<String>();

		// ArrayList holds the separated Phrases from the user's input.
		phrasesArray = separatePhrases(input);

		// if there is a quit word, then null is returned.
		if (foundQuitWord(phrasesArray)) {
			return null;
		}

		// The variable will hold the longest phrase.
		String phraseToUse = "";

		// longest phrase is stored in the variable.
		phraseToUse = selectPhrase(phrasesArray);

		// After swapping words in the phrase, the new phrase is stored in
		// another variable.
		String newInput = swapWords(phraseToUse, Config.INPUT_WORD_MAP);

		// The phase is split into an array (separated by spaced) and that is
		// returned.
		String[] userInput = newInput.split(" ");
		return userInput;
	}

	/**
	 * Reads a file that contains keywords and responses. A line contains either
	 * a list of keywords or response, any blank lines are ignored. All leading
	 * and trailing whitespace on a line is ignored. A keyword line begins with
	 * "keywords" with all the following tokens on the line, the keywords. Each
	 * line that follows a keyword line that is not blank is a possible response
	 * for the keywords. For example (the numbers are for our description
	 * purposes here and are not in the file):
	 * 
	 * 1 keywords computer 2 Do computers worry you? 3 Why do you mention
	 * computers? 4 5 keywords i dreamed 6 Really, <3>? 7 Have you ever
	 * fantasized <3> while you were awake? 8 9 Have you ever dreamed <3>
	 * before?
	 *
	 * In line 1 is a single keyword "computer" followed by two possible
	 * responses on lines 2 and 3. Line 4 and 8 are ignored since they are blank
	 * (contain only whitespace). Line 5 begins new keywords that are the words
	 * "i" and "dreamed". This keyword list is followed by three possible
	 * responses on lines 6, 7 and 9.
	 * 
	 * The keywords and associated responses are each stored in their own
	 * ArrayList. The response table is an ArrayList of the keyword and
	 * responses lists. For every keywords list there is an associated response
	 * list. They are added in pairs into the list that is returned. There will
	 * always be an even number of items in the returned list.
	 * 
	 * Note that in the event an IOException occurs when trying to read the file
	 * then an error message "Error reading <fileName>", where <fileName> is the
	 * parameter, is printed and a non-null reference is returned, which may or
	 * may not have any elements in it.
	 * 
	 * @param fileName
	 *            The name of the file to read
	 * @return The response table
	 */
	public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {

		// new file will be created.
		File file = new File(fileName);

		// responseTable is created
		ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();

		// line variable created to hold each line read.
		String line = "";

		// The arrayLists will hold the keywords and their respective responses.
		ArrayList<String> keywords = new ArrayList<String>();
		ArrayList<String> responses = new ArrayList<String>();
		try {

			// scanner will read from the file.
			Scanner sc = new Scanner(file);

			// While there is another line to read from the file.
			while (sc.hasNextLine()) {

				// The next line is stored in the variable and all leading and
				// trailing white-spaces are removed.
				line = sc.nextLine();
				line = line.trim();

				// if the line is empty then we go back to the beginning and
				// read the next line.
				if (line.length() == 0) {
					continue;
				}

				// if the line started with "keywords"
				if (line.startsWith("keywords")) {

					// we create new arrayLists of keywords and responses
					keywords = new ArrayList<String>();
					responses = new ArrayList<String>();

					// we store the rest of the line (after "keyword") in a
					// variable and trim it.
					String subStr = line.substring(8);
					subStr = subStr.trim();

					// The remainder of the phrase is split into an array
					// (separated by spaces)
					String[] tempArray = subStr.split(" ");

					// loop through each word in the array
					for (int i = 0; i < tempArray.length; i++) {

						// add the word at each index into the arrayList
						// keywords
						keywords.add(i, tempArray[i]);
					}

					// keywords is added to responseTable.
					responseTable.add(keywords);
				}

				// If the line doesn't start with "keywords"
				else {

					// If the line is empty after trimming we continue.
					line = line.trim();
					if (line.length() == 0) {
						continue;
					}

					// otherwise we add the line to the responses list
					responses.add(line);
				}

				// After looping through the lines, once we find the next line
				// that starts with keywords, then we store the list of keywords
				// to responseTable.
				if (line.startsWith("keywords")) {
					responseTable.add(responses);
				}
			}

			// if the scanner value is not null then we close it.
			if (!sc.equals(null)) {
				sc.close();
			}
		}

		// if an exception is caught then we print an error message and return
		// null.
		catch (IOException e) {
			System.out.println("Error reading " + fileName);
			return null;
		}

		// The responseTable is returned.
		return responseTable;
	}

	/**
	 * Checks to see if the keywords match the sentence. In other words, checks
	 * to see that all the words in the keyword list are in the sentence and in
	 * the same order. If all the keywords match then this method returns an
	 * array with the unmatched words before, between and after the keywords. If
	 * the keywords do not match then null is returned.
	 * 
	 * When the phrase contains elements before, between, and after the
	 * keywords, each set of the three is returned in its own element String[]
	 * keywords = {"i", "dreamed"}; String[] phrase = {"do", "you", "know",
	 * that", "i", "have", "dreamed", "of", "being", "an", "astronaut"};
	 * 
	 * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] =
	 * "of being an astronaut"
	 * 
	 * In an example where there is a single keyword, the resulting List's first
	 * element will be the the pre-sequence element and the second element will
	 * be everything after the keyword, in the phrase String[] keywords =
	 * {"always"}; String[] phrase = {"I", "always", "knew"};
	 * 
	 * toReturn[0] = "I" toReturn[1] = "knew"
	 * 
	 * In an example where a keyword is not in the phrase in the correct order,
	 * null is returned. String[] keywords = {"computer"}; String[] phrase =
	 * {"My","dog", "is", "lost"};
	 * 
	 * return null
	 * 
	 * @param keywords
	 *            The words to match, in order, in the sentence.
	 * @param phrase
	 *            Each word in the sentence.
	 * @return The unmatched words before, between and after the keywords or
	 *         null if the keywords are not all matched in order in the phrase.
	 */
	public static String[] findKeyWordsInPhrase(ArrayList<String> keywords,
			String[] phrase) {
		// see the algorithm presentation linked in Eliza.pdf.

		// the array to be returned is initialized with length 1 more than the
		// keywords array size.
		String[] toReturn = new String[keywords.size() + 1];

		// Loop through and set each index of toReturn to ""
		for (int i = 0; i < keywords.size() + 1; i++) {
			toReturn[i] = "";
		}

		// initialize j,k, and unmatched.
		int keywordCounter = 0;
		int arrayIndex = 0;
		String unmatched = "";

		// loop through each word in the sentence.
		for (int i = 0; i < phrase.length; i++) {

			// if the current word and keyword value are equal
			if (phrase[i].equals(keywords.get(keywordCounter))) {

				// then we increment the values and reset the unmatched string
				// to ""
				arrayIndex++;
				keywordCounter++;
				unmatched = "";
			}

			else {

				// otherwise we add the phrase to unmatched and store the
				// trimmed form of the string in the index of toReturn
				unmatched = unmatched + phrase[i] + " ";
				toReturn[arrayIndex] = unmatched.trim();
			}

			// to avoid OutOfBounds error
			if (keywordCounter >= keywords.size()) {
				keywordCounter--;
			}
		}

		// if the sizes don't match then null is returned.
		if (arrayIndex != keywords.size()) {
			return null;
		}

		// otherwise
		else {

			// return toReturn
			return toReturn;
		}
	}

	/**
	 * Selects a randomly generated response within the list of possible
	 * responses using the provided random number generator where the number
	 * generated corresponds to the index of the selected response. Use Random
	 * nextInt( responseList.size()) to generate the random number. If
	 * responseList is null or 0 length then return null.
	 * 
	 * @param rand
	 *            A random number generator.
	 * @param responseList
	 *            A list of responses to choose from.
	 * @return A randomly selected response
	 */
	public static String selectResponse(Random rand,
			ArrayList<String> responseList) {

		// If the responseList is null or 0 length, then null is returned.
		if ((responseList == null) || (responseList.size() == 0)) {
			return null;
		}

		// Using a random number generator, it will get a random response from
		// the list.
		int value = rand.nextInt(responseList.size());

		// The response is returned.
		return responseList.get(value);
	}

	/**
	 * This method takes processed user input and forms a response. This looks
	 * through the response table in order checking to see if each keyword
	 * pattern matches the userWords. The first matching keyword pattern found
	 * determines the list of responses to choose from. A keyword pattern
	 * matches the userWords, if all the keywords are found, in order, but not
	 * necessarily contiguous. This keyword matching is done by
	 * findKeyWordsInPhrase method. See the findKeyWordsInPhrase algorithm in
	 * the Eliza.pdf.
	 * 
	 * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
	 * Otherwise one of possible responses for the matched keywords is selected
	 * with selectResponse method. The response selected is checked for the
	 * replacement symbol <n> where n is 1 to the length of unmatchedWords array
	 * returned by findKeyWordsInPhrase. For each replacement symbol the
	 * corresponding unmatched words element (index 0 for <1>, 1 for <2> etc.)
	 * has its pronouns swapped with swapWords using Config.PRONOUN_MAP and then
	 * replaces the replacement symbol in the response.
	 * 
	 * @param userWords
	 *            using input after preparing.
	 * @param rand
	 *            A random number generator.
	 * @param responseTable
	 *            A table containing a list of keywords and response pairs.
	 * @return The generated response
	 */
	public static String prepareResponse(String[] userWords, Random rand,
			ArrayList<ArrayList<String>> responseTable) {

		// Initialize array and ArrayList
		ArrayList<String> responses = new ArrayList<String>();
		String[] keywords = null;

		// Loop through the responseTable (only for the keywords)
		for (int i = 0; i < responseTable.size(); i += 2) {

			// if the value at the current index contains the keyword pattern
			if (findKeyWordsInPhrase(responseTable.get(i), userWords) != null) {

				// if it does, then the keywords and responses are stored and we
				// break from the loop.
				keywords = new String[responseTable.get(i).size() + 1];
				keywords = findKeyWordsInPhrase(responseTable.get(i), userWords);
				responses = responseTable.get(i + 1);
				break;
			}

			// ArrayList is set to null if no match is found and we go to the
			// next iteration.
			else {
				responses = null;
				continue;
			}
		}

		// if responses are null then we return NO_MATCH_RESPONSE
		if (responses == null) {
			return Config.NO_MATCH_RESPONSE;
		}

		// Random response chosen from the list of responses is stored in a
		// variable.
		String responseToUse = selectResponse(rand, responses);

		// loop through each response.
		for (int j = 0; j < responses.size() + 1; ++j) {

			// <1>,<2> etc... is stored in a variable.
			String replaceSymbol = "<" + (j + 1) + ">";

			// if it is in the current response.
			if (responseToUse.contains(replaceSymbol)) {

				// then we find the corresponding unmatchedWords phrase and swap
				// its pronoun words.
				String replaced = swapWords(keywords[j], Config.PRONOUN_MAP);

				// Then we use the result to replace the <n> in the chosen
				// response.
				responseToUse = responseToUse.replace(replaceSymbol, replaced);
			}

			// if it is not then we move onto the next response.
			else {
				continue;
			}
		}

		// The final response is returned.
		return responseToUse;
	}

	/**
	 * Creates a file with the given name, and fills that file line-by-line with
	 * the tracked conversation. Every line ends with a newline. Throws an
	 * IOException if a writing error occurs.
	 * 
	 * @param dialog
	 *            the complete conversation
	 * @param fileName
	 *            The file in which to write the conversation
	 * @throws IOException
	 */
	public static void saveDialog(ArrayList<String> dialog, String fileName)
			throws IOException {
		try {

			// file is created.
			File file = new File(fileName);

			// the PrintWriter will write into the file.
			PrintWriter pWrite = new PrintWriter(file);

			// it will write each line from the conversation into a new line.
			for (int i = 0; i < dialog.size(); i++) {
				pWrite.write(dialog.get(i) + "\n");
			}

			// we flush the PrintWriter
			pWrite.flush();

			// close the PrintWriter
			pWrite.close();
		}

		// if an exception is found then we throw the exception.
		catch (IOException e) {
			throw e;
		}
	}
}
