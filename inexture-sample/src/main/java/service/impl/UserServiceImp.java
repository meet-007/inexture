package service.impl;





import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

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
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImp.
 */
public class UserServiceImp implements UserService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImp.class.getName());

	/** The response. */
	private transient String response = ""; // response message
	private transient Properties prop = null;
	public UserServiceImp() throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		prop = DbUtil.getProperties("webpage-response.properties");
	}
	/**
	 * Sets the params.
	 *
	 * @param req the req
	 * @return the user
	 * @throws ParseException the parse exception
	 */
	public static User setParams(final HttpServletRequest req) throws ParseException   {
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
			LOGGER.warn("setParams(HttpServletRequest) - exception ignored", e); //$NON-NLS-1$

		}
		try {
			user.setGender(Integer.parseInt(req.getParameter("gender")));
		}catch(final NumberFormatException e) {
			LOGGER.warn("setParams(HttpServletRequest) - exception ignored", e); //$NON-NLS-1$

		}
		try {
			user.setDob( new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).parse( req.getParameter("dob")));
		}catch(final NumberFormatException e) {
			LOGGER.warn("setParams(HttpServletRequest) - exception ignored", e); //$NON-NLS-1$

		}

		user.setRole(2);
		try {
			user.setTech(Integer.parseInt(req.getParameter("tech")));
		}catch(final NumberFormatException e) {
			LOGGER.warn("setParams(HttpServletRequest) - exception ignored", e); //$NON-NLS-1$

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
	public String regesterUser(final HttpServletRequest req) throws ParseException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, ClassNotFoundException, SQLException, IOException, ServletException   {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("regesterUser(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		User user = UserServiceImp.setParams(req);

		user.setPassword(AESCrypt.encrypt(user.getPassword()));
		final UserDao userdao = new UserDaoImpl(); // creating dao object
		if (!userdao.insert(user, "insert")) { // calling dao method
			user = userdao.selectUser(user.getEmail());
			final AddressService adressService = new AddressServiceImpl();
			if (adressService.addAddress(req, user.getIduser())) {
				final LangTransServ its = new LangTransImpl();
				if (its.addLangTransaction(req, user.getIduser())) {
					final ImageService imageService = new ImageServiceImpl();
					if (imageService.saveImage(req, user.getIduser())) {
						response = prop.getProperty("reg.success");
					}else {
						response = prop.getProperty("reg.failure");
					}
				}else {
					response = prop.getProperty("reg.failure");
				}
			} else {
				response = prop.getProperty("reg.failure");
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
	public User getUser(final String email, final String pass) throws ClassNotFoundException, SQLException, IOException {
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
	public String updateUser(final HttpServletRequest req, final int iduser) throws ParseException, ClassNotFoundException, SQLException, IOException, ServletException   {
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
		final AddressService addressService = new AddressServiceImpl();
		addressService.updateAddress(req, iduser);
		final ImageService imageService = new ImageServiceImpl();
		imageService.updateImage(req, iduser);
		response =  prop.getProperty("updateuser.success");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateUser(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getAllUser(int)
	 */
	@Override
	public ArrayList<User> getAllUser(final int role) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAllUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ArrayList<User> returnArrayList = new UserDaoImpl().selectAllUser(role);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAllUser(int) - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#getUser(int)
	 */
	@Override
	public User getUser(final int iduser) throws ClassNotFoundException, SQLException, IOException {
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
	public boolean deleteUser(final int iduser)
			throws ClassNotFoundException, SQLException, IOException, ParseException, ServletException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deleteUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final User user = new User();
		user.setIduser(iduser);

		final boolean returnboolean = new UserDaoImpl().insert(user, "delete");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deleteUser(int) - end"); //$NON-NLS-1$
		}
		return returnboolean;
	}

	/* (non-Javadoc)
	 * @see service.user.UserService#updatePass(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updatePass(final HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException   {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updatePass(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		String resp = "";
		User user = null;
		final UserDao udao = new UserDaoImpl();
		if (req.getParameter("email") == null && req.getParameter("password") == null) {
			final HttpSession session = req.getSession();
			user = (User) session.getAttribute("user");
			if (user.getPassword().equals(AESCrypt.encrypt(req.getParameter("oldpass")))) {
				user.setPassword(AESCrypt.encrypt(req.getParameter("newpass")));

				if (!udao.updatePassword(user)) {
					resp =  prop.getProperty("changepass.success");
				}
			} else {
				resp =  prop.getProperty("changepass.failure");
			}

		} else {
			final String email = req.getParameter("email");
			String password = req.getParameter("password");
			user = udao.selectUser(email);
			if (user == null) {
				resp =  prop.getProperty("forgotpass.failure");
			} else {
				password = AESCrypt.encrypt(password);
				user.setPassword(password);
				if (!udao.updatePassword(user)) {
					resp =	prop.getProperty("forgotpass.succes");
				}
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
	public boolean checkUserExist(final HttpServletRequest req) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("checkUserExist(HttpServletRequest) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final String email = req.getParameter("email");
		final User user = new UserDaoImpl().selectUser(email);
		if (user != null) {
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
