package sample.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
/**
 * @author inexture
 *
 */
@NamedQuery(query = "from User where email=:email", name = "get_user_frm_email")
@NamedQuery(query = "from User where email=:email and password=:password", name = "get_user_frm_email_pass")
@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Logger for this class. */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** The iduser. */
	private Long iduser;

	/** The firstname. */
	@NotNull
	@Size(min = 2, max = 100)
	/** The firstname. */
	private String firstname;

	/** The lastname. */
	@NotNull
	@Size(min = 2, max = 100)
	/** The lastname. */
	private String lastname;

	/** The email. */
	@NotNull
	@Size(max = 80)
	@Email
	/** The email. */
	private String email;

	/** The password. */
	@NotNull
	@Size(min = 6, max = 15)
	/** The password. */
	private String password;

	/** The mobile. */
	@NotNull
	/** The mobile. */
	private Long mobile;

	/** The gender. */
	@NotNull
	/** The gender. */
	private Integer gender;

	/** The dob. */
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	/** The dob. */
	private Date dob;

	/** The role. */
	@ManyToOne
	/** The role. */
	private Role role;

	/** The tech. */
	@ManyToOne
	@NotNull
	/** The tech. */
	private TechMaster tech;

	/** The address list. */
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, targetEntity = Address.class)
	@Cascade(CascadeType.DELETE)
	@Valid
	private List<Address> addressList;

	/** The languages. */
	@ManyToMany(fetch = FetchType.EAGER)
	@NotNull
	private Set<LangMaster> languages;

	/** The user images. */
	// @Transient
	@OneToMany(mappedBy = "iduser", fetch = FetchType.LAZY)
	@Cascade(CascadeType.DELETE)
	private List<UserImages> userImages;

	// @ElementCollection
	// private List<MultipartFile> userImages;

	/**
	 * Gets the address list.
	 *
	 * @return the address list
	 */
	public List<Address> getAddressList() {
		return addressList;
	}

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public Date getDob() {
		if(dob==null) {
			return null;
		}
		return (Date)dob.clone();
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
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * Gets the iduser.
	 *
	 * @return the iduser
	 */
	public Long getIduser() {
		return iduser;
	}

	/**
	 * Gets the languages.
	 *
	 * @return the languages
	 */
	public Set<LangMaster> getLanguages() {
		return languages;
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
	 * Gets the mobile.
	 *
	 * @return the mobile
	 */
	public Long getMobile() {
		return mobile;
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
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Gets the tech.
	 *
	 * @return the tech
	 */
	public TechMaster getTech() {
		return tech;
	}

	/**
	 * Gets the user images.
	 *
	 * @return the user images
	 */
	public List<UserImages> getUserImages() {
		return userImages;
	}

	/**
	 * Sets the address list.
	 *
	 * @param addressList the new address list
	 */
	public void setAddressList(final List<Address> addressList) {
		this.addressList = addressList;
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
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(final String email) {
		this.email = email;
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
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(final Integer gender) {
		this.gender = gender;
	}

	/**
	 * Sets the iduser.
	 *
	 * @param iduser the new iduser
	 */
	public void setIduser(final Long iduser) {
		this.iduser = iduser;
	}

	/**
	 * Sets the languages.
	 *
	 * @param languages the new languages
	 */
	public void setLanguages(final Set<LangMaster> languages) {
		this.languages = languages;
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
	 * Sets the mobile.
	 *
	 * @param mobile the new mobile
	 */
	public void setMobile(final Long mobile) {
		this.mobile = mobile;
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
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(final Role role) {
		this.role = role;
	}

	/**
	 * Sets the tech.
	 *
	 * @param tech the new tech
	 */
	public void setTech(final TechMaster tech) {
		this.tech = tech;
	}

	/**
	 * Sets the user images.
	 *
	 * @param userImages the new user images
	 */
	public void setUserImages(final List<UserImages> userImages) {
		this.userImages = userImages;
	}

}