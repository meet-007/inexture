package sample.controller;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import sample.models.UserImages;

// TODO: Auto-generated Javadoc
/**
 * The Class UserImageProeryEditor.
 */
public class UserImageProeryEditor implements org.springframework.core.convert.converter.Converter<MultipartFile, UserImages>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(UserImageProeryEditor.class.getName());

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public UserImages convert(final MultipartFile source) {
		// TODO Auto-generated method stub
		final UserImages uiImages = new UserImages();
		try {
			uiImages.setImage(source.getBytes() );
		} catch (final IOException e) {
			logger.error("convert(MultipartFile)", e); //$NON-NLS-1$
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
