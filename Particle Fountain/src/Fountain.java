// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Particle Fountain
// Files: P2ParticleFountain.jar
// Course: CS 300 - Spring 2019
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
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
// /////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (NONE)
// Online Sources: (NONE)
//
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

/**
 * This class contains the methods and main for the ParticleFountain project. This class allows for
 * particles to be created in the particle array, allow for their movement and acceleration in the x
 * and y directions, and then remove old particles that have exceeded a maximum age. This class also
 * has methods that help in running the program.
 * 
 * @author Ayuj Prasad
 * @author Kenneth Mui
 *
 */
public class Fountain {

  /**
   * Creates a single particle at position (3,3) with velocity (-1,-2). Then checks whether calling
   * updateParticle() on this particle's index correctly results in changing its position to
   * (2,1.3).
   * 
   * @return true when no defect is found, and false otherwise
   * 
   */
  private static boolean testUpdateParticle() {

    // holds the result of the tests.
    boolean testPassed = true;

    // initialize a particle array for the test and assign with respective velocities and positions.
    particles = new Particle[3];
    particles[2] = new Particle();
    particles[2].setPositionX(3);
    particles[2].setPositionY(3);
    particles[2].setVelocityX(-1);
    particles[2].setVelocityY(-2);

    // call updateParticle for the test particle at index 2.
    updateParticle(2);

    // check to see if the x-position of the particle after the method call is 2.0. If the test
    // fails, testPassed is assigned with false.
    // (Float comparison method).
    if (!(Math.abs(particles[2].getPositionX() - 2.0f) < 0.1)) {
      testPassed = false;
    }

    // check to see if the y-position of the particle after the method call is 1.3. If the test
    // fails, testPassed is assigned with false.
    // (Float comparison method).
    if (!(Math.abs(particles[2].getPositionY() - 1.3f) < 0.1)) {
      testPassed = false;
    }

    // reset the particles array to a null reference.
    particles = null;

    // return result of the test.
    return testPassed;
  }

  /**
   * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
   * and another that does not). Then checks whether the old particles were removed and the young
   * particle was left alone.
   * 
   * @return true when no defect is found, and false otherwise
   * 
   */
  private static boolean testRemoveOldParticles() {

    // holds the result of the tests.
    boolean testPassed = true;

    // initialize a particle array for the test.
    particles = new Particle[3];
    particles[0] = new Particle();
    particles[1] = new Particle();
    particles[2] = new Particle();

    // assign each particle in the array with an age value (2 values greater than 6 and 1 value less
    // than 6).
    particles[0].setAge(8);
    particles[1].setAge(22);
    particles[2].setAge(2);

    // call removeParticles with maxAge of 6.
    removeOldParticles(6);

    // after the method call, if the particle at the 0th index was not removed (failed), then
    // testPassed is assigned with false.
    if (particles[0] != null) {
      testPassed = false;
    }

    // after the method call, if the particle at the 1st index was not removed (failed), then
    // testPassed is assigned with false.
    if (particles[1] != null) {
      testPassed = false;
    }

    // after the method call, if the particle at the 2nd index was removed (failed), then testPassed
    // is assigned with false.
    if (particles[2] == null) {
      testPassed = false;
    }

    // reset the particles array to a null reference.
    particles = null;

    // return the result from the test.
    return testPassed;
  }

  // the random variable that is used for all randomly generated values.
  private static Random randGen = new Random();

  // creation of a particles array.
  private static Particle[] particles;

  // fields for x and y position of the particles.
  private static int positionX;
  private static int positionY;

  // fields to initialize start and end color of the particles.
  private static int startColor;
  private static int endColor;

  /**
   * This method calls the runApplication() method in the Utility class to set up a graphical window
   * for the program.
   * 
   * @param args (unused)
   * 
   */
  public static void main(String[] args) {

    // Runs the fountain application.
    // Creates a new graphical window for this program.
    Utility.runApplication();

  }

  /**
   * This method sets up the environment for the program to be run. It is only called at the
   * beginning of the program.
   * 
   */
  public static void setup() {

    // call for testUpdateParticle().
    boolean test1 = testUpdateParticle();

    // if the first test failed, then the fail message is printed.
    if (test1 == false) {
      System.out.println("tetUpdateParticle FAILED: test particle did not move as expected "
          + "when updateParticle() was called.");
    }

    // call for testRemoveOldParticles().
    boolean test2 = testRemoveOldParticles();

    // if the second test failed, then the fail message is printed.
    if (test2 == false) {
      System.out.println("testRemoveOldParticles FAILED: test particles were not removed "
          + "(or kept in the array) as expected when removeOldParticles() was called.");
    }

    // initialize x and y positions to 400 and 300 respectively.
    positionX = 400;
    positionY = 300;

    // initialize start color to blue - (23, 141, 235).
    startColor = Utility.color(23, 141, 235);

    // initialize end color to lighter blue - (23, 200, 255).
    endColor = Utility.color(23, 200, 255);

    // initialize particles array to size 800.
    particles = new Particle[800];

  }

