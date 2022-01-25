
/**
 * This class is an implementation of a 5 branch tree used to store sequences in
 * this project
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 */
public class Tree {

    // root refers to the first node/root of the tree
    private NodeDNA<char[]> root;
    // an instance of Flyweight
    private Flyweight fly = new Flyweight();

    /**
     * The start depth for the tree
     */
    public static final int ROOT_DEPTH = 0;

    /**
     * Constructor initializes the tree to an empty state
     */
    public Tree() {
        // Empty state is a flyweight
        root = fly;
    }


    /**
     * This is the recursive implementation of the insert method for the tree
     * 
     * This method is recursive as the insert method of the node being called in
     * this method is implemented recursively where flyweight is the basecase
     * and internalNode insert is the recursive
     * 
     * @param sequence
     *            the sequence to be inserted
     */
    public void insert(char[] sequence) {

        if (sequence == null) {
            return;
        }

        // Call the recursive insert here
        root = root.insert(ROOT_DEPTH, sequence);
    }


    /**
     * This is the remove method that removes the specified sequence
     * 
     * This method is recursive as the remove method of the node being called in
     * this method is implemented recursively where leafnode is the basecase and
     * InternalNode remove is the recursive
     * 
     * @param sequence
     *            the sequence to be removed
     */
    public void remove(char[] sequence) {
        if (sequence == null) {
            return;
        }

        // Call the recursive node remove here
        root = root.remove(root, sequence);

    }


    /**
     * This is the normal print implementation for the tree
     * Prints the tree structure
     * Takes help from the recursive print helper method
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    public void print(int depth) {

        System.out.println("tree dump:");
        if (root == fly) {
            // tree is empty
            System.out.println("E");
            return;
        }
        root.print(depth);

    }


    /**
     * This method prints the structure of the tree with the stat of the
     * sequence
     * This takes help from the print helper
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    public void printStat(int depth) {

        System.out.println("tree dump:");
        if (root == fly) {
            // tree is empty
            System.out.println("E");
            return;
        }
        // call the helper print from the beginning of the tree
        // stat is true
        root.printStats(depth);

    }


    /**
     * This method prints the structure of the tree with the length of the
     * sequence
     * This takes help from the print helper
     * 
     * @param depth
     *            the depth of the node you are printing
     */
    public void printLength(int depth) {

        System.out.println("tree dump:");
        if (root == fly) {
            // tree is empty
            System.out.println("E");
            return;
        }
        // call the helper print from the beginning of the tree
        // len is true

        root.printLen(depth);

    }


    /**
     * This method searches the tree for a sequence, prints exact match if
     * called with $ else prints where sequence is found with prefix
     * 
     * This method is recursive as the search method of the node being called in
     * this method is implemented recursively where leafnode and flyweight are
     * the basecases while the internalNode remove is the recursive
     * 
     * @param sequence
     *            the sequence to be searched
     */
    public void search(char[] sequence) {
        ArrayList<char[]> sequenceListFound = new ArrayList<char[]>();
        NodeCount nodeCnt = new NodeCount();

        if (sequence == null) {
            return;
        }

        // Call the recursive search here
        root.search(sequence, sequenceListFound, nodeCnt);

        // The recursive call to the search finds the matches and adds to the
        // arrayList and the node count to the nodeCnt
        sequenceListFound.moveToPos(ROOT_DEPTH); // the beginning of the list
        if (sequenceListFound.length() == 0) {
            System.out.println("# of nodes visited: " + nodeCnt.getNodeCount());
            System.out.println("no sequence found");
        }
        else {
            System.out.println("# of nodes visited: " + nodeCnt.getNodeCount());
        }
        for (int i = 0; i < sequenceListFound.length(); i++) {
            System.out.println("sequence: " + String.valueOf(sequenceListFound
                .getValue()));
            sequenceListFound.moveToPos(i + 1);
        }
    }

}
