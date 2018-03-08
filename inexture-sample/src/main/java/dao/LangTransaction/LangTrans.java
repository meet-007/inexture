package dao.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;

import model.LangTransact;

public interface LangTrans {
	 final static String INSERT = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";
	int InsertLangTrans (LangTransact[] lt) throws ClassNotFoundException, SQLException, IOException;
}
