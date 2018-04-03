import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class TryMe {

  public static void main(String[] args) {
    WeightedQuickUnionUF x;
    x = new WeightedQuickUnionUF(5);
    System.out.println(x.find(1));
  }
}
