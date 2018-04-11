package model;

// TODO: Auto-generated Javadoc
/**
 * The Class TechMaster.
 */
public class TechMaster {

	/** Logger for this class. */

	/** The idtech. */
	private int idtech;

	/** The tech. */
	private String tech;

	/**
	 * Gets the idtech.
	 *
	 * @return the idtech
	 */
	public int getIdtech() {
		return idtech;
	}

	/**
	 * Sets the idtech.
	 *
	 * @param idtech the new idtech
	 */
	public void setIdtech(final int idtech) {
		this.idtech = idtech;
	}

	/**
	 * Gets the tech.
	 *
	 * @return the tech
	 */
	public String getTech() {
		return tech;
	}

	/**
	 * Sets the tech.
	 *
	 * @param tech the new tech
	 */
	public void setTech(final String tech) {
		this.tech = tech;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TechMaster [idtech=" + idtech + ", tech=" + tech + "]";
	};

}
