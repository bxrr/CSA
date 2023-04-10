package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Snake {
	private ArrayList<SnakeNode> snake;
	private String dir;
	private int size;
	private int start;
	
	public Snake(int nodes, int size)
	{
		start = nodes;
		snake = new ArrayList<SnakeNode>();
		for(int i = 0; i < start; i++)
		{
			snake.add(new SnakeNode(15+i, 10, size));
		}
		dir = "left";
		this.size = size;
	}
	
	public ArrayList<SnakeNode> getNodes()
	{
		return snake;
	}
	
	public void move(String dir)
	{
		snake.get(0).move(dir);
		for(int i = 1; i < snake.size(); i++) 
		{
			snake.get(i).trail(snake.get(i-1));
		}
		System.out.println();
	}
	
	public void addNode()
	{
		int[] temp = snake.get(snake.size()-1).prevCoord();
		snake.add(new SnakeNode(temp[0], temp[1], size));
		System.out.println("node it up!!!");
	}
	
	public boolean collide()
	{
		if(snake.get(0).curCoord()[0] >= 1000/size || snake.get(0).curCoord()[0] < 0) return true;
		if(snake.get(0).curCoord()[1] >= 700/size || snake.get(0).curCoord()[1] < 0) return true;
		for(int i = 1; i < snake.size(); i++)
		{
			if(snake.get(0).collision(snake.get(i)))
			{
				System.out.println("collided");
				return true;
			}
		}
		return false;
	}
	
	public int check_apples(ArrayList<Apple> ap)
	{
		for(int i = 0; i < ap.size(); i++)
		{
			Apple a = ap.get(i);
			if(snake.get(0).curCoord()[0] == a.curCoord()[0] && snake.get(0).curCoord()[1] == a.curCoord()[1])
			{
				addNode();
				return i;
			}
		}
		return -1;
	}
	
	public int size()
	{
		return snake.size();
	}
	
	public void reset()
	{
		snake = new ArrayList<SnakeNode>();
		for(int i = 0; i < start; i++)
		{
			snake.add(new SnakeNode(15+i, 10, size));
		}
	}
}
