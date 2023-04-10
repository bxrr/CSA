package main;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
	private int pX, pY, w, h;
	public static int speed;
	
	public Paddle(int startX, int startY, int w, int h) {
		super(startX, startY, w, h);
		this.pX = startX;
		this.pY = startY;
		this.w = w;
		this.h = h;
		speed = 7;
	}
	
	public void movePaddle(boolean[] KeysPressed) {
		if(this.x + this.w > 800) {
			this.x = 800;
		}
		if(KeysPressed[KeyEvent.VK_LEFT] && this.x > 0) {
			this.x -= this.speed;
		}
		if(KeysPressed[KeyEvent.VK_RIGHT] && this.x + this.w < 600) {
			this.x += this.speed;
		}
		if(KeysPressed[KeyEvent.VK_A]  && this.x > 0) {
			this.x -= this.speed;
		}
		if(KeysPressed[KeyEvent.VK_D]  && this.x + this.w < 600) {
			this.x += this.speed;
		}
	}
}