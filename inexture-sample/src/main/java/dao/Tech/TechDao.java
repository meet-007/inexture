package dao.Tech;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TechMaster;

public interface TechDao {
	final static String select = "select * from tech_master;";

	ArrayList selectTech() throws ClassNotFoundException, SQLException, IOException;
}
