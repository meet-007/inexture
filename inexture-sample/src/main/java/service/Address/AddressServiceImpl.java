package service.Address;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Address.AddressDao;
import dao.Address.AddressDaoImpl;
import model.Address;
import util.DbUtil;

public class AddressServiceImpl implements AddressService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class.getName());

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
		int noOfAddressDetails = country.length;
		ArrayList<Address> adrs = new ArrayList<Address>();
		// if (noOfAddressDetails > 0) {

		for (int i = 0; i < noOfAddressDetails; i++) {
			Address address = new Address();
			address.setAddressline1(addressline1[i]);
			address.setAddressline2(addressline2[i]);
			address.setPin(Integer.parseInt(pin[i])); // converting into integer type
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
		// }else {
		// adrs = null;
		// }

		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return adrs;
	}

	public boolean addAddress(HttpServletRequest request, int userid)

			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("addAddress(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		if (request.getParameter("addressline1") != null) {
			AddressDao ado = new AddressDaoImpl();
			ArrayList<Address> adrslist = null;
			adrslist = AddressServiceImpl.setParams(request, userid);
			int rowCount = 0;
			rowCount = ado.insertAddress(adrslist, "insert");
			if (rowCount > 0) {
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
			System.out.println(
					"no need to insert because no address found from user side" + "--------" + "returning true");

			if (logger.isDebugEnabled()) {
				logger.debug("addAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
	}

	public ArrayList<Address> getUserAddress(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserAddress(int) - start"); //$NON-NLS-1$
		}

		AddressDao ado = new AddressDaoImpl();
		ArrayList<Address> returnArrayList = ado.selectAddress(iduser);
		if (logger.isDebugEnabled()) {
			logger.debug("getUserAddress(int) - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

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
						System.out.println("update id" + newaddress.getIdadress());
						updateaddrs.add(newaddress);
						oldaddresslist.remove(oldaddress);
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					System.out.println("insert id" + newaddress.getIdadress());
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
			System.out.println(
					"no need to update because no address found from user side" + "--------" + "returning true");

			if (logger.isDebugEnabled()) {
				logger.debug("updateAddress(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		}
	}

}
