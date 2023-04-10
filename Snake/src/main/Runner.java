package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Runner extends GDV5 {
	private int nodes = 6;
	private Snake snake = new Snake(nodes, 20);
	private ArrayList<Apple> apples = new ArrayList<Apple>();
	private ArrayList<Apple> gapples = new ArrayList<Apple>();
	private String snake_dir = "left";
	private String moved_dir = snake_dir;
	Image im = new Image();
	BufferedImage bc = im.loader("src/image/btc(1).png");
	Sound bg_sound = new Sound("src/sounds/bg.wav");
	
	private int difficulty = -1;
	private int gamemode = 0;
	private long frame = 0;
	private long cur_frame = 0;
	private boolean died = false;
	private Scoreboard sb = new Scoreboard(0);
	
	private void snakeControl()
	{
		if(KeysPressed[KeyEvent.VK_W] && !moved_dir.equals("down"))
		{
			snake_dir = "up";
		}
		else if(KeysPressed[KeyEvent.VK_S] && !moved_dir.equals("up"))
		{
			snake_dir = "down";
		}
		else if(KeysPressed[KeyEvent.VK_A] && !moved_dir.equals("right"))
		{
			snake_dir = "left";
		}
		else if(KeysPressed[KeyEvent.VK_D] && !moved_dir.equals("left"))
		{
			snake_dir = "right";
		}
	}
	
	public void title_screen() {
		if(KeysPressed[KeyEvent.VK_W]) {
			gamemode = 3;
		}
		else if(KeysPressed[KeyEvent.VK_S]) {
			gamemode = 2;
		}
	}
	
	public void diff_select()
	{
		if(KeysPressed[KeyEvent.VK_A]) {
			difficulty = 0;
			gamemode = 1;
		}
		else if(KeysPressed[KeyEvent.VK_S]) {
			difficulty = 1;
			gamemode = 1;
		}
		else if(KeysPressed[KeyEvent.VK_D]) {
			difficulty = 2;
			gamemode = 1;
		}
	}
	
	public void instructions() {
		if(KeysPressed[KeyEvent.VK_A]) {
			gamemode = 0;
		}
	}
	
	public void death_screen() {
		if(KeysPressed[KeyEvent.VK_A]) {
			gamemode = 0;
		}
	}
	
	public void update() 
	{
		switch(gamemode)
		{
		case 0:
			bg_sound.se.stop();
			sb.reset();
			snake.reset();
			apples = new ArrayList<Apple>();
			gapples = new ArrayList<Apple>();
			snake = new Snake(nodes, 20);
			frame = 0;
			cur_frame = 0;
			difficulty = -1;
			snake_dir = "left";
			moved_dir = snake_dir;
			died = false;
			title_screen();
			break;
		case 1:
			bg_sound.se.play();
			frame++;
			if(snake.collide())
			{
				if(!died)
				{
					died = true;
					cur_frame = frame;
				}
				
				for(int i = 0; i < ((int)((frame - cur_frame) / 1) < snake.size() ? (int)((frame - cur_frame) / 1) : snake.size()); i++)
				{
					snake.getNodes().get(i).death();
				}
				
				if(frame - cur_frame > 180)
				{
					gamemode = 4;
				}
			}
			else
			{
				snakeControl();
				if((int)(snake.getNodes().size() / 5) > apples.size() && apples.size() < 20)
				{
					Apple temp = new Apple(0, 0, 20);
					temp.rand_pos(snake);
					apples.add(temp);
				}
				
				if(apples.size() >= 5 && (int)(snake.getNodes().size() / 20) > gapples.size() && gapples.size() < 15)
				{
					Apple temp = new Apple(0, 0, 20);
					temp.rand_pos(snake);
					gapples.add(temp);
				}
				
				int eaten = snake.check_apples(apples);
				if(eaten != -1)
				{
					Sound eat = new Sound("src/sounds/yummy_crypto.wav");
					eat.se.play();
					sb.scored(1);
					apples.get(eaten).rand_pos(snake);
				}
				eaten = snake.check_apples(gapples);
				if(eaten != -1)
				{
					Sound eat = new Sound("src/sounds/yummy_crypto.wav");
					eat.se.play();
					for(int i = 0; i < 2; i++) snake.addNode();
					sb.scored(3);
					gapples.get(eaten).rand_pos(snake);
				}
				
				int update_delay = difficulty == 0 ? 25 : difficulty == 1 ? 10 : 4;
				if(frame % update_delay == 0)
				{
					snake.move(snake_dir);
					moved_dir = snake_dir;
				}
			}
			break;
		case 2:
			instructions();
			break;
		case 3:
			diff_select();
			break;
		case 4:
			death_screen();
			break;
		}
	}

	
	public void draw(Graphics2D win) 
	{
		switch(gamemode)
		{
		case 0:
			win.setColor(Color.white);
			win.setFont(new Font("Bahnschrift", Font.BOLD, 100));
			win.drawString("SNAKE", getMaxWindowX() / 2 - 200, getMaxWindowY() / 3);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
			win.drawString("W - PLAY", getMaxWindowX() / 2 - 155, getMaxWindowY() * 1 / 2);
			win.drawString("S - CONTROLS", getMaxWindowX() / 2 - 155, getMaxWindowY() * 3 / 5);
			break;
		case 1:
			win.setColor(Color.white);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
			win.drawString(sb.toString(), getMaxWindowX() / 2 - 40, 25);
			boolean head = true;
			for(SnakeNode node : snake.getNodes())
			{
				if(head)
				{
					win.setColor(Color.orange);
					head = false;
				}
				else
				{
					win.setColor(Color.yellow);
				}
				win.fill(node);
				win.draw(node);
			}
			
			for(Apple a : apples)
			{
				win.setColor(Color.red);
				win.fill(a);
				win.draw(a);
			}
			for(Apple ga : gapples)
			{
//				win.setColor(Color.green);
//				win.fill(ga);
				win.drawImage(bc, null, (int)ga.getMinX(), (int)ga.getMinY());
			}
			break;
		case 2:
			win.setColor(Color.white);
			win.setFont(new Font("Bahnschrift", Font.BOLD, 100));
			win.drawString("SNAKE", getMaxWindowX() / 2 - 200, getMaxWindowY() / 3);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
			win.drawString("Press A to return home.", 20, 50);
			win.drawString("OBJECTIVE: Collect the apples/bitcoin and", getMaxWindowX() / 2 - 400, getMaxWindowY() * 5 / 10);
			win.drawString("avoid hitting your tail/walls", getMaxWindowX() / 2 - 400, getMaxWindowY() * 6 / 10);
			win.drawString("WASD to move the snake", getMaxWindowX() / 2 - 400, getMaxWindowY() * 8 / 10);
			break;
		case 3:
			win.setColor(Color.white);
			win.setFont(new Font("Bahnschrift", Font.BOLD, 100));
			win.drawString("SNAKE", getMaxWindowX() / 2 - 200, getMaxWindowY() / 3);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
			win.drawString("A: easy, S: normal, D: hard", getMaxWindowX() / 2 - 250, getMaxWindowY() * 1 / 2);
			break;
		case 4:
			win.setColor(Color.white);
			win.setFont(new Font("Bahnschrift", Font.BOLD, 100));
			win.drawString("SNAKE", getMaxWindowX() / 2 - 200, getMaxWindowY() / 3);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 70));
			win.drawString("YOU DIED! " + sb, getMaxWindowX() / 2 - 300, getMaxWindowY() * 1 / 2);
			win.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
			win.drawString("Press A to return home.", 20, 50);
		}
	}	
	
	public static void main(String args[]) {
		Runner p = new Runner();
		p.start();
	}
}
