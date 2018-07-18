package sample.services;




import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.dao.GenericHibernateDao;
import sample.models.TechMaster;

// TODO: Auto-generated Javadoc
/**
 * The Class TechServImpl.
 */
@Service
public class TechServImpl implements TechService {

	/** The generic hibernate dao. */
	@Autowired
	private GenericHibernateDao<TechMaster> genericHibernateDao;

	/* (non-Javadoc)
	 * @see sample.services.TechService#findAll()
	 */
	@Override
	@Transactional
	public List<TechMaster> findAll() {
		// TODO Auto-generated method stub
		genericHibernateDao.setClazz(TechMaster.class);
		return genericHibernateDao.findAll();
	}





}
