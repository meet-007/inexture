package controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Role.RoleDao;
import dao.Role.RoleDaoImpl;
import model.Role;
import model.User;
import service.user.UserService;
import service.user.UserServiceImp;
import java.lang.*;
/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("hello0000000000000000000");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		System.out.println("filter called");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rspmsg = "";
		UserService us = new UserServiceImp();
		String page = null;
		try {
			User u =us.getUser(email, password);
			if(u != null) {
				System.out.println("user found");
				HttpSession session = req.getSession();
				session.setAttribute("user", u);
				RoleDao roledao = new RoleDaoImpl();
				Role role = roledao.getRole(u.getRole());
				if(role.getRole().equals("admin")) {
					page = "AdminHome.jsp";
				}else {
					// pass the request along the filter chain
					chain.doFilter(request, response);
					PrintWriter pt = response.getWriter();
					pt.println("asdfffffffffff");
				}
			}
			else {
				rspmsg = "Invalid Username or password please try again";
				page = "Login.jsp";
			}
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
			rspmsg = e.getLocalizedMessage();
			page = "Login.jsp";
		}
		System.out.println(rspmsg);
		RequestDispatcher rd = request.getRequestDispatcher(page);
		request.setAttribute("rspmsg",rspmsg);
		rd.forward(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter initiallizeddddddddddddddddddd");
		
	}

}
