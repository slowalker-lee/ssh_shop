package slowalker.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import slowalker.shop.user.vo.User;

public class UserDao extends HibernateDaoSupport{
	@SuppressWarnings("unchecked")
	public User findByName(String username) {
		User user = null;
		String hql = "from User where username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0) {
			user = list.get(0);
		}
		return user;
	}

	public void save(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	@SuppressWarnings("unchecked")
	public User findByCode(String code) {
		User user = null;
		String hql = "from User where code = ?";
		List<User> users = (List<User>) this.getHibernateTemplate().find(hql, code);
		if(users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	public void update(User user) {
		this.getHibernateTemplate().update(user);
		
	}

	public User login(User user) {
		User u = null;
		String hql = "from User where username = ? and password = ? and state = 1";
		@SuppressWarnings("unchecked")
		List<User> users = 
				(List<User>) this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword());
		if(users != null && users.size() > 0)
			u = users.get(0);
		return u;
	}
}
