package model;

// TODO: Auto-generated Javadoc
/**
 * The Class LangMaster.
 */
public class LangMaster {

	/** Logger for this class. */

	/** The idlang. */
	private int idlang;

	/** The lang. */
	private String lang;

	/**
	 * Gets the idlang.
	 *
	 * @return the idlang
	 */
	public int getIdlang() {
		return idlang;
	}

	/**
	 * Sets the idlang.
	 *
	 * @param idlang the new idlang
	 */
	public void setIdlang(int idlang) {
		this.idlang = idlang;
	}

	/**
	 * Gets the lang.
	 *
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * Sets the lang.
	 *
	 * @param lang the new lang
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LangMaster [idlang=" + idlang + ", lang=" + lang + "]";
	}

}
