package main;

public class Scoreboard {
	private int l_score;
	private int r_score;
	private int win_num;
	
	public Scoreboard(int wn) {
		l_score = 0;
		r_score = 0;
		win_num = wn;
	}
	
	public void l_increase() {
		l_score++;
	}
	
	public void r_increase() {
		r_score++;
	}
	
	public boolean compare() {
		return l_score > r_score;
	}
	
	public String score_str() {
		return l_score + "   " + r_score;
	}
	
	public int get_l() {
		return l_score;
	}
	
	public int get_r() {
		return r_score;
	}
	
	public int play_to() {
		return win_num;
	}
	
	public boolean check_left_win() {
		return l_score == win_num;
	}
	
	public boolean check_right_win() {
		return r_score == win_num;
	}
	
	public boolean check_any_win() {
		return check_left_win() || check_right_win();
	}
	
	public void reset() {
		l_score = 0;
		r_score = 0;
	}
}