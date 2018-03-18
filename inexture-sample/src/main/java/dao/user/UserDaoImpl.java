package dao.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;

import model.User;
import util.DbUtil;

public class UserDaoImpl implements UserDao {

	public boolean insert(User u, String operation) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ArrayList arr = new ArrayList();
		arr.add(u.getFirstname());
		arr.add(u.getLastname());
		arr.add(u.getEmail());
		arr.add(u.getPassword());
		arr.add(u.getMobile());
		arr.add(u.getGender());
		arr.add(u.getDob());
		arr.add(u.getRole());
		arr.add(u.getTech());
		boolean result = true;
		if (operation.equals("insert")) {
			result = DbUtil.dbOperationInsert(INSERT, arr);
		} else {
			arr.add(u.getIduser());
			result = DbUtil.dbOperationInsert(UPDATE, arr);
		}
		return result;
	}

	public int selectUserId(long mobile) throws ClassNotFoundException, SQLException, IOException {
		int userId = 0;
		ResultSet rs = DbUtil.dbOperationSelect(SELECTUID, mobile);
		while (rs.next()) {

			userId = rs.getInt(1);

		}
		return userId;
	}

	public User selectUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException {
		int userid = -1;
		ResultSet rs = DbUtil.dbOperationSelect(SELECTUSERFORLOGIN, email, pass);
		User u = null;
		while (rs.next()) {
			u = new User();
			u.setIduser(rs.getInt(1));
			u.setFirstname(rs.getString(2));
			u.setLastname(rs.getString(3));
			u.setEmail(rs.getString(4));
			u.setPassword(rs.getString(5));
			u.setMobile(rs.getLong(6));
			u.setGender(rs.getInt(7));
			Date d = new Date(rs.getDate(8).getTime());
			u.setDob(d);
			u.setRole(rs.getInt(9));
			u.setTech(rs.getInt(10));

		}
		return u;
	}

	public ArrayList<User> selectAllUser(int role) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs = DbUtil.dbOperationSelect(SELECTALLUSER, role);
		ArrayList<User> users = new ArrayList<User>();
		User u = null;
		while (rs.next()) {
			u = new User();
			u.setIduser(rs.getInt(1));
			u.setFirstname(rs.getString(2));
			u.setLastname(rs.getString(3));
			u.setEmail(rs.getString(4));
			u.setPassword(rs.getString(5));
			u.setMobile(rs.getLong(6));
			u.setGender(rs.getInt(7));
			Date d = new Date(rs.getDate(8).getTime());
			u.setDob(d);
			u.setRole(rs.getInt(9));
			u.setTech(rs.getInt(10));
			users.add(u);
		}
		return users;
	}
}
