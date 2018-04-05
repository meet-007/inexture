package service.impl;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.impl.AddressDaoImpl;
import dao.interfaces.AddressDao;
import model.Address;
import service.interfaces.AddressService;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressServiceImpl.
 */
public class AddressServiceImpl implements AddressService {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class.getName());

	/**
	 * Sets the params.
	 *
	 * @param request the request
	 * @param iduser the iduser
	 * @return the array list
	 */
	public static ArrayList<Address> setParams(HttpServletRequest request, int iduser) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		String addressline1[] = request.getParameterValues("addressline1");
		String addressline2[] = request.getParameterValues("addressline2");
		String pin[] = request.getParameterValues("pin");
		String city[] = request.getParameterValues("city");
		String state[] = request.getParameterValues("state");
		String country[] = request.getParameterValues("country");
		String idaddress[] = request.getParameterValues("idaddress");
		ArrayList<Address> adrs = new ArrayList<>();
		for (int i = 0; i <  country.length; i++) {
			Address address = new Address();
			address.setAddressline1(addressline1[i]);
			address.setAddressline2(addressline2[i]);
			try {
				address.setPin(Integer.parseInt(pin[i])); // converting into integer type
			}catch(NumberFormatException e) {
				LOGGER.error("setParams(HttpServletRequest, int)", e); //$NON-NLS-1$
			}
			address.setCity(city[i]);
			address.setState(state[i]);
			address.setCountry(country[i]);
			address.setIduser(iduser);
			if (request.getParameter("idaddress") != null) {
				if (!idaddress[i].equals("x")) {
					address.setIdadress(Integer.parseInt(idaddress[i]));
				}
			}
			adrs.add(address);

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return adrs;
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#addAddress(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public boolean addAddress(HttpServletRequest request, int userid)

			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addAddress(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (request.getParameter("addressline1") != null) {
			AddressDao ado = new AddressDaoImpl();


			if (ado.insertAddress(AddressServiceImpl.setParams(request, userid), "insert")> 0) {

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
				}
				return true;
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addAddress(HttpServletRequest, int) - {}"+"no need to insert because no address found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#getUserAddress(int)
	 */
	@Override
	public ArrayList<Address> getUserAddress(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUserAddress(int) - start"); //$NON-NLS-1$
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUserAddress(int) - end"); //$NON-NLS-1$
		}
		return  new AddressDaoImpl().selectAddress(iduser);
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#updateAddress(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public boolean updateAddress(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateAddress(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (request.getParameter("addressline1") != null) {
			int rowsAffected = 0;
			ArrayList<Address> insertaddrs = new ArrayList<>();
			ArrayList<Address> updateaddrs = new ArrayList<>();
			ArrayList<Address> newaddresslist = AddressServiceImpl.setParams(request, userid);
			AddressDao ado = new AddressDaoImpl();
			ArrayList<Address> oldaddresslist = ado.selectAddress(userid);
			int flag = 0;
			for (Address newaddress : newaddresslist) {
				for (Address oldaddress : oldaddresslist) {
					if (oldaddress.getIdadress() == newaddress.getIdadress()) {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("updateAddress(HttpServletRequest, int) - {}"+ "update id" + newaddress.getIdadress()); //$NON-NLS-1$ //$NON-NLS-2$
						}
						updateaddrs.add(newaddress);
						oldaddresslist.remove(oldaddress);
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("updateAddress(HttpServletRequest, int) - {}"+"insert id" + newaddress.getIdadress()); //$NON-NLS-1$ //$NON-NLS-2$
					}
					insertaddrs.add(newaddress);
				}
				flag = 0;
			}
			if (oldaddresslist.size() > 0) {
				rowsAffected = ado.insertAddress(oldaddresslist, "delete");
			}

			if (updateaddrs.size() > 0) {
				rowsAffected += ado.insertAddress(updateaddrs, "update");
			}
			if (insertaddrs.size() > 0) {
				rowsAffected += ado.insertAddress(insertaddrs, "insert");
			}
			if (rowsAffected > 0) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("updateAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
				}
				return true;
			} else {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("updateAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
				}
				return false;
			}
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("updateAddress(HttpServletRequest, int) - {}"+"no need to update because no address found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("updateAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
	}

}
