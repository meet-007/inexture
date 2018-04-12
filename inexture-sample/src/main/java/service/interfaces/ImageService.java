package service.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import model.UserImages;

// TODO: Auto-generated Javadoc
/**
 * The Interface ImageService.
 */
public interface ImageService {

	/**
	 * Save image.
	 *
	 * @param request the request
	 * @param iduser the iduser
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 */
	boolean saveImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException;

	/**
	 * Gets the user images.
	 *
	 * @param iduser the iduser
	 * @return the user images
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 */
	List<UserImages> getUserImages(int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException;

	/**
	 * Update image.
	 *
	 * @param request the request
	 * @param iduser the iduser
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 */
	boolean updateImage(HttpServletRequest request, int iduser)
			throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException;
}
