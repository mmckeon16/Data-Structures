package rc1;

public class University {
	private final int SIZE;
	private Person[] faculty;
	private Student[] students;
	private Course[] courses;
	private int facID = 0;
	private int stuID = 0;
	private int courseID = 0;
	
	public University(int s){
		SIZE = s;
		this.faculty = new Person[20];
		students = new Student[30];
		courses = new Course[4];
	}
	
	public void addCourse(Course c){
		if (courseID == courses.length-1){
			System.out.println("Don't add more people, this class is full");
			return;
		}
		this.courses[courseID] = c;
		courseID++;
		System.out.println("added");
	}
	
	public static void main(String[] args){
		University stevens = new University(200);
		Professor k = new Professor("Khayyam", 19, "CS");
		for (int i = 0; i<5;i++)
		stevens.addCourse(new Course("CS284", 17, "CS", k));
	}
}
