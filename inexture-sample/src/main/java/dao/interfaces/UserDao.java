package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao {

	/** The Constant UPDATE. */
	final static String UPDATE = "update user set firstname=?,lastname=?,mobile=?,gender=?,dob=?,tech  = ? where iduser = ?;";

	/** The Constant INSERT. */
	final static String INSERT = "insert into user  (firstname,lastname,email,password,mobile,gender,dob,role,tech) values (?,?,?,?,?,?,?,?,?);";

	/** The Constant SELECTUID. */
	final static String SELECTUID = "select iduser from user where mobile = ? ;";

	/** The Constant SELECTUSERFORLOGIN. */
	final static String SELECTUSERFORLOGIN = "select * from user where email = ? and password = ?;";

	/** The Constant SELECTALLUSER. */
	final static String SELECTALLUSER = "select * from user where role = ?";

	/** The Constant SELECTUSER. */
	final static String SELECTUSER = "select * from user where iduser = ?";

	/** The Constant SELECTUSERFRMEMAIL. */
	final static String SELECTUSERFRMEMAIL = "select * from user where email = ?";

	/** The Constant DELETEUSER. */
	final static String DELETEUSER = "DELETE  from user WHERE iduser = ?";

	/** The Constant UPDATEPASS. */
	final static String UPDATEPASS = "update user set password = ? WHERE iduser = ?";

	/**
	 * Insert.
	 *
	 * @param user the u
	 * @param operation the operation
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	boolean insert(User user, String operation) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Select user.
	 *
	 * @param email the email
	 * @param pass the pass
	 * @return the user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	User selectUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Select all user.
	 *
	 * @param role the role
	 * @return the array list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	List<User> selectAllUser(int role) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Select user.
	 *
	 * @param iduser the iduser
	 * @return the user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	User selectUser(int iduser) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Select user.
	 *
	 * @param email the email
	 * @return the user
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	User selectUser(String email) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Update password.
	 *
	 * @param user the u
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	boolean updatePassword(User user) throws ClassNotFoundException, SQLException, IOException;

}
