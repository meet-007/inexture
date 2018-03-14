package service.Image;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import dao.Image.ImageDao;
import dao.Image.ImageDaoImpl;
import model.UserImages;

public class ImageServiceImpl implements ImageService {

	public boolean SaveImage(HttpServletRequest request, int iduser) throws IOException, ServletException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println(request.getContentType());
		// UserImages uimg[] = new UserImages[];
		ArrayList arr = new ArrayList();
		arr = (ArrayList) request.getParts();
		Iterator it = arr.iterator();
		int count = 0;
		for (Object ob : arr) {
			Part p = (Part) ob;
			if (p.getSubmittedFileName() != null) {
				count++;
			}
		}
		UserImages[] uimg = new UserImages[count];
		int i = 0;
		// File f = new File("d:/img.jpg");
		// FileOutputStream fos = new FileOutputStream(f);
		InputStream is = null;
		// int read =0;
		while (it.hasNext()) {
			Part pt = (Part) it.next();
			if (pt.getSubmittedFileName() != null) {
				is = pt.getInputStream();
				//				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				//				int nRead;
				//				byte[] data = new byte[1024];
				//				while ((nRead = is.read(data, 0, data.length)) != -1) {
				//					buffer.write(data, 0, nRead);
				//				}
				//
				//				buffer.flush();
				//				buffer.toByteArray();

				uimg[i] = new UserImages();
				uimg[i].setIduser(iduser);
				uimg[i].setImage(is);
				i++;
			}

		}
		int totalImageInserted = new ImageDaoImpl().insertImage(uimg);
		// while(( read = is.read())!=-1) {
		// fos.write(read);
		// }
		// fos.close();

		// byte[] b = new byte[1000];
		// // int bytereaded = is.read(b);
		// System.out.println(bytereaded);
		if(totalImageInserted > 0) {
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

}
