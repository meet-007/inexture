package service.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.LangMaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface LangServ.
 */
public interface LangServ {

	/**
	 * Gets the lang.
	 *
	 * @return the lang
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	List<LangMaster> getLang() throws ClassNotFoundException, SQLException, IOException;
}
