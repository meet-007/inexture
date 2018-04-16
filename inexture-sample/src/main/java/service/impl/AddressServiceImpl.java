package service.impl;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public static List<Address> setParams(final HttpServletRequest request, final int iduser) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}
		final ArrayList<Address> adrs = new ArrayList<>();

		final String addressline1[] = request.getParameterValues("addressline1");
		final String addressline2[] = request.getParameterValues("addressline2");
		final String pin[] = request.getParameterValues("pin");
		final String city[] = request.getParameterValues("city");
		final String state[] = request.getParameterValues("state");
		final String country[] = request.getParameterValues("country");
		final String idaddress[] = request.getParameterValues("idaddress");
		final int elementLength = Integer.parseInt(request.getParameter("czContainer_czMore_txtCount"));
		if (elementLength>0) {
			if((addressline1 != null) && (addressline2 != null) && (pin != null)  && (city != null) &&(state != null) &&(country != null)){
				if ((elementLength == addressline1.length) && (elementLength == addressline2.length) && (elementLength == pin.length) && (elementLength == city.length) &&(elementLength == state.length) &&(elementLength == country.length)) {

					for (int i = 0; i <  country.length; i++) {
						final Address address = new Address();
						address.setAddressline1(addressline1[i]);
						address.setAddressline2(addressline2[i]);
						try {
							address.setPin(Integer.parseInt(pin[i])); // converting into integer type
						}catch(final NumberFormatException e) {
							LOGGER.error("setParams(HttpServletRequest, int)", e); //$NON-NLS-1$
						}
						address.setCity(city[i]);
						address.setState(state[i]);
						address.setCountry(country[i]);
						address.setIduser(iduser);
						if ((request.getParameter("idaddress") != null)&&!idaddress[i].equals("x")) {
							address.setIdadress(Integer.parseInt(idaddress[i]));
						}
						adrs.add(address);

					}
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
					}
				}
			}
		}
		return adrs;
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#addAddress(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public boolean addAddress(final HttpServletRequest request, final int userid)

			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addAddress(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (request.getParameter("addressline1") == null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addAddress(HttpServletRequest, int) - {}"+"no need to insert because no address found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;

		} else {

			if (new AddressDaoImpl().insertAddress(AddressServiceImpl.setParams(request, userid), "insert")> 0) {

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
				}
				return true;
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;

		}
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#getUserAddress(int)
	 */
	@Override
	public ArrayList<Address> getUserAddress(final int iduser) throws ClassNotFoundException, SQLException, IOException {
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
	public boolean updateAddress(final HttpServletRequest request, final int userid)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateAddress(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		int rowsAffected = 0;
		final ArrayList<Address> insertaddrs = new ArrayList<>();
		final ArrayList<Address> updateaddrs = new ArrayList<>();
		ArrayList<Address> newaddresslist = new ArrayList<>();
		if (request.getParameter("addressline1") != null) {
			newaddresslist = (ArrayList<Address>) AddressServiceImpl.setParams(request, userid);
		}
		final AddressDao ado = new AddressDaoImpl();
		final ArrayList<Address> oldaddresslist = (ArrayList<Address>)ado.selectAddress(userid);
		int flag = 0;
		for (final Address newaddress : newaddresslist) {
			for (final Address oldaddress : oldaddresslist) {
				if (oldaddress.getIdadress() == newaddress.getIdadress()) {
					updateaddrs.add(newaddress);
					oldaddresslist.remove(oldaddress);
					flag = 1;
					break;
				}
			}
			if (flag == 0) {

				insertaddrs.add(newaddress);
			}
			flag = 0;
		}
		if (!oldaddresslist.isEmpty()) {
			rowsAffected = ado.insertAddress(oldaddresslist, "delete");
		}

		if (!updateaddrs.isEmpty()) {
			rowsAffected += ado.insertAddress(updateaddrs, "update");
		}
		if (!insertaddrs.isEmpty()) {
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

	}

}
