import java.text.DecimalFormat;

public class Hotel {
	private String hotelName;
	private Room[][] rooms;
	private int totalRooms;
	
	public Hotel(String name, int numRooms, int numFloors)
	{
		hotelName = name;
		totalRooms = numRooms * numFloors;
		rooms = new Room[numFloors][numRooms];
		
		for(int i = 0; i < rooms.length; i++)
		{
			for(int j = 0; j < rooms[i].length; j++)
			{
				String tempRType = rooms[i].length-1 - j <= 4 ? "single king" : "double queen";
				tempRType = rooms[i].length - 1 == j ? "suite" : tempRType;
				rooms[i][j] = new Room(i * 100 + j+1, tempRType);
			}
		}
	}
	
	public int getNumberOccupied()
	{
		int nOcc = 0;
		for(Room[] floor : rooms)
		{
			for(Room room : floor)
			{
				if(room.getOccupantName() != null)
					nOcc++;
			}
		}
		
		return nOcc;
	}
	
	public double getOccupancyRate()
	{
		final DecimalFormat td = new DecimalFormat("00.00");
		return Double.parseDouble(td.format((double)getNumberOccupied() / totalRooms));
	}
	
	public Boolean rentRoom(String rType, String guestName, int nDays)
	{
		for(int j = 0; j < rooms.length; j++)
		{
			for(int i = 0; i < rooms[j].length; i++)
			{
				if(rooms[j][i].getOccupantName() == null && rooms[j][i].getRoomType() == rType)
				{
					rooms[j][i].setOccupant(guestName, nDays);
					return true;
				}
			}
		}
		return false;
	}
	
	public void advanceDay()
	{
		for(int j = 0; j < rooms.length; j++)
		{
			for(int i = 0; i < rooms[j].length; i++)
			{
				rooms[j][i].advanceDay();
			}
		}
	}
	
	public String toString()
	{
		return hotelName + ": " + getOccupancyRate()*100 + "% occupied";
	}
	
	public void printRooms()
	{
		for(Room[] floor : rooms)
		{
			for(Room room : floor)
			{
				System.out.println(room);
			}
		}
	}
}
