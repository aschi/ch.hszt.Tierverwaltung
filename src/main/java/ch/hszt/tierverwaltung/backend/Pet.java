package ch.hszt.tierverwaltung.backend;


/**
 * Class pet, manages all data of a pet
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
	 * Default Constructor
	 */
	public Pet() {
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
	 * Constructor
	 * @param species
	 * @param race
	 * @param name
	 * @param age
	 * @param sizeId
	 * @param dieseasePattern
	 * @param eatingHabits
	 * @param run
	 * @param contactOtherPets
	 * @param contactPeople
	 * @param remarks
	 * @param additionalCosts
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
	 * Constructor
	 * @param petId
	 * @param fkCustomer
	 * @param species
	 * @param race
	 * @param name
	 * @param age
	 * @param sizeId
	 * @param diseasePattern
	 * @param eatingHabits
	 * @param run
	 * @param contactOtherPets
	 * @param contactPeople
	 * @param remarks
	 * @param additionalCosts
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
	 * Sets species
	 * @param species to be set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * Returns race
	 * @return race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * Sets race
	 * @param race to bet set
	 */
	public void setRace(String race) {
		this.race = race;
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
	 * Returns age
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * sets age
	 * @param age to be set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * returns sizeId
	 * @return sizeId
	 */
	public int getSizeId() {
		return sizeId;
	}

	/**
	 * Sets sizeId
	 * @param sizeId to be set
	 */
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	/**
	 * returns diseasePattern
	 * @return diseasePattern
	 */
	public String getDiseasePattern() {
		return diseasePattern;
	}

	/**
	 * 
	 * @param krankheitsbild
	 */
	public void setDiseasePattern(String diseasePattern) {
		this.diseasePattern = diseasePattern;
	}

	/**
	 * Returns eatingHabits
	 * @return eatingHabits
	 */
	public String getEatingHabits() {
		return eatingHabits;
	}

	/**
	 * Sets eatingHabits
	 * @param eatingHabits to be set
	 */
	public void setEatingHabits(String eatingHabits) {
		this.eatingHabits = eatingHabits;
	}

	/**
	 * Returns run
	 * @return run
	 */
	public char getRun() {
		return run;
	}

	/**
	 * Sets auslauf
	 * @param auslauf to be set
	 */
	public void setRun(char run) {
		this.run = run;
	}

	/**
	 * Returns contactOtherPets
	 * @return contactOtherPets
	 */
	public String getContactOtherPets() {
		return contactOtherPets;
	}

	/**
	 * Sets umgangTier
	 * @param umgangTier to be set
	 */
	public void setContactOtherPets(String contactOtherPets) {
		this.contactOtherPets = contactOtherPets;
	}

	/**
	 * returns contactPeople
	 * @return contactPeople
	 */
	public String getContactPeople() {
		return contactPeople;
	}

	/**
	 * Sets contactPeople
	 * @param contactPeople to be set
	 */
	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}

	/**
	 * Returns remarks
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Sets remarks
	 * @param remarks to be set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * Returns petID
	 * @return petID
	 */
	public int getPetId() {
		return petID;
	}
	
	/**
	 * Returns fkCustomer
	 * @return fkCustomer
	 */
	public int getFkCustomer() {
		return fkCustomer;
	}
	
	/**
	 * Sets fkCustomer
	 * @param fkCustomer
	 */
	public void setFkCustomer(int fkCustomer) {
		this.fkCustomer = fkCustomer;
	}
	
	/**
	 * Sets petID
	 * @param petID
	 */
	public void setPetID(int petID) {
		this.petID = petID;
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
	
	@Override
	public String toString(){
		return getName() + " - " + getSpecies();
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Pet){
			Pet p = (Pet)o;
			boolean rv = true;
			
			if(getPetId() > 0 && getPetId() != p.getPetId()){
				rv = false;
			}
			
			if(getFkCustomer() != p.getFkCustomer()){
				rv = false;
			}
	
			if(!getSpecies().equals(p.getSpecies())){
				rv = false;
			}
			
			if(!getRace().equals(p.getRace())){
				rv = false;
			}
			
			if(!getName().equals(p.getName())){
				rv = false;
			}
			
			if(getAge() != p.getAge()){
				rv = false;
			}
			
			if(getSizeId() != p.getSizeId()){
				rv = false;
			}
			
			if(!getDiseasePattern().equals(p.getDiseasePattern())){
				rv = false;
			}
			
			if(!getEatingHabits().equals(p.getEatingHabits())){
				rv = false;
			}
			
			if(getRun() != p.getRun()){
				rv = false;
			}
			
			if(!getContactOtherPets().equals(p.getContactOtherPets())){
				rv = false;
			}
			
			if(!getContactPeople().equals(p.getContactPeople())){
				rv = false;
			}
			
			if(!getRemarks().equals(p.getRemarks())){
				rv = false;
			}

			if(getAdditionalCosts() != p.getAdditionalCosts()){
				rv = false;
			}
			
			return rv;
		}else{
			return false;
		}
	}
	
}
