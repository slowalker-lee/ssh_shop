package slowalker.shop.cart.action;
import org.apache.struts2.ServletActionContext;

/**
 * 购物车action
 */
import com.opensymphony.xwork2.ActionSupport;

import slowalker.shop.cart.vo.Cart;
import slowalker.shop.cart.vo.CartItem;
import slowalker.shop.product.service.ProductService;

public class CartAction extends ActionSupport {
	private Integer pid;
	
	private Integer count; 
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//将购物项目添加到购物车
	public String addCart() {
		// 封装一个CartItem的对象
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findByPid(pid));
		
		//购物车对象需要存放在session
		this.getCart().addCartItem(cartItem);
		return "addCart";
	}
	
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public String clear() {
		//ServletActionContext.getRequest().getSession().removeAttribute("cart");
		Cart c = 
				(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		c.clearCart();
		return "clear";
	}
	
	public String delete() {
		Cart c = this.getCart();
		c.removeCartItem(pid);
		return "delete";
	}
	
	public String showCart() {
		return "showCart";
	}
}
