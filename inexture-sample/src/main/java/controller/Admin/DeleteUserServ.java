package controller.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.UserService;
import service.user.UserServiceImp;

/**
 * Servlet implementation class DeleteUserServ
 */
public class DeleteUserServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteUserServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result = true;
		PrintWriter out = null;
		response.setContentType("application/json");
		String json = "";
		try {
			out = response.getWriter();
			//out.println("hello");
			System.out.println(request.getParameter("iduser"));
			UserService userserv = new UserServiceImp();
			result = userserv.deleteUser(Integer.parseInt(request.getParameter("iduser")));
			if(!result) {
				json = "{\"result\":\"deleted success\",\"bool\":\"0\"}";
			}else {
				json = "{\"result\":\"fail to delete\",\"bool\":\"1\"}";
			}
		}catch(Exception e) {
			//msg = e.getMessage();
		}
		out.println(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
