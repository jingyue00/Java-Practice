package edu.nyu.cs9053.homework4.hierarchy;

public abstract class WinterSportPlayer {

	private final String name;
	private final int age;

	protected WinterSportPlayer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || (this.getClass() != obj.getClass())) {
			return false;
		}
		WinterSportPlayer player = (WinterSportPlayer) obj;
		if (this.name == null ? player.name != null : !this.name.equals(player.name)) {
			return false;
		}
		return this.age == player.age;  		
	}

	@Override public int hashCode() {
		int result = this.name == null ? 0 : name.hashCode();
		result = 31 * result + this.age;
		return result;
	} 
}