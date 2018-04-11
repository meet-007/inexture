package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Address;

// TODO: Auto-generated Javadoc
/**
 * The Interface AddressDao.
 */
public interface AddressDao {

	/** The Constant INSERT. */
	final static String INSERT = "insert into address (iduser,addressline1,addressline2,pin,city,state,country) values (?,?,?,?,?,?,?)";

	/** The Constant SELECT. */
	final static String SELECT = "select * from address where iduser = ?";

	/** The Constant UPDATE. */
	final static String UPDATE = "update address set iduser=?, addressline1 = ? ,addressline2 = ?,pin = ?,city = ?,state = ?,country = ? where idaddress = ?";

	/** The Constant DELETE. */
	final static String DELETE = "delete from address where idaddress=?";

	/**
	 * Insert address.
	 *
	 * @param addressList the it
	 * @param operation the operation
	 * @return the int
	 * @throws SQLException the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	int insertAddress(List<Address> addressList, String operation) throws SQLException, ClassNotFoundException, IOException;

	/**
	 * Select address.
	 *
	 * @param iduser the iduser
	 * @return the array list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	List<Address> selectAddress(int iduser) throws ClassNotFoundException, SQLException, IOException;

}
