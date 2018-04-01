package model;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class TechMaster {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(TechMaster.class.getName());

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
