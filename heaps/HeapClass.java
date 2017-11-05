package heaps;

import java.util.ArrayList;

public class HeapClass<E extends Comparable<E>> {
	private ArrayList<E> data;
	
	HeapClass(){
		data = new ArrayList<E>();
	}
	
	private void swap(int i, int j){
		E temp = data.get(i);
		data.set(i, data.get(j));
		data.set(j, temp);
	}
	
	public void add(E item){
		data.add(item); //append item to end of array
		
		int child = data.size()-1;
		int parent = (child-1)/2;
		
		while (parent>=0 && data.get(parent).compareTo(data.get(child)) > 0){
			swap(parent,child);
			child = parent;
			parent = (child-1)/2;
		}
	}
	
	public E remove(){
		if(data.isEmpty()){
			throw new IllegalArgumentException();
		}else if(data.size() == 1){ //only one item here
			E item = data.get(0);
			data.remove(0);
			return item;
		} else{
			E item = data.get(0);
			data.set(0, data.get(data.size()-1));
			data.remove(data.size()-1);
			
			int parent = 0;
			int leftC = 1;
			int rightC = 2;
			
			boolean isHeap = false;
			
			while(leftC < data.size() && !isHeap){
				int minChild = leftC;
				if(rightC < data.size() && data.get(leftC).compareTo(data.get(rightC)) > 0){
					minChild = rightC;
				}
				if(data.get(parent).compareTo(data.get(minChild)) > 0){ //parent greater
					swap(parent,minChild);					
					parent = minChild;
					leftC = parent*2 + 1;
					rightC = parent*2 + 2;
				} else {
					isHeap = true;
				}	
			}
			return item;
		}
	}
	
	public String toString(){
		return data.toString();
	}
	
	public static void main(String[] args){
		HeapClass<Integer> h = new HeapClass<Integer>();
		h.add(7);
		h.add(9);
		h.add(23);
		h.add(52);
		h.add(54);
		h.add(71);
		
		System.out.println(h);
		h.add(3);
		System.out.println(h);
		
		h.remove();
		System.out.println(h);
	}

}
