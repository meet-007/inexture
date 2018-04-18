package validations;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
// TODO: Auto-generated Javadoc

import util.DbUtil;

/**
 * The Class RegistrationValidation.
 */
public class RegistrationValidation {

	/**
	 * Validate.
	 *
	 * @param req
	 *            the req
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
	public String validate(final HttpServletRequest req) throws IOException, ServletException {
		/* getting parameters from request object */
		final String fname = req.getParameter("fname");
		final String lname = req.getParameter("lname");
		String email = null;
		String pass = null;
		if (req.getParameter("email") != null && req.getParameter("pass") != null) {
			email = req.getParameter("email");
			pass = req.getParameter("pass");
		}
		final String mobile = req.getParameter("mobile");
		final String dob = req.getParameter("dob");
		final String gender = req.getParameter("gender");
		final String tech = req.getParameter("tech");
		final String lang[] = req.getParameterValues("lang");
		// String role = req.getParameter("role");
		final Properties prop = DbUtil.getProperties("validations.properties");
		StringBuilder error = new StringBuilder();
		if (fname == null || "".equals(fname)) {
			error.append(prop.getProperty("firstname.null.errormsg"));
			error.append("<br/>");
		} else if (fname.length() > 100) {
			error.append( prop.getProperty("firstname.length.errormsg"));
			error.append("<br/>");
		}
		if (lname == null ||"".equals(lname)) {
			error.append( prop.getProperty("lastname.null.errormsg"));
			error.append("<br/>");
		} else if (lname.length() > 100) {
			error.append( prop.getProperty("lastname.length.errormsg"));
			error.append("<br/>");
		}
		if(req.getServletPath().equals("/RegUser")) {
			if (email == null ||"".equals(email)) {
				error.append( prop.getProperty("email.null.errormsg"));
				error.append("<br/>");

			} else if (email.length() > 80) {
				error.append( prop.getProperty("email.length.errormsg"));
				error.append("<br/>");
			} else if (!email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+")) {
				error.append( prop.getProperty("email.format.errormsg"));
				error.append("<br/>");
			}


			if (pass == null ||"".equals(pass)) {
				error.append( prop.getProperty("password.null.errormsg"));
				error.append("<br/>");
			} else if (pass.length() < 6 || pass.length() > 15) {
				error.append( prop.getProperty("password.length.errormsg"));
				error.append("<br/>");
			}
		}
		if (mobile == null || "".equals(mobile)) {
			error.append(prop.getProperty("mobile.null.errormsg"));
			error.append("<br/>");
		} else if (mobile.length() > 10) {
			error.append( prop.getProperty("mobile.length.errormsg"));
			error.append("<br/>");
		}
		if (dob == null ||"".equals(dob)) {
			error.append(prop.getProperty("dob.null.errormsg"));
			error.append("<br/>");
		} else if (!dob.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])")) {
			error.append( prop.getProperty("dob.format.errormsg"));
			error.append("<br/>");
		}
		if (gender == null ||"".equals(gender)) {
			error.append( prop.getProperty("gender.null.errormsg"));
			error.append("<br/>");
		}
		if (tech == null ||"".equals(tech)) {
			error.append( prop.getProperty("tech.null.errormsg"));
			error.append("<br/>");
		}
		// language validation

		if (RegistrationValidation.validateLang(lang) == true) {
			error.append( prop.getProperty("language.null.errormsg"));
			error.append("<br/>");
		}

		// address section validation

		error = RegistrationValidation.validateAddress(req, error,prop);

		// image validation

		if (RegistrationValidation.validateImage(req)) {
			error.append(prop.getProperty( "image.format.errormsg"));
			error.append("<br/>");
		}
		return error.toString();
	}

	/**
	 * Validate lang.
	 *
	 * @param lang
	 *            the lang
	 * @return true, if successful
	 */
	public static boolean validateLang(final String lang[]) {
		boolean iserror = true;
		if (lang != null) {
			for (final String language : lang) {
				if (!"".equals(language)) {
					iserror = false;
				}
			}
		}
		return iserror;
	}

	/**
	 * Validate address.
	 *
	 * @param req
	 *            the req
	 * @param error
	 *            the error
	 * @return the string
	 */
	public static StringBuilder validateAddress(final HttpServletRequest req, final StringBuilder error,final Properties prop) {
		final String addressline1[] = req.getParameterValues("addressline1");
		final String addressline2[] = req.getParameterValues("addressline2");
		final String pin[] = req.getParameterValues("pin");
		final String city[] = req.getParameterValues("city");
		final String state[] = req.getParameterValues("state");
		final String country[] = req.getParameterValues("country");
		final int elementLength = Integer.parseInt(req.getParameter("czContainer_czMore_txtCount"));
		if (elementLength>0) {
			if (addressline1 != null && addressline2 != null&&pin != null &&city != null &&state != null &&country != null) {
				if (elementLength == addressline1.length && elementLength == addressline2.length && elementLength == pin.length && elementLength == city.length &&elementLength == state.length &&elementLength == country.length) {
					for (int i = 0; i < addressline1.length; i++) {
						if (addressline1[i].equals("")) {
							error.append( prop.getProperty( "addressline1.null.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						} else if (addressline1[i].length() > 100) {
							error.append( prop.getProperty( "addressline1.length.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						}
						if (addressline2[i].length() > 100) {
							error.append( prop.getProperty( "addressline2.length.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						}
						if (pin[i].equals("")) {
							error.append( prop.getProperty( "pin.null.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						} else if (pin[i].length() > 6 && pin[i].length() < 6) {
							error.append( prop.getProperty( "pin.length.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						} else if (!pin[i].matches("[0-9]+")) {
							error.append( prop.getProperty( "pin.format.errormsg"));
							error.append(i+1);
							error.append("<br/>");

						}
						if (city[i].equals("")) {
							error.append( prop.getProperty( "city.null.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						} else if (city[i].length() > 45) {
							error.append(prop.getProperty( "city.length.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						}
						if (state[i].equals("")) {
							error.append( prop.getProperty( "state.null.errormsg"));
							error.append(i+1);
							error.append(i+1);
							error.append("<br/>");
						} else if (state[i].length() > 45) {
							error.append( prop.getProperty( "state.length.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						}
						if (country[i].equals("")) {
							error.append( prop.getProperty( "country.null.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						} else if (city[i].length() > 45) {
							error.append( prop.getProperty( "country.length.errormsg"));
							error.append(i+1);
							error.append("<br/>");
						}
					}
				}else {
					error.append(prop.getProperty( "addrs.inconsistent"));
					error.append("<br/>");
				}
			}else {
				error.append( prop.getProperty( "addrs.inconsistent"));
				error.append("<br/>");
			}
		}
		return error;
	}

	/**
	 * Validate image.
	 *
	 * @param request
	 *            the request
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
	public static boolean validateImage(final HttpServletRequest request) throws IOException, ServletException {
		boolean iserror = true;
		for (final Part part : request.getParts()) {
			if (part.getContentType() != null)   {
				if(part.getContentType().equals("image/jpeg")|| part.getContentType().equals("application/octet-stream")) {
					iserror = false;
				}
			}
		}
		return iserror;
	}
}
