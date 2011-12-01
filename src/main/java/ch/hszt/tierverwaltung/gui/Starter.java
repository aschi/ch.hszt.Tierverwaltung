package ch.hszt.tierverwaltung.gui;

import java.sql.SQLException;

import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.database.tier.TierDataMapper;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class Starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Tier tier = new Tier("Hund", "BobTail", "Percy", 8, 1,
				"Nierenprobleme", "Abends Nassfutter", '1', "Freundlich",
				"Boese", "keine", 3.80);
		TierDataMapper tdm = new TierDataMapper();
		try {
			tdm.save(tier);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new MainGui();
	}

}
