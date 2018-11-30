package app.salesorder.infrastructure.persistence.hibernate;

import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.salesorder.domain.entity.Salesorder;
import app.salesorder.domain.repository.SalesorderRepository;

@Transactional(rollbackOn=Exception.class)
@Repository
public class SalesorderHibernateRepository extends BaseHibernateRepository<Salesorder> implements SalesorderRepository {

	public SalesorderHibernateRepository() {
		super(Salesorder.class);
	}
	
	@Override
    public List<Salesorder> getAll() throws SQLException {		
        Criteria criteria = unitOfWork.getSession().createCriteria(Salesorder.class);
        System.out.println("SalesorderHibernateRepository - getAll "  + criteria.list());
        return criteria.list();
    }

	@Override
	public Salesorder findByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
