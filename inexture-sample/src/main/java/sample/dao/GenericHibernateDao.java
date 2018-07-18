package sample.dao;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * The Class GenericHibernateDao.
 *
 * @param <T> the generic type
 */
@Repository
@Scope("prototype")
public class GenericHibernateDao < T extends Serializable > extends AbstractHibernateDao< T > implements IGenericDao< T >
{
	//
}