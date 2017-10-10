package hw3;

import java.util.ArrayList;

/**
 * 
 * @author Mary McKeon
 * Section B
 * I pledge my honor that I have abided by the Stevens Honor System
 *
 */
public class IDList <E> {
	private static class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		private Node (E elem){
			data = elem;
			next = null;
			prev = null;
		}
		
		private Node (E elem, Node<E> prev, Node<E> next){
			data = elem;
			this.next = next;
			this.prev = prev;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	public IDList() {
		size = 0;
		head = null;
		tail = null;
		indices = new ArrayList<Node<E>>();
	}
	
	/**
	 * 
	 * This method adds a new Node at an index, and updates the indices Array accordingly
	 * 
	 * @param index where node will be inserted into doubly linked list
	 * @param elem the data of the new node being added
	 * @return boolean true if successful in creating new node
	 */
	public boolean add(int index, E elem){
		if (index < 0 || index > indices.size()){
			throw new IndexOutOfBoundsException();
		} else if (index == 0){
			Node <E> newHead = new Node<E>(elem, null, head);
			head.prev = newHead;
			head = newHead;
			if (head == null){ //the list is empty
				tail = head;
			}
			indices.add(0, new Node<E>(elem, null, head));
			return true;
		} else if (index == indices.size()){
			append(elem);
			return true;
		} else{
			Node <E> insertion = new Node<E>(elem, indices.get(index-1).next, indices.get(index).next);
			indices.get(index-1).next.next = insertion;
			indices.get(index).next.prev = insertion;
			indices.add(index, new Node<E>(elem, null, insertion));
			return true;
		}
	}
	
	/**
	 * Adds an element to an array at the beginning of an array
	 * 
	 * @param elem object you want to place in the node
	 * @return boolean true if it was placed
	 */
	public boolean add(E elem){
		if (head != null){ 
			Node <E> newHead = new Node<E>(elem, null, head);
			head.prev = newHead;
			head = newHead;
			indices.add(0, new Node<E>(elem, null, head));
			return true;
		} else { //the list is empty
			head = new Node<E>(elem, null, null);
			tail = head;
			indices.add(new Node<E>(elem, null, head));
			return true;
		}
	}
	
	/**
	 * This method creates a new node, and adds it to the end of the list
	 * @param elem to be appended in a node at the end of the list
	 * @return boolean if elem was appended
	 */
	public boolean append (E elem){
		if (tail != null){
			Node <E> newTail = new Node<E>(elem, tail, null);
			tail.next = newTail;
			tail = newTail;
			indices.add(indices.size(), new Node<E>(elem, null, tail));
			return true;
		} else { //the list is empty
			tail = new Node<E>(elem, null, null);
			head = tail;
			indices.add(indices.size(), new Node<E>(elem, null, tail));
			return true;
		}
	}	
	
	/**
	 * 
	 * @param index of item you want to get
	 * @return E item in array at requested index
	 */
	public E get (int index){
		if (index < 0 || index > indices.size()){
			throw new IndexOutOfBoundsException();
		} else {
			return indices.get(index).next.data;
		}
	}
	
	/**
	 * 
	 * @return E element in head
	 */
	public E getHead (){
		return this.head.data;
	}
	
	/**
	 * 
	 * @return E element in tail
	 */
	public E getLast (){
		return this.tail.data;
	}
	
	/**
	 * 
	 * @return integer size
	 */
	public int size(){
		return indices.size();
	}
	
	/**
	 * removes the item at head and makes the next node head
	 * @return E that was removed from the head position
	 */
	public E remove(){
		if (head == null){
			return null;
		}else{
			Node <E> temp = head;
			head = head.next;
			indices.remove(0);
			return temp.data;
		}
	}
	
	/**
	 * removes the tail item and returns the data in tail
	 * @return E data in tail
	 */
	public E removeLast(){
		if (tail == null){
			return null;
		} else{
			Node <E> temp = tail;
			tail = tail.prev;
			tail.next = null;
			indices.remove(indices.size()-1);
			return temp.data;
		}
	}
	
	/**
	 * Removes the node at the specified index
	 * @param index of object being removed
	 * @return E data of node that was removed
	 */
	public E removeAt(int index){
		if (head == null){
			return null;
		} else if (index == 0){
			return remove();
		}else if(index == indices.size()-1){
			return removeLast();
		} else if (index > 0 && index < indices.size()){
			Node <E> temp = indices.get(index).next;
			indices.get(index-1).next.next = indices.get(index).next.next;
			indices.get(index+1).next.prev = indices.get(index).next.prev;
			indices.remove(index);
			return temp.data;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * removes the first occurrence of element E in the IDList
	 * @param elem that will be removed
	 * @return bool true if removed and false if not
	 */
	public boolean remove(E elem){
		if (head == null){
			return false;
		} else {
			Node<E> curr = head;
			int count = 0;
			while (curr != null){
				if (curr.data == elem){
					removeAt(count);
					return true;
				} 
				curr = curr.next;
				count++;
			}
			return false;
		}
	}
	
	/**
	 * @return String version of Double Linked List
	 */
	public String toString(){
		String theString = "[";
		for (int i = 0; i < indices.size(); i++){
			if (indices.get(i).data == null){
				theString += "null,";
			} else {
			theString += indices.get(i).data.toString() + ",";
			}
		}
		theString = theString.substring(0, theString.length()-1) + "]";
		return theString;
	}
}
