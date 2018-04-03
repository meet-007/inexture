package service.Address;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.Address.AddressDao;
import dao.Address.AddressDaoImpl;
import model.Address;

// TODO: Auto-generated Javadoc
/**
 * The Class AddressServiceImpl.
 */
public class AddressServiceImpl implements AddressService {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class.getName());

	/**
	 * Sets the params.
	 *
	 * @param request the request
	 * @param iduser the iduser
	 * @return the array list
	 */
	public static ArrayList<Address> setParams(HttpServletRequest request, int iduser) {
		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		String addressline1[] = request.getParameterValues("addressline1");
		String addressline2[] = request.getParameterValues("addressline2");
		String pin[] = request.getParameterValues("pin");
		String city[] = request.getParameterValues("city");
		String state[] = request.getParameterValues("state");
		String country[] = request.getParameterValues("country");
		String idaddress[] = request.getParameterValues("idaddress");
		ArrayList<Address> adrs = new ArrayList<Address>();
		for (int i = 0; i <  country.length; i++) {
			Address address = new Address();
			address.setAddressline1(addressline1[i]);
			address.setAddressline2(addressline2[i]);
			try {
				address.setPin(Integer.parseInt(pin[i])); // converting into integer type
			}catch(NumberFormatException e) {
				logger.error("setParams(HttpServletRequest, int)", e); //$NON-NLS-1$
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
		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return adrs;
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#addAddress(javax.servlet.http.HttpServletRequest, int)
	 */
	public boolean addAddress(HttpServletRequest request, int userid)

			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("addAddress(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (request.getParameter("addressline1") != null) {
			AddressDao ado = new AddressDaoImpl();


			if (ado.insertAddress(AddressServiceImpl.setParams(request, userid), "insert")> 0) {

				if (logger.isDebugEnabled()) {
					logger.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
				}
				return true;
			}

			if (logger.isDebugEnabled()) {
				logger.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("addAddress(HttpServletRequest, int) - {}"+"no need to insert because no address found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (logger.isDebugEnabled()) {
				logger.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#getUserAddress(int)
	 */
	public ArrayList<Address> getUserAddress(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserAddress(int) - start"); //$NON-NLS-1$
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getUserAddress(int) - end"); //$NON-NLS-1$
		}
		return  new AddressDaoImpl().selectAddress(iduser);
	}

	/* (non-Javadoc)
	 * @see service.Address.AddressService#updateAddress(javax.servlet.http.HttpServletRequest, int)
	 */
	public boolean updateAddress(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("updateAddress(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (request.getParameter("addressline1") != null) {
			int rowsAffected = 0;
			ArrayList<Address> insertaddrs = new ArrayList<Address>();
			ArrayList<Address> updateaddrs = new ArrayList<Address>();
			ArrayList<Address> newaddresslist = AddressServiceImpl.setParams(request, userid);
			AddressDao ado = new AddressDaoImpl();
			ArrayList<Address> oldaddresslist = ado.selectAddress(userid);
			int flag = 0;
			for (Address newaddress : newaddresslist) {
				for (Address oldaddress : oldaddresslist) {
					if (oldaddress.getIdadress() == newaddress.getIdadress()) {
						if (logger.isDebugEnabled()) {
							logger.debug("updateAddress(HttpServletRequest, int) - {}"+ "update id" + newaddress.getIdadress()); //$NON-NLS-1$ //$NON-NLS-2$
						}
						updateaddrs.add(newaddress);
						oldaddresslist.remove(oldaddress);
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					if (logger.isDebugEnabled()) {
						logger.debug("updateAddress(HttpServletRequest, int) - {}"+"insert id" + newaddress.getIdadress()); //$NON-NLS-1$ //$NON-NLS-2$
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
				if (logger.isDebugEnabled()) {
					logger.debug("updateAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
				}
				return true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("updateAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
				}
				return false;
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("updateAddress(HttpServletRequest, int) - {}"+"no need to update because no address found from user side--------returning true"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (logger.isDebugEnabled()) {
				logger.debug("updateAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
	}

}
