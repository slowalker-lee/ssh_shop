package slowalker.shop.product.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import slowalker.shop.product.vo.Product;
import slowalker.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	//首页上热门商品查询
	public List<Product> findHot() {
		//离线对象查询
		@SuppressWarnings("unchecked")
		//执行查询
		List<Product> products = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
			criteria.add(Restrictions.eq("is_hot", 1));
			// 倒序排序
			criteria.addOrder(Order.desc("pdate"));
			products = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
			
			System.out.println("dao层" + products.toString());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return products;
	}

	//首页最新方法查询
	public List<Product> findNew() {
		DetachedCriteria criteria =DetachedCriteria.forClass(Product.class);
		//按照日期进行倒序排序
		criteria.addOrder(Order.desc("pdate"));
		List<Product> newProduct = 
				(List<Product>) this.getHibernateTemplate().
				findByCriteria(criteria, 0, 10);
		return newProduct;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		Product product = this.getHibernateTemplate().get(Product.class, pid);
		return product;
	}

	//根据分类id查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		//String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// 分页的另外一种写法
		/*this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				return null;
			}
		})*/
		List<Product> list = null;
		list = this.getHibernateTemplate().
				execute(new PageHibernateCallback<Product>(hql,new Object[] {cid},begin,limit));
		if(list != null && list.size() != 0) {
			System.out.println("cid list 长度" + (list.size()));
			return list;
		}
			
		return null;
	}
	//查询cid对应产品记录数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product as p where p.categorySecond.category.cid = ?";
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, cid);
		if(list == null || list.size() == 0)	
			return 0;
		return list.get(0).intValue();
	}

	//查询csid对应的产品数
	public int findCountCsid(Integer csid) {
		
		String hql = "select count(*) from Product as p where p.categorySecond.csid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	
	/*public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = null;
		list = this.getHibernateTemplate().
				execute(new PageHibernateCallback<Product>(hql,new Object[] {csid},begin,limit));
		if(list != null && list.size() != 0) {
			System.out.println("csid list 长度" + (list.size()));
			return list;
		}
		System.out.println("csid list 长度");
		List<Product> list =  (List<Product>) this.getHibernateTemplate().find(hql, csid);
		System.out.println(list.toString());
		return list;
	}*/

	//根据csid查询记录
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		/*String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = 
				this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[] {csid}, begin, limit));
		if(list != null && list.size() != 0) {
			return list;
		}
		System.out.println("list 为空 + 起始值" + begin);
		return list;*/
		
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		//String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().
				execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			System.out.println("list 长度" + (list.size()));
			return list;
		}
		System.out.println("list 长度" + (list.size()));
		System.out.println("scid list 为空 + 起始值" + begin);
		System.out.println(csid + " " + begin + " " + limit);
		
		return null;
	}

}
