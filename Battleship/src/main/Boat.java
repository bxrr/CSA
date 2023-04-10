package main;

public abstract class Boat {
	private int team;
	private Coordinates location;
	private int direction;
	private int health;
	private int strength;
	private int vision; 
	
	public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) 
	{
		this.team = team;
		this.location = location;
		this.direction = direction; 
		this.health = health;
		this.strength = strength;
		this.vision = vision;
	}
	
	public int getTeam() 
	{
		return team;
	}
	
	public Coordinates getLocation() 
	{
		return location;
	}
	
	public char getDirection() 
	{
		switch(this.direction) {
			case(0):
				return '\u2191';
			case(1):
				return '\u2197';
			case(2):
				return '\u2192';
			case(3):
				return '\u2198';
			case(4):
				return '\u2193';
			case(5):
				return '\u2199';
			case(6):
				return '\u2190';
			case(7):
				return '\u2196';
			default:
				return 'X';
		}
	}
	
	public int getHealth() 
	{
		return health;
	}
	
	public int getStrength() 
	{
		return strength;
	}
	
	public int getVision() 
	{
		return vision;
	}
	
	abstract String getID();
	
	abstract String act(int[] choices, World w);
	
	abstract String getActions();
	
	public String move(World world) 
	{
		Coordinates moveTo = world.getAdjacentLocation(this.location, this.direction);
		if(moveTo != null)
		{
			if(world.isLocationOccupied(moveTo))
			{
				return toString() + " cannot move to " + moveTo.toString() + " as it is occupied.";
			}
			return toString() + " moves from " + getLocation().toString() + " to " + moveTo.toString();
		}
		else
		{
			return toString() + " cannot move off the map.";
		}
	}
	
	public String turn(int input) 
	{
		String temp = "";
		temp += this.getID();
		if(input == -1) 
		{
			temp += " turned left";
		}
		else temp += " turned right";
		
		this.direction += input;
		if(direction < 0) this.direction = 7;
		if(direction > 7) this.direction = 0;
		temp += ", now facing " + this.getDirection();
		return temp; 
	}
	
	public String takeHit(int atk_str) 
	{
		health -= atk_str;
		if(health <= 0) 
		{
			health = 0;
			return toString() + " has been sunk!";
		}
		return toString() + " takes " + atk_str + " damage.";
	}
	
	public void setLocation(Coordinates c)
	{
		location = c;
	}
	
	public String toString() 
	{
		return getID();
	}
}