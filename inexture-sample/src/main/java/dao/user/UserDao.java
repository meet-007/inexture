package dao.user;

import java.io.IOException;
import java.sql.SQLException;

import model.User;


public interface UserDao {
final static String INSERT = "insert into user  (firstname,lastname,email,password,mobile,gender,dob,role,tech) values (?,?,?,?,?,?,?,?,?);";
final static String SELECTUID = "select iduser from user where mobile = ? ;";
final static String SELECTUSERFORLOGIN = "select * from user where email = ? and password = ?;";
boolean insert(User u) throws ClassNotFoundException, SQLException, IOException;
int selectUserId(long l) throws ClassNotFoundException, SQLException, IOException;
User selectUser( String email,String pass) throws ClassNotFoundException, SQLException, IOException;
}
