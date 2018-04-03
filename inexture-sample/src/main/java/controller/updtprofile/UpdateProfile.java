package controller.updtprofile;




import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.User;
import service.Address.AddressServiceImpl;
import service.Image.ImageServiceImpl;
import service.LangTransaction.LangTransImpl;
import service.user.UserServiceImp;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class UpdateProfile.
 */
public class UpdateProfile extends HttpServlet {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(UpdateProfile.class.getName());

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		try {
			User user = null;
			if (request.getParameter("iduser") != null) {
				user = new UserServiceImp().getUser(Integer.parseInt(request.getParameter("iduser")));
				request.setAttribute("user", user);
			} else {
				user = (User) request.getSession().getAttribute("user");
			}
			request.setAttribute("addrslist", new AddressServiceImpl().getUserAddress(user.getIduser()));
			request.setAttribute("imglist", new ImageServiceImpl().getUserImages(user.getIduser()));
			request.setAttribute("languages", new LangTransImpl().getUserLanguages(user.getIduser()));
			RequestDispatcher rd = request.getRequestDispatcher("ShowRegServ?page=update");
			rd.forward(request, response);
		} catch (Exception e) {
			logger.error("doGet(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		doGet(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}
}
