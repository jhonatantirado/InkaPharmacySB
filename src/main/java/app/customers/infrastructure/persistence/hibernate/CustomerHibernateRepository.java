package app.customers.infrastructure.persistence.hibernate;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.customers.domain.entity.Customer;
import app.customers.domain.repository.CustomerRepository;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> implements CustomerRepository {

	public CustomerHibernateRepository() {
		super(Customer.class);
	}

        @Override
        public List<Customer> getAll() throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Customer.class);
            return criteria.list();
        }

        @Override
        public Customer findByName(String name) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(Customer.class);
            criteria.add(Restrictions.eq("name", name));
            return (Customer) criteria.uniqueResult();
        }
}
