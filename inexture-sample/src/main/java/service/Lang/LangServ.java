package service.Lang;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LangServ {
	ArrayList getLang() throws ClassNotFoundException, SQLException, IOException;
}
