import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    
    private int gridSize;
    private int sideLength;
    private int[][] grid;
    private int top; //extra index added to parents id[] in UF
    private int bottom; //extra index added to parents id[] in UF
    private WeightedQuickUnionUF UF;
    private int countOfOpenSites = 0;
    
    //validate input for a number that falls outside of the grid range
    private void checkOutOfBounds(int row, int col) 
    {
        if (row < 1 || row > sideLength)
            throw new IllegalArgumentException("There is no row " + row + ", out of bounds");
        if (col < 1 || col > sideLength)
            throw new IllegalArgumentException("There is no column " + col + ", out of bounds");
    }
    
    private void checkRange(int n) {
        if(n < 1 || n > sideLength)
            throw new IllegalArgumentException("Should be int");
    }
   
    //DONE
    private int getIndexOfSite(int row, int col) 
    {
        return ((row - 1) * sideLength) + (col - 1); 
    }
    
    //initialize a new Percolation algorithm w/ n*n 
    public Percolation(int n)
    {
        if (n <= 0) throw new java.lang.IllegalArgumentException("must be more than zero");
        gridSize = n * n;
        sideLength = n;
        grid = new int[n][n]; //all will apparently be 0, which will mean false (blocked)
        top = n * n;
        bottom = (n * n) + 1;
        UF = new WeightedQuickUnionUF((n * n) + 2);
    }

    //DO
    public void open(int row, int col) 
    {   
        //validate
        checkRange(row);
        checkRange(col);
        checkOutOfBounds(row, col);
        
        //leave if already open;
        if (isOpen(row, col)) return;
        
        //set site to open (1) and inc the count 
        grid[row - 1][col - 1] = 1;
        countOfOpenSites += 1;
        
        //record unions
        int i = getIndexOfSite(row, col);
        int aboveSite;
        int belowSite;
        int leftSite;
        int rightSite;
            
        //rows
        if (row == 1) 
        {
            UF.union(i, top);
            belowSite = isOpen(row + 1, col) ? getIndexOfSite(row + 1, col) : -1;
            if(belowSite > -1) UF.union(i, belowSite);
        }//if top, always do union with v-top
        else if (row == sideLength) 
        {
            UF.union(i, bottom); //if bottom, always union w v-bottom
            aboveSite = isOpen(row - 1, col) ? getIndexOfSite(row - 1, col) : -1;
            if(aboveSite > -1) UF.union(i, aboveSite);
        }
        else
        {
            aboveSite = isOpen(row - 1, col) ? getIndexOfSite(row - 1, col) : -1;
            belowSite = isOpen(row + 1, col) ? getIndexOfSite(row + 1, col) : -1;
            if(aboveSite > -1) UF.union(i, aboveSite);
            if(belowSite > -1) UF.union(i, belowSite);
        }
        
        //cols
        if (col == 1) //check if the site is open, and if so, save the index
        {
            rightSite = isOpen(row, col + 1) ? getIndexOfSite(row, col + 1) : -1;
            if(rightSite > -1) UF.union(i, rightSite);
        }
        else if (col == sideLength)
        {
            leftSite = isOpen(row, col - 1) ? getIndexOfSite(row, col - 1) : -1;
            if(leftSite > -1) UF.union(i, leftSite);
        }
        else
        {
            rightSite = isOpen(row, col + 1) ? getIndexOfSite(row, col + 1) : -1;
            leftSite = isOpen(row, col - 1) ? getIndexOfSite(row, col - 1) : -1;
            if(leftSite > -1) UF.union(i, leftSite);
            if(rightSite > -1) UF.union(i, rightSite);
        }
    }
    
    //DONE
    public boolean isOpen(int row, int col) 
    {
        checkRange(row);
        checkRange(col);
        checkOutOfBounds(row, col);
        return (grid[row - 1][col - 1] > 0);
    }
    
    //DONE
    //check the site's connection
    public boolean isFull(int row, int col)
    {
        checkRange(row);
        checkRange(col);
        checkOutOfBounds(row, col);
        if(!isOpen(row, col)) return false;
        int i = getIndexOfSite(row, col);
        return UF.connected(i, top);
    }
    
    //DONE
    public int numberOfOpenSites()
    {
        return countOfOpenSites;
    }
    
    //DONE
    public boolean percolates() 
    {
        return UF.connected(top, bottom);
    }
    
//    //for tests
//    public static void main(String[] args)
//    {
//        
//    }
}