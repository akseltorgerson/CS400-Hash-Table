//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HashTableTest
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

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;

import org.junit.jupiter.api.Assertions;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/**
 * This is the test class for HashTable.java
 */
public class HashTableTest {

    HashTable<Integer, String> H1; // a hash table of key type Integer and value type String
    HashTable<String, String> H2; // same as above but key type is String

    /**
     * This method sets up our variables before the tests run
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        H1 = new HashTable<>(10, .75);
        H2 = new HashTable<>(25, .25);
    }

    /**
     * This method cleans up our variables after the tests have run
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        H1 = null; // set our pointers to null
        H2 = null;
    }

    /**
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer, String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9)
            fail("collision resolution must be indicated with 1-9");
    }

    /**
     * IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer, String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } catch (IllegalNullKeyException e) { /* expected */ } catch (Exception e) {
            fail("insert null key should not throw exception " + e.getClass().getName());
        }
    }

    /**
     * This test tests inserting items into a hash table
     */
    @Test
    public void test002_insert_basic() {
        try {
            H1.insert(1, "one"); // insert a bunch of values
            H1.insert(2, "two");
            H1.insert(3, "three");
            H1.insert(4, "four");
            H1.insert(5, "five");

            if (H1.numKeys() != 5) { // check the keys
                fail("num keys is incorrect after inserting 5 items");
            }
            if (H1.getLoadFactor() != .5) { // check the load factor
                fail("load factor is incorrect after inserting 5 items");
            }
            if (!H1.get(1).equals("one")) { // check if a value is correct
                fail("get did not return the correct value");
            }
        } catch (IllegalNullKeyException e) {
            fail("should not throw INK exception");
        } catch (DuplicateKeyException e) {
            fail("should not throw DK exception");
        } catch (KeyNotFoundException e) {
            fail("should not throw KNF exception");
        }
    }

    /**
     * This test tests if the load factors are calculated correctly
     */
    @Test
    public void test003_get_loadFactor() {
        try {
            H1.insert(1, "one"); // insert 5 values for each table
            H1.insert(2, "two");
            H1.insert(3, "three");
            H1.insert(4, "four");
            H1.insert(5, "five");

            H2.insert("1", "one");
            H2.insert("2", "two");
            H2.insert("3", "three");
            H2.insert("4", "four");
            H2.insert("5", "five");

            if(H1.getLoadFactor() != .5) { // load factor should be .5
                fail("load factor for H1 is not correct");
            }

            if(H2.getLoadFactor() != .2) { // load factor should be .2
                fail("load factor for H2 is not correct");
            }

        } catch (Exception e) {
            fail("should not throw exception");
        }
    }

    /**
     * Test to make sure the constructor properly sets the load factor threshold and
     * make sure the getter returns them correctly
     */
    @Test
    public void test004_getLoadFactorThreshold() {
        if (H1.getLoadFactorThreshold() != .75) {
            fail("Table threshold for H1 is not correct");
        }

        if (H2.getLoadFactorThreshold() != .25) {
            fail("Table threshold for H2 is not correct");
        }
    }

    /**
     * Test to determine if the functionality of get and remove work properly
     */
    @Test
    public void test005_get_and_remove() {
        try {
            H1.insert(1, "one");
            H1.insert(2, "two");
            H1.insert(3, "three");
            H1.insert(4, "four");
            H1.insert(5, "five");

            if(!H1.get(2).equals("two")) { // get should return the value "two"
                fail("get does not return the correct value");
            }

            if(!H1.remove(2)) { // remove should return true if success
                fail("key failed to remove");
            }

            if(H1.remove(2)) { // this key doesn't exist so remove should return false
                fail("key does not exist so remove should return false");
            }
        } catch (Exception e) {
            fail("should not throw exception");
        }

    }

}
