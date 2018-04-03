package dao.LangTransaction;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.LangTransact;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class LangTransImpl.
 */
public class LangTransImpl implements LangTrans {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(LangTransImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.LangTransaction.LangTrans#InsertLangTrans(java.util.ArrayList, java.lang.String)
	 */
	public int InsertLangTrans(ArrayList<LangTransact> it, String operation)
			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("InsertLangTrans(ArrayList<LangTransact>, String) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		ArrayList<Object> arr = new ArrayList<Object>();
		int count = 0;
		for (int i = 0; i < it.size(); i++) {
			arr.add(0, it.get(i).getIdlangmaster());
			arr.add(1, it.get(i).getIduser());
			if (operation.equals("insert")) {
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

		if (logger.isDebugEnabled()) {
			logger.debug("InsertLangTrans(ArrayList<LangTransact>, String) - end"); //$NON-NLS-1$
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.LangTransaction.LangTrans#selectUserLanguages(int)
	 */
	public ArrayList<LangTransact> selectUserLanguages(int iduser)
			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectUserLanguages(int) - start"); //$NON-NLS-1$
		}

		ResultSet rs = DbUtil.dbOperationSelect(SELECT, iduser);
		ArrayList<LangTransact> ltarr = new ArrayList<LangTransact>();
		while (rs.next()) {
			LangTransact lt = new LangTransact();
			lt.setIdlang_transaction(rs.getInt(1));
			lt.setIduser(rs.getInt(2));
			lt.setIdlangmaster(rs.getInt(3));
			ltarr.add(lt);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("selectUserLanguages(int) - end"); //$NON-NLS-1$
		}
		return ltarr;
	}

}
