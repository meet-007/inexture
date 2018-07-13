package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.services.UserService;


@Controller
@RequestMapping("/user/admin")
public class AdminController {

	@Autowired
	UserService userService;
	@RequestMapping(value="/ShowAllUser",method=RequestMethod.GET)
	String showAllUser(Model model) {
		model.addAttribute("userslist",userService.findAll());
		return "ManageUser";
	}

}
