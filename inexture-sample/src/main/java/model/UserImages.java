package model;

import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class UserImages.
 */
public class UserImages {

	/** Logger for this class. */

	/** The iduser images. */
	private int iduser_images;

	/** The iduser. */
	private int iduser;

	/** The image. */
	private InputStream image;

	/**
	 * Gets the iduser images.
	 *
	 * @return the iduser images
	 */
	public int getIduser_images() {
		return iduser_images;
	}

	/**
	 * Sets the iduser images.
	 *
	 * @param iduser_images the new iduser images
	 */
	public void setIduser_images(int iduser_images) {
		this.iduser_images = iduser_images;
	}

	/**
	 * Gets the iduser.
	 *
	 * @return the iduser
	 */
	public int getIduser() {
		return iduser;
	}

	/**
	 * Sets the iduser.
	 *
	 * @param iduser the new iduser
	 */
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public InputStream getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(InputStream image) {
		this.image = image;
	}

}
