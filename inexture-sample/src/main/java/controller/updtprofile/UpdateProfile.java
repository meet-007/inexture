package controller.updtprofile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.Address.AddressService;
import service.Address.AddressServiceImpl;
import javax.servlet.http.HttpSession;
import model.Address;

/**
 * Servlet implementation class UpdateProfile
 */
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateProfile() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			AddressService adrserv = new AddressServiceImpl();
			Address[] adrsarr = adrserv.getUserAddress(user.getIduser());
			request.setAttribute("addrslist",adrsarr);
			RequestDispatcher rd = request.getRequestDispatcher("ShowRegServ?page=update");
			rd.forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
