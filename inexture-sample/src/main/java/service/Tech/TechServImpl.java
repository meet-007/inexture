package service.Tech;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.Tech.TechDao;
import dao.Tech.TechDaoImpl;

public class TechServImpl implements TechServ{
	
	
	public ArrayList getTech() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		TechDao td =new  TechDaoImpl();
		ArrayList technologies = td.selectTech(); 
		
		return technologies;
	}

}
