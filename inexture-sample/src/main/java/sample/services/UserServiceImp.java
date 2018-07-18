package sample.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import sample.dao.GenericHibernateDao;
import sample.dao.UserDao;
import sample.models.Address;
import sample.models.User;
import sample.models.UserImages;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImp.
 */
@SuppressWarnings("PMD")
@Service
public class UserServiceImp implements UserService {

	/** The user dao. */
	@Autowired
	UserDao userDao;

	/** The address dao. */
	@Autowired
	GenericHibernateDao<Address> addressDao;

	/** The image dao. */
	@Autowired
	GenericHibernateDao<UserImages> imageDao;

	/** The session. */
	@Autowired
	HttpSession session;

	/* (non-Javadoc)
	 * @see sample.services.UserService#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ModelAndView authenticate(final String email, final String password) {
		// TODO Auto-generated method stub
		final User user = userDao.getUser(email, password);
		final List<UserImages> imageList = imageDao.createQuery("select_image_frm_user", user);
		final ModelAndView modelAndView = new ModelAndView();
		if (user == null) {
			modelAndView.setViewName("redirect:/user/login");
		} else {
			user.setUserImages(imageList);
			modelAndView.addObject("userObject", user);
			modelAndView.setViewName("redirect:/user/" + user.getRole().getRolename() + "/home");
		}
		return modelAndView;
	}

	/* (non-Javadoc)
	 * @see sample.services.UserService#create(sample.models.User)
	 */
	@Override
	@Transactional
	public void create(final User user) {
		// TODO Auto-generated method
		userDao.setClazz(User.class);
		final Long id = (Long) userDao.save(user);
		user.setIduser(id);
		if (user.getAddressList() != null) {
			for (final Address address : user.getAddressList()) {
				address.setUser(user);
				addressDao.save(address);
			}
		}
		if (user.getUserImages() != null) {
			for (final UserImages image : user.getUserImages()) {
				if (image.getImage().length != 0) {
					image.setIduser(user);
					imageDao.save(image);
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see sample.services.UserService#delete(long)
	 */
	@Override
	@Transactional
	public boolean delete(final long id) {
		// TODO Auto-generated method stub
		userDao.setClazz(User.class);
		return userDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see sample.services.UserService#findAll()
	 */
	@Override
	@Transactional
	public List<User> findAll() {
		// TODO Auto-generated method stub
		userDao.setClazz(User.class);
		return userDao.findAll();
	}

	/* (non-Javadoc)
	 * @see sample.services.UserService#get(long)
	 */
	@Override
	@Transactional
	public User get(final long id) {
		// TODO Auto-generated method stub
		userDao.setClazz(User.class);
		final User user = userDao.findOne(id);
		final Set<Address> addressSet = new HashSet<Address>(user.getAddressList());
		final List<Address> addresslist = new ArrayList<Address>(addressSet);
		user.setAddressList(addresslist);
		final List<UserImages> imageList = imageDao.createQuery("select_image_frm_user", user);
		user.setUserImages(imageList);
		// System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
		// + addressSet.size());

		return user;

	}

	/* (non-Javadoc)
	 * @see sample.services.UserService#update(sample.models.User, long, java.util.List)
	 */
	@Override
	@Transactional
	public void update(final User user, final long iduser, final List<Long> deletedImages) {
		// TODO Auto-generated method stub
		userDao.setClazz(User.class);
		final User dbuser = userDao.findOne(iduser);
		user.setEmail(dbuser.getEmail());
		user.setPassword(dbuser.getPassword());
		user.setRole(dbuser.getRole());
		user.setIduser(iduser);
		userDao.clear();
		userDao.saveOrUpdate(user);
		int save = 0;
		final List<Address> useraddrslist = user.getAddressList();
		if (dbuser.getAddressList() != null) {
			if (dbuser.getAddressList().size() > 0) {
				for (final Address dbAddress : dbuser.getAddressList()) {
					if (user.getAddressList() != null) {
						if (user.getAddressList().size() > 0) {
							for (final Address address : useraddrslist) {
								if (dbAddress.getIdadress().equals(address.getIdadress())) {
									save = 1;
									break;
								}
							}
						}
					}
					if (save == 0) {
						addressDao.delete(dbAddress);

					}
					save = 0;
				}
			}
		}

		if (useraddrslist != null) {
			if (useraddrslist.size() > 0) {
				for (final Address adrs : useraddrslist) {
					adrs.setUser(user);
					addressDao.saveOrUpdate(adrs);
				}
			}
		}
		if (deletedImages != null) {
			for (final Long imageId : deletedImages) {
				imageDao.setClazz(UserImages.class);
				imageDao.deleteById(imageId);
			}
		}
		if (user.getUserImages() != null) {
			for (final UserImages image : user.getUserImages()) {
				if (image.getImage().length != 0) {
					image.setIduser(user);
					imageDao.save(image);
				}

			}
		}
	}

	/* (non-Javadoc)
	 * @see sample.services.UserService#updatePass(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void updatePass(final String email, final String password, final String newPass) {
		// TODO Auto-generated method stub
		User user = null;
		if (email == null) {
			user = (User) session.getAttribute("userObject");
			if (user.getPassword().equals(password)) {
				user.setPassword(newPass);
				userDao.saveOrUpdate(user);
			}

		} else {

			user = userDao.getUser(email);
			if (user != null) {
				if (user.getEmail() != null) {
					if (user.getEmail().equals(email)) {
						user.setPassword(password);
						userDao.saveOrUpdate(user);
					}
				}
			}

		}

	}
}
