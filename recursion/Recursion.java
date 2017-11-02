package recursion;

public class Recursion {
	
	public static double factorial(double n){
		if (n == 1){
			return 1;
		} else {
			return n * factorial(n-1);
		}
	}
	
	/**
	 * this is automatically turned into a while loop by the compiler
	 * @param n
	 * @param a
	 * @return
	 */
	private static double tailRec(double n, double a){
		if (n==0){
			return a;
		} else {
			return tailRec(n-1, n*a);
		}
	}
	
	public static double tailRec(double n){
		return tailRec(n,1);
	}
	
	public static double iterativeFact(double n){
		double result = 1;
		for (int i = 1; i <= n; i++){
			result = result * i;
		}
		
		return result;
	}
	
	/**
	 * exponential growth rate
	 * @param n
	 * @return
	 */
	public static double fib(double n){
		if (n<=1){
			return 1;
		} else {
			return fib(n-1) + fib(n-2);
		}
	}
	
	private static double fibMem(double old, double curr, double n){
		if (n<=1){
			return curr;
		} else {
			return fibMem(curr, old + curr, n-1);
		}
	}
	
	public static double fibMem(double n){
		return fibMem(1, 1, n);
	}
	
	public static void main(String[] args){
		
	}
}
