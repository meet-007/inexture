package sample.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class UserImages.
 */
@NamedQuery(query="from UserImages where iduser=?0" ,name="select_image_frm_user")
@Entity
@Table(name="userimages")
public class UserImages  implements Serializable{

	/** Logger for this class. */

	/** The iduser images. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iduserImages;


	/** The iduser. */
	@ManyToOne
	private User iduser;

	@Lob
	/** The image. */
	private byte[] image;

	public User getIduser() {
		return iduser;
	}

	public Long getIduserImages() {
		return iduserImages;
	}




	public byte[] getImage() {
		return image;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}

	public void setIduserImages(Long iduserImages) {
		this.iduserImages = iduserImages;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}




}
