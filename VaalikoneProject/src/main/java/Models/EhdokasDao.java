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

import Models.Ehdokas;


public class EhdokasDao {
	
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	private static final String SELECT_EHDOKAS = "select * from Ehdokkaat where Ehdokas_ID=1";

	/**
	 * EhdokasDao Constructor.
	 *
	 * @param url database uri
	 * @param user database username
	 * @param pass database password
	 */
	public EhdokasDao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}

	/**
	 * Gets database driver and tries to get connection to database
	 *
	 * @return boolean if database connection is established
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
	 * Gets first ehdokas from database
	 *
	 * @return ArrayList<Ehdokas> list
	 */
	public ArrayList<Ehdokas> readEhdokas() {
		ArrayList<Ehdokas> list=new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS=stmt.executeQuery(SELECT_EHDOKAS);
			System.out.println(stmt);
			
			// Step 3: Execute the query or update query
			
			
			while (RS.next()) {
				Ehdokas e=new Ehdokas();
				e.setId(RS.getInt("Ehdokas_ID"));
				e.setEtunimi(RS.getString("Etunimi"));
				e.setSukunimi(RS.getString("Sukunimi"));
				e.setPuolue(RS.getString("Puolue"));
				e.setPaikkakunta(RS.getString("Paikkakunta"));
				e.setIka(RS.getInt("Ika"));
				e.setAmmatti(RS.getString("Ammatti"));
				e.setTunnus(RS.getString("Tunnus"));
				list.add(e);
			}
			return list;
		}
		catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Get filled Ehdokas object based on given id,
	 * or empty object if error
	 *
	 * @param id Ehdokas_ID
	 * @return Ehdokas object
	 */
	public Ehdokas getEhdokas(int id) {
		// Pre make ehdokas object
		Ehdokas ehdokas = new Ehdokas();

		// create sql query
		String query = "SELECT * FROM Ehdokkaat WHERE Ehdokas_ID = ?";


		try {
			// create statement, insert tunnus to query
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);

			// Get resultset from database query
			ResultSet rs=stmt.executeQuery();

			// if data was returned from database, insert into ehdokas object
			if (rs.next()) {
				ehdokas.setId(rs.getInt("Ehdokas_ID"));
				ehdokas.setEtunimi(rs.getString("Etunimi"));
				ehdokas.setSukunimi(rs.getString("Sukunimi"));
				ehdokas.setPuolue(rs.getString("Puolue"));
				ehdokas.setPaikkakunta(rs.getString("Paikkakunta"));
				ehdokas.setIka(rs.getInt("Ika"));
				ehdokas.setAmmatti(rs.getString("Ammatti"));
				ehdokas.setTunnus(rs.getString("Tunnus"));
			}
		}
		catch (SQLException e) {
			// if sql error, print error and return empty ehdokas
			e.printStackTrace();
			return ehdokas;
		}

		// Return filled out ehdokas object
		return ehdokas;
	}
	
	/**
	 * Get filled Ehdokas object based on given tunnus,
	 * or empty object if error
	 *
	 * @param tunnus String
	 * @return Ehdokas object
	 */
	public Ehdokas getEhdokas(String tunnus) {
		// Pre make ehdokas object
		Ehdokas ehdokas = new Ehdokas();

		// create sql query
		String query = "SELECT * FROM Ehdokkaat WHERE Tunnus = ?";
		
    	// If tunnus is not correct, return false
    	if(tunnus == "" || tunnus == null) {
    		return ehdokas;
    	}


		try {
			// create statement, insert tunnus to query
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, tunnus);

			// Get resultset from database query
			ResultSet rs=stmt.executeQuery();

			// if data was returned from database, insert into ehdokas object
			if (rs.next()) {
				ehdokas.setId(rs.getInt("Ehdokas_ID"));
				ehdokas.setEtunimi(rs.getString("Etunimi"));
				ehdokas.setSukunimi(rs.getString("Sukunimi"));
				ehdokas.setPuolue(rs.getString("Puolue"));
				ehdokas.setPaikkakunta(rs.getString("Paikkakunta"));
				ehdokas.setIka(rs.getInt("Ika"));
				ehdokas.setAmmatti(rs.getString("Ammatti"));
				ehdokas.setTunnus(rs.getString("Tunnus"));
			}
		}
		catch (SQLException e) {
			// if sql error, print error and return empty ehdokas
			e.printStackTrace();
			return ehdokas;
		}

		// Return filled out ehdokas object
		return ehdokas;
	}

	/**
	 * Method for updating an existing ehdokas from database
	 *
	 * @param ehdokas Ehdokas
	 * @return boolean if ehdokas was updated
	 */
    public boolean updateEhdokas(Ehdokas ehdokas) {
    	// create SQL query
    	String query = "UPDATE Ehdokkaat SET ";
    	query += "Etunimi = ?, ";
    	query += "Sukunimi = ?, ";
    	query += "Puolue = ?, ";
    	query += "Paikkakunta = ?, ";
    	query += "Ika = ?, ";
    	query += "Ammatti = ? ";
    	query += "WHERE Tunnus = ?";

    	boolean updated = false;

    	try {
			// create statement, insert gotten data from ehdokas
			PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setString(1, ehdokas.getEtunimi());
    		stmt.setString(2, ehdokas.getSukunimi());
    		stmt.setString(3, ehdokas.getPuolue());
    		stmt.setString(4, ehdokas.getPaikkakunta());
    		stmt.setInt(5, ehdokas.getIka());
    		stmt.setString(6, ehdokas.getAmmatti());
    		stmt.setString(7, ehdokas.getTunnus());

    		// Execute the statement
    		updated = stmt.executeUpdate() > 0;
    		stmt.close();

    	}catch(SQLException e) {
			// if sql error, print error
    		e.printStackTrace();
    	}

    	// Return if data was updated
    	return updated;
    }

	/**
	 * Method for creating new Ehdokas accounts with tunnus
	 *
	 * @param username String
	 * @param salasana String
	 * @param ehdokas Ehdokas
	 * @return boolean if acccount is created
	 */
	public boolean createEhdokas(String username, String salasana, Ehdokas ehdokas){

		// if given ehdokas object, username or salasana are empty, return false
        if(ehdokas.getTunnus() == null || username == "" || salasana == ""){
            return false;
        }

        // create new tunnus before creating new ehdokas, as they are linked
        TunnusDao tunnusdao = new TunnusDao(conn);
        if(tunnusdao.createTunnus(username, salasana, 1)){
        	// create SQL query
            String query = "INSERT INTO Ehdokkaat (Etunimi, Sukunimi, Puolue, Paikkakunta, Ika, Ammatti, Tunnus) ";
            query += "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try{
            	// Create new ehdokas object, insert gotten data from ehdokas
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, ehdokas.getEtunimi());
                stmt.setString(2, ehdokas.getSukunimi());
                stmt.setString(3, ehdokas.getPuolue());
                stmt.setString(4, ehdokas.getPaikkakunta());
                stmt.setInt(5, ehdokas.getIka());
                stmt.setString(6, ehdokas.getAmmatti());
                stmt.setString(7, username);

                // Execute statement and return if data was inserted
                boolean rowInserted = stmt.executeUpdate() > 0;
                stmt.close();
                return rowInserted;

            }catch (SQLException e){
				// if sql error, print error and return false
                e.printStackTrace();
                return false;
            }
        }else{
        	// if creating tunnus fails, return false
            return false;
        }
    }

	/**
	 * Get all ehdokkaat from database, and return them as a list
	 *
	 * @return List<Ehdokas>
	 */
	public ArrayList<Ehdokas> listEhdokkaat() {
		// Create empty Ehdokas list
		ArrayList<Ehdokas> list=new ArrayList<>();
		// Create sql query
		String query = "select * from Ehdokkaat";

		try {
			// create statement and execute the query
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);

			// While there are rows of data
			while (rs.next()) {
				// Create new ehdokas object, insert gotten data from resultset and add to the list
				Ehdokas e=new Ehdokas();
				e.setId(rs.getInt("Ehdokas_ID"));
				e.setEtunimi(rs.getString("Etunimi"));
				e.setSukunimi(rs.getString("Sukunimi"));
				e.setPuolue(rs.getString("Puolue"));
				e.setPaikkakunta(rs.getString("Paikkakunta"));
				e.setIka(rs.getInt("Ika"));
				e.setAmmatti(rs.getString("Ammatti"));
				e.setTunnus(rs.getString("Tunnus"));
				list.add(e);
			}
			return list;
		}
		catch (SQLException e) {
			// if sql error, print error and return false
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Database method to delete ehdokas and the connected tunnus
	 *
	 * @param tunnus String
	 * @return boolean if both deletes are successful
	 */
	public boolean deleteEhdokas(String tunnus) {
		// Create both needed sql querys
    	String query = "DELETE FROM Ehdokkaat WHERE Tunnus = ?";
    	String query1 = "DELETE FROM Tunnukset WHERE Tunnus = ?";
    	boolean deleted = false;

    	// If tunnus is not correct, return false
    	if(tunnus == "" || tunnus == null) {
    		return false;
    	}

    	try {
    		// Prepare statement, insert fields into statement and execute query
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setString(1, tunnus);
    		deleted = stmt.executeUpdate() > 0;
    		stmt.close();

    		// If first query is succesful
    		if(deleted) {
				// Prepare statement, insert fields into statement and execute query
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

