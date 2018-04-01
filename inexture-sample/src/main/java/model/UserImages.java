package model;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.InputStream;

public class UserImages {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(UserImages.class.getName());

	private int iduser_images;
	private int iduser;
	private InputStream image;

	public int getIduser_images() {
		return iduser_images;
	}

	public void setIduser_images(int iduser_images) {
		this.iduser_images = iduser_images;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

}
