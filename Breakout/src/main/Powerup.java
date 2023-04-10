package main;

import java.util.*;
import java.awt.Rectangle;

import java.awt.Color;
import java.awt.Graphics2D;

public class Powerup extends Rectangle {
	
	private int type;
	public static ArrayList<Powerup> powers = new ArrayList<Powerup>();
	
	public Powerup(int startX, int startY) {
		super(startX, startY, 10, 20);
		type = (int) (Math.random() * 2);
		powers.add(this);
	}
	
	public void move(Graphics2D win) {
		if(this.type == 0) {
			win.setColor(Color.red);
			win.fill(this);
		}
		else if (this.type == 1) {
			win.setColor(Color.blue); 
			win.fill(this);
		}
		
		this.y += 7;
	}
	
	public static void collideWithPaddle(Paddle p) {
		for(Brick b : Brick.bricks) {
			for(int i = 0; i < powers.size(); i ++) {
				if(powers.get(i).intersects(p)) {
					if(powers.get(i).type == 0) {
						Brick.lives += 1;
					}
					else if (powers.get(i).type == 1) {
						Paddle.speed += 1;
					}
					powers.remove(i);
				}
			}
		}
	}
}
