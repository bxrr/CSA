package main;

public class Cat {
	// instance fields
	private String name;
	private int age;
	private double weight;
	
	// constructor(s)
	public Cat(String name, int age, double weight, String breed) {
		this.name = name;
		this.age = age;
		this.weight = weight;
	}
	
	// methods
	public String speak() {
		return this.name + ": Woof!";
	}
	
	
	public static void main(String args[]) {
		Cat myCat = new Cat("brandon", 15, 500, "dog");
		System.out.println(myCat.speak());
	}
}

