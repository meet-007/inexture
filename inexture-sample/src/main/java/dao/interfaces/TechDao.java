package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TechMaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface TechDao.
 */
public interface TechDao {

	/** The Constant select. */
	final static String SELECT = "select * from tech_master;";

	/**
	 * Select tech.
	 *
	 * @return the array list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	ArrayList<TechMaster> selectTech() throws ClassNotFoundException, SQLException, IOException;
}
