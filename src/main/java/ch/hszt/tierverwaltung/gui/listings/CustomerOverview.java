package ch.hszt.tierverwaltung.gui.listings;

import java.sql.SQLException;
import java.util.List;

import ch.hszt.tierverwaltung.backend.Customer;
import ch.hszt.tierverwaltung.backend.Pet;
import ch.hszt.tierverwaltung.database.customer.CustomerDataMapper;
import ch.hszt.tierverwaltung.gui.MainGui;

public class CustomerOverview extends Overview<Customer>{

	public CustomerOverview(MainGui gui) throws SQLException{
		super(gui);
		setMapper(new CustomerDataMapper());
		setInput(getMapper().getList());
		setUpTable();
	}
	
	public CustomerOverview(MainGui gui, List<Customer> input){
		super(gui, input);
		setUpTable();
	}
	
	private void setUpTable(){
		String[] columnNames = {"Name", "Vorname", "Ort", "Telefon Nr", "Tiere"};
		createTable(columnNames, getInput(), new Customer());
	}
	
	
	/**
	 * Tabelle aktualisieren mit der
	 */
	@Override
	public void updateTableValues(List<Customer> input) {
		super.updateTableValues(input);
		String[] row = new String[5];
		String tNames;
		
		for(Customer k : input){
			row[0] = k.getName();
			row[1] = k.getFirstName();
			row[2] = k.getCity();
			row[3] = k.getPhoneNo();
			
			tNames = "";
			for(Pet t : k.getPets()){
				tNames += t.getName() +", ";
			}
			row[4] = tNames.equals("") ? tNames : tNames.substring(0, tNames.length()-2);
			
			getModel().addRow(row);
		}
		
	}
	
	@Override
	public void updateTableValues(){
		try {
			if(getMapper() == null){
				setMapper(new CustomerDataMapper());
			}
			updateTableValues(getMapper().getList());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
}
