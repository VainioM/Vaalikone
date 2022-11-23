package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Dao;
import Models.EhdokasDao;
import Models.Question;
import Models.QuestionDao;



/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/questions")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDao Dao=null;

	/**
	 * Initialize
	 */
	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");

		Dao = new QuestionDao(dbUrl, dbUser, dbPass);
	}
    
	/**
	 * Constructor QuestionServlet
	 */
	public QuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * doGet for sending questions to question jsp
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    		ArrayList<Question> list=null;
    		if (Dao.getConnection()) {
    		//read questions with Dao
			list=Dao.readQuestion();
			Cookie[] cookies = request.getCookies();
    		for (int i = 0; i < cookies.length; i++) {   			
    			String value = cookies[i].getName();
    			if(value.equals("Kunta")) {
    				System.out.println(cookies[i].getValue());
    			}
    		}
			System.out.println("Luettu");
    		}
    		else {
    			System.out.println("No connection to database");
    		}
    		request.setAttribute("Questionlist", list);
    		
    		//Forward questions to question page
    		RequestDispatcher rd=request.getRequestDispatcher("/questions.jsp");
    		rd.forward(request, response);

    }
    
}