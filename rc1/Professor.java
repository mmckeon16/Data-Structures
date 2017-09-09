package rc1;

public class Professor extends Person {
	protected String department;
	
	public Professor(String n, int a, String d){
		super(n, a);
		department = d;
	}
}
