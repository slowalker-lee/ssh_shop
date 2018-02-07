package slowalker.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import slowalker.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{

	public List<Category> findAll() {
		List<Category> lists = (List<Category>) this.getHibernateTemplate().find("from Category");
		return lists;
	}

}
