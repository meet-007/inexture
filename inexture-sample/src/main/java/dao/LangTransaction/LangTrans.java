package dao.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LangTransact;

public interface LangTrans {
	 final static String INSERT = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";
	 final static String SELECT = "select * from lang_transaction where iduser = ?;";

	int InsertLangTrans (LangTransact[] lt) throws ClassNotFoundException, SQLException, IOException;
	ArrayList<LangTransact> selectUserLanguages(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
