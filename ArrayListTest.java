import java.util.NoSuchElementException;
import student.TestCase;

/**
 * This is the test class for ArrayList
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 * 
 */
public class ArrayListTest extends TestCase {

    private ArrayList<Character> list; // instance of the class to test


    /**
     * This is the setUp for the test methods
     */
    public void setUp() {
        list = new ArrayList<Character>();

        list.append('A');
        list.append('A');
        list.append('B');
        list.append('C');
    }


    /**
     * tests constructor with no parameter
     */
    public void testDefaultConstructor() {
        assertEquals(50, list.getMaxSize());
    }


    /**
     * tests constructor with size parameter
     */
    public void testSizeConstructor() {
        list = new ArrayList<Character>(6);
        assertEquals(6, list.getMaxSize());
    }


    /**
     * Tests both isEmpty() and clear method
     */
    public void testEmptyClear() {

        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }


    /**
     * Tests insert method
     * Also tests other small methods indirectly
     */
    public void testInsert() {

        list.moveToStart();
        char firstElem = list.getValue();
        assertEquals('A', firstElem);
        assertEquals(4, list.length());
        // now, insert at 2
        assertTrue(list.moveToPos(2)); // move curr to 2
        assertTrue(list.insert('Z')); // insert
        // check
        assertEquals(5, list.length());
        char elemAt2 = list.getValue();
        assertEquals('Z', elemAt2);

    }


    /**
     * This tests the edge cases in insert and append
     */
    public void testEdgeCaseInsertAppend() {
        list = new ArrayList<Character>(2);
        list.append('A');
        list.append('B');
        assertFalse(list.append('C'));
        assertFalse(list.insert('C'));
    }


    /**
     * Test the curr index and methods
     */
    public void testCurrMethods() {
        list.moveToEnd();
        char lastElem = list.getValue();
        assertEquals('C', lastElem);
        assertEquals(3, list.currPos());

        assertFalse(list.moveToPos(8));
    }


    /**
     * Tests exception value
     */
    public void testGetValueFalse() {
        list.moveToPos(4); // curr == listSize
        Exception e = null;
        try {
            list.getValue();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof NoSuchElementException);

        list.moveToPos(-4);
        Exception c = null;
        try {
            list.getValue();
        }
        catch (Exception exception) {
            c = exception;
        }
        assertTrue(c instanceof NoSuchElementException);

    }


    /**
     * Test remove exception cases
     */
    public void testRemoveException() {
        list.moveToPos(4); // curr == listSize
        Exception e = null;
        try {
            list.remove();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof NoSuchElementException);

        list.moveToPos(-4);
        Exception c = null;
        try {
            list.remove();
        }
        catch (Exception exception) {
            c = exception;
        }
        assertTrue(c instanceof NoSuchElementException);

    }


    /**
     * Test the remove method
     */
    public void testRemove() {
        list.moveToPos(2);
        char removeElem = list.remove();
        assertEquals('B', removeElem);
        assertEquals(3, list.length());

    }
}
