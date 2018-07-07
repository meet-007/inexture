package sample.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import sample.models.TechMaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface TechServ.
 */
public interface TechService {

	/**
	 * Gets the tech.
	 *
	 * @return the tech
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	List<TechMaster> findAll();
}
