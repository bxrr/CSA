package main;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.KeyEvent;

public class Runner extends GDV5 {
	private Ball ball = new Ball(getMaxWindowX() / 2, getMaxWindowY() / 2, 15);
	private Paddle left = new Paddle(30, getMaxWindowY() / 2);
	private Paddle right = new Paddle(getMaxWindowX() - 30, getMaxWindowY() / 2);
	private Rectangle divider = new Rectangle(getMaxWindowX() / 2 - 1, 0, 2, getMaxWindowY());
	private Scoreboard s = new Scoreboard(5);
	
	private int left_d = 0;
	private int right_d = 0;
	private int i_count = 0;
	private double speed = 5;
	private boolean pause = false;
	private String score_str = "";
	private int gamemode = 0;
	private int incorrect_num_frames = 0;
	
	private boolean left_charged = false;
	private int left_charged_time = 0;
	private boolean right_charged = false;
	private int right_charged_time = 0;
	
	private boolean win = false;
	
	// game methods ======================================================
	public void left_movement() { 
		if(left_charged)
			left_charged_time++;
		if(left_charged_time > 20)
			left_charged = false;
		if(KeysPressed[KeyEvent.VK_W] && left.getMinY() > 0) {
			left.move(-6);
		}
		if(KeysPressed[KeyEvent.VK_S] && left.getMaxY() < getMaxWindowY()) {
			left.move(6);
		}
		if(KeysPressed[KeyEvent.VK_D] && left_charged_time < 20) {
			left_charged = true;
		}
	}
	
	public void right_movement() {
		if(right_charged)
			right_charged_time++;
		if(right_charged_time > 20)
			right_charged = false;
		if(KeysPressed[KeyEvent.VK_UP] && right.getMinY() > 0) {
			right.move(-6);
		}
		if(KeysPressed[KeyEvent.VK_DOWN] && right.getMaxY() < getMaxWindowY()) {
			right.move(6);
		}
		if(KeysPressed[KeyEvent.VK_LEFT] && right_charged_time < 20) {
			right_charged = true;
		}
	}
	
	public void auto_move(Paddle p, boolean left) {
		int correct_dir = (int) (Math.random() * 3000);
		if(correct_dir == 0) incorrect_num_frames += 10;
		if(incorrect_num_frames == 0) {
			if(p.getCenterY() < ball.getCenterY() && p.getMaxY() < getMaxWindowY()) {
				p.move(7);
			}
			else if(p.getCenterY() > ball.getCenterY() && p.getMinY() > 0) {
				p.move(-7);
			}
		}
		else {
			incorrect_num_frames--;
			if(p.getCenterY() < ball.getCenterY() && p.getMinY() > 0)
				p.move(-7);
			else if(right.getCenterY() > ball.getCenterY() && p.getMaxY() < getMaxWindowY())
				p.move(7);
			else
				return;
		}
	}
	
	public void game_over() {
		pause = true;
		win = true;

		if(s.check_left_win()) {
			score_str = s.get_l() + "LEFT WINS " + s.get_r();
		}
		else {
			score_str = s.get_l() + " RIGHT WINS " + s.get_r();
		}
	}
	
	public void reset_round(int score) {
		
		ball.reset(s.compare());
		left.reset();
		right.reset();
		score_str = s.score_str();
		i_count = 0;
		pause = true;
		speed = 5;
		left_charged_time = 0;
		left_charged = false;
		right_charged_time = 0;
		right_charged = false;
		
		if(s.check_any_win()) {
			game_over();
		}
	}
	
	public void ball_collision() {
		ball.move_rel(speed, speed);
		int check = ball.check_bounce(getMaxWindowX(), getMaxWindowY());
		if(check == -1) {
			s.r_increase();
			reset_round(s.get_l());
		}
		else if(check == 1) {
			s.l_increase();
			reset_round(s.get_r());
		}
		
		if(ball.paddle_bounce(left, true)) {
			if(left_charged)
				speed += Math.abs(speed) / speed;
			left_charged_time = 0;
		}
		if(ball.paddle_bounce(right, false)) {
			if(right_charged)
				speed += Math.abs(speed) / speed;
			right_charged_time = 0;
		}
	}
	
