package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.Dao;
import Models.Ehdokas;
import Models.EhdokasDao;



/**
 * Servlet implementation class EhdokasServlet
 */
@WebServlet(urlPatterns= {"/ehdokas-sivu", "/ehdokas-sivu/*"})
public class EhdokasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EhdokasDao Dao;

	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");

		Dao = new EhdokasDao(dbUrl, dbUser, dbPass);
	}
    
	public EhdokasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Ehdokas pages GET handler, routes requests to the right methods
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	// Get request url
	    String url = request.getPathInfo();
	    
        // Get request session
        HttpSession session = request.getSession();
	    
	    if(url != null) {
		    int id = -1;
		    try {
		    	// Try to parse id from url
		    	id = Integer.parseInt(url.substring(1));
		    	showEhdokas(request, response, id);
		    	
		    }catch(NumberFormatException | SQLException e) {
		    	// Id in url is invalid, check if url is me and redirect to own page
		    	if(url.substring(1).equals("me")) {
		    		try {
						redirectProfile(request, response);
					} catch (SQLException e1) {
						e1.printStackTrace();
						response.sendRedirect("/ehdokas-sivu");
					}
		    	}else {
		    		response.sendRedirect("/ehdokas-sivu");
		    	}
		    }

		    
	    }else {
	    	try {
				showEhdokasList(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("/");
			} 
	    }
    }

	/**
	 * Ehdokas pages POST handler, routes post data to right methods
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	// Get request url
	    String url = request.getPathInfo();
	    
        // Get request session
        HttpSession session = request.getSession();
        
        if(url != null) {
		    int id = -1;
		    try {
		    	// Try to parse id from url
		    	id = Integer.parseInt(url.substring(1));
		    	updateEhdokas(request, response, id);
		    }catch(NumberFormatException | SQLException e) {
		    	// Id in url is invalid, return to list
		    	response.sendRedirect("/ehdokas-sivu");
		    }
		    
		
        }else{
        	response.sendRedirect(""+request.getServletPath()+request.getPathInfo());
        }

    }

	/**
	 * The POST handler to update Ehdokas on database from post data and return to profile page
	 *
	 * @param request
	 * @param response
	 * @param id
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
    private void updateEhdokas(HttpServletRequest request, HttpServletResponse response, int id)
            throws SQLException, IOException, ServletException {
    	
    	HttpSession session = request.getSession();    	
        // Get Database connection
	    if(Dao.getConnection()) {
	    	Ehdokas e = Dao.getEhdokas(id);
	    	// Was ehdokas found
	    	if(e.getEtunimi() != null) {
	    		// If currently logged in user matches the found ehdokas
    			if(session.getAttribute("username").equals(e.getTunnus())) {
    				Ehdokas ehdokas = new Ehdokas();
    				
    		        ehdokas.setEtunimi(request.getParameter("etunimi"));
    		        ehdokas.setSukunimi(request.getParameter("sukunimi"));
    		        ehdokas.setPuolue(request.getParameter("puolue"));
    		        ehdokas.setPaikkakunta(request.getParameter("paikkakunta"));
    		        int ika = 0;
    		        try {
    		        	ika = Integer.parseInt(request.getParameter("ika"));
    				} catch (Exception ex) {

    				}
    		        ehdokas.setIka(ika);
    		        ehdokas.setAmmatti(request.getParameter("ammatti"));
    		        ehdokas.setTunnus(e.getTunnus());
    		        
    		        // Update ehdokas using ehdokasdao
    		        if(Dao.updateEhdokas(ehdokas)) {
    		        	response.sendRedirect(""+request.getServletPath()+request.getPathInfo());
    		        }
    			}
	    	}
	    }
    }

	/**
	 * GET handler for /me route. used by candidates to redirect to their own page
	 *
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
    private void redirectProfile(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	HttpSession session = request.getSession();    	
    	
		String username = (String)session.getAttribute("username");
		if(username == null) {
			response.sendRedirect("/ehdokas-sivu");
		}else {
    		if(Dao.getConnection()) {
    			Ehdokas ehdokas = Dao.getEhdokas(username);
    			if(ehdokas.getEtunimi() != null) {
    				String redirectUrl = "/ehdokas-sivu/"+ehdokas.getId();
    				response.sendRedirect(redirectUrl);
    			}else {
    				response.sendRedirect("/ehdokas-sivu");
    			}
    		}
		}
    }

	/**
	 * GET route to list all candidates in a single page with links to learn more
	 *
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
    private void showEhdokasList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		ArrayList<Ehdokas> list=null;
		if (Dao.getConnection()) {
			list=Dao.listEhdokkaat();
			System.out.println("Luettu");
		}
		else {
			System.out.println("No connection to database");
		}
		request.setAttribute("Ehdokaslist", list);
		
		RequestDispatcher rd=request.getRequestDispatcher("/ehdokas-sivu.jsp");
		rd.forward(request, response);
    }

	/**
	 * GET page for individual candidate information or for candidate to edit their page
	 *
	 * @param request
	 * @param response
	 * @param id
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
    private void showEhdokas(HttpServletRequest request, HttpServletResponse response, int id)
            throws SQLException, IOException, ServletException {
    	
    	HttpSession session = request.getSession();    	
    	
    	// Get Database connection
	    if(Dao.getConnection()) {
	    	Ehdokas e = Dao.getEhdokas(id);
	    	if(e.getEtunimi() != null) {
	    		String username = (String)session.getAttribute("username");
    			if(e.getTunnus().equals(username)) {
    				// Profile owner - Check if its for edit
		    		if(request.getParameter("edit") != null) {
		    			// Profile owner - requesting to edit page
				    	request.setAttribute("ehdokas", e);
				    	request.setAttribute("edit", true);
				    	RequestDispatcher rd = request.getRequestDispatcher("/ehdokas-profile.jsp");
				    	rd.forward(request, response);
		    		}else {
		    			// Profile owner - just to see page
				    	request.setAttribute("ehdokas", e);
				    	request.setAttribute("owner", true);
				    	RequestDispatcher rd = request.getRequestDispatcher("/ehdokas-profile.jsp");
				    	rd.forward(request, response);
		    		}
	    		}else {
	    			// Normal user - Show page
			    	request.setAttribute("ehdokas", e);
			    	RequestDispatcher rd = request.getRequestDispatcher("/ehdokas-profile.jsp");
			    	rd.forward(request, response);
	    		}
	    	}else {
	    		response.sendRedirect("/ehdokas-sivu");
	    	}
	    	
	    }
    }
}