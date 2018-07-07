package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;
import sample.services.LangServ;
import sample.services.TechService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	TechService techService;
	@Autowired
	LangServ  langService;
	@RequestMapping(value="/register",method=RequestMethod.POST)
	String doRegister(@ModelAttribute User user) {
		return "redirectLogin";
	}
	@RequestMapping(value="/register",method=RequestMethod.GET)
	String showRegistrationForm(Model model) {
		model.addAttribute("tech",techService.findAll());
		model.addAttribute("lang",langService.findAll());
		return "Registration";
	}


}
