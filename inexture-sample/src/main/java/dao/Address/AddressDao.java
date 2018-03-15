package dao.Address;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Address;

public interface AddressDao {
	final static String INSERT = "insert into address (iduser,addressline1,addressline2,pin,city,state,country) values (?,?,?,?,?,?,?)";
	final static String SELECT = "select * from address where iduser = ?";
	final static String UPDATE = "update address set iduser=?, addressline1 = ? ,addressline2 = ?,pin = ?,city = ?,state = ?,country = ? where idaddress = ?";
	final static String DELETE = "delete from address where idaddress=?";
	int insertAddress(ArrayList<Address> it,String operation) throws SQLException, ClassNotFoundException, IOException;
	ArrayList<Address> selectAddress(int iduser) throws ClassNotFoundException, SQLException, IOException;
	
}
