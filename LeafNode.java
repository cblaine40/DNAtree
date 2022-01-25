import java.text.DecimalFormat;

/**
 * 
 */

/**
 * LeafNode class holds the sequence for the tree.
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 *
 */
public class LeafNode extends NodeDNA<char[]> {
    private char[] info;
    private int deep;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private static Flyweight fly = new Flyweight();

    /**
     * Constructor to initialize the leaf node that holds data (sequence)
     * 
     * @param sequence
     *            the DNA
     * @param depth
     *            the position to set the node too
     */
    public LeafNode(char[] sequence, int depth) {
        setDNA(sequence);
        setDepth(depth);

    }


    /**
     * Setter method to set the sequence in the LeafNode.
     * 
     * @param sequence
     *            what to set the LeafNode to
     */
    public void setDNA(char[] sequence) {
        info = sequence;
    }


    /**
     * Getter for the data inside the LeafNode
     * 
     * @return data sequence
     */
    public char[] getDNA() {
        return info;
    }


    /**
     * This method is to remove a LeafNode.
     * 
     * @param node
     *            the node that remove is being called on
     * @param sequence
     *            the sequence you are trying to remove
     * @return the node you are calling remove on next
     */
    @Override
    public NodeDNA<char[]> remove(NodeDNA<char[]> node, char[] sequence) {

        String newSeq = String.valueOf(sequence);
        String oldSeq = String.valueOf(this.getDNA());
        // compare the two sequences
        if (newSeq.equals(oldSeq)) {

            System.out.println("sequence " + newSeq + " removed");

            // return empty leafnode
            return fly;

        }
        // otherwise the sequence does not exist
        System.out.println("sequence " + newSeq + " does not exist");
        return this;
    }


    /**
     * A setter method to set the depth.
     * 
     * @param depth
     *            the depth you are setting the node for
     */
    @Override
    public void setDepth(int depth) {
        deep = depth;

    }


    /**
     * Getter method for the depth
     * 
     * @return the depth
     */
    @Override
    public int getDepth() {
        return deep;
    }


    /**
     * This method is to search for a sequence inside the tree. It may be
     * looking for an exact sequence or using a prefix of a sequence.
     * 
     * @param node
     *            the sequence descriptor that you are searching
     * @param sequenceList
     *            the list that you put the sequence in if you find it
     * @param numNodes
     *            the number of nodes that you have searched
     */
    @Override
    public void search(
        char[] node,
        ArrayList<char[]> sequenceList,
        NodeCount numNodes) {

        // Increments the number of nodes searched
        numNodes.countUp();

        // set the depth to - 1 to check inside the $ node
        int value = node.length - 1;

        // Set the node to a string
        String seqDescriptor = String.valueOf(node);

        // String together this sequence
        String thisSeq = String.valueOf(getDNA());
        // check to see if it wants an exact match
        if (node[value] == '$') {
            // Make a substring without the last $
            String subbed = seqDescriptor.substring(0, node.length - 1);

            // if the sequence and the node are equal
            if (thisSeq.equals(subbed)) {
                // insert the char array into the list
                sequenceList.append(this.getDNA()); // inserting at the end of
                                                    // the list
            }

        }
        else {

            // if the sequence begins with the seq descriptor
            if (thisSeq.startsWith(seqDescriptor)) {
                sequenceList.append(getDNA());
            }

        }

    }


    /**
     * This method is to insert on a LeafNode.
     * 
     * @param location
     *            the depth where you are calling insert
     * @param sequence
     *            the sequence to insert
     * @return The node to call insert on next
     */
    @Override
    public NodeDNA<char[]> insert(int location, char[] sequence) {
        // If the sequences already exists print
        if (String.valueOf(this.getDNA()).equals(String.valueOf(sequence))) {
            System.out.println("sequence " + String.valueOf(sequence)
                + " already exists");
            return this;
        }

        // First make a new internal node at the current location
        InternalNode in = new InternalNode(getDepth());

        // Set LeafNode depth +1
        this.setDepth(getDepth() + 1);

        // Case 1: the sequence == depth then insert it at the $
        // Case 2: sequence > depth then insert + 1

        // Old leaf node: case 1
        if (this.getDNA().length == in.getDepth()) {
            // Insert the leaf node into the $
            in.insertNodeDNA(this, '$');
        }
        // Old leaf node: case 2
        else {
            // insert the old leaf node at the next position
            in.insertNodeDNA(this, this.getDNA()[location]);

        }

        // New leaf node: case 1
        if (sequence.length == in.getDepth()) {
            // create a new leaf node
            LeafNode leaf = new LeafNode(sequence, getDepth());

            // insert that leaf into $
            in.insertNodeDNA(leaf, '$');

            // Print out where it was inserted
            System.out.println("sequence " + String.valueOf(sequence)
                + " inserted at level " + leaf.getDepth());

        }
        else {
            // Call insert on the internal node
            in.insert(getDepth(), sequence);

        }

        // return the internal node
        return in;

    }


    /**
     * Get the statistics of a specific protein in the sequence
     * 
     * @param data
     *            the sequence to find the stats for
     * @param protein
     *            the single character in the sequence
     * @return a string of the statistic for the protein
     */
    private String getStatistic(char[] data, char protein) {
        double numChar = 0.00;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == protein) {
                numChar++;

            }

        }

        return df.format((numChar * 100.00) / data.length);

    }


    /**
     * Print method to print the entire sequence
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    @Override
    public void print(int depth) {
        String str = "";
        for (int i = 0; i < (getDepth() * 2); i++) {
            str += " ";
        }
        str += String.valueOf(this.getDNA());
        System.out.println(str);

    }


    /**
     * Print method to print the entire sequence and the length
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    @Override
    public void printLen(int depth) {
        String str = "";
        for (int i = 0; i < (getDepth() * 2); i++) {
            str += " ";
        }
        str += String.valueOf(this.getDNA());

        System.out.println(str + " " + this.getDNA().length);

    }


    /**
     * Print out the string and the statistic of each letter
     * 
     * @param depth
     */
    @Override
    public void printStats(int depth) {
        String str = "";
        for (int i = 0; i < (getDepth() * 2); i++) {
            str += " ";
        }
        str += String.valueOf(this.getDNA());

        str += (" A:" + getStatistic(this.getDNA(), 'A'));
        str += (" C:" + getStatistic(this.getDNA(), 'C'));
        str += (" G:" + getStatistic(this.getDNA(), 'G'));
        str += (" T:" + getStatistic(this.getDNA(), 'T'));

        System.out.println(str);

    }

}
