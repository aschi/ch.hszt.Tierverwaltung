package ch.hszt.tierverwaltung.database.kunde;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.*;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Kunde;
import ch.hszt.tierverwaltung.database.IDataMapper;

public class KundeDataMapperMockTest {

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
	public void inserTest() {
		final IDataMapper<Kunde> dba = (IDataMapper<Kunde>)context.mock(IDataMapper.class);
		
		context.checking(new Expectations() {
			oneOf (dba).insert(instance);
		});
		
	}
	
	// @Test
	// public void saveKundeTest() throws SQLException {
	// final IDataMapper<Kunde> dba = (IDataMapper<Kunde>)
	// context.mock(IDataMapper.class);
	//
	// context.checking(new Expectations() {{
	// // one and all the other invocation count methods
	// // return an instance of the same class as it's
	// // argument
	// one (dba).update(instance);
	// }});
	//
	// try {
	// instance.save();
	// } catch (ValidationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	
	
	
	@Test
	public void saveTest() {

	}

	// public int insert(T entry) throws SQLException;
	// public void update(T entry) throws SQLException;
	// public void delete(T entry) throws SQLException;
	// public List<T> getList() throws SQLException;
	// public T getEntry(int id) throws SQLException;

	//
	// @Before
	// public void setUp() {
	// context = new JUnit4Mockery();
	// instance = new Kunde();
	// }
	//
	// @Test
	// public void saveKundeTest() throws SQLException {
	// final IDataMapper<Kunde> dba = (IDataMapper<Kunde>)
	// context.mock(IDataMapper.class);
	//
	// context.checking(new Expectations() {{
	// // one and all the other invocation count methods
	// // return an instance of the same class as it's
	// // argument
	// one (dba).update(instance);
	// }});
	//
	// try {
	// instance.save();
	// } catch (ValidationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Test
	// public void deleteKundeTest() throws SQLException {
	// final IDataMapper<Kunde> dba = (IDataMapper<Kunde>)
	// context.mock(IDataMapper.class);
	//
	// context.checking(new Expectations() {{
	// // one and all the other invocation count methods
	// // return an instance of the same class as it's
	// // argument
	// one (dba).delete(instance);
	// }});
	//
	// instance.delete();
	// }
}
