package dao.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserImages;
import util.DbUtil;

public class ImageDaoImpl implements ImageDao {

	public int insertImage(UserImages[] uimg) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ArrayList arr = new ArrayList();
		int count = 0;
		for(int i=0;i<uimg.length;i++)
		{
			arr.add(0,uimg[i].getIduser());
			arr.add(1,uimg[i].getImage());
			if(!DbUtil.dbOperationInsert(INSERT,arr )) {
				count ++;
			}
			arr.remove(0);
			arr.remove(0);
		}
		
		return count;
	}

	public ArrayList selectImages(int iduser) {
		// TODO Auto-generated method stub
//		Integer id = (Integer)iduser;
//		DbUtil.dbOperationInsert(SELECT,id.toString() )
		return null;
	}

}
