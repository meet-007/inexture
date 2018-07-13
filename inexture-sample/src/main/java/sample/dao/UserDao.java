package sample.dao;

import sample.models.User;

public interface UserDao extends IGenericDao<User>{
	User getUser (String email,String password);

}
