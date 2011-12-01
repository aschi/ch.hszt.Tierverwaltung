package ch.hszt.tierverwaltung.kunden.backend;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.ValidationException;

public class KundeJTest {

	@Test
	public void validateOkTest() {
		Kunde kunde = new Kunde("Hans", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
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
		Kunde kunde = new Kunde("", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
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
		Kunde kunde = new Kunde("Hans", "", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
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
		Kunde kunde = new Kunde("Hans", "Muster", null, "ZH-8000", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
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
		Kunde kunde = new Kunde("Hans", "Muster", "Mustergasse 5", "", "Zürich", "0441001010", "hans.muster@mustdomain.mu");
		
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
		Kunde kunde = new Kunde("Hans", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", null, "hans.muster@mustdomain.mu");
		
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
		Kunde kunde = new Kunde("Hans", "Muster", "Mustergasse 5", "ZH-8000", "Zürich", "0441001010", null);
		
		List<String> ve = null;
		try {
			kunde.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("E-Mail-Adresse ist NULL", ve.get(0));
		
	}

}
