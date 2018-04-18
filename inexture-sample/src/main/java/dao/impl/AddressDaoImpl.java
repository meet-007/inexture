package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.AddressDao;
import model.Address;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressDaoImpl.
 */
@SuppressWarnings("PMD")
public class AddressDaoImpl implements AddressDao {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(AddressDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Address.AddressDao#insertAddress(java.util.ArrayList, java.lang.String)
	 */
	@Override
	public int insertAddress(final List<Address> addressList,final String operation)
			throws SQLException, ClassNotFoundException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("insertAddress(ArrayList<Address>, String) - start"); //$NON-NLS-1$
		}
		final ArrayList<Object> arr = new ArrayList<>();
		int count=0;
		for (int i = 0; i < addressList.size(); i++) {
			arr.add(addressList.get(i).getIduser());
			arr.add(addressList.get(i).getAddressline1());
			arr.add(addressList.get(i).getAddressline2());
			arr.add(addressList.get(i).getPin());
			arr.add(addressList.get(i).getCity());
			arr.add(addressList.get(i).getState());
			arr.add(addressList.get(i).getCountry());
			if ("insert".equals(operation)) {
				if (!DbUtil.dbOperationInsert(INSERT, arr)) {
					count++;
				}
			} else if ("update".equals(operation)) {
				arr.add(addressList.get(i).getIdadress());
				if (!DbUtil.dbOperationInsert(UPDATE, arr)) {
					count++;
				}
			} else {
				arr.clear();
				arr.add(addressList.get(i).getIdadress());
				if (!DbUtil.dbOperationInsert(DELETE, arr)) {
					count++;
				}
			}
			arr.clear();
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("insertAddress(ArrayList<Address>, String) - end"); //$NON-NLS-1$
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.Address.AddressDao#selectAddress(int)
	 */
	@Override
	public ArrayList<Address> selectAddress(final int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectAddress(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		try(final ResultSet resultSet = DbUtil.dbOperationSelect(SELECT, iduser)){
			final ArrayList<Address> addresslist = new ArrayList<>();

			while (resultSet.next()) {
				final Address address = new Address();
				address.setIdadress(resultSet.getInt(1));
				address.setIduser(resultSet.getInt(2));
				address.setAddressline1(resultSet.getString(3));
				address.setAddressline2(resultSet.getString(4));
				address.setPin(resultSet.getInt(5));
				address.setCity(resultSet.getString(6));
				address.setState(resultSet.getString(7));
				address.setCountry(resultSet.getString(8));
				addresslist.add(address);
			}
			resultSet.close();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("selectAddress(int) - end"); //$NON-NLS-1$
			}
			return addresslist;
		}
	}

}
