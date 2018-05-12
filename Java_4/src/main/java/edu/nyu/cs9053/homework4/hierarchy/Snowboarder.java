package edu.nyu.cs9053.homework4.hierarchy;

public class Snowboarder extends WinterSportPlayer {

	private final int boarderLength;

	public Snowboarder(String name, int age, int boarderLength) {
		super(name, age);
		this.boarderLength = boarderLength;
	}

	public int getBoarderLength() {
		return boarderLength;
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
		Snowboarder snowboarder = (Snowboarder) obj;
		return this.boarderLength == snowboarder.boarderLength;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.boarderLength;
		return result;
	} 	
}