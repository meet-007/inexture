package sample.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

	public void clear();

	void create(final T entity);

	boolean delete(final T entity);

	boolean deleteById(final long entityId);

	List<T> findAll();

	T findOne(final long id);

	public void flush();

	Serializable save(T entity);
	void saveOrUpdate(T entity);
	void setClazz(Class<T> clazzToSet);
}