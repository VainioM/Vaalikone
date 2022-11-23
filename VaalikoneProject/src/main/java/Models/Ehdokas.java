package Models;

public class Ehdokas {
	
	private int Ehdokas_ID;
	private String Etunimi;
	private String Sukunimi;
	private String Puolue;
	private String Paikkakunta;
	private int Ika;
	private String Ammatti;
	private String Tunnus;

	/**
	 * Ehdokas contrustor
	 *
	 * @param Ehdokas_ID
	 * @param Etunimi
	 * @param Sukunimi
	 * @param Puolue
	 */
	public Ehdokas(String Ehdokas_ID, String Etunimi, String Sukunimi, String Puolue) {
    
		setId(Ehdokas_ID);
		this.Etunimi=Etunimi;
		this.Sukunimi=Sukunimi;
		this.Puolue=Puolue;
	}

	/**
	 * Create new Ehdokas with empty constructor
	 */
	public Ehdokas(){
		
	}

	/**
	 * Create new Ehdokas with ID and Etunimi
	 *
	 * @param Ehdokas_ID int
	 * @param Etunimi String
	 */
	public Ehdokas(int Ehdokas_ID, String Etunimi){
		super();
		this.Etunimi=Etunimi;
	}

	/**
	 * Get ehdokas ID
	 * @return
	 */
	public int getId(){
		return Ehdokas_ID;
	}

	/**
	 * Set ehdokas ID
	 *
	 * @param Ehdokas_ID int
	 */
	public void setId(int Ehdokas_ID) {
		this.Ehdokas_ID = Ehdokas_ID;
	}

	/**
	 * Try to set ehdokas ID from string
	 *
	 * @param Ehdokas_ID String
	 */
	public void setId(String Ehdokas_ID) {
		try {
			this.Ehdokas_ID = Integer.parseInt(Ehdokas_ID);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}

	/**
	 * Get ehdokas Etunimi
	 *
	 * @return Etunimi String
	 */
	public String getEtunimi() {
		return Etunimi;
	}

	/**
	 * set ehdokas etunimi
	 *
	 * @param Etunimi String
	 */
	public void setEtunimi(String Etunimi) {
		this.Etunimi=Etunimi;
	}

	/**
	 * Get ehdokas Sukunimi
	 *
	 * @return Sukunimi String
	 */
	public String getSukunimi() {
		return Sukunimi;
	}

	/**
	 * Set ehdokas Sukunimi
	 * @param Sukunimi String
	 */
	public void setSukunimi(String Sukunimi) {
		this.Sukunimi=Sukunimi;
	}

	/**
	 * Get ehdokas Puolue
	 * @return Puolue String
	 */
	public String getPuolue() {return this.Puolue;}

	/**
	 * Set ehdokas Puolue
	 * @param puolue String
	 */
	public void setPuolue(String puolue) { this.Puolue = puolue; }

	/**
	 * Get ehdokas paikkakunta
	 * @return paikkakunta String
	 */
	public String getPaikkakunta() { return Paikkakunta; }

	/**
	 * set ehdokas paikkakunta
	 * @param paikkakunta String
	 */
	public void setPaikkakunta(String paikkakunta) { Paikkakunta = paikkakunta; }

	/**
	 * get ehdokas ikä
	 * @return ika int
	 */
	public int getIka() { return Ika; }

	/**
	 * set ehdokas ikä
	 * @param ika int
	 */
	public void setIka(int ika) { Ika = ika; }

	/**
	 * get ehdokas ammatti
	 * @return ammatti String
	 */
	public String getAmmatti() { return Ammatti;}

	/**
	 * set ehdokas ammatti
	 * @param ammatti String
	 */
	public void setAmmatti(String ammatti) { Ammatti = ammatti; }

	/**
	 * get ehdokas tunnus
	 * @return tunnu String
	 */
	public String getTunnus() { return Tunnus; }

	/**
	 * set ehdokas tunnus
	 * @param tunnus String
	 */
	public void setTunnus(String tunnus) { Tunnus = tunnus;}
}
