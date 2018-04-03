package service.Tech;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TechMaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface TechServ.
 */
public interface TechServ {

	/**
	 * Gets the tech.
	 *
	 * @return the tech
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	ArrayList<TechMaster> getTech() throws ClassNotFoundException, SQLException, IOException;
}
