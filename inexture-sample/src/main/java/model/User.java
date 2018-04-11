package model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {

	/** Logger for this class. */

	/** The iduser. */
	private int iduser;

	/** The firstname. */
	private String firstname;

	/** The lastname. */
	private String lastname;

	/** The email. */
	private String email;

	/** The password. */
	private String password;

	/** The mobile. */
	private long mobile;

	/** The gender. */
	private int gender;

	/** The dob. */
	private Date dob;

	/** The role. */
	private int role;

	/** The tech. */
	private int tech;

	/**
	 * Gets the mobile.
	 *
	 * @return the mobile
	 */
	public long getMobile() {
		return mobile;
	}

	/**
	 * Sets the mobile.
	 *
	 * @param mobile the new mobile
	 */
	public void setMobile(final long mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the iduser.
	 *
	 * @return the iduser
	 */
	public int getIduser() {
		return iduser;
	}

	/**
	 * Sets the iduser.
	 *
	 * @param iduser the new iduser
	 */
	public void setIduser(final int iduser) {
		this.iduser = iduser;
	}

	/**
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param firstname the new firstname
	 */
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param lastname the new lastname
	 */
	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(final int gender) {
		this.gender = gender;
	}

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public Date getDob() {
		return  (Date)dob.clone();
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	public void setDob(final Date dob) {
		this.dob = (Date)dob.clone();
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(final int role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + ", gender=" + gender + ", dob=" + dob + ", role="
				+ role + ", tech=" + tech + "]";
	}

	/**
	 * Gets the tech.
	 *
	 * @return the tech
	 */
	public int getTech() {
		return tech;
	}

	/**
	 * Sets the tech.
	 *
	 * @param tech the new tech
	 */
	public void setTech(final int tech) {
		this.tech = tech;
	}

}