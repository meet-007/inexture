package dao.Address;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Address;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressDaoImpl.
 */
public class AddressDaoImpl implements AddressDao {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(AddressDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Address.AddressDao#insertAddress(java.util.ArrayList, java.lang.String)
	 */
	public int insertAddress(ArrayList<Address> it, String operation)
			throws SQLException, ClassNotFoundException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("insertAddress(ArrayList<Address>, String) - start"); //$NON-NLS-1$
		}
		ArrayList<Object> arr = new ArrayList<Object>();
		int count=0;
		for (int i = 0; i < it.size(); i++) {
			arr.add(it.get(i).getIduser());
			arr.add(it.get(i).getAddressline1());
			arr.add(it.get(i).getAddressline2());
			arr.add(it.get(i).getPin());
			arr.add(it.get(i).getCity());
			arr.add(it.get(i).getState());
			arr.add(it.get(i).getCountry());
			if (operation.equals("insert")) {
				if (!DbUtil.dbOperationInsert(INSERT, arr)) {
					count++;
				}
			} else if (operation.equals("update")) {
				arr.add(it.get(i).getIdadress());
				if (!DbUtil.dbOperationInsert(UPDATE, arr)) {
					count++;
				}
			} else {
				arr.removeAll(arr);
				arr.add(it.get(i).getIdadress());
				if (!DbUtil.dbOperationInsert(DELETE, arr)) {
					count++;
				}
			}
			arr.removeAll(arr);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("insertAddress(ArrayList<Address>, String) - end"); //$NON-NLS-1$
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.Address.AddressDao#selectAddress(int)
	 */
	public ArrayList<Address> selectAddress(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectAddress(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ResultSet rs = DbUtil.dbOperationSelect(SELECT, iduser);
		ArrayList<Address> addresslist = new ArrayList<Address>();
		while (rs.next()) {
			Address address = new Address();
			address.setIdadress(rs.getInt(1));
			address.setIduser(rs.getInt(2));
			address.setAddressline1(rs.getString(3));
			address.setAddressline2(rs.getString(4));
			address.setPin(rs.getInt(5));
			address.setCity(rs.getString(6));
			address.setState(rs.getString(7));
			address.setCountry(rs.getString(8));
			addresslist.add(address);
		}
		rs.close();

		if (logger.isDebugEnabled()) {
			logger.debug("selectAddress(int) - end"); //$NON-NLS-1$
		}
		return addresslist;
	}

}
