package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class DbUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Properties prop = DbUtil.getProperties();
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String uname = prop.getProperty("uname");
		String pass = prop.getProperty("pass");
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uname, pass);
		return con;
	}

	public static Properties getProperties() throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fis = classLoader.getResourceAsStream("db.properties");
		prop.load(fis);

		return prop;
	}

	public static boolean dbOperationInsert(String query, ArrayList param)
			throws SQLException, ClassNotFoundException, IOException {

		Connection con = DbUtil.getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		Iterator it = param.iterator();
		int index = 1;
		while (it.hasNext()) {
			Object ob = it.next();

			// String typename = ob.getClass().getName();
			if(ob instanceof InputStream)
			{
				pst.setBinaryStream(index, (InputStream)ob);
			}else {
				pst.setObject(index, ob);
			}
			// pst.setObject(index, ob, );
			// if(ob instanceof String) {
			// pst.setString(index, (String)ob);
			// }else if(ob instanceof Integer) {
			// pst.setInt(index, (Integer)ob);
			// }else if(ob instanceof Long) {
			// pst.setLong(index, (Long)ob);
			// }
			index++;
		}
		boolean result = pst.execute();
		con.close();
		return result;
	}

	public static ResultSet dbOperationSelect(String query, Object... params)
			throws SQLException, ClassNotFoundException, IOException {
		Connection con = DbUtil.getConnection();
		PreparedStatement st = con.prepareStatement(query);
		if(params != null) {
			int index=1;
			for(Object param : params) {
				st.setObject(index,param);
				index++;
			}
		}
		ResultSet rs = st.executeQuery();
		return rs;
	}

}
