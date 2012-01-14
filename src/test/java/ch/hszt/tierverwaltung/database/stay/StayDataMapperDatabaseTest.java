package ch.hszt.tierverwaltung.database.stay;


import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.backend.Petspace;
import ch.hszt.tierverwaltung.backend.Stay;

public class StayDataMapperDatabaseTest {

	@Test
	public void testInsertUpdateDeleteStay() throws Exception {

		boolean testOk = true;
		String errorMessage = "";
		Stay stay =  null;

		StayDataMapper tba = new StayDataMapper();


		// INSERT Test
		//--------------
		stay = new Stay(new Pet(), new Petspace(), new Date(), new Date());

		// Stay in Datenbank schreiben
		int id = tba.insert(stay);
		System.out.println("stay id " + id);

		List<Stay> stayList = tba.getList();
		if (stayList == null || stayList.isEmpty()) {
			testOk = false;
			errorMessage = errorMessage + " \n method call getList was not successful";
		}

		Stay stayFromDb = null;

		// stay aus DB ermitteln
		for (Stay t : stayList) {
			System.out.println(t.getDateFrom().getTime());
			System.out.println(stay.getDateFrom().getTime());
			System.out.println(t.getDateFrom());
			System.out.println(stay.getDateFrom());
			if (stay.getPet().getID() == t.getPet().getID() &&
					stay.getPetspace().getID() == t.getPetspace().getID()
							&& stay.getDateFrom().getTime() == t.getDateFrom().getTime()
							&& stay.getDateTo().getTime() == t.getDateTo().getTime()) {
				stayFromDb = t;
			}
		}

		// Wenn der Tierplatz nicht in der Liste war, war Insert fehlerhaft
		if (stayFromDb == null) {
			testOk = false;
			errorMessage = errorMessage + " \n Insert was not successfully";
		} else {
			System.out.println("petplace id petplaceFromDb " + stayFromDb.getID());
		}

		// Test UPDATE
		//------------------
		stayFromDb.setDateFrom(new Date());
		tba.update(stayFromDb);

		List<Stay> stayListUpdate = tba.getList();
		Stay stayBUpdate = null;
		if (stayListUpdate != null || !stayListUpdate.isEmpty()) {

			// Tier aus DB ermitteln
			for (Stay t : stayListUpdate) {
				if (stayFromDb.getPet().getID() == t.getPet().getID()
						&& stayFromDb.getPetspace().getID() == t.getPetspace().getID()
								&& stayFromDb.getDateFrom().getTime() == t.getDateFrom().getTime()
								&& stayFromDb.getDateTo().getTime() == t.getDateTo().getTime()) {
					stayBUpdate = t;
				}
			}
		}
		
		if (stayBUpdate == null) {
			testOk = false;
			errorMessage = errorMessage + " \n Update was not successfully";
		}

		//Test der Methode getEntry
		//----------------------------------
		Stay t = tba.getEntry(stayFromDb.getID());

		// Wenn das Tier von getEntry nicht gleich unserem Tier ist, war Test
		// falsch
		if (t == null
				|| !(stayFromDb.getPet().getID() == t.getPet().getID()
						&& stayFromDb.getPetspace().getID() == t.getPetspace().getID()
								&& stayFromDb.getDateFrom().getTime() == t.getDateFrom().getTime()
								&& stayFromDb.getDateTo().getTime() == t.getDateTo().getTime())) {
			testOk = false;
			errorMessage = errorMessage + " \n getEntry call was not susccessfully";
		}

		// Test der Deletemethode
		//-----------------------------
		tba.delete(stayFromDb);

		// Wenn Tier nun immernoch in Liste ist, war Test delete nicht
		// erfolgreich
		List<Stay> nachDeleteList = tba.getList();
		for (Stay t2 : nachDeleteList) {
			if (stayFromDb.getID() == t2.getID()) {
				testOk = false;
				errorMessage = errorMessage + " \n Delete method call was not successfully";
			}
		}

		TestCase.assertEquals(errorMessage, true, testOk);

	}

//	public static void main(String[] args) {
//		StayDataMapperDatabaseTest test = new StayDataMapperDatabaseTest();
//		try {
//			test.testInsertUpdateDeleteStay();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("feeehler");
//		}
//	}

}
