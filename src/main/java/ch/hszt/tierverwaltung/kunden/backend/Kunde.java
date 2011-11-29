package ch.hszt.tierverwaltung.kunden.backend;

import java.sql.SQLException;

import ch.hszt.tierverwaltung.backend.IDataRecord;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.IDatabaseAccess;

public class Kunde implements IDataRecord {
	
	private IDatabaseAccess<Kunde> db;
	private int id;
	
	public Kunde() {
		
	}
	
	public Kunde(IDatabaseAccess db) {
		this.db = db;
	}
	
	public void save() throws SQLException {
		db.update(this);
	}
	
	public static Kunde open(int id, IDatabaseAccess db) throws SQLException {
		return (Kunde) db.getEntry(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IDatabaseAccess getDb() {
		return db;
	}

	public void setDb(IDatabaseAccess db) {
		this.db = db;
	}

	@Override
	public void delete() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object clone(){
		return null;
	}
	
	

}
