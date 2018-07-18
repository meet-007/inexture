package sample.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class TechMaster.
 */
@Entity
@Table(name="techmaster")
public class TechMaster implements Serializable {

	/** Logger for this class. */

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The idtech. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtech;

	/** The tech. */
	private String tech;

	/** The users. */
	@OneToMany(mappedBy="tech")
	private List<User> users;

	/**
	 * Gets the idtech.
	 *
	 * @return the idtech
	 */
	public Long getIdtech() {
		return idtech;
	}

	/**
	 * Gets the tech.
	 *
	 * @return the tech
	 */
	public String getTech() {
		return tech;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the idtech.
	 *
	 * @param idtech the new idtech
	 */
	public void setIdtech(final Long idtech) {
		this.idtech = idtech;
	}

	/**
	 * Sets the tech.
	 *
	 * @param tech the new tech
	 */
	public void setTech(final String tech) {
		this.tech = tech;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(final List<User> users) {
		this.users = users;
	}



}
