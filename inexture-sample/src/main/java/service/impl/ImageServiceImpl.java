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
	private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class.getName());

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
		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}
		String delnewimglist[] = null;
		if (request.getParameterValues("delnewimg") != null) {
			delnewimglist = request.getParameterValues("delnewimg");
		}
		boolean insert = true;
		ArrayList<Part> 	arr = (ArrayList<Part>) request.getParts();
		Iterator<Part> it = arr.iterator();
		ArrayList<UserImages> uimg = new ArrayList<>();
		InputStream is = null;
		while (it.hasNext()) {
			Part pt = it.next();
			if (pt.getContentType() != null) {
				if (pt.getContentType().equals("image/jpeg")) {
					if (delnewimglist != null) {
						for (String delnewimg : delnewimglist) {
							if (pt.getSubmittedFileName().equals(delnewimg)) {
								insert = false;
							}
						}
					}
					if (insert == true) {
						is = pt.getInputStream();
						UserImages userimage = new UserImages();
						userimage.setIduser(iduser);
						userimage.setImage(is);
						uimg.add(userimage);
					}
				}
			}

		}
		if (request.getParameter("delimg") != null) {
			String delimg[] = request.getParameterValues("delimg");
			for (String img : delimg) {
				UserImages userimage = new UserImages();
				userimage.setIduser_images(Integer.parseInt(img));
				uimg.add(userimage);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return uimg;
	}

	public boolean saveImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("SaveImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ArrayList<UserImages> uimg = ImageServiceImpl.setParams(request, iduser);
		int totalImageInserted = 0;
		if (uimg.size() > 0) {
			totalImageInserted = new ImageDaoImpl().insertImage(uimg, "insert");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("SaveImage(HttpServletRequest, int) - {}"+"no need to insert because no images found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (logger.isDebugEnabled()) {
				logger.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
		if (totalImageInserted > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}
	}

	public ArrayList<UserImages> getUserImages(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserImages(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("getUserImages(int) - end"); //$NON-NLS-1$
		}
		return new ImageDaoImpl().selectImages(iduser);
	}

	/* (non-Javadoc)
	 * @see service.Image.ImageService#UpdateImage(javax.servlet.http.HttpServletRequest, int)
	 */
	public boolean updateImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("UpdateImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ImageDao it = new ImageDaoImpl();
		ArrayList<UserImages> dbimages = it.selectImages(iduser);
		ArrayList<UserImages> newimages = ImageServiceImpl.setParams(request, iduser);
		List<UserImages> updated = new ArrayList<>();
		List<UserImages> deleted = new ArrayList<>();
		int rowsAffected = 0;
		int flag = 0;
		for (UserImages newimage : newimages) {
			for (UserImages dbimage : dbimages) {
				if (dbimage.getIduser_images() == newimage.getIduser_images()) {
					if (logger.isDebugEnabled()) {
						logger.debug("UpdateImage(HttpServletRequest, int) - {}"+"Delete id" + newimage.getIduser_images()); //$NON-NLS-1$ //$NON-NLS-2$
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
			rowsAffected = it.insertImage((ArrayList<UserImages>) deleted, "delete");
		}
		if (updated.size() > 0) {
			rowsAffected += it.insertImage((ArrayList<UserImages>) updated, "insert");
		}
		if (rowsAffected > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("UpdateImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("UpdateImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}

	}

}
