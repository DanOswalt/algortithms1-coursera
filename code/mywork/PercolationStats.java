import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    
    private int sideLength;
    private int trials;
    private int numberOfSites;
    private double pArray[];
    private double mean;
    private double stddev;
    private double confidenceHi;
    private double confidenceLo;
    
    public PercolationStats(int n, int t)
    {
        if (n <= 0 || t <= 0) throw new java.lang.IllegalArgumentException("args must both be greater than 0");
        sideLength = n;
        trials = t;
        numberOfSites = sideLength * sideLength;
        pArray = new double[t];
    }
    public double mean()
    {
        return mean;
    }
    public double stddev()
    {
        return stddev;
    }
    public double confidenceLo()
    {
        return confidenceLo;
    }
    public double confidenceHi()
    {
        return confidenceHi;
    }
    public static void main(String[] args) 
    {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        double p;
        double confidenceLevel = 1.96;
        double ci;
        
        PercolationStats percStats = new PercolationStats(n, t);
        
        //monte carlo x number of trials
        for(int i = 0; i < t; i++)
        {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) 
            {          
                int row = StdRandom.uniform(1, n);
                int col = StdRandom.uniform(1, n);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    StdOut.println("opened one");
                } else {
                    StdOut.println("already open");
                }
                if (perc.percolates()) break;
            }
            p = ((double) perc.numberOfOpenSites()) / percStats.numberOfSites;
            percStats.pArray[i] = p;
        }
        //then find the stats after all are done
        percStats.mean = StdStats.mean(percStats.pArray);
        percStats.stddev = StdStats.stddev(percStats.pArray);
        ci = confidenceLevel * percStats.stddev / Math.sqrt(percStats.pArray.length);
        percStats.confidenceLo = percStats.mean - ci;
        percStats.confidenceHi = percStats.mean + ci;
        
        StdOut.println("mean                    = " + percStats.mean());
        StdOut.println("stddev                  = " + percStats.stddev());
        StdOut.println("95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
    }
}