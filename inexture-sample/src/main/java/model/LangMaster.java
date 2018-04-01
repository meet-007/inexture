package model;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class LangMaster {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LangMaster.class.getName());

	private int idlang;
	private String lang;

	public int getIdlang() {
		return idlang;
	}

	public void setIdlang(int idlang) {
		this.idlang = idlang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "LangMaster [idlang=" + idlang + ", lang=" + lang + "]";
	}

}
