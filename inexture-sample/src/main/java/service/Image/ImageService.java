package service.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.User;
import model.UserImages;

public interface ImageService {
boolean SaveImage(HttpServletRequest request,int iduser) throws IOException, ServletException, ClassNotFoundException, SQLException;
ArrayList<UserImages> getUserImages(int iduser)throws IOException, ServletException, ClassNotFoundException, SQLException;
}
