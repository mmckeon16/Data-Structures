package queue;

import java.util.NoSuchElementException;

public class LLQueue <E> {
	private class Node<E> {
		private E item;
		private Node<E> next;
		
		Node(E item){
			this.item = item;
			next = null;
		}
		
		Node(E item, Node<E> aNode){
			this.item = item;
			next = aNode;
		}
	}
	
	private Node<E> in;
	private Node<E> out;
	private int size = 0;
	
	/**
	 * adds new item to top
	 * @param item added
	 * @return
	 */
	public boolean offer(E item){
		if (in == null){
			in = new Node<E> (item);
			out = in;
		} else{
			in.next = new Node<E> (item, null);
			in = in.next;
		}
		size++;
		return true;
	}
	
	public E peek(){
		if (out == null){
			throw new NoSuchElementException();
		} else {
			return out.item;
		}
	}
	
	public E remove(){
		if (out == null){
			throw new NoSuchElementException();
		} else {
			Node<E> temp = out;
			out = out.next;
			size--;
			if (out == null){ //last item removed
				in = out;
			}
			return temp.item;
		}
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0; // or (out||in) == null
	}
	
	public static void main(String[] args){
		LLQueue<Integer> i = new LLQueue<Integer>();
		
		i.offer(1);
		i.offer(2);
		i.offer(3);
		i.offer(4);
		
		System.out.println(i.size());
		System.out.println(i.remove());
		System.out.println(i.remove());
		System.out.println(i.remove());
		//System.out.println(i.remove());
	}
}
