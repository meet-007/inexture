package model;

public class LangMaster {
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
