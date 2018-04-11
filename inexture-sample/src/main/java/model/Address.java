package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 */
public class Address {

	/** Logger for this class. */

	/** The idadress. */
	private int idadress;;

	/** The iduser. */
	private int iduser;

	/** The addressline 1. */
	private String addressline1;

	/** The addressline 2. */
	private String addressline2;

	/** The pin. */
	private int pin;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The country. */
	private String country;

	/**
	 * Gets the idadress.
	 *
	 * @return the idadress
	 */
	public int getIdadress() {
		return idadress;
	}

	/**
	 * Sets the idadress.
	 *
	 * @param idadress the new idadress
	 */
	public void setIdadress(final int idadress) {
		this.idadress = idadress;
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
	 * Gets the addressline 1.
	 *
	 * @return the addressline 1
	 */
	public String getAddressline1() {
		return addressline1;
	}

	/**
	 * Sets the addressline 1.
	 *
	 * @param addressline1 the new addressline 1
	 */
	public void setAddressline1(final String addressline1) {
		this.addressline1 = addressline1;
	}

	/**
	 * Gets the addressline 2.
	 *
	 * @return the addressline 2
	 */
	public String getAddressline2() {
		return addressline2;
	}

	/**
	 * Sets the addressline 2.
	 *
	 * @param addressline2 the new addressline 2
	 */
	public void setAddressline2(final String addressline2) {
		this.addressline2 = addressline2;
	}

	/**
	 * Gets the pin.
	 *
	 * @return the pin
	 */
	public int getPin() {
		return pin;
	}

	/**
	 * Sets the pin.
	 *
	 * @param pin the new pin
	 */
	public void setPin(final int pin) {
		this.pin = pin;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [idadress=" + idadress + ", iduser=" + iduser + ", addressline1=" + addressline1
				+ ", addressline2=" + addressline2 + ", pin=" + pin + ", city=" + city + ", state=" + state
				+ ", country=" + country + "]";
	}

}
