package main;
import java.awt.Rectangle;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.awt.Graphics2D;

public class Brick extends Rectangle {
	
	private int x, y, width, height, hits; 
	
	public static int currLevel = 1;
	Color col;
	private boolean alive;
	public static Brick[] bricks = new Brick[0];
	public static Particle[] particles = new Particle[20];
	public static int points = 0;
	public static boolean inProgress = false;
	public static int lives = 3;
	public Brick(int startX, int startY, int width, int height, int hits, boolean alive) {
		super(startX, startY, width - 3, height);
		this.x = startX;
		this.y = startY;
		this.width = width;
		this.height = height;
		this.hits = hits;
		this.alive = true;
		col = Color.white;
		this.changeBrick();
	}
	
	public int getHits() {
		return this.hits;
	}
	
	public static void makeBricks(int numOfBricks, int bricksPerRow) {
		if(!Brick.inProgress) {
			Brick.inProgress = true;
			bricks = new Brick[numOfBricks];
			int x = 1, y = 1;
			int currLifeCount = numOfBricks / bricksPerRow + 1;
			int count = 1;
			for(int i = 0; i < bricks.length; i ++) {
				bricks[i] = new Brick(x, y, GDV5.getMaxWindowX()/bricksPerRow, 20, currLifeCount, true);
				x += GDV5.getMaxWindowX()/bricksPerRow;
				if(count % bricksPerRow == 0) {
					x = 1;
					y += bricks[i].getHeight() + 2;
					currLifeCount --;
				}
				count ++;
			}
		}
	}
	
	public void changeBrick() {
		this.hits -= 1;
		switch(this.hits) {
			case 0:
				this.alive = false;
				break;
			case 1:
				col = Color.white;
				break;
			case 2:
				col = Color.red;
				break;
			case 3: 
				col = Color.orange;
				break;
			case 4:
				col = Color.yellow;
				break;
			case 5:
				col = Color.green;
				break;
			case 6: 
				col = Color.blue;
				break;
			case 7:
				col = Color.magenta;
				break;
			case 8:
				col = Color.pink;
				break;
			case 9:
				col = Color.gray;
				break;
			case 10:
				col = Color.cyan;
				break;
		}
	}
	
	public void collided() {
		changeBrick();
		points += 1000;
	}
	public void draw(Graphics2D pb) {
		if(this.alive == true) {
			pb.setColor(col);
			pb.fill(this);
		}
	}
	
	public void makeParticles() {
		int count = 0;
		for(int i = 0; i < this.particles.length; i ++) {
			particles[i] = new Particle(this.x+count, this.y, 3, 3, this.col);
			count += this.width/10;
			if(i == 9) {
				count = 0;
				y += this.height/2;
			}
		}
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    	final Runnable runnable = new Runnable() {
    		int countDownStarter = 2;
                public void run() {
                    countDownStarter--;
                    if (countDownStarter == 0) {
                    	makeParticlesNull();
                        scheduler.shutdown();
                    }
                }
            };
            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
	}
	public void makeParticlesNull() {
		for(int i = 0; i < this.particles.length; i ++) {
			this.particles[i] = null;
		}
	}
	public static void collideWithBall(Ball ball) {
		
		for(Brick b : bricks) {
			if(b.intersects(ball) && b.alive == true) {
				if(Math.random() > 0.5) {
					Powerup.powers.add(new Powerup(b.x, b.y));
				}
				Scoreboard.addScore();
				int dir = GDV5.collisionDirection(b, ball, (int)ball.getdX(), (int)ball.getdY());
				b.makeParticles();
				b.collided();
				if(dir == 1 || dir == 3) {
					ball.setdY(ball.getdY()*-1);
				}
				else if(dir == 0 || dir == 2) {
					ball.setdX(ball.getdX()*-1);
				}
			}
		}
	}
	
	
	
	public static void destroyBricks() {
		for(Brick b : Brick.bricks) {
			b.collided();
		}
	}
	
	public static void changeLevel() {
		Brick.currLevel ++;
		Brick.inProgress = false;
		Brick.lives = 3;
		Paddle.speed = 6;
	}
	
	public static void drawBricks(Graphics2D win, Ball ball) {
		if(!stillBricks()) {
			ball.resetBall();
			ball.setdX(0);
			ball.setdY(0);
			final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    	final Runnable runnable = new Runnable() {
	    		int countDownStarter = 3;
	                public void run() {
	                    countDownStarter--;
	                    if (countDownStarter == 0) {
	                    	ball.setdX(6);
	                    	ball.setdY(5);
	                        scheduler.shutdown();
	                    }
	                }
	            };
	            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
			changeLevel();
			
		}
		for(int i = 0; i < Brick.bricks.length; i ++) {
			Brick.bricks[i].draw(win);
		}
	}
	
	public static boolean stillBricks() {

		for(int i = 0; i < Brick.bricks.length; i ++) {
			if(Brick.bricks[i].alive == true) {
				return true;
			}
		}
		return false;
	}
	
	public static void drawParticles(Graphics2D win) {
		for(int i = 0; i < Brick.particles.length; i ++) {
			if(Brick.particles[i] != null) {
				win.setColor(Brick.particles[i].col);
				win.fill(Brick.particles[i]);
				Brick.particles[i].move();
			}
		}
	}
}