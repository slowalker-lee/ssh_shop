package slowalker.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import slowalker.shop.category.service.CategoryService;
import slowalker.shop.category.vo.Category;
import slowalker.shop.product.service.ProductService;
import slowalker.shop.product.vo.Product;

/**
 * 首页访问的action
 * @author slowalker
 *
 */
public class IndexAction extends ActionSupport{
	
	//需要注入一级分类的service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//注入商品service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	/**
	 * 执行访问首页的方法
	 */
	public String execute() {
		//查询一级分类
		List<Category> lists = categoryService.findAll();
		//每一个网页都有该数据,将其存入session域
		//不要使用servletActionContext,因为其一进入就需要访问???没懂
//		ServletActionContext.getRequest().getSession().setAttribute("clist", lists);
		ActionContext.getContext().getSession().put("cList", lists);
		
		
		//查询热门商品
		List<Product> products = productService.findHot();
		//保存在值栈中
		//ActionContext.getContext().getValueStack().set("hList", products);
		System.out.println(products.toString());
		ActionContext.getContext().getSession().put("hList", products);;
		
		//查询最新产品
		List<Product> newProduct = productService.findNew();
		
		//将其放入值栈中,在页面中进行使用
		ServletActionContext.getRequest().setAttribute("newProduct", newProduct);
		return "index";
	}
}
