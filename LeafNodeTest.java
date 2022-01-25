import student.TestCase;

/**
 * 
 */

/**
 * Testcase for LeafNode
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 *
 */
public class LeafNodeTest extends TestCase {

    private LeafNode leaf;
    private char[] seq;
    private char[] seq2;
    private InternalNode root;
    private String stdout;
    private ArrayList<char[]> aList;
    private char[] seqM;
    private NodeCount num;


    /**
     * Set up
     */
    public void setUp() {

        seq = new char[4];
        seq2 = new char[1];
        seq2[0] = 'A';
        seq[0] = 'A';
        seq[1] = 'C';
        seq[2] = 'G';
        seq[3] = 'T';
        root = new InternalNode(0);
        leaf = new LeafNode(seq, 1);
        aList = new ArrayList<char[]>();
        seqM = new char[5];
        seqM[0] = 'A';
        seqM[1] = 'C';
        seqM[2] = 'G';
        seqM[3] = 'T';
        seqM[4] = '$';
        num = new NodeCount();

    }


    /**
     * Test get DNA
     */
    public void testDNA() {
        leaf.setDNA(seq2);
        assertEquals(seq2, leaf.getDNA());
    }


    /**
     * Test remove
     */
    public void testRemove() {
        // test remove if the sequences are equal to each other
        leaf.remove(root, seq);

        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT removed" + "\n", stdout);
        systemOut().clearHistory();

        // Remove different sequences
        leaf.remove(root, seq2);

        stdout = systemOut().getHistory();
        assertEquals("sequence A does not exist" + "\n", stdout);
        systemOut().clearHistory();
    }


    /**
     * Test insert
     */
    public void testInsert() {
        // Test if the insert is repeated
        leaf.insert(1, seq);
        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT already exists" + "\n", stdout);
        systemOut().clearHistory();

        leaf.insert(1, seq2);

        leaf.insert(0, seq2);

        leaf.remove(root, seq);

        LeafNode l2 = new LeafNode(seq2, 0);
        l2.insert(0, seq);

    }


    /**
     * Test print
     */
    public void testPrint() {
        // regular print
        leaf.print(1);
        stdout = systemOut().getHistory();
        assertEquals("  ACGT" + "\n", stdout);
        systemOut().clearHistory();

        // print length
        leaf.printLen(1);
        stdout = systemOut().getHistory();
        assertEquals("  ACGT 4" + "\n", stdout);
        systemOut().clearHistory();

        // print stats
        leaf.printStats(1);
        stdout = systemOut().getHistory();
        assertEquals("  ACGT A:25.00 C:25.00 G:25.00 T:25.00" + "\n", stdout);
        systemOut().clearHistory();

        assertEquals(1, leaf.getDepth());
    }


    /**
     * Test Search
     */
    public void testSearchCorrect() {

        // Check search with the same sequence
        leaf.search(seq, aList, num);
        assertEquals(1, num.getNodeCount());
        assertEquals(seq, aList.remove());

    }


    /**
     * Test search exact match
     */
    public void testSearchExact() {

        // search for an exact match
        leaf.search(seqM, aList, num);
        assertEquals(seq, aList.remove());
    }


    /**
     * Test search nothing found <Make a tree and check for assert equals
     * because the else should not be there in leafNode>
     */
    public void testSearchNone() {
        char[] none = new char[2];
        none[0] = 'A';
        none[1] = '$';
        leaf.search(none, aList, num);
        stdout = systemOut().getHistory();
        assertEquals(0, aList.length());

        // Test if it doesn't begin with the descriptor
        none[1] = 'T';

        leaf.search(none, aList, num);
        assertTrue(aList.isEmpty());

    }

}
