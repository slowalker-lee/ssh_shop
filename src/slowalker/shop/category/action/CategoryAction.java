package slowalker.shop.category.action;

import com.opensymphony.xwork2.ActionSupport;

import slowalker.shop.category.service.CategoryService;

public class CategoryAction extends ActionSupport {
	//由spring完成依赖注入
	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
