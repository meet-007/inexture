package sample.models;

import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class UserImages.
 */
@Entity
public class UserImages {

	/** Logger for this class. */

	/** The iduser images. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long iduserImages;


	/** The iduser. */
	@ManyToOne
	private User iduser;

	@Lob
	/** The image. */
	private InputStream image;

	public User getIduser() {
		return iduser;
	}

	public Long getIduserImages() {
		return iduserImages;
	}

	public InputStream getImage() {
		return image;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}

	public void setIduserImages(Long iduserImages) {
		this.iduserImages = iduserImages;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}


}
