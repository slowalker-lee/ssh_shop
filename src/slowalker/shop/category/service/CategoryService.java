package slowalker.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import slowalker.shop.category.dao.CategoryDao;
import slowalker.shop.category.vo.Category;

@Transactional
public class CategoryService {
	//由spring完成依赖注入
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		List<Category> lists = categoryDao.findAll();
		return lists;
	}
	
}
