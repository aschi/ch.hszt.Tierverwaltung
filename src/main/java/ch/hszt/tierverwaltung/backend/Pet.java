package ch.hszt.tierverwaltung.backend;


/**
 * Klasse Tiereintrag, verwaltet einen einzelnen Eintrag aus der Datenbank Tier
 * 
 * @author prisi
 * 
 */
public class Pet implements IDataRecord {

	private int petID;
	private int fkCustomer;
	private String species;
	private String race;
	private String name;
	private int age;
	private int sizeId;
	private String diseasePattern;
	private String eatingHabits;
	private char run;
	private String contactOtherPets;
	private String contactPeople;
	private String remarks;
	private double additionalCosts;

	/**
	 * Konstruktor der Klasse Tiereintrag
	 */
	public Pet() {
		//Initialise strings
		species = "";
		race = "";
		name = "";
		diseasePattern = "";
		eatingHabits = "";
		contactOtherPets = "";
		contactPeople = "";
		remarks = "";
	}

	/**
	 * Konstruktor der Klasse Tiereintrag
	 * 
	 * @param species
	 *            Tabbbellenfeld art
	 * @param race
	 *            Tabellenfeld rasse
	 * @param name
	 *            Tabellenfeld name
	 * @param age
	 *            Tabellenfeld tieralter
	 * @param sizeId
	 *            Tabellenfeld groesseID
	 * @param dieseasePattern
	 *            Tabellenfeld krankheitsbild
	 * @param eatingHabits
	 *            Tabellenfeld essgewohnheit
	 * @param run
	 *            Tabellenfeld auslauf
	 * @param contactOtherPets
	 *            Tabellenfeld umgangTier
	 * @param contactPeople
	 *            Tabellenfeld umgangMensch
	 * @param remarks
	 *            Tabellenfeld anmerkungen
	 * @param additionalCosts
	 *            Tabellenfeld zusatztkosten
	 */
	public Pet(String species, String race, String name, int age,
			int sizeId, String dieseasePattern, String eatingHabits,
			char run, String contactOtherPets, String contactPeople,
			String remarks, double additionalCosts) {
		this.species = species;
		this.race = race;
		this.name = name;
		this.age = age;
		this.sizeId = sizeId;
		this.diseasePattern = dieseasePattern;
		this.eatingHabits = eatingHabits;
		this.run = run;
		this.contactOtherPets = contactOtherPets;
		this.contactPeople = contactPeople;
		this.remarks = remarks;
		this.additionalCosts = additionalCosts;
	}

	/**
	 * Konstruktor der Klasse Tiereintrag
	 * 
	 * @param petId
	 *            ID des Tabelleneintrages Tier
	 * @param species
	 *            Tabbbellenfeld art
	 * @param race
	 *            Tabellenfeld rasse
	 * @param name
	 *            Tabellenfeld name
	 * @param age
	 *            Tabellenfeld tieralter
	 * @param sizeId
	 *            Tabellenfeld groesseID
	 * @param diseasePattern
	 *            Tabellenfeld krankheitsbild
	 * @param eatingHabits
	 *            Tabellenfeld essgewohnheit
	 * @param run
	 *            Tabellenfeld auslauf
	 * @param contactOtherPets
	 *            Tabellenfeld umgangTier
	 * @param contactPeople
	 *            Tabellenfeld umgangMensch
	 * @param remarks
	 *            Tabellenfeld anmerkungen
	 * @param additionalCosts
	 *            Tabellenfeld zusatztkosten
	 */
	public Pet(Integer petId, Integer fkCustomer, String species, String race,
			String name, int age, int sizeId, String diseasePattern,
			String eatingHabits, char run, String contactOtherPets,
			String contactPeople, String remarks, double additionalCosts) {
		this(species, race, name, age, sizeId, diseasePattern,
				eatingHabits, run, contactOtherPets, contactPeople, remarks,
				additionalCosts);
		this.petID = petId;
		this.fkCustomer = fkCustomer;
	}

