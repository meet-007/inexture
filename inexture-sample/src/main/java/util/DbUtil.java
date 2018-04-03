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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DbUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(DbUtil.class.getName());

	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getConnection() - start"); //$NON-NLS-1$
		}
		Properties prop = DbUtil.getProperties();
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String uname = prop.getProperty("uname");
		String pass = prop.getProperty("pass");
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uname, pass);
		if (logger.isDebugEnabled()) {
			logger.debug("getConnection() - end"); //$NON-NLS-1$
		}
		return con;
	}

	public static Properties getProperties() throws FileNotFoundException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("getProperties() - start"); //$NON-NLS-1$
		}
		Properties prop = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fis = classLoader.getResourceAsStream("db.properties");
		prop.load(fis);

		if (logger.isDebugEnabled()) {
			logger.debug("getProperties() - end"); //$NON-NLS-1$
		}
		return prop;
	}

	public static boolean dbOperationInsert(String query, ArrayList<Object> param)
			throws SQLException, ClassNotFoundException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("dbOperationInsert(String, ArrayList) - start"); //$NON-NLS-1$
		}
		Connection con = DbUtil.getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		Iterator<Object> it = param.iterator();
		int index = 1;
		while (it.hasNext()) {
			Object ob = it.next();
			if (ob instanceof InputStream) {
				if (ob != null) {
					pst.setBlob(index, (InputStream) ob);
				}
			} else {
				pst.setObject(index, ob);
			}
			index++;
		}
		boolean result = pst.execute();
		con.close();

		if (logger.isDebugEnabled()) {
			logger.debug("dbOperationInsert(String, ArrayList) - end"); //$NON-NLS-1$
		}
		return result;
	}

	public static ResultSet dbOperationSelect(String query, Object... params)
			throws SQLException, ClassNotFoundException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("dbOperationSelect(String, Object) - start"); //$NON-NLS-1$
		}

		Connection con = DbUtil.getConnection();
		PreparedStatement st = con.prepareStatement(query);
		if (params != null) {
			int index = 1;
			for (Object param : params) {
				st.setObject(index, param);
				index++;
			}
		}
		ResultSet rs = st.executeQuery();

		if (logger.isDebugEnabled()) {
			logger.debug("dbOperationSelect(String, Object) - end"); //$NON-NLS-1$
		}
		return rs;
	}

}
