package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.TechDao;
import model.TechMaster;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class TechDaoImpl.
 */
public class TechDaoImpl implements TechDao {
	
	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(TechDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Tech.TechDao#selectTech()
	 */
	@Override
	public List<TechMaster> selectTech() throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectTech() - start"); //$NON-NLS-1$
		}
		try(final ResultSet resultSet = DbUtil.dbOperationSelect(SELECT)){
			final ArrayList<TechMaster> technologies = new ArrayList<>();
			while (resultSet.next()) {
				final TechMaster techMaster = new TechMaster();
				techMaster.setIdtech(resultSet.getInt(1));
				techMaster.setTech(resultSet.getString(2));
				technologies.add(techMaster);
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("selectTech() - end"); //$NON-NLS-1$


			}
			return technologies;
		}
	}

}
