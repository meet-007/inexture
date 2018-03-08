package service.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface LangTransServ {
	boolean addLangTransaction (HttpServletRequest request,int userid) throws ClassNotFoundException, SQLException, IOException;
}
