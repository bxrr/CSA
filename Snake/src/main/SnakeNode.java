package main;

import java.awt.Rectangle;

public class SnakeNode extends Rectangle {
	private int[] cur = new int[2];
	private int[] last = new int[2];
	int size;
	
	public SnakeNode(int xx, int yy, int size)
	{
		super(xx * size, yy * size, size, size);
		cur[0] = xx; cur[1] = yy;
		last[0] = xx; last[1] = yy;
		this.size = size;
	}
	
	public int[] prevCoord()
	{
		return last;
	}
	
	public int[] curCoord()
	{
		return cur;
	}
	
	public void trail(SnakeNode s)
	{
		last = cur.clone();
		cur = s.prevCoord().clone();
		x = cur[0] * size;
		y = cur[1] * size;
	}
	
	public void move(String dir)
	{
		last = cur.clone();
		if(dir.equals("up"))
		{
			cur[1]--;
		}
		else if(dir.equals("down"))
		{
			cur[1]++;
		}
		else if(dir.equals("left"))
		{
			cur[0]--;
		}
		else if(dir.equals("right"))
		{
			cur[0]++;
		}
		
		x = cur[0] * size;
		y = cur[1] * size;
	}
	
	public void death()
	{
		if(size > 0)
		{
			size -= 2;
			x += 1;
			y += 1;
			super.setSize(size, size);
		}
		else
		{
			x = -100;
		}
	}
	
	public boolean collision(SnakeNode s)
	{
		if(cur[0] == s.curCoord()[0] && cur[1] == s.curCoord()[1])
		{
			return true;
		}
		return false;
	}
} 
