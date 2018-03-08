package dao.Address;

import java.io.IOException;
import java.sql.SQLException;

import model.Address;

public interface AddressDao {
	final static String INSERT = "insert into address (iduser,addressline1,addressline2,pin,city,state,country) values (?,?,?,?,?,?,?)";
	int insertAddress(Address[] addrs) throws SQLException, ClassNotFoundException, IOException;
}
