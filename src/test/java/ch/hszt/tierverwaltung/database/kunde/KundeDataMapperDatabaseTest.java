package ch.hszt.tierverwaltung.database.kunde;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.database.kunde.KundeDataMapper;

public class KundeDataMapperDatabaseTest {

	@Test
	public void testInsertUpdateDeleteKunde() throws Exception {

		boolean testOk = true;
		String fehlertext = "";
		Customer kunde =  null;

		KundeDataMapper tba = new KundeDataMapper();


		// INSERT Test
		//--------------
		kunde = new Customer("Schmid", "Adrian", "IrgendwoimAG", "AG-9999", "Baden", "0793003030", "Aaaaschiiiii@gmx.ch");

		// kunde in Datenbank schreiben
		int id = tba.insert(kunde);
		System.out.println("kunde id " + id);

		List<Customer> kundeList = tba.getList();
		if (kundeList == null || kundeList.isEmpty()) {
			testOk = false;
			fehlertext = fehlertext + " \n Methodenaufruf getList war falsch";
		}

		Customer kundeDB = null;

		// kunde aus DB ermitteln
		for (Customer t : kundeList) {
			if ((kunde.getName().equals(t.getName())
					&& kunde.getFirstName().equals(t.getFirstName())
					&& kunde.getAddress().equals(t.getAddress())
					&& kunde.getZip().equals(t.getZip())
					&& kunde.getCity().equals(t.getCity())
					&& kunde.getPhoneNo().equals(t.getPhoneNo())
					&& kunde.getEMail().equals(t.getEMail()))) {
				kundeDB = t;
			}
		}

		// Wenn das kunde nicht in der Liste war, war Insert fehlerhaft
		if (kundeDB == null) {
			testOk = false;
			fehlertext = fehlertext + " \n Insert war falsch, Kunde nicht in Liste enthalten";
		} else {
			System.out.println("kunde id dbkunde " + kundeDB.getCustomerId());
		}

		// Test UPDATE
		//------------------
		kundeDB.setEMail("neueEmail@haha.de");
		tba.update(kundeDB);

		List<Customer> kundeListUpdate = tba.getList();
		Customer kundeDBUpdate = null;
		if (kundeListUpdate != null || !kundeListUpdate.isEmpty()) {

			// kunde aus DB ermitteln
			for (Customer t : kundeListUpdate) {
				if ((kundeDB.getName().equals(t.getName())
						&& kundeDB.getFirstName().equals(t.getFirstName())
						&& kundeDB.getAddress().equals(t.getAddress())
						&& kundeDB.getZip().equals(t.getZip())
						&& kundeDB.getCity().equals(t.getCity())
						&& kundeDB.getPhoneNo().equals(t.getPhoneNo())
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
		Customer t = tba.getEntry(kundeDB.getCustomerId());

		// Wenn das kunde von getEntry nicht gleich unserem kunde ist, war Test
		// falsch
		if (t == null
				|| !(kundeDB.getName().equals(t.getName())
						&& kundeDB.getFirstName().equals(t.getFirstName())
						&& kundeDB.getAddress().equals(t.getAddress())
						&& kundeDB.getZip().equals(t.getZip())
						&& kundeDB.getCity().equals(t.getCity())
						&& kundeDB.getPhoneNo().equals(t.getPhoneNo())
						&& kundeDB.getEMail().equals(t.getEMail()))) {
			testOk = false;
			fehlertext = fehlertext + " \n getEntry Aufruf war falsch, nicht richtiger Kunde ermittelt";
		}

		// Test der Deletemethode
		//-----------------------------
		tba.delete(kundeDB);

		// Wenn Kunde nun immernoch in Liste ist, war Test delete nicht
		// erfolgreicht
		List<Customer> nachDeleteList = tba.getList();
		for (Customer t2 : nachDeleteList) {
			if (kundeDB.getCustomerId() == t2.getCustomerId()) {
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
