
/**
 * 
 */

/**
 * This class is an empty LeafNode
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 *
 */
public class Flyweight extends NodeDNA<char[]> {

    /**
     * Empty constructor
     */
    public Flyweight() {
        // leave empty
    }


    /**
     * Method to remove a flyweight. If remove is called on a flyweight it means
     * the sequence doesn't exist.
     * 
     * @param root
     *            the node that remove is being called on
     * @param sequence
     *            the sequence you are trying to remove
     * @return the node you are calling remove on next
     */
    @Override
    public NodeDNA<char[]> remove(NodeDNA<char[]> root, char[] sequence) {

        System.out.println("sequence " + String.valueOf(sequence)
            + " does not exist");
        return this;
    }


    /**
     * Leave this empty for a flyweight
     * 
     * @param depth
     *            the depth to set it to
     */
    @Override
    public void setDepth(int depth) {
        // leave empty

    }


    /**
     * Return 0 for the flyweight
     * 
     * @return the depth
     */
    @Override
    public int getDepth() {
        return 0;
    }


    /**
     * Flyweight is the base case for the insert method. Print the sequence and
     * where it was printed
     * 
     * @param location
     *            the depth of where the node is being asked to be inserted
     * 
     * @param sequence
     *            The sequence to be inserted
     */
    @Override
    public NodeDNA<char[]> insert(int location, char[] sequence) {

        System.out.println("sequence " + String.valueOf(sequence)
            + " inserted at level " + location);

        // add a new leaf node with the sequence at the correct depth
        return (new LeafNode(sequence, location));

    }


    /**
     * Print method. Print E for a flyweight and indent it 2 times from it's
     * parent.
     * 
     * @param depth
     *            the depth of the node to print
     */
    @Override
    public void print(int depth) {
        String str = "";

        // children are indented two times then their parent

        for (int i = 0; i < 2 * (depth + 1); i++) {
            str += " ";
        }
        str += "E";
        System.out.println(str);

    }


    /**
     * Print method. Print E for a flyweight and indent it 2 times from it's
     * parent.
     * 
     * @param depth
     *            the depth of the node to print
     */
    @Override
    public void printLen(int depth) {
        // print E
        print(depth);

    }


    /**
     * Print method. Print E for a flyweight and indent it 2 times from it's
     * parent.
     * 
     * @param depth
     *            the depth of the node to print
     */
    @Override
    public void printStats(int depth) {
        print(depth);

    }


    /**
     * This method is to search for a sequence inside the tree. Searching a
     * flyweight will just increase the node count.
     * 
     * @param value
     *            the sequence descriptor that you are searching
     * @param sequence
     *            the list that you put the sequence in if you find it
     * @param numNodes
     *            the number of nodes that you have searched
     */
    @Override
    public void search(
        char[] value,
        ArrayList<char[]> sequence,
        NodeCount numNodes) {
        numNodes.countUp();

    }

}
