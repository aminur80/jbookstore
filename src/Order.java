
import java.util.ArrayList;

public class Order {

	private String orderNo;

	private ArrayList<CartItem> shoppingCart;

	public Order(String orderNo) {
		this.orderNo = orderNo;
	}

	public void addItemToOrder(CartItem cartItem) {
		if (this.shoppingCart == null) {
			this.shoppingCart = new ArrayList<>();
		}
		this.shoppingCart.add(cartItem);
	}

	public ArrayList<CartItem> getShoppingCart() {
		return this.shoppingCart;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

}
