package service.Address;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.Address.AddressDao;
import dao.Address.AddressDaoImpl;
import model.Address;

public class AddressServiceImpl implements AddressService {

	public boolean addAddress(HttpServletRequest request, int userid)

			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String addressline1[] = request.getParameterValues("addressline1");
		String addressline2[] = request.getParameterValues("addressline2");
		String pin[] = request.getParameterValues("pin");
		String city[] = request.getParameterValues("city");
		String state[] = request.getParameterValues("state");
		String country[] = request.getParameterValues("country");
		int noOfAddressDetails = country.length;
		Address adrs[] = new Address[noOfAddressDetails];
		for (int i = 0; i < noOfAddressDetails; i++) {
		    adrs[i] = new Address();
			adrs[i].setAddressline1(addressline1[i]);
			adrs[i].setAddressline2(addressline2[i]);
			adrs[i].setPin(Integer.parseInt(pin[i])); // converting into integer type
			adrs[i].setCity(city[i]);
			adrs[i].setState(state[i]);
			adrs[i].setCountry(country[i]);
			adrs[i].setIduser(userid);
		}
		AddressDao ado = new AddressDaoImpl();
		int rowCount = ado.insertAddress(adrs);
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	public Address[] getUserAddress(int iduser) throws ClassNotFoundException, SQLException, IOException {
		AddressDao ado = new AddressDaoImpl();
		return ado.selectAddress(iduser);
	}
	
}
