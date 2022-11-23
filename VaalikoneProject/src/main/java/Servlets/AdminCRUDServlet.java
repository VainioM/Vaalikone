package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.Admin;
import Models.Dao;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminCRUDServlet", value = "/dashboard/admin/*")
public class AdminCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao;

    /**
     * Servlet init method. Readies the database connection for use
     */
	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");
		
		dao = new Dao(dbUrl, dbUser, dbPass);
	}

    /**
     * AdminCRUD GET handler. routes requests to right methods.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        
        // Get request session
        HttpSession session = request.getSession();

        
        // Get sessions role
        int Role = dao.tunnus.checkRole((String)session.getAttribute("username"));
        
        // if session does not have admin role, return back to index
        if(Role != 3){
        	response.sendRedirect("/");
        }

        try {
        	// Route to different CRUD routes
            switch (url){
                case "/new":
                	newAdmin(request, response);
                    break;
                case "/edit":
                	getAdmin(request, response);
                    break;
                case "/delete":
                	deleteAdmin(request, response);
                    break;
                default:
                	RequestDispatcher dispatcher = request.getRequestDispatcher("/admincrud.jsp");
                	dispatcher.forward(request, response);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * AdminCRUD POST handler. routes POST methods.
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String url = request.getPathInfo();
        
        // Get request session
        HttpSession session = request.getSession();

        
        // Get sessions role
        int Role = dao.tunnus.checkRole((String)session.getAttribute("username"));
        
        // if session does not have admin role, return back to index
        if(Role != 3){
        	response.sendRedirect("/");
        }
        
        try {
        	// Route to different CRUD routes
            switch (url){
                case "/new":
                	createAdmin(request, response);
                    break;
                case "/edit":
                	updateAdmin(request, response);
                    break;
                default:
                	response.sendRedirect("/dashboard");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    /**
     * GET method to show new admin creation page
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void newAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/admincrud.jsp");
    	dispatcher.forward(request, response);
    }

    /**
     * POST method to create new admin. takes data from parameters to create nww admin on database
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void createAdmin(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
        String tunnus = request.getParameter("tunnus");
        String nimi = request.getParameter("name");
        String salasana = request.getParameter("salasana");
        
        if(tunnus == null || salasana == null) {
        	// Error creating account, return to dashboard
        	response.sendRedirect("/dashboard");
        }
        
        if(dao.admins.createAdmin(tunnus, salasana, nimi)) {
        	response.sendRedirect("/dashboard");
        }else {
        	response.sendRedirect("/dashboard");
        }
        
    }

    /**
     * POST method to update existing admins information
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
        String tunnus = request.getParameter("tunnus");
        String nimi = request.getParameter("name");
        
        if(tunnus == null || nimi == null) {
        	// Error creating account, return to dashboard
        	response.sendRedirect("/dashboard");
        }
        
        if(dao.admins.updateAdmin(tunnus, nimi)) {
        	response.sendRedirect("/dashboard");
        }else {
        	response.sendRedirect("/dashboard");
        }
    }

    /**
     * GET method to show edit admin information page
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void getAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/admincrud.jsp");
    	String param = request.getParameter("q");
    	if(param != null) {
    		Admin admin = dao.admins.getAdmin(param);
        	if(admin.getNimi() != null) {
        		request.setAttribute("admin", admin);
        		dispatcher.forward(request, response);
        	}else {
        		response.sendRedirect("/dashboard");
        	}
    	}else{
    		response.sendRedirect("/dashboard");
    	}
    }

    /**
     * GET method to delete existing admin based on their tunnus
     * tunnus is retrieved from GET parameter named 'q'
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	String param = request.getParameter("q");
    	if(param != null) {
    		if(dao.admins.deleteAdmin(param)) {
    			// Admin deleted successfully
    			// TODO: Add confirmation flash?
    			response.sendRedirect("/dashboard");
    		}else {
    			// Error deleting admin, return to dashboard
    			// TODO: add error flash?
    			response.sendRedirect("/dashboard");
    		}
    	}
    	
    }
}
