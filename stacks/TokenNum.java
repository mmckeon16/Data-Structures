package stacks;

public class TokenNum extends Token{
	private Integer data;
	
	TokenNum(int i){
		data = i;
	}
	
	public boolean isNum(){
		return true;
	}
	
	public Integer getValue(){
		return data;
	}
	
}
