package sample.dao;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.LangDao;
import model.LangMaster;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class LangDaoImpl.
 */
public class LangDaoImpl implements LangDao {
	
	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(LangDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Lang.LangDao#selectLang()
	 */
	@Override
	public List<LangMaster> selectLang() throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectLang() - start"); //$NON-NLS-1$
		}
		try(final ResultSet resultSet = DbUtil.dbOperationSelect(SELECT)){
			final ArrayList<LangMaster> languages = new ArrayList<>();
			while (resultSet.next()) {
				final LangMaster languageMaster = new LangMaster();
				languageMaster.setIdlang(resultSet.getInt(1));
				languageMaster.setLang(resultSet.getString(2));
				languages.add(languageMaster);
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("selectLang() - end"); //$NON-NLS-1$
			}

			return languages;
		}
	}

}
