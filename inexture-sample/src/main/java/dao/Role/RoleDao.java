package dao.Role;

import java.io.IOException;
import java.sql.SQLException;

import model.Role;

public interface RoleDao {
	final static String SELECTROLE = "select * from role_master where idrole_master = ?";
	Role getRole(int id) throws ClassNotFoundException, SQLException, IOException;
}
