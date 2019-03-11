// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture



import java.util.ArrayList;

//
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

    // TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
    private double loadFactorThreshold;
    private int capacity;
    private int numKeys;
    private ArrayList<ArrayList<K>> hashTable;

    private class HashNode {
        private K key;
        private V value;
        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    // TODO: comment and complete a default no-arg constructor
    public HashTable() {
        this.capacity = 0; // when creating a ht with no arguments we will just set fields to 0
        this.loadFactorThreshold = 0;
        numKeys = 0;
    }

    // TODO: comment and complete a constructor that accepts
    // initial capacity and load factor threshold
    // threshold is the load factor that causes a resize and rehash
    public HashTable(int initialCapacity, double loadFactorThreshold) {
        this.capacity = initialCapacity;
        this.loadFactorThreshold = loadFactorThreshold;
        numKeys = 0;
        ArrayList<ArrayList<K>> hashTable = new ArrayList<ArrayList<K>>(initialCapacity);
    }

    @Override public double getLoadFactorThreshold() {
        return loadFactorThreshold;
    }

    @Override public double getLoadFactor() {
        return numKeys / capacity;
    }

    @Override public int getCapacity() {
        return capacity;
    }

    @Override public int getCollisionResolution() {
        return 4;
    }

    @Override public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        if (key == null) { throw new IllegalNullKeyException(); }
        HashNode currentNode = new HashNode(key, value);

        int hashID = currentNode.hashCode();
        int hashIndex = getCapacity() % hashID;




        //hashTable.get()


    }

    @Override public boolean remove(K key) throws IllegalNullKeyException {
        if (key == null) { throw new IllegalNullKeyException(); }




        return false;
    }

    @Override public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        return null;
    }

    @Override public int numKeys() {
        return numKeys;
    }

    // TODO: add all unimplemented methods so that the class can compile



}
