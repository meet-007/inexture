package validations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
// TODO: Auto-generated Javadoc

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
		if ((req.getParameter("email") != null) && (req.getParameter("pass") != null)) {
			email = req.getParameter("email");
			pass = req.getParameter("pass");
		}
		final String mobile = req.getParameter("mobile");
		final String dob = req.getParameter("dob");
		final String gender = req.getParameter("gender");
		final String tech = req.getParameter("tech");
		final String lang[] = req.getParameterValues("lang");
		// String role = req.getParameter("role");
		StringBuilder error = new StringBuilder();
		if ((fname == null) || "".equals(fname)) {
			error.append( "fname should not be null \n");
		} else if ((fname.length() > 100)) {
			error.append( "fname should not be more than 100 characters <br/>");
		}
		if ((lname == null) ||"".equals(lname)) {
			error.append( "lname should not be null");
		} else if ((lname.length() > 100)) {
			error.append( "lname should not be more than 100 characters <br/>");
		}
		if(req.getServletPath() == "/Registration.jsp") {
			if ((email == null) ||"".equals(email)) {
				error.append( "email should not be null");

			} else if (email.length() > 80) {
				error.append( "email should not be more than 80 characters <br/>");
			} else if (!email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+")) {
				error.append( "email should  be in valid format <br/>");
			}


			if ((pass == null) ||"".equals(pass)) {
				error.append( "password should not be null \n");
			} else if ((pass.length() < 6) || (pass.length() > 15)) {
				error.append( "password should not be more than 15 characters and less than 6 characters <br/>");
			}
		}
		if ((mobile == null) || "".equals(mobile)) {
			error.append( "moble should not be null \n");
		} else if ((mobile.length() > 10)) {
			error.append( "mobile should not be more than 10 characters<br/>");
		}
		if ((dob == null) ||"".equals(dob)) {
			error.append( "dob should not be null <br/>");
		} else if (!dob.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])")) {
			error.append( "dob should be in proper format <br/>");
		}
		if ((gender == null) ||"".equals(gender)) {
			error.append( "gender should not be null <br/>");
		}
		if ((tech == null) ||"".equals(tech)) {
			error.append( "tech should not be null <br/>");
		}
		// language validation

		if (RegistrationValidation.validateLang(lang) == true) {
			error.append( "language should not be null");
		}

		// address section validation

		error = RegistrationValidation.validateAddress(req, error);

		// image validation

		if (RegistrationValidation.validateImage(req) == true) {
			error.append( "image must have jpg extension");
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
	public static StringBuilder validateAddress(final HttpServletRequest req, final StringBuilder error) {
		final String addressline1[] = req.getParameterValues("addressline1");
		final String addressline2[] = req.getParameterValues("addressline2");
		final String pin[] = req.getParameterValues("pin");
		final String city[] = req.getParameterValues("city");
		final String state[] = req.getParameterValues("state");
		final String country[] = req.getParameterValues("country");
		final int elementLength = Integer.parseInt(req.getParameter("czContainer_czMore_txtCount"));
		if ((addressline1 != null) && (addressline2 != null)&&(pin != null) &&(city != null) &&(state != null) &&(country != null)) {
			if ((elementLength == addressline1.length) && (elementLength == addressline2.length) && (elementLength == pin.length) && (elementLength == city.length) &&(elementLength == state.length) &&(elementLength == country.length)) {
				for (int i = 0; i < addressline1.length; i++) {
					if (addressline1[i].equals("")) {
						error.append( "Addressline1 of address " + i + 1 + " should not be null <br/>");
					} else if (addressline1[i].length() > 100) {
						error.append( "Addressline1 of address " + i + 1 + " should not be more than 100 characters <br/>");
					}
					if (addressline2[i].length() > 100) {
						error.append( "Addressline2 of address " + i + 1 + " should not be more than 100 characters <br/>");
					}
					if (pin[i].equals("")) {
						error.append( "pin of address " + i + 1 + " should not be null <br/>");
					} else if ((pin[i].length() > 6) && (pin[i].length() < 6)) {
						error.append( "pin of address " + i + 1
								+ " should not be more than 6 characters and less than 6 characters <br/>");
					} else if (!pin[i].matches("[0-9]+")) {
						error.append( "pin of address " + i + 1 + " should be in proper format <br/>");

					}
					if (city[i].equals("")) {
						error.append( "city of address " + i + 1 + " should not be null <br/>");
					} else if (city[i].length() > 45) {
						error.append( "city of address " + i + 1 + " should not be more than 45 characters <br/>");
					}
					if (state[i].equals("")) {
						error.append( "state of address " +( i + 1) + " should not be null <br/>");
					} else if (state[i].length() > 45) {
						error.append( "state of address " + i + 1 + " should not be more than 45 characters <br/>");
					}
					if (country[i].equals("")) {
						error.append( "country of address " + i + 1 + " should not be null <br/>");
					} else if (city[i].length() > 45) {
						error.append( "country of address " + i + 1 + " should not be more than 45 characters <br/>");
					}
				}
			}else {
				error.append( "inconsistent address details<br/>");
			}
		}else {
			error.append( "inconsistent address details<br/>");
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
			if ((part.getContentType() != null))   {
				if(part.getContentType().equals("image/jpeg")|| part.getContentType().equals("application/octet-stream")) {
					iserror = false;
				}
			}
		}
		return iserror;
	}
}
