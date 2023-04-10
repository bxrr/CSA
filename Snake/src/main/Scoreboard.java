package main;

public class Scoreboard {
	private int score = 0;

	public Scoreboard(int score)
	{
		this.score = score;
	}
	
	public void scored(int n)
	{
		score += n;
	}
	
	public String toString()
	{
		return "Score: " + score;
	}
	
	public void reset()
	{
		score = 0;
	}
}
