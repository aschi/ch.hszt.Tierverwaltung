package ch.hszt.tierverwaltung.backend;

import java.util.Date;

public class Stay implements IDataRecord {

	private int id;
	private int petId;
	private int petspaceId;
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
			ve.addErrorMessage("Pespace-ID missed");
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
