package eu.benjam.bol;

public class Match {
	int x;
	int y;
	double score;
	String digit;
	
	public Match(int x,int y, double score, String digit){
		this.x = x;
		this.y = y;
		this.score = score;
		this.digit = digit;		
	}
	
	public String getDigit() {
		return digit;
	}

	public void setDigit(String digit) {
		this.digit = digit;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
