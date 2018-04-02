package controller.login;




import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.Role.RoleDao;
import dao.Role.RoleDaoImpl;
import model.Role;
import model.User;
import service.user.UserService;
import service.user.UserServiceImp;
import util.AESCrypt;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LoginFilter.class.getName());

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
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
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rspmsg = "";
		UserService us = new UserServiceImp();
		String page = null;
		try {
			password = AESCrypt.encrypt(password);
			User u = us.getUser(email, password);
			if (u != null) {
				System.out.println("user found");
				HttpSession session = req.getSession();
				session.setAttribute("user", u);
				RoleDao roledao = new RoleDaoImpl();
				Role role = roledao.getRole(u.getRole());
				if (role.getRole().equals("admin")) {
					page = "AdminHome.jsp";
				} else {
					page = "UserHome.jsp";
				}
			} else {
				rspmsg = "Invalid Username or password please try again";

			}
		} catch (Exception e) {
			logger.error("doFilter(ServletRequest, ServletResponse, FilterChain)", e); //$NON-NLS-1$

			System.out.println(e.getMessage());
			rspmsg = "there is some error try login after sometime";
		}
		if (page == null) {
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			request.setAttribute("rspmsg1", rspmsg);
			rd.forward(request, response);
		} else {
			resp.sendRedirect(page);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
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
