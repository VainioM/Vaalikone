package Models;

public class Tunnus {
    protected String username;
    protected int oikeus;

    /**
     * Tunnus constructor
     * 
     * @param username String
     * @param oikeus int
     */
    public Tunnus(String username, int oikeus){
        this.username = username;
        this.oikeus = oikeus;
    }
    
    /**
     * Tunnus empty constructor
     */
    public Tunnus() {
    	
    }
    
    /**
     * Get tunnus username
     * @return username String
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Set tunnus username
     * @param newUsername String
     */
    public void setUsername(String newUsername){
        this.username = newUsername;
    }

    /**
     * Get oikeus
     * @return oikeus int
     */
    public int getOikeus() {
        return this.oikeus;
    }

    /**
     * Set Oikeus
     * @param newOikeus int
     */
    public void setOikeus(int newOikeus){
        this.oikeus = newOikeus;
    }
}