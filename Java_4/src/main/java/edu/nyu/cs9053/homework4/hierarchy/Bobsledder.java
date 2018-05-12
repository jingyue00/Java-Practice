package edu.nyu.cs9053.homework4.hierarchy;

public class Bobsledder extends Sledder {

	private final int teamNums;

	public Bobsledder(String name, int age, String sledColor, int teamNums) {
		super(name, age, sledColor);
		this.teamNums = teamNums;
	}

	public int getTeamNums() {
		return teamNums;
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
		Bobsledder bobsledder = (Bobsledder) obj;
		return this.teamNums == bobsledder.teamNums;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.teamNums;
		return result;
	} 
}