import student.TestCase;

/**
 * 
 */

/**
 * Testcase for Flyweight node
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 *
 */
public class FlyweightTest extends TestCase {
    private Flyweight fly;

    private char[] seq;
    private String stdout;
    private NodeCount num;


    /**
     * Set up
     */
    public void setUp() {
        fly = new Flyweight();
        seq = new char[3];
        seq[0] = 'A';
        seq[1] = 'A';
        seq[2] = 'A';
        num = new NodeCount();

    }


    /**
     * Test remove
     */
    public void testRemove() {

        fly.remove(fly, seq);
        stdout = systemOut().getHistory();
        assertEquals("sequence AAA does not exist" + "\n", stdout);
    }


    /**
     * Test insert
     */
    public void testInsert() {
        fly.insert(2, seq);
        stdout = systemOut().getHistory();
        assertEquals("sequence AAA inserted at level 2" + "\n", stdout);

    }


    /**
     * Test Prints
     */
    public void testPrint() {
        // regular print
        fly.print(1);
        stdout = systemOut().getHistory();
        assertEquals("    E" + "\n", stdout);
        systemOut().clearHistory();

        // print length
        fly.printLen(1);
        stdout = systemOut().getHistory();
        assertEquals("    E" + "\n", stdout);
        systemOut().clearHistory();

        // print stats
        fly.printStats(1);
        stdout = systemOut().getHistory();
        assertEquals("    E" + "\n", stdout);
        systemOut().clearHistory();

    }


    /**
     * Test depth
     */
    public void testDepth() {
        fly.setDepth(0);
        assertEquals(0, fly.getDepth());
    }


    /**
     * Test search
     */
    public void testSearch() {
        fly.search(seq, null, num);
        assertEquals(1, num.getNodeCount());
    }

}

