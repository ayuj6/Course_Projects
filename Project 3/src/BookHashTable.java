// ////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P3a - BookHashTable.java
// Course: CS 400 (001), Fall 2019
//
// Author: Ayuj Prasad
// Email: prasad22@wisc.edu
// Lecturer's Name: Deb Deppeler
// ///////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

// DO ADD REQUIRED PUBLIC METHODS TO IMPLEMENT interfaces
//
// DO NOT ADD ADDITIONAL PUBLIC MEMBERS TO YOUR CLASS 
// (no public or package methods that are not in implemented interfaces)
//
// if open addressing: describe probe sequence 
// if buckets: describe data structure for each bucket
//
// Hashing Algorithm: My hashTable is an ArrayList of ArrayLists, 
//					  my algorithm takes the first character of the key, 
// 					  and mods it with the table size to ensure that it 
//					  will be placed in a valid index.
//
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object

/**
 * HashTable implementation that uses:
 * 
 * @param <K> unique comparable identifier for each <K,V> pair, may not be null
 * @param <V> associated value with a key, value may be null
 */
public class BookHashTable implements HashTableADT<String, Book> {

	/**
	 * This is the inner class that will be used to store the key-pair values in the
	 * hashTable
	 * 
	 * @author ayujprasad
	 *
	 * @param <K> The key associated with the Key-Value pair
	 * @param <V> The value associated with the Key-Value pair
	 */
	class BookNode<K, V> {
		// key and value fields
		K key;
		V value;

		/**
		 * The constructor for this inner class that will initialize the key and value
		 * fields
		 * 
		 * @param key   the key object to be initialized
		 * @param value the value object to be initialized
		 */
		public BookNode(K key, V value) {
			// set the key and value fields to their respective parameter values
			this.key = key;
			this.value = value;
		}
	}

	// the hashTable that will hold the key-book values
	private ArrayList<ArrayList<BookNode>> bookHashTable = new ArrayList<ArrayList<BookNode>>();

	// field to hold the current loadFactorThreshold
	private double load_Factor_Threshold;

	// current size
	private int numkeys;

	// capacity
	private int tableCapacity;

	// The initial capacity that is used if none is specifed user
	static final int DEFAULT_CAPACITY = 101;

	// The load factor that is used if none is specified by user
	static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;

