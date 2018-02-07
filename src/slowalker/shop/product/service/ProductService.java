package slowalker.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import slowalker.shop.product.vo.Product;
import slowalker.shop.utils.PageBean;
import slowalker.shop.product.dao.ProductDao;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		List<Product> newProduct = productDao.findNew();
		return newProduct;
	}

	public Product findByPid(Integer pid) {
		Product product = productDao.findByPid(pid);
		return product;
	}

	//分页查询商品
	public PageBean<Product> findByPage(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<>();
		//设置当前页数
		pageBean.setPage(page);
		int limit = 8;
		pageBean.setLimit(limit);
		int totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount%limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin = (page -1) * limit;
		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<>();
		//设置当前页数
		pageBean.setPage(page);
		int limit = 8;
		pageBean.setLimit(limit);
		int totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount%limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin = (page -1) * limit;
		
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		
		pageBean.setList(list);
		
		
		return pageBean;
	}

	
}
