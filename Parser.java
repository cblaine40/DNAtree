
/**
 * This class ...
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 *
 */
public class Parser {

    // To store the input command
    private String command;
    // The tree that needs to be build up
    private Tree tree;


    /**
     * This constructor assigns the input line to the field command
     * 
     * @param input
     *            the input line from the file
     * 
     * @param myTree
     *            the tree structure used
     */
    public Parser(String input, Tree myTree) {
        this.command = input;
        this.tree = myTree;
    }


    /**
     * It splits the input line and calls the appropriate function to process
     * the requested command
     */
    public void parseInput() {
        int len = 0; // To store the length of the sequence
        // split the input line according to space and then store it in an array
        String[] inputArr = (command.trim()).split("\\s+");

        // The data at index 0 is the command, and the sequence is at
        // inputArr[1] for commands that require it
        if (inputArr[0].equals("insert")) {
            len = inputArr[1].length();
            // Now, call the appropriate insert method
            tree.insert(setSequence(len, inputArr[1]));

        }
        else if (inputArr[0].equals("remove")) {
            len = inputArr[1].length();

            // Now, call the appropriate remove method
            tree.remove(setSequence(len, inputArr[1]));

        }
        else if (inputArr[0].equals("print")) {
            if (inputArr.length == 1) {
                // This is just "print"
                // Call the appropriate print method
                tree.print(-1);
            }
            else {
                if (inputArr[1].equals("stats")) {
                    // Call the appropriate print method
                    tree.printStat(-1);
                }
                else { // print lengths
                       // Call the appropriate method for print lengths
                    tree.printLength(-1);
                }
            }

        }
        else { // This would be the search
            len = inputArr[1].length();
            // Now, call the appropriate remove method
            tree.search(setSequence(len, inputArr[1]));

        }
    }


    /**
     * This is a helper method to store the sequence properly
     * 
     * @param len
     *            the length of the sequence
     * @param str
     *            the sequence input
     * 
     * @return the character array
     */
    public char[] setSequence(int len, String str) {
        char[] seq = new char[len];
        for (int i = 0; i < len; i++) {
            seq[i] = str.charAt(i);
        }
        return seq;
    }

}
