/*
 *
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.impl.UserServiceImp;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ShowAllUser.
 */
public class ShowAllUser extends HttpServlet {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(ShowAllUser.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		try {
			request.setAttribute("userslist", new UserServiceImp().getAllUser(2));
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			request.getRequestDispatcher("ManageUser.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error("doGet(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
		}


		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
