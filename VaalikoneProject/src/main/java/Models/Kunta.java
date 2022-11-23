package Models;
/**
 * 
 * Class contains the necessary information about the kunta objects.
 * Kunta objects are used to reference the database objects in Kunnat table.
 *
 */
public class Kunta {
	protected String nimi;
	/**
	 * Default constructor
	 */
	public Kunta() {
		
	}
	/**
	 * 
	 * @return nimi The name of the kunta object
	 */
	public String getNimi() {
		return this.nimi;
	}
	/**
	 * 
	 * @param a Sets the name of the kunta object
	 */
	public void setNimi(String a) {
		this.nimi = a;
	}
}



