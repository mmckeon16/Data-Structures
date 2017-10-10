package stacks;

import java.util.Stack;

public class PalindromeChecker {
	private String theString;
	private Stack<Character> s = new Stack<Character>();
	
	PalindromeChecker(String str){
		theString = str;
	}
	
	private void fillStack(){
		for (int i = 0; i<theString.length(); i++){
			s.push(theString.charAt(i));
		}
	}
	
	private String buildReverse(){
		fillStack();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i<theString.length(); i++){
			result.append(s.pop());
		}
		return result.toString();
	}
	
	public boolean isPalindrome(){
		return theString.replace(" ", "").equalsIgnoreCase(buildReverse().replace(" ", ""));
	}
	
	public static void main(String[] args){
		PalindromeChecker c1 = new PalindromeChecker("kay ak");
		
		System.out.println(c1.isPalindrome());
	}
}
