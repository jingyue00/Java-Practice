package edu.nyu.cs9053.homework4.hierarchy;

public class SkeletonPlayer extends Sledder {

	private final double maxReachSpeed;

	public SkeletonPlayer(String name, int age, String sledColor, double maxReachSpeed) {
		super(name, age, sledColor);
		this.maxReachSpeed = maxReachSpeed;
	}

	public double getMaxReachSpeedr() {
		return maxReachSpeed;
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
		SkeletonPlayer skeletonPlayer = (SkeletonPlayer) obj;
		return this.maxReachSpeed == skeletonPlayer.maxReachSpeed;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Double.valueOf(this.maxReachSpeed).hashCode();
		return result;
	} 
}