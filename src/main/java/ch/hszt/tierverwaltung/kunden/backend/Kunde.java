package ch.hszt.tierverwaltung.kunden.backend;

import ch.hszt.tierverwaltung.database.IDatabaseAccess;

public class Kunde {
	
	private IDatabaseAccess db;
	private int id;
	
	public Kunde() {
		
	}
	
	public Kunde(IDatabaseAccess db) {
		this.db = db;
	}
	
	public void save() {
		db.updateKunde(this);
	}
	
	public static Kunde open(int id, IDatabaseAccess db) {
		return db.getKunde(id);
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
