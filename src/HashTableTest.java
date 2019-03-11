// TODO: add imports as needed

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
 
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
import java.util.Random;



/** TODO: add class header comments here*/
public class HashTableTest{

    // TODO: add other fields that will be used by multiple tests
    HashTable<Integer, String>H1;
    HashTable<String, String>H2;
    
    // TODO: add code that runs before each test method
    @Before public void setUp() throws Exception {
        H1 = new HashTable<>(10, .75);
        H2 = new HashTable<>(25, .25);
    }

    // TODO: add code that runs after each test method
    @After public void tearDown() throws Exception {
        H1 = null;
        H2 = null;
    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }

    @Test public void test002_insert_basic() {
        try {
            H1.insert(1, "one");
            H1.insert(2, "two");
            H1.insert(3, "three");
            H1.insert(4, "four");
            H1.insert(5, "five");

            if(H1.numKeys() != 5) {
                fail("num keys is incorrect after inserting 5 items");
            }

            if(H1.getLoadFactor() != .5) {
                fail("load factor is incorrect after inserting 5 items");
            }

            if(!H1.get(1).equals("one")) {
                fail("get did not return the correct value");
            }
        } catch (IllegalNullKeyException e) {
            fail("should not throw INK exception");
        } catch (DuplicateKeyException e) {
            fail("should not throw DK exception");
        } catch(KeyNotFoundException e) {
            fail("should not throw KNF exception");
        }






    }
    
    // TODO add your own tests of your implementation
    
}
