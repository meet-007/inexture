package service.impl;





import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.LangTrans;
import model.LangTransact;
import service.interfaces.LangTransServ;

/**
 * The Class LangTransImpl.
 */
public class LangTransImpl implements LangTransServ {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(LangTransImpl.class.getName());

	/**
	 * Sets the params.
	 *
	 * @param request the request
	 * @param userid the userid
	 * @return the array list
	 */
	public static ArrayList<LangTransact> setParams(HttpServletRequest request, int userid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		final String[] lang = request.getParameterValues("lang");
		final ArrayList<LangTransact> it = new ArrayList<>();
		for (final String element : lang) {
			final LangTransact lt = new LangTransact();
			lt.setIdlangmaster(Integer.parseInt(element));
			lt.setIduser(userid);
			it.add(lt);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return it;
	}

	/* (non-Javadoc)
	 * @see service.impl.LangTransServ#addLangTransaction(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public boolean addLangTransaction(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addLangTransaction(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		final ArrayList<LangTransact> it = LangTransImpl.setParams(request, userid);
		final LangTrans langtrans = new dao.impl.LangTransImpl();
		final int rowsAffected = langtrans.insertLangTrans(it, "insert");
		if (rowsAffected > 0) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see service.impl.LangTransServ#getUserLanguages(int)
	 */
	@Override
	public ArrayList<LangTransact> getUserLanguages(int iduser)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUserLanguages(int) - start"); //$NON-NLS-1$
		}

		final LangTrans lt = new dao.impl.LangTransImpl();

		final ArrayList<LangTransact> returnArrayList =(ArrayList<LangTransact>) lt.selectUserLanguages(iduser);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getUserLanguages(int) - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

	/* (non-Javadoc)
	 * @see service.impl.LangTransServ#updateLangTransaction(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public boolean updateLangTransaction(HttpServletRequest request, int iduser)
			throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateLangTransaction(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}
		// TODO Auto-generated method stub
		final LangTrans it = new dao.impl.LangTransImpl();
		final ArrayList<LangTransact> dblanguages =(ArrayList<LangTransact>) it.selectUserLanguages(iduser);
		final ArrayList<LangTransact> newlangarr = LangTransImpl.setParams(request, iduser);
		final List<LangTransact> updated = new ArrayList<>();
		int rowsAffected = 0;
		final LangTrans langtrans = new dao.impl.LangTransImpl();
		int flag = 0;
		for (final LangTransact newlang : newlangarr) {
			for (final LangTransact dblang : dblanguages) {
				if (dblang.getIdlangmaster() == newlang.getIdlangmaster()) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("updateLangTransaction(HttpServletRequest, int) - {}"+ "update id" + newlang); //$NON-NLS-1$ //$NON-NLS-2$
					}
					dblanguages.remove(dblang);
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				updated.add(newlang);
			}
			flag = 0;
		}
		if (dblanguages.size() > 0) {
			rowsAffected = langtrans.insertLangTrans(dblanguages, "delete");
		}
		if (updated.size() > 0) {
			rowsAffected += langtrans.insertLangTrans(newlangarr, "insert");
		}
		if (rowsAffected > 0) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("updateLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("updateLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}
	}

}
