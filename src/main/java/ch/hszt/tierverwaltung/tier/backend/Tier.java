package ch.hszt.tierverwaltung.tier.backend;

import java.sql.SQLException;

import ch.hszt.tierverwaltung.backend.IDataRecord;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.tier.TierDatabaseAccess;

/**
 * Klasse Tiereintrag, verwaltet einen einzelnen Eintrag aus der Datenbank Tier
 * 
 * @author prisi
 * 
 */
public class Tier implements IDataRecord {

	private Integer tierID;
	private Integer fkKunde;
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
	public void delete() throws SQLException, ValidationException {
		validate();
		TierDatabaseAccess.getInstance().delete(this);
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

	public Integer getTierID() {
		return tierID;
	}

	public Integer getFkKunde() {
		return fkKunde;
	}

	public void setTierID(Integer id) {
		tierID = id;
	}

}
