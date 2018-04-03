package controller.login;




import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.user.UserServiceImp;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ForgotPassServ.
 */
public class ForgotPassServ extends HttpServlet {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(ForgotPassServ.class.getName());

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		try {
			String error = new validations.LoginValidation().validate(request.getParameter("email"), request.getParameter("password"));
			if(!error.equals("")) {
				request.setAttribute("errormsg", error);
				throw new Exception("please enable javascript");
			}
			request.setAttribute("rspmsg2", new UserServiceImp().updatePass(request));
		} catch (Exception e1) {
			logger.error("doPost(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$
			request.setAttribute("rspmsg2", e1.getMessage());
			e1.printStackTrace();
		}
		request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
