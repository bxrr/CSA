package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class Scoreboard {
	public static int score = 0;
	boolean lockedChoiceSelection, helpScreen, Game, lost;
	
	public Scoreboard() {}
	
	public void drawScore(Graphics2D win) {
		win.setColor(Color.white);
		win.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		win.drawString("Points: " + Scoreboard.score + "    Lives: " + Brick.lives, 15, GDV5.getMaxWindowY()-15);
	}
	
	public static void addScore() {
		Scoreboard.score += 1000;
	}
	
	public void drawHelpScreen(Graphics2D win) {
		win.setColor(Color.white);
		win.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		win.drawString("Hit the ball with your paddle to destroy bricks", 25, 200);
		win.drawString("You gain points whenever a brick is destroyed ", 25, 260);		
		
		win.drawString("Arrow keys to move left/right", 25, 400);
		win.drawString("Backspace to return", 25, 460);
	}
	
	public void drawLoseScreen(Graphics2D win) {
		win.setColor(Color.white);
		win.setFont(new Font("Bahnschrift", Font.BOLD, 32));
		win.drawString("YOU LOST: LEVEL " + Brick.currLevel + "!", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 40);
		win.drawString("Backspace to return", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 100);
	}
	
	public void choiceSelector(Graphics2D win, boolean[] KeysPressed) {
		if(KeysPressed[KeyEvent.VK_1] && !this.lockedChoiceSelection) {
			this.Game = true;
			this.helpScreen = false;
			this.lockedChoiceSelection = true;
		}
		if(KeysPressed[KeyEvent.VK_2] && !this.lockedChoiceSelection) {
			this.helpScreen = true;
			this.Game = false;
			this.lockedChoiceSelection = true;
		}
		if(Brick.lives == 0) {
			this.helpScreen = false;
			this.Game = false;
			this.lockedChoiceSelection = false;
			this.lost = true;
		}
		if(KeysPressed[KeyEvent.VK_BACK_SPACE]) {
			Brick.lives = 3;
			this.helpScreen = false;
			this.Game = false;
			this.lockedChoiceSelection = false;
			this.lost = false;
		}
		
	}
	
	public void startScreen(Graphics2D win) {
		win.setColor(Color.white);
		win.setFont(new Font("Bahnschrift", Font.BOLD, 70));
		win.drawString("BREAKOUT", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 240);
		win.setFont(new Font("Bahnschrift", Font.BOLD, 50));
		win.drawString("Q: Play ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 380);
		win.drawString("E: Instructions", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 460);
	}
}