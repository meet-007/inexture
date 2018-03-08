package dao.Role;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Role;
import util.DbUtil;

public class RoleDaoImpl implements RoleDao{

	public Role getRole(int id) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs = DbUtil.dbOperationSelect(SELECTROLE, id);
		Role role = new Role();
		while(rs.next()) {
			role.setIdrole(rs.getInt(1));
			role.setRole(rs.getString(2));
		}
		return role;
	}
	
}
