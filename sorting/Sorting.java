package sorting;

public class Sorting <E extends Comparable<E>>{

	public static void merge(Integer[] a, Integer[] b, Integer[] c){
		int ia = 0, ib = 0, ic = 0;
		
		while(ia < a.length && ib < b.length){
			if(a[ia].compareTo(b[ib]) <= 0){
				c[ic] = a[ia];
				ia++;
			} else{
				c[ic] = b[ib];
				ib++;
			}
			ic++;
		}
			while(ia<a.length){
				c[ic] = a[ia];
				ia++;
				ic++;
			}
			while(ib<b.length){
				c[ic] = b[ib];
				ib++;
				ic++;
			}
		}
	
	public static void main(String[] args){
		Integer[] a = {50, 60, 90, 30};
		Integer[] b = {45, 20, 80, 15, 35};
		Integer[] c = new Integer[b.length+a.length];
		
		merge(a,b,c);
		int ic = 0;
		while(ic < c.length){
			System.out.println(c[ic]);
			ic++;
		}
		
		
	}
}
