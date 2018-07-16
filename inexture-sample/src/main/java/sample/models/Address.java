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

import org.springframework.lang.NonNull;

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

	/** The iduser. */
	@NonNull
	@ManyToOne(optional=false)
	private User user;

	@NotNull(message="{addressline1.null.errormsg}")
	@Size(max=100,message="{addressline1.length.errormsg}")
	/** The addressline 1. */
	private String addressline1;

	@NotNull(message="{addressline2.null.errormsg}")
	@Size(max=100,message="{addressline2.length.errormsg}")
	/** The addressline 2. */
	private String addressline2;

	@NotNull(message="{pin.null.errormsg}")
	//@Size(min=6,max=6,message="pin.length.errormsg")
	/** The pin. */
	private Integer pin;

	@NotNull(message="{city.null.errormsg}")
	@Size(max=45,message="{city.length.errormsg}")
	/** The city. */
	private String city;

	@NotNull(message="{state.null.errormsg}")
	@Size(max=45,message="{state.length.errormsg}")
	/** The state. */
	private String state;

	@NotNull(message="{country.null.errormsg}")
	@Size(max=100,message="{country.length.errormsg}")
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
