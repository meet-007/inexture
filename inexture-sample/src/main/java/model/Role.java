package model;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Role {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Role.class.getName());

	private int idrole;
	private String role;

	public int getIdrole() {
		return idrole;
	}

	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
