package main;

import java.util.Scanner;

public class SalesTax {
	public static void main(String args[]) {
		System.out.println("Enter amount of purchase");
		Scanner reader = new Scanner(System.in);
		double cost = reader.nextDouble();
		double state_tax = cost * 0.04;
		double county_tax = cost * 0.02;
		System.out.println("Cost: " + cost + "\nState Tax: " + state_tax + "\nCounty Tax: " + county_tax + 
						   "\nTotal tax: " + (state_tax + county_tax) + "\nTotal: " + (cost + state_tax + county_tax));
	}
}
