package service.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.LangTransact;

/**
 * The Interface LangTransServ.
 */
public interface LangTransServ {

	/**
	 * Adds the lang transaction.
	 *
	 * @param request the request
	 * @param userid the userid
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	boolean addLangTransaction(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Gets the user languages.
	 *
	 * @param iduser the iduser
	 * @return the user languages
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	ArrayList<LangTransact> getUserLanguages(int iduser) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Update lang transaction.
	 *
	 * @param request the request
	 * @param userid the userid
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	boolean updateLangTransaction(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException;
}
