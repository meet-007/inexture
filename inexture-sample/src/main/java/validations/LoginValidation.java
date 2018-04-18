package validations;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;

import util.DbUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginValidation.
 */
@SuppressWarnings("PMD")
public class LoginValidation {

	/**
	 * Validate.
	 *
	 * @param email the email
	 * @param pass the pass
	 * @return the string
	 */
	public String validate(final String email,final String pass)  throws IOException, ServletException{
		final Properties prop = DbUtil.getProperties("validations.properties");
		final StringBuilder  error = new StringBuilder(117);
		if ("".equals(email)) {
			error.append( prop.getProperty("email.null.errormsg"));
			error.append("<br/>");
		} else if (email.length() > 80) {
			error.append( prop.getProperty("email.length.errormsg"));
			error.append("<br/>");
		} else if (!email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+")) {
			error.append( prop.getProperty("email.format.errormsg"));
			error.append("<br/>");

		}
		if ("".equals(pass)) {
			error.append( prop.getProperty("password.null.errormsg"));
			error.append("<br/>");
		} else if (  (pass.length() >15)||(pass.length() < 6)) {
			error.append( prop.getProperty("password.length.errormsg"));
			error.append("<br/>");
		}
		return error.toString();
	}
}
