package service.user;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.User;

public interface UserService {
	String regesterUser(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException;
	User getUser(String email,String pass) throws ClassNotFoundException, SQLException, IOException;
}
