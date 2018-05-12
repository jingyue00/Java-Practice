package edu.nyu.cs9053.homework4.hierarchy;

public class MogulSkier extends Skier {

	private final double avgRoundSpeed;

	public MogulSkier(String name, int age, int skiLength, int avgRoundSpeed) {
		super(name, age, skiLength);
		this.avgRoundSpeed = avgRoundSpeed;
	}

	public double getAvgRoundSpeed() {
		return avgRoundSpeed;
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
		MogulSkier mogulSkier = (MogulSkier) obj;
		return this.avgRoundSpeed == mogulSkier.avgRoundSpeed;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Double.valueOf(this.avgRoundSpeed).hashCode();
		return result;
	} 
}