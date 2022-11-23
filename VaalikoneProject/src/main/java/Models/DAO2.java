package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 * @deprecated
 * Was used before index page functionalities were added to Dao.java
 *
 */
@Deprecated
public class DAO2 {
	
	private String url = "jdbc:mysql://localhost:3306/vaalikone10?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";;
	private String user = "user";
	private String pass = "salasana";
	private Connection conn;
	
	private static String _query = "select*from Kunnat";
	
	public DAO2() {
		
	}
	
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
	
	public ArrayList<Kunta> readKunta() {
		ArrayList<Kunta> list=new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS=stmt.executeQuery(_query);
			System.out.println(stmt);
			
			while (RS.next()) {
				Kunta e=new Kunta();
				e.setNimi(RS.getString("Nimi"));
				list.add(e);
			}
			return list;
		}
		catch (SQLException e) {
			return null;
		}
		
		
		}

}
