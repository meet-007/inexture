package sample.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class Address.
 */
@Entity
@Table(name="address")
public class Address implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Logger for this class. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** The idadress. */
	private Long idadress;

	/** The iduser. */

	@ManyToOne(optional=false)
	private User user;

	/** The addressline 1. */
	@NotNull
	@Size(max=100)
	/** The addressline 1. */
	private String addressline1;

	/** The addressline 2. */
	@NotNull
	@Size(max=100)
	/** The addressline 2. */
	private String addressline2;

	/** The pin. */
	@NotNull
	//@Size(min=6,max=6,message="pin.length.errormsg")
	/** The pin. */
	private Integer pin;

	/** The city. */
	@NotNull
	@Size(max=45)
	/** The city. */
	private String city;

	/** The state. */
	@NotNull
	@Size(max=45)
	/** The state. */
	private String state;

	/** The country. */
	@NotNull
	@Size(max=100)
	/** The country. */
	private String country;

	/**
	 * Gets the addressline 1.
	 *
	 * @return the addressline 1
	 */
	public String getAddressline1() {
		return addressline1;
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
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
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
	 * Gets the idadress.
	 *
	 * @return the idadress
	 */
	public Long getIdadress() {
		return idadress;
	}

	/**
	 * Gets the pin.
	 *
	 * @return the pin
	 */
	public Integer getPin() {
		return pin;
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
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
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
	 * Sets the addressline 2.
	 *
	 * @param addressline2 the new addressline 2
	 */
	public void setAddressline2(final String addressline2) {
		this.addressline2 = addressline2;
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
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * Sets the idadress.
	 *
	 * @param idadress the new idadress
	 */
	public void setIdadress(final Long idadress) {
		this.idadress = idadress;
	}

	/**
	 * Sets the pin.
	 *
	 * @param pin the new pin
	 */
	public void setPin(final Integer pin) {
		this.pin = pin;
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
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(final User user) {
		this.user = user;
	}

}
