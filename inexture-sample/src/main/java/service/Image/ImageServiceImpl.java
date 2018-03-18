package service.Image;

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
import dao.LangTransaction.LangTrans;
import model.UserImages;
import model.UserImages;

public class ImageServiceImpl implements ImageService {

	public static ArrayList<UserImages> setParams(HttpServletRequest request, int iduser)
			throws IOException, ParseException, ServletException {
		ArrayList arr = new ArrayList();
		arr = (ArrayList) request.getParts();
		Iterator it = arr.iterator();
		int count = 0;
		for (Object ob : arr) {
			Part p = (Part) ob;
			if (p.getContentType() != null) {
				if (p.getContentType().equals("image/jpeg")) {
					System.out.println("-----------------" + p.getSubmittedFileName());
					count++;
				}
			}
		}
		ArrayList<UserImages> uimg = new ArrayList<UserImages>();
		int i = 0;
		// File f = new File("d:/img.jpg");
		// FileOutputStream fos = new FileOutputStream(f);
		InputStream is = null;
		// int read =0;
		while (it.hasNext()) {
			Part pt = (Part) it.next();
			System.out.println("--" + pt.getSubmittedFileName() + "------------" + pt.getSize()
			+ "--" + pt.getName() + "-" + pt.getContentType());
			if (pt.getContentType() != null) {
				if (pt.getContentType().equals("image/jpeg")) {
					System.out.println("-----------------" + pt.getSubmittedFileName() + "------------" + pt.getSize()
							+ "-------------" + pt.getName() + "----------" + pt.getContentType());
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
					i++;
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
		return uimg;
	}

	public boolean SaveImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		System.out.println(request.getContentType());
		// UserImages uimg[] = new UserImages[];
		ArrayList<UserImages> uimg = ImageServiceImpl.setParams(request, iduser);
		int totalImageInserted = new ImageDaoImpl().insertImage(uimg, "insert");
		// while(( read = is.read())!=-1) {
		// fos.write(read);
		// }
		// fos.close();

		// byte[] b = new byte[1000];
		// // int bytereaded = is.read(b);
		// System.out.println(bytereaded);
		if (totalImageInserted > 0) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<UserImages> getUserImages(int iduser) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ImageDao imdao = new ImageDaoImpl();
		return imdao.selectImages(iduser);
	}

	public boolean UpdateImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
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
			rowsAffected = it.insertImage((ArrayList<UserImages>)deleted, "delete");
		}
		if (updated.size() > 0) {
			rowsAffected += it.insertImage((ArrayList<UserImages>)updated, "insert");
		}
		if (rowsAffected > 0) {
			return true;
		} else {
			return false;
		}

	}

}
