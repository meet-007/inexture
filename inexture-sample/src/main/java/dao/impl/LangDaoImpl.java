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
	private static final Logger LOGGER = LogManager.getLogger(LangDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Lang.LangDao#selectLang()
	 */
	@Override
	public ArrayList<LangMaster> selectLang() throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectLang() - start"); //$NON-NLS-1$
		}
		final ResultSet resultSet = DbUtil.dbOperationSelect(SELECT);
		final ArrayList<LangMaster> languages = new ArrayList<>();
		final LangMaster languageMaster = new LangMaster();
		while (resultSet.next()) {
			languageMaster.setIdlang(resultSet.getInt(1));
			languageMaster.setLang(resultSet.getString(2));
			languages.add(languageMaster);
		}
		resultSet.close();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectLang() - end"); //$NON-NLS-1$
		}
		return languages;
	}

}
