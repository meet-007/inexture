package dao.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LangTransact;

public interface LangTrans {
	 final static String INSERT = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";
	 final static String SELECT = "select * from lang_transaction where iduser = ?;";
	 final static String UPDATE = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";
	 final static String DELETE = "delete from lang_transaction where idlangmaster =? and  iduser = ?;";
	int InsertLangTrans (ArrayList<LangTransact> it,String operation) throws ClassNotFoundException, SQLException, IOException;
	ArrayList<LangTransact> selectUserLanguages(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
