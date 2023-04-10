package main;
import java.lang.Math;

public class Main {
	public static void main(String args[]) {
		for(int u = -3; u <= 3; u++) {
			System.out.println(" ".repeat(Math.abs(u)) + "*".repeat(7 - 2 * Math.abs(u)));
		}
	}
}
