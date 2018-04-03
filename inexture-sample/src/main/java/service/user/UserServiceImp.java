package service.user;




import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImp.
 */
public class UserServiceImp implements UserService {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(UserServiceImp.class.getName());

	/** The response. */
	String response = ""; // response message

	/**
	 * Sets the params.
	 *
	 * @param req the req
	 * @return the user
	 * @throws Exception the exception
	 */
	public static User setParams(HttpServletRequest req) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		/* getting parameters from request object */

		// String[] lang= req.getParameterValues("lang");
		/* setting data into user object */
		User user = new User();
		user.setFirstname(req.getParameter("fname"));
		user.setLastname(req.getParameter("lname"));
		user.setEmail(req.getParameter("email"));

		user.setPassword(req.getParameter("pass"));

		try {
			user.setMobile(Long.parseLong(req.getParameter("mobile")));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			user.setGender(Integer.parseInt(req.getParameter("gender")));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			user.setDob( new SimpleDateFormat("yyyy-MM-dd").parse( req.getParameter("dob")));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}

		user.setRole(2);
		try {
			user.setTech(Integer.parseInt(req.getParameter("tech")));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}


		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#regesterUser(javax.servlet.http.HttpServletRequest)
	 */
	public String regesterUser(HttpServletRequest req) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("regesterUser(HttpServletRequest) - start"); //$NON-NLS-1$
		}

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

		if (logger.isDebugEnabled()) {
			logger.debug("regesterUser(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getUser(java.lang.String, java.lang.String)
	 */
	public User getUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUser(String, String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		UserDao userdao = new UserDaoImpl();
		User returnUser = userdao.selectUser(email, pass);
		if (logger.isDebugEnabled()) {
			logger.debug("getUser(String, String) - end"); //$NON-NLS-1$
		}
		return returnUser;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#updateUser(javax.servlet.http.HttpServletRequest, int)
	 */
	public String updateUser(HttpServletRequest req, int iduser) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("updateUser(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

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

		if (logger.isDebugEnabled()) {
			logger.debug("updateUser(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getAllUser(int)
	 */
	public ArrayList<User> getAllUser(int role) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ArrayList<User> returnArrayList = new dao.user.UserDaoImpl().selectAllUser(role);
		if (logger.isDebugEnabled()) {
			logger.debug("getAllUser(int) - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getUser(int)
	 */
	public User getUser(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUser(int) - start"); //$NON-NLS-1$
		}

		UserDao userdao = new UserDaoImpl();
		User returnUser = userdao.selectUser(iduser);
		if (logger.isDebugEnabled()) {
			logger.debug("getUser(int) - end"); //$NON-NLS-1$
		}
		return returnUser;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#deleteUser(int)
	 */
	public boolean deleteUser(int iduser)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		User u = new User();
		u.setIduser(iduser);

		boolean returnboolean = new UserDaoImpl().insert(u, "delete");
		if (logger.isDebugEnabled()) {
			logger.debug("deleteUser(int) - end"); //$NON-NLS-1$
		}
		return returnboolean;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#updatePass(javax.servlet.http.HttpServletRequest)
	 */
	public String updatePass(HttpServletRequest req) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("updatePass(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		String resp = "";
		User u = null;
		UserDao udao = new UserDaoImpl();
		if ((req.getParameter("email") != null) && (req.getParameter("password") != null)) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			u = udao.selectUser(email);
			if (u != null) {
				password = AESCrypt.encrypt(password);
				u.setPassword(password);
				if (!udao.updatePassword(u)) {
					resp = "password updated";
				}
			} else {
				resp = "user not found ! please try again";
			}
		} else {
			HttpSession session = req.getSession();
			u = (User) session.getAttribute("user");
			if (u.getPassword().equals(AESCrypt.encrypt(req.getParameter("oldpass")))) {
				u.setPassword(AESCrypt.encrypt(req.getParameter("newpass")));

				if (!udao.updatePassword(u)) {
					resp = "password updated";
				}
			} else {
				resp = "user not found ! please try again";
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("updatePass(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#checkUserExist(javax.servlet.http.HttpServletRequest)
	 */
	public boolean checkUserExist(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("checkUserExist(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		User u = new UserDaoImpl().selectUser(email);
		if (u != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("checkUserExist(HttpServletRequest) - end"); //$NON-NLS-1$
			}
			return true;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("checkUserExist(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return false;
	}
}
