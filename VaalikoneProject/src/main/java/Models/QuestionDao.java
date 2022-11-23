package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

import Models.Ehdokas;

/**
 * Class QuestionDao
 * @author vilts
 *
 */
public class QuestionDao {
	
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	//query for selecting questions
	private static final String SELECT_QUESTION = "select Kysymys_ID,Kysymys from Kysymykset";
	
	/**
	 * Constructor QuestionDao
	 * @param url
	 * @param user
	 * @param pass
	 */
	public QuestionDao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	/**
	 * Get connection
	 * @return
	 */
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	                throw new SQLException(e);
	            }
	            conn = DriverManager.getConnection(url, user, pass);
	        }
	        return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	

	/**
	 * Reads questions from database, creates an arraylist from them and returns the list
	 * @return list of questions
	 */
	public ArrayList<Question> readQuestion() {
		ArrayList<Question> list=new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS=stmt.executeQuery(SELECT_QUESTION);
			System.out.println(stmt);
			
			// Step 3: Execute the query or update query
			
			
			while (RS.next()) {
				Question e=new Question();
				e.setQuestionID(RS.getInt("Kysymys_ID"));
				e.setQuestion(RS.getString("Kysymys"));
				list.add(e);
			}
			return list;
		}
		catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Sends the question for the question page
	 * @param id
	 * @return question object
	 */
	public Question getQuestion(int id) {
		String query = "SELECT * FROM Kysymykset WHERE Kysymys_ID = ?";
		Question q = new Question();
		
		try {
			//prepare sql
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			
			//Set question id
			if(rs.next()) {
				q.setQuestionID(rs.getInt("Kysymys_ID"));
				q.setQuestion(rs.getString("Kysymys"));

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return q;
	}
	
	/**
	 * Create more questions into database
	 * @param kysymys
	 * @return
	 */
    public boolean createQuestion(String kysymys){
    	//query for creating questions to database
        String query = "INSERT INTO Kysymykset (Kysymys) VALUES (?)";

        // Make sure question string isnt empty
        if(kysymys.isEmpty()) {
        	return false;
        }

        try {
            // Prepare sql and insert values to query
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kysymys);


            // Execute statement
            boolean rowInserted = stmt.executeUpdate() > 0;
            stmt.close();
            return rowInserted;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 
     * @param id
     * @return
     */
	public boolean deleteQuestion(int id) {
		// Create both needed sql querys
    	String query = "DELETE FROM Kysymykset WHERE Kysymys_ID = ?";

    	try {
    		// Prepare statement, insert fields into statement and execute query
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setInt(1, id);
    		boolean rowsDeleted = stmt.executeUpdate() > 0;
    		stmt.close();
    		return rowsDeleted;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}

    }
	
	/**
	 * Update new values to questions
	 * @param id
	 * @param uusiKysymys
	 * @return updated
	 */
    public boolean updateQuestion(int id, String uusiKysymys) {
    	String query = "UPDATE Kysymykset SET Kysymys = ? WHERE Kysymys_ID = ? ";
    	boolean updated = false;
    	
    	if(uusiKysymys.isEmpty()) {
    		return updated;
    	}
    	
    	try {
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setString(1, uusiKysymys);
    		stmt.setInt(2, id);
    		updated = stmt.executeUpdate() > 0;
    		stmt.close();
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		return updated;

    }
	
	
	
}

