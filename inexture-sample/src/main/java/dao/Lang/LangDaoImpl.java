package dao.Lang;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.LangMaster;
import model.TechMaster;
import util.DbUtil;

public class LangDaoImpl implements LangDao {

	public ArrayList selectLang() throws ClassNotFoundException, SQLException, IOException {
		Connection con = DbUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(select);
		ArrayList languages = new ArrayList();
		while (rs.next()) {
			LangMaster lm = new LangMaster();
			lm.setIdlang(rs.getInt(1));
			lm.setLang(rs.getString(2));
			languages.add(lm);
		}
		return languages;
	}

}
