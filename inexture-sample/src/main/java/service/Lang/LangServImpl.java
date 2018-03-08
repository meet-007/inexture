package service.Lang;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.Lang.LangDao;
import dao.Lang.LangDaoImpl;


public class LangServImpl implements LangServ{

	public ArrayList getLang() throws ClassNotFoundException, SQLException, IOException {
		LangDao lang = new LangDaoImpl();
		return lang.selectLang();
	}

}		