	@Override
	public void validate() throws ValidationException {
		ValidationException ve = new ValidationException();

		if (getSpecies() == null || getSpecies().equals("")) {
			ve.addErrorMessage("Art nicht abgefüllt");
		}

		if (getRace() == null || getRace().equals("")) {
			ve.addErrorMessage("Rasse nicht abgefüllt");
		}

		if (getName() == null || getName().equals("")) {
			ve.addErrorMessage("Name nicht abgefüllt");
		}

		if (getRun() != '1' && getRun() != '0') {
			ve.addErrorMessage("Auslauf muss 0 (Nein) oder 1 (Ja) sein");
		}

		if (getRemarks() == null) {
			ve.addErrorMessage("Anmerkungen ist NULL");
		}

		if (getEatingHabits() == null) {
			ve.addErrorMessage("Essgewohnheit ist NULL");
		}

		if (getSizeId() != 1 && getSizeId() != 2 && getSizeId() != 3) {
			ve.addErrorMessage("Groesse ID muss Wert 1 (< 30 cm), 2 (> 30 cm) oder 3 (> 1m) enthalten");
		}

		if (getAge() < 0) {
			ve.addErrorMessage("Alter muss > 0 sein");
		}

		if (getContactPeople() == null) {
			ve.addErrorMessage("Umgang mit Mensch ist NULL");
		}
		
		if (getContactOtherPets() == null) {
			ve.addErrorMessage("Umgang mit Tier ist NULL");
		}
		
		if (getAdditionalCosts() < 0) {
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
	public String getSpecies() {
		return species;
	}

	/**
	 * Sets art
	 * @param art to be set
	 */
	public void setSpecies(String art) {
		this.species = art;
	}

	/**
	 * Returns rasse
	 * @return rasse
	 */
	public String getRace() {
		return race;
	}

	/**
	 * Sets rasse
	 * @param rasse to bet set
	 */
	public void setRace(String rasse) {
		this.race = rasse;
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
	public int getAge() {
		return age;
	}

	/**
	 * sets tieralter
	 * @param tieralter to be set
	 */
	public void setAge(int tieralter) {
		this.age = tieralter;
	}

	/**
	 * returns groesseID
	 * @return groesseID
	 */
	public int getSizeId() {
		return sizeId;
	}

	/**
	 * Sets groesseID
	 * @param groesseID to be set
	 */
	public void setSizeId(int groesseID) {
		this.sizeId = groesseID;
	}

	/**
	 * returns Krankheitsbild
	 * @return krankheitsbild
	 */
	public String getDiseasePattern() {
		return diseasePattern;
	}

	/**
	 * 
	 * @param krankheitsbild
	 */
	public void setDiseasePattern(String krankheitsbild) {
		this.diseasePattern = krankheitsbild;
	}

	/**
	 * Returns essgewohnheit
	 * @return essgewohnheit
	 */
	public String getEatingHabits() {
		return eatingHabits;
	}

	/**
	 * Sets essgewohnheit
	 * @param essgewohnheit to be set
	 */
	public void setEatingHabits(String essgewohnheit) {
		this.eatingHabits = essgewohnheit;
	}

	/**
	 * Returns auslauf
	 * @return auslauf
	 */
	public char getRun() {
		return run;
	}

	/**
	 * Sets auslauf
	 * @param auslauf to be set
	 */
	public void setRun(char auslauf) {
		this.run = auslauf;
	}

	/**
	 * Returns umgangTier
	 * @return umgangTier
	 */
	public String getContactOtherPets() {
		return contactOtherPets;
	}

	/**
	 * Sets umgangTier
	 * @param umgangTier to be set
	 */
	public void setContactOtherPets(String umgangTier) {
		this.contactOtherPets = umgangTier;
	}

	/**
	 * returns umgangMensch
	 * @return umgangMensch
	 */
	public String getContactPeople() {
		return contactPeople;
	}

	/**
	 * Sets umgangMensch
	 * @param umgangMensch to be set
	 */
	public void setContactPeople(String umgangMensch) {
		this.contactPeople = umgangMensch;
	}

	/**
	 * Returns anmerkungen
	 * @return anmerkungen
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Sets anmerkungen
	 * @param anmerkungen to be set
	 */
	public void setRemarks(String anmerkungen) {
		this.remarks = anmerkungen;
	}

	/**
	 * returns additionalCosts
	 * @return additionalCosts
	 */
	public double getAdditionalCosts() {
		return additionalCosts;
	}

	/**
	 * Sets additionalCosts
	 * @param Additional Costs to be set
	 */
	public void setAdditionalCosts(double additionalCosts) {
		this.additionalCosts = additionalCosts;
	}

	/**
	 * Returns tierID
	 * @return tierID
	 */
	public int getTierID() {
		return petID;
	}
	
	/**
	 * Returns fkKunde
	 * @return fkKunde
	 */
	public int getFkKunde() {
		return fkCustomer;
	}
	
	public void setFkKunde(int fkKunde) {
		this.fkCustomer = fkKunde;
	}
	
	public void setTierID(int tierID) {
		this.petID = tierID;
	}

	@Override
	public Object clone(){
		return new Pet(new String(this.getSpecies()), new String(this.getRace()), new String(this.getName()), this.getAge(),
				this.getSizeId(), new String(this.getDiseasePattern()), new String(this.getEatingHabits()),
				this.getRun(), new String(this.getContactOtherPets()), new String(this.getContactPeople()),
				new String(this.getRemarks()), this.getAdditionalCosts());
	}

	@Override
	public int getID() {
		return petID;
	}

}
