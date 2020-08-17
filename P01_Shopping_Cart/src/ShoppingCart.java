// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Shopping Cart App
// Files: ShoppingCart.java
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
import java.util.Scanner;

/**
 * This class contains the methods and main for the ShoppingCart project. This class allows users to
 * add and remove items to their cart, display the number of times an item was added to the cart,
 * display the items in the cart, print the market catalog, display the user's cart, and checkout.
 * 
 * @author Ayuj Prasad
 * @author Kenneth Mui
 *
 */
public class ShoppingCart {

  // shopping cart max capacity
  private static final int CART_CAPACITY = 20;

  // sales tax
  private static final double TAX_RATE = 0.05;

  // A perfect-size two-dimensional array that stores the available items in the market
  // MARKET_ITEMS[i][0] refers to a String that represents the description of the item identified by
  // index i
  // MARKET_ITEMS[i][1] refers to a String that represents the unit price of the item identified by
  // index i in dollars.
  public static final String[][] MARKET_ITEMS = new String[][] { {"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

  /**
   * This method interacts with the user. It calls supporting methods and updates the user's cart as
   * a command is entered.
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {

    // the user's cart to which items will be added to and removed from
    String[] userCart = new String[CART_CAPACITY];

    // the number of items in the cart
    int numItems = 0;

    // cost of items in cart without tax
    double subTotalCost = 0.0;

    // the cost of items in the cart after tax
    double totalCost = 0.0;

    // number of occurrences of an item in the user's cart
    int numOfOccurrences = 0;

    // scanner to read user's commands
    Scanner readInput = new Scanner(System.in);

    // the character to identify what letter the user's command is
    char userCmd = '\u0000';


    System.out.println("=============   Welcome to the Shopping Cart App   =============");
    System.out.println();
    System.out.println();

    // loop to keep displaying command menu as long as the quit character is not entered
    do {

      // printing the command menu
      System.out.println("COMMAND MENU:");
      System.out.println(" [P] print the market catalog");
      System.out
          .println(" [A <index>] add one occurrence of an item to the cart given its identifier");
      System.out.println(" [C] checkout");
      System.out.println(" [D] display the cart content");
      System.out
          .println(" [O <index>] number of occurrences of an item in the cart given its identifier");
      System.out
          .println(" [R <index>] remove one occurrence of an item from the cart given its identifier");
      System.out.println(" [Q]uit the application");
      System.out.println();

      // prompting user of their command
      System.out.print("ENTER COMMAND: ");

      // read the user's input
      String userInput = readInput.nextLine();

      // turn to upper-case and remove all leading and trailing white-spaces
      userInput = userInput.toUpperCase().trim();

      // the first character of the user's command is stored
      userCmd = userInput.charAt(0);

      // if userCmd is 'P'
      if (userCmd == 'P') {

        // method is called to print the market catalog
        printMarketCatalog();
        System.out.println();

        // if userCmd is 'A'
      } else if (userCmd == 'A') {

        // the remaining characters of the user's input is stored
        String itemIndexString = userInput.substring(2);

        // the character(s) are parsed to make it an integer
        int itemIndex = Integer.parseInt(itemIndexString);

        // add the item at itemIndex to the user's cart
        // numItems is updated with the result of the method call
        numItems = add(itemIndex, userCart, numItems);
        System.out.println();

        // if userCmd is 'C'
      } else if (userCmd == 'C') {

        // the sub-total cost is calculated for the items in the cart
        // subTotalCost is updated with the result of the call
        subTotalCost = getSubTotalPrice(userCart, numItems);

        // totalCost is the cost of the items after adding tax
        totalCost = subTotalCost + (TAX_RATE * subTotalCost);

        // the checkout statement is printed in the given format
        System.out.println("#items: " + numItems + " Subtotal: $"
            + String.format("%.2f", subTotalCost) + " Tax: $"
            + String.format("%.2f", (TAX_RATE * subTotalCost)) + " TOTAL: $"
            + String.format("%.2f", totalCost));
        System.out.println();
      }

      // if userCmd is 'D'
      else if (userCmd == 'D') {

        // the user's cart is printed (with commas between items)
        displayCartContent(userCart, numItems);
        System.out.println();
      }

      // if userCmd is 'O'
      else if (userCmd == 'O') {

        // the remaining characters of the user's input is stored
        String itemIndexString = userInput.substring(2);

        // the character(s) are parsed to make it an integer
        int itemIndex = Integer.parseInt(itemIndexString);

        // the number of occurrences of the items at itemIndex is calculated
        // numOfOccurrences is updated with the result of the method call
        numOfOccurrences = occurrencesOf(itemIndex, userCart, numItems);

        // the statement is printed in the given format
        System.out.println("The number of occurrences of " + getItemDescription(itemIndex)
            + " (id #" + itemIndex + ") is: " + numOfOccurrences);
        System.out.println();
      }

      // if userCmd is 'R'
      else if (userCmd == 'R') {

        // the remaining characters of the user's input is stored
        String itemIndexString = userInput.substring(2);

        // the character(s) are parsed to make it an integer
        int itemIndex = Integer.parseInt(itemIndexString);

        // the first occurrence of the item at itemIndex is removed
        // numItems is updated with the result of the method call
        numItems = remove(getItemDescription(itemIndex), userCart, numItems);
        System.out.println();

      }

      // quit the loop if userCmd is 'Q'
    } while (userCmd != 'Q');

    // the thank you message
    System.out.println("=============  Thank you for using this App!!!!!  =============");

    // scanner is closed
    readInput.close();
  }

  /**
   * Adds the item with the given its identifier index at the end of the cart
   * 
   * @param index of the item within the marketItems array
   * @param cart shopping cart
   * @param count number of items present within the cart
   * @return the number of items present in the cart after the item with identifier index is added
   */
  public static int add(int index, String[] cart, int count) {

    // if number of items is at max capacity, then warning message is printed and count is returned.
    if (count == CART_CAPACITY) {
      System.out.println("WARNING: The cart is full. You cannot add any new item.");
      return count;
    }

    // adding the item from marketItems to the cart at the next index.
    cart[count] = getItemDescription(index);

    // increment count to update number of items on the cart.
    count++;

    // return the number of items present in the cart after adding item from marketItems.
    return count;
  }

  /**
   * Returns how many occurrences of the item with index itemIndex are present in the shopping cart
   * 
   * @param itemIndex identifier of the item to count its occurrences in the cart
   * @param cart shopping cart
   * @param count number of items present within the cart
   * @return the number of occurrences of item in the cart
   */
  public static int occurrencesOf(int itemIndex, String[] cart, int count) {

    // if the cart is empty, then 0 is returned.
    if (cart.length == 0) {
      return 0;
    }

    // the item to look for in the cart is stored in the variable.
    String targetItem = getItemDescription(itemIndex);

    // variable to count number of occurrences of item.
    int numOfOccurrences = 0;

    // iterate through the cart (only for as many items are present on the cart).
    for (int i = 0; i < count; i++) {

      // if the item at the current index matches the item we are searching for.
      if (cart[i].equals(targetItem)) {

        // increment the number of occurrences of the item.
        numOfOccurrences++;
      }

      // if not, then continue the loop and search the next item.
      else {
        continue;
      }
    }

    // return the number of times the item occurs in the user's cart
    return numOfOccurrences;
  }

  /**
   * Removes the first (only one) occurrence of itemToRemove if found and returns the number of
   * items in the cart after remove operation is completed either successfully or not
   * 
   * @param itemToRemove the item to be removed from the cart
   * @param cart shopping cart
   * @param count number of items present within the cart
   * @return the number of items present in the cart after the itemToRemove is removed from the cart
   */
  public static int remove(String itemToRemove, String[] cart, int count) {

    // Variable to hold the index value (in the cart) of the itemToRemove.
    int removeIndex = indexOf(itemToRemove, cart, count);

    // after looping, if the index is -1 (item is not in the cart), then the warning message is
    // printed.
    if (removeIndex == -1) {
      System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
    }

    // else (if item is in the cart)
    else {

      // the last item in the cart is put into the current index and that last index is removed.
      cart[removeIndex] = cart[count - 1];
      cart[count] = null;

      // the number of items in the cart is decremented.
      count--;
    }

    // return number of items in the cart after the remove call.
    return count;
  }

  /**
   * Calculates the total cost of the items present in the cart (without tax) based on their value
   * on the market catalog (MARKET_ITEMS)
   * 
   * @param cart shopping cart
   * @param count number of items present within the cart
   * @return the total value (cost) of the cart without tax in $ (double)
   */

  public static double getSubTotalPrice(String[] cart, int count) {

    // the total price of the items without tax
    double totalPrice = 0.0;

    // the item at the current index in the user's cart
    String currentItem = null;

    // the price of currentItem in the market catalog with the $
    String itemReference = null;

    // the integer value of the price of currentItem
    double itemPrice = 0.0;

    // the string value of the price of currentItem
    String priceString = null;

    // loop to go through each item in the cart
    for (int i = 0; i < count; ++i) {
      currentItem = cart[i];

      // loop through each item in the market catalog
      for (int j = 0; j < MARKET_ITEMS.length; ++j) {

        // if currentItem is the same as the item in the current index of the market catalog
        if (currentItem.equals(getItemDescription(j))) {

          // take the price value of that item and break out of the loop
          itemReference = MARKET_ITEMS[j][1];
          break;

          // otherwise move onto the next item
        } else {
          continue;
        }
      }

      // take all characters after the $ and convert it into an integer
      priceString = itemReference.substring(1);
      itemPrice = Double.valueOf(priceString);

      // add the price to totalPrice
      totalPrice = totalPrice + itemPrice;
    }

    // return totalPrice
    return totalPrice;
  }

  /**
   * Prints the Market Catalog (item identifiers, description, and unit prices)
   * 
   */
  public static void printMarketCatalog() {

    // Market Catalog design
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id 	Description 	 Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

    // loop through the market catalog
    for (int i = 0; i < MARKET_ITEMS.length; ++i) {

      // print out each item's index, name (description), and price in the given format
      System.out.println(i + "\t\t" + getItemDescription(i) + "    \t " + MARKET_ITEMS[i][1]);
    }

    // End of market catalog design
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Displays the cart content (items separated by commas)
   * 
   * @param cart shopping cart
   * @param count number of items present within the cart
   */
  public static void displayCartContent(String[] cart, int count) {

    System.out.print("Cart Content: ");

    // loop through the cart, printing with a comma after each item
    for (int i = 0; i < count; ++i) {
      System.out.print(cart[i] + ", ");
    }
    System.out.println();
  }

  /**
   * Returns the description of the item at the given index
   * 
   * @param index the index of the item to search in the market catalog
   * @return description of the item in the market catalog (MARKET_ITEMS)
   */
  private static String getItemDescription(int index) {

    // returns the item name (description) of the item at "index" in the market catalog
    return MARKET_ITEMS[index][0];
  }

  /**
   * Returns the index of an item within the user's shopping cart
   * 
   * @param item description
   * @param cart Shopping cart
   * @param count number of items present in the shopping cart
   * @return index of the item within the shopping cart, and -1 if the item does not exist in the
   *         cart
   */
  private static int indexOf(String item, String[] cart, int count) {

    // Variable to hold the index value (in the cart) of the itemToRemove.
    int removeIndex = -1;

    // loop through all items in the cart.
    for (int i = 0; i < count; i++) {

      // if the item at the current index is found
      if (cart[i].equals(item)) {

        // the index of the item is noted and the loop is broken.
        removeIndex = i;
        break;
      }

      // if item is not the itemToRemove then continue looping through the cart.
      else {
        continue;
      }

    }

    // the index of the itemToRemove is returned (-1 if it is not in the cart)
    return removeIndex;
  }
}
