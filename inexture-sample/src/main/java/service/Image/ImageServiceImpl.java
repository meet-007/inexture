package service.Image;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

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

import dao.Image.ImageDao;
import dao.Image.ImageDaoImpl;
import model.UserImages;

public class ImageServiceImpl implements ImageService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class.getName());

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
		ArrayList arr = new ArrayList();
		arr = (ArrayList) request.getParts();
		Iterator it = arr.iterator();
		ArrayList<UserImages> uimg = new ArrayList<UserImages>();
		// File f = new File("d:/img.jpg");
		// FileOutputStream fos = new FileOutputStream(f);
		InputStream is = null;
		// int read =0;
		while (it.hasNext()) {
			Part pt = (Part) it.next();
			System.out.println("--" + pt.getSubmittedFileName() + "------------" + pt.getSize() + "--" + pt.getName()
					+ "-" + pt.getContentType());
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
						System.out.println("-----------------" + pt.getSubmittedFileName() + "------------"
								+ pt.getSize() + "-------------" + pt.getName() + "----------" + pt.getContentType());
						is = pt.getInputStream();
						/*
						 * ByteArrayOutputStream buffer = new ByteArrayOutputStream(); int nRead; byte[]
						 * data = new byte[1024]; while ((nRead = is.read(data, 0, data.length)) != -1)
						 * { buffer.write(data, 0, nRead); }
						 *
						 * buffer.flush(); buffer.toByteArray();
						 */
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
			int noOfDelImg = delimg.length;

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

	public boolean SaveImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("SaveImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		System.out.println(request.getContentType());
		// UserImages uimg[] = new UserImages[];
		ArrayList<UserImages> uimg = ImageServiceImpl.setParams(request, iduser);
		int totalImageInserted = 0;
		if (uimg.size() > 0) {
			totalImageInserted = new ImageDaoImpl().insertImage(uimg, "insert");
		} else {
			System.out.println(
					"no need to insert because no images found from user side" + "--------" + "returning true");

			if (logger.isDebugEnabled()) {
				logger.debug("SaveImage(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
		// while(( read = is.read())!=-1) {
		// fos.write(read);
		// }
		// fos.close();

		// byte[] b = new byte[1000];
		// // int bytereaded = is.read(b);
		// System.out.println(bytereaded);
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
		ImageDao imdao = new ImageDaoImpl();
		ArrayList<UserImages> returnArrayList = imdao.selectImages(iduser);
		if (logger.isDebugEnabled()) {
			logger.debug("getUserImages(int) - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

	public boolean UpdateImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug("UpdateImage(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ImageDao it = new ImageDaoImpl();
		ArrayList<UserImages> dbimages = it.selectImages(iduser);
		ArrayList<UserImages> newimages = ImageServiceImpl.setParams(request, iduser);
		List<UserImages> updated = new ArrayList<UserImages>();
		List<UserImages> deleted = new ArrayList<UserImages>();
		int rowsAffected = 0;
		int flag = 0;
		for (UserImages newimage : newimages) {
			for (UserImages dbimage : dbimages) {
				if (dbimage.getIduser_images() == newimage.getIduser_images()) {
					System.out.println("Delete id" + newimage.getIduser_images());
					deleted.add(dbimage);
					flag = 1;
					break;
				}

			}
			if (flag == 0) {
				updated.add(newimage);
				// rowsAffected += langtrans.InsertLangTrans(newlang,"update");
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
