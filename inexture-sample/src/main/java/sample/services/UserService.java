package sample.services;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import sample.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
/**
 * @author inexture
 *
 */
public interface UserService {

	/**
	 * Authenticate.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the model and view
	 */
	ModelAndView authenticate(String email,String password);

	/**
	 * Creates the.
	 *
	 * @param user the user
	 */
	void create(User user);

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	boolean delete(long iduser);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<User> findAll();

	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the user
	 */
	User get(long iduser);

	/**
	 * Update.
	 *
	 * @param user the user
	 * @param iduser the iduser
	 * @param deletedImages the deleted images
	 */
	void update(User user,long iduser,List<Long> deletedImages);

	/**
	 * Update pass.
	 *
	 * @param email the email
	 * @param password the password
	 * @param newPass the new pass
	 */
	void updatePass(String email,String password,String newPass);
}
