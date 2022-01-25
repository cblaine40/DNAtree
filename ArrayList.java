import java.util.NoSuchElementException;

/**
 * This class is an implementation of a generic ArrayList in java
 * Some of the code in this class is taken from OpenDSA module 7.3
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 *
 * @param <T>
 *            the generic type of what the array list will hold
 */
public class ArrayList<T> {

    private T[] listArray; // Array holding list elements
    private static final int DEFAULT_SIZE = 50; // Default size
    private int maxSize; // Maximum size of list
    private int listSize; // Current # of list items
    private int curr; // Position of current element, starts at 0

    /**
     * This constructor takes in the size and creates a list of length size
     * 
     * @param size
     *            the length of the list
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int size) {
        maxSize = size;
        listSize = 0;
        curr = 0;
        listArray = (T[])new Object[size]; // Create listArray
    }


    /**
     * This creates a list with the default size
     */
    public ArrayList() {
        this(DEFAULT_SIZE);
    }


    /**
     * Tells if the list is empty or not
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        return listSize == 0;
    }


    /**
     * Gets the max size the list can be
     * 
     * @return int max size of list
     */
    public int getMaxSize() {
        return maxSize;
    }


    /**
     * Clears the list like reinitializing
     */
    public void clear() {
        listSize = 0;
        curr = 0; // Simply reinitialize values
    }


    /**
     * Inserts the element at the current position
     * 
     * @param elem
     *            the element to be inserted into the list
     * @return true if insert successful
     */
    public boolean insert(T elem) {
        // if list is full
        if (listSize >= maxSize) {
            return false;
        }
        // Need to make room to add the element
        for (int i = listSize; i > curr; i--) {
            listArray[i] = listArray[i - 1];
        }
        listArray[curr] = elem;
        listSize++; // Increment list size
        return true;
    }


    /**
     * Inserts the element at the end of the list
     * 
     * @param elem
     *            the item to insert
     * @return true if insert successful
     */
    public boolean append(T elem) {
        if (listSize >= maxSize) {
            return false;
        }
        listArray[listSize++] = elem;
        return true;
    }


    /**
     * Removes the current element
     * 
     * @return element that was removed
     * @throws NoSuchElementException
     */
    public T remove() throws NoSuchElementException {
        if ((curr < 0) || (curr >= listSize)) { // No current element
            throw new NoSuchElementException();
        }
        T elem = listArray[curr]; // Copy the element
        // shift the elements next to curr elem one place to left
        for (int i = curr; i < listSize - 1; i++) {
            listArray[i] = listArray[i + 1];
        }
        listSize--; // Decrement size
        return elem;
    }


    /**
     * To set curr index to 0
     */
    public void moveToStart() { // Set to front
        curr = 0;
    }


    /**
     * Set curr index to end
     */
    public void moveToEnd() { // Set at end
        curr = listSize - 1;
    }


    /**
     * Tells the length of the list
     * 
     * @return int length
     */
    public int length() {
        return listSize;
    }


    /**
     * Returns the current position
     * 
     * @return int position
     */
    public int currPos() {
        return curr;
    }


    /**
     * Moves the curr to a particular position
     * 
     * @param pos
     *            position to move to
     * @return true if move possible else false
     */
    public boolean moveToPos(int pos) {
        if (pos > listSize) {
            return false;
        }
        curr = pos;
        return true;
    }


    /**
     * Tells the current element
     * 
     * @return the elem
     * @throws NoSuchElementException
     */
    public T getValue() throws NoSuchElementException {
        if ((curr < 0) || (curr >= listSize)) {
            // No current element
            throw new NoSuchElementException();
        }
        return listArray[curr];
    }

}
