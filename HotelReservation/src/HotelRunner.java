

public class HotelRunner {
	//Hotel h1 = new Hotel("Flamingo", 72, 9);
	public static void main(String[] args) {
		System.out.println("First Case: ");
		Hotel h = new Hotel("Mirage", 10, 1);
		h.printRooms();
		System.out.println("**************");
		System.out.println("Second Case: ");
		Hotel h2 = new Hotel("Venetian", 30, 5);
		h2.printRooms();
		System.out.println("**************");
		System.out.println("Third Case: ");
		Hotel h3 = new Hotel("Bellagio", 77, 7);
		h3.printRooms();
		System.out.println("**************");
		System.out.println("Big Test: ");
		Hotel h1 = new Hotel("Flamingo", 8, 1);
		System.out.println("Day0");
		h1.printRooms();
		h1.rentRoom("suite", "Molly Putz", 5);
		h1.rentRoom("single king", "Steve Kramer", 2);
		h1.rentRoom("single king", "Keeanu Reeves", 3);
		h1.rentRoom("double queen", "Taylor Swift", 1);
		System.out.println("Day1");
		System.out.println(h1);
		h1.printRooms();
		//h1.getOccupancyRate();
		h1.advanceDay();
		System.out.println("Day2");
		//h1.getOccupancyRate();
		h1.printRooms();
		h1.advanceDay();
		System.out.println(h1);
		
		System.out.println("Day3");
		//h1.getOccupancyRate();
		h1.printRooms();
		System.out.println(h1);
		h1.advanceDay();
		
		System.out.println("Day4");
		h1.printRooms();
		System.out.println(h1);
		h1.advanceDay();
		
		System.out.println("Day5");
		h1.printRooms();
		System.out.println(h1);
		h1.advanceDay();
		System.out.println("Day6");
		h1.rentRoom("single king", "Peter Muller", 3);
		h1.rentRoom("double queen", "Justin Bieber", 1);
		h1.printRooms();
		System.out.println(h1);
		h1.advanceDay();
		System.out.println("Day7");
		System.out.println(h1);
		h1.printRooms();
		h1.advanceDay();
		
		

	}
	


}


