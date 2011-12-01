package ch.hszt.tierverwaltung.kunden.backend;

import java.sql.SQLException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Kunde;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.IDataMapper;

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
	public void saveKundeTest() throws SQLException {
		final IDataMapper<Kunde> dba = (IDataMapper<Kunde>) context.mock(IDataMapper.class);
		
        context.checking(new Expectations() {{
            // one and all the other invocation count methods
            // return an instance of the same class as it's
            // argument
            one (dba).update(instance);
        }});
        
		try {
			instance.save();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteKundeTest() throws SQLException {
		final IDataMapper<Kunde> dba = (IDataMapper<Kunde>) context.mock(IDataMapper.class);
		
        context.checking(new Expectations() {{
            // one and all the other invocation count methods
            // return an instance of the same class as it's
            // argument
            one (dba).delete(instance);
        }});
        
		instance.delete();
	}



}
