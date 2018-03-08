package dao.LangTransaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LangTransact;
import util.DbUtil;

public class LangTransImpl implements LangTrans{

	public int InsertLangTrans(LangTransact[] it) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ArrayList arr = new ArrayList();
		int count = 0;
		for(int i=0;i<it.length;i++)
		{
			arr.add(0,it[i].getIdlangmaster());
			arr.add(1,it[i].getIduser());
			if(!DbUtil.dbOperationInsert(INSERT,arr  )) {
				count ++;
			}
			arr.remove(0);
			arr.remove(0);
		}
		
		
		
		return count;
	}

}
