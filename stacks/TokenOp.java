package stacks;

public class TokenOp extends Token{
	private Character data;
	
	private TokenOp(Character c){
		data = c;
	}
	
	public boolean isNum(){
		return false;
	}
	
	public Character getValue(){
		return data;
	}
}
