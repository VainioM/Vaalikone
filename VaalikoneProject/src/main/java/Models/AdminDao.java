package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class AdminDao {

    private Connection conn;

    /**
     * Admins dao contructor.
     *
     * @param conn Connection
     */
    public AdminDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Method for creating new Tunnus and Admin accounts
     *
     * @param username String
     * @param password String
     * @param nimi String
     * @return boolean if it created admin account
     */
    public boolean createAdmin(String username, String password, String nimi){

        if(username== "" || password == ""){
            return false;
        }

        TunnusDao tunnusdao = new TunnusDao(conn);
        if(tunnusdao.createTunnus(username, password, 3)){
            String query = "INSERT INTO Adminit (Nimi, Tunnus) VALUES (?, ?)";

            try{
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nimi);
                stmt.setString(2, username);
                boolean rowInserted = stmt.executeUpdate() > 0;
                stmt.close();
                return rowInserted;

            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Looks for every admin from database and returns them in a list
     *
     * @return list of admins as AdminsModel
     */
    public List<Admin> listAdmins(){
        List<Admin> listAdmins = new ArrayList<>();

        String query = "SELECT * from Adminit";

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Admin admin = new Admin();
                admin.setId(rs.getInt("Admin_ID"));
                admin.setTunnus(rs.getString("Tunnus"));
                admin.setNimi(rs.getString("Nimi"));

                listAdmins.add(admin);
            }
            stmt.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return listAdmins;
    }

    /**
     * Looks for specified admin account and returns AdminsModel
     *
     * @param tunnus String
     * @return  admin AdminsModel
     */
    public Admin getAdmin(String tunnus){
        Admin admin = new Admin();

        String query = "SELECT Admin_ID, Tunnus, Nimi FROM Adminit WHERE Tunnus = ?";
        
        if(tunnus == "" || tunnus == null) {
        	return admin;
        }

        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tunnus);
            
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                admin.setId(rs.getInt("Admin_ID"));
                admin.setTunnus(rs.getString("Tunnus"));
                admin.setNimi(rs.getString("Nimi"));
            }
            stmt.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return admin;
    }

    /**
     * Update admins name using their tunnus.
     *
     * @param tunnus String
     * @param nimi String
     * @return boolean if admins information was updated
     */
    public boolean updateAdmin(String tunnus, String nimi) {
    	String query = "UPDATE Adminit SET Nimi = ? WHERE Tunnus = ?";
    	boolean updated = false;
    	
    	try {
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setString(1, nimi);
    		stmt.setString(2, tunnus);
    		updated = stmt.executeUpdate() > 0;
    		stmt.close();
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return updated;
    }

    /**
     * Delete admin based from their tunnus
     *
     * @param tunnus String
     * @return boolean if the admins data was deleted
     */
    public boolean deleteAdmin(String tunnus) {
    	
    	String query = "DELETE FROM Adminit WHERE Tunnus = ?";
    	String query1 = "DELETE FROM Tunnukset WHERE Tunnus = ?";
    	boolean deleted = false;
    	
    	if(tunnus == "" || tunnus == null) {
    		return false;
    	}
    	
    	try {
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setString(1, tunnus);
    		deleted = stmt.executeUpdate() > 0;
    		stmt.close();
    		if(deleted) {
    			PreparedStatement stmt1 = conn.prepareStatement(query1);
    			stmt1.setString(1, tunnus);
    			deleted = stmt1.executeUpdate() > 0;
    			stmt1.close();
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	
    	return deleted;
    }

}