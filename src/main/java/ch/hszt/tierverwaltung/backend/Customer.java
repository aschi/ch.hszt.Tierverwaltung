package ch.hszt.tierverwaltung.backend;

import java.util.ArrayList;


public class Customer implements IDataRecord {

	private int customerId;
	private String name;
	private String firstName;
	private String address;
	private String zip;
	private String city;
	private String phoneNo;
	private String eMail;
	private ArrayList<Pet> pets;

	/**
	 * Leerer Konstruktor. Initialisiert alle Werte
	 */
	public Customer() {
		name = "";
		firstName = "";
		address = "";
		city = "";
		zip = "";
		phoneNo = "";
		eMail = "";
		pets = new ArrayList<Pet>();
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (ausgenommen id & tierliste)
	 * @param name
	 * @param firstName
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param eMail
	 */
	public Customer(String name, String firstName, String address, String zip,
			String city, String phoneNo, String eMail) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.address = address;
		this.zip = zip;
		this.city = city;
		this.phoneNo = phoneNo;
		this.eMail = eMail;
		pets = new ArrayList<Pet>();
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (inklusive id, ausgenommen tierliste)
	 * @param id
	 * @param name
	 * @param firstName
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param eMail
	 */
	public Customer(int id, String name, String firstName, String address,
			String zip, String city, String phoneNo, String eMail) {
		this(name, firstName, address, zip, city, phoneNo, eMail);
		this.customerId = id;
		pets = new ArrayList<Pet>();
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (ausgenommen id, inklusive tierliste)
	 * @param name
	 * @param firstName
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param eMail
	 * @param pets
	 */
	public Customer(String name, String firstName, String address, String zip,
			String city, String phoneNo, String eMail, ArrayList<Pet> pets) {
		this(name, firstName, address, zip, city, phoneNo, eMail);
		this.pets = pets;
	}

	/**
	 * Konstruktor zum direkten setzen aller Felder (inklusive id, inklusive tierliste)
	 * @param name
	 * @param fristName
	 * @param address
	 * @param zip
	 * @param city
	 * @param phoneNo
	 * @param eMail
	 * @param pets
	 */
	public Customer(int id, String name, String fristName, String address,
			String zip, String city, String phoneNo, String eMail,
			ArrayList<Pet> pets) {
		this(id, name, fristName, address, zip, city, phoneNo, eMail);
		this.pets = pets;
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

		if (getFirstName() == null || getFirstName().equals("")) {
			ve.addErrorMessage("Vorname nicht abgefüllt");
		}

		if (getAddress() == null || getAddress().equals("")) {
			ve.addErrorMessage("Adresse nicht abgefüllt");
		}

		if (getZip() == null || getZip().equals("")) {
			ve.addErrorMessage("PLZ nicht abgefüllt");
		}

		if (getCity() == null || getCity().equals("")) {
			ve.addErrorMessage("Ort nicht abgefüllt");
		}

		if (getPhoneNo() == null || getPhoneNo().equals("")) {
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
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * Setzt die Kunden Id
	 * @param customerId Neue Id
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setzt den Vornamen des Kunden
	 * @param firstName Neuer Vorname
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gibt die Adresse des Kunden zurück
	 * @return Adresse des Kunden
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setzt die Adresse des Kunden
	 * @param addresse Neue Adresse
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gibt die PLZ des Kunden zurück
	 * @return PLZ des Kunden
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Setzt die PLZ des Kunden
	 * @param zip Neue PLZ
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Gibt den Wohnort des Kunden zurück
	 * @return Wohnort des Kunden
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setzt den Wohnort des Kunden
	 * @param city Neuer Wohnort
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gibt die Telefon Nr des Kunden zurück
	 * @return Telefon Nr des Kunden
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Setzt die Telefon Nr des Kunden
	 * @param phoneNo Neue Telefon Nr
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	public ArrayList<Pet> getPets() {
		return pets;
	}

	/**
	 * Überschreibt die Liste der dem Kunden zugewiesenen Tiere
	 * @param pets Neue Liste
	 */
	public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}

	/**
	 * Erstellt eine exakte kopie des Kundenobjekts
	 */
	@Override
	public Object clone() {
		return new Customer(customerId, new String(name), new String(firstName),
				new String(address), new String(zip), new String(city), new String(phoneNo),
				new String(eMail), (ArrayList<Pet>) pets.clone());
	}

	/**
	 * Gibt die Id des Kunden zurück
	 */
	@Override
	public int getID() {
		return customerId;
	}

}
