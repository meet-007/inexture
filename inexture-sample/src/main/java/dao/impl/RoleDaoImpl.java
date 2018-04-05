package dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.interfaces.RoleDao;
import model.Role;
import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleDaoImpl.
 */
public class RoleDaoImpl implements RoleDao {

	/** Logger for this class. */
	private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class.getName());

	/* (non-Javadoc)
	 * @see dao.Role.RoleDao#getRole(int)
	 */
	@Override
	public Role getRole(int id) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getRole(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		final ResultSet rs = DbUtil.dbOperationSelect(SELECTROLE, id);
		final Role role = new Role();
		while (rs.next()) {
			role.setIdrole(rs.getInt(1));
			role.setRole(rs.getString(2));
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getRole(int) - end"); //$NON-NLS-1$
		}
		return role;
	}

}