	public void two_player() {
		if(!pause) {
			ball_collision();				
			left_movement();
			right_movement();
			
			speed += speed < 7.0 ? 0.0005 : 0;
		}
		
		// pause game at the end of each round
		i_count++;
		if(win) {
			if(i_count > 300) {
				pause = false;
				gamemode = 0;
				score_str = "";
			}
		}
		else if(i_count > 120) {
			pause = false;
			score_str = "";
		}
	}
	
	public void single_player() {
		if(!pause) {
			ball_collision();
			left_movement();
			auto_move(right, false);
			
			speed += speed < 7.0 ? 0.0005 : 0;
		}
		
		// pause game at the end of each round
		i_count++;
		if(win) {
			if(i_count > 300) {
				pause = false;
				gamemode = 0;
				score_str = "";
			}
		}
		else if(i_count > 120) {
			pause = false;
			score_str = "";
		}
	}
	
	public void title_screen() {
		if(KeysPressed[KeyEvent.VK_A]) {
			gamemode = 3;
		}
		else if(KeysPressed[KeyEvent.VK_W]) {
			gamemode = 1;
		}
		else if(KeysPressed[KeyEvent.VK_D]) {
			gamemode = 2;
		}
	}
	
	public void instructions() {
		if(KeysPressed[KeyEvent.VK_S]) {
			gamemode = 0;
		}
	}
	
	// runtime methods ===================================================
	public static void main(String args[]) {
		Runner p = new Runner();
		p.start();
	}
	
	public void reset_items() {
		s.reset();
		ball.reset(true);
		left.reset();
		right.reset();
		score_str = "";
	}

	@Override
	public void update() {
		switch(gamemode) {
		case 0: // title
			title_screen();
			win = false;
			break;
		case 1: // instructions
			instructions();
			break;
		case 2:
			two_player();
			break;
		case 3:
			single_player();
			break;
		}
		if(KeysPressed[KeyEvent.VK_BACK_SPACE]) {
			reset_items();
			gamemode = 0;
		}
	}

	@Override
	public void draw(Graphics2D win) {
		if(gamemode == 0) {
			win.setColor(Color.white);
			win.setFont(new Font("Bahnschrift", Font.BOLD, 100));
			win.drawString("PONG", getMaxWindowX() / 2 - 133, getMaxWindowY() / 3);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
			win.drawString("W - HOW TO PLAY", getMaxWindowX() / 2 - 155, getMaxWindowY() * 1 / 2);
			win.drawString("A - ONE PLAYER", getMaxWindowX() / 2 - 155, getMaxWindowY() * 3 / 5);
			win.drawString("D - TWO PLAYER", getMaxWindowX() / 2 - 155, getMaxWindowY() * 7 / 10);
		}
		
		if(gamemode == 1) {
			win.setColor(Color.white);
			win.setFont(new Font("Bahnschrift", Font.BOLD, 100));
			win.drawString("PONG", getMaxWindowX() / 2 - 133, getMaxWindowY() / 3);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
			win.drawString("Press S to return home.", 20, 50);
			win.drawString("LEFT PADDLE: W-up; S-down; D-frame boost", 20, getMaxWindowY() * 1 / 2);
			win.drawString("RIGHT PADDLE: UP-up; DOWN-down; LEFT-frame boost", 20, getMaxWindowY() * 2 / 3);
			win.drawString("OBJECTIVE: Score 5 points on your opponent by hitting ", 20, getMaxWindowY() * 5 / 6);
			win.drawString("the ball past your opponent's paddle.", 20, getMaxWindowY() * 7 / 8);
		}
		
		if(gamemode > 1) {
			win.setColor(Color.white);
			win.draw(ball);
			win.draw(left);
			win.draw(right);
			win.fill(ball);
			
			if(left_charged) win.setColor(Color.yellow);
			win.fill(left);
			if(right_charged) win.setColor(Color.yellow);
			else win.setColor(Color.white);
			win.fill(right);
			win.setColor(Color.lightGray);
			win.draw(divider);
			
			win.setFont(new Font("Bahnschrift", Font.BOLD, 35));
			win.drawString(score_str, getMaxWindowX() / 2 - 33 , getMaxWindowY() / 16);
		}
	}
}
