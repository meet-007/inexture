package service.Lang;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.Lang.LangDao;
import dao.Lang.LangDaoImpl;

public class LangServImpl implements LangServ {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LangServImpl.class.getName());

	public ArrayList getLang() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLang() - start"); //$NON-NLS-1$
		}

		LangDao lang = new LangDaoImpl();
		ArrayList returnArrayList = lang.selectLang();
		if (logger.isDebugEnabled()) {
			logger.debug("getLang() - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

}
