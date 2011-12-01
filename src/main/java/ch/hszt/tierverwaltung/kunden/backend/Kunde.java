package ch.hszt.tierverwaltung.kunden.backend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.hszt.tierverwaltung.backend.IDataRecord;
import ch.hszt.tierverwaltung.backend.ValidationException;
import ch.hszt.tierverwaltung.tier.backend.Tier;

public class Kunde implements IDataRecord {
	
	private int id;
	private String name;
	private String vorname;
	private String addresse;
	private int plz;
	private String ort;
	private String telNo;
	private String email;
	private ArrayList<Tier> tiere;
	
	public Kunde() {
		name = "";
		vorname = "";
		addresse = "";
		ort = "";
		telNo = "";
		email = "";
		tiere = new ArrayList<Tier>();
	}
	
	public Kunde(int id, String name, String vorname, String addresse, int plz,
			String ort, String telNo, String email, ArrayList<Tier> tiere) {
		super();
		this.id = id;
		this.name = name;
		this.vorname = vorname;
		this.addresse = addresse;
		this.plz = plz;
		this.ort = ort;
		this.telNo = telNo;
		this.email = email;
		this.tiere = tiere;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Tier> getTiere() {
		return tiere;
	}

	public void setTiere(ArrayList<Tier> tiere) {
		this.tiere = tiere;
	}

	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void delete() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object clone(){
		return new Kunde(id, new String(name), new String(vorname), new String(addresse), plz,
				new String(ort), new String(telNo), new String(email), (ArrayList<Tier>)tiere.clone());
	}
	
	

}
