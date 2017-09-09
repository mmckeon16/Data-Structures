package rc1;

public class Course {
	protected String name;
	protected int cap;
	protected String dept;
	protected Professor prof;
	
	public Course(String n, int c, String d, Professor p){
		this.name = n;
		this.cap=c;
		this.dept=d;
		this.prof=p;
	}
}
