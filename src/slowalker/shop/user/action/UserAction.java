package slowalker.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import slowalker.shop.user.service.UserService;
import slowalker.shop.user.vo.User;
/**
 * 用户模块 action 类
 * @author slowalker
 *
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	//模型驱动
	private User user = new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	//service注入
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String toRegisterPage() {
		return "toRegisterPage";
	}
	
	
	/*
	 * ajax进行异步校验用户名方法
	 */
	public String findByName() {
		String username = user.getUsername();
		User user = userService.findByName(username);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(user == null) {
			try {
				response.getWriter().println("<font color='green'>用户名可以使用</font>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				response.getWriter().println("<font color='red'>用户名已被占用</font>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NONE;
	}
	
	//进行数据校验,页面不跳转
	//此步进行xml校验
	public String regist() {
		//将user写入数据库
		userService.save(user);
		this.addActionMessage("注册成功,请去激活");
		return "regist";
	}
	
	//用户激活
	public String active() {
		//查询激活码对应用户是否存在
		System.out.println(user.getCode());
		User userExist = userService.findByCode(user.getCode());
		if(userExist == null) {
			//激活失败
			this.addActionMessage("激活失败,请重新注册");
			System.out.println("激活失败");
			return "regist";
		}
		else {
			//激活成功
			//设置状态为1
			userExist.setState(1);
			userExist.setCode(null);
			userService.update(userExist);
			System.out.println("激活成功");
			this.addActionMessage("激活成功,请去登录");
		}
		
		return "active";
	}
	
	public String toLoginPage() {
		return "toLoginPage";
	}
	
	public String login() {
		//模型驱动获取用户名,密码
		//调用userService查询
		User userExist = userService.login(user);
		if(userExist != null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", userExist);
			return "loginSuccess";
		}
		this.addActionError("登录失败,帐号或密码错误,或帐号未激活");
		return "toLoginPage";
	}
	
	//用户退出,销毁session
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

	
}
