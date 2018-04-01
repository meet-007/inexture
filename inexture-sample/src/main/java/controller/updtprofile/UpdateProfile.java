package controller.updtprofile;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;
import service.Address.AddressService;
import service.Address.AddressServiceImpl;
import service.Image.ImageService;
import service.Image.ImageServiceImpl;
import service.LangTransaction.LangTransImpl;
import service.LangTransaction.LangTransServ;
import service.user.UserServiceImp;

/**
 * Servlet implementation class UpdateProfile
 */
public class UpdateProfile extends HttpServlet {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(UpdateProfile.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UpdateProfile() {
		// TODO Auto-generated constructor stub
	}

	/**
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
				int iduser = Integer.parseInt(request.getParameter("iduser"));
				user = new UserServiceImp().getUser(iduser);
				request.setAttribute("user", user);
			} else {
				HttpSession session = request.getSession();
				user = (User) session.getAttribute("user");
			}
			AddressService adrserv = new AddressServiceImpl();
			ArrayList<Address> adrsarr = adrserv.getUserAddress(user.getIduser());
			request.setAttribute("addrslist", adrsarr);
			LangTransServ lts = new LangTransImpl();
			ImageService imgserv = new ImageServiceImpl();
			request.setAttribute("imglist", imgserv.getUserImages(user.getIduser()));
			request.setAttribute("languages", lts.getUserLanguages(user.getIduser()));

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
