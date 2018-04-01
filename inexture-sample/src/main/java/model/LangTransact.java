package model;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class LangTransact {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LangTransact.class.getName());

	private int idlang_transaction;
	private int iduser;
	private int idlangmaster;

	public int getIdlang_transaction() {
		return idlang_transaction;
	}

	public void setIdlang_transaction(int idlang_transaction) {
		this.idlang_transaction = idlang_transaction;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdlangmaster() {
		return idlangmaster;
	}

	public void setIdlangmaster(int idlangmaster) {
		this.idlangmaster = idlangmaster;
	}

}
