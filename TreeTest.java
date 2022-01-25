
import student.TestCase;

/**
 * This class tests the tree class
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 */
public class TreeTest extends TestCase {

    private Tree myTree;
    private char[] sequence;
    private String stdout;


    /**
     * Sets up the environment for testing
     * Called before each test
     */
    public void setUp() {
        myTree = new Tree();
        sequence = new char[4];
        sequence[0] = 'A';
        sequence[1] = 'C';
        sequence[2] = 'G';
        sequence[3] = 'T';
    }


    /**
     * Test single insert on empty tree
     */
    public void testInsertEmptyTree() {
        myTree.insert(sequence);
        myTree.print(0);
        myTree.printLength(0);
        myTree.printStat(0);
        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 0" + "\n" + "tree dump:"
            + "\n" + "ACGT" + "\n" + "tree dump:" + "\n" + "ACGT 4" + "\n"
            + "tree dump:" + "\n" + "ACGT A:25.00 C:25.00 G:25.00 T:25.00"
            + "\n", stdout);
        systemOut().clearHistory();

    }


    /**
     * Check when sequence happen to be null
     */
    public void testInsertNull() {
        myTree.insert(null);
        stdout = systemOut().getHistory();
        assertEquals("", stdout);
        systemOut().clearHistory();
    }


    /**
     * Checks the insert method with multiple insertions
     */
    public void testInsert() {

        // sequence AAAA
        char[] seq2 = new char[4];
        seq2[0] = 'A';
        seq2[1] = 'A';
        seq2[2] = 'A';
        seq2[3] = 'A';

        // sequence AA
        char[] seq3 = new char[2];
        seq3[0] = 'A';
        seq3[1] = 'A';

        // sequence AAACCCCGGTGAAAACGTA
        char[] seq4 = new char[19];
        seq4[0] = 'A';
        seq4[1] = 'A';
        seq4[2] = 'A';
        seq4[3] = 'C';
        seq4[4] = 'C';
        seq4[5] = 'C';
        seq4[6] = 'C';
        seq4[7] = 'G';
        seq4[8] = 'G';
        seq4[9] = 'T';
        seq4[10] = 'G';
        seq4[11] = 'A';
        seq4[12] = 'A';
        seq4[13] = 'A';
        seq4[14] = 'A';
        seq4[15] = 'C';
        seq4[16] = 'G';
        seq4[17] = 'T';
        seq4[18] = 'A';

        // ACGT inserted first at level 0
        myTree.insert(sequence);
        myTree.insert(seq2);
        myTree.insert(seq3);

        myTree.insert(seq4);

        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 0" + "\n"
            + "sequence AAAA inserted at level 2" + "\n"
            + "sequence AA inserted at level 3" + "\n"
            + "sequence AAACCCCGGTGAAAACGTA inserted at level 4" + "\n",
            stdout);
        systemOut().clearHistory();

    }


    /**
     * Testing all the print methods for empty tree
     */
    public void testEmptyPrint() {
        myTree.print(-1);
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "E" + "\n", stdout);
        systemOut().clearHistory();

        myTree.printLength(0);
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "E" + "\n", stdout);
        systemOut().clearHistory();

        myTree.printStat(0);
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "E" + "\n", stdout);
        systemOut().clearHistory();

    }


    /**
     * Checks all the print methods including
     * print(), printLength(), printStat()
     */
    public void testPrints() {
        char[] seq = new char[4];
        seq[0] = 'A';
        seq[1] = 'A';
        seq[2] = 'T';
        seq[3] = 'A';

        myTree.insert(sequence);
        myTree.insert(seq);
        myTree.print(0);
        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 0" + "\n"
            + "sequence AATA inserted at level 2" + "\n" + "tree dump:" + "\n"
            + "I" + "\n" + "  I" + "\n" + "    AATA" + "\n" + "    ACGT" + "\n"
            + "    E" + "\n" + "    E" + "\n" + "    E" + "\n" + "  E" + "\n"
            + "  E" + "\n" + "  E" + "\n" + "  E" + "\n", stdout);
        systemOut().clearHistory();

        // Now, check print len
        myTree.printLength(0);
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "I" + "\n" + "  I" + "\n"
            + "    AATA 4" + "\n" + "    ACGT 4" + "\n" + "    E" + "\n"
            + "    E" + "\n" + "    E" + "\n" + "  E" + "\n" + "  E" + "\n"
            + "  E" + "\n" + "  E" + "\n", stdout);
        systemOut().clearHistory();

        // Now, check print stat
        myTree.printStat(0);
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "I" + "\n" + "  I" + "\n"
            + "    AATA A:75.00 C:0.00 G:0.00 T:25.00" + "\n"
            + "    ACGT A:25.00 C:25.00 G:25.00 T:25.00" + "\n" + "    E" + "\n"
            + "    E" + "\n" + "    E" + "\n" + "  E" + "\n" + "  E" + "\n"
            + "  E" + "\n" + "  E" + "\n", stdout);
        systemOut().clearHistory();

    }


    /**
     * Check remove when sequence happen to be null
     */
    public void testRemoveNull() {
        myTree.remove(null);
        stdout = systemOut().getHistory();
        assertEquals("", stdout);
        systemOut().clearHistory();
    }


    /**
     * Test remove method
     */
    public void testRemove() {
        // test remove error
        myTree.remove(sequence);

        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT does not exist" + "\n", stdout);
        systemOut().clearHistory();

        // Insert things into the tree
        char[] seq2 = new char[4];
        seq2[0] = 'A';
        seq2[1] = 'A';
        seq2[2] = 'A';
        seq2[3] = 'A';

        char[] seq3 = new char[2];
        seq3[0] = 'G';
        seq3[1] = 'A';

        myTree.insert(sequence);
        myTree.insert(seq2);
        myTree.insert(seq3);

        // Clear history of the out
        systemOut().clearHistory();
        myTree.print(0);
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "I" + "\n" + "  I" + "\n"
            + "    AAAA" + "\n" + "    ACGT" + "\n" + "    E" + "\n" + "    E"
            + "\n" + "    E" + "\n" + "  E" + "\n" + "  GA" + "\n" + "  E"
            + "\n" + "  E" + "\n", stdout);

        // remove
        systemOut().clearHistory();
        myTree.remove(seq2);
        stdout = systemOut().getHistory();

        assertEquals("sequence AAAA removed" + "\n", stdout);

        systemOut().clearHistory();

        // print the correct formation
        myTree.print(0);
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "I" + "\n" + "  ACGT" + "\n" + "  E"
            + "\n" + "  GA" + "\n" + "  E" + "\n" + "  E" + "\n", stdout);

    }


    /**
     * Check search when sequence happen to be null
     */
    public void testSearchNull() {
        myTree.search(null);
        stdout = systemOut().getHistory();
        assertEquals("", stdout);
        systemOut().clearHistory();
    }


    /**
     * This method tests the search with prefix
     */
    public void testSearchPrefix() {
        // sequence AAAA
        char[] seq2 = new char[4];
        seq2[0] = 'A';
        seq2[1] = 'A';
        seq2[2] = 'A';
        seq2[3] = 'A';

        // sequence AA
        char[] seq3 = new char[2];
        seq3[0] = 'A';
        seq3[1] = 'A';

        // sequence AAACCCCGGTGAAAACGTA
        char[] seq4 = new char[6];
        seq4[0] = 'A';
        seq4[1] = 'A';
        seq4[2] = 'A';
        seq4[3] = 'C';
        seq4[4] = 'C';
        seq4[5] = 'C';

        // search sequence
        char[] seqSearch = new char[3];
        seqSearch[0] = 'A';
        seqSearch[1] = 'A';
        seqSearch[2] = 'A';

        // ACGT inserted first at level 0
        myTree.insert(sequence);
        myTree.insert(seq2);
        myTree.insert(seq3);
        myTree.insert(seq4);
        systemOut().clearHistory(); // to clear the insert prints

        myTree.search(seqSearch);

        stdout = systemOut().getHistory();
        assertEquals("# of nodes visited: 9" + "\n" + "sequence: AAAA" + "\n"
            + "sequence: AAACCC" + "\n", stdout);
        systemOut().clearHistory();

    }


    /**
     * This method tests for exact matches with $
     */
    public void testSearchExact() {
        // sequence AAAA
        char[] seq2 = new char[4];
        seq2[0] = 'A';
        seq2[1] = 'A';
        seq2[2] = 'A';
        seq2[3] = 'A';

        // sequence AA
        char[] seq3 = new char[3];
        seq3[0] = 'A';
        seq3[1] = 'A';
        seq3[2] = 'A';

        // sequence AAACCCCGGTGAAAACGTA
        char[] seq4 = new char[6];
        seq4[0] = 'A';
        seq4[1] = 'A';
        seq4[2] = 'A';
        seq4[3] = 'C';
        seq4[4] = 'C';
        seq4[5] = 'C';

        // search sequence exact match due to $
        char[] seqSearch = new char[4];
        seqSearch[0] = 'A';
        seqSearch[1] = 'A';
        seqSearch[2] = 'A';
        seqSearch[3] = '$';

        // ACGT inserted first at level 0
        myTree.insert(sequence);
        myTree.insert(seq2);
        myTree.insert(seq3);
        myTree.insert(seq4);
        systemOut().clearHistory(); // to clear the insert prints
        myTree.search(seqSearch);

        stdout = systemOut().getHistory();
        assertEquals("# of nodes visited: 5" + "\n" + "sequence: AAA" + "\n",
            stdout);
        systemOut().clearHistory();

    }


    /**
     * No sequence found test
     */
    public void testSearchExactSeq() {
        // sequence AAAA
        char[] seq2 = new char[4];
        seq2[0] = 'A';
        seq2[1] = 'A';
        seq2[2] = 'A';
        seq2[3] = 'A';

        // sequence AA
        char[] seq3 = new char[3];
        seq3[0] = 'A';
        seq3[1] = 'A';
        seq3[2] = 'A';

        // sequence AAACCCCGGTGAAAACGTA
        char[] seq4 = new char[6];
        seq4[0] = 'A';
        seq4[1] = 'A';
        seq4[2] = 'A';
        seq4[3] = 'C';
        seq4[4] = 'C';
        seq4[5] = 'C';

        // search sequence exact match due to $
        char[] seqSearch = new char[4];
        seqSearch[0] = 'A';
        seqSearch[1] = 'A';
        seqSearch[2] = 'A';
        seqSearch[3] = '$';

        myTree.insert(sequence);
        myTree.insert(seq2);
        myTree.insert(seq3);
        myTree.insert(seq4);
        systemOut().clearHistory(); // to clear the insert prints
        myTree.remove(seq3);
        // search when no sequence available
        myTree.search(seqSearch);
        stdout = systemOut().getHistory();
        assertEquals("sequence AAA removed" + "\n" + "# of nodes visited: 5"
            + "\n" + "no sequence found" + "\n", stdout);
        systemOut().clearHistory();
    }

}
