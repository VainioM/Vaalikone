package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.Admin;
import Models.Dao;
import Models.Ehdokas;
import Models.EhdokasDao;
import Models.Question;
import Models.QuestionDao;
import Models.Tunnus;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Admin", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	private EhdokasDao ehdokasdao;
	private QuestionDao questiondao;

    /**
     * Dashboard init method. Readies the database connection for use
     */
	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");
		
		dao = new Dao(dbUrl, dbUser, dbPass);
		ehdokasdao = new EhdokasDao(dbUrl, dbUser, dbPass);
		questiondao = new QuestionDao(dbUrl, dbUser, dbPass);
	}

    /**
     * Dashboard GET handler. Searched database for data and returns it to page for admin management
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get request session
        HttpSession session = request.getSession();

        // Get sessions role
        int Role = dao.tunnus.checkRole((String)session.getAttribute("username"));
        
        // if session does not have admin role, return back to index
        if(Role != 3){
        	response.sendRedirect("/");
        }
        // Session has admin role, get admin page
        else{
            // Get all tunnukset from database and insert it in request attribute
            //List<Tunnus> listTunnukset = dao.tunnus.listTunnukset();
            //request.setAttribute("listTunnukset", listTunnukset);

            // Get all admins from database and insert it in request attribute
            List<Admin> listAdmins = dao.admins.listAdmins();
            request.setAttribute("listAdmins", listAdmins);

            // Get all ehdokkaat from database and insert it in request attribute
            List<Ehdokas> listEhdokkaat = new ArrayList<>();
            if(ehdokasdao.getConnection()) {
            	listEhdokkaat = ehdokasdao.listEhdokkaat();
            }
            request.setAttribute("listEhdokkaat", listEhdokkaat);
            
            if(questiondao.getConnection()) {
		        List<Question> listKysymykset = questiondao.readQuestion();
		        request.setAttribute("listKysymykset", listKysymykset);
            }

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Dashboard POST handler. just redirects back to dashboard in GET
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    response.sendRedirect("/dashboard");
    }
}
