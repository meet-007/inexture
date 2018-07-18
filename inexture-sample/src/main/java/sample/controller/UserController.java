package sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import sample.models.LangMaster;
import sample.models.TechMaster;
import sample.models.User;
import sample.services.LangServ;
import sample.services.TechService;
import sample.services.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("userObject")
public class UserController {

	/** The tech service. */
	@Autowired
	private TechService techService;

	/** The lang service. */
	@Autowired
	private LangServ langService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Change pass.
	 *
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public String changePass(@RequestParam("oldpass") final String oldPassword, @RequestParam("newpass") final String newPassword,
			final Model model) {
		userService.updatePass(null, oldPassword, newPassword);
		model.addAttribute("rspmsg2", "password updated");
		return "redirect:ChangePass";
	}

	/**
	 * Delete user.
	 *
	 * @param iduser the id
	 * @return the string
	 */
	@RequestMapping(value = "/Delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteUser(@PathVariable final int iduser) {

		return userService.delete(iduser) ? "{\"result\":\"" + "success" + "\",\"bool\":\"0\"}"
				: "{\"result\":\"" + "not deleted" + "\",\"bool\":\"1\"}";
	}

	/**
	 * Do register.
	 *
	 * @param user the user
	 * @param error the error
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") final User user, final BindingResult error, final Model model) {
		if (error.hasErrors()) {
			model.addAttribute("tech", techService.findAll());
			model.addAttribute("lang", langService.findAll());
			model.addAttribute("user", user);
			return "Registration";
		}
		userService.create(user);
		return "redirect:/user/login";
	}

	/**
	 * Forgot pass.
	 *
	 * @param email the email
	 * @param password the password
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
	public String forgotPass(@RequestParam("email") final String email, @RequestParam("password") final String password, final Model model) {
		userService.updatePass(email, password, null);
		model.addAttribute("rspmsg", "password updated");
		return "ForgotPassword";
	}

	/**
	 * Inits the binder.
	 *
	 * @param webDataBinder the web data binder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(TechMaster.class, "tech", new TechPropertyEditor());
		webDataBinder.registerCustomEditor(LangMaster.class, "languages", new LangPropertyEditor());
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		final SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy",Locale.getDefault());
		dateFormat.setLenient(false);
		dateFormat1.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat1, true));
		//webDataBinder.registerCustomEditor(UserImages.class, new UserImageProeryEditor());
		//webDataBinder.setConversionService(conversionService);
	}

	/**
	 * Login page.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "Login";
	}

	/**
	 * Logout.
	 *
	 * @param status the status
	 * @return the string
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public	String logout(final SessionStatus status) {
		status.setComplete();
		return "redirect:login";
	}

	/**
	 * Show registration form.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(final Model model) {
		model.addAttribute("tech", techService.findAll());
		model.addAttribute("lang", langService.findAll());
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}
		return "Registration";
	}

	/**
	 * Show update profile page.
	 *
	 * @param model the model
	 * @param idUser the id user
	 * @return the string
	 */
	@RequestMapping(value = "/UpdateProfile", method = RequestMethod.GET)
	public String showUpdateProfilePage(final Model model, @RequestParam(value = "iduser", required = false) final Long idUser) {
		model.addAttribute("tech", techService.findAll());
		model.addAttribute("lang", langService.findAll());
		if (idUser != null) {
			model.addAttribute("user", userService.get(idUser));
		}
		return "UpdateProfile";
	}

	/**
	 * Show update profile page.
	 *
	 * @param user the user
	 * @param iduser the iduser
	 * @param session the session
	 * @param status the status
	 * @param deletedImages the deleted images
	 * @return the model and view
	 */
	@RequestMapping(value = "/UpdateProfile", method = RequestMethod.POST)
	public ModelAndView showUpdateProfilePage(@ModelAttribute final User user, @RequestParam("iduser") final Long iduser,
			final HttpSession session, final SessionStatus status,@RequestParam(name="deletedImages",required=false) final List<Long> deletedImages) {
		userService.update(user, iduser,deletedImages);
		final User sessionUser = (User) session.getAttribute("userObject");
		final ModelAndView modelAndView = new ModelAndView();
		if (user.getIduser().equals(sessionUser.getIduser())) {
			modelAndView.addObject("userObject", userService.get(iduser));
			modelAndView.setViewName("redirect:UpdateProfile");
		} else {
			modelAndView.setViewName("redirect:UpdateProfile?iduser=" + iduser);
		}
		return modelAndView;
	}

	/**
	 * User home.
	 *
	 * @param role the role
	 * @param user the user
	 * @return the string
	 */
	@RequestMapping(value = "/{role}/home", method = RequestMethod.GET)
	public String userHome(@PathVariable final String role, @SessionAttribute("userObject") final User user) {
		if ("user".equals(role)) {
			return "UserHome";
		}
		return "AdminHome";
	}

	/**
	 * Verify.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the model and view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView verify(@RequestParam("email") final String email, @RequestParam("password") final String password) {

		return userService.authenticate(email, password);
	}

}
