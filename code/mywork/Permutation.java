import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int count = 0;
        
        while (!StdIn.isEmpty())
        {
            String word = StdIn.readString();
            rq.enqueue(word);
        }
        
        while(!rq.isEmpty() && count < n)
        {
            String item = rq.dequeue();
            StdOut.println(item);
            count++;
        }            
    }
}











