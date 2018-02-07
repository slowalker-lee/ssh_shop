package slowalker.shop.user.service;

import javax.mail.MessagingException;

import org.springframework.transaction.annotation.Transactional;

import slowalker.shop.user.dao.UserDao;
import slowalker.shop.user.vo.User;
import slowalker.shop.utils.MailUtils;
import slowalker.shop.utils.UUIDUtils;

@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User findByName(String username) {
		User user = userDao.findByName(username);
		return user;
	}

	public void save(User user) {
		user.setState(0);//0未激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送邮件
		MailUtils.sendMail(user.getEmail(), code);
	}

	public User findByCode(String code) {
		User user = userDao.findByCode(code);
		return user;
	}

	public void update(User user) {
		userDao.update(user);
		
	}

	//用户登陆方法
	public User login(User user) {
		
		return userDao.login(user);
	}
	
	
}
