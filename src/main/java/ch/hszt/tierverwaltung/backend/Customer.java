package ch.hszt.tierverwaltung.backend;

import java.util.ArrayList;

/**
 * Class of the entry customer
 * Handles all data of a customer
 * @author prisi
 *
 */
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
	 * Empty Consrtucor
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
	 * Constructor
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
	 * Constructor
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
	 * Constructor
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
	 * Constructor
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
	 * Validates all fields from customer-object
	 * 
	 * @throws ValidationException in case the validation is not successful
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
	 * Returns the id from the customer-object
	 * @return id
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the new id
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new customer-name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new first name from customer
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address new customer address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return zip code
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip new customer zipcode
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city new customer city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return phone number
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo new customer phone number
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return Email 
	 */
	public String getEMail() {
		return eMail;
	}

	/**
	 * @param eMail new customer e-mail-address
	 */
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return pet list
	 */
	public ArrayList<Pet> getPets() {
		return pets;
	}

	/**
	 * @param pets new list including all pets
	 */
	public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}

	/**
	 * Creates an exact copy of the object customer (new instance)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object clone() {
		return new Customer(customerId, new String(name), new String(firstName),
				new String(address), new String(zip), new String(city), new String(phoneNo),
				new String(eMail), (ArrayList<Pet>) pets.clone());
	}

	@Override
	public int getID() {
		return customerId;
	}

}
