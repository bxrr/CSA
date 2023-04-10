package main;

import java.awt.Rectangle;

public class Paddle extends Rectangle {
	private int sX;
	private int sY;
	
	public Paddle(int x, int y) {
		super(x, y, 6, 80);
		sX = x;
		sY = y;
	}
	
	public void move(int d) {
		y += d;
	}
	
	public void reset() {
		x = sX;
		y = sY;
	}
}
