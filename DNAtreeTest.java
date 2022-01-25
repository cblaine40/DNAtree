
import java.io.IOException;
import student.TestCase;

/**
 * This class tests the DNATree class's main method
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 *
 */
public class DNAtreeTest extends TestCase {

    private DNAtree tree; // instance of the class to test
    private String stdout; // to see what is printed in the console

    /**
     * This is the setUp for the test methods
     */
    public void setUp() {
        tree = new DNAtree();
    }


    /**
     * Tests that the program works as intended when input file
     * does not exists
     * 
     * @throws IOException
     */
    public void testNoInputFile() throws IOException {
        String[] args = new String[] { "input.txt" };
        tree.main(args);
        stdout = systemOut().getHistory(); // now get the print from console
        // check if the correct error message is printed
        assertEquals("Input File does not exists input.txt" + "\n", stdout);

        systemOut().clearHistory(); // just if we want to test other prints

    }


    /**
     * Tests that the program works as intended when input file
     * exists
     * 
     * @throws IOException
     */
    public void testInputFile() throws IOException {
        String[] args = new String[] { "SampleInput.txt" };
        tree.main(args);
        stdout = systemOut().getHistory(); // now get the print from console
        // check if the correct error message is printed
        assertEquals("sequence ACGT inserted at level 0\r\n"
            + "sequence AAAA inserted at level 2\r\n"
            + "sequence AA inserted at level 3\r\n"
            + "sequence AAACCCCGGTGAAAACGTA inserted at level 4\r\n"
            + "# of nodes visited: 5\r\n" + "sequence: AAAA\r\n"
            + "# of nodes visited: 13\r\n" + "sequence: AAAA\r\n"
            + "sequence: AAACCCCGGTGAAAACGTA\r\n" + "sequence: AA\r\n"
            + "# of nodes visited: 3\r\n" + "sequence: ACGT" + "\n", stdout);

        systemOut().clearHistory(); // just if we want to test other prints

    }


    /**
     * Tests that the program works as intended when input file
     * exists
     * 
     * @throws IOException
     */
    public void testInputPrintFile() throws IOException {
        String[] args = new String[] { "SampleOther.txt" };
        tree.main(args);
        stdout = systemOut().getHistory(); // now get the print from console
        // check if the correct error message is printed
        assertEquals("sequence ACGT inserted at level 0\n"
            + "sequence GGTT inserted at level 1\n"
            + "sequence AAA inserted at level 2\n"
            + "sequence AA inserted at level 3\n"
            + "tree dump:\n"
            + "I\n"
            + "  I\n"
            + "    I\n"
            + "      AAA\n"
            + "      E\n"
            + "      E\n"
            + "      E\n"
            + "      AA\n"
            + "    ACGT\n"
            + "    E\n"
            + "    E\n"
            + "    E\n"
            + "  E\n"
            + "  GGTT\n"
            + "  E\n"
            + "  E\n"
            + "tree dump:\n"
            + "I\n"
            + "  I\n"
            + "    I\n"
            + "      AAA 3\n"
            + "      E\n"
            + "      E\n"
            + "      E\n"
            + "      AA 2\n"
            + "    ACGT 4\n"
            + "    E\n"
            + "    E\n"
            + "    E\n"
            + "  E\n"
            + "  GGTT 4\n"
            + "  E\n"
            + "  E\n"
            + "sequence TATA inserted at level 1\n"
            + "tree dump:\n"
            + "I\n"
            + "  I\n"
            + "    I\n"
            + "      AAA A:100.00 C:0.00 G:0.00 T:0.00\n"
            + "      E\n"
            + "      E\n"
            + "      E\n"
            + "      AA A:100.00 C:0.00 G:0.00 T:0.00\n"
            + "    ACGT A:25.00 C:25.00 G:25.00 T:25.00\n"
            + "    E\n"
            + "    E\n"
            + "    E\n"
            + "  E\n"
            + "  GGTT A:0.00 C:0.00 G:50.00 T:50.00\n"
            + "  TATA A:50.00 C:0.00 G:0.00 T:50.00\n"
            + "  E\n", stdout);

        systemOut().clearHistory(); // just if we want to test other prints

    }

}
