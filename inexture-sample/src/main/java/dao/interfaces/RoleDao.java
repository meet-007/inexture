package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;

import model.Role;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleDao.
 */
public interface RoleDao {

	/** The Constant SELECTROLE. */
	final static String SELECTROLE = "select * from role_master where idrole_master = ?";

	/**
	 * Gets the role.
	 *
	 * @param iduser the id
	 * @return the role
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	Role getRole(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
