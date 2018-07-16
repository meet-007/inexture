package sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

@Controller
@RequestMapping("/user")
@SessionAttributes("userObject")
public class UserController {
	@Autowired
	TechService techService;
	@Autowired
	LangServ langService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	String changePass(@RequestParam("oldpass") String oldPassword, @RequestParam("newpass") String newPassword,
			Model model) {
		userService.updatePass(null, oldPassword, newPassword);
		model.addAttribute("rspmsg2", "password updated");
		return "redirect:ChangePass";
	}

	@RequestMapping(value = "/Delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteUser(@PathVariable int id) {

		return userService.delete(id) ? "{\"result\":\"" + "success" + "\",\"bool\":\"0\"}"
				: "{\"result\":\"" + "not deleted" + "\",\"bool\":\"1\"}";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	String doRegister(@Valid @ModelAttribute("user") User user, BindingResult error, Model model) {
		if (error.hasErrors()) {
			model.addAttribute("tech", techService.findAll());
			model.addAttribute("lang", langService.findAll());
			model.addAttribute("user", user);
			return "Registration";
		}
		userService.create(user);
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
	String forgotPass(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		userService.updatePass(email, password, null);
		model.addAttribute("rspmsg", "password updated");
		return "ForgotPassword";
	}

	@InitBinder
	void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(TechMaster.class, "tech", new TechPropertyEditor());
		webDataBinder.registerCustomEditor(LangMaster.class, "languages", new LangPropertyEditor());
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		dateFormat1.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat1, true));
		//webDataBinder.registerCustomEditor(UserImages.class, new UserImageProeryEditor());
		//webDataBinder.setConversionService(conversionService);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String loginPage() {
		return "Login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	String showRegistrationForm(Model model) {
		model.addAttribute("tech", techService.findAll());
		model.addAttribute("lang", langService.findAll());
		if (!model.containsAttribute("user")) {
			final User user = new User();
			model.addAttribute("user", new User());
		}
		return "Registration";
	}

	@RequestMapping(value = "/UpdateProfile", method = RequestMethod.GET)
	String showUpdateProfilePage(Model model, @RequestParam(value = "iduser", required = false) Long idUser) {
		model.addAttribute("tech", techService.findAll());
		model.addAttribute("lang", langService.findAll());
		if (idUser != null) {
			model.addAttribute("user", userService.get(idUser));
		}
		return "UpdateProfile";
	}

	@RequestMapping(value = "/UpdateProfile", method = RequestMethod.POST)
	ModelAndView showUpdateProfilePage(@ModelAttribute User user, @RequestParam("iduser") Long iduser,
			HttpSession session, SessionStatus status) {
		userService.update(user, iduser);
		final User sessionUser = (User) session.getAttribute("userObject");
		final ModelAndView mv = new ModelAndView();
		if (user.getIduser() == sessionUser.getIduser()) {
			mv.addObject("userObject", userService.get(iduser));
			mv.setViewName("redirect:UpdateProfile");
		} else {
			mv.setViewName("redirect:UpdateProfile?iduser=" + iduser);
		}
		return mv;
	}

	@RequestMapping(value = "/{role}/home", method = RequestMethod.GET)
	String userHome(@PathVariable String role, @SessionAttribute("userObject") User user) {
		if (role.equals("user")) {
			return "UserHome";
		}
		return "AdminHome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	ModelAndView verify(@RequestParam("email") String email, @RequestParam("password") String password) {

		return userService.authenticate(email, password);
	}

}
