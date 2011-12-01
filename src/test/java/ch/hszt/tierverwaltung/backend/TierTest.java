package ch.hszt.tierverwaltung.backend;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Tier;
import ch.hszt.tierverwaltung.backend.ValidationException;

public class TierTest {

	@Test
	public void validateOkTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);
		
		ValidationException ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e;
		}
		
		TestCase.assertSame(null, ve);
		
	}

	@Test
	public void validateArtNokTest() {
		Tier tier = new Tier(null, "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Art nicht abgefüllt", ve.get(0));
	}
	
	@Test
	public void validateRasseNokTest() {
		Tier tier = new Tier("Hund", "", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Rasse nicht abgefüllt", ve.get(0));
	}

	@Test
	public void validateNameNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Name nicht abgefüllt", ve.get(0));
	}
	
	@Test
	public void validateAuslaufNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '3', "Freundlich",
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Auslauf muss 0 (Nein) oder 1 (Ja) sein", ve.get(0));
	}
	
	@Test
	public void validateAnmerkungNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", null, 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Anmerkungen ist NULL", ve.get(0));
	}

	@Test
	public void validateEssgewohnheitNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", null, '1', "Freundlich",
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Essgewohnheit ist NULL", ve.get(0));
		
	}

	@Test
	public void validateGroesseNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 0,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Groesse ID muss Wert 1 (< 30 cm), 2 (> 30 cm) oder 3 (> 1m) enthalten", ve.get(0));
		
	}

	@Test
	public void validateTieralterNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", -1, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Alter muss > 0 sein", ve.get(0));
		
	}

	@Test
	public void validateUmgangMenschNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "freundlich",
				null, "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Umgang mit Mensch ist NULL", ve.get(0));
		
	}

	@Test
	public void validateUmgangTierNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', null,
				"Boese", "keine", 3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Umgang mit Tier ist NULL", ve.get(0));
		
	}
	
	@Test
	public void validateZusatzkostenNokTest() {
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", -3.80);
		
		List<String> ve = null;
		try {
			tier.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		TestCase.assertEquals("Zusatzkosten müssen > 0 sein", ve.get(0));
		
	}

}
