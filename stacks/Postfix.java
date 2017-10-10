package stacks;

import java.util.List;
//import tokens.*;

public class Postfix {
	private List<Token> tokens;
	private StackSLL<Token> stack;
	
	public Postfix(List<Token> ts){
		tokens = ts;
		stack = new StackSLL<Token>();
	}
	
	public Integer eval() {
		for(int i = 0; i < tokens.size(); i++){
			if(tokens.get(i).isNum()){
				stack.push(tokens.get(i));
			} else {
				Token right = stack.pop();
				Token left = stack.pop();
				
				stack.push(evalOp(((TokenOp)tokens.get(i)), (TokenNum)left, (TokenNum)right));
			}
		}
		return ((TokenNum)stack.pop()).getValue();
	}
	
	private TokenNum evalOp(TokenOp to, TokenNum left, TokenNum right){
		switch (((TokenOp)to).getValue()){
		case '+': return new TokenNum(left.getValue() + right.getValue());
		case '-': return new TokenNum(left.getValue() - right.getValue());
		default: throw new IllegalArgumentException();
		}
	}
	
	public static void main(String[] args){
		Token t = new TokenNum(3);
		
		System.out.println(((TokenNum)t).getValue());
	}
	
}
