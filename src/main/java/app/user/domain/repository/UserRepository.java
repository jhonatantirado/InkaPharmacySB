package app.user.domain.repository;

import app.user.domain.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
	public void create(User user) throws SQLException;
	public void delete(User user) throws SQLException;
	public User read(long id) throws SQLException;
        public List<User> getAll() throws SQLException;
        public User findByName(String name) throws SQLException;
}
