package main;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;


public class Particle extends Rectangle {

	private double speed; 
	private int counter;
	
	public Particle(int startX, int startY, int x, int y, Color col) {
		super(startX, startY, x, y);
		this.col = col;
		this.speed = 4;
		this.counter = 0;
	}
	
	public void move() {
		
		if(counter % 150 == 0) {
			int direction = (int) (Math.random() * 360);
			
			double angle = direction;
			this.x += (this.speed * Math.cos(angle));
			this.y += (this.speed * Math.sin(angle));
		}
		this.counter ++;
	}
	
	Color col;
	
	public void draw(Graphics2D win) {
		for(int i = 0; i < Brick.particles.length; i ++) {
			if(Brick.particles[i] != null) {
				win.setColor(Brick.particles[i].col);
				win.fill(Brick.particles[i]);
			}
		}
	}
}