package controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  javax.servlet.http.HttpSession;
import model.User;
import service.user.UserServiceImp;

/**
 * Servlet implementation class ForgotPassServ
 */
public class ForgotPassServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ForgotPassServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rspmsg = null;
		try {
			rspmsg = new UserServiceImp().updatePass(request);
		} catch (Exception e1) {
			rspmsg = e1.getMessage();
			e1.printStackTrace();
		}
		request.setAttribute("rspmsg", rspmsg);
		RequestDispatcher rd = request.getRequestDispatcher("ForgotPassword.jsp");
		rd.forward(request, response);
		
	}

}
