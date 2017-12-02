package treap;

import java.util.Random;
import java.util.Stack;

/**
 * 
 * @author Mary McKeon
 *
 * @param Treap HW5
 */

public class Treap<E extends Comparable<E>> {
	private class Node<E>{
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E > left;
		public Node <E > right;
		
		public Node (E data , int priority ){
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
		}
		
		/**
		 * Rotates the nodes to the right so the left of the root becomes the new "root" of the subtree
		 * @return new "root" after the rotation
		 */
		Node <E> rotateRight(){
			Node<E> pivot = this.left;
			Node<E> root = this;
			
			if(root.left == null){
				return null;
			} else {
				root.left = pivot.right; 
				pivot.right = root;
			}
			return pivot;
		}
		
		/**
		 * Rotates the nodes to the left so the right of the root becomes the new "root" of the subtree
		 * @return new "root" after the rotation
		 */
		Node <E> rotateLeft(){
			Node<E> pivot = this.right;
			Node<E> root = this;
			
			if(root.right == null){
				return null;
			} else {
				root.right = pivot.left; 
				pivot.left = root;
			}
			return pivot;
		}
	}
	
	private Random priorityGenerator;
	private  Node <E> root;
	
	public Treap(){
		priorityGenerator = new Random();
		root = null;
	}
	
	public Treap(long seed){
		priorityGenerator = new Random(seed);
		root = null;
	}
	
    /**
     * this function adds the node to the bottom of the treap based on the key value, keeping it a bst, and stores the path to
     * that location in a stack that is returned
     * @param Node<E> leaf node that is added
     * @return Stack<Node> that is the path to the node that was added
     */
	public Stack<Node> addAsLeaf(Node<E> leaf){
		Node<E> curr = root;
		Stack<Node> path = new Stack<>();
		boolean foundSpot = false;
		
		while(curr != null && !foundSpot){
			if(curr.data.compareTo(leaf.data) == 0){
				path = null;
				foundSpot = true;
			} else if(curr.data.compareTo(leaf.data) < 0){
				path.push(curr);
				if(curr.right != null){
					curr = curr.right;
				} else {
					curr.right = leaf;
					path.push(curr.right);
					foundSpot = true;
				}
				
			} else if(curr.data.compareTo(leaf.data) > 0){
				path.push(curr);
				if(curr.left != null){
					curr = curr.left;
				} else{
					curr.left = leaf;
					path.push(curr.left);
					foundSpot = true;
				}
			}
		}
		return path;
	}
	
	/**
	 * Method used to check if priority already exists in the treap
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean findPriority(Node <E> root, Integer priority){
		Node<E> curr = root;
		boolean found = false;
		if(curr.priority == priority){
			found = true;
		} else {
			while(curr != null && !found){
				if(curr.priority == priority){
					found = true;
				} else if(curr.priority < priority){
					curr = curr.right;
				} else if(curr.priority > priority){
					curr = curr.left;
				}
			}
		}
		return found;
	}
	
	/**
	 * This function rotates the node up until the treap follows treap priority
	 * @param path
	 */
	private void reheap(Stack<Node> path){
		//stringStack(path);
		Stack<Node> p = path;
		Node<E> child = p.pop();
		Node<E> parent = null;
		if(p.size()>0){
			parent = p.pop();
		}
		while(p.size() > 0  && parent.priority < child.priority){  //while parent is less than child 
			if(child.equals(parent.right)){
				if(parent.equals(root)){
					root = parent.rotateLeft();
				} else{
					if(p.peek()!= null){
						Node<E> above = p.peek();
						if(above.right != null && above.right.equals(parent)){
							 above.right = parent.rotateLeft();
						} else if(above.left != null && above.left.equals(parent)){
							above.left = parent.rotateLeft();
						}
					}
				}
			} else {
				if(parent.equals(root)){
				root = parent.rotateRight();
				} else{
					if(p.peek() != null){
						Node<E> above = p.peek();
						if(above.right != null && above.right.equals(parent)){
							above.right = parent.rotateRight();
						} else if(above.left != null && above.left.equals(parent)){
							above.left = parent.rotateRight();
						}
					}
				}
			}
			if(p.size() > 0){
				parent = p.pop();
			}
		}
		if(parent != null){
			if(child.equals(parent.right) && parent.priority < child.priority){
				if(parent.equals(root)){
					root = parent.rotateLeft();
				} else{
					parent.rotateLeft();
				}
			} else if(child.equals(parent.left) && parent.priority < child.priority){
				if(parent.equals(root)){
					root = parent.rotateRight();
					} else{
						parent.rotateRight();
					}
			}
		}		
	}

	/**
	 * Given a key, this method creates a random priority for the heap, calls the helper heap 
	 * function add(E key, int priority), and then continuously rotates the heap until all priorities make a heap
	 * @param key
	 * @return
	 */
	boolean add(E key){
		return add(key, priorityGenerator.nextInt(1000));
	}
	
