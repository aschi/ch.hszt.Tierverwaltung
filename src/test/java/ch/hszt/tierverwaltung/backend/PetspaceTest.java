package ch.hszt.tierverwaltung.backend;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class PetspaceTest {
	
	@Test
	public void validateOkTest() {
		Petspace tierplatz = new Petspace(2, 200, "Schöne Möbel zum Verkratzen", 7, '1', 300);
		
		ValidationException ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e;
		}
		
		TestCase.assertSame(null, ve);
		
	}

	@Test
	public void validateGeeignetFuerTierIDNokTest() {
		Petspace tierplatz = new Petspace(0, 200, "Schöne Möbel zum Verkratzen", 7, '1', 300);
		
		List<String> ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		System.out.println(ve.get(0));
		TestCase.assertEquals("GeeignetFuerTierID muss Wert 1 (Hamster, Hase), 2 (Katze), 3 (kleine Hunde), 4 (grosse Hunde) enthalten", ve.get(0));
	}
	
	@Test
	public void validateGroesseNokTest() {
		Petspace tierplatz = new Petspace(2, 0, "Schöne Möbel zum Verkratzen", 7, '1', 300);
		
		List<String> ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		TestCase.assertEquals("Groesse muss > 0 sein", ve.get(0));
	}

	@Test
	public void validateAusstattungNokTest() {
		Petspace tierplatz = new Petspace(2, 10, "", 7, '1', 300);
		
		List<String> ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Ausstattung ist NULL", ve.get(0));
	}
	
	@Test
	public void validateAnzahlTiereNokTest() {
		Petspace tierplatz = new Petspace(2, 10, "Schöne Möbel zum Verkratzen", 0, '1', 300);
		
		List<String> ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Anzahl Tiere nicht abgefüllt", ve.get(0));
	}
	
	@Test
	public void validateAuslaufNokTest() {
		Petspace tierplatz = new Petspace(2, 10, "Schöne Möbel zum Verkratzen", 10, '5', 300);
		
		List<String> ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Auslauf muss 0 (Nein) oder 1 (Ja) sein", ve.get(0));
	}

	@Test
	public void validateAuslaufOhneGroesseNokTest() {
		Petspace tierplatz = new Petspace(2, 10, "Schöne Möbel zum Verkratzen", 10, '1', 0);
		
		List<String> ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Wenn Auslauf auf 1 (Ja) ist, dann muss die Auslaufgroesse abgefüllt werden", ve.get(0));
		
	}

	@Test
	public void validateAuslaufGroesseMitAuslaufNeinNokTest() {
		Petspace tierplatz = new Petspace(2, 10, "Schöne Möbel zum Verkratzen", 10, '0', 20);
		
		List<String> ve = null;
		try {
			tierplatz.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Wenn Auslauf auf 0 (Nein) ist, dann muss die Auslaufgroesse leer sein", ve.get(0));
		
	}

	
}
