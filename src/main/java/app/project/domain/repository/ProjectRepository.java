package app.project.domain.repository;

import app.project.domain.entity.Project;
import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {
	public void create(Project project) throws SQLException;
	public void delete(Project project) throws SQLException;
	public Project read(long id) throws SQLException;
        public List<Project> getAll() throws SQLException;
        public Project findByName(String name) throws SQLException;
}
