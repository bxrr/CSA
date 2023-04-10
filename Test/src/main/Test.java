package main;

public class Test {
	public Test(int a ) { 
		System.out.println("hei");
	}
	public static int timesTwo ()

	{

		return ((int) (Math.random() * 11)) + 5;

	}
	
	public static void main(String args[]) {
		while(true) System.out.println(timesTwo());
		Test a = new Test(5.0);
	}
}
