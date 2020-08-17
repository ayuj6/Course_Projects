// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Iterating to Philosophy - NextWikiLink class
// Files: NextWikiLink.java, jsoup-1.10.3.jar
// Course: CS300 - Spring 2019
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
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This class is used for running the code that will prompt the user for inputs and print out the
 * recurring wikipedia links from each page. It also contains the overrided apply method that
 * returns the subsequent wiki links.
 * 
 * @author ayujprasad
 *
 */
public class NextWikiLink implements Function<String, String> {

  /**
   * This overrided method makes use of the jsoup library and downloads the wikipedia page, using
   * the input parameter "t". It also skips all pronunciation and citation links on this page and
   * returns the link attribute to the next allocated wikipedia page
   * 
   * @param t The link of the wikipedia page to read through
   * @return The next (non-citation and non-pronunciation) link on the page
   */
  @Override
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }

  /**
   * The main method is implemented to act like a "Wikipedia crawling application". It prompts the
   * user to enter a topic name and number of iterations to follow, then prepend "/wiki/" to the
   * user's input, and replace spaces with underscores. And finally, it uses a for-each loop to
   * iterate through the number of links requested.
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {

    // scanner to read user inputs
    Scanner userInput = new Scanner(System.in);

    // function to use for our generator
    NextWikiLink testWiki = new NextWikiLink();

    // prompt user for page topic
    System.out.println("Enter a wikipedia page topic: ");
    String pageName = userInput.nextLine();

    // prompt user for number of iterations
    System.out.println("Enter the number of pages you'd like to step through: ");
    int numIterations = userInput.nextInt();

    // trim to remove any unnecessary spaces
    pageName = pageName.trim();

    // replace spaces with "_" and prepends "/wiki/" to the pageName
    pageName = pageName.replace(" ", "_");
    pageName = "/wiki/" + pageName;

    // new generator that will be used to obtain an iterator
    Generator<String> wikiGen = new Generator<String>(pageName, testWiki, numIterations);

    // iterator that will be used to obtain the following page links
    Iterator<String> FIterator = wikiGen.iterator();

    // for-each loop that will be used to print the subsequent pages
    for (String nextPage : wikiGen) {
      // using the next method, we obtain the nextPage (the next link that will be used)
      nextPage = FIterator.next();

      // we print out the new page we received
      System.out.println(nextPage);

      // if this page contained either of the error messages then we break out of the loop
      if (nextPage.contains("FAILED to find wikipedia page:")
          || nextPage.contains("FAILED to find a link in wikipedia page:")) {
        break;
      }
    }

    // close scanner to avoid leaking
    userInput.close();
  }
}
