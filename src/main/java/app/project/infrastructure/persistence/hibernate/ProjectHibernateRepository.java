package app.project.infrastructure.persistence.hibernate;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.customers.domain.entity.Customer;
import app.customers.domain.repository.CustomerRepository;
import app.project.domain.entity.Project;
import app.project.domain.repository.ProjectRepository;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class ProjectHibernateRepository extends BaseHibernateRepository<Project> implements ProjectRepository {

	public ProjectHibernateRepository() {
		super(Project.class);
	}

        @Override
        public List<Project> getAll() throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Project.class);
            return criteria.list();
        }

        
       
        
        @Override
        public Project findByName(String name) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Project.class);
            criteria.add(Restrictions.eq("name", name));
            return (Project) criteria.uniqueResult();
        }
}
