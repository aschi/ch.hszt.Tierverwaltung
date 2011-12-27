package ch.hszt.tierverwaltung.backend;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.backend.ValidationException;

public class KundeJTest {

	@Test
	public void validateOkTest() {
		Customer kunde = new Customer("Hans", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
		ValidationException ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e;
		}
		
		TestCase.assertSame(null, ve);
		
	}

	@Test
	public void validateArtNokTest() {
		Customer kunde = new Customer("", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
		List<String> ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Name nicht abgefüllt", ve.get(0));
	}
	
	@Test
	public void validateRasseNokTest() {
		Customer kunde = new Customer("Hans", "", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
		List<String> ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Vorname nicht abgefüllt", ve.get(0));
	}

	@Test
	public void validateNameNokTest() {
		Customer kunde = new Customer("Hans", "Muster", null, "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
		List<String> ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Adresse nicht abgefüllt", ve.get(0));
	}
	
	@Test
	public void validateAuslaufNokTest() {
		Customer kunde = new Customer("Hans", "Muster", "Mustergasse 5", "", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
		List<String> ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("PLZ nicht abgefüllt", ve.get(0));
	}
	
	@Test
	public void validateAnmerkungNokTest() {
		Customer kunde = new Customer("Hans", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", null, "hans.muster@mustdomain.mu");
		
		List<String> ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Telefonnummer nicht abgefüllt", ve.get(0));
	}

	@Test
	public void validateEssgewohnheitNokTest() {
		Customer kunde = new Customer("Hans", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", null);
		
		List<String> ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("E-Mail-Adresse ist NULL", ve.get(0));
		
	}

}
