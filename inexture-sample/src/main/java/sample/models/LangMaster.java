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

	/** The idlang. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idlang;

	/** The lang. */
	private String lang;

	@ManyToMany(mappedBy="languages")
	private List<User> users;


	public Long getIdlang() {
		return idlang;
	}


	public String getLang() {
		return lang;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setIdlang(Long idlang) {
		this.idlang = idlang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}



}
