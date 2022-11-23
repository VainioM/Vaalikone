package Servlets;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import Models.Dao;
import Models.EhdokasDao;

import javax.servlet.annotation.*;

@WebServlet(name= "LoginLogout", urlPatterns = {"/login", "/logout"})
public class LoginLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	private EhdokasDao ehdokasdao;

    /**
     * Servlet init method. Readies the database connection for use
     */
	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");
		
		dao = new Dao(dbUrl, dbUser, dbPass);
		ehdokasdao = new EhdokasDao(dbUrl, dbUser, dbPass);
	}

    /**
     * LoginLogout GET handler. routes account to login page or logs session out
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get request url
        String url = request.getServletPath();

        // Get request session
        HttpSession session = request.getSession();

        // GET /login path
        if(url.equals("/login")) {
            // Check session if user is not already logged in
            if(session.getAttribute("username") == null){
                // Return login.jsp page
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
            // If user is already logged in, redirect to main page
            else {
                response.sendRedirect(request.getContextPath());
            }
        }

        // GET /logout path
        else if(url.equals("/logout")) {
            // Invalidate users session
            session.invalidate();
            // logout flash/Alert
            request.setAttribute("alert", "You have been logged out!");
            // Redirect to main page
            request.getRequestDispatcher("/").forward(request, response);
            //response.sendRedirect("/");
        }
    }

    /**
     * LoginLogout POST handler. handles account login request
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	// Get request url
        String url = request.getServletPath();

        // Check that post was on login path
        if(url.equals("/login")) {
        	// Get username and password params from form
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            // TODO: Enforce character lengths/limits


            // Check username / password
            if (dao.tunnus.checkPassword(username, password)) {
            	int role = dao.tunnus.checkRole(username);
                // Set session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                // Return to the main page logged in
                response.sendRedirect("/");

            } else {
                // set flash error for invalid user/pass
                request.setAttribute("alert", "Invalid username and/or password");
                // TODO: Return to login page with flash error
                response.sendRedirect("/login");
            }
        }
    }
}