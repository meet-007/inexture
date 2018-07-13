package sample.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

// TODO: Auto-generated Javadoc
/**
 * The Class UserImages.
 */
@Entity
@Table(name="userimages")
public class UserImages {

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
	private MultipartFile image;

	public User getIduser() {
		return iduser;
	}

	public Long getIduserImages() {
		return iduserImages;
	}



	public MultipartFile getImage() {
		return image;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}

	public void setIduserImages(Long iduserImages) {
		this.iduserImages = iduserImages;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}



}
