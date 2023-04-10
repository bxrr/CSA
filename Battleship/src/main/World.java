package main;

public class World {
	private Boat[][] map;
	public static final int NORTH = 0, NORTHEAST = 1, EAST = 2, SOUTHEAST = 3, SOUTH = 4, SOUTHWEST = 5, WEST = 6, NORTHWEST = 7;
	
	public World(int w, int h)
	{
		w = w < 4 ? 4 : (w > 10 ? 10 : w);
		h = h < 4 ? 4 : (h > 10 ? 10 : h);
		
		map = new Boat[h][w];
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				map[i][j] = null;
			}
		}
	}
	
	public int getWidth()
	{
		return map[0].length;
	}
	
	public int getHeight()
	{
		return map.length;
	}
	
	public boolean isLocationValid(Coordinates c)
	{
		if(c.getX() >= 0 && c.getX() <= getWidth() && c.getY() >= 0 && c.getY() <= getHeight())
		{
			return true;
		}
		return false;
	}
	
	public boolean isLocationOccupied(Coordinates c)
	{
		return map[c.getY()][c.getX()] != null;
	}
	
	public boolean setOccupant(Boat b, Coordinates c)
	{
		if(isLocationValid(c) && !isLocationOccupied(c))
		{
			map[c.getY()][c.getX()] = b;
			return true;
		}
		return false;
	}
	
	public Coordinates getAdjacentLocation(Coordinates c, int dir)
	{
		Coordinates temp;
		switch(dir)
		{
		case NORTH:
			temp = new Coordinates(c.getX(), c.getY() - 1);
			break;
		case NORTHEAST:
			temp = new Coordinates(c.getX() + 1, c.getY() - 1);
			break;
		case EAST:
			temp = new Coordinates(c.getX() + 1, c.getY());
			break;
		case SOUTHEAST:
			temp = new Coordinates(c.getX() + 1, c.getY() + 1);
			break;
		case SOUTH:
			temp = new Coordinates(c.getX(), c.getY() + 1);
			break;
		case SOUTHWEST:
			temp = new Coordinates(c.getX() - 1, c.getY() + 1);
			break;
		case NORTHWEST:
			temp = new Coordinates(c.getX() - 1, c.getY() - 1);
			break;
		case WEST:
			temp = new Coordinates(c.getX() - 1, c.getY());
			break;
		default:
			temp = null;
		}
		
		if(isLocationValid(temp)) return temp;
		return null;
	}
	
	public String drawTeamMap(Boat[] boats, int view)
	{
		String[][] board = new String[this.getHeight()][this.getWidth()];
		String board_str = "";
		board_str += " @ ";
		for(int i = 0; i < map[0].length; i++)
		{
			board_str += " " + (i+1) + " ";
		}
		board_str += "\n";
		
		if(view == 1)
		{
			for(int i = 0; i < map.length; i++)
			{
				board_str += " " + (char)(i + 'A') + " ";
				for(int j = 0; j < map[i].length; j++)
				{
					board_str += "###";
				}
				board_str += "\n";
			}
			
			return board_str;
		}
		else if (view == 2) {
			for(Boat boat : boats) 
			{
				Coordinates c = boat.getLocation();
				board[c.getY()][c.getX()] = boat.getDirection() + boat.toString();
				
				for(int i = Math.max(0, c.getY()-boat.getVision()); i <= Math.min(this.getHeight()-1, c.getY() + boat.getVision()); i++) 
				{
					for(int j =  Math.max(0,  c.getX()-boat.getVision()); j <= Math.min(this.getWidth()-1, c.getX() + boat.getVision()); j++) 
					{
						if(isLocationOccupied(new Coordinates(j, i)))
						{
							if(map[i][j].getTeam() != boat.getTeam()) 
								board[i][j] = map[i][j].getDirection() + map[i][j].toString();
						}
						else if(board[i][j] == null)
						{
							board[i][j] = "~~~";
						}
					}
				}
			}
		}
		else if(view == 3){
			for(Boat boat : boats) 
			{
				Coordinates c = boat.getLocation();
				board[c.getY()][c.getX()] = boat.getHealth() + boat.toString();
				
				for(int i = Math.max(0, c.getY()-boat.getVision()); i <= Math.min(this.getHeight()-1, c.getY() + boat.getVision()); i++) 
				{
					for(int j =  Math.max(0,  c.getX()-boat.getVision()); j <= Math.min(this.getWidth()-1, c.getX() + boat.getVision()); j++) 
					{
						if(isLocationOccupied(new Coordinates(j, i)))
						{
							if(map[i][j].getTeam() != boat.getTeam()) 
								board[i][j] = map[i][j].getHealth() + map[i][j].toString();
						}
						else if(board[i][j] == null)
						{
							board[i][j] = "~~~";
						}
					}
				}
			}
		}
		
		for(int i = 0; i < map.length; i++)
		{
			board_str += " " + (char)(i + 'A') + " ";
			for(int j = 0; j < map[i].length; j++)
			{
				if(board[i][j] != null)
				{
					board_str += board[i][j];
				}
				else
				{
					board_str += "###";
				}
			}
			board_str += "\n";
		}
		
		return board_str;
	}
	
	public static void main(String[] args)
	{
		World w = new World(9, 10);
		System.out.println(w.drawTeamMap(null, 2));
	}
}
