package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.Dao;
import Models.Ehdokas;
import Models.EhdokasDao;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "EhdokasCRUDServlet", value = "/dashboard/ehdokas/*")
public class EhdokasCRUDServlet extends HttpServlet {
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
     * the GET handler, routing requests to correct methods based on dynamic route.
     * Defaults to sending back to dashboard
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String url = request.getPathInfo();
        
        // Get  session
        HttpSession session = request.getSession();
        
        // Get sessions role
        int Role = dao.tunnus.checkRole((String)session.getAttribute("username"));
        
        // if session does not have admin role, return back to index
        if(Role != 3){
        	response.sendRedirect("/");
        }

        try {
        	// Route to different CRUD methods
            switch (url){
                case "/new":
                	newEhdokas(request, response);
                    break;
                case "/edit":
                	getEhdokas(request, response);
                    break;
                case "/delete":
                	deleteEhdokas(request, response);
                    break;
                default:
                    response.sendRedirect("/dashboard");
                    break;
            }
        }catch (Exception e){
            // In case of error, print it and return to dashboard
            e.printStackTrace();
            response.sendRedirect("/dashboard");
        }
    }

    /**
     * the POST handler, routing requests to right method based on dynamic route
     * otherwise default to sending them back to dashboard
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
                	createEhdokas(request, response);
                    break;
                case "/edit":
                	updateEhdokas(request, response);
                    break;
                default:
                	response.sendRedirect("/dashboard");
                    break;
            }
        }catch (Exception e){
            // In case of error, print it and return to dashboard
            e.printStackTrace();
            response.sendRedirect("/dashboard");
        }
        
    }

    /**
     * the GET route to make new Ehdokas
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void newEhdokas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Returns ehdokascrud page without injecting data, thus it showing new ehdokas form
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ehdokascrud.jsp");
    	dispatcher.forward(request, response);
    }

    /**
     * the POST method to create new Ehdokas. Gets fields from form data, creates new Ehdokas object
     * with that data and send it to database dao to insert it.
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void createEhdokas(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
        // Get tunnus and salasana from from data thats used
        // to create new tunnus
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");

        // Create new Ehdokas object from posted form data
        Ehdokas ehdokas = new Ehdokas();
        ehdokas.setEtunimi(request.getParameter("etunimi"));
        ehdokas.setSukunimi(request.getParameter("sukunimi"));
        ehdokas.setPuolue(request.getParameter("puolue"));
        ehdokas.setPaikkakunta(request.getParameter("paikkakunta"));
        int ika = 0;
        try {
        	ika = Integer.parseInt(request.getParameter("ika"));
		} catch (Exception e) {

		}
        ehdokas.setIka(ika);
        ehdokas.setAmmatti(request.getParameter("ammatti"));
        ehdokas.setTunnus(tunnus);
        
        
        if(tunnus == null || salasana == null) {
        	// Error creating account, return to dashboard
        	response.sendRedirect("/dashboard");
        }

        // Get database connection
        if(ehdokasdao.getConnection()) {
            // Create new ehdokas from ehdokasdao
	        if(ehdokasdao.createEhdokas(tunnus, salasana, ehdokas)) {
                // TODO: Flash success
	        	response.sendRedirect("/dashboard");
	        }else {
                // TODO: Flash error
	        	response.sendRedirect("/dashboard");
	        }
        }
        
    }

    /**
     * The POST method to update specified ehdokas
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void updateEhdokas(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
        // Get tunnus from form data
        String tunnus = request.getParameter("tunnus");
        
        if(tunnus == null) {
        	// Error creating account, return to dashboard
        	response.sendRedirect("/dashboard");
        }

        // Create new ehdokas object from form data
        Ehdokas ehdokas = new Ehdokas();
        ehdokas.setEtunimi(request.getParameter("etunimi"));
        ehdokas.setSukunimi(request.getParameter("sukunimi"));
        ehdokas.setPuolue(request.getParameter("puolue"));
        ehdokas.setPaikkakunta(request.getParameter("paikkakunta"));
        int ika = 0;
        try {
        	ika = Integer.parseInt(request.getParameter("ika"));
		} catch (Exception e) {

		}
        ehdokas.setIka(ika);
        ehdokas.setAmmatti(request.getParameter("ammatti"));
        ehdokas.setTunnus(tunnus);

        // Get database connection
        if(ehdokasdao.getConnection()) {
            // Update ehdokas using ehdokasdao
	        if(ehdokasdao.updateEhdokas(ehdokas)) {
	            // TODO: Flash success
	        	response.sendRedirect("/dashboard");
	        }else {
                // TODO: Flash error
	        	response.sendRedirect("/dashboard");
	        }
        }
    }

    /**
     * The GET method to edit ehdokas
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void getEhdokas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ehdokascrud.jsp");
    	// Get tunnus from 'q' get param
    	String param = request.getParameter("q");

    	// If no q param found, return to dashboard
    	if(param == null){
    		System.out.println("No Params");
    	    response.sendRedirect("/dashboard");
        }
    	int id = -1;
    	try {
    		id = Integer.parseInt(param);
    	}catch(NumberFormatException e) {
    		//e.printStackTrace();
    		response.sendRedirect("/dashboard");
    	} finally {
    		
    	}

    	// Get database connection
    	if(ehdokasdao.getConnection()) {
    	    // Get filled Ehdokas object from database
    		Ehdokas ehdokas = ehdokasdao.getEhdokas(id);

    		// make sure ehdokas was found, otherwise return to dashboard
        	if(ehdokas.getEtunimi() != null) {
        	    // Send ehdokas object to template engine to be pre-filled in page
        		request.setAttribute("ehdokas", ehdokas);
        		dispatcher.forward(request, response);
        	}else {
        		response.sendRedirect("/dashboard");
        	}
    	}else{
    	    // If no database connection could be made, return to dashboard
    		System.out.println("error2");
    		response.sendRedirect("/dashboard");
    	}
    }

    /**
     * The GET method to delete ehdokas based on their tunnus
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void deleteEhdokas(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
        // Get tunnus from 'q' GET param
    	String param = request.getParameter("q");

    	// if no tunnus found, return to dashboard
    	if(param == null){
    	    // TODO: Flash error
    	    response.sendRedirect("/dashboard");
        }

    	// Get database connection
    	if(ehdokasdao.getConnection()) {
    	    // Try to delete ehdokas with dao
    		if(ehdokasdao.deleteEhdokas(param)) {
    			// Ehdokas deleted successfully
    			// TODO: Flash success
    			response.sendRedirect("/dashboard");
    		}else {
    			// Error deleting ehdokas
    			// TODO: Flash error
    			response.sendRedirect("/dashboard");
    		}
    	}else{
    	    // Not able to connect to database, return to dashboard
    	    response.sendRedirect("/dashboard");
        }
    	
    }
}
