package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.LangTrans;
import model.LangTransact;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class LangTransImpl.
 */
@SuppressWarnings("PMD")
public class LangTransImpl implements LangTrans {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(LangTransImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.LangTransaction.LangTrans#InsertLangTrans(java.util.ArrayList, java.lang.String)
	 */
	@Override
	public int insertLangTrans(final List<LangTransact> languageTransaction,final  String operation)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("InsertLangTrans(ArrayList<LangTransact>, String) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		final ArrayList<Object> arr = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < languageTransaction.size(); i++) {
			arr.add(0, languageTransaction.get(i).getIdlangmaster());
			arr.add(1, languageTransaction.get(i).getIduser());
			if ("insert".equals(operation)) {
				if (!DbUtil.dbOperationInsert(INSERT, arr)) {
					count++;
				}
			} else {
				if (!DbUtil.dbOperationInsert(DELETE, arr)) {
					count++;
				}
			}
			arr.remove(0);
			arr.remove(0);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("InsertLangTrans(ArrayList<LangTransact>, String) - end"); //$NON-NLS-1$
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.LangTransaction.LangTrans#selectUserLanguages(int)
	 */
	@Override
	public ArrayList<LangTransact> selectUserLanguages(final int iduser)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectUserLanguages(int) - start"); //$NON-NLS-1$
		}

		try(final ResultSet resultSet = DbUtil.dbOperationSelect(SELECT, iduser)){
			final ArrayList<LangTransact> ltarr = new ArrayList<>();
			while (resultSet.next()) {
				final LangTransact languageTransaction = new LangTransact();
				languageTransaction.setIdlangTransaction(resultSet.getInt(1));
				languageTransaction.setIduser(resultSet.getInt(2));
				languageTransaction.setIdlangmaster(resultSet.getInt(3));
				ltarr.add(languageTransaction);
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("selectUserLanguages(int) - end"); //$NON-NLS-1$
			}
			return ltarr;
		}
	}

}
