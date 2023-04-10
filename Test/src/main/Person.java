package main;

public class Person {
	private String name;
	private int age;
	private String hobby;
	private double heightCM;
	private int weight;
	
	public Person(String name, int a, double h, int weight) {
		this.name = name;
		age = a;
		heightCM = h;
		this.weight = weight;
	}
	
	public void setHobby(String h) {
		this.hobby = h;
	}
	
	public static void main(String args[]) {
		Person person = new Person("Brandon", 17, 160, 140);
		System.out.println(person.age);
		System.out.println(person.hobby);
	}
}
