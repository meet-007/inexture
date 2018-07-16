package sample.dao;

import org.springframework.stereotype.Repository;

import sample.models.User;

@Repository
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().createNamedQuery("get_user_frm_email")
				.setParameter("email", email).uniqueResult();
	}

	@Override
	public User getUser(String email, String password) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().createNamedQuery("get_user_frm_email_pass")
				.setParameter("email", email).setParameter("password", password).uniqueResult();
	}

}
