package controller.Admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.UserServiceImp;

/**
 * Servlet implementation class ShowAllUser
 */
public class ShowAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rspmsg = "";
		try {
			request.setAttribute("userslist",new UserServiceImp().getAllUser(2));
		}catch(Exception e) {
			rspmsg=e.getMessage();
		}
		RequestDispatcher rd = request.getRequestDispatcher("ManageUser.jsp");
		rd.forward(request, response);
	}

}
