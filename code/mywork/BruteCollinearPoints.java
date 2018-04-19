import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;


public class BruteCollinearPoints {
    
    private Point[] ps;
    private LineSegment[] segments;
    
    public BruteCollinearPoints(Point[] points) {
        ps = points;
        int n = ps.length;
        // examine 4 points at a time
        
        // if p -> q -> r -> s, then pq, pr, and ps should be equal.
        // push the segment into segments, but only once, and not the sub-segments
        
        // for each point, compare to all others?
        // for this point, compare to each next on like the threesum algorithm
        
        // Fix the first element as A[i]
        for (int p = 0; p < n - 3; p++) 
        {
            // Fix the second element as A[j]
            for (int q = p + 1; q < n - 2; q++) 
            {
                // Now look for the third number
                for (int r = q + 1; r < n - 1; r++) 
                {
                    // Fourth
                    for (int s = r + 1; s < n; s++) {
                        double pqSlope = ps[p].slopeTo(ps[q]);
                        double prSlope = ps[p].slopeTo(ps[r]);
                        double psSlope = ps[p].slopeTo(ps[s]);
                        
                        if (pqSlope == prSlope &&
                            pqSlope == psSlope) {
                            segments.push(new LineSegment(ps[p], ps[q]));
                        }
                         //StdOut.println(ps[p].toString() + ps[q].toString() + ps[r].toString() + ps[s].toString());
                    }
                }
            }
        }
    }    
    
    public int numberOfSegments() {
        return segments.length;
    }
    
    public LineSegment[] segments() {
        return segments;
    }
    
    public static void main(String[] args) {
        // read the n points from a file
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }
}