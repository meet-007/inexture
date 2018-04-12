package dao.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.LangTransact;

// TODO: Auto-generated Javadoc
/**
 * The Interface LangTrans.
 */
public interface LangTrans {

	/** The Constant INSERT. */
	String INSERT = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";

	/** The Constant SELECT. */
	String SELECT = "select * from lang_transaction where iduser = ?;";

	/** The Constant UPDATE. */
	String UPDATE = "insert into lang_transaction (idlangmaster,iduser) values (?,?);";

	/** The Constant DELETE. */
	String DELETE = "delete from lang_transaction where idlangmaster =? and  iduser = ?;";

	/**
	 * Insert lang trans.
	 *
	 * @param langTransList the it
	 * @param operation the operation
	 * @return the int
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	int insertLangTrans(List<LangTransact> langTransList, String operation)
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
	List<LangTransact> selectUserLanguages(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
