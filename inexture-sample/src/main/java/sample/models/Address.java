package sample.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 */
@Entity
@Table(name="address")
public class Address implements Serializable {

	/** Logger for this class. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** The idadress. */
	private Long idadress;

	@ManyToOne(optional=false)
	/** The iduser. */
	private User user;

	/** The addressline 1. */
	private String addressline1;

	/** The addressline 2. */
	private String addressline2;

	/** The pin. */
	private Integer pin;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The country. */
	private String country;

	public String getAddressline1() {
		return addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Long getIdadress() {
		return idadress;
	}

	public Integer getPin() {
		return pin;
	}

	public String getState() {
		return state;
	}

	public User getUser() {
		return user;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setIdadress(Long idadress) {
		this.idadress = idadress;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
