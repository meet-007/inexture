package controller.registration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import validations.RegistrationValidation;

/**
 * Servlet implementation class RegUser
 */
@MultipartConfig
public class RegUser extends HttpServlet {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(RegUser.class.getName());

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		String rspmsg = null;
		User u = null;
		ArrayList<LangTransact> newlangarr = null;
		ArrayList<Address> adrsarr = null;
		ArrayList<UserImages> uimglist = null;
		String error = null;
		String page = "";
		try {
			validations.RegistrationValidation rvalidate = new RegistrationValidation();
			error = rvalidate.validate(request);
			if(error.equals("")) {
				rspmsg = new UserServiceImp().regesterUser(request);
				page = "Login.jsp";
			}else {
				page = "ShowRegServ";
				throw new Exception("enable javaScript if it is disabled");

			}
		} catch (Exception e1) {
			logger.error("doPost(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$

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
			page = "ShowRegServ";
		}

		request.setAttribute("rspmsg", rspmsg);
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
