package validations;

public class LoginValidation {

	public String validate(String email,String pass) {
		String error = "";
		if (email.equals("")) {
			error += "email should not be null";

		} else if (email.length() > 80) {
			error += "email should not be more than 80 characters <br/>";
		} else if (!email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+")) {
			error += "email should  be in valid format <br/>";

		}
		if (pass.equals("")) {
			error += "password should not be null <br/>";
		} else if (  (pass.length() >15)||(pass.length() < 6)) {
			error += "password should not be more than 15 characters and less than 6 characters <br/>";
		}
		return error;
	}
}
