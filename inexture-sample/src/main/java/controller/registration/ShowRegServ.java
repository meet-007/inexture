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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String page = "";

			TechServ ts = new TechServImpl();
			ArrayList technologies = ts.getTech();
			request.setAttribute("tech", technologies);
			LangServ ls = new LangServImpl();
			ArrayList languages = ls.getLang();
			request.setAttribute("lang", languages);
			if(request.getParameter("page")!=null) {
				page="UpdateProfile.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}else {
				page="Registration.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			doGet(request, response);
		}
	}
}