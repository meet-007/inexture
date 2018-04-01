package dao.Image;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserImages;
import util.DbUtil;

public class ImageDaoImpl implements ImageDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ImageDaoImpl.class.getName());

	public int insertImage(ArrayList<UserImages> uimg, String operation)
			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("insertImage(ArrayList<UserImages>, String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ArrayList arr = new ArrayList();
		int count = 0;
		for (int i = 0; i < uimg.size(); i++) {
			arr.add(0, uimg.get(i).getIduser());
			arr.add(1, uimg.get(i).getImage());
			if (operation.equals("insert")) {
				if (!DbUtil.dbOperationInsert(INSERT, arr)) {
					count++;
				}
			} else {
				arr.removeAll(arr);
				arr.add(uimg.get(i).getIduser_images());
				if (!DbUtil.dbOperationInsert(DELETE, arr)) {
					count++;
				}
			}

			arr.removeAll(arr);

		}

		if (logger.isDebugEnabled()) {
			logger.debug("insertImage(ArrayList<UserImages>, String) - end"); //$NON-NLS-1$
		}
		return count;
	}

	public ArrayList selectImages(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectImages(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		Integer id = (Integer) iduser;
		ResultSet rs = DbUtil.dbOperationSelect(SELECT, id.toString());
		ArrayList<UserImages> uiarr = new ArrayList<UserImages>();
		while (rs.next()) {
			UserImages ui = new UserImages();
			ui.setIduser_images(rs.getInt(1));
			ui.setIduser(rs.getInt(2));
			ui.setImage(rs.getBinaryStream(3));
			uiarr.add(ui);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("selectImages(int) - end"); //$NON-NLS-1$
		}
		return uiarr;
	}

}
