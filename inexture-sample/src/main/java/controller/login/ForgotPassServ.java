package controller.login;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import model.User;
import service.user.UserServiceImp;

/**
 * Servlet implementation class ForgotPassServ
 */
public class ForgotPassServ extends HttpServlet {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ForgotPassServ.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ForgotPassServ() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		String rspmsg = null;
		try {
			rspmsg = new UserServiceImp().updatePass(request);
		} catch (Exception e1) {
			logger.error("doPost(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$

			rspmsg = e1.getMessage();
			e1.printStackTrace();
		}
		request.setAttribute("rspmsg", rspmsg);
		RequestDispatcher rd = request.getRequestDispatcher("ForgotPassword.jsp");
		rd.forward(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
