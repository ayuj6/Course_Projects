//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Sokoban Project (Test Sokoban file) - Big Program 1
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

/**
 * This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * This class contains a few methods for testing methods in the Sokoban class as
 * they are developed. These methods are all private as they are only intended
 * for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests
	 * when you are ready for them to run.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {
		// Milestone 1
		testCheckLevel();
		// Milestone 2
		testInitBoard();
		testCheckWin();
		testCalcDelta();
		testCheckDelta();
		// Milestone 3
		testTogglePos();
		testShiftBox();
		testDoMove();
		testProcessMove();
	}

	private static void testCheckLevel() {
		int numTests = 7;
		int passed = numTests;
		int res; //Result from checkLevel
		//Different boards to check for the unique tests.
		final char[][][] testLevels = {
				{
						// {' ', ' ', ' '},
						// {' ', '=', ' '},
						// {'@', ' ', ' '},
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
						{ Config.WORKER_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR } },
				{
						// {' ', '#', ' ', '#', ' ', ' '},
						// {' ', '#', '=', '#', '#', '#'},
						// {'#', '#', ' ', '#', ' ', ' '},
						// {' ', ' ', ' ', ' ', ' ', ' '},
						// {' ', ' ', ' ', ' ', '=', ' '},
						// {'@', ' ', ' ', '#', '#', ' '},
						{ Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR },
						{ Config.WALL_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.BOX_CHAR, Config.EMPTY_CHAR },
						{ Config.WORKER_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.EMPTY_CHAR }, },
				{
						// {'#', '#', '#', '#', '#', '#', '#', '#', ' '},
						// {' ', '=', ' ', '#', ' ', '#', ' ', ' ', ' '},
						// {' ', ' ', ' ', '#', ' ', '#', ' ', '=', ' '},
						// {'#', '#', ' ', '#', '#', '#', ' ', ' ', '#'},
						// {'#', '#', ' ', '#', '#', '#', ' ', ' ', '#'},
						// {' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', '#',
						// '#'},
						// {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						// '#'},
						// {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '=',
						// '#'},
						// {'@', '#', '#', '#', '#', '#', '#', '#', ' ', ' ',
						// ' '},
						{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.BOX_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.BOX_CHAR,
								Config.EMPTY_CHAR },
						{ Config.WALL_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.WALL_CHAR },
						{ Config.WALL_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.EMPTY_CHAR,
								Config.WALL_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.WALL_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR, Config.BOX_CHAR,
								Config.WALL_CHAR },
						{ Config.WORKER_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.WALL_CHAR, Config.WALL_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR }, },
				{
						// {' ', ' ', ' '},
						// {' ', '=', ' ', ' '}
						// {' ', ' ', ' '},
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.BOX_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR } },
				{
						// {' ', ' ', ' '},
						// {' ', '=', '='},
						// {'@', ' ', ' '},
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.WALL_CHAR },
						{ Config.WORKER_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR } },
				{
						// {' ', ' ', ' '},
						// {' ', ' ', ' '},
						// {'@', ' ', ' '},
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR },
						{ Config.WORKER_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR } },
				{
						// {' ', ' ', ' '},
						// {' ', '=', ' ', ' '}
						// {'@', '@', ' '},
						{ Config.EMPTY_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR },
						{ Config.EMPTY_CHAR, Config.BOX_CHAR,
								Config.EMPTY_CHAR, Config.EMPTY_CHAR },
						{ Config.WORKER_CHAR, Config.EMPTY_CHAR,
								Config.EMPTY_CHAR } }, };

		final int[][] testGoals = { { 2 }, { 1, 1 }, { 8, 0, 0, 0, 10, 8 },
				{ 2, 1 }, { 2, 1 }, { 2, 1 }, { 1, 1, 1, 2 } };

		//Each test is to test the different return values from chedckLevel
		// Test 1
		if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
			System.out
					.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned "
							+ res);
			passed--;
		}

		// Test 2
		char[][][] lvl = new char[2][][];
		if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
			System.out
					.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned "
							+ res);
			passed--;
		}

		// Test 3

		if ((res = Sokoban.checkLevel(0, testLevels, testGoals)) != -2) {
			System.out
					.println("FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned "
							+ res);
			passed--;
		}

		// Test 4
		if ((res = Sokoban.checkLevel(5, testLevels, testGoals)) != -3) {
			System.out
					.println("FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned "
							+ res);
			passed--;
		}

		// Test 5
		if ((res = Sokoban.checkLevel(1, testLevels, testGoals)) != -4) {
			System.out
					.println("FAILED: Sokoban.checkLevel Test 5. Expected -4, but value returned "
							+ res);
			passed--;
		}

		// Test 6
		if ((res = Sokoban.checkLevel(2, testLevels, testGoals)) != -5) {
			System.out
					.println("FAILED: Sokoban.checkLevel Test 6. Expected -5, but value returned "
							+ res);
			passed--;
		}
		// Test 7
		if ((res = Sokoban.checkLevel(3, testLevels, testGoals)) != -6) {
			System.out
					.println("FAILED: Sokoban.checkLevel Test 7. Expected -6, but value returned "
							+ res);
			passed--;
		}

		System.out.println("testCheckLevel: Passed " + passed + " of "
				+ numTests + " tests.");
	}

	/**
	 * Returns true if the arrays are the same size and have the same contents.
	 */
	private static boolean compBoards(char[][] a, char[][] b) {
		if (a == null || b == null)
			return false;
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++) {
			if (!Arrays.equals(a[i], b[i])) {
				return false;
			}
		}
		return true;
	}

	private static void testInitBoard() {
		int numTests = 2;
		int passed = numTests;

		// Test 1
		int[] pTest1 = new int[2];
		char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS,
				pTest1);
		if (!Arrays.equals(pTest1, new int[] { 4, 4 })) {
			System.out
					.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
							+ Arrays.toString(pTest1));
			passed--;
		} else {
			char[][] bCompTest1 = new char[][] {
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR },
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR },
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
							Config.BOX_CHAR, Config.EMPTY_CHAR },
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR },
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.WORKER_CHAR } };
			if (!compBoards(bTest1, bCompTest1)) {
				System.out
						.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
				System.out.println("Generated:");
				Sokoban.printBoard(bTest1);
				System.out.println("Expected:");
				Sokoban.printBoard(bCompTest1);
				passed--;
			}
		}
		// End of Test 1


		// Test 2
		int[] pTest2 = new int[2];
		char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS,
				pTest2); //Create now array with level 1 from Config.LEVELS.
		if (!Arrays.equals(pTest2, new int[] { 7, 10 })) {
			System.out
					.println("FAILED: Sokoban.initBoard Test 2. Expected initial position: {7, 10} , but value after call "
							+ Arrays.toString(pTest2));
			passed--;
		} else {
			char[][] bCompTest2 = new char[][] {
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR },
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.BOX_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR },
					{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.BOX_CHAR },
					{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.BOX_CHAR,
							Config.EMPTY_CHAR, Config.BOX_CHAR,
							Config.EMPTY_CHAR },
					{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.EMPTY_CHAR, Config.WALL_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR },
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.EMPTY_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.GOAL_CHAR, Config.GOAL_CHAR },
					{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.BOX_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.GOAL_CHAR, Config.GOAL_CHAR },
					{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.WORKER_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.GOAL_CHAR, Config.GOAL_CHAR },
					{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.EMPTY_CHAR, Config.EMPTY_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR,
							Config.WALL_CHAR, Config.WALL_CHAR } };
			if (compBoards(bTest1, bCompTest2)) {
				System.out
						.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
				System.out.println("Generated:");
				Sokoban.printBoard(bTest2);
				System.out.println("Expected:");
				Sokoban.printBoard(bCompTest2);
				passed--;
			}
		}
		// End of Test 2

		System.out.println("testInitBoard: Passed " + passed + " of "
				+ numTests + " tests.");
	}

	private static void testCheckWin() {
		int numTests = 1;
		int winPass = numTests;

		// Test 1
		if (Sokoban.checkWin(Config.LEVELS[0]) == true) {
			System.out.println("FAILED: Sokoban.checkWin Test 1.");
			winPass--;
		}
		// End of Test 1

		System.out.println("testCheckWin: Passed " + winPass + " of "
				+ numTests + " tests.");
	}

	private static void testCalcDelta() {
		int numTests = 1;
		int calcDeltaPass = numTests;
		
		// Test 1
		String testStr = "81";
		int[] arr1 = new int[2];
		int[] deltaArray = { -1, 0 };
		arr1 = Sokoban.calcDelta(testStr);
		if ((arr1[0] != deltaArray[0]) || (arr1[1] != deltaArray[1])) {
			System.out
					.println("FAILED: Sokoban.calcDelta Test 1. Expected {-1,0}, but value returned {"
							+ arr1[0] + "," + arr1[1] + "}.");
			calcDeltaPass--;
		}

		// End of Test 1

		System.out.println("testCalcDelta: Passed " + calcDeltaPass + " of "
				+ numTests + " tests.");
	}

	private static void testCheckDelta() {
		int numTests = 1;
		int checkDeltaPass = numTests;
		int deltaRes; //Result from checkDelta
		
		// Test 1
		int[] deltArr = { 1, 2 };
		int[] pos1 = { 4, 4 };
		char[] valid1 = new char[2];
		char[][] deltaTest1 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
						Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if ((deltaRes = Sokoban.checkDelta(deltaTest1, pos1, deltArr, valid1)) != -2) {
			System.out
					.println("FAILED: Sokoban.checkDelta Test 1. Expected -2, but value returned "
							+ deltaRes);
			checkDeltaPass--;
		}
		// End of Test 1

		System.out.println("testCheckDelta: Passed " + checkDeltaPass + " of "
				+ numTests + " tests.");
	}

	private static void testTogglePos() {
		int numTests = 1;
		int togglePosPass = numTests;
		
		// Test 1
		//Test Board along with test Values and test Characters.
		char[][] testBoard = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
						Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		int[] togglePos = { 2, 3 };
		char testVal = Config.GOAL_CHAR;
		char testOpt1 = Config.BOX_GOAL_CHAR;
		char testOpt2 = Config.BOX_CHAR;
		Sokoban.togglePos(testBoard, togglePos, testVal, testOpt1, testOpt2);
		if (testBoard[togglePos[0]][togglePos[1]] == testOpt1) {
			System.out
					.println("FAILED: Sokoban.togglePos Test 1. Expected Config.BOX_CHAR, but value returned "
							+ testOpt1);
			togglePosPass--;
		}
		// End of Test 1

		System.out.println("testTogglePos: Passed " + togglePosPass + " of "
				+ numTests + " tests.");
	}

	private static void testShiftBox() {
		int numTests = 1;
		int shiftBoxPass = numTests;
		int resultVal; //Result from shiftBox
		// Test 1
		//Test board with test values
		char[][] testBoardBox = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
						Config.BOX_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		int[] posShiftBox = { 2, 3 };
		int[] deltaVal = { 0, 1 };
		resultVal = Sokoban.shiftBox(testBoardBox, posShiftBox, deltaVal);
		if (resultVal != -4) {
			System.out
					.println("FAILED: Sokoban.shiftBox Test 1. Expected -4, but value returned "
							+ resultVal);
			shiftBoxPass--;
		}
		// End of Test 1
		System.out.println("testShiftBox: Passed " + shiftBoxPass + " of "
				+ numTests + " tests.");
	}

	private static void testDoMove() {
		int numTests = 1;
		int doMovePass = numTests;
		int resultVal; //Result from doMove
		// Test 1
		//Test board and values
		char[][] testBoardMove = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
						Config.BOX_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		int[] posMove = { 3, 4 };
		int[] deltaVal = { -1, 0 };
		resultVal = Sokoban.doMove(testBoardMove, posMove, deltaVal);
		if (resultVal != -2) {
			System.out
					.println("FAILED: Sokoban.doMove Test 1. Expected -2, but value returned "
							+ resultVal);
			doMovePass--;
		}
		// End of Test 1
		System.out.println("testDoMove: Passed " + doMovePass + " of "
				+ numTests + " tests.");

	}

	private static void testProcessMove() {
		int numTests = 1;
		int processMovePass = numTests;
		int resultVal; //Result from processMove
		// Test 1
		//Test Board and values
		char[][] testBoardProcess = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
						Config.BOX_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		int[] posProcess = { 3, 4 };
		int[] deltaVal = { 0, 0 };
		resultVal = Sokoban.processMove(testBoardProcess, posProcess, deltaVal);
		if (resultVal != 0) {
			System.out
					.println("FAILED: Sokoban.processMove Test 1. Expected 0, but value returned "
							+ resultVal);
			processMovePass--;
		}
		// End of Test 1
		System.out.println("testProcessMove: Passed " + processMovePass
				+ " of " + numTests + " tests.");
	}
}