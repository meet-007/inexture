package controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.impl.UserServiceImp;
import util.ResetRegForm;
import validations.JavaScriptEnableExcepion;
import validations.RegistrationValidation;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class RegUser.
 */
@SuppressWarnings("PMD")
@MultipartConfig
public class RegUser extends HttpServlet {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(RegUser.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,final HttpServletResponse response)
			throws ServletException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		String page = "";

		try {
			final RegistrationValidation rvalidate = new RegistrationValidation();
			final String error = rvalidate.validate(request);
			if(error.isEmpty()) {
				request.setAttribute("rspmsg", new UserServiceImp().regesterUser(request));
				page = "Login.jsp";
			}else {
				request.setAttribute("errormsg", error);
				throw new JavaScriptEnableExcepion("enable javaScript if it is disabled");
			}
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException | ClassNotFoundException | ParseException | SQLException
				| JavaScriptEnableExcepion e1) {
			LOGGER.error("doPost(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$
			ResetRegForm.resetForm(request);
			request.setAttribute("rspmsg", e1.getMessage());
			page = "ShowRegServ";
		}

		request.getRequestDispatcher(page).forward(request, response);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Reset form.
	 *
	 * @param request the request
	 */


}
