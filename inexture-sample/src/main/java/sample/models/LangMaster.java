package sample.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class LangMaster.
 */
@Entity
@Table(name="langmaster")
public class LangMaster implements java.io.Serializable {

	/** Logger for this class. */

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** The idlang. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idlang;

	/** The lang. */
	private String lang;

	/** The users. */
	@ManyToMany(mappedBy="languages")
	private List<User> users;


	/**
	 * Gets the idlang.
	 *
	 * @return the idlang
	 */
	public Long getIdlang() {
		return idlang;
	}


	/**
	 * Gets the lang.
	 *
	 * @return the lang
	 */
	public String getLang() {
		return lang;
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
	 * Sets the idlang.
	 *
	 * @param idlang the new idlang
	 */
	public void setIdlang(final Long idlang) {
		this.idlang = idlang;
	}


	/**
	 * Sets the lang.
	 *
	 * @param lang the new lang
	 */
	public void setLang(final String lang) {
		this.lang = lang;
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
