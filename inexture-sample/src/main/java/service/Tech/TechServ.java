package service.Tech;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TechMaster;

public interface TechServ {
	
	ArrayList getTech() throws ClassNotFoundException, SQLException, IOException ;
}
