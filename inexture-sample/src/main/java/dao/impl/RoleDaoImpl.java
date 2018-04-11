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
	public Role getRole(final int iduser) throws ClassNotFoundException, SQLException, IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getRole(int) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		try(final ResultSet resultSet = DbUtil.dbOperationSelect(SELECTROLE, iduser)){
			final Role role = new Role();
			while (resultSet.next()) {
				role.setIdrole(resultSet.getInt(1));
				role.setRole(resultSet.getString(2));
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("getRole(int) - end"); //$NON-NLS-1$
			}
			return role;
		}
	}

}
