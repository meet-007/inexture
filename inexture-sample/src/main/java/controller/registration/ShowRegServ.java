package controller.registration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Lang.LangServ;
import service.Lang.LangServImpl;
import service.Tech.TechServ;
import service.Tech.TechServImpl;

/**
 * Servlet implementation class ShowRegServ
 */
public class ShowRegServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			TechServ ts = new TechServImpl();
			ArrayList technologies = ts.getTech();
			request.setAttribute("tech", technologies);
			LangServ ls = new LangServImpl();
			ArrayList languages = ls.getLang();
			request.setAttribute("lang", languages);
			RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			doGet(request, response);
		}
	}
}