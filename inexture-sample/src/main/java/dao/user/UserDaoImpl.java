package dao.user;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.User;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDaoImpl.
 */
public class UserDaoImpl implements UserDao {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.user.UserDao#insert(model.User, java.lang.String)
	 */
	public boolean insert(User u, String operation) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("insert(User, String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ArrayList<Object> arr = new ArrayList<Object>();
		arr.add(u.getFirstname());
		arr.add(u.getLastname());
		arr.add(u.getEmail());
		arr.add(u.getPassword());
		arr.add(u.getMobile());
		arr.add(u.getGender());
		arr.add(u.getDob());
		arr.add(u.getRole());
		arr.add(u.getTech());
		if (operation.equals("insert")) {
			if (logger.isDebugEnabled()) {
				logger.debug("insert(User, String) - end"); //$NON-NLS-1$
			}
			return DbUtil.dbOperationInsert(INSERT, arr);
		} else if (operation.equals("delete")) {
			arr.removeAll(arr);
			arr.add(u.getIduser());
			if (logger.isDebugEnabled()) {
				logger.debug("insert(User, String) - end"); //$NON-NLS-1$
			}
			return DbUtil.dbOperationInsert(DELETEUSER, arr);
		} else {
			arr.remove(arr.size() - 2);
			arr.remove(2);
			arr.remove(2);
			arr.add(u.getIduser());
			if (logger.isDebugEnabled()) {
				logger.debug("insert(User, String) - end"); //$NON-NLS-1$
			}
			return DbUtil.dbOperationInsert(UPDATE, arr);
		}


	}


	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectUser(java.lang.String, java.lang.String)
	 */
	public User selectUser(String email, String pass) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectUser(String, String) - start"); //$NON-NLS-1$
		}

		if (logger.isDebugEnabled()) {
			logger.debug("selectUser(String, String) - end"); //$NON-NLS-1$
		}
		return UserDaoImpl.setUser(DbUtil.dbOperationSelect(SELECTUSERFORLOGIN, email, pass));
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectAllUser(int)
	 */
	public ArrayList<User> selectAllUser(int role) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectAllUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ResultSet rs = DbUtil.dbOperationSelect(SELECTALLUSER, role);
		ArrayList<User> users = new ArrayList<User>();
		User u = null;
		while (rs.next()) {
			u = new User();
			u.setIduser(rs.getInt(1));
			u.setFirstname(rs.getString(2));
			u.setLastname(rs.getString(3));
			u.setEmail(rs.getString(4));
			u.setPassword(rs.getString(5));
			u.setMobile(rs.getLong(6));
			u.setGender(rs.getInt(7));
			Date d = new Date(rs.getDate(8).getTime());
			u.setDob(d);
			u.setRole(rs.getInt(9));
			u.setTech(rs.getInt(10));
			users.add(u);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("selectAllUser(int) - end"); //$NON-NLS-1$
		}
		return users;
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectUser(int)
	 */
	public User selectUser(int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectUser(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		if (logger.isDebugEnabled()) {
			logger.debug("selectUser(int) - end"); //$NON-NLS-1$
		}
		return UserDaoImpl.setUser(DbUtil.dbOperationSelect(SELECTUSER, iduser));
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#selectUser(java.lang.String)
	 */
	public User selectUser(String email) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectUser(String) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub

		if (logger.isDebugEnabled()) {
			logger.debug("selectUser(String) - end"); //$NON-NLS-1$
		}
		return UserDaoImpl.setUser(DbUtil.dbOperationSelect(SELECTUSERFRMEMAIL, email));
	}

	/* (non-Javadoc)
	 * @see dao.user.UserDao#updatePassword(model.User)
	 */
	public boolean updatePassword(User u) throws ClassNotFoundException, SQLException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("updatePassword(User) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ArrayList<Object> arr = new ArrayList<Object>();
		arr.add(u.getPassword());
		arr.add(u.getIduser());

		if (logger.isDebugEnabled()) {
			logger.debug("updatePassword(User) - end"); //$NON-NLS-1$
		}
		return DbUtil.dbOperationInsert(UPDATEPASS, arr);
	}

	/**
	 * Sets the user.
	 *
	 * @param rs the rs
	 * @return the user
	 * @throws SQLException the SQL exception
	 */
	public static User setUser(ResultSet rs) throws SQLException {
		User u = null;
		while (rs.next()) {
			u = new User();
			u.setIduser(rs.getInt(1));
			u.setFirstname(rs.getString(2));
			u.setLastname(rs.getString(3));
			u.setEmail(rs.getString(4));
			u.setPassword(rs.getString(5));
			u.setMobile(rs.getLong(6));
			u.setGender(rs.getInt(7));
			Date d = new Date(rs.getDate(8).getTime());
			u.setDob(d);
			u.setRole(rs.getInt(9));
			u.setTech(rs.getInt(10));

		}
		return u;
	}
}
