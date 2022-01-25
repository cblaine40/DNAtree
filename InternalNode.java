
/**
 * 
 */

/**
 * This class represents an Internal Node. Each internal node has 5 children
 * that represent the letters A, C, G, T, or an extra node $
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 *
 */
public class InternalNode extends NodeDNA<char[]> {
    /**
     * Node to represent the a child
     */
    private NodeDNA<char[]> a;

    /**
     * Node to represent the c child
     */
    private NodeDNA<char[]> c;

    /**
     * Node to represent the g child
     */
    private NodeDNA<char[]> g;

    /**
     * Node to represent the t child
     */
    private NodeDNA<char[]> t;

    /**
     * Node to represent the $ child
     */
    private NodeDNA<char[]> extra;
    private static Flyweight flyweight = new Flyweight();
    private int deep;

    /**
     * Constructor for the InternalNode. Set five empty children for the
     * InternalNode
     * 
     * @param depth
     *            the depth to set the InternalNode to
     */
    public InternalNode(int depth) {

        // Make five empty leaf nodes
        a = flyweight;
        c = flyweight;
        g = flyweight;
        t = flyweight;
        extra = flyweight;

        setDepth(depth);
    }


    /**
     * This method is to remove an InternalNode.
     * 
     * @param root
     *            the node that remove is being called on
     * @param sequence
     *            the sequence you are trying to remove
     * @return the node you are calling remove on next
     */
    @Override
    public NodeDNA<char[]> remove(NodeDNA<char[]> root, char[] sequence) {

        char letter;
        // if the sequence is the size of depth
        if (sequence.length > this.getDepth()) {
            letter = sequence[this.getDepth()];

        }
        else {
            // the sequence must be located at the same depth
            // therefore at $
            letter = '$';

        }

        NodeDNA<char[]> node = findNode(letter).remove(root, sequence);

        // set the removed node to the node at the letter
        insertNodeDNA(node, letter);

        return shrinkSizeReturn();

    }


    /**
     * Helper the method to get the number of flyweight.
     * 
     * @return the number of flyweights
     */
    private int flyweightCount() {

        int numFlys = 0;
        if (a instanceof Flyweight) {
            numFlys++;

        }
        if (c instanceof Flyweight) {
            numFlys++;
        }
        if (g instanceof Flyweight) {
            numFlys++;
        }
        if (t instanceof Flyweight) {
            numFlys++;
        }
        if (extra instanceof Flyweight) {
            numFlys++;
        }
        return numFlys;

    }


    /**
     * Helper method to get the number of LeafNodes.
     * 
     * @return the number of LeafNodes
     * 
     */
    private int leafNodeCount() {
        int leaf = 0;
        if (a instanceof LeafNode) {
            leaf++;

        }
        if (c instanceof LeafNode) {
            leaf++;
        }
        if (g instanceof LeafNode) {
            leaf++;
        }
        if (t instanceof LeafNode) {
            leaf++;
        }
        if (extra instanceof LeafNode) {
            leaf++;
        }
        return leaf;
    }


    /**
     * Helper method for remove. It will either return a flyweight if all the
     * children are flyweight or it will return a LeafNode if only one is a
     * LeafNode.
     * 
     * @return the node to be returned
     */
    private NodeDNA<char[]> shrinkSizeReturn() {
        if (flyweightCount() == 5) {
            return flyweight;
        }
        if (leafNodeCount() == 1 && flyweightCount() == 4) {
            NodeDNA<char[]> nextRoot;

            // Find the leaf node
            if (a instanceof LeafNode) {
                nextRoot = a;
            }
            else if (g instanceof LeafNode) {
                nextRoot = g;
            }
            else if (c instanceof LeafNode) {
                nextRoot = c;
            }
            else if (t instanceof LeafNode) {
                nextRoot = t;
            }
            else {
                nextRoot = extra;
            }

            // Set its depth back one
            nextRoot.setDepth(nextRoot.getDepth() - 1);
            return nextRoot;

        }
        else {
            // Return the same node if it doesn't match the cases
            return this;
        }

    }


