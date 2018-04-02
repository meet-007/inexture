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

/**
 * Servlet Filter implementation class BackBtnDisable
 */
public class BackBtnDisable implements Filter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(BackBtnDisable.class.getName());

	/**
	 * Default constructor.
	 */
	public BackBtnDisable() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
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
		HttpSession session = req.getSession();
		System.out.println(req.getRequestURI());
		if (req.getRequestURI().matches(".*(js|css|png|jpg|woff|woff2|ttf)$")) {
			chain.doFilter(request, response);
		} else {
			if ((session.getAttribute("user") == null) && needsAuthentication(req.getRequestURI())) {
				RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
			resp.setHeader("Pragma", "no-cache");
			resp.setDateHeader("Expires", 0);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end"); //$NON-NLS-1$
		}
	}

	private boolean needsAuthentication(String url) {
		if (logger.isDebugEnabled()) {
			logger.debug("needsAuthentication(String) - start"); //$NON-NLS-1$
		}

		String[] validNonAuthenticationUrls = { "inexture-sample/", "Login.jsp", "ShowRegServ", "index.jsp",
				"ForgotPassword.jsp", "CheckUser", "RegUser", "ForgotPassServ" };
		for (String validUrl : validNonAuthenticationUrls) {
			if (url.endsWith(validUrl)) {
				if (logger.isDebugEnabled()) {
					logger.debug("needsAuthentication(String) - end"); //$NON-NLS-1$
				}
				return false;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("needsAuthentication(String) - end"); //$NON-NLS-1$
		}
		return true;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
