package service.Tech;




import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.Tech.TechDaoImpl;
import model.TechMaster;

// TODO: Auto-generated Javadoc
/**
 * The Class TechServImpl.
 */
public class TechServImpl implements TechServ {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(TechServImpl.class.getName());

	/* (non-Javadoc)
	 * @see service.Tech.TechServ#getTech()
	 */
	public ArrayList<TechMaster> getTech() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getTech() - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		if (logger.isDebugEnabled()) {
			logger.debug("getTech() - end"); //$NON-NLS-1$
		}
		return new TechDaoImpl().selectTech();
	}

}
