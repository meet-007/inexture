package controller.general;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.UserService;
import service.user.UserServiceImp;

/**
 * Servlet implementation class CheckUser
 */
public class CheckUser extends HttpServlet {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(CheckUser.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CheckUser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		boolean result = false;
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String json = "";
		try {
			UserService userserv = new UserServiceImp();
			result = userserv.checkUserExist(request);
			if (result) {
				json = "{\"result\":\"Email id already exist try another email \",\"bool\":\"0\"}";
			} else {
				json = "{\"result\":\"\",\"bool\":\"1\"}";
			}
		} catch (Exception e) {
			logger.warn("doGet(HttpServletRequest, HttpServletResponse) - exception ignored", e); //$NON-NLS-1$

			// msg = e.getMessage();
		}
		out.println(json);

		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
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
		doGet(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
