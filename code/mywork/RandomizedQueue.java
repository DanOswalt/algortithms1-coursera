import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int size;
    private Node last;
    
    private class Node
    {
        Item item;
        Node next;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = last;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    private void checkNullInput(Item item)
    {
        if(item == null) { throw new java.lang.IllegalArgumentException("must provide 1 arg"); }
    }
    
    private void throwErrIfEmpty() {
        if (isEmpty()) { throw new java.util.NoSuchElementException("list is empty");}
    }
    
   public boolean isEmpty()                // is the randomized queue empty?
   { return size() == 0; }
   
   public int size()                        // return the number of items on the randomized queue
   { return size; }
   
   public void enqueue(Item item)           // add the item
   {
       checkNullInput(item);
       Node oldLast = last;
       last = new Node();
       last.item = item;
       last.next = oldLast;
       size++;
   }
   
   public Item dequeue()                    // remove and return a random item
   {
       throwErrIfEmpty();
       Node current = last;
       Node previous = null;
      
       int randomIndex = StdRandom.uniform(size);
      
       if (randomIndex == 0)
       {
           last = current.next;
       } 
       else
       {      
           for(int i = 0; i < randomIndex; i++) {
               previous = current;
               current = previous.next;
           }
           //remove current by changing the previous.next
           previous.next = current.next;
       }
 
       size--;
       return current.item;
   }
   
   public Item sample()                     // return a random item (but do not remove it)
   {
       throwErrIfEmpty();
       int randomIndex = StdRandom.uniform(size);
       Node current = last;
      
       //traverse to next node until the i'th item is found
       for(int i = 0; i < randomIndex; i++) {
           current = current.next;
       }
       return current.item;
   }
   
   public static void main(String[] args)   // unit testing (optional)
   {
       //tests   
   }
}