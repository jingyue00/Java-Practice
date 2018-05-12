package edu.nyu.cs9053.homework4.hierarchy;

public class Curler extends WinterSportPlayer {

	private final int highestScore;

	public Curler(String name, int age, int highestScore) {
		super(name, age);
		this.highestScore = highestScore;
	}

	public int getHighestScore() {
		return highestScore;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Curler curler = (Curler) obj;
		return this.highestScore == curler.highestScore;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.highestScore;
		return result;
	} 
}