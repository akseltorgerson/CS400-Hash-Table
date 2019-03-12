//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HashTable
// Course:          CS400 2019 Spring
//
// Author:          Aksel Torgerson
// Email:           atorgerson@wisc.edu
// Lecturer's Name: Debra Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         n/a
// Online Sources:  n/a
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/** COLLISION RESOLUTION SCHEME **/
// For my collision resolution scheme I chose to use buckets. I implemented
// them using an ArrayList.

/** HASHING ALGORITHM **/
// For my hashing algorithm I used Javas built-in hashCode function
// and then used to modulo operator to fit it into the capacity of the
// table.

import java.util.ArrayList;

/**
 * This class represents a hash table that can store K,V key value
 * pairs
 *
 * @author akseltorgerson
 *
 * @param <K> the unique key of each node
 * @param <V> the respective value of each node
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

    private double loadFactorThreshold;                 // load factor limit
    private int capacity;                               // how many keys the table can hold
    private int numKeys;                                // how many keys exist
    private ArrayList<ArrayList<HashNode>> hashTable;   // array list of array list of nodes

    /**
     * This class represents one key value pair grouped together as a node
     * These are what are stored in the bucket array lists
     */
    private class HashNode {
        private K key;
        private V value;
        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Default no-arg constructor for a HashTable
     * Calls the other constructor of HashTable with a default
     * value of 10 for capacity and .75 for loadFactorThreshold
     */
    public HashTable() {
        // avoid redundancy by calling the other constructor here
        this(10, .75);
    }

    /**
     * Constructor for creating a HashTable with a specified starting
     * capacity and loadFactorThreshold
     *
     * @param initialCapacity how large the table will be initially
     * @param loadFactorThreshold the ratio of keys/capacity that we must rehash
     */
    public HashTable(int initialCapacity, double loadFactorThreshold) {
        this.capacity = initialCapacity;
        this.loadFactorThreshold = loadFactorThreshold;
        numKeys = 0;
        // instantiate hashTable with an array list of array list of hash nodes
        hashTable = new ArrayList<ArrayList<HashNode>>(this.capacity);
        for(int i = 0; i < this.capacity; i++) {
            // for every spot in the hashTable create a bucket in that spot
            hashTable.add(i, new ArrayList<HashNode>());
        }
    }

    /**
     * Getter for the loadFactorThreshold
     * @return loadFactorThreshold
     */
    @Override public double getLoadFactorThreshold() {
        return loadFactorThreshold;
    }

    /**
     * Calculates and returns the load factor
     * @returns the load factor the hash table
     */
    @Override public double getLoadFactor() {
        return (double)numKeys / capacity;
    }

    /**
     * Getter for the capacity
     * @returns the capacity
     */
    @Override public int getCapacity() {
        return capacity;
    }

    @Override public int getCollisionResolution() {
        return 4;
    }

    /**
     * Insert method for the hashTable. Uses a bucket to put collisions in, and
     * calls the rehash function for when the load factor gets surpassed;
     * Functions in O(1) time complexity
     * @param key
     * @param value
     * @throws IllegalNullKeyException
     * @throws DuplicateKeyException
     */
    @Override public void insert(K key, V value)
        throws IllegalNullKeyException, DuplicateKeyException {

        // if the key is null throw an INK exception
        if (key == null) { throw new IllegalNullKeyException(); }

        // create a new node with the key and value pair that needs to be inserted
        HashNode currentNode = new HashNode(key, value);

        // use Javas built-in hashCode function to get a hashID, then mod it with the capacity
        int hashID = currentNode.key.hashCode();
        int hashIndex = Math.abs(hashID) % capacity;

        // after we find the hashIndex, we need to check that bucket to see if that key is there
        for(int i = 0; i < hashTable.get(hashIndex).size(); i++) {
            if(hashTable.get(hashIndex).get(i).key.equals(currentNode.key)) {
                // throw a dupe key exception if they keys are equal
                throw new DuplicateKeyException();
            }
        }

        hashTable.get(hashIndex).add(currentNode);
        numKeys++;

        // must check if were at the load factor yet
        if (this.getLoadFactor() >= loadFactorThreshold) {
            expandTable();
        }
    }

    private void expandTable() throws IllegalNullKeyException, DuplicateKeyException {
        // make the new table that were going to put all the values in
        HashTable newTable = new HashTable((capacity * 2) + 1, loadFactorThreshold);
        for(int i = 0; i < capacity; i++) {
            for(int j = 0; j < hashTable.get(i).size(); j++) {
                HashNode node = hashTable.get(i).get(j);

                newTable.insert(node.key, node.value);
            }
        }
        this.hashTable = newTable.hashTable; // update our hashTable to point to the new one
        this.capacity = (capacity * 2) + 1; // update the capacity of our new table
    }

    @Override public boolean remove(K key) throws IllegalNullKeyException {
        if (key == null) { throw new IllegalNullKeyException(); }

        int hashID = key.hashCode();
        int hashIndex = Math.abs(hashID) % capacity;

        // loop through the bucket to see if any of the keys are the same
        for(int i = 0; i < hashTable.get(hashIndex).size(); i++) {
            if(hashTable.get(hashIndex).get(i).key.equals(key)) {
                // cool cool we found the key, now just remove it
                hashTable.get(hashIndex).remove(i);
                numKeys--;
                return true;
            }
        }
        // if we looked through the bucket the key should hash to, and we didn't find a match
        return false;
    }

    @Override public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if (key == null) { throw new IllegalNullKeyException(); }

        int hashID = key.hashCode();
        int hashIndex = Math.abs(hashID) % capacity;

        // do the same thing as remove basically
        for(int i = 0; i < hashTable.get(hashIndex).size(); i++) {
            if(hashTable.get(hashIndex).get(i).key.equals(key)) {
                // cool cool we found the key, now just return the value
                return hashTable.get(hashIndex).get(i).value;
            }
        }
        // throw this if we didn't locate the key
        throw new KeyNotFoundException();
    }

    @Override public int numKeys() {
        return numKeys;
    }

    public ArrayList<ArrayList<HashNode>> getTable() {
        return hashTable;
    }

    // TODO: add all unimplemented methods so that the class can compile



}
