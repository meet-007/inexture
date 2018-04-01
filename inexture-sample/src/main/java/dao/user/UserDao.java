package dao.user;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public interface UserDao {
	final static String UPDATE = "update user set firstname=?,lastname=?,mobile=?,gender=?,dob=?,tech  = ? where iduser = ?;";
	final static String INSERT = "insert into user  (firstname,lastname,email,password,mobile,gender,dob,role,tech) values (?,?,?,?,?,?,?,?,?);";
	final static String SELECTUID = "select iduser from user where mobile = ? ;";
	final static String SELECTUSERFORLOGIN = "select * from user where email = ? and password = ?;";
	final static String SELECTALLUSER = "select * from user where role = ?";
	final static String SELECTUSER = "select * from user where iduser = ?";
	final static String SELECTUSERFRMEMAIL = "select * from user where email = ?";
	final static String DELETEUSER = "DELETE  from user WHERE iduser = ?";
	final static String UPDATEPASS = "update user set password = ? WHERE iduser = ?";

	boolean insert(User u, String operation) throws ClassNotFoundException, SQLException, IOException;

	// int selectUserId(long l) throws ClassNotFoundException, SQLException,
	// IOException;
	User selectUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException;

	ArrayList<User> selectAllUser(int role) throws ClassNotFoundException, SQLException, IOException;

	User selectUser(int iduser) throws ClassNotFoundException, SQLException, IOException;

	User selectUser(String email) throws ClassNotFoundException, SQLException, IOException;

	boolean updatePassword(User u) throws ClassNotFoundException, SQLException, IOException;

}
