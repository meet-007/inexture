package util;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.UpdateServ;
import service.impl.AddressServiceImpl;
import service.impl.ImageServiceImpl;
import service.impl.LangTransImpl;
import service.impl.UserServiceImp;

// TODO: Auto-generated Javadoc
/**
 * The Class ResetRegForm.
 */
public final class ResetRegForm {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(UpdateServ.class.getName());

	/**
	 * Instantiates a new reset reg form.
	 */
	private ResetRegForm(){

	}

	/**
	 * Reset form.
	 *
	 * @param request the request
	 */
	public	static void resetForm(final HttpServletRequest request) {
		try {
			request.setAttribute("user", UserServiceImp.setParams(request));
		} catch (final ParseException e2) {
			LOGGER.error("doPost(HttpServletRequest, HttpServletResponse)", e2); //$NON-NLS-1$
		}finally {
			try {
				request.setAttribute("languages", LangTransImpl.setParams(request, -1));
			}catch (final NumberFormatException e) {
				LOGGER.error("doPost(HttpServletRequest, HttpServletResponse)", e);
			}finally {
				try {
					request.setAttribute("languages", LangTransImpl.setParams(request, -1));
				}catch (final NumberFormatException e) {
					LOGGER.error("doPost(HttpServletRequest, HttpServletResponse)", e);
				}finally {
					try {
						request.setAttribute("addrslist", AddressServiceImpl.setParams(request, -1));
					}catch (final NumberFormatException e) {
						LOGGER.error("doPost(HttpServletRequest, HttpServletResponse)", e);
					}finally {
						try {
							request.setAttribute("imglist", ImageServiceImpl.setParams(request, -1));
						} catch (IOException | ParseException | ServletException|NumberFormatException  e) {
							LOGGER.error("doPost(HttpServletRequest, HttpServletResponse)", e); //$NON-NLS-1$
						}
					}
				}
			}
		}
	}
}
