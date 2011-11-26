package ch.hszt.tierverwaltung.kunden.backend;

import java.sql.SQLException;

import ch.hszt.tierverwaltung.database.IDatabaseAccess;

public class Kunde {
	
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
	
	

}
