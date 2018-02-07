package slowalker.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

	
	// 以商品id作为map的键值
	private Map<Integer, CartItem> map = new LinkedHashMap<>();
	//购物总计
	

	// Cart对象中有一个叫cartItems属性.
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
		
		
	private double total; //总计
	
	/**
	 * 购物车功能:
	 * 	1.清空
	 * 	2.移除购物项
	 * 	3.添加购物项
	 */
	
	
	//1.清空购物车
	public void clearCart() {
		//所有购物项为空
		map.clear();
		//总计设为0
		total = 0;
	}
	
	public double getTotal() {
		return total;
	}

	//移除购物项
	public void removeCartItem(Integer pid) {
		total = total - map.get(pid).getCount() * map.get(pid).getProduct().getShop_price();
		//将购物项从集合中移除,
		CartItem ct = map.remove(pid);
		//修改总计
		/*total -= ct.getSubtotal();*/
		
		
		//total = total - map.get(pid).getCount() * map.get(pid).getProduct().getShop_price();
		
		
	}
	
	//添加购物项
	public void addCartItem(CartItem cartItem) {
		//判断购物项中是否已经存在将添加项目的购物项
		Integer pid = cartItem.getProduct().getPid();
		
		//判断当前map中是否含有即将存入商品的pid
		if(map.containsKey(pid)) {
			//如果已经包含,则从原有中获得 并增加其数量
			CartItem cartItemBefore = map.get(pid);
			cartItemBefore.setCount(cartItemBefore.getCount() + cartItem.getCount());
		}else {
			map.put(cartItem.getProduct().getPid(), cartItem);
		}
		
		total += cartItem.getSubtotal();
	}


}
