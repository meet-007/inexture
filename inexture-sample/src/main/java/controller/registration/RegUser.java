package controller.registration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import org.omg.CORBA.Request;

import service.user.UserServiceImp;

/**
 * Servlet implementation class RegUser
 */
@MultipartConfig
public class RegUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rspmsg = null;
		try {
			rspmsg = new UserServiceImp().regesterUser(request);
			
		} catch (Exception e1) {
			rspmsg = e1.getMessage();
			e1.printStackTrace();
		}
		request.setAttribute("rspmsg", rspmsg);
		RequestDispatcher rd = request.getRequestDispatcher("ShowRegServ");
		rd.forward(request, response);
	}

}
