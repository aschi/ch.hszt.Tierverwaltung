package ch.hszt.tierverwaltung.backend;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class StayTest {
	
	@Test
	public void validateOkTest() {
		Stay stay = new Stay(1, 1, new Date(), new Date());
		
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
		Stay stay = new Stay(1, 1, null, new Date());
		
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
		Stay stay = new Stay(0, 1, new Date(), new Date());
		
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
		Stay stay = new Stay(1, 0, new Date(), new Date());
		
		List<String> ve = null;
		try {
			stay.validate();
		} catch (ValidationException e) {
			ve = e.getErrorMsgs();
		}
		
		TestCase.assertEquals("Petspace-ID missed", ve.get(0));
	}

}
