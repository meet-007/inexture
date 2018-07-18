package sample.dao;

import sample.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao extends IGenericDao<User>{

	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @return the user
	 */
	User getUser (String email);

	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the user
	 */
	User getUser (String email,String password);

}
