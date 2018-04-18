package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.ImageDao;
import model.UserImages;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageDaoImpl.
 */
@SuppressWarnings("PMD")
public class ImageDaoImpl implements ImageDao {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(ImageDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.interfaces.ImageDao#insertImage(java.util.List, java.lang.String)
	 */
	@Override
	public int insertImage(final List<UserImages> uimg, final String operation)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("insertImage(ArrayList<UserImages>, String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ArrayList<Object> arr = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < uimg.size(); i++) {
			arr.add(0, uimg.get(i).getIduser());
			arr.add(1, uimg.get(i).getImage());
			if ("insert".equals(operation)) {
				if (!DbUtil.dbOperationInsert(INSERT, arr)) {
					count++;
				}
			} else {
				arr.clear();
				arr.add(uimg.get(i).getIduserImages());
				if (!DbUtil.dbOperationInsert(DELETE, arr)) {
					count++;
				}
			}
			arr.clear();
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("insertImage(ArrayList<UserImages>, String) - end"); //$NON-NLS-1$
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.Image.ImageDao#selectImages(int)
	 */
	@Override
	public ArrayList<UserImages> selectImages(final Integer iduser) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectImages(int) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		try(final ResultSet resultSet = DbUtil.dbOperationSelect(SELECT,iduser.toString())){
			final ArrayList<UserImages> uiarr = new ArrayList<>();
			while (resultSet.next()) {
				final UserImages userImage = new UserImages();
				userImage.setIduserImages(resultSet.getInt(1));
				userImage.setIduser(resultSet.getInt(2));
				userImage.setImage(resultSet.getBinaryStream(3));
				uiarr.add(userImage);
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("selectImages(int) - end"); //$NON-NLS-1$
			}
			return uiarr;
		}
	}

}
