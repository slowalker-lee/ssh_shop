package slowalker.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import slowalker.shop.order.dao.OrderDao;
import slowalker.shop.order.vo.Order;
import slowalker.shop.utils.PageBean;

/**
 * 订单业务层逻辑
 * @author slowalker
 *
 */
@Transactional
public class OrderService {
	//spring自动注入orderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(Order order) {
		orderDao.save(order);
		
	}

	/**
	 * 
	 * @param uid	查询该uid对应的用户的订单
	 * @param page	用户请求访问的页数
	 * @return
	 */
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		int limit = 5;		//每页记录数
		int totalCount = orderDao.findByCountUid(uid); //总计录数
		int totalPage = 0;	//总页数
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		PageBean<Order> pageBean = new PageBean<>();
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		System.out.println("limit = " + limit + " totalcount = " + totalCount);
		System.out.println("page " + page + " totalpage " + totalPage);
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(Integer oid) {
		Order order = orderDao.findByOid(oid);
		return order;
	}

	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}
	
}
