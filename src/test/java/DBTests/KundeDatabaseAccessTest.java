package DBTests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.database.kunde.KundeDataMapper;
import ch.hszt.tierverwaltung.kunden.backend.Kunde;

public class KundeDatabaseAccessTest {

	@Test
	public void testInsertUpdateDeleteKunde() throws Exception {

		boolean testOk = true;
		String fehlertext = "";
		Kunde kunde =  null;

		KundeDataMapper tba = new KundeDataMapper();


		// INSERT Test
		//--------------
		kunde = new Kunde("Schmid", "Adrian", "IrgendwoimAG", "AG-9999", "Baden", "0793003030", "Aaaaschiiiii@gmx.ch");

		// kunde in Datenbank schreiben
		int id = tba.insert(kunde);
		System.out.println("kunde id " + id);

		List<Kunde> kundeList = tba.getList();
		if (kundeList == null || kundeList.isEmpty()) {
			testOk = false;
			fehlertext = fehlertext + " \n Methodenaufruf getList war falsch";
		}

		Kunde kundeDB = null;

		// kunde aus DB ermitteln
		for (Kunde t : kundeList) {
			if ((kunde.getName().equals(t.getName())
					&& kunde.getVorname().equals(t.getVorname())
					&& kunde.getAdresse().equals(t.getAdresse())
					&& kunde.getPlz().equals(t.getPlz())
					&& kunde.getOrt().equals(t.getOrt())
					&& kunde.getTelefon().equals(t.getTelefon())
					&& kunde.getEMail().equals(t.getEMail()))) {
				kundeDB = t;
			}
		}

		// Wenn das kunde nicht in der Liste war, war Insert fehlerhaft
		if (kundeDB == null) {
			testOk = false;
			fehlertext = fehlertext + " \n Insert war falsch, Kunde nicht in Liste enthalten";
		} else {
			System.out.println("kunde id dbkunde " + kundeDB.getKundeID());
		}

		// Test UPDATE
		//------------------
		kundeDB.setEMail("neueEmail@haha.de");
		tba.update(kundeDB);

		List<Kunde> kundeListUpdate = tba.getList();
		Kunde kundeDBUpdate = null;
		if (kundeListUpdate != null || !kundeListUpdate.isEmpty()) {

			// kunde aus DB ermitteln
			for (Kunde t : kundeListUpdate) {
				if ((kundeDB.getName().equals(t.getName())
						&& kundeDB.getVorname().equals(t.getVorname())
						&& kundeDB.getAdresse().equals(t.getAdresse())
						&& kundeDB.getPlz().equals(t.getPlz())
						&& kundeDB.getOrt().equals(t.getOrt())
						&& kundeDB.getTelefon().equals(t.getTelefon())
						&& kundeDB.getEMail().equals(t.getEMail()))) {
					kundeDBUpdate = t;
				}
			}
		}
		
		if (kundeDBUpdate == null) {
			testOk = false;
			fehlertext = fehlertext + " \n Update nicht korrekt verlaufen";
		}

		//Test der Methode getEntry
		//----------------------------------
		Kunde t = tba.getEntry(kundeDB.getKundeID());

		// Wenn das kunde von getEntry nicht gleich unserem kunde ist, war Test
		// falsch
		if (t == null
				|| !(kundeDB.getName().equals(t.getName())
						&& kundeDB.getVorname().equals(t.getVorname())
						&& kundeDB.getAdresse().equals(t.getAdresse())
						&& kundeDB.getPlz().equals(t.getPlz())
						&& kundeDB.getOrt().equals(t.getOrt())
						&& kundeDB.getTelefon().equals(t.getTelefon())
						&& kundeDB.getEMail().equals(t.getEMail()))) {
			testOk = false;
			fehlertext = fehlertext + " \n getEntry Aufruf war falsch, nicht richtiger Kunde ermittelt";
		}

		// Test der Deletemethode
		//-----------------------------
		tba.delete(kundeDB);

		// Wenn Kunde nun immernoch in Liste ist, war Test delete nicht
		// erfolgreicht
		List<Kunde> nachDeleteList = tba.getList();
		for (Kunde t2 : nachDeleteList) {
			if (kundeDB.getKundeID() == t2.getKundeID()) {
				testOk = false;
				fehlertext = fehlertext + " \n Deletemethode war falsch, Kunde immernoch in DB";
			}
		}

		TestCase.assertEquals(fehlertext, true, testOk);

	}

//	public static void main(String[] args) {
//		KundeDatabaseAccessTest test = new KundeDatabaseAccessTest();
//		try {
//			test.testInsertUpdateDeleteKunde();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			  e.printStackTrace();
//			System.out.println("feeehler");
//		}
//	}

}
