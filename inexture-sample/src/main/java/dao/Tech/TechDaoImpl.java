package dao.Tech;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.TechMaster;
import util.DbUtil;

public class TechDaoImpl implements TechDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(TechDaoImpl.class.getName());

	public ArrayList selectTech() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectTech() - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		Connection con = DbUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(select);
		ArrayList technologies = new ArrayList();
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