	/**
	 * This function takes a priority and key, and calls the helper funtion addAsLeaf() before calling the helper reheap function
	 * @param key
	 * @param priority
	 * @return
	 */
	boolean add(E key, int priority){
		boolean added = true;
		if(root == null || !this.findPriority(root, priority)){
			added = true;
			if(root == null){
				root = new Node(key, priority);
			} else{
				Stack<Node> path = addAsLeaf(new Node(key, priority));	
				if(path != null){
					reheap(path);
				}
			}
		} else{
			added = false;
		}
		return added;
	}
	
	/**
	 * This method deletes a node by finding it, and rotating it downwards until it reaches a leaf position and can be removed
	 * @param key of node being removed
	 * @return boolean true if successfully deleted
	 */
	boolean delete(E key){
		Node<E> parent = root;
		boolean found = false;
		Node<E> curr = root;
		Node<E> above = parent;
		boolean leftParent = false;
		if(parent.data.equals(key)){
			found = true;
		}
		
		//find node to delete
		while(curr != null && !found){ 
			if(curr.data.compareTo(key) == 0){
				found = true;
			} else {
				while(curr != null && !found){
					if(curr.data.compareTo(key) == 0){
						found = true;
					} else if(curr.data.compareTo(key) < 0){
						parent = curr;
						curr = curr.right;
					} else if(curr.data.compareTo(key) > 0){
						parent = curr;
						curr = curr.left;
					}
				}
			}
		}
		
		if(!found){
			return false;
		} 
		
		//reheap downwards
		while(curr.left != null || curr.right != null){
			if(curr.right != null && curr.left != null){ //reheap according to which is larger
				if(parent.right != null && parent.right.equals(curr)){
					above = parent;
					parent = parent.right;
					leftParent = false;
				} else if(parent.left.equals(curr)){
					above = parent;
					parent = parent.left;
					leftParent = true;
				}
				if(curr.right.priority > curr.left.priority){ //reheap to left with right node and parent
					parent = parent.right;
					if(leftParent){
						above.left = curr.rotateLeft();
					}else{
						above.right = curr.rotateLeft();
					}
				} else{
					parent = parent.left;
					if(leftParent){
						above.left = curr.rotateRight();
					}else{
						above.right = curr.rotateRight();
					}
				}
			} else if(curr.right != null){ //right node is occupied and rotate left must fix this
				if(parent.right != null && parent.right.equals(curr)){
					above = parent;
					parent = parent.right;
					leftParent = false;
				} else if(parent.left != null && parent.left.equals(curr)){
					above = parent;
					parent = parent.left;
					leftParent = true;
				}
				parent = parent.right;
				if(leftParent){
					above.left = curr.rotateLeft();
				}else{
					above.right = curr.rotateLeft();
				}
			} else { //left node is occupied and rotate right must fix this
				if(parent.right != null && parent.right.equals(curr)){
					above = parent;
					parent = parent.right;
					leftParent = false;
				}else if(parent.left != null && parent.left.equals(curr)){
					above = parent;
					parent = parent.left;
					leftParent = true;
				}
				parent = parent.left;
				if(leftParent){
					above.left = curr.rotateRight();
				}else{
					above.right = curr.rotateRight();
				}
			}
		}
		
		if(curr.equals(root)){
			root = null;
			return true;
		} else if (parent != null && curr != null){
			if(parent.right != null && parent.right.equals(curr)){
				parent.right = null;
				return true;
			} else if(parent.left != null && parent.left.equals(curr)){
				parent.left = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * helper method to find which finds the node that has the key and returns true if it there, false if not, within the given subtree
	 * @param root of subtree being looked at
	 * @param key of node being found
	 * @return boolean found or not
	 */
	private boolean find(Node <E> root, E key){
		Node<E> curr = root;
		boolean found = false;
		if(curr.data.compareTo(key) == 0){
			found = true;
		} else {
			while(curr != null && !found){
				if(curr.data.compareTo(key) == 0){
					found = true;
				} else if(curr.data.compareTo(key) < 0){
					curr = curr.right;
				} else if(curr.data.compareTo(key) > 0){
					curr = curr.left;
				}
			}
		}
		return found;
	}
	
	/**
	 * given a key, checks if node with that key is in the heap
	 * @param key
	 * @return
	 */
	public boolean find(E key){
		return find(root, key);
	}
	
	/**
	 * toString helper method
	 * @param curr
	 * @param depth
	 * @return
	 */
	private StringBuilder toString(Node<E> curr, int depth){
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < depth; i++){
			s.append("  ");
		}
		
		if(curr == null){
			s.append("null\n");
			return s;
		}
		
		s.append("(key= " + curr.data.toString() + ", priority= " + curr.priority + ")\n");
		s.append(toString(curr.left, depth+1));
		s.append(toString(curr.right, depth+1));
		return s;
	}
	
	/**
	 * toString returns string version of heap utilizing its helper method
	 * @return string version of heap
	 */
	public String toString (){
		return toString(root, 0).toString();
	}	
}
