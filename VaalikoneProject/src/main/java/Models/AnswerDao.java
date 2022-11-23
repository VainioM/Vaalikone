package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AnswerDao {
	
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	
	//Query for deciding the best candidate
	private static final String ANSWER_QUERY = "SELECT ROUND(((sum(val)-48)*100)/(0-48),1) AS precent, Ehdokas_ID \r\n" + 
			"FROM (\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 1\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 2\r\n" + 
			"		GROUP BY Ehdokas_ID\r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 3\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 4\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 5\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 6\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 7\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 8\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 9\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 10\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 11\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT ABS(Vastaus - ?) AS val, Ehdokas_ID\r\n" + 
			"		FROM Vastaukset t\r\n" + 
			"		WHERE Kysymys_ID = 12\r\n" + 
			"		GROUP BY Ehdokas_ID \r\n" + 
			") t\r\n" + 
			"GROUP BY Ehdokas_ID\r\n"+
			"ORDER BY precent DESC";
	
	
	/**
	 * Constuctor AnswerDao
	 * @param url
	 * @param user
	 * @param pass
	 */
	public AnswerDao(String url, String user, String pass) {
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
	 * Calculate the best candidate based on list of user answers
	 * @param list
	 * @return best candidate as answer
	 */
	public String calculateCandidate(ArrayList<String> list) {
		
		String answer = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(ANSWER_QUERY);
			
    		for (int i = 0; i < list.size(); i++) {
    			stmt.setInt(i+1, Integer.parseInt(list.get(i)));
    		}
    		ResultSet rs = stmt.executeQuery();
			
    		if(rs.next()) {
    			answer = rs.getString("Ehdokas_ID");
    		}
			
			return answer;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		}
	
	
	
}

