package dao.Address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Address;

public class AddressDaoImpl implements AddressDao {

	public int insertAddress(Address[] addrs) throws SQLException, ClassNotFoundException, IOException {
		Connection con = util.DbUtil.getConnection();
		PreparedStatement pst = pst = con.prepareStatement(INSERT);
		for (int i = 0; i < addrs.length; i++) {
			
			pst.setInt(1, addrs[i].getIduser());
			pst.setString(2, addrs[i].getAddressline1());
			pst.setString(3, addrs[i].getAddressline2());
			pst.setInt(4, addrs[i].getPin());
			pst.setString(5, addrs[i].getCity());
			pst.setString(6, addrs[i].getState());
			pst.setString(7, addrs[i].getCountry());
			pst.addBatch();
		}
		int[] rowaffected =  pst.executeBatch();
		return rowaffected.length;
	}

}
