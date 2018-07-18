package sample.dao;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGenericDao.
 *
 * @param <T> the generic type
 */
public interface IGenericDao<T extends Serializable> {

	/**
	 * Clear.
	 */
	public void clear();

	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 */
	public void create(final T entity);

	/**
	 * Delete.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	public 	boolean delete(final T entity);

	/**
	 * Delete by id.
	 *
	 * @param entityId the entity id
	 * @return true, if successful
	 */
	public boolean  deleteById(final long entityId);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public	List<T> findAll();

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the t
	 */
	public T findOne(final long iduser);

	/**
	 * Flush.
	 */
	public void flush();

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the serializable
	 */
	public Serializable save(T entity);

	/**
	 * Save or update.
	 *
	 * @param entity the entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * Sets the clazz.
	 *
	 * @param clazzToSet the new clazz
	 */
	public void setClazz(Class<T> clazzToSet);
}