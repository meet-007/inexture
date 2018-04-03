package dao.Tech;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.TechMaster;
import util.DbUtil;

public class TechDaoImpl implements TechDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(TechDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Tech.TechDao#selectTech()
	 */
	public ArrayList<TechMaster> selectTech() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectTech() - start"); //$NON-NLS-1$
		}
		ResultSet rs = DbUtil.dbOperationSelect(select);
		ArrayList<TechMaster> technologies = new ArrayList<TechMaster>();
		while (rs.next()) {
			TechMaster tm = new TechMaster();
			tm.setIdtech(rs.getInt(1));
			tm.setTech(rs.getString(2));
			technologies.add(tm);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("selectTech() - end"); //$NON-NLS-1$
		}
		return technologies;
	}

}
