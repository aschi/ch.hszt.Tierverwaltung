package ch.hszt.tierverwaltung.backend;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class StayTest {
	private Pet pet = new Pet(1, 1, "Hund", "Mischling", "Hugo", 10, 1, "", "", '1', "gut", "gut", "", 0);
	private Petspace petSpace = new Petspace(1, 10, "equipment", 5, '1', 10, 1);
	
	@Test
	public void validateOkTest() {
		Stay stay = new Stay(pet, petSpace, new Date(), new Date());
		
		ValidationException ve = null;
		try {
			stay.validate();
		} catch (ValidationException e) {
			ve = e;
		}
		
		TestCase.assertSame(null, ve);
		
	}

	@Test
	public void validateDateFromNok() {
		Stay stay = new Stay(pet, petSpace, null, new Date());
		
		List<String> ve = null;
		try {
			stay.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("DateFrom is null", ve.get(0));
	}
	
	@Test
	public void validatePetIdNok() {
		Pet p2 = (Pet)pet.clone();
		p2.setPetID(0);
		Stay stay = new Stay(p2, petSpace, null, new Date());
		
		List<String> ve = null;
		try {
			stay.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Pet-ID missed", ve.get(0));
	}
	
	@Test
	public void validatePetspaceIdNok() {
		Petspace ps2 = (Petspace)petSpace.clone();
		ps2.setID(0);
		Stay stay = new Stay(pet, ps2, new Date(), new Date());
		
		List<String> ve = null;
		try {
			stay.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Petspace-ID missed", ve.get(0));
	}

}
