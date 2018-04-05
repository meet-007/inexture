package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.ImageDao;
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
		ArrayList<Object> arr = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < uimg.size(); i++) {
			arr.add(0, uimg.get(i).getIduser());
			arr.add(1, uimg.get(i).getImage());
			if (operation.equals("insert")) {
				if (!DbUtil.dbOperationInsert(INSERT, arr)) {
					count++;
				}
			} else {
				arr.clear();
				arr.add(uimg.get(i).getIduser_images());
				if (!DbUtil.dbOperationInsert(DELETE, arr)) {
					count++;
				}
			}
			arr.clear();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("insertImage(ArrayList<UserImages>, String) - end"); //$NON-NLS-1$
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.Image.ImageDao#selectImages(int)
	 */
	public ArrayList<UserImages> selectImages(Integer iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectImages(int) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		ResultSet rs = DbUtil.dbOperationSelect(SELECT,iduser.toString());
		ArrayList<UserImages> uiarr = new ArrayList<>();
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
