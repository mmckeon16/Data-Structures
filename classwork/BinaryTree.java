package classwork;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * read the slides Mary
 * @author Mary McKeon
 *
 * @param <E>
 */
public class BinaryTree <E>{
	public class Node<E> {
		protected E data;
		protected Node<E> left;
		protected Node<E> right;
		
		public Node(E data){
			super();
			this.data = data;
		}
		
		public Node(E data, Node<E> left, Node<E> right){
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	protected Node<E> root;
	int size;
	
	BinaryTree(E data){
		root = new Node<E>(data);
		size = 1;
	}
	
	BinaryTree(){
		root = null;
		size = 0;
	}
	
	BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right){
		root = new Node(data, left.root, right.root);
		size = 1 + left.size + right.size;
	}
	
	public int numOfNodesHelper(Node<E> curr){
		if (curr == null){
			return 0;
		} else{
			return 1 + numOfNodesHelper(curr.left) + numOfNodesHelper(curr.right);
		}
	}
	
	public int numOfNodes(){
		return numOfNodesHelper(root);
	}
	
	/**
	 * overloading
	 * @param current
	 * @return
	 */
	private int height(Node<E> current){
		if(current == null){
			return 0;
		} else {
			return 1 + Math.max(height(current.left), height(current.right));
		}
		// or return current == null ? 0 : 1 + Math.max(height(current.left), height(current.right));
	}
	
	public int height(){
		return height(root);
	}
	
	
	private List<String> inOrder(Node<E> curr){
		List<String> l = new ArrayList<String>();
		
		if(curr == null){
			return l;
		}
		
		l.addAll(inOrder(curr.left));
		l.add(curr.data.toString());
		l.addAll(inOrder(curr.right));
		
		return l;
	}
	
	public List<String> inOrder(){
		return inOrder(root);
	}
	
	private StringBuilder toString(Node<E> curr, int depth){
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < depth; i++){
			s.append("  ");
		}
		
		if(curr == null){
			s.append("null\n");
			return s;
		}
		
		s.append(curr.data.toString() + "\n");
		s.append(toString(curr.left, depth+1));
		s.append(toString(curr.right, depth+1));
		return s;
	}
	
	private boolean isBalanced(Node<E> curr){
		if(curr == null){
			return true;
		} else{
			return Math.abs(height(curr.left)-height(curr.right)) <=1 && isBalanced(curr.left) && isBalanced(curr.right);
		}
	}
	
	public boolean isBalanced(){
		return isBalanced(root);
	}
	
	public String toString(){
		return toString(root, 0).toString();
	}
	
	public BinaryTree<Integer> rbt(int depth, int max){
		if(depth == 0){
			return new BinaryTree<Integer>();
		} else {
			Random rand = new Random();
			int rint = rand.nextInt(60);
			BinaryTree<Integer> lt = null;
			BinaryTree<Integer> rt = null;
			if(rint <= 20){
				lt = rbt(depth-1, max);
				rt = rbt(depth-1, max);
			} else if(rint <= 40){
				lt = rbt(depth-1, max);
				rt = new BinaryTree<Integer>();
			}else if(rint<=60){
				lt = new BinaryTree<Integer>();rbt(depth-1, max);
				rt = new BinaryTree<Integer>();
			}
			return new BinaryTree<Integer>(rand.nextInt(max), lt, rt);
		} 
	}
	
	public static void main(String[] args){
		BinaryTree<Integer> t1 = new BinaryTree<Integer>(3);
		BinaryTree<Integer> t2 = new BinaryTree<Integer>(10);
		BinaryTree<Integer> t3 = new BinaryTree<Integer>(24);
		BinaryTree<Integer> t4 = new BinaryTree<Integer>(12, t2, t3);
		BinaryTree<Integer> t5 = new BinaryTree<Integer>(7, t1, t4);
		
		System.out.println("Num of nodes: " + t5.numOfNodes());
		System.out.println("Height: " + t5.height());
		System.out.println(t5.toString());
		
	}
	
}
