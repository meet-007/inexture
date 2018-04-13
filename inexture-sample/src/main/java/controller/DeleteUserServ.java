/*
 *
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.impl.UserServiceImp;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class DeleteUserServ.
 */
public class DeleteUserServ extends HttpServlet {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(DeleteUserServ.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,final  HttpServletResponse response)
			throws ServletException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doGet(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub


		try {
			response.setContentType("application/json");
			response.getWriter()
			.println((new UserServiceImp().deleteUser(Integer.parseInt(request.getParameter("iduser"))))
					? "{\"result\":\"fail to delete\",\"bool\":\"1\"}"
							: "{\"result\":\"deleted success\",\"bool\":\"0\"}");
		} catch (NumberFormatException | ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			LOGGER.error("doGet(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doGet(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}
}
