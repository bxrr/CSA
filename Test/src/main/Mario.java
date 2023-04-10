package main;

import java.util.Scanner;

public class Mario {
	public static void main(String args[]) {
		System.out.print("Enter number of rows: ");
		Scanner u_in = new Scanner(System.in);
		int num = u_in.nextInt();
		
		for(int i = num; i > 0; i--) {
			// left side stuff
			for(int u = 0; u < i-1; u++) {
				System.out.print(" ");
			}
			
			for(int g = 0; g < num-i+1; g++) {
				System.out.print("#");
			}
			
			System.out.print("  ");
			
			
			// right side stuff
			for(int g = 0; g < num-i+1; g++) {
				System.out.print("#");
			}
			
			for(int u = 0; u < i-1; u++) {
				System.out.print(" ");
			}
			
			System.out.println();
		}
	}
}
