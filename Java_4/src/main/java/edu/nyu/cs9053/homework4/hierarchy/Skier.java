package edu.nyu.cs9053.homework4.hierarchy;

public abstract class Skier extends WinterSportPlayer {

	private final int skiLength;

	protected Skier(String name, int age, int skiLength) {
		super(name, age);
		this.skiLength = skiLength;
	}

	public int getSkiLength() {
		return skiLength;
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
		Skier skier = (Skier) obj;
		return this.skiLength == skier.skiLength;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.skiLength;
		return result;
	} 	
}