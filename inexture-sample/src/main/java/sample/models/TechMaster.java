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

	/** The idtech. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idtech;

	/** The tech. */
	private String tech;

	@OneToMany(mappedBy="tech")
	private List<User> users;

	public Long getIdtech() {
		return idtech;
	}

	public String getTech() {
		return tech;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setIdtech(Long idtech) {
		this.idtech = idtech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}



}
