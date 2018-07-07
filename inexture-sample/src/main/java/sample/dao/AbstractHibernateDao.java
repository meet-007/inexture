package sample.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDao<T extends Serializable> {

	private Class<T> clazz;

	@Autowired
	SessionFactory sessionFactory;

	public void create(T entity) {
		getCurrentSession().persist(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(long entityId) {
		final T entity = findOne(entityId);
		delete(entity);
	}

	public List<T> findAll() {
		final List<T> mylist = getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
		//System.out.println("###################################################"+clazz.getSimpleName());
		return mylist;
	}

	public T findOne(long id) {
		return getCurrentSession().get(clazz, id);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}
}