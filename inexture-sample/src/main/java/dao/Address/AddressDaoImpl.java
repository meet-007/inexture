package dao.Address;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Address;
import util.DbUtil;

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

	public Address[] selectAddress(int iduser) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs = DbUtil.dbOperationSelect(SELECT, iduser);
		rs.last();
		Address[] adress = new Address[rs.getRow()];
		rs.beforeFirst();
		int i=0;
		while(rs.next()) {
			adress[i]=new Address();
			adress[i].setIdadress(rs.getInt(1));
			adress[i].setIduser(rs.getInt(2));
			adress[i].setAddressline1(rs.getString(3));
			adress[i].setAddressline2(rs.getString(4));
			adress[i].setPin(rs.getInt(5));
			adress[i].setCity(rs.getString(6));
			adress[i].setState(rs.getString(7));
			adress[i].setCountry(rs.getString(8));
			i++;
		}
		rs.close();
		return adress;
	}

}
