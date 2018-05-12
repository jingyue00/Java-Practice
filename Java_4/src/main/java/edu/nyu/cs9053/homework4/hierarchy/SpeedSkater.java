package edu.nyu.cs9053.homework4.hierarchy;

public class SpeedSkater extends IceSkater {

	private final int maxSpeed;

	public SpeedSkater(String name, int age, int skateSize, int maxSpeed) {
		super(name, age, skateSize);
		this.maxSpeed = maxSpeed;
	}

	public int getMaxSpeed() {
		return maxSpeed;
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
		SpeedSkater speedSkater = (SpeedSkater) obj;
		return this.maxSpeed == speedSkater.maxSpeed;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.maxSpeed;
		return result;
	} 	
}