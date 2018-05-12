package edu.nyu.cs9053.homework4.hierarchy;

public class Luger extends Sledder {

	private final int sledsWeigh;

	public Luger(String name, int age, String sledColor, int sledsWeigh) {
		super(name, age, sledColor);
		this.sledsWeigh = sledsWeigh;
	}

	public int getSledsWeigh() {
		return sledsWeigh;
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
		Luger luger = (Luger) obj;
		return this.sledsWeigh == luger.sledsWeigh;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.sledsWeigh;
		return result;
	} 
}