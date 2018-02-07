package slowalker.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import slowalker.shop.cart.vo.Cart;
import slowalker.shop.cart.vo.CartItem;
import slowalker.shop.order.service.OrderService;
import slowalker.shop.order.vo.Order;
import slowalker.shop.order.vo.OrderItem;
import slowalker.shop.user.vo.User;
import slowalker.shop.utils.PageBean;

/**
 * 订单管理action
 * @author slowalker
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	//模型驱动
	private Order order = new Order();
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return this.order;
	}
	
	//spring注入orderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		
		this.orderService = orderService;
	}
	
	
	public String save() {
		//保存数据到数据库
		//订单数据补全
		System.out.println("orderaction 1");
		order.setOrdertime(new Date());
		order.setState(1); //1未付款 2已付款未发货 3.已付款已发货未收获 4.交易完成
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null) {
			this.addActionError("亲, 你还没有购物");
		}
		System.out.println("orderaction 2");
		order.setTotal(cart.getTotal());
		//设置订单中订单项
		for(CartItem cartItem:cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		
		//设置订单所属账户
		System.out.println("orderaction 3");
		User user = 
				(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user ==null) {
			this.addActionError("请先去登录");
			return "login";
		}
		System.out.println("orderaction 4");
		order.setUser(user);
		orderService.save(order);
		//将订单对象显示到页面上
		//通过值栈的方式进行回显,因为order实现模型驱动,可以直接获得
		return "saveSuccess";
	}
	
	private Integer page;
	
	
	public void setPage(Integer page) {
		this.page = page;
	}


	public String findByUid() {
		User user = 
				(User)ServletActionContext.getRequest().getSession().getAttribute("user");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		System.out.println(pageBean.getList());
		return "findByUidSuccess";
	}
	
	public String payOrder() {
		// 1.修改数据:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// 修改订单
		orderService.update(currOrder);
		return "paySuccess";
	}
}
