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
 * The Class Role.
 */
@Entity
@Table(name="role")
public class Role implements Serializable {

	/** Logger for this class. */

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The idrole. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idrole;

	/** The role. */
	private String rolename;

	/** The users. */
	@OneToMany(mappedBy="role")
	private List<User> users;
	/**
	 * Gets the idrole.
	 *
	 * @return the idrole
	 */

	public Long getIdrole() {
		return idrole;
	}

	/**
	 * Gets the rolename.
	 *
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
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
	 * Sets the idrole.
	 *
	 * @param idrole the new idrole
	 */
	public void setIdrole(final Long idrole) {
		this.idrole = idrole;
	}

	/**
	 * Sets the rolename.
	 *
	 * @param rolename the new rolename
	 */
	public void setRolename(final String rolename) {
		this.rolename = rolename;
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
