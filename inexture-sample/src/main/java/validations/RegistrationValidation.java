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
	 * @param req the req
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public String validate(HttpServletRequest req) throws IOException, ServletException {
		/* getting parameters from request object */
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String tech = req.getParameter("tech");
		String lang[] = req.getParameterValues("lang");
		//String role = req.getParameter("role");
		String error = "";
		if(fname.equals("")) {
			error += "fname should not be null \n";
		}else if (fname.length() > 100) {
			error += "fname should not be more than 100 characters <br/>";
		}
		if(lname.equals("")) {
			error += "lname should not be null";
		}else if (lname.length() > 100) {
			error += "lname should not be more than 100 characters <br/>";
		}
		if(email.equals("")) {
			error += "email should not be null";

		}else if (email.length() > 80) {
			error +="email should not be more than 80 characters <br/>";
		}else if(!email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+")){
			error +="email should  be in valid format <br/>";
		}
		if(pass.equals("")) {
			error += "password should not be null \n";
		}else if ((pass.length() > 15) && (pass.length()<6)) {
			error +="password should not be more than 15 characters and less than 6 characters <br/>";
		}
		if(mobile.equals("")) {
			error +="moble should not be null \n";
		}else if (mobile.length() > 10) {
			error +="mobile should not be more than 10 characters<br/>";
		}
		if(dob.equals("")) {
			error +="dob should not be null <br/>";
		}else if (! dob.matches("\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])")) {
			error +="dob should be in proper format <br/>";
		}
		if(gender.equals("")) {
			error +="gender should not be null <br/>";
		}
		if(tech.equals("")) {
			error +="tech should not be null <br/>";
		}
		//language validation

		if(RegistrationValidation.validateLang(lang) == true) {
			error += "language should not be null";
		}

		//address section validation

		error = RegistrationValidation.validateAddress(req,error);

		//image validation

		if(RegistrationValidation.validateImage(req) == true) {
			error += "image must have jpg extension";
		}
		return error;
	}

	/**
	 * Validate lang.
	 *
	 * @param lang the lang
	 * @return true, if successful
	 */
	static boolean validateLang(String lang[]) {
		boolean iserror = true;
		if(lang != null) {
			for(String language : lang) {
				if(!language.equals("")) {
					iserror = false;
				}
			}
		}
		return iserror;
	}

	/**
	 * Validate address.
	 *
	 * @param req the req
	 * @param error the error
	 * @return the string
	 */
	static String validateAddress(HttpServletRequest req,String error) {
		String addressline1[] = req.getParameterValues("addressline1");
		String addressline2[] = req.getParameterValues("addressline2");
		String pin[] = req.getParameterValues("pin");
		String city[] = req.getParameterValues("city");
		String state[] = req.getParameterValues("state");
		String country[] = req.getParameterValues("country");
		if(addressline1 != null) {
			for (int i=0;i<addressline1.length;i++) {
				if(addressline1[i].equals("")) {
					error += "Addressline1 of address "+i+1+" should not be null <br/>";
				}else if(addressline1[i].length() > 100){
					error += "Addressline1 of address "+i+1+" should not be more than 100 characters <br/>";
				}
				if(addressline2[i].length() > 100){
					error += "Addressline2 of address "+i+1+" should not be more than 100 characters <br/>";
				}
				if(pin[i].equals("")) {
					error += "pin of address "+i+1+" should not be null <br/>";
				}else if((pin[i].length() > 6) && (pin[i].length() < 6)){
					error += "pin of address "+i+1+" should not be more than 6 characters and less than 6 characters <br/>";
				}else if(!pin[i].matches("[0-9]+")) {
					error += "pin of address "+i+1+" should be in proper format <br/>";

				}
				if(city[i].equals("")) {
					error += "city of address "+i+1+" should not be null <br/>";
				}else if(city[i].length() >45 ){
					error += "city of address "+i+1+" should not be more than 45 characters <br/>";
				}if(state[i].equals("")) {
					error += "state of address "+i+1+" should not be null <br/>";
				}else if(state[i].length() >45 ){
					error += "state of address "+i+1+" should not be more than 45 characters <br/>";
				}if(country[i].equals("")) {
					error += "country of address "+i+1+" should not be null <br/>";
				}else if(city[i].length() >45 ){
					error += "country of address "+i+1+" should not be more than 45 characters <br/>";
				}
			}
		}

		return error;
	}

	/**
	 * Validate image.
	 *
	 * @param request the request
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	static boolean validateImage(HttpServletRequest request) throws IOException, ServletException {
		boolean iserror = true;
		for(Part part : request.getParts()) {
			if(part.getContentType() != null) {
				if(part.getContentType().equals("image/jpeg")||part.getContentType().equals("application/octet-stream")) {
					iserror = false;
				}
			}
		}
		return iserror;
	}
}

