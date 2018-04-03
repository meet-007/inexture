package controller.general;

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
 * Servlet implementation class CheckUser.
 */
public class CheckUser extends HttpServlet {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(CheckUser.class.getName());

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		try {
			response.setContentType("application/json");
			response.getWriter()
			.println((new UserServiceImp().checkUserExist(request))
					? "{\"result\":\"Email id already exist try another email \",\"bool\":\"0\"}"
							: "{\"result\":\"\",\"bool\":\"1\"}");
		} catch (Exception e) {
			logger.warn("doGet(HttpServletRequest, HttpServletResponse) - exception ignored", e); //$NON-NLS-1$

		}

		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
