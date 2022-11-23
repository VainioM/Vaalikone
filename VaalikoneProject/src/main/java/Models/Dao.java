package Models;

import java.sql.*;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

public class Dao {
    private Connection conn;

    public TunnusDao tunnus;
    public AdminDao admins;
    /**
     * SQL statement for getting all 'kunta' objects from 'Kunnat' table 
     */
    private static final String _query = "select*from Kunnat";

    public Dao(String dbUrl, String dbUsername, String dbPassword) {
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            
            tunnus = new TunnusDao(conn);
            admins = new AdminDao(conn);
        }catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Closes the database connection
     */
    public void close() {
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * 
     * @return ArrayList<Kunta>
     * 
     * 
     */
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
