package service.interfaces;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
	 * @throws InvalidKeyException the invalid key exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	String regesterUser(HttpServletRequest req)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;

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
	 */
	String updateUser(HttpServletRequest req, int idUser)
			throws ClassNotFoundException, ParseException, SQLException, IOException, ServletException ;

	/**
	 * Gets the all user.
	 *
	 * @param role the role
	 * @return the all user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	List<User> getAllUser(int role) throws ClassNotFoundException, SQLException, IOException;

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
	 * @throws InvalidKeyException the invalid key exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	String updatePass(HttpServletRequest req)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;

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
