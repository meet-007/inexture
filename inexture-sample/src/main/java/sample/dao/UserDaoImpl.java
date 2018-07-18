package sample.dao;

import org.springframework.stereotype.Repository;

import sample.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDaoImpl.
 */
@Repository
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

	/* (non-Javadoc)
	 * @see sample.dao.UserDao#getUser(java.lang.String)
	 */
	@Override
	public User getUser(final String email) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().createNamedQuery("get_user_frm_email")
				.setParameter("email", email).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see sample.dao.UserDao#getUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User getUser(final String email, final String password) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().createNamedQuery("get_user_frm_email_pass")
				.setParameter("email", email).setParameter("password", password).uniqueResult();
	}

}
