package dao.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserImages;

public interface ImageDao {
	final static String INSERT = "insert into user_images (iduser,image) values (?,?);";
	final static String SELECT = "select * from user_images where iduser = ?; ";
	final static String DELETE = "delete from user_images where iduser_images = ?; ";
	int insertImage(ArrayList<UserImages> uimg,String operation) throws ClassNotFoundException, SQLException, IOException;
	ArrayList selectImages(int iduser) throws ClassNotFoundException, SQLException, IOException;
}
