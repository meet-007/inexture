package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.LangDao;
import model.LangMaster;
import util.DbUtil;

public class LangDaoImpl implements LangDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LangDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Lang.LangDao#selectLang()
	 */
	public ArrayList<LangMaster> selectLang() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectLang() - start"); //$NON-NLS-1$
		}
		ResultSet rs = DbUtil.dbOperationSelect(select);
		ArrayList<LangMaster> languages = new ArrayList<>();
		while (rs.next()) {
			LangMaster lm = new LangMaster();
			lm.setIdlang(rs.getInt(1));
			lm.setLang(rs.getString(2));
			languages.add(lm);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("selectLang() - end"); //$NON-NLS-1$
		}
		return languages;
	}

}
