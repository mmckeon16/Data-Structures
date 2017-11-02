package classwork;

public class MidtermReview <E> {
	private class Node<E> {
		private E data;
		private Node<E> next;
		
		Node(E item){
			data = item;
			next = null;
		}
		
		Node(E item, Node<E> aNode){
			data = item;
			next = aNode;
		}
	}
	
	private Node<E> in;
	private Node<E> out;
	private int size;
	
	private boolean member(Node<Integer> list, Integer i){
		Node<Integer> curr = list;
		boolean found = false;
		
		while(!found && curr != null){
			found = found || curr.data.equals(i);
			curr = curr.next;
		}
		return found;
	}
	
	public int checkTestResults(Node<Integer> testResults, Node<Integer> expectedResults){
		Node<Integer> currentTR = testResults;
		Node<Integer> currentET = expectedResults;
		int total = 0;
		
		while (currentTR != null && currentET != null){
			if (currentTR.data == currentET.data){
				total++;
			}
			currentTR = currentTR.next;
			currentET = currentET.next;
		}
		if (currentTR == null && currentET == null){
			return total;
		}
		throw new IllegalArgumentException();
	}
	
	private boolean hasRepitions(Node<Integer> list){
		Node<Integer> curr = list;
		boolean hasRep = false;
		
		while (!hasRep && curr != null){
			hasRep = hasRep || member(curr.next, curr.data);
			curr = curr.next;
		}
		return hasRep;
	}
	
	public E removeBob(){
		if (out == null){
			throw new IllegalArgumentException();
		}
		E temp = out.data;
		out = out.next;
		size--; //did not have
		if (out == null){ //did not have
			in = null;
		}
		return temp;
	}
	
	public E removeAlice(){
		if (out == null){
			throw new IllegalArgumentException();
		}
		E temp = out.data;
		Node<E> curr = in;
		if (out == in){
			out = null;
			in = null;
			size--;
			return temp;
		}
		while(curr.next != out){
			curr = curr.next;
		}
		out = curr;
		out.next = null;
		size --;
		
		return temp;
	}

}
