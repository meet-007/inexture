package controller;




import java.io.IOException;

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
import service.impl.AddressServiceImpl;
import service.impl.ImageServiceImpl;
import service.impl.LangTransImpl;
import service.impl.UserServiceImp;
// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class UpdateServ.
 */
@MultipartConfig
public class UpdateServ extends HttpServlet {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(UpdateServ.class.getName());

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

			User user = null;

			HttpSession session  = request.getSession();
			if (request.getParameter("iduser") == null) {
				user = (User) session.getAttribute("user");
			} else {
				user = new UserServiceImp().getUser(Integer.parseInt(request.getParameter("iduser")));
				request.setAttribute("user", user );
			}


			String error = new validations.RegistrationValidation().validate(request);
			if(error.equals("")) {
				request.setAttribute("rspmsg", new UserServiceImp().updateUser(request, user.getIduser()));
				session.removeAttribute("user");
				session.setAttribute("user", new UserServiceImp().getUser(user.getEmail(), user.getPassword()));
			}else {
				request.setAttribute("errormsg", error);
				throw new Exception("enable javaScript if it is disabled");

			}
		} catch (Exception e1) {
			logger.error("doGet(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$
			try {
				request.setAttribute("user", UserServiceImp.setParams(request));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
				// TODO Auto-generated catch block
			}try {
				request.setAttribute("languages", LangTransImpl.setParams(request, -1));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
				// TODO Auto-generated catch block
			}try {
				request.setAttribute("addrslist", AddressServiceImpl.setParams(request, -1));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
				// TODO Auto-generated catch block
			}try {
				request.setAttribute("imglist", ImageServiceImpl.setParams(request, -1));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
				// TODO Auto-generated catch block
			}
			request.setAttribute("rspmsg", e1.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("UpdateProfile");
		rd.forward(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
