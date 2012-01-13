package ch.hszt.tierverwaltung.backend;

import java.util.Date;

public class Stay implements IDataRecord {

	private int id;
	private int petId;
	private Pet pet;
	private int petspaceId;
	private Petspace petspace;



	private Date dateFrom;
	private Date dateTo;

	/**
	 * Default Constructor
	 */
	public Stay() {
		
	}

	/**
	 * Constructor
	 * 
	 * @param petId
	 * @param petspaceId
	 * @param dateFrom
	 * @param dateTo
	 */
	public Stay(int petId, int petspaceId, Date dateFrom, Date dateTo) {
		super();
		this.petId = petId;
		this.petspaceId = petspaceId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	/**
	 * Constructor
	 * @param id
	 * @param petId
	 * @param petspaceId
	 * @param dateFrom
	 * @param dateTo
	 */
	public Stay(int id, int petId, int petspaceId, Date dateFrom, Date dateTo) {
		super();
		this.id = id;
		this.petId = petId;
		this.petspaceId = petspaceId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	
	public int getPetId() {
		return petId;
	}
	
	public void setID(int id){
		this.id = id;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getPetspaceId() {
		return petspaceId;
	}

	public void setPetspaceId(int petspaceId) {
		this.petspaceId = petspaceId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Petspace getPetspace() {
		return petspace;
	}

	public void setPetspace(Petspace petspace) {
		this.petspace = petspace;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void validate() throws ValidationException {
		ValidationException ve = new ValidationException();

		if (getPetId() <= 0) {
			ve.addErrorMessage("Pet-ID missed");
		}

		if (getPetspaceId() <= 0) {
			ve.addErrorMessage("Petspace-ID missed");
		}

		if (getDateFrom() == null) {
			ve.addErrorMessage("DateFrom is null");
		}

		if (getDateTo() == null) {
			ve.addErrorMessage("DateTo is null");
		}
		
		if (!ve.getErrorMsgs().isEmpty()) {
			throw ve;
		}
	}
	

	@Override
	public Object clone(){
		return new Stay(getPetId(), getPetspaceId(), new Date(getDateFrom().getTime()), new Date(getDateTo().getTime()));
	}

}
