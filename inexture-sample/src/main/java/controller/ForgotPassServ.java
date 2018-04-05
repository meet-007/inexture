package controller;




import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.impl.UserServiceImp;
import validations.JavaScriptEnableExcepion;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ForgotPassServ.
 */
public class ForgotPassServ extends HttpServlet {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(ForgotPassServ.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
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
			final String error = new validations.LoginValidation().validate(request.getParameter("email"), request.getParameter("password"));
			if(!error.isEmpty()) {
				request.setAttribute("errormsg", error);
				throw new JavaScriptEnableExcepion("please enable javascript");
			}
			request.setAttribute("rspmsg2", new UserServiceImp().updatePass(request));
		} catch (InvalidKeyException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException
				| NoSuchAlgorithmException | NoSuchPaddingException | JavaScriptEnableExcepion | SQLException e) {
			// TODO Auto-generated catch block.
			LOGGER.error("doGet(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
		}

		request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
