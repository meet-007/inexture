package service.user;

import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import model.User;

public interface UserService {
	String regesterUser(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException;
	User getUser(String email,String pass) throws ClassNotFoundException, SQLException, IOException;
	String updateUser(HttpServletRequest req,int idUser) throws ClassNotFoundException,ParseException,SQLException,IOException, ServletException;
	ArrayList<User> getAllUser(int role) throws ClassNotFoundException, SQLException, IOException;
}
