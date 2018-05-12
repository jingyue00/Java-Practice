package edu.nyu.cs9053.homework4.hierarchy;

public class CrossCountrySkier extends Skier {

	private final String equipment;

	public CrossCountrySkier(String name, int age, int skiLength, String equipment) {
		super(name, age, skiLength);
		this.equipment = equipment;
	}

	public String getEquipment() {
		return equipment;
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
		CrossCountrySkier crossCountrySkier = (CrossCountrySkier) obj;
		return this.equipment == crossCountrySkier.equipment;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (equipment == null ? 0 : equipment.hashCode());
		return result;
	} 
}