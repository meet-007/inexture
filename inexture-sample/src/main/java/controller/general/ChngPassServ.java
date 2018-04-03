package controller.general;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.user.UserServiceImp;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ChngPassServ.
 */
public class ChngPassServ extends HttpServlet {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(ChngPassServ.class.getName());

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
		RequestDispatcher rd = null;
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			rd = request.getRequestDispatcher("ChangePass.jsp");
			request.setAttribute("rspmsg", new UserServiceImp().updatePass(request));
		} catch (Exception e1) {
			logger.error("doPost(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$
			request.setAttribute("rspmsg", e1.getMessage());
			e1.printStackTrace();
		}
		rd.forward(request, response);
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
