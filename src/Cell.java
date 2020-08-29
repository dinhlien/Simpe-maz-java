/**
 *
 * @author Linh
 */
public class Cell {
    int row;
    int col;
    boolean visited = false;
    //0,1,2,3 = north, east, south, west
    /**
     *               neighbor[0]
     * neighbor[3] -     cell    - neighbor[1]
     *               neighbor[2]
     */
    //neighbor[k] = null if no passage from this cell to it or DNE
    Cell[] neighbor = new Cell[4];
    int lastOut;
    
    public void setVisited(boolean value) {
        visited = value;        
    }
    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        visited = false;
        lastOut = 0;
    }
    
    @Override
    public String toString() {
        
        return "Row: " + row + "\tColumn: " + col;
    }
}
