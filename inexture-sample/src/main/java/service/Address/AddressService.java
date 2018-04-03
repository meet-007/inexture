package service.Address;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.Address;

// TODO: Auto-generated Javadoc
/**
 * The Interface AddressService.
 */
public interface AddressService {

	/**
	 * Adds the address.
	 *
	 * @param request the request
	 * @param userid the userid
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	boolean addAddress(HttpServletRequest request, int userid) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Gets the user address.
	 *
	 * @param iduser the iduser
	 * @return the user address
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	ArrayList<Address> getUserAddress(int iduser) throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Update address.
	 *
	 * @param request the request
	 * @param userid the userid
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	boolean updateAddress(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException;

}