  /**
   * This method is constantly being called by the program until it is terminated. It repeatedly
   * refreshes the background and gives individual calls to update each particle in the particles
   * array.
   * 
   */
  public static void update() {

    // set background color to (235, 213, 186).
    int backgroundColor = Utility.color(235, 213, 186);
    Utility.background(backgroundColor);

    // loop through particles array.
    for (int i = 0; i < particles.length; ++i) {

      // for each index that is not null
      if (particles[i] != null) {

        // that particle at the given index is updated
        updateParticle(i);

      }
    }


    // call createNewParticles with 10 new particles to be created.
    createNewParticles(10);

    // call removeParticles method with maxAge 80.
    removeOldParticles(80);



  }

  /**
   * This method updates the values (velocities, position, age, transparency and color) for each
   * particle at the index "index" of the particles array.
   * 
   * @param index is the index of the particle in the particles array that we are updating in the
   *        method.
   * 
   */
  private static void updateParticle(int index) {

    // for the particle at index, get the circle's x-position, y-position and size.
    Utility.circle(particles[index].getPositionX(), particles[index].getPositionY(),
        particles[index].getSize());

    // fill its color and transparency.
    int transparency = particles[index].getTransparency();
    int color = particles[index].getColor();
    Utility.fill(color, transparency);

    // update the y-velocity to be the old y-velocity + 0.3f.
    particles[index].setVelocityY(particles[index].getVelocityY() + 0.3f);

    // update the x-position to be the old position + the x-velocity.
    particles[index]
        .setPositionX(particles[index].getPositionX() + particles[index].getVelocityX());

    // update the y-position to be the old position + the y-velocity.
    particles[index]
        .setPositionY(particles[index].getPositionY() + particles[index].getVelocityY());

    // set the age to be incremented by 1.
    particles[index].setAge(particles[index].getAge() + 1);
  }

  /**
   * The method loops through the particles array and creates new particles in the null references
   * in the array. Each particle is assigned random positions, velocities, age, size, color, and
   * transparency. The array will continue to create new particles until "numOfNewParticles"
   * particles are created.
   * 
   * @param numOfNewParticles the number of particles that are created.
   * 
   */
  private static void createNewParticles(int numOfNewParticles) {

    // counter to count how many particles have been created.
    int particleCounter = 0;

    // loop through the particles array.
    for (int i = 0; i < particles.length; ++i) {

      // for each index that has null reference
      if (particles[i] == null) {

        // the index is made to reference a new particle.
        particles[i] = new Particle();

        // given a random age between 0 and 40.
        particles[i].setAge(randGen.nextInt(41));

        // given a random size between 4 and 11 (upper-bound exclusive).
        particles[i].setSize(randGen.nextInt(7) + 4 + randGen.nextFloat());

        // given a random color that is a mix of startColor and endColor.
        particles[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextFloat()));

        // given a random transparency between 32 and 127
        particles[i].setTransparency(randGen.nextInt(96) + 32);

        // given a random x and y position that is -3 to +2.999999 of Fountain.positionX and
        // Fountain.positionY
        int randPosition = randGen.nextInt(7);
        particles[i].setPositionX((Fountain.positionX - 3) + (randPosition * randGen.nextFloat()));
        particles[i].setPositionY((Fountain.positionY - 3) + (randPosition * randGen.nextFloat()));

        // given a random x-velocity between -1 to 0.999999
        int randVelocityX = randGen.nextInt(2);
        particles[i].setVelocityX(-1f + randVelocityX + randGen.nextFloat());

        // given a random y-velocity between -10 to -5.000001
        int randVelocityY = randGen.nextInt(5);
        particles[i].setVelocityY(-10f + randVelocityY + randGen.nextFloat());

        // increment particleCounter for the new particle that was created.
        ++particleCounter;
      }

      // if particleCounter and numOfNewParticles are equal, then we break out of the loop.
      if (particleCounter == numOfNewParticles) {
        break;
      }
    }
  }

  /**
   * It will loop through the particles array to find all particles that don't have a null
   * reference. Among these particles, if their age exceeds a defined "maxAge", then they are
   * removed from the array (their references is changed to null).
   * 
   * @param maxAge the maximum age that the particles can reach before being removed from the array.
   * 
   */
  private static void removeOldParticles(int maxAge) {

    // loop through the particles array.
    for (int i = 0; i < particles.length; ++i) {

      // if the particle at index i is not null
      if (particles[i] != null) {

        // check to see if that particle's age is greater than the specified maxAge.
        if (particles[i].getAge() > maxAge) {

          // if it is then change the particle's index to a null reference.
          particles[i] = null;
        }
      }
    }
  }

  /**
   * The method is used to center the fountain to the x and y position of the mouse click.
   * 
   * @param xPos the x-position of the mouse click.
   * @param yPos the y-position of the mouse click.
   * 
   */
  public static void mousePressed(int xPos, int yPos) {

    // update Fountain.positionX and Fountain.positionY to be the xPos and yPos (where the mouse
    // clicked).
    Fountain.positionX = xPos;
    Fountain.positionY = yPos;
  }

  /**
   * The method will save a screenshot of the graphical window if the 'p' key is pressed.
   * 
   * @param pressedKey is the key that is pressed
   * 
   */
  public static void keyPressed(char pressedKey) {

    // if the key that was pressed is 'p'
    if (pressedKey == 'p') {

      // save "screenshot.png"
      Utility.save("screenshot.png");
    }
  }
}
