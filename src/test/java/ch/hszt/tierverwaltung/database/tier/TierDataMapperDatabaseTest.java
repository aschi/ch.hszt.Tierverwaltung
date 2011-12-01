package ch.hszt.tierverwaltung.database.tier;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;

public class TierDataMapperDatabaseTest {

	@Test
	public void testInsertUpdateDeleteTier() throws Exception {

		boolean testOk = true;
		String fehlertext = "";
		Tier tier =  null;

		TierDataMapper tba = new TierDataMapper();


		// INSERT Test
		//--------------
		tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);

		// Tier in Datenbank schreiben
		int id = tba.insert(tier);
		System.out.println("tier id " + id);

		List<Tier> tierList = tba.getList();
		if (tierList == null || tierList.isEmpty()) {
			testOk = false;
			fehlertext = fehlertext + " \n Methodenaufruf getList war falsch";
		}

		Tier tierDB = null;

		// Tier aus DB ermitteln
		for (Tier t : tierList) {
			if ((tier.getArt().equals(t.getArt())
					&& tier.getRasse().equals(t.getRasse())
					&& tier.getName().equals(t.getName())
					&& tier.getTieralter() == t.getTieralter()
					&& tier.getGroesseID() == t.getGroesseID()
					&& tier.getKrankheitsbild().equals(t.getKrankheitsbild())
					&& tier.getEssgewohnheit().equals(t.getEssgewohnheit())
					&& tier.getAuslauf() == t.getAuslauf()
					&& tier.getUmgangMensch().equals(t.getUmgangMensch())
					&& tier.getUmgangTier().equals(t.getUmgangTier())
					&& tier.getAnmerkungen().equals(t.getAnmerkungen()) && tier
						.getZusatzkosten() == t.getZusatzkosten())) {
				tierDB = t;
			}
		}

		// Wenn das Tier nicht in der Liste war, war Insert fehlerhaft
		if (tierDB == null) {
			testOk = false;
			fehlertext = fehlertext + " \n Insert war falsch, Tier nicht in Liste enthalten";
		} else {
			System.out.println("tier id dbtier " + tierDB.getTierID());
		}

		// Test UPDATE
		//------------------
		tierDB.setAnmerkungen("anmerkungupdate");
		tba.update(tierDB);

		List<Tier> tierListupdate = tba.getList();
		Tier tierDBUpdate = null;
		if (tierListupdate != null || !tierListupdate.isEmpty()) {

			// Tier aus DB ermitteln
			for (Tier t : tierListupdate) {
				if ((tierDB.getArt().equals(t.getArt())
						&& tierDB.getRasse().equals(t.getRasse())
						&& tierDB.getName().equals(t.getName())
						&& tierDB.getTieralter() == t.getTieralter()
						&& tierDB.getGroesseID() == t.getGroesseID()
						&& tierDB.getKrankheitsbild().equals(t.getKrankheitsbild())
						&& tierDB.getEssgewohnheit().equals(t.getEssgewohnheit())
						&& tierDB.getAuslauf() == t.getAuslauf()
						&& tierDB.getUmgangMensch().equals(t.getUmgangMensch())
						&& tierDB.getUmgangTier().equals(t.getUmgangTier())
						&& tierDB.getAnmerkungen().equals(t.getAnmerkungen()) 
						&& tierDB.getZusatzkosten() == t.getZusatzkosten())) {
					tierDBUpdate = t;
				}
			}
		}
		
		if (tierDBUpdate == null) {
			testOk = false;
			fehlertext = fehlertext + " \n Update nicht korrekt verlaufen";
		}

		//Test der Methode getEntry
		//----------------------------------
		Tier t = tba.getEntry(tierDB.getTierID());

		// Wenn das Tier von getEntry nicht gleich unserem Tier ist, war Test
		// falsch
		if (t == null
				|| !(tierDB.getArt().equals(t.getArt())
						&& tierDB.getRasse().equals(t.getRasse())
						&& tierDB.getName().equals(t.getName())
						&& tierDB.getTieralter() == t.getTieralter()
						&& tierDB.getGroesseID() == t.getGroesseID()
						&& tierDB.getKrankheitsbild().equals(t.getKrankheitsbild())
						&& tierDB.getEssgewohnheit().equals(t.getEssgewohnheit())
						&& tierDB.getAuslauf() == t.getAuslauf()
						&& tierDB.getUmgangMensch().equals(t.getUmgangMensch())
						&& tierDB.getUmgangTier().equals(t.getUmgangTier())
						&& tierDB.getAnmerkungen().equals(t.getAnmerkungen()) 
						&& tierDB.getZusatzkosten() == t.getZusatzkosten())) {
			testOk = false;
			fehlertext = fehlertext + " \n getEntry Aufruf war falsch, nicht richtiges Tier ermittelt";
		}

		// Test der Deletemethode
		//-----------------------------
		tba.delete(tierDB);

		// Wenn Tier nun immernoch in Liste ist, war Test delete nicht
		// erfolgreich
		List<Tier> nachDeleteList = tba.getList();
		for (Tier t2 : nachDeleteList) {
			if (tierDB.getTierID() == t2.getTierID()) {
				testOk = false;
				fehlertext = fehlertext + " \n Deletemethode war falsch, tier immernoch in DB";
			}
		}

		TestCase.assertEquals(fehlertext, true, testOk);

	}

//	public static void main(String[] args) {
//		TierDatabaseAccessTest test = new TierDatabaseAccessTest();
//		try {
//			test.testInsertUpdateDeleteTier();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("feeehler");
//		}
//	}

}
