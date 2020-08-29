import java.io.FileNotFoundException;

/**
 * Linh Le
 * CS 260
 * Project 2
 * Spring 2018
 */

/**
 *
 * @author Linh
 */
public class Application {

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here

        MazeBuilder builder = new MazeBuilder();
        //Maze maze = new Maze(builder.loadMazeFromFiles("EW.txt", "NS.txt"));
        //maze.findPath();

        Maze mazeRandom = new Maze(builder.loadMazeFromFiles("DNE","DNE"));
        mazeRandom.findPath();
    }
}

