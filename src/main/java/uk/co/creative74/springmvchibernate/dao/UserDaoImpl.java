package uk.co.creative74.springmvchibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import uk.co.creative74.springmvchibernate.model.Employee;
import uk.co.creative74.springmvchibernate.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	static final Logger appLog = LoggerFactory.getLogger("application-log");

	public User findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public User findByUsername(String username) {
		
		appLog.debug("looking for user in DB : " + username);
		
		List<User> users = new ArrayList<User>();
		
		users = getSession()
				.createQuery("from User where username = ?")
				.setParameter(0, username)
				.list();
		
		if (users.size() > 0) {
			
			appLog.debug("found for user in DB : " + users.get(0).getUsername());
			
			return users.get(0);
		} else {
			
			appLog.debug("looking for user in DB : NULL");
			
			return null;
		}		
	}

	public void saveUser(User user) {
		persist(user);
	}

	public void deleteUserByID(int id) {
		Query query = getSession().createSQLQuery("delete from User where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

}
