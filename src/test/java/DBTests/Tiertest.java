package DBTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Tiertest {

	public void tiertest() throws Exception {
		
		Class.forName("org.sqlite.JDBC");

		
		Connection conn = DriverManager.getConnection("jdbc:sqlite:tierverwaltung.db");
		String sql;
//		sql = "INSERT INTO 'tier' VALUES (null, 'katze', 'BobTail', 'Percy', 8, 1, 'Nierenprobleme', 'Abends Nassfutter', '1', 'Freundlich', 'Boese', 'keine', 3.80);";
		Statement stmt = conn.createStatement();
//		stmt.execute(sql);
	
		sql = "SELECT * FROM 'tier';";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println(rs.getString("art"));
			System.out.println(rs.getString("rasse"));
			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("tieralter"));
			System.out.println(rs.getString("groesseID"));
			System.out.println(rs.getString("krankheitsbild"));
			System.out.println(rs.getString("essgewohnheit"));
			System.out.println(rs.getString("auslauf"));
			System.out.println(rs.getString("umgangTier"));
			System.out.println(rs.getString("umgangMensch"));
			System.out.println(rs.getString("anmerkungen"));
			System.out.println(rs.getString("zusatzkosten"));
		}

		rs.close();
		conn.close();
		

	}

	public static void main(String[] args) {
		Tiertest test = new Tiertest();
		try {
			test.tiertest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("feeehler");
		}
	}

}
