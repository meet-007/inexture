package dao.Lang;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LangDao {
	final static String select = "select * from language_master;";
	ArrayList selectLang () throws ClassNotFoundException, SQLException, IOException;
}
