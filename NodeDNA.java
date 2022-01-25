
/**
 * Super class for the different DNA nodes
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 * 
 * @param <T>
 *            the object that will be stored in the NodeDNA
 *
 */
public abstract class NodeDNA<T> {
    /**
     * Set the node to the value you want
     * 
     * @param location
     *            where to insert it
     * @param sequence
     *            the sequence to insert
     * @return the node to call insert on next
     */
    public abstract NodeDNA<T> insert(int location, T sequence);


    /**
     * Removes and returns the Node of the sequence put in
     * 
     * @param node
     *            to call remove on
     * @param sequence
     *            the sequence to remove
     * @return the next node to call remove on
     */
    public abstract NodeDNA<T> remove(NodeDNA<T> node, T sequence);


    /**
     * Setter method to set the depth.
     * 
     * @param depth
     *            the location to set it to
     */
    public abstract void setDepth(int depth);


    /**
     * Getter method to get the depth
     * 
     * @return the depth
     */
    public abstract int getDepth();


    /**
     * Search for a value. If found put it into the list.
     * 
     * @param value
     *            the sequence to search for
     * @param sequenceList
     *            the list to add the sequence to if found
     * @param numNodes
     *            the number of nodes that had been visited
     */
    public abstract void search(
        T value,
        ArrayList<char[]> sequenceList,
        NodeCount numNodes);


    /**
     * Using a preorder traversal it prints out the node structure and the
     * sequence it contains
     * 
     * @param depth
     *            the depth of the node
     */
    public abstract void print(int depth);


    /**
     * Print everything in the first print and the length of the sequence
     * 
     * @param depth
     *            the depth of the node
     */
    public abstract void printLen(int depth);


    /**
     * Print like the first print but with the stats of each letter in the
     * sequence
     * 
     * @param depth
     *            the depth of the node
     */
    public abstract void printStats(int depth);

}
