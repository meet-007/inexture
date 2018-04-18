package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class DbUtil.
 */
@SuppressWarnings("PMD")
public final class DbUtil {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(DbUtil.class.getName());

	/**
	 * Instantiates a new db util.
	 */
	private DbUtil() {}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getConnection() - start"); //$NON-NLS-1$
		}
		final Properties prop = DbUtil.getProperties("db.properties");
		final String driver = prop.getProperty("driver");
		final String url = prop.getProperty("url");
		final String uname = prop.getProperty("uname");
		final String pass = prop.getProperty("pass");
		Class.forName(driver);
		final Connection con = DriverManager.getConnection(url, uname, pass);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getConnection() - end"); //$NON-NLS-1$
		}
		return con;
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Properties getProperties(final String pname) throws FileNotFoundException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getProperties() - start"); //$NON-NLS-1$
		}
		final Properties prop = new Properties();
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final InputStream fis = classLoader.getResourceAsStream(pname);
		prop.load(fis);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getProperties() - end"); //$NON-NLS-1$
		}
		return prop;
	}

	/**
	 * Db operation insert.
	 *
	 * @param query the query
	 * @param param the param
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean dbOperationInsert(final String query, final List<Object> param)
			throws SQLException, ClassNotFoundException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("dbOperationInsert(String, ArrayList) - start"); //$NON-NLS-1$
		}
		boolean result = true;
		try(Connection con = DbUtil.getConnection()){
			try(PreparedStatement pst = con.prepareStatement(query)){
				final Iterator<Object> iterator = param.iterator();
				int index = 1;
				while (iterator.hasNext()) {
					final Object object = iterator.next();
					if (object instanceof InputStream) {
						pst.setBlob(index, (InputStream) object);
					} else {
						pst.setObject(index, object);
					}
					index++;
				}
				result = pst.execute();
				pst.close();
				con.close();
			}
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("dbOperationInsert(String, ArrayList) - end"); //$NON-NLS-1$
		}
		return result;
	}

	/**
	 * Db operation select.
	 *
	 * @param query the query
	 * @param params the params
	 * @return the result set
	 * @throws SQLException the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ResultSet dbOperationSelect(final String query, final Object... params)
			throws SQLException, ClassNotFoundException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("dbOperationSelect(String, Object) - start"); //$NON-NLS-1$
		}

		final Connection con = DbUtil.getConnection();
		final PreparedStatement preparedStatement = con.prepareStatement(query);

		if (params != null) {
			int index = 1;
			for (final Object param : params) {
				preparedStatement.setObject(index, param);
				index++;
			}
		}
		final ResultSet resultSet = preparedStatement.executeQuery();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("dbOperationSelect(String, Object) - end"); //$NON-NLS-1$
		}
		return resultSet;


	}

}
