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

import Models.AnswerDao;
import Models.Question;
import Models.Dao;
import Models.EhdokasDao;
import Models.QuestionDao;


/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet(name = "AnswerServlet", urlPatterns="/answers")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AnswerDao Dao=null;
	String idString = "1";
	
	/**
	 * Initialize
	 */
	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");

		Dao = new AnswerDao(dbUrl, dbUser, dbPass);
	}
	
	
	public AnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	
	/**
	 * Answer page POST handler, routes post data to right methods
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	
	/**
	 * Gets answers as a list and uses calculateCandidate from QuestionDao and redirects to the best candidate
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	//Create a list for the answers
    	ArrayList<String> answersList = new ArrayList<String>();
    	String answer = null;
    	
    	//Loop through the 12 answers
    	for (int i = 1; i <= 12; i++) {
    	
    	String questionID=request.getParameter(i+"");
    	
    	
    	//add answer to the list
    	answersList.add(questionID);
    	
    	//System.out.println(idInt);
    	
    	//Print if the answer is null
    	if(questionID == null) {
    		
    		System.out.println("Answer empty");
    		
    		}else {
    		//Print question ID
    		System.out.println(questionID);
    		
    		}
    	
    	}
    	
    	//Prints answers and query answer in console
		System.out.println("List of answers: " + answersList);
		if(Dao.getConnection()) {
    		answer = Dao.calculateCandidate(answersList);
    		
    		System.out.println("Answer from query:" + answer);
		}

		//redirection to best candidate based on id from database
		response.sendRedirect("/ehdokas-sivu/" + answer);
		
    		
    }
    
    
    /**
     * the GET handler, routing requests to correct methods based on dynamic route.
     * Sending to /questions page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    
    /**
     * doGet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        response.sendRedirect("/questions");
    	    } 
    
}