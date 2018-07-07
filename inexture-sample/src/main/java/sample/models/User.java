package sample.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Entity
public class User {

	/** Logger for this class. */

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/** The iduser. */
	private Long iduser;

	/** The firstname. */
	private String firstname;

	/** The lastname. */
	private String lastname;

	/** The email. */
	private String email;

	/** The password. */
	private String password;

	/** The mobile. */
	private Long mobile;

	/** The gender. */
	private Integer gender;

	/** The dob. */
	private Date dob;

	@ManyToOne
	/** The role. */
	private Role role;

	@ManyToOne
	/** The tech. */
	private TechMaster tech;

	@OneToMany(mappedBy="user")
	/** The Address */
	private List<Address> AddressList;

	@ManyToMany
	private List<LangMaster> languages;

	@OneToMany(mappedBy="iduser")
	private List<UserImages> userImages;


	public List<Address> getAddressList() {
		return AddressList;
	}


	public Date getDob() {
		return dob;
	}


	public String getEmail() {
		return email;
	}


	public String getFirstname() {
		return firstname;
	}


	public Integer getGender() {
		return gender;
	}


	public Long getIduser() {
		return iduser;
	}


	public List<LangMaster> getLanguages() {
		return languages;
	}


	public String getLastname() {
		return lastname;
	}


	public Long getMobile() {
		return mobile;
	}


	public String getPassword() {
		return password;
	}


	public Role getRole() {
		return role;
	}


	public TechMaster getTech() {
		return tech;
	}


	public List<UserImages> getUserImages() {
		return userImages;
	}


	public void setAddressList(List<Address> addressList) {
		AddressList = addressList;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public void setGender(Integer gender) {
		this.gender = gender;
	}


	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}


	public void setLanguages(List<LangMaster> languages) {
		this.languages = languages;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public void setTech(TechMaster tech) {
		this.tech = tech;
	}


	public void setUserImages(List<UserImages> userImages) {
		this.userImages = userImages;
	}





}