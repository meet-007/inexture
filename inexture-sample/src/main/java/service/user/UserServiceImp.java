package service.user;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import model.User;
import service.Address.AddressService;
import service.Address.AddressServiceImpl;
import service.Image.ImageService;
import service.Image.ImageServiceImpl;
import service.LangTransaction.LangTransImpl;
import service.LangTransaction.LangTransServ;
import util.AESCrypt;

public class UserServiceImp implements UserService {
	String response = ""; // response message

	public static User setParams(HttpServletRequest req) throws Exception {
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

	public String regesterUser(HttpServletRequest req)
			throws Exception {
		// TODO Auto-generated method stub

		User user = UserServiceImp.setParams(req);

		user.setPassword(AESCrypt.encrypt(user.getPassword()));
		UserDao userdao = new UserDaoImpl(); // creating dao object
		if (!userdao.insert(user, "insert")) { // calling dao method
			user = userdao.selectUser(user.getEmail());
			AddressService as = new AddressServiceImpl();
			if (as.addAddress(req, user.getIduser())) {
				LangTransServ its = new LangTransImpl();
				if (its.addLangTransaction(req, user.getIduser())) {
					ImageService is = new ImageServiceImpl();
					if (is.SaveImage(req, user.getIduser())) {
						response = "Registration successfull";
					}
				}
			} else {
				response = "Registration unsuccessfull";
			}
		}
		return response;
	}

	public User getUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDaoImpl();
		return userdao.selectUser(email, pass);
	}

	public String updateUser(HttpServletRequest req, int iduser)
			throws Exception {
		// TODO Auto-generated method stub
		User user = UserServiceImp.setParams(req);
		user.setIduser(iduser);
		UserDao userdao = new UserDaoImpl();
		userdao.insert(user, "update");
		LangTransServ lts = new LangTransImpl();
		lts.updateLangTransaction(req, iduser);
		AddressService as = new AddressServiceImpl();
		as.updateAddress(req, iduser);
		response = "update successfull";
		ImageService is = new ImageServiceImpl();
		is.UpdateImage(req, iduser);

		return response;
	}

	public ArrayList<User> getAllUser(int role) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		return new dao.user.UserDaoImpl().selectAllUser(role);
	}

	public User getUser(int iduser) throws ClassNotFoundException, SQLException, IOException {
		UserDao userdao = new UserDaoImpl();
		return userdao.selectUser(iduser);
	}

	public boolean deleteUser(int iduser)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException {
		// TODO Auto-generated method stub
		User u = new User();
		u.setIduser(iduser);
		String msg = "";

		return new UserDaoImpl().insert(u, "delete");
	}

	public String updatePass(HttpServletRequest req)
			throws Exception {
		// TODO Auto-generated method stub
		String resp = "";
		User u = null;
		UserDao udao = new UserDaoImpl();
		if ((req.getParameter("email") != null) && (req.getParameter("password") != null)) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			u = udao.selectUser(email);
			if (u != null) {
				password =	AESCrypt.encrypt(password);
				u.setPassword(password);
				if (!udao.updatePassword(u)) {
					resp = "password updated";
				}
			} else {
				resp = "user not found ! please try again";
			}
		} else {
			HttpSession session = req.getSession();
			u = (User)session.getAttribute("user");
			if (u.getPassword().equals(AESCrypt.encrypt(req.getParameter("oldpass")))) {
				u.setPassword(req.getParameter("newpass"));
				if (!udao.updatePassword(u)) {
					resp = "password updated";
				}
			}else {
				resp = "user not found ! please try again";
			}
		}

		return resp;
	}


	public boolean checkUserExist(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		User u = new UserDaoImpl().selectUser(email);
		if(u!=null) {
			return true;
		}
		return false;
	}
}
