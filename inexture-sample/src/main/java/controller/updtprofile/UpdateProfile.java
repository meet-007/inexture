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
import service.Image.ImageService;
import service.Image.ImageServiceImpl;
import service.LangTransaction.LangTransImpl;
import service.LangTransaction.LangTransServ;

import javax.servlet.http.HttpSession;
import model.Address;
import java.util.ArrayList;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			AddressService adrserv = new AddressServiceImpl();
			ArrayList<Address> adrsarr = adrserv.getUserAddress(user.getIduser());
			request.setAttribute("addrslist", adrsarr);
			LangTransServ lts = new LangTransImpl();
			ImageService imgserv = new ImageServiceImpl();
			request.setAttribute("imglist", imgserv.getUserImages(user.getIduser()));
			request.setAttribute("languages", lts.getUserLanguages(user.getIduser()));
			RequestDispatcher rd = request.getRequestDispatcher("ShowRegServ?page=update");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
