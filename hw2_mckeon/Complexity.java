package hw2_mckeon;

/*
 * Mary McKeon Section B
 */
public class Complexity {

	public void method1(int n){
		int counter = 0;
		for (int i = 0; i < n; i++){
			for (int k = 0; k < n; k++){
				System.out.println("Operation" + counter);
				counter++;
			}
		}
	}
	
	public void method2(int n){
		int counter = 0;
		for (int i = 1; i < n; i=i*2){
			System.out.println("Operation" + counter);
			counter++;
		}
	}
	
	public void method3(int n){
		int counter = 0;
		for (int k = 0; k < n-1; k++){
			for (int i = 1; i < n; i=i*2){
				System.out.println("Operation" + counter);
				counter++;
			}
		}
	}
	
	public void method4(int n){
		int counter = 0;
		for (int i = 0; i < n; i++){
			for (int k = 0; k < n; k++){
				for (int j = 0; j < n; j++){
					System.out.println("Operation" + counter);
					counter++;
				}
			}
		}
	}
	
	public void method5(int n){
		int counter = 0;
		for (double k = n; k > 2; k = Math.sqrt(k)){
			System.out.println("Operation" + counter);
			counter++;
		}
	}
	
	public int method6(int n){
		if (n <= 1){
			return n;
		} else{
			return (method6(n-1) + method6(n-2));
		}
	}
}
