package ch.hszt.tierverwaltung.kunden.backend;

import static org.junit.Assert.fail;
import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import ch.hszt.tierverwaltung.database.IDatabaseAccess;

public class KundeTest {

    /** jmock context */
    Mockery context;
    /** instance under test */
    Kunde instance;
    
    @Before
    public void setUp() {
        context = new JUnit4Mockery();
        instance = new Kunde();
    }
    
	@Test
	public void saveKundeTest() {
		final IDatabaseAccess dba = context.mock(IDatabaseAccess.class);
		instance.setDb(dba);
		
        context.checking(new Expectations() {{
            // one and all the other invocation count methods
            // return an instance of the same class as it's
            // argument
            one (dba).updateKunde(instance);
        }});
        
		instance.save();
		
		
	}
	
	@Test
	public void openKundeTest() {
		final IDatabaseAccess dba = context.mock(IDatabaseAccess.class);
		instance.setDb(dba);
		instance.setId(1);
		
        context.checking(new Expectations() {{
            // one and all the other invocation count methods
            // return an instance of the same class as it's
            // argument
            oneOf (dba).getKunde(1); will(returnValue(instance));
        }});
        
		Kunde compare = Kunde.open(1, dba);
		
		TestCase.assertEquals(compare, instance);
		
		
	}

}