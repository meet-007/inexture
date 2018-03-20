package controller.updtprofile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import service.user.UserService;
import service.user.UserServiceImp;

import javax.servlet.annotation.*;

/**
 * Servlet implementation class UpdateServ
 */
@MultipartConfig
public class UpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rspmsg = null;
		try {
			User user = null;
			HttpSession session = null;
			if(request.getParameter("iduser")==null) {
				session = request.getSession();
				user = (User) session.getAttribute("user");	
			}else {
				int iduser = Integer.parseInt(request.getParameter("iduser"));
				user = new UserServiceImp().getUser(iduser);
				request.setAttribute("user",user);
			}
			
			rspmsg = new UserServiceImp().updateUser(request, user.getIduser());
			session.removeAttribute("user");
			session.setAttribute("user",new UserServiceImp().getUser(user.getEmail(), user.getPassword()));
		} catch (Exception e1) {
			rspmsg = e1.getMessage();
			e1.printStackTrace();
		}
		request.setAttribute("rspmsg", rspmsg);
		RequestDispatcher rd = request.getRequestDispatcher("UpdateProfile");
		rd.forward(request, response);
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
