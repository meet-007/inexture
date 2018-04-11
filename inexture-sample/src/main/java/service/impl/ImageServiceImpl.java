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
import javax.servlet.http.Part;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.impl.ImageDaoImpl;
import dao.interfaces.ImageDao;
import model.UserImages;
import service.interfaces.ImageService;

public class ImageServiceImpl implements ImageService {
	/**
	 * Logger for this class
	 */
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
	public static ArrayList<UserImages> setParams(HttpServletRequest request, int iduser)
			throws IOException, ParseException, ServletException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}
		String delnewimglist[] = null;
		if (request.getParameterValues("delnewimg") != null) {
			delnewimglist = request.getParameterValues("delnewimg");
		}
		boolean insert = true;
		final ArrayList<Part> 	arr = (ArrayList<Part>) request.getParts();
		final Iterator<Part> it = arr.iterator();
		final ArrayList<UserImages> uimg = new ArrayList<>();
		InputStream is = null;
		while (it.hasNext()) {
			final Part pt = it.next();
			if (pt.getContentType() != null) {
				if (pt.getContentType().equals("image/jpeg")) {
					if (delnewimglist != null) {
						for (final String delnewimg : delnewimglist) {
							if (pt.getSubmittedFileName().equals(delnewimg)) {
								insert = false;
							}
						}
					}
					if (insert == true) {
						is = pt.getInputStream();
						final UserImages userimage = new UserImages();
						userimage.setIduser(iduser);
						userimage.setImage(is);
						uimg.add(userimage);
					}
				}
			}

		}
		if (request.getParameter("delimg") != null) {
			final String delimg[] = request.getParameterValues("delimg");
			for (final String img : delimg) {
				final UserImages userimage = new UserImages();
				userimage.setIduserImages(Integer.parseInt(img));
				uimg.add(userimage);
			}
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return uimg;
	}

	@Override
	public boolean saveImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("SaveImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ArrayList<UserImages> uimg = ImageServiceImpl.setParams(request, iduser);
		int totalImageInserted = 0;
		if (uimg.size() > 0) {
			totalImageInserted = new ImageDaoImpl().insertImage(uimg, "insert");
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SaveImage(HttpServletRequest, int) - {}"+"no need to insert because no images found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
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

	@Override
	public ArrayList<UserImages> getUserImages(int iduser) throws ClassNotFoundException, SQLException, IOException {
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
	public boolean updateImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UpdateImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ImageDao it = new ImageDaoImpl();
		final ArrayList<UserImages> dbimages = (ArrayList<UserImages>)it.selectImages(iduser);
		final ArrayList<UserImages> newimages = ImageServiceImpl.setParams(request, iduser);
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
		if (deleted.size() > 0) {
			rowsAffected = it.insertImage(deleted, "delete");
		}
		if (updated.size() > 0) {
			rowsAffected += it.insertImage(updated, "insert");
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
