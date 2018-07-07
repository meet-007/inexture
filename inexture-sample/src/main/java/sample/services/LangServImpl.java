package sample.services;




import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.dao.GenericHibernateDao;
import sample.models.LangMaster;

// TODO: Auto-generated Javadoc
/**
 * The Class LangServImpl.
 */
@Service
public class LangServImpl implements LangServ {

	@Autowired
	GenericHibernateDao<LangMaster> genericHibernateDao;

	@Override
	@Transactional
	public List<LangMaster> findAll() {
		// TODO Auto-generated method stub
		genericHibernateDao.setClazz(LangMaster.class);
		return genericHibernateDao.findAll();
	}




}
