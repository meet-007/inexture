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
import org.springframework.lang.NonNull;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@NamedQuery(query = "from User where email=:email", name = "get_user_frm_email")
@NamedQuery(query = "from User where email=:email and password=:password", name = "get_user_frm_email_pass")
@Entity
@Table(name = "user")
public class User implements Serializable {

	/** Logger for this class. */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** The iduser. */
	private Long iduser;

	@NotNull(message="{firstname.null.errormsg}")
	@Size(min=2,max=100 ,message="{firstname.length.errormsg}")
	/** The firstname. */
	private String firstname;

	@NotNull(message="{lastname.null.errormsg}")
	@Size(min=2,max=100 ,message="{lastname.length.errormsg}")
	/** The lastname. */
	private String lastname;

	@NotNull(message="{email.null.errormsg}")
	@Size(max=80,message="{email.length.errormsg}")
	@Email(message="{email.format.errormsg}")
	/** The email. */
	private String email;

	@NotNull(message="{password.null.errormsg}")
	@Size(min=6,max=15,message="{password.length.errormsg}")
	/** The password. */
	private String password;

	@NotNull(message="{mobile.null.errormsg}")
	/** The mobile. */
	private Long mobile;

	@NotNull
	/** The gender. */
	private Integer gender;

	@NotNull(message="{dob.null.errormsg}")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	/** The dob. */
	private Date dob;

	@ManyToOne
	@NonNull
	/** The role. */
	private Role role;

	@ManyToOne
	@NotNull(message="{tech.null.errormsg}")
	/** The tech. */
	private TechMaster tech;

	@OneToMany(mappedBy = "user",fetch=FetchType.EAGER,targetEntity=Address.class)
	@Cascade(CascadeType.DELETE)
	@Valid
	private List<Address> addressList;

	@ManyToMany(fetch=FetchType.EAGER)
	@NotNull(message="{language.null.errormsg}")
	private Set<LangMaster> languages;
	//@Transient
	@OneToMany(mappedBy = "iduser",fetch=FetchType.LAZY)
	@Cascade(CascadeType.DELETE)
	private List<UserImages> userImages;

	// @ElementCollection
	// private List<MultipartFile> userImages;



	public List<Address> getAddressList() {
		return addressList;
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

	public Set<LangMaster> getLanguages() {
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
		this.addressList = addressList;
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

	public void setLanguages(Set<LangMaster> languages) {
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