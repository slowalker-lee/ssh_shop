package slowalker.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import slowalker.shop.category.service.CategoryService;
import slowalker.shop.product.service.ProductService;
import slowalker.shop.product.vo.Product;
import slowalker.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//接受当前页数
	private int page;
	
	public void setPage(int page) {
		this.page = page;
	}

	//模型驱动
	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	//接受分类的id
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String findByPid() {
		//模型驱动对象会在栈顶
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据一级分类查询商品
	public String findByCid() {
		//获取分类id,在该类中定义cid属性,提供setter
		//categoryService.findAll();
		
		//但是因为session中有一级分类的信息,不需要再查询
		
		
		PageBean<Product> pageBean = productService.findByPage(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCid";
	}

	public Integer getCid() {
		return cid;
	}
	
	//接受csid
	private Integer csid;
	

	public int getCsid() {
		return csid;
	}

	public void setCsid(int csid) {
		this.csid = csid;
	}

	public String findByCsid() {
		System.out.println("csid value" + csid);
		PageBean<Product> pageBean= productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCsid";
	}
	
}
