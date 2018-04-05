package service.impl;




import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.impl.UserDaoImpl;
import dao.interfaces.UserDao;
import model.User;
import service.interfaces.AddressService;
import service.interfaces.ImageService;
import service.interfaces.LangTransServ;
import service.interfaces.UserService;
import util.AESCrypt;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImp.
 */
public class UserServiceImp implements UserService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImp.class.getName());

	/** The response. */
	String response = ""; // response message

	/**
	 * Sets the params.
	 *
	 * @param req the req
	 * @return the user
	 * @throws ParseException
	 * @throws Exception the exception
	 */
	public static User setParams(HttpServletRequest req) throws ParseException   {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		/* getting parameters from request object */

		// String[] lang= req.getParameterValues("lang");
		/* setting data into user object */
		final User user = new User();
		user.setFirstname(req.getParameter("fname"));
		user.setLastname(req.getParameter("lname"));
		user.setEmail(req.getParameter("email"));

		user.setPassword(req.getParameter("pass"));

		try {
			user.setMobile(Long.parseLong(req.getParameter("mobile")));
		}catch(final NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			user.setGender(Integer.parseInt(req.getParameter("gender")));
		}catch(final NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			user.setDob( new SimpleDateFormat("yyyy-MM-dd").parse( req.getParameter("dob")));
		}catch(final NumberFormatException e) {
			e.printStackTrace();
		}

		user.setRole(2);
		try {
			user.setTech(Integer.parseInt(req.getParameter("tech")));
		}catch(final NumberFormatException e) {
			e.printStackTrace();
		}


		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#regesterUser(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String regesterUser(HttpServletRequest req) throws ParseException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, ClassNotFoundException, SQLException, IOException, ServletException   {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("regesterUser(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		User user = UserServiceImp.setParams(req);

		user.setPassword(AESCrypt.encrypt(user.getPassword()));
		final UserDao userdao = new UserDaoImpl(); // creating dao object
		if (!userdao.insert(user, "insert")) { // calling dao method
			user = userdao.selectUser(user.getEmail());
			final AddressService as = new AddressServiceImpl();
			if (as.addAddress(req, user.getIduser())) {
				final LangTransServ its = new LangTransImpl();
				if (its.addLangTransaction(req, user.getIduser())) {
					final ImageService is = new ImageServiceImpl();
					if (is.saveImage(req, user.getIduser())) {
						response = "Registration successfull";
					}
				}
			} else {
				response = "Registration unsuccessfull";
			}
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("regesterUser(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User getUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUser(String, String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final UserDao userdao = new UserDaoImpl();
		final User returnUser = userdao.selectUser(email, pass);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUser(String, String) - end"); //$NON-NLS-1$
		}
		return returnUser;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#updateUser(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public String updateUser(HttpServletRequest req, int iduser) throws ParseException, ClassNotFoundException, SQLException, IOException, ServletException   {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateUser(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final User user = UserServiceImp.setParams(req);
		user.setIduser(iduser);
		final UserDao userdao = new UserDaoImpl();
		userdao.insert(user, "update");
		final LangTransServ lts = new LangTransImpl();
		lts.updateLangTransaction(req, iduser);
		final AddressService as = new AddressServiceImpl();
		as.updateAddress(req, iduser);
		response = "update successfull";
		final ImageService is = new ImageServiceImpl();
		is.updateImage(req, iduser);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateUser(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getAllUser(int)
	 */
	@Override
	public ArrayList<User> getAllUser(int role) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAllUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ArrayList<User> returnArrayList = new dao.impl.UserDaoImpl().selectAllUser(role);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAllUser(int) - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getUser(int)
	 */
	@Override
	public User getUser(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUser(int) - start"); //$NON-NLS-1$
		}

		final UserDao userdao = new UserDaoImpl();
		final User returnUser = userdao.selectUser(iduser);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUser(int) - end"); //$NON-NLS-1$
		}
		return returnUser;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int iduser)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deleteUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final User u = new User();
		u.setIduser(iduser);

		final boolean returnboolean = new UserDaoImpl().insert(u, "delete");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deleteUser(int) - end"); //$NON-NLS-1$
		}
		return returnboolean;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#updatePass(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updatePass(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException   {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updatePass(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		String resp = "";
		User u = null;
		final UserDao udao = new UserDaoImpl();
		if ((req.getParameter("email") != null) && (req.getParameter("password") != null)) {
			final String email = req.getParameter("email");
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
			final HttpSession session = req.getSession();
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

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updatePass(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#checkUserExist(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean checkUserExist(HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("checkUserExist(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final String email = req.getParameter("email");
		final User u = new UserDaoImpl().selectUser(email);
		if (u != null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("checkUserExist(HttpServletRequest) - end"); //$NON-NLS-1$
			}
			return true;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("checkUserExist(HttpServletRequest) - end"); //$NON-NLS-1$
		}
		return false;
	}
}
