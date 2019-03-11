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
    private ArrayList<ArrayList<HashNode>> hashTable;

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
        this(10, .75);
    }

    // TODO: comment and complete a constructor that accepts
    // initial capacity and load factor threshold
    // threshold is the load factor that causes a resize and rehash
    public HashTable(int initialCapacity, double loadFactorThreshold) {
        this.capacity = initialCapacity;
        this.loadFactorThreshold = loadFactorThreshold;
        numKeys = 0;
        hashTable = new ArrayList<ArrayList<HashNode>>(this.capacity);
        for(int i = 0; i < this.capacity; i++) {
            hashTable.add(i, new ArrayList<HashNode>());
        }
    }

    @Override public double getLoadFactorThreshold() {
        return loadFactorThreshold;
    }

    @Override public double getLoadFactor() {
        return (double)numKeys / capacity;
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

        int hashID = currentNode.key.hashCode();
        int hashIndex = Math.abs(hashID) % capacity;

        for(int i = 0; i < hashTable.get(hashIndex).size(); i++) {
            if(hashTable.get(hashIndex).get(i).key.equals(currentNode.key)) {
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
