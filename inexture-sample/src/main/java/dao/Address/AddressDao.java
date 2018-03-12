package dao.Address;

import java.io.IOException;
import java.sql.SQLException;

import model.Address;

public interface AddressDao {
	final static String INSERT = "insert into address (iduser,addressline1,addressline2,pin,city,state,country) values (?,?,?,?,?,?,?)";
	final static String SELECT = "select * from address where iduser = ?";

	int insertAddress(Address[] addrs) throws SQLException, ClassNotFoundException, IOException;
	Address[] selectAddress(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