	/**
	 * REQUIRED default no-arg constructor Uses default capacity and sets load
	 * factor threshold for the newly created hash table.
	 */
	public BookHashTable() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD);
	}

	/**
	 * This private helper method computes a hasIndex for a given key
	 * 
	 * @param key the key that will be used to computer the hashcode
	 * @return the hashIndex to be used
	 */
	private int hashcode(String key) {
		// return the positive value of the first character modded with the table size
		return Math.abs((int) key.charAt(0) % getCapacity());
	}

	/**
	 * Creates an empty hash table with the specified capacity and load factor.
	 * 
	 * @param initialCapacity     number of elements table should hold at start.
	 * @param loadFactorThreshold the ratio of items/capacity that causes table to
	 *                            resize and rehash
	 */
	public BookHashTable(int initialCapacity, double loadFactorThreshold) {
		// initialize our ArrayList of ArrayLists
		bookHashTable = new ArrayList<ArrayList<BookNode>>();

		// set tableCapacity and loadFactorThreshold to the parameters
		tableCapacity = initialCapacity;
		load_Factor_Threshold = loadFactorThreshold;

		// set numkeys to 0
		numkeys = 0;

		// initialize each index for the capacity to be an empty arrayList (to avoid
		// NullPointerExceptions)
		for (int i = 0; i < initialCapacity; ++i) {
			bookHashTable.add(new ArrayList<BookNode>());
		}
	}

	/**
	 * Add the key,value pair to the data structure and increase the number of keys.
	 * If key is null, throw IllegalNullKeyException; If key is already in data
	 * structure, throw DuplicateKeyException();
	 * 
	 * @param key   the key associated with the book
	 * @param value the book that will be inserted to the table
	 * 
	 * @throws IllegalNullKeyException if the key is null
	 * @throws DuplicateKeyException   if the key is already present in the table
	 */
	@Override
	public void insert(String key, Book value) throws IllegalNullKeyException, DuplicateKeyException {
		// if the key is null, throw a new IllegalNullKeyException
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// increase array size
		// if loadFactor exceeds the loadFactorThreshold, resize the hashTable
		double currentThreshold = getLoadFactorThreshold();
		if (currentThreshold >= load_Factor_Threshold) {
			resize();
		}

		// create a new BookNode
		BookNode itemToInsert = new BookNode(key, value);

		// store the hashIndex of the key in a variable
		int hashIndex = hashcode(key);

		// get the arrayList from the hashIndex
		ArrayList<BookNode> tempArray = bookHashTable.get(hashIndex);

		// if the index has more than one items
		if (tempArray.size() > 0) {

			// for each book in the index,
			for (int i = 0; i < tempArray.size(); ++i) {
				// if the keys match, throw a DuplicateKeyException
				if (tempArray.get(i).key == key) {
					throw new DuplicateKeyException();
				}
			}
			// otherwise, if not keys match, increment numkeys and add the bookNode to the
			// index
			numkeys = numkeys + 1;
			bookHashTable.get(hashIndex).add(itemToInsert);
		} else {
			// if the hashIndex was empty, increment numkeys and add the bookNode to the
			// index
			numkeys = numkeys + 1;
			bookHashTable.get(hashIndex).add(itemToInsert);
		}
	}

	/**
	 * private helper method to resize the hashtable when loadFactor exceeds the
	 * loadFactorThreshold
	 */
	private void resize() {

		// increase capacity
		this.tableCapacity = 2 * this.tableCapacity + 1;

		// create a temporary arrayList
		ArrayList<ArrayList<BookNode>> tempArrayList = new ArrayList<ArrayList<BookNode>>();

		// initialize it with empty arrayLists
		for (int i = 0; i < tableCapacity; i++) {
			tempArrayList.add(new ArrayList<BookNode>());
		}

		// for each index in the old hashTable
		for (int k = 0; k < bookHashTable.size(); ++k) {
			ArrayList<BookNode> temp = bookHashTable.get(k);

			// re-insert each item from the old hashTable (at current index) into the temp Hashtable
			for (int l = 0; l < temp.size(); l++) {
				tempArrayList.get(hashcode((String) temp.get(l).key)).add(temp.get(l));
			}
		}

		// set the old hashTable to hold the reference of the temp hashtable
		bookHashTable = tempArrayList;
	}

	/**
	 * If key is found, remove the key,value pair from the data structure decrease
	 * number of keys. return true
	 * 
	 * If key is null, throw IllegalNullKeyException If key is not found, return
	 * false
	 * 
	 * @param key the key to be used to find the book to remove
	 * @return true if the value was removed, false otherwise
	 * 
	 * @throws IllegalNullKeyException if the key is null
	 */
	@Override
	public boolean remove(String key) throws IllegalNullKeyException {
		// if the key is null, throw a new IllegalNullKeyException
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// store the hashIndex of the key in a variable
		int indexToRemove = hashcode(key);

		// if the given arrayList index has items in it,
		if (bookHashTable.get(indexToRemove).size() > 0) {
			// loop through each book item in this index
			for (int i = 0; i < bookHashTable.get(indexToRemove).size(); i++) {
				// if the keys match,
				if (bookHashTable.get(indexToRemove).get(i).key == key) {
					// remove this corresponding book
					bookHashTable.get(indexToRemove).remove(i);
					// decrement numkeys
					numkeys--;
					// return true
					return true;
				}
			}
		}
		// since key wasn't found, return false
		return false;
	}

	/**
	 * Returns the value associated with the specified key Does not remove key or
	 * decrease number of keys
	 *
	 * If key is null, throw IllegalNullKeyException If key is not found, throw
	 * KeyNotFoundException().
	 * 
	 * @param key the key to be used to search for the corresponding book in the
	 *            table
	 * @return the book corresponding to the given key
	 * 
	 * @throws IllegalNullKeyException if the key is null
	 * @throws KeyNotFoundException    if the key is not present in the table
	 */
	@Override
	public Book get(String key) throws IllegalNullKeyException, KeyNotFoundException {
		// if the key is null, throw a new IllegalNullKeyException
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// store the hashIndex of the key in a variable
		int hashIndex = hashcode(key);

		// if the given arrayList index has items in it,
		if (bookHashTable.get(hashIndex).size() > 0) {
			// loop through each book item in this index
			for (int i = 0; i < bookHashTable.get(hashIndex).size(); i++) {
				// if the keys match, return the corresponding book
				if (bookHashTable.get(hashIndex).get(i).key == key) {
					return (Book) bookHashTable.get(hashIndex).get(i).value;
				}
			}
		}
		// since key wasn't found, throw this exception
		throw new KeyNotFoundException();
	}

	/**
	 * Returns the number of key,value pairs in the data structure
	 * 
	 * @return the number of key-value pairs in the table
	 */
	@Override
	public int numKeys() {
		// return numkeys
		return numkeys;
	}

	/**
	 * Returns the load factor for this hash table that determines when to increase
	 * the capacity of this hash table
	 * 
	 * @return the loadFactorThresold from the number of keys and tableCapacity
	 */
	@Override
	public double getLoadFactorThreshold() {
		// return the current loadFactorThresold of the table
		return ((double) numkeys / tableCapacity);
	}

	/**
	 * Capacity is the size of the hash table array This method returns the current
	 * capacity.
	 *
	 * The initial capacity must be a positive integer, 1 or greater and is
	 * specified in the constructor.
	 *
	 * REQUIRED: When the load factor is reached, the capacity must increase to: 2 *
	 * capacity + 1
	 *
	 * Once increased, the capacity never decreases
	 * 
	 * @return the current table capacity
	 */
	@Override
	public int getCapacity() {
		// return tableCapacity (current table capacity)
		return tableCapacity;
	}

	/**
	 * Returns the collision resolution scheme used for this hash table. Implement
	 * this ADT with one of the following collision resolution strategies and
	 * implement this method to return an integer to indicate which strategy.
	 *
	 * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN
	 * ADDRESSING: double hashing 4 CHAINED BUCKET: array list of array lists 5
	 * CHAINED BUCKET: array list of linked lists 6 CHAINED BUCKET: array list of
	 * binary search trees 7 CHAINED BUCKET: linked list of array lists 8 CHAINED
	 * BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of
	 * binary search trees
	 * 
	 * @return for this table - 4
	 */
	@Override
	public int getCollisionResolutionScheme() {
		// return 4
		return 4;
	}

}