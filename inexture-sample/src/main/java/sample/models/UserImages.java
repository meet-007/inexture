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

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	/** The iduser images. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iduserImages;


	/** The iduser. */
	@ManyToOne
	private User iduser;

	/** The image. */
	@Lob
	/** The image. */
	private byte[] image;

	/**
	 * Gets the iduser.
	 *
	 * @return the iduser
	 */
	public User getIduser() {
		return iduser;
	}

	/**
	 * Gets the iduser images.
	 *
	 * @return the iduser images
	 */
	public Long getIduserImages() {
		return iduserImages;
	}




	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public byte[] getImage() {
		return image.clone();
	}

	/**
	 * Sets the iduser.
	 *
	 * @param iduser the new iduser
	 */
	public void setIduser(final User iduser) {
		this.iduser = iduser;
	}

	/**
	 * Sets the iduser images.
	 *
	 * @param iduserImages the new iduser images
	 */
	public void setIduserImages(final Long iduserImages) {
		this.iduserImages = iduserImages;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(final byte[] image) {
		this.image = image.clone();
	}




}
