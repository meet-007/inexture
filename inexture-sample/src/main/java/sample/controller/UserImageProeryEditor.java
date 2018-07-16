package sample.controller;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import sample.models.UserImages;

public class UserImageProeryEditor implements org.springframework.core.convert.converter.Converter<MultipartFile, UserImages>{

	@Override
	public UserImages convert(MultipartFile source) {
		// TODO Auto-generated method stub
		final UserImages uiImages = new UserImages();
		try {
			uiImages.setImage(source.getBytes());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uiImages;
	}

	//	@Override
	//	public Object getValue() {
	//		final UserImages image = (UserImages)getValue();
	//
	//		return image;
	//	}
	//
	//	@Override
	//	public void setValue(Object value) {
	//		final UserImages userImages = new UserImages();
	//		userImages.setImage((MultipartFile)value);
	//		setValue(userImages);
	//	}



}
