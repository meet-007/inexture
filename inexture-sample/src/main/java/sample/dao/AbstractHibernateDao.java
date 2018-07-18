package sample.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractHibernateDao.
 *
 * @param <T> the generic type
 */
@SuppressWarnings("PMD")
public abstract class AbstractHibernateDao<T extends Serializable> {

	/** The clazz. */
	private Class<T> clazz;

	/** The session factory. */
	@Autowired
	public SessionFactory sessionFactory;


	/**
	 * Clear.
	 */
	public void clear() {
		getCurrentSession().clear();

	}

	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 */
	public void create(final T entity) {
		getCurrentSession().persist(entity);
	}

	/**
	 * Creates the query.
	 *
	 * @param name the name
	 * @param parameters the parameters
	 * @return the list
	 */
	public List<T> createQuery(final String name,final Object ...parameters) {
		final Query query = getCurrentSession().createNamedQuery(name);
		int i=0;
		for(final Object pObject:parameters) {
			query.setParameter(i, pObject);
			i++;
		}
		return query.getResultList();

	}

	/**
	 * Delete.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	public boolean delete(final T entity) {
		try {
			getCurrentSession().delete(entity);
			return true;
		}catch (final EntityNotFoundException e) {
			// TODO: handle exception
			return false;
		}

	}

	/**
	 * Delete by id.
	 *
	 * @param entityId the entity id
	 * @return true, if successful
	 */
	public boolean deleteById(final long entityId) {
		final T entity = findOne(entityId);
		return delete(entity);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<T> findAll() {

		return  getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the t
	 */
	public T findOne(final long id) {
		return getCurrentSession().get(clazz, id);
	}

	/**
	 * Flush.
	 */
	public void flush() {
		getCurrentSession().flush();

	}

	/**
	 * Gets the current session.
	 *
	 * @return the current session
	 */
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the serializable
	 */
	public Serializable save(final T entity) {
		return getCurrentSession().save(entity);
	}

	/**
	 * Save or update.
	 *
	 * @param entity the entity
	 */
	public void saveOrUpdate(final T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	/**
	 * Sets the clazz.
	 *
	 * @param clazzToSet the new clazz
	 */
	public final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

}