package ch.hszt.tierverwaltung.database.tierplatz;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Tierplatz;

public class TierplatzDataMapperDatabaseTest {

	@Test
	public void testInsertUpdateDeleteTierplatz() throws Exception {

		boolean testOk = true;
		String fehlertext = "";
		Tierplatz tierplatz =  null;

		TierplatzDataMapper tba = new TierplatzDataMapper();


		// INSERT Test
		//--------------
		tierplatz = new Tierplatz(1, 10, "2 Hütte, Wasserspender", 4, '1', 30);

		// Tier in Datenbank schreiben
		int id = tba.insert(tierplatz);
		System.out.println("tierplatz id " + id);

		List<Tierplatz> tierplatzList = tba.getList();
		if (tierplatzList == null || tierplatzList.isEmpty()) {
			testOk = false;
			fehlertext = fehlertext + " \n Methodenaufruf getList war falsch";
		}

		Tierplatz tierplatzDB = null;

		// Tier aus DB ermitteln
		for (Tierplatz t : tierplatzList) {
			if ((tierplatz.getGeeignetFuerTierID() == t.getGeeignetFuerTierID()
					&& tierplatz.getGroesse() == t.getGroesse()
					&& tierplatz.getAusstattung().equals(t.getAusstattung())
					&& tierplatz.getAnzahlTiere() == t.getAnzahlTiere()
					&& tierplatz.getAuslauf() == t.getAuslauf()
					&& tierplatz.getAuslaufGroesse() == t.getAuslaufGroesse())) {
				tierplatzDB = t;
			}
		}

		// Wenn der Tierplatz nicht in der Liste war, war Insert fehlerhaft
		if (tierplatzDB == null) {
			testOk = false;
			fehlertext = fehlertext + " \n Insert war falsch, Tier nicht in Liste enthalten";
		} else {
			System.out.println("tierplatz id dbtierplatz " + tierplatzDB.getID());
		}

		// Test UPDATE
		//------------------
		tierplatzDB.setAusstattung(tierplatzDB.getAusstattung() + " + Futternapf");
		tba.update(tierplatzDB);

		List<Tierplatz> tierplatzListupdate = tba.getList();
		Tierplatz tierplatzDBUpdate = null;
		if (tierplatzListupdate != null || !tierplatzListupdate.isEmpty()) {

			// Tier aus DB ermitteln
			for (Tierplatz t : tierplatzListupdate) {
				if ((tierplatzDB.getGeeignetFuerTierID() == t.getGeeignetFuerTierID()
						&& tierplatzDB.getGroesse() == t.getGroesse()
						&& tierplatzDB.getAusstattung().equals(t.getAusstattung())
						&& tierplatzDB.getAnzahlTiere() == t.getAnzahlTiere()
						&& tierplatzDB.getAuslauf() == t.getAuslauf()
						&& tierplatzDB.getAuslaufGroesse() == t.getAuslaufGroesse())) {
					tierplatzDBUpdate = t;
				}
			}
		}
		
		if (tierplatzDBUpdate == null) {
			testOk = false;
			fehlertext = fehlertext + " \n Update nicht korrekt verlaufen";
		}

		//Test der Methode getEntry
		//----------------------------------
		Tierplatz t = tba.getEntry(tierplatzDB.getID());

		// Wenn das Tier von getEntry nicht gleich unserem Tier ist, war Test
		// falsch
		if (t == null
				|| !(tierplatzDB.getGeeignetFuerTierID() == t.getGeeignetFuerTierID()
						&& tierplatzDB.getGroesse() == t.getGroesse()
						&& tierplatzDB.getAusstattung().equals(t.getAusstattung())
						&& tierplatzDB.getAnzahlTiere() == t.getAnzahlTiere()
						&& tierplatzDB.getAuslauf() == t.getAuslauf()
						&& tierplatzDB.getAuslaufGroesse() == t.getAuslaufGroesse())) {
			testOk = false;
			fehlertext = fehlertext + " \n getEntry Aufruf war falsch, nicht richtiger Tierplatz ermittelt";
		}

		// Test der Deletemethode
		//-----------------------------
		tba.delete(tierplatzDB);

		// Wenn Tier nun immernoch in Liste ist, war Test delete nicht
		// erfolgreich
		List<Tierplatz> nachDeleteList = tba.getList();
		for (Tierplatz t2 : nachDeleteList) {
			if (tierplatzDB.getID() == t2.getID()) {
				testOk = false;
				fehlertext = fehlertext + " \n Deletemethode war falsch, tierplatz immernoch in DB";
			}
		}

		TestCase.assertEquals(fehlertext, true, testOk);

	}

//	public static void main(String[] args) {
//		TierplatzDataMapperDatabaseTest test = new TierplatzDataMapperDatabaseTest();
//		try {
//			test.testInsertUpdateDeleteTierplatz();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("feeehler");
//		}
//	}

}
