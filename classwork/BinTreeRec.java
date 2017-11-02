package classwork;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Binary Tree Stub for Recitation
 * @author khayyamsaleem
 */
public class BinTreeRec<E extends Comparable<E>>{
    Node<E> root; //root of the whole tree

    /**
     * inner node class for BTs
     */
    public static class Node<E extends Comparable<E>>{
        E data;
        Node<E> left;
        Node<E> right;

        public Node(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public String toString(){
            return "(" + this.data.toString() +")";
        }
    }

    /**
     * Constructor for BT
     */
    public BinTreeRec(){
        this.root = null;
    }
    
    public void setRoot(Node<E> curr, Node<E> left, Node<E> right){
    	this.root = curr;
    	this.root.left = left;
    	this.root.right = right;
    }

    /**
     * yields size of the BT (helper)
     * @param localRoot current node
     * @return size of localRoot subtree
     */
    public int size(Node<E> localRoot){
        return (localRoot == null)? 0 : 1 + size(localRoot.left) + size(localRoot.right);
    }

    /**
     * yields size of BT
     * @return size of BT
     */
    public int size(){
        return size(this.root);
    }

    /**
     * max
     * @param a one number
     * @param b another number
     * @return maximum of two ints
     */
    public int max(int a, int b){
        return (a > b) ? a : b;
    }

    /**
     * height (helper)
     * @param localRoot current node
     * @return height of subtree
     */
    public int height(Node<E> localRoot){
        return (localRoot == null)? 0 : 1 + Math.max(height(localRoot.left), height(localRoot.right));
    }

    /**
     * height
     * @return height of Binary Tree
     */
    public int height(){
        return this.height(this.root);
    }

    /**
     * isFull (helper)
     * @param localRoot current node
     * @return whether or not subtree is full
     */
    public boolean isFull(Node<E> localRoot){
        if (localRoot.left == null && localRoot.right == null){
        	return true;
        }
        if (localRoot.left == null && localRoot.right!= null || localRoot.left != null && localRoot.right == null){
        	return false;
        } else{
        	return true && isFull(localRoot.left) && isFull(localRoot.right);
        }
    }

    /**
     * tells whether or not the binary tree is full
     * @return true if full, false otherwise
     */
    public boolean isFull(){
        return isFull(this.root);
    }

    /**
     * tells whether or not BT is perfect
     * @return true if perfect, false otherwise
     */
    public boolean isPerfect(){
        return (this.size() == (int)Math.pow(2, this.height())-1);
    }

    /**
     * isComplete (helper)
     * implementation discussed <a href="http://tinyurl.com/mh7cg5w">here</a>
     * @param localRoot current node
     * @param ind index assigned to node
     * @param nodeCount size of tree
     * @return true if subtree complete, false otherwise
     */
    public boolean isComplete(Node<E> localRoot, int ind, int nodeCount){
        //TODO
        return false;
    }

    /**
     * implementation of isComplete with queues
     * @param localRoot current node
     * @return true if local subtree is complete, false otherwise
     */
    public boolean isComplete(Node<E> localRoot){
        if (localRoot.left == null && localRoot.right == null){
        	return true;
        }
        return(localRoot.left != null && localRoot.right != null) && isFull(localRoot.left) && isFull(localRoot.right);
    }

    /**
     * tells whether or not a binary tree is complete
     * @return true if complete, false otherwise
     */
    public boolean isComplete(){
        //return isComplete(this.root, 0, this.size());
        return isComplete(this.root);
    }
    
    /**
     * breadth first traversal
     * @return
     */
    public void BFT(){
    	
    }

    public static void main(String[] args){
        BinTreeRec<Integer> b = new BinTreeRec<>();
        b.root = new Node<Integer>(5);
        b.root.left = new Node<Integer>(3);
        b.root.right = new Node<Integer>(6);
      //  b.root.right.right = new Node<Integer>(7);
        //b.root.left.left = new Node<Integer>(4);
        System.out.println("Size: " + b.size());
        System.out.println("Height: " + b.height());
        System.out.println("isFull?: " + b.isFull());
        System.out.println("isPerfect?: " + b.isPerfect());
        System.out.println("isComplete?: " + b.isComplete());
    }

}
