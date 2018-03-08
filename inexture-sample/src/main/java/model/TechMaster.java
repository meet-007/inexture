package model;

public class TechMaster {
	private int idtech;
	private String tech;
	public int getIdtech() {
		return idtech;
	}
	public void setIdtech(int idtech) {
		this.idtech = idtech;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	@Override
	public String toString() {
		return "TechMaster [idtech=" + idtech + ", tech=" + tech + "]";
	};

}
