package service.Address;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface AddressService {
	boolean addAddress(HttpServletRequest request , int userid) throws ClassNotFoundException, SQLException, IOException;
}
