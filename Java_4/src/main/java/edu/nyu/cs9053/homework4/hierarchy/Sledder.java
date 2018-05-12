package edu.nyu.cs9053.homework4.hierarchy;

public abstract class Sledder extends WinterSportPlayer {

	private final String sledColor;

	protected Sledder(String name, int age, String sledColor) {
		super(name, age);
		this.sledColor = sledColor;
	}

	public String getSledColor() {
		return sledColor;
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
		Sledder sledder = (Sledder) obj;
		return (sledColor == null ? sledder.sledColor == null : sledColor.equals(sledder.sledColor));
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (sledColor == null ? 0 : sledColor.hashCode());
		return result;
	} 
}