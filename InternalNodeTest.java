import student.TestCase;

/**
 *  
 */

/**
 * Testcase for Internal Node
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 *
 */
public class InternalNodeTest extends TestCase {
    private InternalNode in;
    private char[] seq;
    private String stdout;
    private char[] seq2;
    private InternalNode in2;
    private ArrayList<char[]> aList;
    private NodeCount num;

    /**
     * Test set up
     */
    public void setUp() {
        in = new InternalNode(0);
        seq = new char[4];
        seq[0] = 'A';
        seq[1] = 'C';
        seq[2] = 'G';
        seq[3] = 'T';
        seq2 = new char[1];
        seq2[0] = 'G';
        in2 = new InternalNode(1);
        aList = new ArrayList<char[]>();
        num = new NodeCount();

    }


    /**
     * Test get depth
     */
    public void testGetDepth() {
        assertEquals(0, in.getDepth());
    }


    /**
     * test insert, find node and insertNodeDNA
     */
    public void testInsert() {

        // Insert on a flyweight
        in.insert(0, seq);

        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 1" + "\n", stdout);

        systemOut().clearHistory();

        // test
        in2.insert(1, seq2);
        stdout = systemOut().getHistory();
        assertEquals("sequence G inserted at level 2" + "\n", stdout);

        systemOut().clearHistory();

        // Test G
        in.setDepth(1);
        in.insert(1, seq);
        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 2" + "\n", stdout);

        systemOut().clearHistory();

        // Test C
        in.setDepth(2);
        in.insert(2, seq);
        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 3" + "\n", stdout);

        systemOut().clearHistory();

        // Test T
        in.setDepth(3);
        in.insert(3, seq);
        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 4" + "\n", stdout);

        systemOut().clearHistory();

    }


    /**
     * Test Prints
     */
    public void testPrint() {
        // regular print
        in.print(1);
        stdout = systemOut().getHistory();
        
        systemOut().clearHistory();

        // print length
        in.printLen(1);
        stdout = systemOut().getHistory();
        assertEquals("I\n" + "  E\n" + "  E\n" + "  E\n" + "  E\n" + "  E\n",
            stdout);
        systemOut().clearHistory();

        // print stats
        in.printStats(1);
        stdout = systemOut().getHistory();
        assertEquals("I\n" + "  E\n" + "  E\n" + "  E\n" + "  E\n" + "  E\n",
            stdout);
        systemOut().clearHistory();

    }


    /**
     * Test search
     */
    public void testSearch() {

        // When the node is empty
        in.search(seq, aList, num);
        assertEquals(2, num.getNodeCount());
        assertTrue(aList.isEmpty());

        // search the same height
        in2.search(seq2, aList, num);
        assertTrue(aList.isEmpty());

        // Insert the seq into the tree then check
        in.insert(0, seq);
        in.search(seq, aList, num);
        assertFalse(aList.isEmpty());

    }


    /**
     * Test remove different nodes
     */
    public void testRemove() {
        char[] seq3 = new char[4];
        seq3 = seq;
        seq3[0] = 'C';

        char[] seq5 = new char[4];
        seq5 = seq;
        seq3[0] = 'T';

        char[] seq4 = new char[4];
        seq4 = seq;
        seq3[0] = 'G';

        in.insert(0, seq);
        in.insert(0, seq3);
        in.insert(0, seq4);
        in.insert(0, seq5);

        systemOut().clearHistory();
        in.remove(in.findNode('A'), seq4);
        stdout = systemOut().getHistory();

        assertEquals("sequence GCGT removed\n", stdout);
    }

}
