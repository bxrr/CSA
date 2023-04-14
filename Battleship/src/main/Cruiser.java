package main;

public class Cruiser extends ScoutBoat {
	public Cruiser(int id, Coordinates c, int dir)
	{
		super(id, c, dir, 3, 3);
	}
	
	public String getID()
	{
		return "C" + super.getTeam();
	}
	
	public String getActions()
	{
		return "Choose any of the following actions for the Cruiser:\n"
				+ " 1. Move\n 2. Turn left\n 3. Turn right\n"; 
	}
	
	public String act(int[] move, World w)
	{
		String temp = "";
		for(int i = 0; i < move.length; i++)
		{
			switch(move[i])
			{
			case 1:
				temp += super.move(w) + "\n";
			case 2:
				temp += super.turn(-1) + "\n";
			case 3:
				temp += super.turn(1) + "\n";
			}
		}
		return temp;
	}
}
