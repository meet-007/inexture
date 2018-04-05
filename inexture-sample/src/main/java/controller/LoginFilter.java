package controller;




import java.io.IOException;

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

// TODO: Auto-generated Javadoc
/**
 * Servlet Filter implementation class LoginFilter.
 */
public class LoginFilter implements Filter {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(LoginFilter.class.getName());

	/**
	 * Destroy.
	 *
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		if (logger.isDebugEnabled()) {
			logger.debug("destroy() - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("destroy() - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Do filter.
	 *
	 * @param request the request
	 * @param response the response
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		// place your code here
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String error = new validations.LoginValidation().validate(email, password);
			if(!error.equals("")) {
				request.setAttribute("errormsg", error);
				throw new Exception("please enable javascript and then try again");
			}
			User u =  null;
			u = new UserServiceImp().getUser(email, AESCrypt.encrypt(password));
			if (u != null) {
				System.out.println("user found");
				HttpSession session = req.getSession();
				session.setAttribute("user", u);
				Role role = new RoleDaoImpl().getRole(u.getRole());
				if (role.getRole().equals("admin")) {
					resp.sendRedirect("AdminHome.jsp");
				} else {
					resp.sendRedirect("UserHome.jsp");
				}
			} else {
				request.setAttribute("rspmsg1", "Invalid Username or password please try again");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			logger.error("doFilter(ServletRequest, ServletResponse, FilterChain)", e); //$NON-NLS-1$
			request.setAttribute("rspmsg1", e.getMessage());
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}



		if (logger.isDebugEnabled()) {
			logger.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Inits the.
	 *
	 * @param fConfig the f config
	 * @throws ServletException the servlet exception
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("init(FilterConfig) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("init(FilterConfig) - end"); //$NON-NLS-1$
		}
	}

}