    /**
     * Setter method to set the depth of the node. The children of the internal
     * node are set 1 more then the parameter.
     * 
     * @param depth
     *            the number to set the depth to
     */
    @Override
    public void setDepth(int depth) {
        deep = depth;

        // Set the depth for the flyweight children depth + 1
        a.setDepth(depth + 1);
        c.setDepth(depth + 1);
        g.setDepth(depth + 1);
        t.setDepth(depth + 1);
        extra.setDepth(depth + 1);

    }


    /**
     * Getter method to get the depth
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
        // Increase the number of nodes searched
        numNodes.countUp();

        // Char list containing all the letters
        char[] letters = { 'A', 'C', 'G', 'T', '$' };
        if (node.length > this.getDepth()) {
            // find the node with the character at this depth
            this.findNode(node[getDepth()]).search(node, sequenceList,
                numNodes);
        }
        else {
            // make a for loop going through each character
            for (int i = 0; i < letters.length; i++) {
                // find the node for that character matching the descriptor
                this.findNode(letters[i]).search(node, sequenceList, numNodes);

            }
        }

    }


    /**
     * Method to insert a node into one of the children of the InternalNode.
     * 
     * @param node
     *            the node to insert into one of the children
     * @param letter
     *            the letter to identify which children to enter into
     */
    public void insertNodeDNA(NodeDNA<char[]> node, char letter) {
        if (letter == 'A') {
            a = node;
        }
        else if (letter == 'C') {
            c = node;
        }
        else if (letter == 'G') {
            g = node;

        }
        else if (letter == 'T') {
            t = node;
        }
        else {
            extra = node;
        }
    }


    /**
     * Method to call insert on an internal node. Get the identifier to find
     * what child node to go to in order to insert on a flyweight.
     * 
     * @param location
     *            the depth of where the node is being asked to be inserted
     * 
     * @param sequence
     *            The sequence to be inserted
     */
    @Override
    public NodeDNA<char[]> insert(int location, char[] sequence) {
        char label;

        if (sequence.length > this.getDepth()) {
            // get the character for the value it is located
            label = sequence[this.getDepth()];

        }
        else {
            // else print it at the $
            label = '$';

        }
        // insert the new internal node at the next depth for the specified
        // character
        NodeDNA<char[]> insert = findNode(label).insert(getDepth() + 1,
            sequence);

        // Make this new node
        insertNodeDNA(insert, label);
        return this;

    }


    /**
     * Method to find a node for a specific letter
     * 
     * @param value
     *            the letter to identify which node to fine
     * @return the node that matches the character letter
     */
    public NodeDNA<char[]> findNode(char value) {
        if (value == 'A') {
            return a;
        }
        else if (value == 'G') {
            return g;
        }
        else if (value == 'C') {
            return c;
        }
        else if (value == 'T') {
            return t;
        }
        else {
            return extra;
        }

    }


    /**
     * Print I to represent an InternalNode. Print each node 2 times from it's
     * parent.
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    @Override
    public void print(int depth) {
        String print = "";

        for (int i = 0; i < (getDepth() * 2); i++) {
            print += " ";
        }
        print += "I";

        System.out.println(print);
        
        //print out each child
        a.print(getDepth());
        c.print(getDepth());
        g.print(getDepth());
        t.print(getDepth());
        extra.print(getDepth());
        

    }


    /**
     * Print I to represent an InternalNode. Print each node 2 times from it's
     * parent.
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    @Override
    public void printLen(int depth) {
        String print = "";

        for (int i = 0; i < (getDepth() * 2); i++) {
            print += " ";
        }
        print += "I";

        System.out.println(print);
        
        //print out each child
        a.printLen(getDepth());
        c.printLen(getDepth());
        g.printLen(getDepth());
        t.printLen(getDepth());
        extra.printLen(getDepth());

    }


    /**
     * Print I to represent an InternalNode. Print each node 2 times from it's
     * parent.
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    @Override
    public void printStats(int depth) {
        String print = "";

        for (int i = 0; i < (getDepth() * 2); i++) {
            print += " ";
        }
        print += "I";

        System.out.println(print);
        
        //print out each child
        a.printStats(getDepth());
        c.printStats(getDepth());
        g.printStats(getDepth());
        t.printStats(getDepth());
        extra.printStats(getDepth());

    }

}
