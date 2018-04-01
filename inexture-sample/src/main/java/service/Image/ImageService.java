package service.Image;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.User;
import model.UserImages;

public interface ImageService {
	boolean SaveImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException;

	ArrayList<UserImages> getUserImages(int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException;

	boolean UpdateImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException;
}
