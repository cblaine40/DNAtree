
// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
// with anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
// Samridhi Roshan, Camille Blaine

import java.io.*;

/**
 * This class contains the main method which opens the command file to read the
 * commands and finally get the output from the other classes to print the it to
 * stdOut
 * 
 * @author Camille Blaine
 * @author Samridhi Roshan
 * @version 17/8/21
 *
 */
public class DNAtree {

    /**
     * Main method for the DNA tree
     * 
     * @param args
     *            the arguments from the file input
     * @throws IOException
     *             exception that is thrown if there is no file
     */
    public static void main(String[] args) throws IOException {

        // commandFile is the input file to the program
        File commandFile = null;
        // args[0] is the name of the input file
        commandFile = new File(args[0]);

        if (!commandFile.exists()) {
            // if input file does not exist, inform and stop
            System.out.println("Input File does not exists " + args[0]);
            return;
        }

        // If the file exists, read commands till the EOF
        RandomAccessFile file = new RandomAccessFile(commandFile, "r");

        String command = ""; // line will store one line from the input file
        // A new tree initialized to be sent to Parser later for the build up
        Tree myTree = new Tree();

        while (true) {

            try {
                command = file.readLine();
                // need to ignore blank lines in the file
                if ((command.trim()).length() == 0) {
                    continue;
                }
                // else, send data to the Parser for storing in the database
                (new Parser(command, myTree)).parseInput();
            }
            catch (Exception e) {
                // Once we reach EOFException stop reading
                break;
            }

        }

        file.close();

    }

}
