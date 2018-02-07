package slowalker.shop.order.dao; 

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import slowalker.shop.order.vo.Order;
import slowalker.shop.utils.PageHibernateCallback;

/**
 * 订单的dao层
 * @author slowalker
 *
 */
public class OrderDao extends HibernateDaoSupport{
	public void save(Order order) {
		System.out.println("进行保存");
		this.getHibernateTemplate().save(order);
	}

	public int findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, uid);
		if(list != null && list.size() > 0) {
			Long a = list.get(0);
			return a.intValue();
		}
		
		return 0;
		
	}

	public List<Order> findByPageUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = 
				this.getHibernateTemplate().execute(
						new PageHibernateCallback<Order>(hql, new Object[] {uid}, 3, 3)
						);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
		
		
		
		/*String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = (List<Order>) this.getHibernateTemplate().find(hql, uid);
		return list;*/
	}

	public Order findByOid(Integer oid) {
		Order order = this.getHibernateTemplate().get(Order.class, oid);
		return order;
	}

	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

}
