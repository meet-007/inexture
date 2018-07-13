package sample.services;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import sample.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {
	ModelAndView authenticate(String email,String password);
	void create(User user);
	List<User> findAll();
	User get(long id);
	void update(User user,long iduser);
}
