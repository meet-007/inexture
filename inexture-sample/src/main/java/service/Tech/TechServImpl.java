package service.Tech;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.Tech.TechDao;
import dao.Tech.TechDaoImpl;

public class TechServImpl implements TechServ {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(TechServImpl.class.getName());

	public ArrayList getTech() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getTech() - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		TechDao td = new TechDaoImpl();
		ArrayList technologies = td.selectTech();

		if (logger.isDebugEnabled()) {
			logger.debug("getTech() - end"); //$NON-NLS-1$
		}
		return technologies;
	}

}
