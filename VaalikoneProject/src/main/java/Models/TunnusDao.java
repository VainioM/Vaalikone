package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class TunnusDao {

    private Connection conn;

    /**
     * TunnusDao constructor
     * @param conn Connection
     */
    public TunnusDao(Connection conn){
        this.conn = conn;
    }


    /**
     * Create new Tunnus with given username, password and oikeus
     *
     * @param username String
     * @param password String
     * @param oikeus int
     * @return if user is created
     */
    public boolean createTunnus(String username, String password, int oikeus){

        String query = "INSERT INTO Tunnukset (Tunnus, Salasana, Oikeus) VALUES (?, ?, ?)";

        if(username == "" || password == ""){
            return false;
        }

        try {
            // Hash password before inserting
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());

            // Prepare sql and insert values to query
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, hashedpw);
            stmt.setInt(3, oikeus);

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
     * Looks for every tunnus from database and returns them in a list
     *
     * @return list of users
     */
    public List<Tunnus> listTunnukset(){
        List<Tunnus> listTunnukset = new ArrayList<>();

        String query = "SELECT * from Tunnukset";

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                String tunnus = rs.getString("Tunnus");
                int oikeus = rs.getInt("Oikeus");

                Tunnus user = new Tunnus(tunnus, oikeus);
                listTunnukset.add(user);
            }
            stmt.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return listTunnukset;
    }

    /**
     * checkPassword method returns if given username / password combo match
     * passwords are hashed using bcrypt, so method from there is used to check
     * passwords matching
     *
     * @param username String
     * @param password String
     * @return if username/password match
     */
    public boolean checkPassword(String username, String password) {

        // SQL query
        String query = "SELECT Salasana FROM Tunnukset WHERE Tunnus = ?";
        String hashedpw = null;
        
        if(username == "" || password == "") {
        	return false;
        }


        try {
            // Init statement to inject params to query
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            // Execute statement and get ResultSet
            ResultSet rs = stmt.executeQuery();

            // If rows are found, save it to variable
            if (rs.next()) {
                hashedpw = rs.getString("Salasana");
            }
            // On SQL error, print stacktrace
        }catch (SQLException e){
            e.printStackTrace();
        }

        // If hashed password is returned from DB
        if(hashedpw != null) {
            // Check given password with hashed password from database
            if(BCrypt.checkpw(password, hashedpw)) {
                // Passwords match!
                return true;
            }else {
                // Passwords don't match :(
                return false;
            }
        }else {
            // Username didn't return password
            return false;
        }

    }

    /**
     * Given username, returns the users role as int based on roles from vaalikone10.sql
     * if given username is empty, return 0 as unauthenticated user
     * Roles: 0 = Unauthenticated, 1 = Ehdokas, 2 = Puolue, 3 = Admin
     *
     * @param username String
     * @return role int
     */
    public int checkRole(String username) {
        if(username == null || username == ""){
            return 0;
        }

        String query = "SELECT Oikeus FROM Tunnukset WHERE Tunnus = ?";
        int role = 0;

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                role = rs.getInt("Oikeus");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return role;
    }

}
