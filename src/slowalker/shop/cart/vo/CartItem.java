package slowalker.shop.cart.vo;

import slowalker.shop.product.vo.Product;

public class CartItem {
	private Product product;		//购物项中商品信息
	private int count;				//购物项中商品数量
	private double subtotal;		//购物项中每个项目小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	
	/*小计自动计算
	 * public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
	
	
	
}
