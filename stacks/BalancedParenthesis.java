package stacks;

import java.util.EmptyStackException;

public class BalancedParenthesis {
	private static final String OPEN = "([{";
	private static final String CLOSE = ")]}";
	private String exp;
	StackSLL<Character> s;
	
	BalancedParenthesis(String exp){
	this.exp = exp;
	s = new StackSLL<Character>();
	}
	
	public boolean isBalanced(){
		int i = 0;
		boolean balanced = true;
		try {
		while (balanced && i<exp.length()){
			if (isOpen(exp.charAt(i))){
				s.push(exp.charAt(i));
			} else {
				balanced = CLOSE.indexOf(exp.charAt(i)) == OPEN.indexOf(s.peek());
				s.pop();
			}
			i++;
		}
		} catch (EmptyStackException e){
			balanced = false;
		}
		return balanced && s.isEmpty();
	}
	
	public static boolean isOpen(char c){
		return OPEN.indexOf(c) > -1;
	}
	
	public static boolean isClose(char c){
		return CLOSE.indexOf(c) > -1;
	}
	
	public static void main (String[] args){
		BalancedParenthesis bp = new BalancedParenthesis("[[]]{()}");
		System.out.println(bp.isBalanced());
		
	}
}
