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

	@Autowired
	UserDao userDao;
	@Autowired
	GenericHibernateDao<Address> addressDao;
	@Autowired
	GenericHibernateDao<UserImages> imageDao;
	@Autowired
	HttpSession session;

	@Override
	@Transactional
	public ModelAndView authenticate(String email, String password) {
		// TODO Auto-generated method stub
		final User user = userDao.getUser(email, password);
		final ModelAndView modelAndView = new ModelAndView();
		if (user == null) {
			modelAndView.setViewName("redirect:/user/login");
		} else {

			modelAndView.addObject("userObject", user);
			modelAndView.setViewName("redirect:/user/" + user.getRole().getRolename() + "/home");
		}
		return modelAndView;
	}

	@Override
	@Transactional
	public void create(User user) {
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
				image.setIduser(user);
				imageDao.save(image);
			}
		}

	}

	@Override
	@Transactional
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		userDao.setClazz(User.class);
		return userDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		// TODO Auto-generated method stub
		userDao.setClazz(User.class);
		return userDao.findAll();
	}

	@Override
	@Transactional
	public User get(long id) {
		// TODO Auto-generated method stub
		userDao.setClazz(User.class);
		final User user = userDao.findOne(id);
		final Set<Address> addressSet = new HashSet<Address>(user.getAddressList());
		final List<Address> addresslist = new ArrayList<Address>(addressSet);
		user.setAddressList(addresslist);
		// System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
		// + addressSet.size());

		return user;

	}

	@Override
	@Transactional
	public void update(User user, long iduser) {
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
								if (dbAddress.getIdadress() == address.getIdadress()) {
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
		if (user.getUserImages().size() > 0) {
			for (final UserImages image : user.getUserImages()) {
				if (image.getIduserImages() != null) {
					imageDao.delete(image);
				}
			}
		}
	}

	@Override
	@Transactional
	public void updatePass(String email, String password, String newPass) {
		// TODO Auto-generated method stub
		User user = null;
		if (email == null) {
			user = (User) session.getAttribute("userObject");
			if (user.getPassword().equals(password)) {
				user.setPassword(newPass);
				userDao.saveOrUpdate(user);
			}
		} else {
			if (email.equals(user.getEmail())) {
				user = userDao.getUser(email);
				user.setPassword(password);
				userDao.saveOrUpdate(user);
			}
		}

	}
}
