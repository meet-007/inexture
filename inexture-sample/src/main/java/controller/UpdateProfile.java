package controller;




import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.User;
import service.impl.AddressServiceImpl;
import service.impl.ImageServiceImpl;
import service.impl.LangTransImpl;
import service.impl.UserServiceImp;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class UpdatePr ofile.
 */
public class UpdateProfile extends HttpServlet {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(UpdateProfile.class.getName());

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
	protected void doGet(final HttpServletRequest request,final HttpServletResponse response)
			throws ServletException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doGet(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		try {
			User user = null;
			if (request.getParameter("iduser") == null) {
				user = (User) request.getSession().getAttribute("user");
			} else {
				user = new UserServiceImp().getUser(Integer.parseInt(request.getParameter("iduser")));
				request.setAttribute("user", user);
			}
			request.setAttribute("addrslist", new AddressServiceImpl().getUserAddress(user.getIduser()));
			request.setAttribute("imglist", new ImageServiceImpl().getUserImages(user.getIduser()));
			request.setAttribute("languages", new LangTransImpl().getUserLanguages(user.getIduser()));
			final RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowRegServ?page=update");
			requestDispatcher.forward(request, response);
		} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
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
	protected void doPost(final HttpServletRequest request,final  HttpServletResponse response)
			throws ServletException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		doGet(request, response);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}
}
