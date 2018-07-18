package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.services.UserService;


// TODO: Auto-generated Javadoc
/**
 * The Class AdminController.
 *
 * @author inexture
 */
@Controller
@RequestMapping("/user/admin")
public class AdminController {

	/** The user service. */
	@Autowired
	private  UserService userService;

	/**
	 * Show all user.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value="/ShowAllUser",method=RequestMethod.GET)
	public String showAllUser(final Model model) {
		model.addAttribute("userslist",userService.findAll());
		return "ManageUser";
	}

}
