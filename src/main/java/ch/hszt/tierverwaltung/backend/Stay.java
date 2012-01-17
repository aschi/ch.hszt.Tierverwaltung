package ch.hszt.tierverwaltung.backend;

import java.util.Date;

public class Stay implements IDataRecord {

	private int id;
	private Pet pet;
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
	 * @param pet
	 * @param petspace
	 * @param dateFrom
	 * @param dateTo
	 */
	public Stay(Pet pet, Petspace petspace, Date dateFrom, Date dateTo) {
		super();
		this.pet = pet;
		this.petspace = petspace;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	
	/**
	 * Constructor
	 * @param id
	 * @param pet
	 * @param petspace
	 * @param dateFrom
	 * @param dateTo
	 */
	public Stay(int id, Pet pet, Petspace petspace, Date dateFrom, Date dateTo) {
		super();
		this.id = id;
		this.pet = pet;
		this.petspace = petspace;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public void setID(int id){
		this.id = id;
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

		if (getPet()==null || getPet().getID() <= 0) {
			ve.addErrorMessage("Pet-ID missed");
		}

		if (getPetspace()==null || getPetspace().getID() <= 0) {
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
		return new Stay(getPet(), getPetspace(), getDateFrom()!=null?(Date)getDateFrom().clone():null, getDateTo()!=null?(Date)getDateTo().clone():null);
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Stay){
			Stay s = (Stay)o;
			boolean rv = true;
			
			if(getID() > 0){
				if(getID() != s.getID()){
					rv = false;
				}
			}
			
			if(!getPet().equals(s.getPet())){
				rv = false;
			}
			
			if(!getPetspace().equals(s.getPetspace())){
				rv = false;
			}
			
			if(!getDateFrom().equals(s.getDateFrom())){
				rv = false;
			}
			
			return rv;
		}else{
			return false;
		}
	}

}
