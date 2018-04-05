package controller;




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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * Servlet Filter implementation class BackBtnDisable.
 */
public class BackBtnDisable implements Filter {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(BackBtnDisable.class.getName());


	/**
	 * Destroy.
	 *
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
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

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getRequestURI().matches(".*(js|css|png|jpg|woff|woff2|ttf)$")) {
			chain.doFilter(request, response);
		} else {
			if ((req.getSession().getAttribute("user") == null) && needsAuthentication(req.getRequestURI())) {
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

	/**
	 * Needs authentication.
	 *
	 * @param url the url
	 * @return true, if successful
	 */
	private boolean needsAuthentication(String url) {
		if (logger.isDebugEnabled()) {
			logger.debug("needsAuthentication(String) - start"); //$NON-NLS-1$
		}

		String[] validNonAuthenticationUrls = { "inexture-sample/", "Login.jsp", "ShowRegServ", "index.jsp",
				"ForgotPassword.jsp", "CheckUser", "RegUser", "ForgotPassServ" };
		for (String validUrl : validNonAuthenticationUrls) {
			if (url.endsWith(validUrl)) {
				return false;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("needsAuthentication(String) - end"); //$NON-NLS-1$
		}
		return true;
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
		// TODO Auto-generated method stub
	}

}
