package ch.hszt.tierverwaltung.tier.backend;

/**
 * Klasse Tiereintrag, verwaltet einen einzelnen Eintrag aus der Datenbank Tier
 * @author prisi
 *
 */
public class Tier {

	private String art;
	private String rasse;
	private String name;
	private int tieralter;
	private int groesseID;
	private String krankheitsbild;
	private String essgewohnheit;
	private char auslauf;
	private String umgangTier;
	private String umgangMensch;
	private String anmerkungen;
	private double zusatzkosten;

	/**
	 * Konstruktor der Klasse Tiereintrag
	 */
	public Tier() {

	}

	/**
	 * Konstruktor der Klasse Tiereintrag
	 * @param art Tabbbellenfeld art
	 * @param rasse Tabellenfeld rasse
	 * @param name Tabellenfeld name
	 * @param tieralter Tabellenfeld tieralter
	 * @param groesseID Tabellenfeld groesseID
	 * @param krankheitsbild Tabellenfeld krankheitsbild
	 * @param essgewohnheit Tabellenfeld essgewohnheit
	 * @param auslauf Tabellenfeld auslauf
	 * @param umgangTier Tabellenfeld umgangTier
	 * @param umgangMensch Tabellenfeld umgangMensch
	 * @param anmerkungen Tabellenfeld anmerkungen
	 * @param zusatzkosten Tabellenfeld zusatztkosten
	 */
	public Tier(String art, String rasse, String name, int tieralter,
			int groesseID, String krankheitsbild, String essgewohnheit,
			char auslauf, String umgangTier, String umgangMensch,
			String anmerkungen, double zusatzkosten) {
		this.art = art;
		this.rasse = rasse;
		this.name = name;
		this.tieralter = tieralter;
		this.groesseID = groesseID;
		this.krankheitsbild = krankheitsbild;
		this.essgewohnheit = essgewohnheit;
		this.auslauf = auslauf;
		this.umgangTier = umgangTier;
		this.umgangMensch = umgangMensch;
		this.anmerkungen = anmerkungen;
		this.zusatzkosten = zusatzkosten;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getRasse() {
		return rasse;
	}

	public void setRasse(String rasse) {
		this.rasse = rasse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTieralter() {
		return tieralter;
	}

	public void setTieralter(int tieralter) {
		this.tieralter = tieralter;
	}

	public int getGroesseID() {
		return groesseID;
	}

	public void setGroesseID(int groesseID) {
		this.groesseID = groesseID;
	}

	public String getKrankheitsbild() {
		return krankheitsbild;
	}

	public void setKrankheitsbild(String krankheitsbild) {
		this.krankheitsbild = krankheitsbild;
	}

	public String getEssgewohnheit() {
		return essgewohnheit;
	}

	public void setEssgewohnheit(String essgewohnheit) {
		this.essgewohnheit = essgewohnheit;
	}

	public char getAuslauf() {
		return auslauf;
	}

	public void setAuslauf(char auslauf) {
		this.auslauf = auslauf;
	}

	public String getUmgangTier() {
		return umgangTier;
	}

	public void setUmgangTier(String umgangTier) {
		this.umgangTier = umgangTier;
	}

	public String getUmgangMensch() {
		return umgangMensch;
	}

	public void setUmgangMensch(String umgangMensch) {
		this.umgangMensch = umgangMensch;
	}

	public String getAnmerkungen() {
		return anmerkungen;
	}

	public void setAnmerkungen(String anmerkungen) {
		this.anmerkungen = anmerkungen;
	}

	public double getZusatzkosten() {
		return zusatzkosten;
	}

	public void setZusatzkosten(double zusatzkosten) {
		this.zusatzkosten = zusatzkosten;
	}
	
	

}
