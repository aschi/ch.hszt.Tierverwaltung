package ch.hszt.tierverwaltung.database.customer;

import java.sql.SQLException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.database.IDataMapper;

/**
 * as explained in the lesson, we decided to make a fake-mock-test because 
 * we agreed that mocktests doesn't make much sense in our project.
 * More about is in our projectdocumentation.
 * @author prisi
 *
 */
public class CustomerDataMapperMockTest {

	/** jmock context */
	Mockery context;
	/** instance under test */
	Customer instance;

	@Before
	public void setUp() {
		context = new JUnit4Mockery();
		instance = new Customer();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void insertCustomerTest() throws SQLException {
		final IDataMapper<Customer> dba = (IDataMapper<Customer>) context
				.mock(IDataMapper.class);

		context.checking(new Expectations() {
			{
				// Check if insert did work on the interface
				one(dba).insert(instance);
			}
		});
		
		dba.insert(instance);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void updateCustomerTest() throws SQLException {
		final IDataMapper<Customer> dba = (IDataMapper<Customer>) context
				.mock(IDataMapper.class);

		instance.setAddress("newAdress");
		context.checking(new Expectations() {
			{
				// Check if the instance will be updatet
				one(dba).update(instance);
			}
		});
		
		dba.update(instance);
			
	}

	@Test
	@SuppressWarnings("unchecked")
	public void deleteCustomerTest() throws SQLException {
		final IDataMapper<Customer> dba = (IDataMapper<Customer>) context
				.mock(IDataMapper.class);
		
		context.checking(new Expectations() {
			{
				// Check if the instance will be deleted
				one(dba).delete(instance);
			}
		});
		
		try {
			dba.delete(instance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
