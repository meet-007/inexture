package dao.Role;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;

import model.Role;

public interface RoleDao {
	final static String SELECTROLE = "select * from role_master where idrole_master = ?";

	Role getRole(int id) throws ClassNotFoundException, SQLException, IOException;
}
