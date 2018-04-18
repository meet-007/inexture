package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.User;
import service.impl.UserServiceImp;
import util.ResetRegForm;
import validations.JavaScriptEnableExcepion;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class UpdateServ.
 */
@MultipartConfig
public class UpdateServ extends HttpServlet {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(UpdateServ.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		try {
			User user = null;

			final HttpSession session = request.getSession();
			if (request.getParameter("iduser") == null) {
				user = (User) session.getAttribute("user");
			} else {
				user = new UserServiceImp().getUser(Integer.parseInt(request.getParameter("iduser")));
				request.setAttribute("user", user);
			}

			final String error = new validations.RegistrationValidation().validate(request);
			if (error.isEmpty()) {
				request.setAttribute("rspmsg", new UserServiceImp().updateUser(request, user.getIduser()));
				final User sessionUser = (User)session.getAttribute("user");
				if (user.getIduser()==sessionUser.getIduser()) {
					session.removeAttribute("user");
					session.setAttribute("user", new UserServiceImp().getUser(user.getEmail(), user.getPassword()));
				}
			} else {
				request.setAttribute("errormsg", error);
				throw new JavaScriptEnableExcepion("enable javaScript if it is disabled");

			}
		} catch (NumberFormatException | ClassNotFoundException | SQLException | ParseException
				| JavaScriptEnableExcepion e) {
			LOGGER.error("doGet(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
			ResetRegForm.resetForm(request);
			request.setAttribute("rspmsg", e.getMessage());
		}

		final RequestDispatcher reqestDispatcher = request.getRequestDispatcher("UpdateProfile");
		reqestDispatcher.forward(request, response);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
