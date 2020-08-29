import javax.swing.*;

/**
 *
 * @author Linh
 */
public class Maze {
    Cell entrance, exit;
    Cell[][] grid;
    
    int rows, cols;
    
    boolean pathFound = false;
    boolean nomore = false;
    Stack<Cell> path = new Stack<Cell>();
    
    public Maze(Cell[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        this.grid = new Cell[rows][cols];
        entrance = grid[0][0];
        exit = grid[rows - 1][cols - 1];                
    }
    
    public void findPath() {
        String msgFound = "The maze has a traversing path.\nCell coordinate are listed on the console.";
        String msgNot = "No traversing path was found.";
        String title = "Result of the search";
        
        path.push(entrance);
        entrance.setVisited(true);
        
        while (path.isEmpty() == false && pathFound == false) {
            //path found is top is exit
            if(path.peek() == exit) {
                pathFound = true;
                JOptionPane.showMessageDialog(null, msgFound, title, JOptionPane.WARNING_MESSAGE);
                System.out.println("The length of the path is " + path.size());
                System.out.println("Cells on the traversing path:\n");
                path.display();
                break;
            }
            
            //check if surrounding neighbors are empty
            
            //for (int i = path.peek().lastOut; i < 4; i++) {
            //    if (path.peek().neighbor[i].visited == true) {
            //        nomore = true;
            //    }
            //    else {
            //        nomore = false;
            //        break;
            //    }
            //}
            
            //pop the stack if no more neighbor to check
          
            if (!(path.peek().lastOut < 4)) {
                path.pop();
            } 
            
            else {
                //while there is a neighbor to check, chek duh
                int i = path.peek().lastOut;
                while(i < 4) {
                    //check the next neighbor
                	path.peek().lastOut++;
                    //if accessible and not visited, push and mark visited
                    if(path.peek().neighbor[i] != null && path.peek().neighbor[i].visited == false) {                    	
                    	path.peek().neighbor[i].setVisited(true);
                        path.push(path.peek().neighbor[i]);
                        break;
                    }                    
                    i++;
                }
            }
            
            
            if (path.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, msgNot, title, JOptionPane.WARNING_MESSAGE);
            }              
        }             
    }
}