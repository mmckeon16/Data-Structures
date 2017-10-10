package stacks;

import java.util.EmptyStackException;

public class StackSLL<E> {
	private static class Node<E>{
		private E data;
		private Node<E> next;
		
		private Node (E elem){
			data = elem;
			next = null;
		}
		
		private Node (E elem, Node<E> next){
			data = elem;
			this.next = next;
		}
	}
	
	Node<E> top;
	Integer size;
	
	StackSLL(){
		top=null;
		size = 0;
	}
	
	public E push(E item){
		top = new Node<E>(item, top);
		size++;
		return top.data;
	}
	
	public boolean isEmpty(){
		return top  == null;
	}
	
	public E peek(){
		if (isEmpty()){
			throw new EmptyStackException();
		}
		return top.data;
	}
	
	public E pop(){
		if (isEmpty()){
			throw new EmptyStackException();
		}
		E temp = top.data;
		top = top.next;
		size--;
		return temp;
	}
}
