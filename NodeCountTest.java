import student.TestCase;

/**
 * Testcase for node count
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 *
 */
public class NodeCountTest extends TestCase {
    
    private NodeCount nc;
    
    /**
     * Set up
     */
    public void setUp() {
        nc = new NodeCount();
    }
    
    /**
     * Test increase the count and get the count.
     */
    public void testCount() {
        nc.countUp();
        assertEquals(1, nc.getNodeCount());
    }
    

}
