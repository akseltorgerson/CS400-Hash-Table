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
    
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {

    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {

    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
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
        HashTable hashTable;
        try {
            hashTable = new HashTable(10, .75);
            hashTable.insert(1, "one");
            hashTable.insert(2, "two");
            hashTable.insert(3, "three");
            hashTable.insert(4, "four");
            hashTable.insert(5, "five");

            if(hashTable.numKeys() != 5) {
                fail("num keys is incorrect after inserting 5 items");
            }

            if(hashTable.getLoadFactor() != .5) {
                fail("load factor is incorrect after inserting 5 items");
            }

            if(!hashTable.get(1).equals("one")) {
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
