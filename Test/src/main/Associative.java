package main;

import java.util.Scanner;

public class Associative {
	private int x;
	private int y;
	private int z;
	
	public Associative(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int firstTwo() {
		return (x + y) * z;
	}
	
	public int lastTwo() {
		return x + y * z;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tempX, tempY, tempZ;
		System.out.println("Enter first num");
		tempX = scan.nextInt();
		System.out.println("Enter second num");
		tempY = scan.nextInt();
		System.out.println("Enter third num");
		tempZ = scan.nextInt();
		
		Associative a = new Associative(tempX, tempY, tempZ);
		System.out.println("grouping first two terms: " + a.firstTwo());
		System.out.println("grouping last two terms: " + a.lastTwo());
	}
}
