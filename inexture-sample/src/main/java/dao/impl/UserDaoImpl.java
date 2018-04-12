package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.UserDao;
import model.User;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDaoImpl.
 */
public class UserDaoImpl implements UserDao {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.user.UserDao#insert(model.User, java.lang.String)
	 */
	@Override
	public boolean insert(final User user, final String operation) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("insert(User, String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ArrayList<Object> arr = new ArrayList<>();
		arr.add(user.getFirstname());
		arr.add(user.getLastname());
		arr.add(user.getEmail());
		arr.add(user.getPassword());
		arr.add(user.getMobile());
		arr.add(user.getGender());
		arr.add(user.getDob());
		arr.add(user.getRole());
		arr.add(user.getTech());
		if ("insert".equals(operation)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("insert(User, String) - end"); //$NON-NLS-1$
			}
			return DbUtil.dbOperationInsert(INSERT, arr);
		} else if ("delete".equals(operation)) {
			arr.clear();
			arr.add(user.getIduser());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("insert(User, String) - end"); //$NON-NLS-1$
			}
			return DbUtil.dbOperationInsert(DELETEUSER, arr);
		} else {
			arr.remove(arr.size() - 2);
			arr.remove(2);
			arr.remove(2);
			arr.add(user.getIduser());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("insert(User, String) - end"); //$NON-NLS-1$
			}
			return DbUtil.dbOperationInsert(UPDATE, arr);
		}


	}


	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User selectUser(final String email,final  String pass) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectUser(String, String) - start"); //$NON-NLS-1$
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectUser(String, String) - end"); //$NON-NLS-1$
		}
		return UserDaoImpl.setUser(DbUtil.dbOperationSelect(SELECTUSERFORLOGIN, email, pass));
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectAllUser(int)
	 */
	@Override
	public ArrayList<User> selectAllUser(final int role) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectAllUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		try(final ResultSet resultSet = DbUtil.dbOperationSelect(SELECTALLUSER, role)){

			final ArrayList<User> users = new ArrayList<>();

			while (resultSet.next()) {
				final User user = new User();
				user.setIduser(resultSet.getInt(1));
				user.setFirstname(resultSet.getString(2));
				user.setLastname(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));
				user.setMobile(resultSet.getLong(6));
				user.setGender(resultSet.getInt(7));
				final Date date = new Date(resultSet.getDate(8).getTime());
				user.setDob(date);
				user.setRole(resultSet.getInt(9));
				user.setTech(resultSet.getInt(10));
				users.add(user);
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("selectAllUser(int) - end"); //$NON-NLS-1$
			}
			return users;
		}
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectUser(int)
	 */
	@Override
	public User selectUser(final int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectUser(int) - end"); //$NON-NLS-1$
		}
		return UserDaoImpl.setUser(DbUtil.dbOperationSelect(SELECTUSER, iduser));
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectUser(java.lang.String)
	 */
	@Override
	public User selectUser(final String email) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectUser(String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("selectUser(String) - end"); //$NON-NLS-1$
		}
		return UserDaoImpl.setUser(DbUtil.dbOperationSelect(SELECTUSERFRMEMAIL, email));
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#updatePassword(model.User)
	 */
	@Override
	public boolean updatePassword(final User user) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updatePassword(User) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ArrayList<Object> arr = new ArrayList<>();
		arr.add(user.getPassword());
		arr.add(user.getIduser());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updatePassword(User) - end"); //$NON-NLS-1$
		}
		return DbUtil.dbOperationInsert(UPDATEPASS, arr);
	}

	/**
	 * Sets the user.
	 *
	 * @param resultSet the rs
	 * @return the user
	 * @throws SQLException the SQL exception
	 */
	public static User setUser(final ResultSet resultSet) throws SQLException {
		User user = null;
		while (resultSet.next()) {
			user = new User();
			user.setIduser(resultSet.getInt(1));
			user.setFirstname(resultSet.getString(2));
			user.setLastname(resultSet.getString(3));
			user.setEmail(resultSet.getString(4));
			user.setPassword(resultSet.getString(5));
			user.setMobile(resultSet.getLong(6));
			user.setGender(resultSet.getInt(7));
			final Date date = new Date(resultSet.getDate(8).getTime());
			user.setDob(date);
			user.setRole(resultSet.getInt(9));
			user.setTech(resultSet.getInt(10));

		}
		return user;
	}
}
