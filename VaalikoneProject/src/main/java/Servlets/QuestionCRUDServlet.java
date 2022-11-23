package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.Admin;
import Models.Dao;
import Models.Question;
import Models.QuestionDao;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "QuestionCRUDServlet", value = "/dashboard/kysymykset/*")
public class QuestionCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	private QuestionDao questiondao;
	
	public void init() {
		String dbUrl = getServletContext().getInitParameter("jdbcURL");
		String dbUser = getServletContext().getInitParameter("jdbcUser");
		String dbPass = getServletContext().getInitParameter("jdbcPass");
		
		dao = new Dao(dbUrl, dbUser, dbPass);
		questiondao = new QuestionDao(dbUrl, dbUser, dbPass);
	}

    /**
     * QuestionCRUD GET handler. routes requests to their methods
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
                	newQuestion(request, response);
                    break;
                case "/edit":
                	getQuestion(request, response);
                    break;
                case "/delete":
                	deleteQuestion(request, response);
                    break;
                default:
                	response.sendRedirect("/dashboard");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/dashboard");
        }
    }

    /**
     * QuestionCRUD POST handler. Routes requests to their methods.
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
                	createQuestion(request, response);
                    break;
                case "/edit":
                	updateQuestion(request, response);
                    break;
                default:
                	response.sendRedirect("/dashboard");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/dashboard");
        }
        
    }
    
    /**
     * GET method to show new question page
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void newQuestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/questioncrud.jsp");
    	dispatcher.forward(request, response);
    }
    
    /**
     * POST method to create new questions
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void createQuestion(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
        String kysymys = request.getParameter("kysymys");
        
        if(kysymys.isEmpty()) {
        	// Error creating question, return to dashboard
        	response.sendRedirect("/dashboard");
        }
        if(questiondao.getConnection()) {
            if(questiondao.createQuestion(kysymys)) {
            	response.sendRedirect("/dashboard");
            }else {
            	response.sendRedirect("/dashboard");
            }
        }
    }
    
    /**
     * POST method to update an already existing questions text
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void updateQuestion(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException{
        String tempid = request.getParameter("id");
        String kysymys = request.getParameter("kysymys");
        
        if(kysymys.isEmpty()) {
        	// Error updating question, return to dashboard
        	response.sendRedirect("/dashboard");
        }
        try {
        	int id = Integer.parseInt(tempid);
        	if(questiondao.getConnection()) {
            	if(questiondao.updateQuestion(id, kysymys)) {
                	response.sendRedirect("/dashboard");
                }
        	}
        	response.sendRedirect("/dashboard");
        }catch(NumberFormatException e) {
        	e.printStackTrace();
        	response.sendRedirect("/dashboard");
        }

    }
    
    /**
     * GET method to show pre-filled page for updating questions
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void getQuestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/questioncrud.jsp");
    	String param = request.getParameter("q");
    	try {
    		int id = Integer.parseInt(param);
    		if(questiondao.getConnection()) {
    			Question q = questiondao.getQuestion(id);
    			if(!q.getQuestion().isEmpty()) {
    	    		request.setAttribute("question", q);
    	    		dispatcher.forward(request, response);
    			}else {
    				response.sendRedirect("/dashboard");
    			}
    		}
    		
    	}catch(NumberFormatException e) {
    		//e.printStackTrace();
    		response.sendRedirect("/dashboard");
    	}

    }
    
    /**
     * GET method to delete existing question based on id
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	String param = request.getParameter("q");
    	try {
    		int id = Integer.parseInt(param);
    		if(questiondao.getConnection()) {
    			if(questiondao.deleteQuestion(id)) {
    				response.sendRedirect("/dashboard");
    			}else {
    				response.sendRedirect("/dashboard");
    			}
    		}
    		
    	}catch(NumberFormatException e) {
    		//e.printStackTrace();
    		response.sendRedirect("/dashboard");
    	}
    	
    }
}
