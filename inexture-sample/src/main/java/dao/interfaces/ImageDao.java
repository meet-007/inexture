package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.UserImages;

// TODO: Auto-generated Javadoc
/**
 * The Interface ImageDao.
 */
public interface ImageDao {

	/** The Constant INSERT. */
	final static String INSERT = "insert into user_images (iduser,image) values (?,?);";

	/** The Constant SELECT. */
	final static String SELECT = "select * from user_images where iduser = ?; ";

	/** The Constant DELETE. */
	final static String DELETE = "delete from user_images where iduser_images = ?; ";

	/**
	 * Insert image.
	 *
	 * @param uimg the uimg
	 * @param operation the operation
	 * @return the int
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	int insertImage(List<UserImages> uimg, String operation)
			throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Select images.
	 *
	 * @param iduser the iduser
	 * @return the array list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	List<UserImages> selectImages(Integer iduser) throws ClassNotFoundException, SQLException, IOException;
}
