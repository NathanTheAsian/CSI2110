/**
 * Array Heap implementation of a priority queue
 * @author Lachlan Plant
 * Completed by Nathan Ng, 300494064.
 */
public class HeapPriorityQueue<K extends Comparable,V> implements PriorityQueue<K ,V> {
    
    private Entry[] storage; //The Heap itself in array form
    private int tail;        //Index of last element in the heap
    
    /**
    * Default constructor with capacity for 100 entries.
    */
    public HeapPriorityQueue(){
        this(100);
    }
    
    /**
    * HeapPriorityQueue constructor with max storage of size elements
    */
    public HeapPriorityQueue(int size){
        if (size < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        storage = new Entry[size];
        tail = -1;
    }
    
    /****************************************************
     * 
     *             Priority Queue Methods
     * 
     ****************************************************/
    
    /**
    * Returns the number of items in the priority queue.
    * O(1)
    * @return number of items
    */
    public int size(){
        return tail + 1;
    }

    /**
    * Tests whether the priority queue is empty.
    * O(1)
    * @return true if the priority queue is empty, false otherwise
    */
    public boolean isEmpty(){
        return tail == -1;
    }
    
    /**
    * Inserts a key-value pair and returns the entry created.
    * O(log(n))
    * @param key     the key of the new entry
    * @param value   the associated value of the new entry
    * @return the entry storing the new key-value pair
    * @throws IllegalArgumentException if the heap is full
    */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        if (size() == storage.length) {
            throw new IllegalArgumentException("Heap is full");
        }
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        Entry<K,V> entry = new Entry<>(key, value);
        storage[++tail] = entry;
        upHeap(tail);
        return entry;
    }
    
    /**
    * Returns (but does not remove) an entry with minimal key.
    * O(1)
    * @return entry having a minimal key (or null if empty)
    */
    public Entry<K,V> min(){
        return isEmpty() ? null : storage[0];
    } 
    
    /**
    * Removes and returns an entry with minimal key.
    * O(log(n))
    * @return the removed entry (or null if empty)
    */ 
    public Entry<K,V> removeMin(){
        if (isEmpty()) {
            return null;
        }
        Entry<K,V> minimum = storage[0];
        storage[0] = storage[tail];
        storage[tail--] = null;
        if (!isEmpty()) {
            downHeap(0);
        }
        return minimum;
    }  
    
    
    /****************************************************
     * 
     *           Methods for Heap Operations
     * 
     ****************************************************/
    
    /**
    * Algorithm to place element after insertion at the tail.
    * O(log(n))
    */
    private void upHeap(int location){
        while (location > 0) {
            int parentLocation = parent(location);
            if (storage[location].getKey().compareTo(storage[parentLocation].getKey()) >= 0) {
                break;
            }
            swap(location, parentLocation);
            location = parentLocation;
        }
    }
    
    /**
    * Algorithm to place element after removal of root and tail element placed at root.
    * O(log(n))
    */
    private void downHeap(int location){
        while (2 * location + 1 <= tail) {
            int smallerChild = 2 * location + 1;
            int rightChild = smallerChild + 1;
            if (rightChild <= tail &&
                    storage[rightChild].getKey().compareTo(storage[smallerChild].getKey()) < 0) {
                smallerChild = rightChild;
            }
            if (storage[location].getKey().compareTo(storage[smallerChild].getKey()) <= 0) {
                break;
            }
            swap(location, smallerChild);
            location = smallerChild;
        }
    }
    
    /**
    * Find parent of a given location,
    * Parent of the root is the root
    * O(1)
    */
    private int parent(int location){
        return location == 0 ? 0 : (location - 1) / 2;
    }
    
   
    /**
    * Inplace swap of 2 elements, assumes locations are in array
    * O(1)
    */
    private void swap(int location1, int location2){
        Entry temporary = storage[location1];
        storage[location1] = storage[location2];
        storage[location2] = temporary;
    }
    
}
