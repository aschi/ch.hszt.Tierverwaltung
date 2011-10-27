package ch.hszt.tierverwaltung.database;

import ch.hszt.tierverwaltung.kunden.backend.Kunde;

public interface IDatabaseAccess {
	
	public Kunde getKunde(int id);
	
	public void updateKunde(Kunde kunde);

}
