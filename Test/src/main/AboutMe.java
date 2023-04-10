package main;

public class AboutMe {
	private String name;
	private String school;
	private int age;
	
	public AboutMe(String name, String school, int age) {
		this.name = name;
		this.school = school;
		this.age = age;
	}
	
	public String myName() {
		return name;
	}
	
	public String mySchool() {
		return school;
	}
	
	public int myAge() {
		return age;
	}
	
	public static void main(String args[]) {
		AboutMe bx = new AboutMe("Brandon", "Beckman", 17);
		System.out.println("My name is " + bx.myName() + ", and I attend " + bx.mySchool() + ". I am " + bx.myAge() + " years old.");
	}
}
