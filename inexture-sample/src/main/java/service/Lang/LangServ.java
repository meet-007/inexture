package service.Lang;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LangServ {
	ArrayList getLang() throws ClassNotFoundException, SQLException, IOException;
}
