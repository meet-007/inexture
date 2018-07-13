package sample.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.web.multipart.MultipartFile;

import sample.models.UserImages;

public class UserImageProeryEditor extends PropertyEditorSupport {

	@Override
	public Object getValue() {
		final MultipartFile image = (MultipartFile)getValue();
		return image;
	}

	@Override
	public void setValue(Object value) {
		final UserImages userImages = new UserImages();
		userImages.setImage((MultipartFile)value);
		setValue(userImages);
	}

}
