package model;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Address {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Address.class.getName());

	private int idadress;;
	private int iduser;
	private String addressline1;
	private String addressline2;
	private int pin;
	private String city;
	private String state;
	private String country;

	public int getIdadress() {
		return idadress;
	}

	public void setIdadress(int idadress) {
		this.idadress = idadress;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [idadress=" + idadress + ", iduser=" + iduser + ", addressline1=" + addressline1
				+ ", addressline2=" + addressline2 + ", pin=" + pin + ", city=" + city + ", state=" + state
				+ ", country=" + country + "]";
	}

}
