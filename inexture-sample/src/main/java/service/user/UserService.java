package service.user;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.User;

public interface UserService {
	String regesterUser(HttpServletRequest req)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException, Exception;

	User getUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException;

	String updateUser(HttpServletRequest req, int idUser)
			throws ClassNotFoundException, ParseException, SQLException, IOException, ServletException, Exception;

	ArrayList<User> getAllUser(int role) throws ClassNotFoundException, SQLException, IOException;

	User getUser(int iduser) throws ClassNotFoundException, SQLException, IOException;

	boolean deleteUser(int iduser)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException;

	String updatePass(HttpServletRequest req)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException, Exception;

	boolean checkUserExist(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException;
}
