package service.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import model.LangTransact;
import model.User;
import service.Address.AddressService;
import service.Address.AddressServiceImpl;
import service.Image.ImageService;
import service.Image.ImageServiceImpl;
import service.LangTransaction.*;

public class UserServiceImp implements UserService {
	String response = ""; // response message
	public static User setParams(HttpServletRequest req) throws ParseException {
		/* getting parameters from request object */
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String tech = req.getParameter("tech");
		// String[] lang= req.getParameterValues("lang");
		String role = req.getParameter("role");
		/* setting data into user object */
		User user = new User();
		user.setFirstname(fname);
		user.setLastname(lname);
		user.setEmail(email);
		user.setPassword(pass);
		user.setMobile(Long.parseLong(mobile));
		user.setGender(Integer.parseInt(gender));
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
		user.setDob(date1);
		user.setRole(2);
		user.setTech(Integer.parseInt(tech));
		return user;
	}
	
	public String regesterUser(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException {
		// TODO Auto-generated method stub
	
		
		User user = UserServiceImp.setParams(req);
		
		int userid = 0;
		UserDao userdao = new UserDaoImpl(); // creating dao object
		if (!userdao.insert(user,"insert")) { // calling dao method
			userid = userdao.selectUserId(user.getMobile());
			AddressService as = new AddressServiceImpl();
			if (as.addAddress(req, userid)) {
				LangTransServ its = new LangTransImpl();
				if (its.addLangTransaction(req, userid)) {
					ImageService is = new ImageServiceImpl();
					if(is.SaveImage(req, userid))
						response = "Registration successfull";
				}
			}else
				response = "Registration unsuccessfull";
		}return response;
}

	public User getUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDaoImpl();
		
		return userdao.selectUser(email,pass);
	}

	public String updateUser(HttpServletRequest req, int iduser) throws ClassNotFoundException, SQLException, IOException, ParseException , ServletException {
		// TODO Auto-generated method stub
		User user = UserServiceImp.setParams(req);
		user.setIduser(iduser);
		UserDao userdao = new UserDaoImpl();
		userdao.insert(user,"update");
			LangTransServ lts = new LangTransImpl();
		lts.updateLangTransaction(req, iduser);
			AddressService as = new AddressServiceImpl();
			as.updateAddress(req, iduser);
			response = "update successfull";
			ImageService is = new ImageServiceImpl();
			is.UpdateImage(req, iduser);
			
		
		return response;
	}
}
