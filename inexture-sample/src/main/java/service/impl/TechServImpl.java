package service.impl;




import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.impl.TechDaoImpl;
import model.TechMaster;
import service.interfaces.TechServ;

// TODO: Auto-generated Javadoc
/**
 * The Class TechServImpl.
 */
public class TechServImpl implements TechServ {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(TechServImpl.class.getName());

	/* (non-Javadoc)
	 * @see service.Tech.TechServ#getTech()
	 */
	@Override
	public List<TechMaster> getTech() throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getTech() - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getTech() - end"); //$NON-NLS-1$
		}
		return new TechDaoImpl().selectTech();
	}

}
