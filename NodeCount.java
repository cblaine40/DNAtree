/**
 * 
 */

/**
 * Class to keep track of the number of nodes visited during a search.
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 *
 */
public class NodeCount {

    // Field for the count of visited nodes
    private int count;

    /**
     * Constructor
     */
    public NodeCount() {
        count = 0;
    }


    /**
     * Increment the count by 1
     */
    public void countUp() {
        count++;
    }


    /**
     * Return the number of visited nodes
     * 
     * @return integer of the count
     */
    public int getNodeCount() {
        return count;
    }

}
