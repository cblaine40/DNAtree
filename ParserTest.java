import student.TestCase;

/**
 * The test class for parser
 * 
 * @author Samridhi Roshan (samridhi18)
 * @author Camille Blaine (cblaine)
 * @version 17/8/21
 *
 */
public class ParserTest extends TestCase {

    private Parser parser;
    private char[] sequence;
    private String stdout;

    private Tree tree;


    /**
     * This is the setUp for the test methods
     */
    public void setUp() {
        // set up the tree
        tree = new Tree();

        // set Parser
        parser = new Parser("     insert ACGT  ", tree);

        // setSequence return the sequence as char[]
        sequence = parser.setSequence(4, "ACGT");
    }


    /**
     * Tests setSequence sets the sequence after taking a string as a parameter
     */
    public void testSetSequence() {
        assertEquals(sequence[0], 'A');
        assertEquals(sequence[1], 'C');
        assertEquals(sequence[2], 'G');
        assertEquals(sequence[3], 'T');

        // "AC"
        assertEquals(parser.setSequence(2, "AC")[0], 'A');
        assertEquals(parser.setSequence(2, "AC")[1], 'C');
    }


    /**
     * Tests insert in the parser
     */
    public void testParserInsert() {
        parser.parseInput();
        stdout = systemOut().getHistory();
        assertEquals("sequence ACGT inserted at level 0" + "\n", stdout);
        systemOut().clearHistory();
    }


    /**
     * Tests remove method from the parser class
     */
    public void testParserRemove() {
        parser.parseInput(); // for ACGT
        parser = new Parser(" insert AAAA", tree);
        parser.parseInput(); // for AAAA
        parser = new Parser("  insert AA   ", tree);
        parser.parseInput(); // for AA

        systemOut().clearHistory();
        parser = new Parser("remove AAAA", tree);
        parser.parseInput(); // for remove
        stdout = systemOut().getHistory();
        assertEquals("sequence AAAA removed" + "\n", stdout);
        systemOut().clearHistory();
    }


    /**
     * Tests all the print method from Parser
     */
    public void testParserPrints() {

        parser.parseInput();
        parser = new Parser("insert       ACTGGGAA ", tree);
        parser.parseInput();

        // Check print
        systemOut().clearHistory();
        parser = new Parser("print", tree);
        parser.parseInput();
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "I" + "\n" + "  I" + "\n" + "    E"
            + "\n" + "    I" + "\n" + "      E" + "\n" + "      E" + "\n"
            + "      ACGT" + "\n" + "      ACTGGGAA" + "\n" + "      E" + "\n"
            + "    E" + "\n" + "    E" + "\n" + "    E" + "\n" + "  E" + "\n"
            + "  E" + "\n" + "  E" + "\n" + "  E" + "\n", stdout);
        systemOut().clearHistory();

        // print stats
        systemOut().clearHistory();
        parser = new Parser("print stats", tree);
        parser.parseInput();
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "I" + "\n" + "  I" + "\n" + "    E"
            + "\n" + "    I" + "\n" + "      E" + "\n" + "      E" + "\n"
            + "      ACGT A:25.00 C:25.00 G:25.00 T:25.00" + "\n"
            + "      ACTGGGAA A:37.50 C:12.50 G:37.50 T:12.50" + "\n"
            + "      E" + "\n" + "    E" + "\n" + "    E" + "\n" + "    E"
            + "\n" + "  E" + "\n" + "  E" + "\n" + "  E" + "\n" + "  E" + "\n",
            stdout);
        systemOut().clearHistory();

        // print lengths
        systemOut().clearHistory();
        parser = new Parser("print lengths", tree);
        parser.parseInput();
        stdout = systemOut().getHistory();
        assertEquals("tree dump:" + "\n" + "I" + "\n" + "  I" + "\n" + "    E"
            + "\n" + "    I" + "\n" + "      E" + "\n" + "      E" + "\n"
            + "      ACGT 4" + "\n" + "      ACTGGGAA 8" + "\n" + "      E"
            + "\n" + "    E" + "\n" + "    E" + "\n" + "    E" + "\n" + "  E"
            + "\n" + "  E" + "\n" + "  E" + "\n" + "  E" + "\n", stdout);
        systemOut().clearHistory();

    }


    /**
     * Tests the search command from Parser
     * Seach without $
     */
    public void testParserSearchNonExact() {

        parser.parseInput();
        parser = new Parser("insert       ACTGGGAA ", tree);
        parser.parseInput(); // ACTGGGAA
        parser = new Parser("   insert     AAAA ", tree);
        parser.parseInput(); // AAAA

        parser = new Parser("   insert     AAAAA ", tree);
        parser.parseInput(); // AAAAA

        // search without $
        systemOut().clearHistory();
        parser = new Parser("search AAA", tree);
        parser.parseInput(); // for search
        stdout = systemOut().getHistory();
        assertEquals("# of nodes visited: 14" + "\n" + "sequence: AAAAA" + "\n"
            + "sequence: AAAA" + "\n", stdout);
        systemOut().clearHistory();

    }


    /**
     * Tests the search command from Parser
     * Seach with $
     */
    public void testParserSearchExact() {

        parser.parseInput();
        parser = new Parser("insert       ACTGGGAA ", tree);
        parser.parseInput(); // ACTGGGAA
        parser = new Parser("   insert     AAAA ", tree);
        parser.parseInput(); // AAAA

        parser = new Parser("   insert     AAAAA ", tree);
        parser.parseInput(); // AAAAA

        // search with $
        systemOut().clearHistory();
        parser = new Parser("search AAA$", tree);
        parser.parseInput(); // for search
        stdout = systemOut().getHistory();
        assertEquals("# of nodes visited: 5" + "\n" + "no sequence found"
            + "\n", stdout);
        systemOut().clearHistory();
    }

}
