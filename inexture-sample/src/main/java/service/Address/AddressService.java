package service.Address;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import model.Address;

public interface AddressService {
	boolean addAddress(HttpServletRequest request , int userid) throws ClassNotFoundException, SQLException, IOException;
	Address[] getUserAddress(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
