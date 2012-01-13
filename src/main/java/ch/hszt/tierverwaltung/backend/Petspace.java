package ch.hszt.tierverwaltung.backend;

/**
 * Class petplace, manages all attributes of a petplace
 * 
 * @author corinne
 * 
 */

public class Petspace implements IDataRecord {
	
	private int adaptedForPetID;
	private int size;
	private String equipment;
	private int noOfPets;
	private char run;
	private int runSize;
	private int id;
	
	/**
	 * Default constructor
	 */
	public Petspace() {
		//Initialise String
		equipment = "";
	}
	
	/** 
	 * Constructor
	 * @param adaptedForPetID
	 * @param size
	 * @param equipment
	 * @param noOfPets
	 * @param run
	 * @param runSize
	 */
	public Petspace(int adaptedForPetID, int size, String equipment,
			int noOfPets, char run, int runSize) {
		this.adaptedForPetID = adaptedForPetID;
		this.size = size;
		this.equipment = equipment;
		this.noOfPets = noOfPets;
		this.run = run;
		this.runSize = runSize;
	}

	/**
	 * Constructor
	 * @param adaptedForPetID
	 * @param size
	 * @param equipment
	 * @param noOfPets
	 * @param run
	 * @param runSize
	 * @param id
	 */
	public Petspace(int adaptedForPetID, int size, String equipment,
			int noOfPets, char run, int runSize, int id) {
		this(adaptedForPetID, size, equipment, noOfPets, run, runSize);
		this.id = id;
	}

	/** 
	 * Returns adaptedForPetID
	 * @return adaptedForPetID
	 */
	public int getAdaptedForPetID() {
		return adaptedForPetID;
	}
	
	/** 
	 * Sets adaptedForPetID
	 * @param adaptedForPetID
	 */
	public void setAdaptedForPetID(int adaptedForPetID) {
		this.adaptedForPetID = adaptedForPetID;
	}
	/** Returns size
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	/** Sets size
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/** Returns equipment
	 * 
	 * @return equipment
	 */
	public String getEquipment() {
		return equipment;
	}

	/** Sets equipment
	 * 
	 * @param equipment
	 */
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	/** Returns noOfPets
	 * 
	 * @return noOfPets
	 */
	public int getNoOfPets() {
		return noOfPets;
	}

	/** Sets noOfPets
	 * 
	 * @param noOfPets
	 */
	public void setNoOfPets(int noOfPets) {
		this.noOfPets = noOfPets;
	}

	/** Returns run
	 * 
	 * @return run
	 */
	public char getRun() {
		return run;
	}

	/** Sets run
	 * 
	 * @param run
	 */
	public void setRun(char run) {
		this.run = run;
	}

	/** Returns runSize
	 * 
	 * @return runSize
	 */
	public int getRunSize() {
		return runSize;
	}

	/** Sets runSize
	 * 
	 * @param runSize
	 */
	public void setRunSize(int runSize) {
		this.runSize = runSize;
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
		
		if (getAdaptedForPetID() < 1 || getAdaptedForPetID() > 4) {
			ve.addErrorMessage("GeeignetFuerTierID muss Wert 1 (Hamster, Hase), 2 (Katze), 3 (kleine Hunde), 4 (grosse Hunde) enthalten");
		}
		
		if (getSize() <= 0) {
			ve.addErrorMessage("Groesse muss > 0 sein");
		}
		
		if (getEquipment() == null || getEquipment().equals("")) {
			ve.addErrorMessage("Ausstattung ist NULL");
		}
		
		if (getNoOfPets() <= 0) {
			ve.addErrorMessage("Anzahl Tiere nicht abgefüllt");
		}
		
		if (getRun() != '1' && getRun() != '0') {
			ve.addErrorMessage("Auslauf muss 0 (Nein) oder 1 (Ja) sein");
		}
		
		if (getRun() == '1' && getRunSize() <= 0) {
			ve.addErrorMessage("Wenn Auslauf auf 1 (Ja) ist, dann muss die Auslaufgroesse abgefüllt werden");
		}
		
		if (getRun() == '0' && getRunSize() >=0) {
			ve.addErrorMessage("Wenn Auslauf auf 0 (Nein) ist, dann muss die Auslaufgroesse leer sein");
		}
		
		if (!ve.getErrorMsgs().isEmpty()) {
			throw ve;
		}
		
	}
	
	@Override
	public Object clone(){
		return new Petspace(this.getAdaptedForPetID(), this.getSize(), new String(this.getEquipment()), 
				this.getNoOfPets(), this.getRun(), this.getRunSize());
	}
	
	@Override
	public String toString(){
		return "Grösse: " + getSize() + "m² - max Anzahl Tiere: " + getNoOfPets() + ((getRun() == '1') ? (" - Auslauf "+ getRunSize() + "m²") : " - Kein Auslauf");
	}

}
