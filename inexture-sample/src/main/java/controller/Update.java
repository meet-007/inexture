package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Update() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Address[] adress = new Address[5];
	
		for(int i=0;i<adress.length;i++) {
			adress[i] =  new Address();
			adress[i].setAddressline1("address line1 "+i);
			adress[i].setAddressline2("address line2 "+i);
			adress[i].setCity("city "+i);
			adress[i].setState("state "+i);
			adress[i].setCountry("country "+i);
			adress[i].setPin(i);
		}
		request.setAttribute("addresslist",adress);
		RequestDispatcher rd = request.getRequestDispatcher("Reg2.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
