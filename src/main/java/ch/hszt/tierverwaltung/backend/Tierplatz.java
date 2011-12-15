package ch.hszt.tierverwaltung.backend;

public class Tierplatz implements IDataRecord {
	
	private int geeignetFuerTierID;
	private int groesse;
	private String ausstattung;
	private int anzahlTiere;
	private char auslauf;
	private int auslaufGroesse;
	private int id;
	

	public Tierplatz() {
		ausstattung = "";
	}
	

	public Tierplatz(int geeignetFuerTierID, int groesse, String ausstattung,
			int anzahlTiere, char auslauf, int auslaufGroesse) {
		this.geeignetFuerTierID = geeignetFuerTierID;
		this.groesse = groesse;
		this.ausstattung = ausstattung;
		this.anzahlTiere = anzahlTiere;
		this.auslauf = auslauf;
		this.auslaufGroesse = auslaufGroesse;
	}

	public Tierplatz(int geeignetFuerTierID, int groesse, String ausstattung,
			int anzahlTiere, char auslauf, int auslaufGroesse, int id) {
		this(geeignetFuerTierID, groesse, ausstattung, anzahlTiere, auslauf, auslaufGroesse);
		this.id = id;
	}

	public int getGeeignetFuerTierID() {
		return geeignetFuerTierID;
	}

	public void setGeeignetFuerTierID(int geeignetFuerTierID) {
		this.geeignetFuerTierID = geeignetFuerTierID;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public String getAusstattung() {
		return ausstattung;
	}

	public void setAusstattung(String ausstattung) {
		this.ausstattung = ausstattung;
	}

	public int getAnzahlTiere() {
		return anzahlTiere;
	}

	public void setAnzahlTiere(int anzahlTiere) {
		this.anzahlTiere = anzahlTiere;
	}

	public char getAuslauf() {
		return auslauf;
	}

	public void setAuslauf(char auslauf) {
		this.auslauf = auslauf;
	}

	public int getAuslaufGroesse() {
		return auslaufGroesse;
	}

	public void setAuslaufGroesse(int auslaufGroesse) {
		this.auslaufGroesse = auslaufGroesse;
	}

	@Override
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public void validate() throws ValidationException {
		ValidationException ve = new ValidationException();
		
//		if (getGeeignetFuerTierID() != 1 && getGeeignetFuerTierID() != 2 && getGeeignetFuerTierID() != 3 && getGeeignetFuerTierID() && != 4 getGeeignetFuerTierID() != 5) {
			
//		}
		
//	}
	
	@Override
	public Object clone(){
		return new Tierplatz(this.getGeeignetFuerTierID(), this.getGroesse(), new String(this.getAusstattung()), 
				this.getAnzahlTiere(), this.getAuslauf(), this.getAuslaufGroesse());
	}
	

}
