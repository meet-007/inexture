package service.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.Lang.LangDaoImpl;
import dao.LangTransaction.LangTrans;
import model.LangTransact;

public class LangTransImpl implements LangTransServ {

	public boolean addLangTransaction(HttpServletRequest request, int userid) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String[] lang = request.getParameterValues("lang");
		int noOfSelectedLang = lang.length;
		LangTransact it[] = new LangTransact[noOfSelectedLang]; 
		for(int i=0; i<lang.length ; i++) {
			it[i] = new LangTransact();
			it[i].setIdlangmaster(Integer.parseInt(lang[i]));
			it[i].setIduser(userid);
		}
		LangTrans langtrans = new dao.LangTransaction.LangTransImpl();
		int rowsAffected = langtrans.InsertLangTrans(it);
		if(rowsAffected>0) {
			return true;
		}else {
		return false;
		}
	}

	public ArrayList<LangTransact> getUserLanguages(int iduser)
			throws ClassNotFoundException, SQLException, IOException {
		LangTrans lt = new dao.LangTransaction.LangTransImpl();
		
		return lt.selectUserLanguages(iduser);
	}

	public boolean updateLangTransaction(HttpServletRequest request, int iduser) throws ClassNotFoundException,SQLException,IOException {
		// TODO Auto-generated method stub
		LangTrans lt = new dao.LangTransaction.LangTransImpl();
		ArrayList<LangTransact> dblanguages = lt.selectUserLanguages(iduser);
		String newlanguages[] =  request.getParameterValues("lang");
		int flag=0;
			for(String newlang : newlanguages) {
				for(LangTransact dblang : dblanguages) {
					if(dblang.getIdlangmaster()==Integer.parseInt(newlang)) {
						System.out.println("update id"+newlang);
						dblanguages.remove(dblang);
						flag=1;
						break;
					}
				}
				if(flag==0) {
				System.out.println("insert id"+newlang);
				}
				flag=0;
			} 
			if(dblanguages.size()>0) {
			for(LangTransact dblang : dblanguages) {
				System.out.println("delete id"+dblang.getIdlangmaster());
			}
			}
		return false;
	}

}
