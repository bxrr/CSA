package main;

public class Submarine extends ScoutBoat implements Attacker {
	private int numOfTorpedos;
	
	public Submarine(int id, Coordinates c, int dir)
	{
		super(id, c, dir, 3, 2);
	}
	
	public String getID()
	{
		return "S" + super.getTeam();
	}
	
	public String getActions()
	{
		return "Choose any of the following actions for the Submarine:\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n"
				+ " 4. Submerge\n"
				+ " 5. Fire torpedoes\n";
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
				break;
			case 2:
				temp += super.turn(-1) + "\n";
				break;
			case 3:
				temp += super.turn(1) + "\n";
				break;
			}
		}
		return temp;
	}
	
	public String attack(World w)
	{
		Coordinates temp = super.getLocation();
		for(int i = 0; i < super.getVision(); i++)
		{
			temp = w.getAdjacentLocation(temp, super.getDirection());
			if(w.isLocationOccupied(temp))
			{
				
			}
		}
	}
}
