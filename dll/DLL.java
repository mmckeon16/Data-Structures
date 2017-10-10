package dll;

import java.util.ArrayList;

public class DLL <E>{
	private static class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> last;
		
		private Node (E elem){
			data = elem;
			next = null;
			last = null;
		}
		
		private Node (E elem, Node<E> prev, Node<E> next){
			data = elem;
			this.next = next;
			last = prev;
		}
	}
	
	public Node<E> head;
	public Node<E> tail;
	public int size;
	public ArrayList<Node<E>> indices;

	public DLL() {
		size = 0;
		head = null;
		tail = null;
		indices = null;
	}
}
