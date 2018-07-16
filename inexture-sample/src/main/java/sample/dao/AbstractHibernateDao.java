package sample.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDao<T extends Serializable> {

	private Class<T> clazz;

	@Autowired
	SessionFactory sessionFactory;


	public void clear() {
		getCurrentSession().clear();

	}

	public void create(T entity) {
		getCurrentSession().persist(entity);
	}

	public boolean delete(T entity) {
		try {
			getCurrentSession().delete(entity);
			return true;
		}catch (final EntityNotFoundException e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean deleteById(long entityId) {
		final T entity = findOne(entityId);
		return delete(entity);
	}

	public List<T> findAll() {
		final List<T> mylist = getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
		// System.out.println("###################################################"+clazz.getSimpleName());
		return mylist;
	}

	public T findOne(long id) {
		return getCurrentSession().get(clazz, id);
	}

	public void flush() {
		getCurrentSession().flush();

	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T entity) {
		return getCurrentSession().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

}