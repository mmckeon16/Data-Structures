package heaps;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Java implementation of a minHeap <br>
 * Challenge (not really): convert to maxHeap <br>
 * Challenge: implement a swap function for arraylists
 * so that we don't have to import Collections <br>
 * Challenge: use an Array instead of an ArrayList (hard) <br>
 * Challenge: rewrite toString to prettyPrint a heap
 * @author: Khayyam Saleem
 */
public class Heap<E extends Comparable<E>>{
    private ArrayList<E> data;

    /**
     * Heap exception definition
     */
    private static class HeapException extends RuntimeException{
        //necessary in newer versions of java, yields a warning 
        //if you don't have it
        private static final long serialVersionUID = 0;
        //constructor for an exception that takes a message
        public HeapException(String message){super(message);}
    }

    /**
     * Constructor for a new heap
     */
    public Heap(){
        this.data = new ArrayList<E>();
    }

    /**
     * tells whether or not heap is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty(){
        return (this.size() == 0);
    }

    /**
     * size of the heap
     * @return size of heap
     */
    public int size(){
        return this.data.size();
    }

    /**
     * returns element of heap at given index
     * @return element at given index
     */
    public E get(int index){
        return this.data.get(index);
    }

    /**
     * returns minimal element of heap
     * @throws HeapException if heap is empty
     * @return minimal element of heap
     */
    public E getMin() throws HeapException{
        if(this.isEmpty()){
            throw new HeapException("Heap is empty");
        } else {
            return this.get(0);
        }
    }

    /**
     * gets index of left child for a given node
     * @param curr index of current node
     * @return index of left child
     */
    public int getLeft(int curr){
        return 2 * curr + 1;
    }

    /**
     * gets index of right child for a given node
     * @param curr index of current node
     * @return index of right child
     */
    public int getRight(int curr){
        return 2 * curr + 2;
    }

    /**
     * gets index of parent for a given node
     * @param curr index of current node
     * @return index of parent 
     */
    public int getParent(int curr){
        return (curr - 1) / 2;
    }

    /**
     * inserts an element into the heap
     * @param elem element to insert
     */
    public void offer(E elem){
        this.data.add(elem);
        reheapUp(this.size()-1);
    }

    /**
     * reheap procedure for inserting (sift up)
     * @param curr index of current node
     */
    public void reheapUp(int curr){
        int p;
        if(curr != 0){
            p = getParent(curr);
            if(this.get(p).compareTo(this.get(curr)) > 0){
                Collections.swap(this.data, p, curr);
                reheapUp(p);
            }
        }
    }

    /**
     * removes root of heap (minimal element for minHeap)
     * @throws HeapException if heap is empty
     */
    public E poll(){
        if(this.isEmpty()){
            throw new HeapException("Heap is empty");
        } else {
            E out = this.data.get(0);
            this.data.set(0, this.get(this.size()-1));
            if (this.size() > 0){
                reheapDown(0);
            }
            return out;
        }
    }

    /**
     * reheap procedure for removing (sift down)
     * @param curr index of current node
     */
    public void reheapDown(int curr){
        int l, r, min;
        l = getLeft(curr);
        r = getRight(curr);
        if(r >= this.size()){
            if(l >= this.size())
                return;
            else
                min = l;
        } else {
            if(this.get(l).compareTo(this.get(r)) <= 0)
                min = l;
            else
                min = r;
        }
        if(this.get(curr).compareTo(this.get(min)) > 0){
            Collections.swap(this.data, min, curr);
            reheapDown(min);
        }
    }

    /**
     * added for laziness
     * @param data array of elements to insert into heap
     */
    public void addAll(E[] data){
        for(E elem : data){
            this.offer(elem);
        }
    }

    /**
     * implementation of heapSort
     * @param data unsorted list
     * @return sorted list
     */
    public ArrayList<E> sort(ArrayList<E> data){
        Heap<E> h = new Heap<>();
        ArrayList<E> sortedList = new ArrayList<>();
        for(E item : data){
            h.offer(item);
        }
        while(h.size() != 0){
            sortedList.add(h.poll());
        }
        return sortedList;
    }

    /**
     * toString for heaps
     * @return string representation of heap
     */
    @Override
    public String toString(){
        return this.data.toString();
    }

    public static void main(String[] args){
       Integer[] x = new Integer[] {10, 31, 9, 1, 0, 15, 6, 22};
       Heap<Integer> h = new Heap<>();
       h.addAll(x);
       System.out.println(h);
       h.poll();
       System.out.println(h);

       System.out.println();
       String[] s = new String[]{"happy", "angry", "sad", "jealous", "amazed", "livid", "surprised"};
       Heap<String> sH = new Heap<>();
       sH.addAll(s);
       System.out.println(sH);
       sH.poll();
       sH.poll();
       System.out.println(sH);
    }

}
