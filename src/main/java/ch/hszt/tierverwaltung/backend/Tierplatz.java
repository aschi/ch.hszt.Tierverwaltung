package ch.hszt.tierverwaltung.backend;

/**
 * Klasse Tierplatz, verwaltet einen einzelnen Eintrag aus der Datenbank Tierplatz
 * 
 * @author corinne
 * 
 */

public class Tierplatz implements IDataRecord {
	
	private int geeignetFuerTierID;
	private int groesse;
	private String ausstattung;
	private int anzahlTiere;
	private char auslauf;
	private int auslaufGroesse;
	private int id;
	
	/**
	 * Konstruktor der Klasse Tierplatz
	 */
	public Tierplatz() {
		//Initialise String
		ausstattung = "";
	}
	
	/** 
	 * Konstruktur der Klasse Tierplatz
	 * 
	 * @param geeignetFuerTierID
	 * 			Tabellenfeld geeignetFuerTierID
	 * @param groesse
	 * 			Tabellenfeld groesse
	 * @param ausstattung
	 * 			Tabellenfeld ausstattung
	 * @param anzahlTiere
	 * 			Tabellenfeld anzahlTiere
	 * @param auslauf
	 * 			Tabellenfeld auslauf
	 * @param auslaufGroesse
	 * 			Tabellenfeld auslaufGroesse
	 */

	public Tierplatz(int geeignetFuerTierID, int groesse, String ausstattung,
			int anzahlTiere, char auslauf, int auslaufGroesse) {
		this.geeignetFuerTierID = geeignetFuerTierID;
		this.groesse = groesse;
		this.ausstattung = ausstattung;
		this.anzahlTiere = anzahlTiere;
		this.auslauf = auslauf;
		this.auslaufGroesse = auslaufGroesse;
	}
	
	/** 
	 * Konstruktur der Klasse Tierplatz
	 * 
	 * @param geeignetFuerTierID
	 * 			Tabellenfeld geeignetFuerTierID
	 * @param groesse
	 * 			Tabellenfeld groesse
	 * @param ausstattung
	 * 			Tabellenfeld ausstattung
	 * @param anzahlTiere
	 * 			Tabellenfeld anzahlTiere
	 * @param auslauf
	 * 			Tabellenfeld auslauf
	 * @param auslaufGroesse
	 * 			Tabellenfeld auslaufGroesse
	 */

	public Tierplatz(int geeignetFuerTierID, int groesse, String ausstattung,
			int anzahlTiere, char auslauf, int auslaufGroesse, int id) {
		this(geeignetFuerTierID, groesse, ausstattung, anzahlTiere, auslauf, auslaufGroesse);
		this.id = id;
	}

	/** 
	 * Returns geeignetFuerTierID
	 * @return geeignetFuerTierID
	 */
	public int getGeeignetFuerTierID() {
		return geeignetFuerTierID;
	}
	
	/** 
	 * Sets geeignetFuerTierID
	 * @param geeignetFuerTierID
	 */
	public void setGeeignetFuerTierID(int geeignetFuerTierID) {
		this.geeignetFuerTierID = geeignetFuerTierID;
	}
	/** Returns groesse
	 * 
	 * @return groesse
	 */
	public int getGroesse() {
		return groesse;
	}
	
	/** Sets groesse
	 * 
	 * @param groesse
	 */
	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	/** Returns ausstattung
	 * 
	 * @return ausstattung
	 */
	public String getAusstattung() {
		return ausstattung;
	}

	/** Sets ausstattung
	 * 
	 * @param ausstattung
	 */
	public void setAusstattung(String ausstattung) {
		this.ausstattung = ausstattung;
	}

	/** Returns anzahlTiere
	 * 
	 * @return anzahlTiere
	 */
	public int getAnzahlTiere() {
		return anzahlTiere;
	}

	/** Sets anzahlTiere
	 * 
	 * @param anzahlTiere
	 */
	public void setAnzahlTiere(int anzahlTiere) {
		this.anzahlTiere = anzahlTiere;
	}

	/** Returns auslauf
	 * 
	 * @return auslauf
	 */
	public char getAuslauf() {
		return auslauf;
	}

	/** Sets auslauf
	 * 
	 * @param auslauf
	 */
	public void setAuslauf(char auslauf) {
		this.auslauf = auslauf;
	}

	/** Returns auslaufGroesse
	 * 
	 * @return auslaufGroesse
	 */
	public int getAuslaufGroesse() {
		return auslaufGroesse;
	}

	/** Sets auslaufGroesse
	 * 
	 * @param auslaufGroesse
	 */
	public void setAuslaufGroesse(int auslaufGroesse) {
		this.auslaufGroesse = auslaufGroesse;
	}

	/** Returns id
	 * 
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	/** Sets id
	 * 
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public void validate() throws ValidationException {
		ValidationException ve = new ValidationException();
		
		if (getGeeignetFuerTierID() < 1 || getGeeignetFuerTierID() > 4) {
			ve.addErrorMessage("GeeignetFuerTierID muss Wert 1 (Hamster, Hase), 2 (Katze), 3 (kleine Hunde), 4 (grosse Hunde) enthalten");
		}
		
		if (getGroesse() <= 0) {
			ve.addErrorMessage("Groesse muss > 0 sein");
		}
		
		if (getAusstattung() == null || getAusstattung().equals("")) {
			ve.addErrorMessage("Ausstattung ist NULL");
		}
		
		if (getAnzahlTiere() <= 0) {
			ve.addErrorMessage("Anzahl Tiere nicht abgefüllt");
		}
		
		if (getAuslauf() != '1' && getAuslauf() != '0') {
			ve.addErrorMessage("Auslauf muss 0 (Nein) oder 1 (Ja) sein");
		}
		
		if (getAuslauf() == '1' && getAuslaufGroesse() <= 0) {
			ve.addErrorMessage("Wenn Auslauf auf 1 (Ja) ist, dann muss die Auslaufgroesse abgefüllt werden");
		}
		
		if (getAuslauf() == '0' && getAuslaufGroesse() >=0) {
			ve.addErrorMessage("Wenn Auslauf auf 0 (Nein) ist, dann muss die Auslaufgroesse leer sein");
		}
		
		if (!ve.getErrorMsgs().isEmpty()) {
			throw ve;
		}
		
	}
	
	@Override
	public Object clone(){
		return new Tierplatz(this.getGeeignetFuerTierID(), this.getGroesse(), new String(this.getAusstattung()), 
				this.getAnzahlTiere(), this.getAuslauf(), this.getAuslaufGroesse());
	}
	

}
