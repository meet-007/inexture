package dao.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;


public interface UserDao {
final static String UPDATE = "update user set firstname=?,lastname=?,email=?,password=?,mobile=?,gender=?,dob=?,role = ?,tech  = ? where iduser = ?;";	
final static String INSERT = "insert into user  (firstname,lastname,email,password,mobile,gender,dob,role,tech) values (?,?,?,?,?,?,?,?,?);";
final static String SELECTUID = "select iduser from user where mobile = ? ;";
final static String SELECTUSERFORLOGIN = "select * from user where email = ? and password = ?;";
final static String SELECTALLUSER = "select * from user where role = ?";
boolean insert(User u,String operation) throws ClassNotFoundException, SQLException, IOException;
int selectUserId(long l) throws ClassNotFoundException, SQLException, IOException;
User selectUser( String email,String pass) throws ClassNotFoundException, SQLException, IOException;
ArrayList<User> selectAllUser(int role) throws ClassNotFoundException, SQLException, IOException;
}
