package main;
import java.util.Scanner;


public class HourlyRate {
	public static void main(String args[]) {
		double hours;
		String name;
		double pay;
		System.out.print("What is your name? ");
		Scanner reader = new Scanner(System.in);
		name = reader.nextLine();
		System.out.println("How many hours did you work this week? ");
		hours = reader.nextDouble();
		System.out.println("What is your hourly pay rate? ");
		pay = reader.nextDouble();
		System.out.println("Hello, " + name);
		double total;
		total = hours*pay;
		System.out.println("Your gross pay is $" )
	}
}
