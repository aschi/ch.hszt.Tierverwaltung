package ch.hszt.tierverwaltung.database;

import java.sql.SQLException;
import java.util.List;

public interface IDatabaseAccess <T> {
	public void insert(T entry) throws SQLException;
	public void update(T entry) throws SQLException;
	public void delete(T entry) throws SQLException;
	public List<T> getList() throws SQLException;
	public T getEntry(int id) throws SQLException;
}
