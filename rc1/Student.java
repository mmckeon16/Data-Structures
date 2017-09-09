package rc1;

public class Student extends Person{
	private float gpa;
	protected int credits;
	
	public Student(String n, int a, float g, int c){
		super(n,a);
		gpa=g;
		credits=c;
	}
	
	public float getGPA(){
		return this.gpa;
	}
	
	public void setGPA(float g){
		this.gpa=g;
	}
	
	public float getCredits(){
		return this.credits;
	}
	
	public void setCredits(int c){
		this.credits=c;
	}
}
