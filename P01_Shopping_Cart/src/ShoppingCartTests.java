// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Shopping Cart App Test
// Files: ShoppingCartTests.java
// Course: CS300 - Spring 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Gary Dahl
//
// ////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Kenneth Mui
// Partner Email: klmui@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
// /////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains a few methods for testing methods in the ShoppingCart class as they are
 * developed and to make sure that the classes work as intended.
 * 
 * @author Ayuj Prasad
 * @author Kenneth Mui
 *
 */

public class ShoppingCartTests {

  /**
   * Checks whether the total number of items within the cart is incremented after adding one item
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testCountIncrementedAfterAddingOnlyOneItem() {

    // boolean local variable evaluated to true if this test passed, false otherwise
    boolean testPassed = true;

    // shopping cart
    String[] cart = new String[20];

    // number of items present in the cart (initially the cart is empty)
    int count = 0;

    // Add an item of index 3 to the cart
    count = ShoppingCart.add(3, cart, count);

    // Check that count was incremented
    if (count != 1) {
      System.out.println("Problem detected: After adding only one item to the cart, "
          + "the cart count should be incremented. But, it was not the case.");
      testPassed = false;
    }

    // return result of the test
    return testPassed;
  }


  /**
   * Checks whether add and OccurrencesOf return the correct output when only one item is added to
   * the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddAndOccurrencesOfForOnlyOneItem() {

    // evaluated to true if test passed without problems, false otherwise
    boolean testPassed = true;

    // define the shopping cart as an oversize array of elements of type String we can set an
    // arbitrary capacity for the cart - for instance 10
    String[] cart = new String[10];

    // number of items present in the cart (initially the cart is empty)
    int count = 0;

    // check that OccurrencesOf returns 0 when called with an empty cart
    if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
      System.out.println("Problem detected: Tried calling OccurrencesOf() method when the cart is "
          + "empty. The result should be 0. But, it was not.");
      testPassed = false;
    }

    // add one item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count);

    // check that OccurrencesOf("Apples", cart, count) returns 1 after adding the item with key 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out.println("Problem detected: After adding only one item with key 0 to the cart, "
          + "OccurrencesOf was used to count how many of that item the cart contained."
          + "It should have returned 1. " + "But, it was not the case.");
      testPassed = false;
    }

    // return result of the test
    return testPassed;
  }

  /**
   * Checks that items can be added more than one time and are found
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testAddOccurrencesOfDuplicateItems() {

    // evaluated to true if test passed without problems, false otherwise
    boolean testPassed = true;

    // shopping cart
    String[] cart = new String[20];

    // number of items present in the cart (initially the cart is empty)
    int count = 0;

    // add one item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count);

    // add one item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count);

    // check that occurrencesOf returns 2 after adding 2 items of index 0 to cart
    if (ShoppingCart.occurrencesOf(0, cart, count) != 2) {
      System.out.println("Problem detected: After adding 2 items with key 0 to the cart, "
          + "OccurrencesOf was called to count how many of that item the cart contains."
          + "It should have returned 2. " + "But, it was not the case.");
      testPassed = false;
    }

    // return result of the test
    return testPassed;
  }

  /**
   * Checks that the correct output is returned when the user tries to add too much items to the
   * cart exceeding its capacity.
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testAddingTooMuchItems() {

    // evaluated to true if test passed without problems, false otherwise
    boolean testPassed = true;

    // shopping cart
    String[] cart = new String[20];

    // number of items present in the cart (initially the cart is empty)
    int count = 0;

    // loop for adding items of index 0 to cart until max capacity.
    for (int i = 0; i < 20; ++i) {
      count = ShoppingCart.add(0, cart, count);
    }

    // see if adding 21st item gives warning message (count is never incremented to exceed the max
    // capacity).
    if ((ShoppingCart.add(0, cart, count) != count)) {
      System.out.println("Problem detected: After adding 20 items with key 0 to the cart, "
          + "Adding another item to cart should have returned the warning message. "
          + "But, it was not the case.");
      testPassed = false;
    }

    // return result of the test
    return testPassed;
  }

  /**
   * Checks that when only one attempt to remove an item present in the cart is made, only one
   * occurrence of that item is removed from the cart.
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testRemoveOnlyOneOccurrenceOfItem() {

    // evaluated to true if test passed without problems, false otherwise
    boolean testPassed = true;

    // shopping cart
    String[] cart = new String[20];

    // number of items present in the cart (initially the cart is empty)
    int count = 0;

    // add one item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count);

    // add one item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count);

    // add one item of index 1 to the cart
    count = ShoppingCart.add(1, cart, count);

    // removing the first instance of Apple (item of index 0)
    count = ShoppingCart.remove("Apple", cart, count);

    // check to see if occurenceOf returns 1 an instance of the item was removed
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out
          .println("Problem detected: After adding 2 items with key 0 to the cart and 1 item with key 1, "
              + "We remove one item with key 0 and use OccurrencesOf to count how many of that item the cart contains."
              + "It should return 1. " + "But, it was not the case.");
      testPassed = false;
    }

    // return result of the test
    return testPassed;

  }

  /**
   * Checks that remove returns false when the user tries to remove an item not present within the
   * cart.
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testRemoveItemNotFoundInCart() {

    // evaluated to true if test passed without problems, false otherwise
    boolean testPassed = true;

    // shopping cart
    String[] cart = new String[20];

    // number of items present in the cart (initially the cart is empty)
    int count = 0;

    // add one item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count);

    // add one item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count);

    // add one item of index 0 to the cart
    count = ShoppingCart.add(1, cart, count);

    // check to see if the warning message is produced if we try to remove an item not present in
    // the cart (count does not change in this case)
    if (ShoppingCart.remove("Spinach", cart, count) != count) {
      System.out
          .println("Problem detected: After adding 2 items with key 0 to the cart and 1 item with key 1, "
              + "We remove one item with key 23 (not in cart) where remove() should have produced the warning message. "
              + "But, it was not the case.");
      testPassed = false;
    }

    // return result of the test
    return testPassed;

  }

  /**
   * Checks that the sub-total calculated from the items in the cart matches with the expected
   * price.
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testGetSubTotalPrice() {
    
    // evaluated to true if test passed without problems, false otherwise
    boolean testPassed = true;
    
    // number of items present in the cart
    int count = 3;
    
    // initializing shopping cart
    String[] cart = new String[count];
    
    // adding item of index 0 to cart
    cart[0] = ShoppingCart.MARKET_ITEMS[0][0];
    
    // adding item of index 0 to cart
    cart[1] = ShoppingCart.MARKET_ITEMS[0][0];
    
    // adding item of index 1 to cart
    cart[2] = ShoppingCart.MARKET_ITEMS[1][0];
    
    // expected price from the method call
    double expectedPrice = 3.77;

    // check to see if the method call returns the expected price value
    if (ShoppingCart.getSubTotalPrice(cart, count) != expectedPrice) {
      System.out
          .println("Problem detected: After adding 2 items with key 0 to the cart and 1 item with key 1, "
              + "We call getSubTotalPrice() and compare it with testPrice. "
              + "The total price should have been the same as testPrice. "
              + "But, it was not the case.");
      testPassed = false;
    }

    // return result of the test
    return testPassed;
  }


  /**
   * main method used to call the unit tests
   * 
   * @param args
   */
  public static void main(String[] args) {

    // calling each method call and showing if they passed or failed
    
    System.out.println("testCountIncrementedAfterAddingOnlyOneItem(): "
        + testCountIncrementedAfterAddingOnlyOneItem());

    System.out.println("testAddAndOccurrencesOfForOnlyOneItem(): "
        + testAddAndOccurrencesOfForOnlyOneItem());

    System.out.println("testAddOccurrencesOfDuplicateItems(): "
        + testAddOccurrencesOfDuplicateItems());

    System.out.println("testAddingTooMuchItems(): " + testAddingTooMuchItems());

    System.out.println("testRemoveOnlyOneOccurrenceOfItem(): "
        + testRemoveOnlyOneOccurrenceOfItem());

    System.out.println("testRemoveItemNotFoundInCart(): " + testRemoveItemNotFoundInCart());

    System.out.println("testGetSubTotalPrice(): " + testGetSubTotalPrice());
  }
}
