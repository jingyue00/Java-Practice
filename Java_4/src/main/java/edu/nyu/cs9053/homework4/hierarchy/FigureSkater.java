package edu.nyu.cs9053.homework4.hierarchy;

public class FigureSkater extends IceSkater {

	private final String disciplines;

	public FigureSkater(String name, int age, int skateSize, String disciplines) {
		super(name, age, skateSize);
		this.disciplines = disciplines;
	}

	public String getDisciplines() {
		return disciplines;
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
		FigureSkater figureSkater = (FigureSkater) obj;
		return disciplines != null ? disciplines.equals(figureSkater.disciplines) : figureSkater.disciplines == null;
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (disciplines == null ? 0 : disciplines.hashCode());
		return result;
	} 	
}