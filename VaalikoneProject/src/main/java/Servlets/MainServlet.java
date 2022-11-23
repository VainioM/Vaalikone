package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.Kunta;
import Models.Dao;
import Models.EhdokasDao;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main Servlet
 */
@WebServlet(name = "MainServlet", value = "/")
public class MainServlet extends HttpServlet {
	private Dao dao;

	/**
	 * Gets database driver and tries to get connection to database
	 */
	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");
		
		dao = new Dao(dbUrl, dbUser, dbPass);
	}

	/**
	 * MainServlet GET handler. shows the main page
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ArrayList<Kunta> list = null;
		list=dao.readKunta();
		request.setAttribute("kuntaList", list);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);	
    }

	/**
	 * Main page POST handler.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
