package service.impl;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.impl.ImageDaoImpl;
import dao.interfaces.ImageDao;
import model.UserImages;
import service.interfaces.ImageService;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageServiceImpl.
 */
public class ImageServiceImpl implements ImageService {
	
	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(ImageServiceImpl.class.getName());

	/**
	 * Sets the params.
	 *
	 * @param request the request
	 * @param iduser the iduser
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ServletException the servlet exception
	 */
	public static List<UserImages> setParams(final HttpServletRequest request, final int iduser)
			throws IOException, ParseException, ServletException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}
		String delnewimglist[] = null;
		if (request.getParameterValues("delnewimg") != null) {
			delnewimglist = request.getParameterValues("delnewimg");
		}

		ArrayList<UserImages> uimg = new ArrayList<>();
		final ArrayList<InputStream> islist = (ArrayList<InputStream>)util.ImageUtil.getImages(request, delnewimglist);
		final Iterator iterator = islist.iterator();
		while (iterator.hasNext()) {

			final InputStream inputStream = (InputStream)iterator.next();
			final UserImages userimage = new UserImages();
			userimage.setIduser(iduser);
			userimage.setImage(inputStream);
			uimg.add(userimage);

		}
		uimg = (ArrayList<UserImages>)ImageServiceImpl.setDeleteImageList(request, uimg);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return uimg;
	}
	
	/**
	 * Sets the delete image list.
	 *
	 * @param request the request
	 * @param uimg the uimg
	 * @return the list
	 */
	public static List<UserImages> setDeleteImageList(final HttpServletRequest request,final List<UserImages> uimg){
		if (request.getParameter("delimg") != null) {
			final String delimg[] = request.getParameterValues("delimg");
			for (final String img : delimg) {
				final UserImages userimage = new UserImages();
				userimage.setIduserImages(Integer.parseInt(img));
				uimg.add(userimage);
			}
		}
		return uimg;
	}

	/* (non-Javadoc)
	 * @see service.interfaces.ImageService#saveImage(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public boolean saveImage(final HttpServletRequest request, final int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("SaveImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ArrayList<UserImages> uimg = (ArrayList<UserImages>)ImageServiceImpl.setParams(request, iduser);
		int totalImageInserted = 0;
		if (uimg.isEmpty()) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SaveImage(HttpServletRequest, int) - {}"+"no need to insert because no images found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			totalImageInserted = new ImageDaoImpl().insertImage(uimg, "insert");
		}
		if (totalImageInserted > 0) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see service.interfaces.ImageService#getUserImages(int)
	 */
	@Override
	public ArrayList<UserImages> getUserImages(final int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUserImages(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUserImages(int) - end"); //$NON-NLS-1$
		}
		return new ImageDaoImpl().selectImages(iduser);
	}

	/* (non-Javadoc)
	 * @see service.Image.ImageService#UpdateImage(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public boolean updateImage(final HttpServletRequest request, final int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UpdateImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ImageDao imagedao = new ImageDaoImpl();
		final ArrayList<UserImages> dbimages = (ArrayList<UserImages>)imagedao.selectImages(iduser);
		final ArrayList<UserImages> newimages = (ArrayList<UserImages>)ImageServiceImpl.setParams(request, iduser);
		final List<UserImages> updated = new ArrayList<>();
		final List<UserImages> deleted = new ArrayList<>();
		int rowsAffected = 0;
		int flag = 0;
		for (final UserImages newimage : newimages) {
			for (final UserImages dbimage : dbimages) {
				if (dbimage.getIduserImages() == newimage.getIduserImages()) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("UpdateImage(HttpServletRequest, int) - {}"+"Delete id" + newimage.getIduserImages()); //$NON-NLS-1$ //$NON-NLS-2$
					}
					deleted.add(dbimage);
					flag = 1;
					break;
				}

			}
			if (flag == 0) {
				updated.add(newimage);
			}
			flag = 0;
		}
		if (!deleted.isEmpty()) {
			rowsAffected = imagedao.insertImage(deleted, "delete");
		}
		if (!updated.isEmpty()) {
			rowsAffected += imagedao.insertImage(updated, "insert");
		}
		if (rowsAffected > 0) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("UpdateImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("UpdateImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}

	}

}
