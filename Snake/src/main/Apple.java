package main;

import java.awt.Rectangle;

public class Apple extends Rectangle {
	private int[] cur = new int[2];
	private int size;
	
	public Apple(int xx, int yy, int size)
	{
		super(xx * size, yy * size, size, size);
		cur[0] = xx; cur[1] = yy;
		this.size = size;
	}
	
	public void rand_pos(Snake s)
	{
		cur[0] = (int)(Math.random() * 1000/size);
		cur[1] = (int)(Math.random() * 700/size);
		while(true)
		{
			boolean change = false;
			for(SnakeNode n : s.getNodes())
			{
				if(n.curCoord()[0] == cur[0] && n.curCoord()[1] == cur[1])
				{
					change = true;
					cur[0] = (int)(Math.random() * 1000/size);
					cur[1] = (int)(Math.random() * 700/size);
				}
			}
			if(!change) break;
		}
		x = cur[0] * size;
		y = cur[1] * size;
	}
	
	public int[] curCoord()
	{
		return cur;
	}
}
