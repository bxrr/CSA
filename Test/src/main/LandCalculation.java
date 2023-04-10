package main;

import java.util.Scanner;
public class LandCalculation {
	public static void main(String args[]) {
		System.out.print("How many square feet of land do you own?");
		double acres;
		Scanner red = new Scanner(System.in);
		acres = red.nextInt();
		acres = acres/43560;
		System.out.println("You have " + acres + " acres");
	}
}
