package main;

import java.awt.Point;
import java.awt.Rectangle;

public class Ball extends Rectangle {
	private int pX = 1;
	private int pY = 1;
	
	private int sX;
	private int sY;
	
	public Ball(int x, int y, int length) {
		super(x, y, length, length);
		sX = x-length/2;
		sY = y;
	}
	
	public void move_rel(double dx, double dy)
	{
		this.x += dx * pX;
		this.y += dy * pY;
	}
	
	public int check_bounce(int right_bound, int bottom_bound) {
		if(this.getCenterX() - this.getWidth() / 2 <= 0)  return -1;
		else if(this.getCenterX() + this.getWidth() / 2 >= right_bound) return 1;
		else if(this.getCenterY() - this.getHeight() / 2 <= 0) pY = 1;
		else if(this.getCenterY() + this.getWidth() / 2 >= bottom_bound) pY = -1;
		return 0;
	}
	
	public boolean paddle_bounce(Paddle p, boolean left)
	{
		boolean check = left ? 
				this.getCenterX() - this.getWidth() <= p.getMinX() && this.getCenterX() - this.getWidth() >= p.getMinX()-25 : 
				this.getCenterX() + this.getWidth() >= p.getMaxX() && this.getCenterX() + this.getWidth() <= p.getMaxX()+25;
		if(this.getCenterY() + this.getHeight() / 2 >= p.getMinY() && this.getCenterY() - this.getHeight() / 2 <= p.getMaxY()) {
			if(check) {
				pX = left ? Math.abs(pX) : -Math.abs(pX);
				return true;
			}
		}
		return false;
	}
	
	public void reset(boolean left)
	{
		pX = left ? -1 : 1;
		pY = (int) (Math.random() * 2) == 0 ? -1 : 1;
		this.x = sX;
		this.y = sY;
	}
}
