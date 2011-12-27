package ch.hszt.tierverwaltung.database.tier;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;

public class TierDataMapperDatabaseTest {

	@Test
	public void testInsertUpdateDeleteTier() throws Exception {

		boolean testOk = true;
		String fehlertext = "";
		Pet tier =  null;

		TierDataMapper tba = new TierDataMapper();


		// INSERT Test
		//--------------
		tier = new Pet("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);

		// Tier in Datenbank schreiben
		int id = tba.insert(tier);
		System.out.println("tier id " + id);

		List<Pet> tierList = tba.getList();
		if (tierList == null || tierList.isEmpty()) {
			testOk = false;
			fehlertext = fehlertext + " \n Methodenaufruf getList war falsch";
		}

		Pet tierDB = null;

		// Tier aus DB ermitteln
		for (Pet t : tierList) {
			if ((tier.getSpecies().equals(t.getSpecies())
					&& tier.getRace().equals(t.getRace())
					&& tier.getName().equals(t.getName())
					&& tier.getAge() == t.getAge()
					&& tier.getSizeId() == t.getSizeId()
					&& tier.getDiseasePattern().equals(t.getDiseasePattern())
					&& tier.getEatingHabits().equals(t.getEatingHabits())
					&& tier.getRun() == t.getRun()
					&& tier.getContactPeople().equals(t.getContactPeople())
					&& tier.getContactOtherPets().equals(t.getContactOtherPets())
					&& tier.getRemarks().equals(t.getRemarks()) && tier
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
		tierDB.setRemarks("anmerkungupdate");
		tba.update(tierDB);

		List<Pet> tierListupdate = tba.getList();
		Pet tierDBUpdate = null;
		if (tierListupdate != null || !tierListupdate.isEmpty()) {

			// Tier aus DB ermitteln
			for (Pet t : tierListupdate) {
				if ((tierDB.getSpecies().equals(t.getSpecies())
						&& tierDB.getRace().equals(t.getRace())
						&& tierDB.getName().equals(t.getName())
						&& tierDB.getAge() == t.getAge()
						&& tierDB.getSizeId() == t.getSizeId()
						&& tierDB.getDiseasePattern().equals(t.getDiseasePattern())
						&& tierDB.getEatingHabits().equals(t.getEatingHabits())
						&& tierDB.getRun() == t.getRun()
						&& tierDB.getContactPeople().equals(t.getContactPeople())
						&& tierDB.getContactOtherPets().equals(t.getContactOtherPets())
						&& tierDB.getRemarks().equals(t.getRemarks()) 
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
		Pet t = tba.getEntry(tierDB.getTierID());

		// Wenn das Tier von getEntry nicht gleich unserem Tier ist, war Test
		// falsch
		if (t == null
				|| !(tierDB.getSpecies().equals(t.getSpecies())
						&& tierDB.getRace().equals(t.getRace())
						&& tierDB.getName().equals(t.getName())
						&& tierDB.getAge() == t.getAge()
						&& tierDB.getSizeId() == t.getSizeId()
						&& tierDB.getDiseasePattern().equals(t.getDiseasePattern())
						&& tierDB.getEatingHabits().equals(t.getEatingHabits())
						&& tierDB.getRun() == t.getRun()
						&& tierDB.getContactPeople().equals(t.getContactPeople())
						&& tierDB.getContactOtherPets().equals(t.getContactOtherPets())
						&& tierDB.getRemarks().equals(t.getRemarks()) 
						&& tierDB.getZusatzkosten() == t.getZusatzkosten())) {
			testOk = false;
			fehlertext = fehlertext + " \n getEntry Aufruf war falsch, nicht richtiges Tier ermittelt";
		}

		// Test der Deletemethode
		//-----------------------------
		tba.delete(tierDB);

		// Wenn Tier nun immernoch in Liste ist, war Test delete nicht
		// erfolgreich
		List<Pet> nachDeleteList = tba.getList();
		for (Pet t2 : nachDeleteList) {
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
