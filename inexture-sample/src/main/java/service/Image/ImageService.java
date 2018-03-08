package service.Image;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.User;

public interface ImageService {
boolean SaveImage(HttpServletRequest request,int iduser) throws IOException, ServletException, ClassNotFoundException, SQLException;
}
