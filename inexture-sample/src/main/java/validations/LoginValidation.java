package validations;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginValidation.
 */
public class LoginValidation {

	/**
	 * Validate.
	 *
	 * @param email the email
	 * @param pass the pass
	 * @return the string
	 */
	public String validate(final String email,final String pass) {
		final StringBuilder  error = new StringBuilder(117);
		if ("".equals(email)) {
			error.append( "email should not be null");

		} else if (email.length() > 80) {
			error.append( "email should not be more than 80 characters <br/>");
		} else if (!email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+")) {
			error.append(  "email should  be in valid format <br/>");

		}
		if ("".equals(pass)) {
			error.append(  "password should not be null <br/>");
		} else if (  (pass.length() >15)||(pass.length() < 6)) {
			error.append( "password should not be more than 15 characters and less than 6 characters <br/>");
		}
		return error.toString();
	}
}
