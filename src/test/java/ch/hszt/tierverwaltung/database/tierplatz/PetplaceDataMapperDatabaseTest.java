package ch.hszt.tierverwaltung.database.tierplatz;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Petplace;

public class PetplaceDataMapperDatabaseTest {

	@Test
	public void testInsertUpdateDeletePetplace() throws Exception {

		boolean testOk = true;
		String errorMessage = "";
		Petplace petplace =  null;

		PetplaceDataMapper tba = new PetplaceDataMapper();


		// INSERT Test
		//--------------
		petplace = new Petplace(1, 10, "2 Hütte, Wasserspender", 4, '1', 30);

		// Tier in Datenbank schreiben
		int id = tba.insert(petplace);
		System.out.println("petplace id " + id);

		List<Petplace> petplaceList = tba.getList();
		if (petplaceList == null || petplaceList.isEmpty()) {
			testOk = false;
			errorMessage = errorMessage + " \n method call getList was not successful";
		}

		Petplace petplaceFromDb = null;

		// Tier aus DB ermitteln
		for (Petplace t : petplaceList) {
			if (petplace.getAdaptedForPetID() == t.getAdaptedForPetID()
					&& petplace.getSize() == t.getSize()
					&& petplace.getEquipment().equals(t.getEquipment())
					&& petplace.getNoOfPets() == t.getNoOfPets()
					&& petplace.getRun() == t.getRun()
					&& petplace.getRunSize() == t.getRunSize()) {
				petplaceFromDb = t;
			}
		}

		// Wenn der Tierplatz nicht in der Liste war, war Insert fehlerhaft
		if (petplaceFromDb == null) {
			testOk = false;
			errorMessage = errorMessage + " \n Insert was not successfully";
		} else {
			System.out.println("petplace id petplaceFromDb " + petplaceFromDb.getID());
		}

		// Test UPDATE
		//------------------
		petplaceFromDb.setEquipment(petplaceFromDb.getEquipment() + " + Futternapf");
		tba.update(petplaceFromDb);

		List<Petplace> petplaceListUpdate = tba.getList();
		Petplace tierplatzDBUpdate = null;
		if (petplaceListUpdate != null || !petplaceListUpdate.isEmpty()) {

			// Tier aus DB ermitteln
			for (Petplace t : petplaceListUpdate) {
				if ((petplaceFromDb.getAdaptedForPetID() == t.getAdaptedForPetID()
						&& petplaceFromDb.getSize() == t.getSize()
						&& petplaceFromDb.getEquipment().equals(t.getEquipment())
						&& petplaceFromDb.getNoOfPets() == t.getNoOfPets()
						&& petplaceFromDb.getRun() == t.getRun()
						&& petplaceFromDb.getRunSize() == t.getRunSize())) {
					tierplatzDBUpdate = t;
				}
			}
		}
		
		if (tierplatzDBUpdate == null) {
			testOk = false;
			errorMessage = errorMessage + " \n Update was not successfully";
		}

		//Test der Methode getEntry
		//----------------------------------
		Petplace t = tba.getEntry(petplaceFromDb.getID());

		// Wenn das Tier von getEntry nicht gleich unserem Tier ist, war Test
		// falsch
		if (t == null
				|| !(petplaceFromDb.getAdaptedForPetID() == t.getAdaptedForPetID()
						&& petplaceFromDb.getSize() == t.getSize()
						&& petplaceFromDb.getEquipment().equals(t.getEquipment())
						&& petplaceFromDb.getNoOfPets() == t.getNoOfPets()
						&& petplaceFromDb.getRun() == t.getRun()
						&& petplaceFromDb.getRunSize() == t.getRunSize())) {
			testOk = false;
			errorMessage = errorMessage + " \n getEntry call was not susccessfully";
		}

		// Test der Deletemethode
		//-----------------------------
		tba.delete(petplaceFromDb);

		// Wenn Tier nun immernoch in Liste ist, war Test delete nicht
		// erfolgreich
		List<Petplace> nachDeleteList = tba.getList();
		for (Petplace t2 : nachDeleteList) {
			if (petplaceFromDb.getID() == t2.getID()) {
				testOk = false;
				errorMessage = errorMessage + " \n Delete method call was not successfully";
			}
		}

		TestCase.assertEquals(errorMessage, true, testOk);

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
