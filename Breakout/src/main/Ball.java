package main;
import java.awt.Rectangle;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.awt.Color;

public class Ball extends Rectangle {
	private Color col;
	private int w, h;
	private double dX, dY;
	
	public Ball(int dX, int dY, int sX, int sY, int w, int h) {
		super(sX, sY, w, h);
		this.dX = dX;
		this.dY = dY;
		this.w = w;
		this.h = h;
	}
	
	public void setdX(double dX) {
		this.dX = dX;
	}
	
	public void setdY(double dY) {
		this.dY = dY;
	}
	
	public void increase_d() {
		if(this.dY != 0) {
			this.dX += dX / Math.abs(dX) * 0.001;
			this.dY += dY / Math.abs(dY) * 0.001;
		}
	}
	
	public double getdY() {
		return this.dY;
	}
	
	public double getdX() {
		return this.dX;
	}
	
	public void move(Paddle p1, Scoreboard sb) {
		if(sb.Game == true) {
			this.x += dX;
			this.y += dY;
		}
		if(this.x+this.w >= GDV5.getMaxWindowX() || this.x <= 0) {
			dX *= -1;
		}
		
		if(this.intersects(p1)) {
			this.dY = -Math.abs(dY);
		}
		
		if(this.y > GDV5.getMaxWindowY()) {
			Brick.lives -= 1;
			this.x = 300;
			this.y = 250;
			dX = 0;
			dY = 0;
			final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    	final Runnable runnable = new Runnable() {
	    		int countDownStarter = 3;
	                public void run() {
	                    countDownStarter--;
	                    if (countDownStarter == 0) {
	                    	dX = 6;
	                    	dY = 6;
	                        scheduler.shutdown();
	                    }
	                }
	            };
	            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
		}
		if(this.y <= 0) {
			this.dY = Math.abs(dY);
		}
	}
	
	public void resetBall() {
		this.x = 300;
		this.y = 400;
	}
}