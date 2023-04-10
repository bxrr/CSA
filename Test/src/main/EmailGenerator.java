package main;

import java.lang.Math;
import java.util.Scanner;

public class EmailGenerator {
	public static String makeUserName(String firstName, String lastName) {
		return firstName.toLowerCase() + lastName.toLowerCase().charAt(0);
	}
	
	public static String makeEmail(String name, int id, String domain) {
		return name + id / 10000 + "@" + domain;
	}
	
	public static int makeIdNumber() {
		return (int) (Math.random() * 90000000) + 10000000;
	}
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your full name: ");
		String firstName = scan.next();
		String lastName = scan.next();
		System.out.println("This user's email is: " + makeEmail(makeUserName(firstName, lastName), makeIdNumber(), "mytusd.org"));
	}
}
