package Models;

public class Admin {
    protected int id;
    protected String tunnus;
    protected String nimi;

    /**
     * Admin empty constructor
     */
    public Admin(){

    }

    /**
     * get admin id
     * @return id int
     */
    public int getId(){ return this.id; }
    
    /**
     * set admin id
     * @param id
     */
    public void setId(int id) { this.id = id; }
    
    /**
     * get admin username
     * @return username String
     */
    public String getTunnus() {return this.tunnus; }
    
    /**
     * set admin username
     * @param tunnus Sring
     */
    public void setTunnus(String tunnus){ this.tunnus = tunnus; }
    
    /**
     *  get admin name
     * @return name String
     */
    public String getNimi() { return this.nimi; }
    
    /**
     * set admin name
     * @param nimi String
     */
    public void setNimi(String nimi) { this.nimi = nimi; }
    
}