package service.user;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Regester user.
	 *
	 * @param req the req
	 * @return the string
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ServletException the servlet exception
	 * @throws Exception the exception
	 */
	String regesterUser(HttpServletRequest req)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException, Exception;

	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @param pass the pass
	 * @return the user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	User getUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Update user.
	 *
	 * @param req the req
	 * @param idUser the id user
	 * @return the string
	 * @throws ClassNotFoundException the class not found exception
	 * @throws ParseException the parse exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @throws Exception the exception
	 */
	String updateUser(HttpServletRequest req, int idUser)
			throws ClassNotFoundException, ParseException, SQLException, IOException, ServletException, Exception;

	/**
	 * Gets the all user.
	 *
	 * @param role the role
	 * @return the all user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	ArrayList<User> getAllUser(int role) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Gets the user.
	 *
	 * @param iduser the iduser
	 * @return the user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	User getUser(int iduser) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Delete user.
	 *
	 * @param iduser the iduser
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ServletException the servlet exception
	 */
	boolean deleteUser(int iduser)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException;

	/**
	 * Update pass.
	 *
	 * @param req the req
	 * @return the string
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ServletException the servlet exception
	 * @throws Exception the exception
	 */
	String updatePass(HttpServletRequest req)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException, Exception;

	/**
	 * Check user exist.
	 *
	 * @param req the req
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	boolean checkUserExist(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException;
}
