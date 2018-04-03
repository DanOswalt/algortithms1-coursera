import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
       
    private Node first;
    private Node last;
    private int size;
    
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
        
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
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
   
    public boolean isEmpty()                 // is the deque empty?
    { 
        return size() == 0;
    }            
   
    public int size()                        // return the number of items on the deque
    {
        return size;
    }
    
    public void addFirst(Item item)          // add the item to the front
    {
        checkNullInput(item);
        
        Node second = first;
            
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = isEmpty() ? null : second;
        if(second != null) {
            second.previous = first;
        } 
        
        size++;
        if(size == 1) {last = first;}
    }
    
    public void addLast(Item item)           // add the item to the end
    {
        checkNullInput(item);
        
        Node penultimate = last;
       
        last = new Node();
        last.item = item;
        last.previous = isEmpty() ? null : penultimate;
        last.next = null;
        if(penultimate != null) {
            penultimate.next = last;
        }
        
        size++;
        if(size == 1) {first = last;}
    }
    
    public Item removeFirst()                
    {
        throwErrIfEmpty();
        
        //save copy of the item to return later
        Item item = first.item;
        first = first.next;
        size--;
        
        if(isEmpty())
        {
            //if it is empty, last and first need to be reset
            last = null;
            first = null;
        } 
        else 
        {
            first.previous = null;
        }
        return item;
    }
    
    public Item removeLast()                 
    {
        throwErrIfEmpty();
        
        Item item = last.item;
        last = last.previous;
        size--;
        
        //if it is empty, last and first need to be reset
        if (isEmpty()) 
        {
            last = null;
            first = null;
        }
        else
        {
            last.next = null;
        }
        
        return item;
    }
    
    public static void main(String[] args)
    {
        // tests
        Deque<String> list = new Deque<String>();
        Iterator<String> iterator = list.iterator();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.addFirst("D");
        
        StdOut.println(list.size());
        StdOut.println(iterator.hasNext());
        
        StdOut.println(list.removeFirst());
        
        while (iterator.hasNext())
            StdOut.println(iterator.next()+ " ");
        
    }
}