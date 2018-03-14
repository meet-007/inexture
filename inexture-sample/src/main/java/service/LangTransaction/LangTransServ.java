package service.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.LangTransact;

public interface LangTransServ {
	boolean addLangTransaction (HttpServletRequest request,int userid) throws ClassNotFoundException, SQLException, IOException;
	ArrayList<LangTransact> getUserLanguages(int iduser) throws ClassNotFoundException, SQLException, IOException;
	boolean updateLangTransaction(HttpServletRequest request,int userid) throws ClassNotFoundException,SQLException,IOException;
}
