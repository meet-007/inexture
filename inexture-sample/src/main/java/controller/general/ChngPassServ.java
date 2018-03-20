package controller.general;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.UserServiceImp;

/**
 * Servlet implementation class ChngPassServ
 */
public class ChngPassServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChngPassServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		RequestDispatcher rd = request.getRequestDispatcher("ChangePass.jsp");
		rd.forward(request, response);
	}

}
