package edu.nyu.cs9053.homework4.hierarchy;

public abstract class IceSkater extends WinterSportPlayer {

	private final int skateSize;

	protected IceSkater(String name, int age, int skateSize) {
		super(name, age);
		this.skateSize = skateSize;
	}

	public int getSkateSize() {
		return skateSize;
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
		IceSkater iceSkater = (IceSkater) obj;
		return this.skateSize == iceSkater.skateSize;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.skateSize;
		return result;
	} 	
}