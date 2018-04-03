package service.Lang;




import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.Lang.LangDaoImpl;
import model.LangMaster;

// TODO: Auto-generated Javadoc
/**
 * The Class LangServImpl.
 */
public class LangServImpl implements LangServ {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(LangServImpl.class.getName());

	/* (non-Javadoc)
	 * @see service.Lang.LangServ#getLang()
	 */
	public ArrayList<LangMaster> getLang() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLang() - start"); //$NON-NLS-1$
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getLang() - end"); //$NON-NLS-1$
		}
		return new LangDaoImpl().selectLang();
	}

}
