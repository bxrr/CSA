package main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class Runner extends GDV5 {
	Paddle paddle = new Paddle(GDV5.getMaxWindowX()/2-35, 700 , 70, 10);
	Ball ball = new Ball(6, 5, 300, 250, 10, 10);
	Scoreboard sb = new Scoreboard();
	public boolean temp = true;

	public static void main(String[] args) {
		Runner r = new Runner();
		r.start();
	}

	
	public void update() {
		ball.move(paddle, sb);
		ball.increase_d();
		Brick.collideWithBall(ball);
		paddle.movePaddle(KeysPressed);
		Powerup.collideWithPaddle(paddle);
		if(KeysPressed[KeyEvent.VK_B]) {
			if(temp) {
				Brick.destroyBricks();
				temp = false;
			}
		}
		if(KeysPressed[KeyEvent.VK_R]) {
			temp = true;
		}
	}

	
	public void draw(Graphics2D win) {
		sb.choiceSelector(win, KeysPressed);
		if(sb.Game == true) {
			int totalBricks = Brick.currLevel*9;
			Brick.makeBricks(totalBricks, 9);
			Brick.drawBricks(win, ball);
			Brick.drawParticles(win);
			for(Powerup p : Powerup.powers)
			{
				p.move(win);
			}
			sb.drawScore(win);
			win.setColor(Color.white);
			win.fill(paddle);
			win.setColor(Color.red);
			win.fill(ball);
		}
		else if(sb.helpScreen) {
			sb.drawHelpScreen(win);
		}
		else if(sb.lost) {
			sb.drawLoseScreen(win);
		}
		else {
			sb.startScreen(win);
		}
	}	
}