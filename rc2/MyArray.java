package rc2;

/**
 * 
 * @author mmcke
 *
 * @param <E> recitation, messing around with generics, made own array class
 */
public class MyArray<E> {
	private E[] data;
	private int size;
	private int curr = 0;
	
	private static class Node<E>{
		E data;
		int num;
		
		public Node(E d, int n){
			data = d;
			num =n;
		}
	}
	
	@SuppressWarnings("unchecked")
	public MyArray(int size){
		this.size = size;
		this.data = (E[])(new Object[size]);
	}
	
	/**
	 * 
	 * @param index of item getting
	 * @return E element
	 */
	public E get(int index){
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		} else{
			return this.data[index];
		}
	}
	
	/**
	 * 
	 * @param elem you are adding at the end of array
	 */
	public void add(E elem){
		if (curr < 0 || curr >= size){
			throw new IndexOutOfBoundsException();
		}else{
			this.data[curr] = elem;
			curr++;
		}
	}
	
	/**
	 * 
	 * @param elem that you are adding
	 * @param index adding to array
	 */
	public void add(E elem, int index){
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}else{
			this.data[index] = elem;
		}
	}
	/**
	 * returns and removes last populated element
	 * @return E element removed
	 */
	public E remove(){
		if (curr == 0){
			return data[0];
		}else{
			E temp = data[curr];
			data[curr] = null;
			curr--;
			return temp;
		}
	}
	
	/**
	 * removes and returns item at index
	 * @param index of removing item
	 * @return E array
	 */
	public E remove(int index){
		if (index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException();
		}else{
			E temp = data[curr];
			data[curr] = null;
			if (curr != 0){
				curr--;
			}
			return temp;
		}
	}
	
	/**
	 * prints array on same line
	 */
	public void print(){
		for (E temp: this.data){
			System.out.print(temp + " ");
		}
	}
	
	/**
	 * @return String representation of Array
	 */
	public String toString(){
		String out ="";
		out += "[";
		for (E temp: this.data){
			out += temp + " ";
		}
		out += "]";
		return out;
	}
	
	public static void main(String[] args){
		MyArray<String> s = new MyArray<String>(5);
		s.add("hello");
		s.add("hola");
		s.add("nick", 1);
		s.print();
		System.out.print(s);
		
	//	MyArray<Node<Boolean>> b = new MyArray<Node<boolean>>(true, 3);
	}
}
