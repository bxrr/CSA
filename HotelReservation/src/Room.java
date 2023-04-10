
public class Room {
	private int roomNumber;
	private int daysRented;
	private String roomType;
	private String occupantName;
	
	public Room(int rNum, String rType) 
	{
		roomNumber = rNum;
		if(rType.equals("single king") || rType.equals("double queen") || rType.equals("suite"))
			roomType = rType;
		else
			roomType = "double queen";
		
		daysRented = 0;
		occupantName = null;
	}
	
	public int getRoomNumber()
	{
		return roomNumber;
	}
	
	public int getDaysRented()
	{
		return daysRented;
	}
	
	public String getRoomType()
	{
		return roomType;
	}
	
	public String getOccupantName()
	{
		return occupantName;
	}
	
	public Boolean setOccupant(String guestName, int numDays)
	{
		if(daysRented > 0)
		{
			return false;
		}
		
		occupantName = guestName;
		daysRented = numDays;
		
		return true;
	}
	
	public void advanceDay()
	{
		if(daysRented > 0)
			daysRented--;
		else
		{
			occupantName = null;
			daysRented = 0;
		}
	}
	
	public String toString()
	{	
		return "Room " + roomNumber + ": " + roomType + " - " +(occupantName != null ? "rented to " + occupantName : "free");
	}
}
