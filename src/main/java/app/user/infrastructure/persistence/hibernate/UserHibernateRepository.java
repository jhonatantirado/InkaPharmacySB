package app.user.infrastructure.persistence.hibernate;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.user.domain.entity.User;
import app.user.domain.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class UserHibernateRepository extends BaseHibernateRepository<User> implements UserRepository {

	public UserHibernateRepository() {
		super(User.class);
	}

        @Override
        public List<User> getAll() throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(User.class);
            return criteria.list();
        }

        @Override
        public User findByName(String username) throws SQLException {
            Criteria criteria = unitOfWork.getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            return (User) criteria.uniqueResult();
        }
}
