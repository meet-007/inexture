package service.LangTransaction;




import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.LangTransaction.LangTrans;
import model.LangTransact;

public class LangTransImpl implements LangTransServ {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LangTransImpl.class.getName());

	public static ArrayList<LangTransact> setParams(HttpServletRequest request, int userid) {
		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		String[] lang = request.getParameterValues("lang");
		int noOfSelectedLang = lang.length;
		ArrayList<LangTransact> it = new ArrayList<LangTransact>();
		for (String element : lang) {
			LangTransact lt = new LangTransact();
			lt.setIdlangmaster(Integer.parseInt(element));
			lt.setIduser(userid);
			it.add(lt);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("setParams(HttpServletRequest, int) - end"); //$NON-NLS-1$
		}
		return it;
	}

	public boolean addLangTransaction(HttpServletRequest request, int userid)
			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("addLangTransaction(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		ArrayList<LangTransact> it = LangTransImpl.setParams(request, userid);

		LangTrans langtrans = new dao.LangTransaction.LangTransImpl();
		int rowsAffected = langtrans.InsertLangTrans(it, "insert");
		if (rowsAffected > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("addLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("addLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}
	}

	public ArrayList<LangTransact> getUserLanguages(int iduser)
			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserLanguages(int) - start"); //$NON-NLS-1$
		}

		LangTrans lt = new dao.LangTransaction.LangTransImpl();

		ArrayList<LangTransact> returnArrayList = lt.selectUserLanguages(iduser);
		if (logger.isDebugEnabled()) {
			logger.debug("getUserLanguages(int) - end"); //$NON-NLS-1$
		}
		return returnArrayList;
	}

	public boolean updateLangTransaction(HttpServletRequest request, int iduser)
			throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("updateLangTransaction(HttpServletRequest, int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		LangTrans it = new dao.LangTransaction.LangTransImpl();
		ArrayList<LangTransact> dblanguages = it.selectUserLanguages(iduser);
		String newlanguages[] = request.getParameterValues("lang");
		ArrayList<LangTransact> newlangarr = LangTransImpl.setParams(request, iduser);

		// List<LangTransact> removed = new ArrayList<LangTransact>(dblanguages);
		// removed.removeAll(Arrays.asList(newlangarr));
		//
		// List<LangTransact> same = new ArrayList<LangTransact>(dblanguages);
		// same.retainAll(Arrays.asList(newlangarr));
		//
		// List<LangTransact> added = Arrays.asList(newlangarr);
		// added.removeAll(dblanguages);
		//
		// LangTransact[] addedarr = (LangTransact[])added.toArray();
		// LangTransact samearr[] = (LangTransact[])same.toArray();
		// LangTransact[] removearr = (LangTransact[])removed.toArray();
		// int rowsAffected = 0;
		// LangTrans langtrans = new dao.LangTransaction.LangTransImpl();
		// if(removed.size()>0) {
		// rowsAffected = langtrans.InsertLangTrans(removearr,"delete");
		// }
		// if(added.size()>0) {
		// rowsAffected += langtrans.InsertLangTrans(addedarr,"update");
		// }
		// if(rowsAffected>0) {
		// return true;
		// }else {
		// return false;
		// }

		// int flag=0;
		// for(LangTransact newlang : newlangarr) {
		// for(LangTransact dblang : dblanguages) {
		// if(dblang.getIdlangmaster()==newlang.getIdlangmaster()) {
		// System.out.println("update id"+newlang);
		// dblanguages.remove(dblang);
		// flag=1;
		// break;
		// }
		// }
		// if(flag==0) {
		// System.out.println("insert id"+newlang);
		// }
		// flag=0;
		// }
		// if(dblanguages.size()>0) {
		// for(LangTransact dblang : dblanguages) {
		// System.out.println("delete id"+dblang.getIdlangmaster());
		// }
		// }
		List<LangTransact> updated = new ArrayList<LangTransact>();
		int rowsAffected = 0;
		LangTrans langtrans = new dao.LangTransaction.LangTransImpl();
		int flag = 0;
		for (LangTransact newlang : newlangarr) {
			for (LangTransact dblang : dblanguages) {
				if (dblang.getIdlangmaster() == newlang.getIdlangmaster()) {
					System.out.println("update id" + newlang);
					dblanguages.remove(dblang);
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				updated.add(newlang);
				// rowsAffected += langtrans.InsertLangTrans(newlang,"update");
			}
			flag = 0;
		}
		if (dblanguages.size() > 0) {
			rowsAffected = langtrans.InsertLangTrans(dblanguages, "delete");
			// for(LangTransact dblang : dblanguages) {
			// System.out.println("delete id"+dblang.getIdlangmaster());
			// }
		}
		if (updated.size() > 0) {
			rowsAffected += langtrans.InsertLangTrans(newlangarr, "insert");
		}
		if (rowsAffected > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("updateLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("updateLangTransaction(HttpServletRequest, int) - end"); //$NON-NLS-1$
			}
			return false;
		}
	}

}
