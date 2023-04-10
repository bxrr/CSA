package main;

public abstract class ScoutBoat extends Boat {
	public ScoutBoat(int team, Coordinates location, int direction, int health, int vision)
	{
		super(team, location, direction, health, 1, vision);
	}
	
	public String takeHit(int attacks)
	{
		int damage = 0;
		for(int i = 0; i < attacks; i++)
		{
			if(Math.random() < 0.25)
			{
				damage++;
			}
		}
		
		return damage == 0 ? this.toString() + " has avoided the attack!" : this.takeHit(damage);
	}
}
