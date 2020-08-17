//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Sokoban Project (Main File) - Big Program 1
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

import java.util.Scanner;
import java.util.Random;

public class Sokoban {

	/**
	 * Prompts the user for a value by displaying prompt. Note: This method
	 * should not add a new line to the output of prompt.
	 *
	 * After prompting the user, the method will consume an entire line of input
	 * while reading an int. If the value read is between min and max
	 * (inclusive), that value is returned. Otherwise, "Invalid value."
	 * terminated by a new line is output to the console and the user is
	 * prompted again.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param prompt
	 *            The name of the value for which the user is prompted.
	 * @param min
	 *            The minimum acceptable int value (inclusive).
	 * @param max
	 *            The maximum acceptable int value (inclusive).
	 * @return Returns the value read from the user.
	 */
	public static int promptInt(Scanner sc, String prompt, int min, int max) {
		boolean value = false; //value for running the loop until an int is entered.
		int userValue = 0;
		max = Config.LEVELS.length - 1;
		do {
			prompt = ("Choose a level between 0 and " + max + ": "); // Prompt to print
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				userValue = sc.nextInt();
				if (userValue >= min && userValue <= max) {
					value = true;
				} else {
					System.out.println("Invalid value.");
				}
			} else {
				System.out.println("Invalid value.");
			}
			sc.nextLine(); // To clear line if it is not an integer input.
		} while (!value); // Continue looping while false.
		return userValue; //Valid level is returned
	}

	/**
	 * Prompts the user for a char value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input
	 * and return the first non-whitespace character converted to lower case.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in
	 * @param prompt
	 *            The user prompt.
	 * @return Returns the first non-whitespace character (in lower case) read
	 *         from the user. If there are no non-whitespace characters read,
	 *         the null character is returned.
	 */
	public static char promptChar(Scanner sc, String prompt1) {
		String prompt11 = "Play again? (y/n) "; //Prompt to print
		System.out.print(prompt11);
		String inputChar = sc.nextLine();
		// We need to take the first character after all white spaces and
		// Upper-Case letters are removed.
		inputChar = inputChar.trim().toLowerCase();
		char firstChar = inputChar.charAt(0);
		return firstChar; //Return the first letter of the word.

	}

	/**
	 * Prompts the user for a string value by displaying prompt. Note: This
	 * method should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input,
	 * remove any leading and trailing whitespace, and return the input
	 * converted to lower case.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in
	 * @param prompt
	 *            The user prompt.
	 * @return Returns the string entered by the user, converted to lower case
	 *         with leading and trailing whitespace removed.
	 */
	public static String promptString(Scanner sc, String prompt) {
		System.out.print(prompt); 
		//Reads input, removes white spaces and converts to lower-case.
		String stringInput = sc.nextLine();
		stringInput = stringInput.trim().toLowerCase();
		return stringInput; //Returns the string after modifying.
	}

	/**
	 * Initializes the game board to a given level. You can assume that the
	 * level at lvl has been successfully verified by the checkLevel method and
	 * that pos is an array of length 2.
	 *
	 * 1 - The game board should be created row-by-row. a - For each row, copy
	 * the values from the corresponding row in the 2-d array contained at index
	 * lvl in levels. b - When the worker is located, it's position should be
	 * recorded in the pos parameter. 2 - For each goal described in the array
	 * at index lvl of goals, convert the character at the goal coordinate to: -
	 * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if
	 * it contains a box - Config.GOAL_CHAR otherwise
	 * 
	 * @param lvl
	 *            The index of the level to load.
	 * @param levels
	 *            The array containing the levels.
	 * @param goals
	 *            The parallel array to levels, containing the goals for the
	 *            levels.
	 * @param pos
	 *            The starting pos of the worker. A length 2 array, where index
	 *            0 is the row and index 1 is the column.
	 * @return A two dimension array representing the initial configuration for
	 *         the given level.
	 */
	public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals,
			int[] pos) {
		char[][] tempArray = new char[levels[lvl].length][]; //Stores the level loaded in a new array.
		for (int i = 0; i < levels[lvl].length; ++i) {
			tempArray[i] = new char[levels[lvl][i].length];
			for (int j = 0; j < levels[lvl][i].length; ++j) {
				tempArray[i][j] = levels[lvl][i][j];
				if (levels[lvl][i][j] == Config.WORKER_CHAR) {
					// Updates pos with the position of the worker.
					pos[0] = i;
					pos[1] = j;
				}
			}
		}

		for (int i = 0; i < goals[lvl].length - 1; i = i + 2) {
			int row = goals[lvl][i];
			int col = goals[lvl][i + 1];
			if (tempArray[row][col] == Config.WORKER_CHAR) {
				tempArray[row][col] = Config.WORK_GOAL_CHAR;
			} else if (tempArray[row][col] == Config.BOX_CHAR) {
				tempArray[row][col] = Config.BOX_GOAL_CHAR;
			} else {
				tempArray[row][col] = Config.GOAL_CHAR;
			}
			// }
		}
		return tempArray; //Returns the initialised array
	}

	/**
	 * Prints out the game board.
	 * 
	 * 1 - Since the game board does not contain the outer walls, print out a
	 * sequence of Config.WALL_CHAR with a length equal to that of the first row
	 * of board, plus the outer wall to the left and the right. 2 - For each row
	 * in board, print out a Config.WALL_CHAR, followed by the contents of the
	 * row, followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence of
	 * Config.WALL_CHAR with a length equal to that of the last row of board,
	 * plus the outer wall to the left and the right.
	 *
	 * Note: each row printed out should be terminated by a new line.
	 *
	 * @param board
	 *            The board to print.
	 */
	public static void printBoard(char[][] board) {
		int maxCol = 0;
		//Constants for loops
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		for (k = 0; k < board[0].length + 2; ++k) {
			System.out.print(Config.WALL_CHAR);
		}
		System.out.println();
		for (i = 0; i < board.length; ++i) {
			System.out.print(Config.WALL_CHAR);
			for (j = 0; j < board[i].length; ++j) {
				System.out.print(board[i][j]);
				maxCol = j;
			}
			System.out.println(Config.WALL_CHAR);
		}
		System.out.print(Config.WALL_CHAR);
		for (l = 0; l < maxCol + 1; ++l) {
			System.out.print(Config.WALL_CHAR);
		}
		System.out.println(Config.WALL_CHAR);
	}

	/**
	 * Runs a given level through some basic sanity checks.
	 *
	 * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl
	 * is a valid index in levels, that the 2-d array at index lvl exists and
	 * that it contains at least 1 row. 3 - lvl is a valid index in goals, the
	 * 1-d array at index lvl exists and that it contains an even number of
	 * cells. 4 - the number of boxes is more than 0. 5 - the number of boxes
	 * equals the number of goals. 6 - the coordinate of each goal is valid for
	 * the given lvl and does not correspond to a wall cell. 7 - the number of
	 * workers is exactly 1. 8 - check for duplicate goals.
	 *
	 * @param lvl
	 *            The index of the level to load.
	 * @param levels
	 *            The array containing the levels.
	 * @param goals
	 *            The parallel array to levels, containg the goals for the
	 *            levels.
	 * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test
	 *         2 fails: -1 - Test 3 fails: -2 - Test 4 fails: -3 - Test 5 fails:
	 *         -4 - Test 6 fails: -5 - Test 7 fails: -6 - Test 8 fails: -7
	 * 
	 */
	public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {

		// Test 1 - The code will check to see if lvl is less than or equal to 0
		// (value is only returned if the test is failed).
		if (lvl < 0) {
			return 0;
		}
		// Test 2 - checks to see if lvl is a valid index that is entered.
		// Then it will check to see if the cells are null or not (exists)
		// and lastly it will check to see if atleast 1 row is present.
		if (lvl >= levels.length || levels[lvl] == null
				|| levels[lvl].length <= 0) {
			return -1;
		}
		// Test 3 - checks to see if goals is not null
		// Then it checks if lvl is within the goals array.
		// It checks if lvl for the goal array exists (not null).
		// It checks to see if the number of goals is even.
		if (goals == null || goals.length <= lvl || goals[lvl] == null
				|| goals[lvl].length < 2 || goals[lvl].length % 2 == 1) {
			return -2;
		}

		// Test 4 - It uses to loop to go through the board and count the number
		// of goals. It should find at least 1 goal.
		int numBoxes = 0;
		for (int i = 0; i < levels[lvl].length; ++i) {
			for (int j = 0; j < levels[lvl][i].length; ++j) {
				if (levels[lvl][i][j] == Config.BOX_CHAR) {
					numBoxes += 1;
				}
			}
		}
		if (numBoxes < 1) {
			return -3;
		}

		// Test 5 - Uses the box counter to check if it is equal to the number
		// of goals.
		if (numBoxes != (goals[lvl].length / 2)) {
			return -4;
		}

		// Test 6 - Checks that, for a loaded level, the goal coordinates valid,
		// and that it does not correspond to a wall position,.
		for (int i = 0; i < goals[lvl].length - 1; i = i + 2) {
			if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.WALL_CHAR) {
				return -5;
			}
		}

		// Test 7 - Loops through every index on the board to count the number
		// of workers. If it is not 1, it will fail the test.
		int numWorkers = 0;
		for (int i = 0; i < levels[lvl].length; ++i) {
			for (int j = 0; j < levels[lvl][i].length; ++j) {
				if (levels[lvl][i][j] == Config.WORKER_CHAR) {
					numWorkers += 1;
				}
			}
		}
		if (numWorkers < 1 || numWorkers > 1) {
			return -6;
		}

		// Test 8 - The code will iterate through to see if there are goals that
		// have been duplicated on the game map.
		for (int i1 = 0; i1 < goals[lvl].length - 1; i1 += 2) {
			for (int j1 = i1 + 2; j1 < goals[lvl].length - 1; j1 += 2) {
				if (goals[lvl][i1] == goals[lvl][j1]
						&& goals[lvl][i1 + 1] == goals[lvl][j1 + 1]) {
					return -7;
				}
			}
		}

		return 1;
	}

	/**
	 * This method builds an int array with 2 cells, representing a movement
	 * vector, based on the String parameter.
	 *
	 * The rules to create the length 2 int array are as follows: - The 1st
	 * character of the String represents the direction. - The remaining
	 * characters (if there are any) are interpreted as integer and represent
	 * the magnitude or the number of steps to take.
	 *
	 * The cell at index 0 represents movement in the rows. Hence, a negative
	 * value represents moving up the rows and a positive value represents
	 * moving down the rows.
	 *
	 * The cell at index 1 represents movement in the columns. Hence, a negative
	 * value represents moving left in the columns and a positive value
	 * represents moving right in the columns.
	 *
	 * If the first character of moveStr does not match on of Config.UP_CHAR,
	 * Config.DOWN_CHAR, Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an
	 * array with 0 in both cells.
	 *
	 * If there are no characters after the first character of moveStr or the
	 * characters cannot be interpreted as an int, set the magnitude of the
	 * movement to 1.
	 *
	 * Hint: Use Scanner to parse the magnitude.
	 *
	 * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would
	 * represent moving up by one character. - If the parameter moveStr is "65":
	 * An array {0, 5} would represent moving right by 5 characters.
	 *
	 * @param moveStr
	 *            The string to parse.
	 * @return The calculated movement vector as a 2 cell int array.
	 */
	public static int[] calcDelta(String moveStr) {
		int[] deltaArr = new int[2]; // Movement vector array
		char dirString; //The character the represents the direction.
		int magVal; // The value of how many steps to move (magnitude).
		if (moveStr.length() > 1) {
			dirString = moveStr.charAt(0);
			String remainStr = moveStr.substring(1, moveStr.length()); //Remaining characters in the string.
			magVal = Integer.parseInt(remainStr); //Convert to integer to use as magnitude.

		} else if (moveStr.length() == 1) {
			dirString = moveStr.charAt(0);
			magVal = 1; //We know that no other characters will be present after the direction so it will move by 1 (by default).
		} else {
			dirString = '0';
			magVal = 0;
		}

		// The conditions to set movement for the different directions
		if (dirString == Config.UP_CHAR) {
			deltaArr[0] = -magVal;
			deltaArr[1] = 0;
		} else if (dirString == Config.DOWN_CHAR) {
			deltaArr[0] = 1 * magVal;
			deltaArr[1] = 0;
		} else if (dirString == Config.LEFT_CHAR) {
			deltaArr[0] = 0;
			deltaArr[1] = -1 * magVal;
		} else if (dirString == Config.RIGHT_CHAR) {
			deltaArr[0] = 0;
			deltaArr[1] = magVal;
		} else {
			deltaArr[0] = 0;
			deltaArr[1] = 0;
		}
		return deltaArr; //Returns the calculated movement array.
	}

	/**
	 * This method checks that moving from one position to another position is a
	 * valid move.
	 *
	 * To validate the move, the method should (in order) check: 1 - that pos is
	 * valid. 2 - that the character at pos in board is in the valid array. 3 -
	 * that the delta is valid. 4 - that the new position is valid and not a
	 * wall character. 5 - that the new position is not a box character For what
	 * makes each test invalid, see the return details below.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to move from. A length 2 array, where index 0 is
	 *            the row and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the
	 *            change in row and index 1 is the change in column.
	 * @param valid
	 *            A character array containing the valid characters for the cell
	 *            at pos.
	 * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not
	 *         length 2, or not on the board. -2 : if the character at pos is
	 *         not valid (not in the valid array). -3 : if delta is null or not
	 *         length 2. -4 : if the new position is off the board or a wall
	 *         character -5 : if the new position is a box character
	 */
	public static int checkDelta(char[][] board, int[] pos, int[] delta,
			char[] valid) {
		boolean isTrue = false;
		// Test for 1
		// Check to see if the position is null, the array is not a length of 2,
		// or it exceeds the board limits
		if ((pos == null) || (pos.length != 2) || (pos[0] > board.length - 1)
				|| (pos[0] < 0) || (pos[1] > board[pos[0]].length - 1)
				|| pos[1] < 0) {
			return -1;
		}

		// Test for 2
		// The board character at the position is not in the valid array
		for (int i = 0; i < valid.length; ++i) {
			if (board[pos[0]][pos[1]] == valid[i]) {
				isTrue = true;
			}
		}
		if (!isTrue) {
			return -2;
		}

		// Test for 3
		// Checks to see if delta is null or not, or if the length of delta is
		// not 2.
		if ((delta == null) || (delta.length != 2)) {
			return -3;
		}

		// Test for 4
		// Checks to see if the position after moving is on the board or if it
		// is a wall character.
		int[] newPos = new int[2];
		newPos[0] = pos[0] + delta[0];
		newPos[1] = pos[1] + delta[1];

		if ((newPos[0] < 0) || (newPos[1] < 0)
				|| ((newPos[0]) > board.length - 1)
				|| (newPos[1] > board[newPos[0]].length - 1)) {
			return -4;
		}
		if (board[newPos[0]][newPos[1]] == Config.WALL_CHAR) {
			return -4;
		}

		// Test for 5
		// Checks to see if the position after moving is a box character (and
		// box can be of 2 types so we are checking for both).
		if ((board[newPos[0]][newPos[1]] == Config.BOX_CHAR)
				|| board[newPos[0]][newPos[1]] == Config.BOX_GOAL_CHAR) {
			return -5;
		}

		// If all tests are passed then 1 is returned.
		return 1;
	}

	/**
	 * Changes a character on the board to one of two characters (opt1 or opt2),
	 * depending on the value of the cell.
	 *
	 * Check the cell at position pos. If the character is val, change it to
	 * opt1. Otherwise, change it to opt2.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the
	 *            row and index 1 is the column.
	 * @param val
	 *            The value to check for in the board.
	 * @param opt1
	 *            The character to change to if the value is val.
	 * @param opt2
	 *            The character to change to if the value is not val.
	 */
	public static void togglePos(char[][] board, int[] pos, char val,
			char opt1, char opt2) {

		if (board[pos[0]][pos[1]] == val) { //If position on board is the value,
			board[pos[0]][pos[1]] = opt1;	//that position is changed to opt1.
		} else {
			board[pos[0]][pos[1]] = opt2;	//If not, then it is changed to opt2.
		}
	}

	/**
	 * Moves a box on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid.
	 * Recall that there are 2 characters that can represent a box. Step 2: Use
	 * your togglePos method to correctly change the character at the new
	 * position to the appropriate box character. Step 3: Again use your
	 * togglePos method to correctly change the character at pos to the the
	 * appropriate character without a box.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the
	 *            row and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the
	 *            change in row and index 1 is the change in column.
	 * @return The return value of checkDelta if less than 1. Otherwise 1.
	 */
	public static int shiftBox(char[][] board, int[] pos, int[] delta) {
		char[] valid = { Config.BOX_CHAR, Config.BOX_GOAL_CHAR, }; //Declaring array with valid characters.
		int[] newPos = new int[2];	//Declaring the new position after movement.
		int result;	//Return value from checkDelta.
		if ((result = Sokoban.checkDelta(board, pos, delta, valid)) < 1) { //If checkDelta doesn't return a valid move.
			return result;
		}
		newPos[0] = pos[0] + delta[0];
		newPos[1] = pos[1] + delta[1];
		int resultVal = Sokoban.checkDelta(board, pos, delta, valid); //Result from checkDelta
		if (resultVal == 1) {	//If checkDelta returns a valid move.
			//Make changes
			Sokoban.togglePos(board, newPos, Config.GOAL_CHAR,
					Config.BOX_GOAL_CHAR, Config.BOX_CHAR);
			Sokoban.togglePos(board, pos, Config.BOX_GOAL_CHAR,
					Config.GOAL_CHAR, Config.EMPTY_CHAR);
		}
		return 1;
	}

	/**
	 * Processes a move of the worker step-by-step.
	 *
	 * Go through the delta step-by-step, calling doMove for each step. That is,
	 * if the delta is {0, -3}, your method should call doMove three times with
	 * an argument of {0, -1} for the delta parameter of doMove. Or, if the
	 * delta is {6, 0}, it would call the doMove six times with an argument of
	 * {1, 0} for the delta parameter of the doMove method.
	 *
	 * During the processing of the move, if ever a call to doMove returns a
	 * value less than 1, your method should stop processing and return that
	 * value.
	 *
	 * Note: You can assume that one of the cells of delta will be 0.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the
	 *            row and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the
	 *            change in row and index 1 is the change in column.
	 * @return If both of the cells of delta are 0, return 0. If the call to
	 *         doMove returns a value less than 1, return that value. Otherwise,
	 *         return 1.
	 */
	public static int processMove(char[][] board, int[] pos, int[] delta) {
		int numTimes; //Counter for how many loops to do
		int doMoveRes;	//Result from doMove
		int counter = 0; //Counter for numTimes to be compared with
		//4 possible moves depending on delta
		int[] moveOpt1 = { 0, 1 };
		int[] moveOpt2 = { 0, -1 };
		int[] moveOpt3 = { 1, 0 };
		int[] moveOpt4 = { -1, 0 };

		if ((delta[0] == 0) && (delta[1] == 0)) { //If both indexes are 0.
			return 0;
		}
		if (delta[0] == 0) {
			numTimes = delta[1]; //takes the value of how many times to be done
			if (delta[1] > 0) {	//If second index is a positive number
				do {
					doMoveRes = Sokoban.doMove(board, pos, moveOpt1); //doMove called with {0,1}
					if (doMoveRes < 1) {
						return doMoveRes;
					}
					counter++;
				} while (counter < numTimes); 
			} else { //If second index is a negative number
				do {
					doMoveRes = Sokoban.doMove(board, pos, moveOpt2);//doMove called with {0,-1}
					if (doMoveRes < 1) {
						return doMoveRes;
					}
					counter++;
				} while (counter < Math.abs(numTimes)); //numTimes is negative so we need to take the positive value of it.
			}
		} else if (delta[1] == 0) {
			numTimes = delta[0];
			if (delta[0] > 0) {
				do {
					doMoveRes = Sokoban.doMove(board, pos, moveOpt3); //doMove called with {1,0}
					if (doMoveRes < 1) {
						return doMoveRes;
					}
					counter++;
				} while (counter < numTimes);
			} else {
				do {
					doMoveRes = Sokoban.doMove(board, pos, moveOpt4); //doMove called with {-1,0}
					if (doMoveRes < 1) {
						return doMoveRes;
					}
					counter++;
				} while (counter < Math.abs(numTimes)); //numTimes is negative so we need to take the positive value of it.
			}
		}
		return 1;
	}

	/**
	 * Moves the worker on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid.
	 * Recall that there are 2 characters that can represent the worker. Step 2:
	 * If checkDelta returns -5, use your shiftBox method to move the box by
	 * delta before moving the worker. Step 3: Use your togglePos method to
	 * correctly change the character at the new position to the appropriate
	 * worker character. Step 4: Again use your togglePos method to correctly
	 * change the character at pos to the the appropriate character without a
	 * worker. Step 5: Update the position of the worker in pos.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the
	 *            row and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the
	 *            change in row and index 1 is the change in column.
	 * @return If checkDelta returns a value less than 1 that is not -5, return
	 *         that value. If checkDelta returns -5 and shiftBox returns a value
	 *         less than 0, return 0. Otherwise, return 1.
	 */
	public static int doMove(char[][] board, int[] pos, int[] delta) {
		char[] valid = { Config.WORK_GOAL_CHAR, Config.WORKER_CHAR, }; //Declaring possible valid characters.
		int[] newPos = new int[2]; //Array for new position after movement
		int resultVal = Sokoban.checkDelta(board, pos, delta, valid); //return value from checkDelta
		if ((resultVal < 1) && (resultVal != -5)) {
			return resultVal;
		}
		//Making the new position
		newPos[0] = pos[0] + delta[0];
		newPos[1] = pos[1] + delta[1];
		if ((resultVal == -5) && (Sokoban.shiftBox(board, newPos, delta) < 1)) {
			return 0;

		}
		//changing the characters in positions
		Sokoban.togglePos(board, newPos, Config.GOAL_CHAR,
				Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);
		Sokoban.togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR,
				Config.EMPTY_CHAR);
		//modifying position after move
		pos[0] = newPos[0];
		pos[1] = newPos[1];
		return 1;
	}

	/**
	 * Checks all the cells in board and ensures that there are no goals that
	 * are not covered by boxes.
	 *
	 * @param board
	 *            The current board.
	 * @return true if all the goals are covered by boxes. Otherwise, false.
	 */
	public static boolean checkWin(char[][] board) {
		boolean result = true;
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[i].length; ++j) {
				// Need to check if there is a goal character or a worker on goal character.
				// We check for worker on goal character because the worker can be on the only goal and the game will end.
				if ((board[i][j] == Config.GOAL_CHAR) || (board[i][j] == Config.WORK_GOAL_CHAR)) {
					result = false;
				}
			}
		}

		return result;
	}

	/**
	 * This is the main method for the Sokoban game. It consists of the main
	 * game and play again loops with calls to the various supporting methods.
	 * The details of the main method for each milestone can be found in the BP1
	 * - Sokoban write-up on the CS 200 webpage:
	 * https://cs200-www.cs.wisc.edu/wp/programs/
	 *
	 * For all milestones, you will need to create a Scanner object to read from
	 * System.in that you will pass to the helper methods.
	 *
	 * For milestone 3, you will need to create a Random object using
	 * Config.SEED as the seed. This object should be create at the beginning of
	 * the method, outside of any loops.
	 *
	 * @param args
	 *            Unused.
	 */
	public static void main(String[] args) {
		int min = -1; // Min level
		int maxLvl = 0; // Max level
		int sokobanLvl; // Return value from checkLevel
		char pChar; // Return value from promptChar
		String prmptStrRet = null; // Return value from promptString
		// Prompts that will be taken as input
		String prompt = null;
		String prompt1 = null;

		char[][] board; // Game board
		int[] sokobanArr = new int[2]; // Worker Position
		Scanner sc = new Scanner(System.in);
		Random rand = new Random(Config.SEED); // Using the Seed in config to
												// load the levels
		boolean checkWin = false; // The value that will be used to keep the
									// loop going while the game is not won.
		int xNum = 0; // Counter for number of moves made
		int[] calcDeltaRet; // Return value of calcDelta

		System.out.println("Welcome to Sokoban!");
		// Game Loop
		do {
			sokobanLvl = promptInt(sc, prompt, min, maxLvl);
			if (sokobanLvl == -1) {
				int randLevel = rand.nextInt(Config.LEVELS.length); //Random level loaded based on seed
				//If random level fails checkLevel + outputs to be done
				if (Sokoban.checkLevel(randLevel, Config.LEVELS, Config.GOALS) != 1) {
					System.out.println("Error loading level!");
					switch (randLevel) {
					case 0:
						System.out.println("Level lvl must be 0 or greater!");
						break;
					case -1:
						System.out.println("Error with Config.LEVELS");
						break;
					case -2:
						System.out.println("Error with Config.GOALS");
						break;
					case -3:
						System.out
								.println("Level lvl does not contain any boxes.");
						break;
					case -4:
						System.out
								.println("Level lvl does not have the same number of boxes as goals.");
						break;
					case -5:
						System.out
								.println("Level lvl has a goal location that is a wall.");
						break;
					case -6:
						System.out
								.println("Level lvl has 0 or more than 1 worker(s).");
						break;
					case -7:
						System.out
								.println("Level lvl contains duplicate goals.");
						break;
					default:
						System.out.println("Unknown Error");
						break;
					}
				}
				//If random level passed checkLevel
				board = initBoard(randLevel, Config.LEVELS, Config.GOALS,
						sokobanArr);
				System.out.println("Sokoban Level " + randLevel);
			}

			else { 
				if (Sokoban.checkLevel(sokobanLvl, Config.LEVELS, Config.GOALS) != 1) { //If user input level fails checkLevel
					System.out.println("Error loading level!");
					switch (sokobanLvl) {
					case 0:
						System.out.println("Level lvl must be 0 or greater!");
						break;
					case -1:
						System.out.println("Error with Config.LEVELS");
						break;
					case -2:
						System.out.println("Error with Config.GOALS");
						break;
					case -3:
						System.out
								.println("Level lvl does not contain any boxes.");
						break;
					case -4:
						System.out
								.println("Level lvl does not have the same number of boxes as goals.");
						break;
					case -5:
						System.out
								.println("Level lvl has a goal location that is a wall.");
						break;
					case -6:
						System.out
								.println("Level lvl has 0 or more than 1 worker(s).");
						break;
					case -7:
						System.out
								.println("Level lvl contains duplicate goals.");
						break;
					default:
						System.out.println("Unknown Error");
						break;
					}
				}
				//If user input level passed checkLevel
				board = initBoard(sokobanLvl, Config.LEVELS, Config.GOALS,
						sokobanArr);
				System.out.println("Sokoban Level " + sokobanLvl);
			}
			// Loop for each move.
			do {
				printBoard(board);
				prmptStrRet = promptString(sc, ": ");
				if (prmptStrRet.charAt(0) == Config.QUIT_CHAR) { //If character the is quit character
					break;
				} else if (prmptStrRet.equals("")) {
					continue;
				} else {
					calcDeltaRet = calcDelta(prmptStrRet);
					if (!((calcDeltaRet[0] == 0) && (calcDeltaRet[1] == 0))) { //If index is not {0,0}
						xNum += Math.abs(calcDeltaRet[0] + calcDeltaRet[1]); //Counter is incremented by positive values of number of movements
						Sokoban.processMove(board, sokobanArr, calcDeltaRet);
						if (Sokoban.checkWin(board)) { //Check for game win.
							checkWin = true;
							break;
						}
					}
				}
			} while ((!(prmptStrRet.charAt(0) == Config.QUIT_CHAR))
					|| (prmptStrRet.equalsIgnoreCase("")));
			if (checkWin) {
				System.out.println("Congratulations! You won in " + xNum
						+ " moves!");
				Sokoban.printBoard(board);
			}
			pChar = promptChar(sc, prompt1);
			xNum = 0; //Re-initialize counter for next game
		} while (pChar == 'y');
		System.out.println("Thanks for playing!");
	}
}