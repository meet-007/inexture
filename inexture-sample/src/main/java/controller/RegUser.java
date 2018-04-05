package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import service.impl.AddressServiceImpl;
import service.impl.ImageServiceImpl;
import service.impl.LangTransImpl;
import service.impl.UserServiceImp;
import validations.RegistrationValidation;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class RegUser.
 */
@MultipartConfig
public class RegUser extends HttpServlet {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(RegUser.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
		}

		String page = "";
		try {
			validations.RegistrationValidation rvalidate = new RegistrationValidation();
			String error = rvalidate.validate(request);
			if(error.equals("")) {
				request.setAttribute("rspmsg", new UserServiceImp().regesterUser(request));
				page = "Login.jsp";
			}else {
				request.setAttribute("errormsg", error);
				throw new Exception("enable javaScript if it is disabled");
			}
		} catch (Exception e1) {
			logger.error("doPost(HttpServletRequest, HttpServletResponse)", e1); //$NON-NLS-1$

			try {
				request.setAttribute("user", UserServiceImp.setParams(request));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				request.setAttribute("languages", LangTransImpl.setParams(request, -1));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				request.setAttribute("addrslist", AddressServiceImpl.setParams(request, -1));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}try {
				request.setAttribute("imglist", ImageServiceImpl.setParams(request, -1));
			} catch (Exception e) {
				logger.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("rspmsg", e1.getMessage());
			e1.printStackTrace();
			page = "ShowRegServ";
		}
		request.getRequestDispatcher(page).forward(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("doPost(HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

}
