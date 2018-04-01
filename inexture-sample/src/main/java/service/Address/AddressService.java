package service.Address;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.Address;

public interface AddressService {
	boolean addAddress(HttpServletRequest request, int userid) throws ClassNotFoundException, SQLException, IOException;

	ArrayList<Address> getUserAddress(int iduser) throws ClassNotFoundException, SQLException, IOException;

	boolean updateAddress(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException;

}
