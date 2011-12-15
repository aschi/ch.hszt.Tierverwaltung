package ch.hszt.tierverwaltung.backend;

import java.util.ArrayList;


public class Kunde implements IDataRecord {

	private int kundeID;
	private String name;
	private String vorname;
	private String adresse;
	private String plz;
	private String ort;
	private String telefon;
	private String eMail;
	private ArrayList<Tier> tiere;

	/**
	 * Leerer Konstruktor. Initialisiert alle Werte
	 */
	public Kunde() {
		name = "";
		vorname = "";
		adresse = "";
		ort = "";
		plz = "";
		telefon = "";
		eMail = "";
		tiere = new ArrayList<Tier>();
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (ausgenommen id & tierliste)
	 * @param name
	 * @param vorname
	 * @param adresse
	 * @param plz
	 * @param ort
	 * @param telefon
	 * @param eMail
	 */
	public Kunde(String name, String vorname, String adresse, String plz,
			String ort, String telefon, String eMail) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
		this.plz = plz;
		this.ort = ort;
		this.telefon = telefon;
		this.eMail = eMail;
		tiere = new ArrayList<Tier>();
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (inklusive id, ausgenommen tierliste)
	 * @param id
	 * @param name
	 * @param vorname
	 * @param adresse
	 * @param plz
	 * @param ort
	 * @param telefon
	 * @param eMail
	 */
	public Kunde(int id, String name, String vorname, String adresse,
			String plz, String ort, String telefon, String eMail) {
		this(name, vorname, adresse, plz, ort, telefon, eMail);
		this.kundeID = id;
		tiere = new ArrayList<Tier>();
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (ausgenommen id, inklusive tierliste)
	 * @param name
	 * @param vorname
	 * @param adresse
	 * @param plz
	 * @param ort
	 * @param telefon
	 * @param eMail
	 * @param tiere
	 */
	public Kunde(String name, String vorname, String adresse, String plz,
			String ort, String telefon, String eMail, ArrayList<Tier> tiere) {
		this(name, vorname, adresse, plz, ort, telefon, eMail);
		this.tiere = tiere;
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (inklusive id, inklusive tierliste)
	 * @param name
	 * @param vorname
	 * @param adresse
	 * @param plz
	 * @param ort
	 * @param telefon
	 * @param eMail
	 * @param tiere
	 */
	public Kunde(int id, String name, String vorname, String adresse,
			String plz, String ort, String telefon, String eMail,
			ArrayList<Tier> tiere) {
		this(id, name, vorname, adresse, plz, ort, telefon, eMail);
		this.tiere = tiere;
	}

	/**
	 * Prüft das aktuelle Kundenobjekt auf seine Gültigkeit. Wirft eine ValidationException falls nicht
	 */
	@Override
	public void validate() throws ValidationException {
		ValidationException ve = new ValidationException();

		if (getName() == null || getName().equals("")) {
			ve.addErrorMessage("Name nicht abgefüllt");
		}

		if (getVorname() == null || getVorname().equals("")) {
			ve.addErrorMessage("Vorname nicht abgefüllt");
		}

		if (getAdresse() == null || getAdresse().equals("")) {
			ve.addErrorMessage("Adresse nicht abgefüllt");
		}

		if (getPlz() == null || getPlz().equals("")) {
			ve.addErrorMessage("PLZ nicht abgefüllt");
		}

		if (getOrt() == null || getOrt().equals("")) {
			ve.addErrorMessage("Ort nicht abgefüllt");
		}

		if (getTelefon() == null || getTelefon().equals("")) {
			ve.addErrorMessage("Telefonnummer nicht abgefüllt");
		}

		if (getEMail() == null) {
			ve.addErrorMessage("E-Mail-Adresse ist NULL");
		}
		
		if (!ve.getErrorMsgs().isEmpty()) {
			throw ve;
		}
	}

	/**
	 * Gibt die Kunden Id zurück
	 * @return id des Kunden
	 */
	public int getKundeID() {
		return kundeID;
	}

	/**
	 * Setzt die Kunden Id
	 * @param kundeID Neue Id
	 */
	public void setKundeID(int kundeID) {
		this.kundeID = kundeID;
	}

	/**
	 * Gibt den Namen des Kunden zurück
	 * @return Name des Kunden
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen des Kunden
	 * @param name Neuer Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt den Vornamen des Kunden zurück
	 * @return Vorname des Kunden
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Setzt den Vornamen des Kunden
	 * @param vorname Neuer Vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Gibt die Adresse des Kunden zurück
	 * @return Adresse des Kunden
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Setzt die Adresse des Kunden
	 * @param addresse Neue Adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Gibt die PLZ des Kunden zurück
	 * @return PLZ des Kunden
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * Setzt die PLZ des Kunden
	 * @param plz Neue PLZ
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}

	/**
	 * Gibt den Wohnort des Kunden zurück
	 * @return Wohnort des Kunden
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * Setzt den Wohnort des Kunden
	 * @param ort Neuer Wohnort
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * Gibt die Telefon Nr des Kunden zurück
	 * @return Telefon Nr des Kunden
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Setzt die Telefon Nr des Kunden
	 * @param telefon Neue Telefon Nr
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * Gibt die Email Adresse des Kunden zurück
	 * @return Email Adresse des Kunden
	 */
	public String getEMail() {
		return eMail;
	}

	/**
	 * Setzt die Email Adresse des Kunden
	 * @param eMail Neue Email Adresse
	 */
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * Gibt eine Liste der dem Kunden zugewiesenen Tiere zurück
	 * @return Liste der dem Kunden zugewiesenen Tiere
	 */
	public ArrayList<Tier> getTiere() {
		return tiere;
	}

	/**
	 * Überschreibt die Liste der dem Kunden zugewiesenen Tiere
	 * @param tiere Neue Liste
	 */
	public void setTiere(ArrayList<Tier> tiere) {
		this.tiere = tiere;
	}

	/**
	 * Erstellt eine exakte kopie des Kundenobjekts
	 */
	@Override
	public Object clone() {
		return new Kunde(kundeID, new String(name), new String(vorname),
				new String(adresse), new String(plz), new String(ort), new String(telefon),
				new String(eMail), (ArrayList<Tier>) tiere.clone());
	}

	/**
	 * Gibt die Id des Kunden zurück
	 */
	@Override
	public int getID() {
		return kundeID;
	}

}
