package app.salesorder.domain.repository;

import java.sql.SQLException;
import java.util.List;

import app.salesorder.domain.entity.Salesorder;

public interface SalesorderRepository {
	public void create(Salesorder salesorder) throws SQLException;
	public void delete(Salesorder salesorder) throws SQLException;
	public Salesorder read(long id) throws SQLException;
        public List<Salesorder> getAll() throws SQLException;
        public Salesorder findByName(String name) throws SQLException;

}


