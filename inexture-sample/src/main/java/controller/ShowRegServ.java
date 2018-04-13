package controller;




import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.impl.LangServImpl;
import service.impl.TechServImpl;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ShowRegServ.
 */
public class ShowRegServ extends HttpServlet {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(ShowRegServ.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,final  HttpServletResponse response)
			throws ServletException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doGet(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}


		try {
			request.setAttribute("tech",  new TechServImpl().getTech());
			request.setAttribute("lang",  new LangServImpl().getLang());
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			if (request.getParameter("page") == null) {
				request.getRequestDispatcher("Registration.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			LOGGER.error("doGet(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
		}


		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doGet(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}