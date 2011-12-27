package ch.hszt.tierverwaltung.database.kunde;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.database.kunde.KundeDataMapper;

public class CustomerDataMapperDatabaseTest {

	@Test
	public void testInsertUpdateDeleteCustomer() throws Exception {

		boolean testOk = true;
		String errorMessage = "";
		Customer customer =  null;

		KundeDataMapper tba = new KundeDataMapper();


		// INSERT Test
		//--------------
		customer = new Customer("Schmid", "Adrian", "IrgendwoimAG", "AG-9999", "Baden", "0793003030", "Aaaaschiiiii@gmx.ch");

		// kunde in Datenbank schreiben
		int id = tba.insert(customer);
		System.out.println("kunde id " + id);

		List<Customer> customerList = tba.getList();
		if (customerList == null || customerList.isEmpty()) {
			testOk = false;
			errorMessage = errorMessage + " \n Methodenaufruf getList war falsch";
		}

		Customer customerFromDb = null;

		// kunde aus DB ermitteln
		for (Customer t : customerList) {
			if ((customer.getName().equals(t.getName())
					&& customer.getFirstName().equals(t.getFirstName())
					&& customer.getAddress().equals(t.getAddress())
					&& customer.getZip().equals(t.getZip())
					&& customer.getCity().equals(t.getCity())
					&& customer.getPhoneNo().equals(t.getPhoneNo())
					&& customer.getEMail().equals(t.getEMail()))) {
				customerFromDb = t;
			}
		}

		// Wenn das kunde nicht in der Liste war, war Insert fehlerhaft
		if (customerFromDb == null) {
			testOk = false;
			errorMessage = errorMessage + " \n Insert war falsch, Kunde nicht in Liste enthalten";
		} else {
			System.out.println("customer id from customerFromDb " + customerFromDb.getCustomerId());
		}

		// Test UPDATE
		//------------------
		customerFromDb.setEMail("newEmail@haha.de");
		tba.update(customerFromDb);

		List<Customer> customerListUpdate = tba.getList();
		Customer customerDBUpdate = null;
		if (customerListUpdate != null || !customerListUpdate.isEmpty()) {

			// kunde aus DB ermitteln
			for (Customer t : customerListUpdate) {
				if ((customerFromDb.getName().equals(t.getName())
						&& customerFromDb.getFirstName().equals(t.getFirstName())
						&& customerFromDb.getAddress().equals(t.getAddress())
						&& customerFromDb.getZip().equals(t.getZip())
						&& customerFromDb.getCity().equals(t.getCity())
						&& customerFromDb.getPhoneNo().equals(t.getPhoneNo())
						&& customerFromDb.getEMail().equals(t.getEMail()))) {
					customerDBUpdate = t;
				}
			}
		}
		
		if (customerDBUpdate == null) {
			testOk = false;
			errorMessage = errorMessage + " \n Update did not run correctly";
		}

		//Test method getEntry
		//----------------------------------
		Customer t = tba.getEntry(customerFromDb.getCustomerId());

		//if customer from getEntry is not the same as our test customer, the test was not succesfull
		if (t == null
				|| !(customerFromDb.getName().equals(t.getName())
						&& customerFromDb.getFirstName().equals(t.getFirstName())
						&& customerFromDb.getAddress().equals(t.getAddress())
						&& customerFromDb.getZip().equals(t.getZip())
						&& customerFromDb.getCity().equals(t.getCity())
						&& customerFromDb.getPhoneNo().equals(t.getPhoneNo())
						&& customerFromDb.getEMail().equals(t.getEMail()))) {
			testOk = false;
			errorMessage = errorMessage + " \n getEntry call was not successfull";
		}

		// Test method delete
		//-----------------------------
		tba.delete(customerFromDb);

		//if customer is still in list, the test was not succesfullt
		List<Customer> afterDeleteList = tba.getList();
		for (Customer t2 : afterDeleteList) {
			if (customerFromDb.getCustomerId() == t2.getCustomerId()) {
				testOk = false;
				errorMessage = errorMessage + " \n Deletemethode was not succesfull, customer is still in database";
			}
		}

		TestCase.assertEquals(errorMessage, true, testOk);

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
