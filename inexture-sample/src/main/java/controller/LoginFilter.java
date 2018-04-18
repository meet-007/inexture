package controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.impl.RoleDaoImpl;
import model.Role;
import model.User;
import service.impl.UserServiceImp;
import util.AESCrypt;
import util.DbUtil;
import validations.JavaScriptEnableExcepion;

// TODO: Auto-generated Javadoc
/**
 * Servlet Filter implementation class LoginFilter.
 */
@SuppressWarnings("PMD")
public class LoginFilter implements Filter {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(LoginFilter.class.getName());

	/**
	 * Destroy.
	 *
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("destroy() - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("destroy() - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Do filter.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param chain
	 *            the chain
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		// place your code here
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse resp = (HttpServletResponse) response;
		try {
			final String email = request.getParameter("email");
			final String password = request.getParameter("password");
			final String error = new validations.LoginValidation().validate(email, password);
			if (!error.isEmpty()) {
				request.setAttribute("errormsg", error);
				throw new JavaScriptEnableExcepion("please enable javascript and then try again");
			}
			User user = null;
			user = new UserServiceImp().getUser(email, AESCrypt.encrypt(password));
			if (user == null) {
				final Properties prop = DbUtil.getProperties("webpage-response.properties");
				request.setAttribute("rspmsg1", prop.getProperty("login.failure"));
				request.getRequestDispatcher("Login.jsp").forward(request, response);

			} else {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - {}" + "user found"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				final HttpSession session = req.getSession();
				session.setAttribute("user", user);
				final Role role = new RoleDaoImpl().getRole(user.getRole());
				if (role.getRole().equals("admin")) {
					resp.sendRedirect("AdminHome.jsp");
				} else {
					resp.sendRedirect("UserHome.jsp");
				}
			}
		} catch (InvalidKeyException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException
				| NoSuchAlgorithmException | NoSuchPaddingException | JavaScriptEnableExcepion | SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("doFilter(ServletRequest, ServletResponse, FilterChain)", e); //$NON-NLS-1$
			request.setAttribute("rspmsg1", e.getMessage());
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Inits the.
	 *
	 * @param fConfig
	 *            the f config
	 * @throws ServletException
	 *             the servlet exception
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(final FilterConfig fConfig) throws ServletException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init(FilterConfig) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("init(FilterConfig) - end"); //$NON-NLS-1$
		}
	}

}
