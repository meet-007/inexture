package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.TechDao;
import model.TechMaster;
import util.DbUtil;

public class TechDaoImpl implements TechDao {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(TechDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Tech.TechDao#selectTech()
	 */
	@Override
	public ArrayList<TechMaster> selectTech() throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectTech() - start"); //$NON-NLS-1$
		}
		final ResultSet rs = DbUtil.dbOperationSelect(SELECT);
		final ArrayList<TechMaster> technologies = new ArrayList<>();
		while (rs.next()) {
			final TechMaster tm = new TechMaster();
			tm.setIdtech(rs.getInt(1));
			tm.setTech(rs.getString(2));
			technologies.add(tm);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectTech() - end"); //$NON-NLS-1$
		}
		return technologies;
	}

}
