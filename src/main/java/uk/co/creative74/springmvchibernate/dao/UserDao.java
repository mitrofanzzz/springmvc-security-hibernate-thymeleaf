package uk.co.creative74.springmvchibernate.dao;

import java.util.List;

import uk.co.creative74.springmvchibernate.model.User;

public interface UserDao {

	User findById(int id);
	
	User findByUsername(String username);

	void saveUser(User user);
	
	void deleteUserByID(int id);
	
	List<User> findAllUsers();
}
