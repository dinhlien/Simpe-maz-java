import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 *
 * @author Linh
 */
public class MazeBuilder {
    public Cell[][] loadMazeRandom() {
        double p;
        int rows, cols;
        Cell[][] grid;

        // store probability, rows and columns
        String prob = JOptionPane.showInputDialog(null, "Enter passage probabiity", "Maze Input",
                        JOptionPane.OK_CANCEL_OPTION);
        p = Double.parseDouble(prob);
        rows = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter #rows", "Maze Input", JOptionPane.OK_CANCEL_OPTION));
        cols = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter #columns", "Maze Input", JOptionPane.OK_CANCEL_OPTION));

        // make a grid
        grid = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // load neighbor arrays
                boolean notbounded = true;

                // north-0 boundary:
                if (i == 0 && j != 0 && j != cols -1) {
                    notbounded = false;
                    grid[i][j].neighbor[3] = (Math.random() < p) ? grid[i][j - 1] : null;
                    grid[i][j].neighbor[2] = (Math.random() < p) ? grid[i + 1][j] : null;
                    grid[i][j].neighbor[1] = (Math.random() < p) ? grid[i][j + 1] : null;
                    grid[i][j].neighbor[0] = null;
                
                }

                // south-2 boundary
                if (i == rows - 1 && j != 0 && j != cols -1) {
                    grid[i][j].neighbor[3] = (Math.random() < p) ? grid[i][j - 1] : null;
                    grid[i][j].neighbor[2] = null;
                    grid[i][j].neighbor[1] = (Math.random() < p) ? grid[i][j + 1] : null;
                    grid[i][j].neighbor[0] = (Math.random() < p) ? grid[i - 1][j] : null;
                    notbounded = false;
                }

                // east-1 boundary
                if (j == cols - 1 && i != 0 && i != rows - 1) {
                    grid[i][j].neighbor[3] = (Math.random() < p) ? grid[i][j - 1] : null;
                    grid[i][j].neighbor[2] = (Math.random() < p) ? grid[i + 1][j] : null;
                    grid[i][j].neighbor[1] = null;
                    grid[i][j].neighbor[0] = (Math.random() < p) ? grid[i - 1][j] : null;
                    notbounded = false;
                }

                // west-3 boundary
                if (j == 0 && i != 0 && i != rows - 1) {
                    grid[i][j].neighbor[3] = null;
                    grid[i][j].neighbor[2] = (Math.random() < p) ? grid[i + 1][j] : null;
                    grid[i][j].neighbor[1] = (Math.random() < p) ? grid[i][j + 1] : null;
                    grid[i][j].neighbor[0] = (Math.random() < p) ? grid[i - 1][j] : null;
                    notbounded = false;
                }
                
                //top left corner
                if (i == 0 && j == 0) {
                    notbounded = false;
                    grid[i][j].neighbor[3] = null;
                    grid[i][j].neighbor[2] = (Math.random() < p) ? grid[i + 1][j] : null;
                    grid[i][j].neighbor[1] = (Math.random() < p) ? grid[i][j + 1] : null;
                    grid[i][j].neighbor[0] = null;
                }
                
                //top right
                if (i == 0 && j == cols -1) {
                    notbounded = false;
                    grid[i][j].neighbor[3] = (Math.random() < p) ? grid[i][j - 1] : null;
                    grid[i][j].neighbor[2] = (Math.random() < p) ? grid[i + 1][j] : null;
                    grid[i][j].neighbor[1] = null;
                    grid[i][j].neighbor[0] = null;
                }
                
                //bottom left
                if (i == rows - 1 && j == 0) {
                    notbounded = false;
                    grid[i][j].neighbor[3] = null;
                    grid[i][j].neighbor[2] = null;
                    grid[i][j].neighbor[1] = (Math.random() < p) ? grid[i][j + 1] : null;
                    grid[i][j].neighbor[0] = (Math.random() < p) ? grid[i - 1][j] : null;
                }
                
                //bottom right
                if (i == rows - 1 && j == cols - 1) {
                    notbounded = false;
                    grid[i][j].neighbor[3] = (Math.random() < p) ? grid[i][j - 1] : null;
                    grid[i][j].neighbor[2] = null;
                    grid[i][j].neighbor[1] = null;
                    grid[i][j].neighbor[0] = (Math.random() < p) ? grid[i - 1][j] : null;
                }

                // inner cells
                if (notbounded == true) {
                    grid[i][j].neighbor[3] = (Math.random() < p) ? grid[i][j - 1] : null;
                    grid[i][j].neighbor[2] = (Math.random() < p) ? grid[i + 1][j] : null;
                    grid[i][j].neighbor[1] = (Math.random() < p) ? grid[i][j + 1] : null;
                    grid[i][j].neighbor[0] = (Math.random() < p) ? grid[i - 1][j] : null;
            }
        }
    }

    System.out.println("Random maze has been searched");

    return grid;
}

    public Cell[][] loadMazeFromFiles(String EW, String NS) throws FileNotFoundException {
        Cell[][] grid;

        File fileEW = new File(EW);
        File fileNS = new File(NS);

        try {
            Scanner scannerEW = new Scanner(fileEW);
            Scanner scannerNS = new Scanner(fileNS);

            int rows = scannerEW.nextInt();
            int cols = scannerEW.nextInt();
            grid = new Cell[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = new Cell(i, j);
                }
            }

            // 0:passable, 1:not passable
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int ew = scannerEW.nextInt();
                    int ns = scannerNS.nextInt();
                    if (ew == 0) {
                        grid[i][j].neighbor[1] = grid[i][j + 1];
                        grid[i][j + 1].neighbor[3] = grid[i][j];
                    }
                    if (ew == 1) {
                        grid[i][j].neighbor[1] = null;
                    }
                    if (ns == 0) {
                        grid[i][j].neighbor[2] = grid[i + 1][j];
                        grid[i + 1][j].neighbor[0] = grid[i][j];
                    }
                    if (ns == 1) {
                        grid[i][j].neighbor[2] = null;
                    }
                }
            }

            scannerEW.close();
            scannerNS.close();

            return grid;
        } 

        catch (FileNotFoundException e) {
            System.out.println("FileNotfoundException: " + e.getMessage());
            System.out.println("Loading a random maze");
            return loadMazeRandom();
        }
    }
}
