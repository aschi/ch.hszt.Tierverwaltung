package ch.hszt.tierverwaltung.kunden.backend;

import java.util.ArrayList;

import ch.hszt.tierverwaltung.backend.IDataRecord;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.tier.backend.Tier;

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

	public Kunde(int id, String name, String vorname, String adresse,
			String plz, String ort, String telefon, String eMail) {
		this(name, vorname, adresse, plz, ort, telefon, eMail);
		this.kundeID = id;
		tiere = new ArrayList<Tier>();
	}

	public Kunde(String name, String vorname, String adresse, String plz,
			String ort, String telefon, String eMail, ArrayList<Tier> tiere) {
		this(name, vorname, adresse, plz, ort, telefon, eMail);
		this.tiere = tiere;
	}

	public Kunde(int id, String name, String vorname, String adresse,
			String plz, String ort, String telefon, String eMail,
			ArrayList<Tier> tiere) {
		this(id, name, vorname, adresse, plz, ort, telefon, eMail);
		this.tiere = tiere;
	}

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

	public int getKundeID() {
		return kundeID;
	}

	public void setKundeID(int kundeID) {
		this.kundeID = kundeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String addresse) {
		this.adresse = addresse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public ArrayList<Tier> getTiere() {
		return tiere;
	}

	public void setTiere(ArrayList<Tier> tiere) {
		this.tiere = tiere;
	}

	@Override
	public Object clone() {
		return new Kunde(kundeID, new String(name), new String(vorname),
				new String(adresse), new String(plz), new String(ort), new String(telefon),
				new String(eMail), (ArrayList<Tier>) tiere.clone());
	}

	@Override
	public int getID() {
		return kundeID;
	}

}
