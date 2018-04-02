package controller.updtprofile;




import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Address;
import model.LangTransact;
import model.User;
import model.UserImages;
import service.Address.AddressServiceImpl;
import service.Image.ImageServiceImpl;
import service.LangTransaction.LangTransImpl;
import service.user.UserServiceImp;

/**
 * Servlet implementation class UpdateServ
 */
@MultipartConfig
public class UpdateServ extends HttpServlet {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(UpdateServ.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UpdateServ() {
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
		String page= null;
		String rspmsg = null;
		String error = null;
		User u = null;
		ArrayList<LangTransact> newlangarr = null;
		ArrayList<Address> adrsarr = null;
		ArrayList<UserImages> uimglist = null;
		try {
			User user = null;
			HttpSession session = session = request.getSession();
			if (request.getParameter("iduser") == null) {
				user = (User) session.getAttribute("user");
			} else {
				int iduser = Integer.parseInt(request.getParameter("iduser"));
				user = new UserServiceImp().getUser(iduser);
				request.setAttribute("user", user);
			}
			error = new validations.RegistrationValidation().validate(request);
			if(error.equals("")) {
				rspmsg = new UserServiceImp().updateUser(request, user.getIduser());
				session.removeAttribute("user");
				session.setAttribute("user", new UserServiceImp().getUser(user.getEmail(), user.getPassword()));
				page="UpdateProfile";
			}else {
				page="UpdateProfile";
				throw new Exception("enable javaScript if it is disabled");

			}
		} catch (Exception e1) {
			logger.error("doGet(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$
			try {
				u = UserServiceImp.setParams(request);
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				newlangarr = LangTransImpl.setParams(request, -1);
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				adrsarr = AddressServiceImpl.setParams(request, -1);
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				uimglist = ImageServiceImpl.setParams(request, -1);
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if(!error.equals("")) {
				request.setAttribute("errormsg", error);
			}
			request.setAttribute("addrslist", adrsarr);
			request.setAttribute("user", u);
			request.setAttribute("languages", newlangarr);
			request.setAttribute("imglist", uimglist);
			rspmsg = e1.getMessage();
			e1.printStackTrace();
			rspmsg = e1.getMessage();
			e1.printStackTrace();
		}
		request.setAttribute("rspmsg", rspmsg);
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doGet(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

	/**
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
		doGet(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
