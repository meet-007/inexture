package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.LangMaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface LangDao.
 */
public interface LangDao {

	/** The Constant select. */
	String SELECT = "select * from language_master;";

	/**
	 * Select lang.
	 *
	 * @return the array list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	List<LangMaster> selectLang() throws ClassNotFoundException, SQLException, IOException;
}
