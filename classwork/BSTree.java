package classwork;

public class BSTree<E extends Comparable> extends BinaryTree<E> {
	
	private boolean addOK = false;
	
	public BSTree(){
		super();
	}
	
	public BSTree(E key){
		super(key);
	}
	
	public BSTree(E key, BSTree<E> left, BSTree<E> right){
		super(key, left, right);
	}
	
	private boolean find(E key, Node<E> curr){
		if(curr == null){
			return false;
		}
		if (curr.data.compareTo(key) == 0){
			return true;
		} else if(curr.data.compareTo(key)<0){
			return find(key, curr.right);
		} else {
			return find(key, curr.left);
		}
	}
	
	/**
	 * Linear operation - worst case have to go through every node when Tree looks like list
	 * @param key
	 * @return
	 */
	public boolean find (E key){
		return find(key, root);
	}
	
	public Node<E> addLeaf(E item, Node<E> curr){
		if(curr == null){
			//addOk = true;
			return new Node<E>(item);
		}
		if(curr.data.compareTo(item) == 0) {
			 //addOk = false;
			 return curr;
		} else if(curr.data.compareTo(item) < 0){
			curr.right = addLeaf(item, curr.right);
			return curr;
		} else {
			curr.left = addLeaf(item, curr.left);
			return curr;
		}
	}
	
	public boolean addLeaf(E item){
		root = addLeaf(item, root);
		return true;
	}
	
	private E findMax(Node<E> curr){
		if(curr.data == null){
			throw new IllegalArgumentException();
		} else if(curr.right == null){
			return curr.data;
		} else{
			return findMax(curr.right);
		}
	}
	
	public E findMax(){
		return findMax(this.root);
	}
	
	public boolean isLeaf(Node<E> curr){
		return curr.left == null && curr.right == null;
	}
	
	public Node<E> removeMaxHelper(Node<E> curr){
		if(curr.right == null){
			if(curr.left == null){
				return null;
			} else{
				return curr.left;
			}
		} else{
			curr.right = removeMaxHelper(curr.right);
			return curr;
		}
	}
	
	
	public E removeMax(Node<E> curr){
		if(curr == null){
			throw new IllegalArgumentException();
		}
		E temp = findMax(curr);
		removeMaxHelper(curr);
		return temp;
	}
	
	//Alternate solution
	private E find_max_and_remove(Node<E> curr){
		if(curr.right.right == null){
			E temp = curr.right.data;
			curr.right = curr.right.left;
			return temp;
		} else{
			return find_max_and_remove(curr.right);
		}
	}
	
	private Node<E> remove(E item, Node<E> curr){
		if(curr == null){
			throw new IndexOutOfBoundsException();
		}
		int comparison = curr.data.compareTo(item);
		if(comparison < 0){ //item >data
			curr.right = remove(item, curr.right);
			return curr;
		} else if(comparison > 0){ //item<data
			curr.left  = remove(item, curr.left);
			return curr;
		} else{ //item = data
			if(isLeaf(curr)){
				return null;
			} else if(curr.left == null){ //node has only right child
				return curr.right;
			} else if(curr.right == null){ // node has only left child
				return curr.left;
			} else if(curr.left.right == null){
					curr.left.right = curr.right;
					return curr.left;
			} else{
				curr.data = removeMax(curr.left);
				return curr;
			}
		}
	}
	
	/**
	 * 
	 * @param rem
	 */
	public Node<E> remove(E item){
		return remove(item, root);
	}
	
	public String toString(Node<E> temp){
		return "Node" + temp.data;
	}
	
	public static void main(String[] args){
		BSTree<Integer> t1 = new BSTree<Integer>(3);
		BSTree<Integer> t2 = new BSTree<Integer>(24, new BSTree<Integer>(14), new BSTree<Integer>());
		BSTree<Integer> t3 = new BSTree<Integer>(12, new BSTree<Integer>(10), t2);
		BSTree<Integer> t4 = new BSTree<Integer>(7, t1, t3);
		
		System.out.println(t4);
		
		System.out.println(t4.find(14));
		System.out.println(t4.find(27));
		t4.addLeaf(11);
		System.out.println(t4.remove(24).toString());
	}
}
