package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LangTransact;

// TODO: Auto-generated Javadoc
/**
 * The Interface LangTrans.
 */
public interface LangTrans {

	/** The Constant INSERT. */
	final static String INSERT = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";

	/** The Constant SELECT. */
	final static String SELECT = "select * from lang_transaction where iduser = ?;";

	/** The Constant UPDATE. */
	final static String UPDATE = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";

	/** The Constant DELETE. */
	final static String DELETE = "delete from lang_transaction where idlangmaster =? and  iduser = ?;";

	/**
	 * Insert lang trans.
	 *
	 * @param it the it
	 * @param operation the operation
	 * @return the int
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	int insertLangTrans(ArrayList<LangTransact> it, String operation)
			throws ClassNotFoundException, SQLException, IOException;

	/**
	 * Select user languages.
	 *
	 * @param iduser the iduser
	 * @return the array list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	ArrayList<LangTransact> selectUserLanguages(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
