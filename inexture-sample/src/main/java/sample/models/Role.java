package sample.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class Role.
 */
@Entity
public class Role {

	/** Logger for this class. */

	/** The idrole. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idrole;

	/** The role. */
	private String rolename;
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

	public String getRolename() {
		return rolename;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setIdrole(Long idrole) {
		this.idrole = idrole;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}





}
