package ch.hszt.tierverwaltung.tier.backend;

import java.sql.SQLException;

import ch.hszt.tierverwaltung.backend.IDataRecord;
import ch.hszt.tierverwaltung.backend.IPublicCloneable;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.tier.TierDatabaseAccess;

/**
 * Klasse Tiereintrag, verwaltet einen einzelnen Eintrag aus der Datenbank Tier
 * 
 * @author prisi
 * 
 */
public class Tier implements IDataRecord {

	private int tierID;
	private int fkKunde;
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
		//Initialise strings
		art = "";
		rasse = "";
		name = "";
		krankheitsbild = "";
		essgewohnheit = "";
		umgangTier = "";
		umgangMensch = "";
		anmerkungen = "";
	}

	/**
	 * Konstruktor der Klasse Tiereintrag
	 * 
	 * @param art
	 *            Tabbbellenfeld art
	 * @param rasse
	 *            Tabellenfeld rasse
	 * @param name
	 *            Tabellenfeld name
	 * @param tieralter
	 *            Tabellenfeld tieralter
	 * @param groesseID
	 *            Tabellenfeld groesseID
	 * @param krankheitsbild
	 *            Tabellenfeld krankheitsbild
	 * @param essgewohnheit
	 *            Tabellenfeld essgewohnheit
	 * @param auslauf
	 *            Tabellenfeld auslauf
	 * @param umgangTier
	 *            Tabellenfeld umgangTier
	 * @param umgangMensch
	 *            Tabellenfeld umgangMensch
	 * @param anmerkungen
	 *            Tabellenfeld anmerkungen
	 * @param zusatzkosten
	 *            Tabellenfeld zusatztkosten
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

	/**
	 * Konstruktor der Klasse Tiereintrag
	 * 
	 * @param tierID
	 *            ID des Tabelleneintrages Tier
	 * @param art
	 *            Tabbbellenfeld art
	 * @param rasse
	 *            Tabellenfeld rasse
	 * @param name
	 *            Tabellenfeld name
	 * @param tieralter
	 *            Tabellenfeld tieralter
	 * @param groesseID
	 *            Tabellenfeld groesseID
	 * @param krankheitsbild
	 *            Tabellenfeld krankheitsbild
	 * @param essgewohnheit
	 *            Tabellenfeld essgewohnheit
	 * @param auslauf
	 *            Tabellenfeld auslauf
	 * @param umgangTier
	 *            Tabellenfeld umgangTier
	 * @param umgangMensch
	 *            Tabellenfeld umgangMensch
	 * @param anmerkungen
	 *            Tabellenfeld anmerkungen
	 * @param zusatzkosten
	 *            Tabellenfeld zusatztkosten
	 */
	public Tier(Integer tierID, Integer fkKunde, String art, String rasse,
			String name, int tieralter, int groesseID, String krankheitsbild,
			String essgewohnheit, char auslauf, String umgangTier,
			String umgangMensch, String anmerkungen, double zusatzkosten) {
		this(art, rasse, name, tieralter, groesseID, krankheitsbild,
				essgewohnheit, auslauf, umgangTier, umgangMensch, anmerkungen,
				zusatzkosten);
		this.tierID = tierID;
		this.fkKunde = fkKunde;
	}

	@Override
	public void save() throws SQLException, ValidationException {
		validate();
		if (getTierID() <= 0) {
			setTierID(TierDatabaseAccess.getInstance().insert(this));
		} else {
			TierDatabaseAccess.getInstance().update(this);
		}
	}

	@Override
	public void delete() throws SQLException {
		if (this.getTierID() > 0) {
			TierDatabaseAccess.getInstance().delete(this);
			this.setTierID(-1);
		}
	}

	@Override
	public void validate() throws ValidationException {
		ValidationException ve = new ValidationException();

		if (getArt() == null || getArt().equals("")) {
			ve.addErrorMessage("Art nicht abgefüllt");
		}

		if (getRasse() == null || getRasse().equals("")) {
			ve.addErrorMessage("Rasse nicht abgefüllt");
		}

		if (getName() == null || getName().equals("")) {
			ve.addErrorMessage("Name nicht abgefüllt");
		}

		if (getAuslauf() != '1' && getAuslauf() != '0') {
			ve.addErrorMessage("Auslauf muss 0 (Nein) oder 1 (Ja) sein");
		}

		if (getAnmerkungen() == null) {
			ve.addErrorMessage("Anmerkungen ist NULL");
		}

		if (getEssgewohnheit() == null) {
			ve.addErrorMessage("Essgewohnheit ist NULL");
		}

		if (getGroesseID() != 1 && getGroesseID() != 2 && getGroesseID() != 3) {
			ve.addErrorMessage("Groesse ID muss Wert 1 (< 30 cm), 2 (> 30 cm) oder 3 (> 1m) enthalten");
		}

		if (getTieralter() < 0) {
			ve.addErrorMessage("Alter muss > 0 sein");
		}

		if (getUmgangMensch() == null) {
			ve.addErrorMessage("Umgang mit Mensch ist NULL");
		}
		
		if (getUmgangTier() == null) {
			ve.addErrorMessage("Umgang mit Tier ist NULL");
		}
		
		if (getZusatzkosten() < 0) {
			ve.addErrorMessage("Zusatzkosten müssen > 0 sein");
		}
		
		if (!ve.getErrorMsgs().isEmpty()) {
			throw ve;
		}
	}

	/**
	 * Returns art
	 * @return art
	 */
	public String getArt() {
		return art;
	}

	/**
	 * Sets art
	 * @param art to be set
	 */
	public void setArt(String art) {
		this.art = art;
	}

	/**
	 * Returns rasse
	 * @return rasse
	 */
	public String getRasse() {
		return rasse;
	}

	/**
	 * Sets rasse
	 * @param rasse to bet set
	 */
	public void setRasse(String rasse) {
		this.rasse = rasse;
	}

	/**
	 * Returns name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name
	 * @param name to  be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns tieralter
	 * @return tieralter
	 */
	public int getTieralter() {
		return tieralter;
	}

	/**
	 * sets tieralter
	 * @param tieralter to be set
	 */
	public void setTieralter(int tieralter) {
		this.tieralter = tieralter;
	}

	/**
	 * returns groesseID
	 * @return groesseID
	 */
	public int getGroesseID() {
		return groesseID;
	}

	/**
	 * Sets groesseID
	 * @param groesseID to be set
	 */
	public void setGroesseID(int groesseID) {
		this.groesseID = groesseID;
	}

	/**
	 * returns Krankheitsbild
	 * @return krankheitsbild
	 */
	public String getKrankheitsbild() {
		return krankheitsbild;
	}

	/**
	 * 
	 * @param krankheitsbild
	 */
	public void setKrankheitsbild(String krankheitsbild) {
		this.krankheitsbild = krankheitsbild;
	}

	/**
	 * Returns essgewohnheit
	 * @return essgewohnheit
	 */
	public String getEssgewohnheit() {
		return essgewohnheit;
	}

	/**
	 * Sets essgewohnheit
	 * @param essgewohnheit to be set
	 */
	public void setEssgewohnheit(String essgewohnheit) {
		this.essgewohnheit = essgewohnheit;
	}

	/**
	 * Returns auslauf
	 * @return auslauf
	 */
	public char getAuslauf() {
		return auslauf;
	}

	/**
	 * Sets auslauf
	 * @param auslauf to be set
	 */
	public void setAuslauf(char auslauf) {
		this.auslauf = auslauf;
	}

	/**
	 * Returns umgangTier
	 * @return umgangTier
	 */
	public String getUmgangTier() {
		return umgangTier;
	}

	/**
	 * Sets umgangTier
	 * @param umgangTier to be set
	 */
	public void setUmgangTier(String umgangTier) {
		this.umgangTier = umgangTier;
	}

	/**
	 * returns umgangMensch
	 * @return umgangMensch
	 */
	public String getUmgangMensch() {
		return umgangMensch;
	}

	/**
	 * Sets umgangMensch
	 * @param umgangMensch to be set
	 */
	public void setUmgangMensch(String umgangMensch) {
		this.umgangMensch = umgangMensch;
	}

	/**
	 * Returns anmerkungen
	 * @return anmerkungen
	 */
	public String getAnmerkungen() {
		return anmerkungen;
	}

	/**
	 * Sets anmerkungen
	 * @param anmerkungen to be set
	 */
	public void setAnmerkungen(String anmerkungen) {
		this.anmerkungen = anmerkungen;
	}

	/**
	 * returns zusatzkosten
	 * @return zusatzkosten
	 */
	public double getZusatzkosten() {
		return zusatzkosten;
	}

	/**
	 * Sets zusatzkosten
	 * @param zusatzkosten to be set
	 */
	public void setZusatzkosten(double zusatzkosten) {
		this.zusatzkosten = zusatzkosten;
	}

	/**
	 * Returns tierID
	 * @return tierID
	 */
	public int getTierID() {
		return tierID;
	}
	
	/**
	 * Returns fkKunde
	 * @return fkKunde
	 */
	public int getFkKunde() {
		return fkKunde;
	}

	private void setTierID(Integer id) {
		tierID = id;
	}
	
	@Override
	public Object clone(){
		return new Tier(new String(this.getArt()), new String(this.getRasse()), new String(this.getName()), this.getTieralter(),
				this.getGroesseID(), new String(this.getKrankheitsbild()), new String(this.getEssgewohnheit()),
				this.getAuslauf(), new String(this.getUmgangTier()), new String(this.getUmgangMensch()),
				new String(this.getAnmerkungen()), this.getZusatzkosten());
	}

}
